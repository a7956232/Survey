package com.yxj.scheduler;

import com.yxj.service.LogService;
import com.yxj.util.LogUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by 95 on 2016/12/15.
 */
//创建日志表的石英任务
public class CreateLogTablesTask extends QuartzJobBean{

    private LogService logService;
    //注入logService
    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    //生成日志表
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //下一月
        String tableName = LogUtil.generateLogTableName(1);
        logService.createLogTable(tableName);
        //下两月
        tableName = LogUtil.generateLogTableName(2);
        logService.createLogTable(tableName);
        //下三月
        tableName = LogUtil.generateLogTableName(3);
        logService.createLogTable(tableName);
    }
}
