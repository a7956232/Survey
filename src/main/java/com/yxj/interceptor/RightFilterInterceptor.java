package com.yxj.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.yxj.action.BaseAction;
import com.yxj.aware.UserAware;
import com.yxj.entity.User;
import com.yxj.entity.security.Right;
import com.yxj.util.ValidateUtil;
import org.apache.struts2.ServletActionContext;

import java.util.Map;

/**
 * Created by 95 on 2016/11/22.
 */
public class RightFilterInterceptor implements Interceptor {
    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        BaseAction action = (BaseAction) ai.getAction();
        ActionProxy proxy = ai.getProxy();
        String ns = proxy.getNamespace();
        String actionName = proxy.getActionName();
        if(ValidateUtil.hasRight(ns,actionName, ServletActionContext.getRequest(),action)){
            return ai.invoke();
        }else {
            return "login";
        }
    }

}
