package com.yxj.action;

import com.yxj.entity.Log;
import com.yxj.service.LogService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 95 on 2016/12/15.
 */
@Controller("logAction")
@Scope("prototype")
public class LogAction extends BaseAction<Log>{
    private static final long serialVersionUID = 1854829617087635451L;
    @Resource
    private LogService logService;

    private List<Log> logs;
    //默认查询的月份数
    private int monthNum = 1;

    public int getMonthNum() {
        return monthNum;
    }

    public void setMonthNum(int monthNum) {
        this.monthNum = monthNum;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    //查询全部日志
    public String findAllLogs(){
        this.logs = logService.findAllEntities();
        return SUCCESS;
    }

    //查询最近的日志
    public String findNearestLogs(){
        this.logs = logService.findNearestLogs(monthNum);
        return SUCCESS;
    }
}
