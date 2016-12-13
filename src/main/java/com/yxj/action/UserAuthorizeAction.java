package com.yxj.action;

import com.yxj.entity.User;
import com.yxj.entity.security.Role;
import com.yxj.service.RoleService;
import com.yxj.service.UserService;
import com.yxj.util.ActionUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by 95 on 2016/12/2.
 */
@Controller("userAuthorizeAction")
@Scope("prototype")
public class UserAuthorizeAction extends BaseAction<User>{

    private static final long serialVersionUID = -3881393609403064435L;

    private List<User> allUsers;
    private List<Role> allRoles;
    private Integer userId;
    private Integer[] ownRoleIds;
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    public Integer[] getOwnRoleIds() {
        return ownRoleIds;
    }

    public void setOwnRoleIds(Integer[] ownRoleIds) {
        this.ownRoleIds = ownRoleIds;
    }

    public List<Role> getAllRoles() {
        return allRoles;
    }

    public void setAllRoles(List<Role> allRoles) {
        this.allRoles = allRoles;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }

    //查询所有用户
    public String findAllUsers(){
        this.allUsers = userService.findAllEntities();
        return SUCCESS;
    }

    //修改用户授权
    public String editAuthorize(){
        this.model = userService.getEntity(userId);
        this.allRoles = roleService.findAllEntities();
        return SUCCESS;
    }

    //更新用户授权
    public String updateAuthorize(){
        userService.updateAuthorize(model,ownRoleIds);
        ActionUtil.setUrl("/userAuthorize_findAllUsers.action");
        return ActionUtil.REDIRECT;
    }

    //清除用户所有授权
    public String clearAuthorize(){
        userService.clearAuthorize(userId);
        ActionUtil.setUrl("/userAuthorize_findAllUsers.action");
        return ActionUtil.REDIRECT;
    }

    //设置标记，用于用户已有角色在checkbox的回显
    public String setTag(Integer id){
        Set<Role> roles = model.getRoles();
        for(Role r:roles){
            if(r.getId() == id){
                return "checked";
            }
        }
        return "";
    }
}
