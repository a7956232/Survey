package com.yxj.action;

import com.yxj.aware.UserAware;
import com.yxj.entity.Survey;
import com.yxj.entity.User;
import com.yxj.service.SurveyService;
import com.yxj.util.ActionUtil;
import com.yxj.util.StringUtil;
import com.yxj.util.ValidateUtil;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by 95 on 2016/11/22.
 */
@Controller("surveyAction")
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware,ServletContextAware {
    @Resource
    private SurveyService surveyService;
    private List<Survey> mySurveys;
    //接收UserAware中的user对象
    private User user;
    //接收传来的参数sid
    private Integer sid;

    //上传的文件
    private File logoPhoto;
    //文件名称
    private String logoPhotoFileName;
    private ServletContext sc;

    public File getLogoPhoto() {
        return logoPhoto;
    }

    public void setLogoPhoto(File logoPhoto) {
        this.logoPhoto = logoPhoto;
    }

    public String getLogoPhotoFileName() {
        return logoPhotoFileName;
    }

    public void setLogoPhotoFileName(String logoPhotoFileName) {
        this.logoPhotoFileName = logoPhotoFileName;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public List<Survey> getMySurveys() {
        return mySurveys;
    }

    public void setMySurveys(List<Survey> mySurveys) {
        this.mySurveys = mySurveys;
    }

    //我的调查
    public String mySurveys(){
        this.mySurveys = surveyService.findMySurveys(user);
        return SUCCESS;
    }

    //新建调查
    public String newSurvey(){
        this.model = surveyService.newSurvey(user);
        return SUCCESS;
    }

    //设计调查
    public String designSurvey(){
        this.model = surveyService.getSurveyWithChildren(sid);
        return SUCCESS;
    }

    //编辑调查
    public String editSurvey(){
        this.model = surveyService.getSurvey(sid);
        return SUCCESS;
    }

    //更新调查
    public String updateSurvey(){
        this.sid = model.getId();
        //保持关联关系
        model.setUser(user);
        surveyService.updateSurvey(model);
        ActionUtil.setUrl("/survey_designSurvey.action?sid="+sid);
        return ActionUtil.REDIRECT;
    }

    @Override
    //UserAware的注入user方法
    public void setUser(User user) {
        this.user = user;
    }

    //删除调查
    public String deleteSurvey(){
        surveyService.deleteSurvey(sid);
        ActionUtil.setUrl("/survey_mySurveys.action");
        return ActionUtil.REDIRECT;
    }

    //清除调查答案
    public String clearAnswers(){
        surveyService.clearAnswers(sid);
        ActionUtil.setUrl("/survey_mySurveys.action");
        return ActionUtil.REDIRECT;
    }

    //切换调查状态
    public String toggleStatus(){
        surveyService.toggleStatus(sid);
        ActionUtil.setUrl("/survey_mySurveys.action");
        return ActionUtil.REDIRECT;
    }

    //到达增加logo界面
    public String to_AddLogo(){
        return SUCCESS;
    }

    //完成Logo上传
    public String AddLogo(){
        //实现上传
        if(ValidateUtil.isValid(logoPhotoFileName)){
            //upload文件夹真实路径
            String dir = sc.getRealPath("/upload");
            //文件扩展名
            String ext = logoPhotoFileName.substring(logoPhotoFileName.lastIndexOf("."));
            //纳秒时间作为文件名
            long l = System.nanoTime();
            File newFile = new File(dir,l+ext);
            //文件另存为
            logoPhoto.renameTo(newFile);
            //更新路径
            surveyService.updateLogoPhotoPath(sid,"/upload/"+ l + ext);
        }
        ActionUtil.setUrl("/survey_designSurvey.action?sid="+sid);
        return ActionUtil.REDIRECT;
    }

    //分析调查
    public String analyzeSurvey(){
        this.model = surveyService.getSurveyWithChildren(sid);
        return SUCCESS;
    }

    public boolean photoExist(){
        String path = model.getLogoPhotoPath();
        if(ValidateUtil.isValid(path)){
            String absPath = sc.getRealPath(path);
            File file = new File(absPath);
            return file.exists();
        }
        return false;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.sc = servletContext;
    }
}
