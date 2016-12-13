package com.yxj.service;

import com.yxj.entity.statistics.QuestionStatisticsModel;

/**
 * Created by 95 on 2016/11/29.
 */
//统计服务
public interface StatisticsService {
    public QuestionStatisticsModel statistics(Integer qid);
}
