package com.yxj.action;

import com.yxj.entity.security.Right;
import com.yxj.entity.security.Role;
import com.yxj.service.RightService;
import com.yxj.service.RoleService;
import com.yxj.util.ActionUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by 95 on 2016/12/2.
 */
@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{

    private static final long serialVersionUID = 2994242928751680127L;

    @Resource
    private RoleService roleService;
    @Resource
    private RightService rightService;

    private Integer roleId;

    private List<Role> allRoles;
    private List<Right> allRights;

    //角色拥有的权限id数组
    private Integer[] ownRightIds;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer[] getOwnRightIds() {
        return ownRightIds;
    }

    public void setOwnRightIds(Integer[] ownRightIds) {
        this.ownRightIds = ownRightIds;
    }

    public List<Right> getAllRights() {
        return allRights;
    }

    public void setAllRights(List<Right> allRights) {
        this.allRights = allRights;
    }

    public List<Role> getAllRoles() {
        return allRoles;
    }

    public void setAllRoles(List<Role> allRoles) {
        this.allRoles = allRoles;
    }

    //查询所有角色
    public String findAllRoles(){
        this.allRoles = roleService.findAllEntities();
        return SUCCESS;
    }

    //到达添加角色界面
    public String toAddRole(){
        this.allRights = rightService.findAllEntities();
        return SUCCESS;
    }

    //保存或更新角色
    public String saveOrUpdateRole(){
        roleService.saveOrUpdateRole(model,ownRightIds);
        ActionUtil.setUrl("/role_findAllRoles.action");
        return ActionUtil.REDIRECT;
    }

    //修改角色
    public String editRole(){
        this.allRights = rightService.findAllEntities();
        this.model = roleService.getEntity(roleId);
        return SUCCESS;
    }

    public String deleteRole(){
        Role r = new Role();
        r.setId(roleId);
        roleService.deleteEntity(r);
        ActionUtil.setUrl("/role_findAllRoles.action");
        return ActionUtil.REDIRECT;
    }

    //设置标记，用于角色已有权限在checkbox的回显
    public String setTag(Integer id){
        Set<Right> rights = model.getRights();
        for(Right r:rights){
            if(r.getId() == id){
                return "checked";
            }
        }
        return "";
    }
}
