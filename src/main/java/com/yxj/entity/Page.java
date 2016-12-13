package com.yxj.entity;

import javassist.SerialVersionUID;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 95 on 2016/11/22.
 */
public class Page extends BaseEntity{

    private static final long serialVersionUID = 2560455814295889670L;
    private Integer id;
    private String title = "未命名";
    private float orderno;

    //Page到Survey多对一
    //transient:临时的，该对象不会被深度复制
    private transient Survey survey;

    //Page到Question一对多
    private Set<Question> questions = new HashSet<>();

    public float getOrderno() {
        return orderno;
    }

    public void setOrderno(float orderno) {
        this.orderno = orderno;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if(id != null){
            this.orderno = id;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
