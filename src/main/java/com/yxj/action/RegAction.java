package com.yxj.action;

import com.yxj.entity.User;
import com.yxj.service.UserService;
import com.yxj.util.DataUtil;
import com.yxj.util.ValidateUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by 95 on 2016/11/21.
 */
@Controller("regAction")
@Scope("prototype")
public class RegAction extends BaseAction<User> {

    private String confirmPassword;
    @Resource
    private UserService userService;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String to_reg(){
        return SUCCESS;
    }

    public String reg(){
        //密码加密
        model.setPassword(DataUtil.md5(model.getPassword()));
        userService.saveEntity(model);
        return SUCCESS;
    }

    public void validateReg(){
        //1.用户名非空
        if(!ValidateUtil.isValid(model.getUsername())){
            addFieldError("username","用户名不能为空！");
        }
        if(!ValidateUtil.isValid(model.getPassword())){
            addFieldError("password","密码不能为空！");
        }
        if(!ValidateUtil.isValid(model.getNickname())){
            addFieldError("username","昵称不能为空！");
        }
        if(hasErrors()){
            return;
        }
        //2.密码一致
        if(!model.getPassword().equals(confirmPassword)){
            addFieldError("password","密码不一致！");
            return;
        }
        //3.用户名占用
        if(userService.isRegisted(model.getUsername())){
            addFieldError("username","用户名已存在！");
        }
    }
}
