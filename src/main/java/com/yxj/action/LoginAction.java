package com.yxj.action;

import com.yxj.entity.User;
import com.yxj.service.RightService;
import com.yxj.service.UserService;
import com.yxj.util.ActionUtil;
import com.yxj.util.DataUtil;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by 95 on 2016/11/21.
 */
@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware{

    private static final long serialVersionUID = 2110416503160410482L;
    @Resource
    private UserService userService;
    @Resource
    private RightService rightService;

    private Map<String, Object> sessionMap;


    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = map;
    }

    public String to_login(){
        return SUCCESS;
    }

    public String login(){
        return SUCCESS;
    }

    public void validateLogin(){
        User existUser = userService.validateLoginInfo(model.getUsername(), DataUtil.md5(model.getPassword()));
        if(existUser ==null){
            this.addActionError("用户名或密码错误！");
        }else {
            //初始化权限总和数组
            int maxPos = rightService.getMaxRightPos();
            existUser.setRightSum(new long[maxPos+1]);
            //计算用户权限总和
            existUser.calculateRightSum();
            sessionMap.put("existUser",existUser);
        }
    }

    public String toFirstPage(){
        return SUCCESS;
    }

    //注销登录
    public String logout(){
        sessionMap.remove("existUser");
        ActionUtil.setUrl("/login_to_login");
        return ActionUtil.REDIRECT;
    }
}
