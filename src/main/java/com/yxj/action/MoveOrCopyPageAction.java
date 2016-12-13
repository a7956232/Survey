package com.yxj.action;

import com.yxj.aware.UserAware;
import com.yxj.entity.Page;
import com.yxj.entity.Survey;
import com.yxj.entity.User;
import com.yxj.service.SurveyService;
import com.yxj.util.ActionUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 95 on 2016/11/27.
 */
@Controller("moveOrCopyPageAction")
@Scope("prototype")
public class MoveOrCopyPageAction extends BaseAction<Page> implements UserAware{
    @Resource
    private SurveyService surveyService;
    private User user;
    private List<Survey> mySurveys;
    private Integer srcPid;
    private Integer tarPid;
    private int pos;
    private Integer sid;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getSrcPid() {
        return srcPid;
    }

    public void setSrcPid(Integer srcPid) {
        this.srcPid = srcPid;
    }

    public Integer getTarPid() {
        return tarPid;
    }

    public void setTarPid(Integer tarPid) {
        this.tarPid = tarPid;
    }

    public List<Survey> getMySurveys() {
        return mySurveys;
    }

    public void setMySurveys(List<Survey> mySurveys) {
        this.mySurveys = mySurveys;
    }

    //到达移动复制页列表页面
    public String toSelectTargetPage(){
        mySurveys = surveyService.getSurveyWithPages(user);
        return SUCCESS;
    }

    public String doMoveOrCopyPage(){
        surveyService.moveOrCopyPage(srcPid,tarPid,pos);
        ActionUtil.setUrl("/survey_designSurvey.action?sid="+sid);
        return ActionUtil.REDIRECT;
    }

    @Override
    public void setUser(User user) {
        this.user=user;
    }
}
