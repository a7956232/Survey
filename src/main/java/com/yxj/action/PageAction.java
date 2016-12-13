package com.yxj.action;

import com.yxj.entity.Page;
import com.yxj.entity.Survey;
import com.yxj.service.SurveyService;
import com.yxj.util.ActionUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by 95 on 2016/11/24.
 */
@Controller("pageAction")
@Scope("prototype")
public class PageAction extends BaseAction<Page>{

    private Integer sid;
    private Integer pid;
    @Resource
    private SurveyService surveyService;

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

    //跳转到 添加页面 页面
    public String toAddPage(){
        return SUCCESS;
    }

    //保存或更新页面
    public String saveOrUpdatePage(){
        //维护关联关系
        Survey s =new Survey();
        s.setId(sid);
        model.setSurvey(s);
        surveyService.saveOrUpdatePage(model);
        ActionUtil.setUrl("/survey_designSurvey.action?sid="+sid);
        return ActionUtil.REDIRECT;
    }

    //编辑页面
    public String editPage(){
        this.model = surveyService.getPage(pid);
        return SUCCESS;
    }

    //删除页面
    public String deletePage(){
        surveyService.deletePage(pid);
        ActionUtil.setUrl("/survey_designSurvey.action?sid="+sid);
        return ActionUtil.REDIRECT;
    }


}
