package com.yxj.dataSource;

import com.yxj.entity.Survey;

/**
 * Created by 95 on 2016/12/16.
 */
//令牌
public class SurveyToken {
    //线程本地化对象
    private static ThreadLocal<SurveyToken> l = new ThreadLocal<>();
    //令牌中带有当前survey对象
    private Survey survey;

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    //将指定的令牌绑定到当前线程
    public static void bindToken(SurveyToken token){
        l.set(token);
    }

    //解除当前线程绑定的令牌
    public static void unbindToken(){
        l.remove();
    }

    //从当前线程获得绑定的令牌
    public static SurveyToken getCurrentToken(){
        return l.get();
    }
}
