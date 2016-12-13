package com.yxj.util;

import com.yxj.service.RightService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;

/**
 * Created by 95 on 2016/12/1.
 */
//提取所有权限工具
public class ExtractAllRightsUtil {
    public static void main(String[] args) throws Exception{
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        RightService rightService = (RightService) ac.getBean("rightService");
        ClassLoader loader = ExtractAllRightsUtil.class.getClassLoader();
        URL url = loader.getResource("com/yxj/action");
        File dir = new File(url.toURI());
        File[] files = dir.listFiles();
        String fname = "";
        for(File f : files){
            fname = f.getName();
            if(fname.endsWith(".class")&&!fname.equals("BaseAction.class")){
                processAction(fname,rightService);
            }
        }
    }

    private static void processAction(String fname,RightService rightService) {
        try {
            String pkgName ="com.yxj.action";
            String simpleClassName = fname.substring(0,fname.indexOf(".class"));
            String className = pkgName + "." + simpleClassName;
            String simpleActionName = simpleClassName.substring(0,1).toLowerCase()+simpleClassName.substring(1,simpleClassName.indexOf("Action"));
            //得到具体类
            Class clazz = Class.forName(className);
            Method[] methods = clazz.getDeclaredMethods();
            Class retType;
            String mName;
            Class[] paramType;
            String url = null;
            for(Method m : methods){
                retType = m.getReturnType();//返回值类型
                mName = m.getName();//方法名称
                paramType = m.getParameterTypes();//参数类型
                if(retType == String.class
                        && !ValidateUtil.isValid(paramType)
                        && Modifier.isPublic(m.getModifiers())){
                    if(mName.equals("execute")){
                        url = "/"+simpleActionName;
                    }else {
                        url = "/"+simpleActionName+"_"+mName;
                    }
                    rightService.appendRightByUrl(url);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
