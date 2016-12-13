package com.yxj.action;

import com.yxj.entity.Page;
import com.yxj.entity.Question;
import com.yxj.service.SurveyService;
import com.yxj.util.ActionUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by 95 on 2016/11/24.
 */
@Controller("questionAction")
@Scope("prototype")
public class QuestionAction extends BaseAction<Question>{
    private Integer sid;
    private Integer pid;
    private Integer qid;
    @Resource
    private SurveyService surveyService;

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    //到达选择题型页面
    public String toSelectQuestionType(){
        return SUCCESS;
    }

    //到达问题设计页面
    public String toDesignQuestion(){
        return SUCCESS;
    }

    //保存或更新问题
    public String saveOrUpdateQuestion(){
        //维护关联关系
        Page p = new Page();
        p.setId(pid);
        model.setPage(p);
        surveyService.saveOrUpdateQuestion(model);
        ActionUtil.setUrl("/survey_designSurvey.action?sid="+sid);
        return ActionUtil.REDIRECT;
    }

    //删除问题
    public String deleteQuestion(){
        surveyService.deleteQuestion(qid);
        ActionUtil.setUrl("/survey_designSurvey.action?sid="+sid);
        return ActionUtil.REDIRECT;
    }

    public String editQuestion(){
        this.model = surveyService.getQuestion(qid);
        return SUCCESS;
    }
}
