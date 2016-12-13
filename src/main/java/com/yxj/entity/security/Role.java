package com.yxj.entity.security;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 95 on 2016/12/1.
 */
//角色
public class Role {
    private Integer id;
    private String roleName;
    private String roleValue;
    private String roleDesc;

    //权限集合
    private Set<Right> rights = new HashSet<>();

    public Set<Right> getRights() {
        return rights;
    }

    public void setRights(Set<Right> rights) {
        this.rights = rights;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(String roleValue) {
        this.roleValue = roleValue;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}
