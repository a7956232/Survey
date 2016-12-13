package com.yxj.service.impl;

import com.yxj.dao.BaseDao;
import com.yxj.entity.security.Right;
import com.yxj.service.RightService;
import com.yxj.util.StringUtil;
import com.yxj.util.ValidateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 95 on 2016/12/1.
 */
@Service("rightService")
public class RightServiceImpl extends BaseServiceImpl<Right> implements RightService{
    @Override
    @Resource(name = "rightDao")
    public void setDao(BaseDao<Right> dao) {
        super.setDao(dao);
    }

    @Override
    public void saveOrUpdateRight(Right r) {
        int pos = 0;
        long code = 1L;
        if(r.getId() == null){
            //插入操作
//            此段代码每次插入都要进行全表扫描性能太低
//            String hql = "from Right r order by r.rightPos desc,r.rightCode desc";
//            List<Right> rights = this.findEntityByHQL(hql);
//            if(!ValidateUtil.isValid(rights)){
//                pos = 0;
//                code = 1L;
//            }else {
//                //得到最上面的right
//                Right top = rights.get(0);
//                int topPos = top.getRightPos();
//                long topCode = top.getRightCode();
//                //判断权限码是否达到最大值
//                if(topCode >= (1L<<60)){
//                    pos = topPos + 1;
//                    code = 1L;
//                }else {
//                    pos = topPos;
//                    code = topCode << 1;
//                }
//            }
            //使用聚集函数查询提高性能
            String hql = "select max(r.rightPos),max(r.rightCode) from Right r where r.rightPos = (select max(rr.rightPos) from Right rr)";
            Object[] arr = (Object[]) this.uniqueResult(hql);
            Integer topPos = (Integer) arr[0];
            Long topCode = (Long) arr[1];
            if(topPos == null){
                //没有权限
                pos = 0;
                code = 1L;
            }else {
                //权限码是否达到最大值
                if(topCode >= (1L<<60)){
                    pos = topPos + 1;
                    code = 1L;
                }else {
                    pos = topPos;
                    code = topCode << 1;
                }
            }
            r.setRightPos(pos);
            r.setRightCode(code);
        }
        this.saveOrUpdateEntity(r);
    }

    @Override
    public void appendRightByUrl(String url) {
        String hql = "select count(*) from Right r where r.rightUrl = ?";
        Long count = (Long) this.uniqueResult(hql,url);
        if(count == 0){
            Right r = new Right();
            r.setRightUrl(url);
            this.saveOrUpdateRight(r);
        }
    }

    @Override
    public void batchUpdateRights(List<Right> rights) {
        if(ValidateUtil.isValid(rights)){
            String hql = "update Right r set r.rightName = ?,r.common = ? where r.id = ?";
            for(Right r:rights){
                this.batchEntityByHQL(hql,r.getRightName(),r.isCommon(),r.getId());
            }
        }
    }

    @Override
    public List<Right> findRightsInRange(Integer[] ids) {
        if(ValidateUtil.isValid(ids)){
            String hql = "from Right r where r.id in ("+ StringUtil.arr2Str(ids)+")";
            return this.findEntityByHQL(hql);
        }
        return null;
    }

    @Override
    public int getMaxRightPos() {
        String hql = "select max(r.rightPos) from Right r";
        Integer pos = (Integer) this.uniqueResult(hql);
        return pos == null ? 0 : pos;
    }
}
