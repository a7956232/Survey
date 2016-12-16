package com.yxj.service;

import com.yxj.entity.Log;

import java.util.List;

/**
 * Created by 95 on 2016/11/21.
 */
public interface LogService extends BaseService<Log>{

    //通过表名创建日志表
    void createLogTable(String tableName);

    //查询最近指定月份数i的日志
    List<Log> findNearestLogs(int i);
}
