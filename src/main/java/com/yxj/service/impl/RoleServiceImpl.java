package com.yxj.service.impl;

import com.yxj.dao.BaseDao;
import com.yxj.entity.security.Right;
import com.yxj.entity.security.Role;
import com.yxj.service.RightService;
import com.yxj.service.RoleService;
import com.yxj.util.StringUtil;
import com.yxj.util.ValidateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 95 on 2016/12/1.
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Override
    @Resource(name = "roleDao")
    public void setDao(BaseDao<Role> dao) {
        super.setDao(dao);
    }

    @Resource
    private RightService rightService;

    @Override
    public void saveOrUpdateRole(Role r, Integer[] ids) {
        if(!ValidateUtil.isValid(ids)){
            r.getRights().clear();
        }else {
            List<Right> rights = rightService.findRightsInRange(ids);
            r.setRights(new HashSet<Right>(rights));
        }
        this.saveOrUpdateEntity(r);
    }

    @Override
    public List<Role> findRolesInRange(Integer[] ids) {
        if(ValidateUtil.isValid(ids)){
            String hql = "from Role r where r.id in ("+ StringUtil.arr2Str(ids)+")";
            return this.findEntityByHQL(hql);
        }
        return null;
    }
}
