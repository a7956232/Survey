package com.yxj.entity;

import com.yxj.entity.security.Right;
import com.yxj.entity.security.Role;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 95 on 2016/11/21.
 */
public class User extends BaseEntity{

    private static final long serialVersionUID = 8919642462329712856L;
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    //权限总和，长度与权限位相关
    private long[] rightSum;

    //是否是超级管理员
    private boolean superAdmin;

    //角色集合
    private Set<Role> roles = new HashSet<>();

    public boolean isSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        this.superAdmin = superAdmin;
    }

    public long[] getRightSum() {
        return rightSum;
    }

    public void setRightSum(long[] rightSum) {
        this.rightSum = rightSum;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    //计算用户权限总和
    public void calculateRightSum() {
        int pos = 0;
        long code = 0;
        for(Role role : roles){
            //判断是否是超级管理员
            if("-1".equals(role.getRoleValue())){
                this.superAdmin = true;
                //释放资源
                roles = null;
                return;
            }
            //计算用户权限总和
            for(Right right : role.getRights()){
                pos = right.getRightPos();
                code = right.getRightCode();
                rightSum[pos] = rightSum[pos] | code;
            }
        }
        //释放资源
        roles = null;
    }

    //判断用户是否具有指定权限
    public boolean hasRight(Right r) {
        int pos = r.getRightPos();
        long code = r.getRightCode();
        return !((rightSum[pos] & code) == 0);
    }
}
