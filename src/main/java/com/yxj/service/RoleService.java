package com.yxj.service;

import com.yxj.entity.security.Role;

import java.util.List;

/**
 * Created by 95 on 2016/12/1.
 */
public interface RoleService extends BaseService<Role> {
    //保存或更新角色
    void saveOrUpdateRole(Role r, Integer[] ids);

    //查找id在指定范围内的角色
    List<Role> findRolesInRange(Integer[] ids);
}
