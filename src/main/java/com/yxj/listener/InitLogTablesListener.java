package com.yxj.listener;

import com.yxj.service.LogService;
import com.yxj.util.LogUtil;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by 95 on 2016/12/3.
 */
//初始化日志表监听器
@Component
public class InitLogTablesListener implements ApplicationListener{
    @Resource
    private LogService logService;

    @Override
    public void onApplicationEvent(ApplicationEvent e) {
        //上下文刷新事件
        if(e instanceof ContextRefreshedEvent){
            System.out.println("--------初始化日志表--------");
            String tableName = LogUtil.generateLogTableName(0);
            logService.createLogTable(tableName);

            tableName = LogUtil.generateLogTableName(1);
            logService.createLogTable(tableName);

            tableName = LogUtil.generateLogTableName(2);
            logService.createLogTable(tableName);
            System.out.println("--------初始化日志表完成！！--------");
        }
    }

}
