package com.yxj.util;

import com.yxj.action.BaseAction;
import com.yxj.aware.UserAware;
import com.yxj.entity.User;
import com.yxj.entity.security.Right;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;

/**
 * Created by 95 on 2016/11/21.
 */
//校验工具类
public class ValidateUtil {
    //判断集合有效性
    public static boolean isValid(Collection col){
        if(col == null || col.isEmpty()){
            return false;
        }
        return true;
    }

    //判断字符串有效性
    public static boolean isValid(String src){
        if(src == null || "".equals(src.trim())){
            return false;
        }
        return true;
    }

    //判断数组有效性
    public static boolean isValid(Object[] arr){
        if(arr == null||arr.length == 0){
            return false;
        }
        return true;
    }

    //判断是否有权限
    public static boolean hasRight(String namespace, String actionName, HttpServletRequest request, BaseAction action){
        if(!ValidateUtil.isValid(namespace)||"/".equals(namespace)){
            namespace = "";
        }
        //将超链接的参数部分滤掉
        if(actionName.contains("?")){
            actionName = actionName.substring(0,actionName.indexOf("?"));
        }
        String url = namespace + "/" + actionName;
        HttpSession session = request.getSession();
        ServletContext sc = session.getServletContext();
        //通过url查询Right对象,避免在拦截器中查询数据库
        //使用spring监听器实现容器初始化完成后立刻将所有权限查询出来并放置到application中。
        //从application中查询所有权限
        Map<String,Right> map = (Map<String, Right>) sc.getAttribute("all_rights_map");
        Right r = map.get(url);
        //公共资源？
        if(r == null||r.isCommon()){
            return true;
        }else {
            User user = (User) session.getAttribute("existUser");
            //登录？
            if(user == null){
                return false;
            }else {
                //useAware处理
                if(action != null && action instanceof UserAware){
                    ((UserAware)action).setUser(user);
                }
                //超级管理员？
                if(user.isSuperAdmin()){
                    return true;
                }else {
                    //有权限？
                    if(user.hasRight(r)){
                        return true;
                    }else {
                        return false;
                    }
                }
            }
        }
    }
}
