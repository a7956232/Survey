package com.yxj.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.yxj.entity.security.Right;
import com.yxj.service.RightService;
import com.yxj.util.ValidateUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.util.Map;

/**
 * Created by 95 on 2016/12/2.
 */
//捕获URL拦截器
public class CatchUrlInterceptor implements Interceptor{
    private static final long serialVersionUID = -2334538927318428798L;

    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        ActionProxy proxy = actionInvocation.getProxy();
        //名字空间
        String ns = proxy.getNamespace();
        //action名称
        String actionName = proxy.getActionName();
        if(ValidateUtil.isValid(ns)||ns.equals("/")){
            ns = "";
        }
        String url = ns + "/" +actionName;
        //在application中取得spring容器
//        ApplicationContext ac = (ApplicationContext) actionInvocation.getInvocationContext().getApplication().get(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        ServletContext sc = ServletActionContext.getServletContext();
        ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
        RightService rs = (RightService) ac.getBean("rightService");
        Map<String,Right> map = (Map<String, Right>) sc.getAttribute("all_rights_map");
        if(!map.containsKey(url)){
            rs.appendRightByUrl(url);
        }
        return actionInvocation.invoke();
    }
}
