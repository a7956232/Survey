package com.yxj.service.impl;

import com.yxj.dao.BaseDao;
import com.yxj.entity.Log;
import com.yxj.service.LogService;
import com.yxj.util.LogUtil;
import org.hibernate.id.UUIDHexGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 95 on 2016/11/21.
 */
@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {
    @Override
    @Resource(name = "logDao")
    public void setDao(BaseDao<Log> dao) {
        super.setDao(dao);
    }

    @Override
    public void createLogTable(String tableName) {
        String sql = "create table if not exists "+tableName+" like logs";
        this.executeSQL(sql);
    }

    @Override
    public List<Log> findNearestLogs(int n) {
        //select * from logs_2016_12 union select * from logs_2016_11 union ...
        String tableName = LogUtil.generateLogTableName(0);
        //查询出最近的日志表名称
        String sql = "select table_name from information_schema.tables " +
                "where table_schema='survey' " +
                "and table_name like 'logs_%' " +
                "and table_name <= '"+tableName+"' " +
                "order by table_name desc limit 0,"+n;
        List list = this.executeSQLQuery(null,sql);
        //查询最近若干月内的日志
        String logSql = "";
        String logName;
        for(int i=0;i<list.size();i++){
            logName = (String) list.get(i);
            if(i == (list.size()-1)){
                logSql = logSql + " select * from "+logName;
            }else {
                logSql = logSql + " select * from "+logName+" union ";
            }
        }
        //指定Log实体类
        return this.executeSQLQuery(Log.class,logSql);
    }

    //重写该方法，动态插入日志记录（动态表）
    @Override
    public void saveEntity(Log l) {
        //insert into logs_2016_12()
        String sql = "insert into "+ LogUtil.generateLogTableName(0)
                +"(id,operator,operName,operParams,operResult,resultMsg,operTime) values(?,?,?,?,?,?,?)";
        UUIDHexGenerator uuid = new UUIDHexGenerator();
        String id = (String) uuid.generate(null,null);
        this.executeSQL(sql,id,l.getOperator(),l.getOperName(),l.getOperParams(),l.getOperResult(),l.getResultMsg(),l.getOperTime());
    }
}
