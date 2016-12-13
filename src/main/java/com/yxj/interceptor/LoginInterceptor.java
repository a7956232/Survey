package com.yxj.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.yxj.action.BaseAction;
import com.yxj.action.LoginAction;
import com.yxj.action.RegAction;
import com.yxj.aware.UserAware;
import com.yxj.entity.User;

/**
 * Created by 95 on 2016/11/22.
 */
public class LoginInterceptor implements Interceptor {
    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        BaseAction action = (BaseAction) ai.getAction();
        if(action instanceof LoginAction||action instanceof RegAction){
            return ai.invoke();
        }else {
            User user = (User) ai.getInvocationContext().getSession().get("existUser");
            if(user == null){
                return "login";
            }else {
                //为实现了UserAware接口的action注入user对象
                if(action instanceof UserAware){
                    ((UserAware) action).setUser(user);
                }
                return ai.invoke();
            }
        }
    }
}
