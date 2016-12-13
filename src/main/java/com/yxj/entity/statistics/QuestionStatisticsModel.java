package com.yxj.entity.statistics;

import com.yxj.entity.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 95 on 2016/11/29.
 */
//问题统计模型
public class QuestionStatisticsModel {
    //被统计的问题
    private Question question;
    //回答该问题的人数
    private int count;
    //选项统计模型集合
    private List<OptionStatisticsModel> osms = new ArrayList<>();

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<OptionStatisticsModel> getOsms() {
        return osms;
    }

    public void setOsms(List<OptionStatisticsModel> osms) {
        this.osms = osms;
    }
}
