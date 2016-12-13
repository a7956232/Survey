package com.yxj.service.impl;

import com.yxj.dao.BaseDao;
import com.yxj.entity.User;
import com.yxj.entity.security.Role;
import com.yxj.service.RoleService;
import com.yxj.service.UserService;
import com.yxj.util.ValidateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 95 on 2016/11/21.
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
    @Override
    @Resource(name = "userDao")
    public void setDao(BaseDao<User> dao) {
        super.setDao(dao);
    }
    @Resource
    private RoleService roleService;

    @Override
    public User validateLoginInfo(String username, String password) {
        String hql = "from User u where u.username = ? and u.password = ?";
        List<User> list = this.findEntityByHQL(hql,username,password);
        return ValidateUtil.isValid(list)?list.get(0):null;
    }

    @Override
    public boolean isRegisted(String username) {
        String hql = "from User u where u.username = ?";
        List<User> list = this.findEntityByHQL(hql,username);
        return ValidateUtil.isValid(list);
    }

    @Override
    public void updateAuthorize(User u, Integer[] ids) {
        //一定要查询在db中的心用户，否则会将用户信息改掉
        User newUser = this.getEntity(u.getId());
        if(!ValidateUtil.isValid(ids)){
            newUser.getRoles().clear();
        }else {
            List<Role> roles = roleService.findRolesInRange(ids);
            newUser.setRoles(new HashSet<Role>(roles));
        }
    }

    @Override
    public void clearAuthorize(Integer userId) {
        this.getEntity(userId).getRoles().clear();
    }
}
