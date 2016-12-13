package com.yxj.action;

import com.yxj.entity.security.Right;
import com.yxj.service.RightService;
import com.yxj.util.ActionUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 95 on 2016/12/1.
 */
@Controller("rightAction")
@Scope("prototype")
public class RightAction extends BaseAction<Right>{

    private static final long serialVersionUID = 1700770489936452254L;

    private List<Right> allRights;
    private Integer rightId;
    @Resource
    private RightService rightService;

    public Integer getRightId() {
        return rightId;
    }

    public void setRightId(Integer rightId) {
        this.rightId = rightId;
    }

    public List<Right> getAllRights() {
        return allRights;
    }

    public void setAllRights(List<Right> allRights) {
        this.allRights = allRights;
    }

    //查询所有权限
    public String findAllRights(){
        this.allRights = rightService.findAllEntities();
        return SUCCESS;
    }

    //到达添加权限界面
    public String toAddRight(){
        return SUCCESS;
    }

    //保存或更新权限
    public String saveOrUpdateRight(){
        rightService.saveOrUpdateRight(model);
        ActionUtil.setUrl("/right_findAllRights.action");
        return ActionUtil.REDIRECT;
    }

    //修改权限
    public String editRight(){
        this.model = rightService.getEntity(rightId);
        return SUCCESS;
    }

    //删除权限
    public String deleteRight(){
        Right r = new Right();
        r.setId(rightId);
        rightService.deleteEntity(r);
        ActionUtil.setUrl("/right_findAllRights.action");
        return ActionUtil.REDIRECT;
    }

    //批量修改权限
    public String batchUpdateRights(){
        rightService.batchUpdateRights(allRights);
        ActionUtil.setUrl("/right_findAllRights.action");
        return ActionUtil.REDIRECT;
    }
}
