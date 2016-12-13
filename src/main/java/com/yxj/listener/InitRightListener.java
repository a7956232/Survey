package com.yxj.listener;

import com.yxj.entity.security.Right;
import com.yxj.service.RightService;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 95 on 2016/12/3.
 */
//初始化权限监听器，在spring初始化完成后立即调用
@Component
public class InitRightListener implements ApplicationListener,ServletContextAware{
    @Resource
    private RightService rightService;
    //接收servletContext
    private ServletContext sc;

    @Override
    public void onApplicationEvent(ApplicationEvent e) {
        //上下文刷新事件
        if(e instanceof ContextRefreshedEvent){
            //查出所有权限
            List<Right> rights = rightService.findAllEntities();
            Map<String,Right> map = new HashMap<>();
            for(Right r : rights){
                map.put(r.getRightUrl(),r);
            }
            if(sc != null){
                sc.setAttribute("all_rights_map",map);
                System.out.println("--------初始化所有权限到application中--------");
            }
        }
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.sc = servletContext;
    }
}
