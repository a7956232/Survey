package com.yxj.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by 95 on 2016/12/16.
 */
//自定义数据源路由器（分布式数据库）
public class SurveyDataSourceRouter extends AbstractRoutingDataSource{
    //检测当前的key
    @Override
    protected Object determineCurrentLookupKey() {
        //得到当前线程绑定的令牌
        SurveyToken token = SurveyToken.getCurrentToken();
        if(token != null){
            Integer id = token.getSurvey().getId();
            //解除当前线程绑定的令牌
            SurveyToken.unbindToken();
            return (id % 2 == 0) ? "even" : "odd";
        }
        return null;
    }
}
