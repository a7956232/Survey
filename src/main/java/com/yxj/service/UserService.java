package com.yxj.service;

import com.yxj.entity.User;

/**
 * Created by 95 on 2016/11/21.
 */
public interface UserService extends BaseService<User>{
    User validateLoginInfo(String username, String password);

    boolean isRegisted(String username);

    //更新用户授权
    void updateAuthorize(User u, Integer[] ids);

    //清除用户所有授权
    void clearAuthorize(Integer userId);
}
