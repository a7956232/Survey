package com.yxj.action;

import com.yxj.entity.Answer;
import com.yxj.entity.Page;
import com.yxj.entity.Survey;
import com.yxj.service.SurveyService;
import com.yxj.util.ActionUtil;
import com.yxj.util.StringUtil;
import com.yxj.util.ValidateUtil;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 95 on 2016/11/27.
 */
@Controller("engageSurveyAction")
@Scope("prototype")
public class EngageSurveyAction extends BaseAction<Survey> implements ServletContextAware,SessionAware,ParameterAware{

    private static final String CURRENT_SURVEY = "current_survey";
    private static final String All_PARAMS_MAP = "all_params_map";

    @Resource
    private SurveyService surveyService;
    private List<Survey> surveys;
    private ServletContext sc;
    private Integer sid;
    private Page currPage;
    private Integer currPid;
    private Map<String, Object> sessionMap;
    private Map<String, String[]> paramsMap;

    public Integer getCurrPid() {
        return currPid;
    }

    public void setCurrPid(Integer currPid) {
        this.currPid = currPid;
    }

    public Page getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Page currPage) {
        this.currPage = currPage;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    //到达参与调查界面
    public String toEngageSurveyList(){
        this.surveys = surveyService.findAllAvailableSurveys();
        return SUCCESS;
    }

    //首次进入参与调查
    public String entry(){
        //查询调查首页
        this.currPage = surveyService.getFirstPage(sid);
        //查询当前调查存放到session当中
        sessionMap.put(CURRENT_SURVEY,currPage.getSurvey());
        //将存放所有参数的大map放到session中，用于用户保存答案和回显
        sessionMap.put(All_PARAMS_MAP,new HashMap<Integer,Map<String,String[]>>());
        return SUCCESS;
    }

    //处理参与调查
    public String doEngageSurvey(){
        String submitName = getSubmitName();
        if(submitName.endsWith("pre")){
            //上一步
            mergeParamsIntoSession();
            this.currPage = surveyService.getPrePage(currPid);
            return "entry";
        }else if(submitName.endsWith("next")){
            //下一步
            mergeParamsIntoSession();
            this.currPage = surveyService.getNextPage(currPid);
            return "entry";
        }else if(submitName.endsWith("done")){
            //完成
            mergeParamsIntoSession();
            surveyService.saveAnswers(processAnswers());
            clearSessionData();
            ActionUtil.setUrl("/engageSurvey_toEngageSurveyList");
            return ActionUtil.REDIRECT;

        }else if(submitName.endsWith("exit")){
            //退出
            clearSessionData();
            ActionUtil.setUrl("/engageSurvey_toEngageSurveyList");
            return ActionUtil.REDIRECT;
        }
        return null;
    }

    //处理答案
    private List<Answer> processAnswers() {
        String key;
        String[] value;
        //矩阵式单选按钮的map
        Map<Integer,String> matrixRadioMap = new HashMap<>();
        //所有答案的集合
        List<Answer> answers = new ArrayList<>();
        Answer a;
        Map<Integer,Map<String,String[]>> allMap = getAllParamsMap();
        for(Map<String,String[]> map : allMap.values()){
            for(Map.Entry<String,String[]> entry :map.entrySet()){
                key = entry.getKey();
                value = entry.getValue();
                if(key.startsWith("q")){
                    //处理所有q开头的参数(q1/q2other/q7_1...)
                    if(!key.contains("other") && !key.contains("_")){
                        //常规参数(q1...)
                        a = new Answer();
                        a.setAnswerIds(StringUtil.arr2Str(value));//答案的值（选项索引/文本内容）
                        a.setQuestionId(getQid(key));//答案所属问题id
                        a.setSurveyId(getCurrSurvey().getId());//答案所属调查id
                        //q2other.....
                        a.setOtherAnswer(StringUtil.arr2Str(map.get(key+"other")));//答案其他项的值（选项索引/文本内容）
                        answers.add(a);
                    }else if(key.contains("_")){
                        //矩阵式单选按钮(q7_1,q7_2)
                        Integer radioQid = getMatrixRadioQid(key);
                        String oldValue = matrixRadioMap.get(radioQid);
                        if(oldValue == null){
                            matrixRadioMap.put(radioQid,StringUtil.arr2Str(value));
                        }else {
                            matrixRadioMap.put(radioQid,oldValue + "," + StringUtil.arr2Str(value));
                        }
                    }
                }
            }
        }
        //单独处理矩阵式单选按钮答案
        processMatrixRadioAnswers(matrixRadioMap,answers);
        return answers;
    }

    //单独处理矩阵式单选按钮答案
    private void processMatrixRadioAnswers(Map<Integer, String> map, List<Answer> answers) {
        Integer key;
        String value;
        Answer a;
        for(Map.Entry<Integer,String> entry : map.entrySet()){
            key = entry.getKey();
            value = entry.getValue();
            a = new Answer();
            a.setAnswerIds(value);
            a.setQuestionId(key);
            a.setSurveyId(getCurrSurvey().getId());
            answers.add(a);
        }
    }

    //提取矩阵式问题id（q7_1---7）
    private Integer getMatrixRadioQid(String key) {
        return  Integer.parseInt(key.substring(1,key.lastIndexOf("_")));
    }

    //获取当前Survey对象
    private Survey getCurrSurvey() {
        return (Survey) sessionMap.get(CURRENT_SURVEY);
    }

    //提取问题id（q12--12）
    private Integer getQid(String key) {
        return Integer.parseInt(key.substring(1));
    }

    //清除session数据
    private void clearSessionData() {
        sessionMap.remove(CURRENT_SURVEY);
        sessionMap.remove(All_PARAMS_MAP);
    }

    //合并参数到session中
    private void mergeParamsIntoSession() {
        Map<Integer,Map<String,String[]>> allParamsMap = getAllParamsMap();
        allParamsMap.put(currPid,paramsMap);
    }

    //获取session中存放所有参数的map
    private Map<Integer,Map<String,String[]>> getAllParamsMap() {
        return (Map<Integer, Map<String, String[]>>) sessionMap.get(All_PARAMS_MAP);
    }

    //获得提交按钮的名称（name）
    private String getSubmitName() {
        for(String key : paramsMap.keySet()){
            if(key.startsWith("submit_")){
                return key;
            }
        }
        return null;
    }

    //获得图片的url地址
    public String getImageUrl(String path){
        if(ValidateUtil.isValid(path)){
            String absPath = sc.getRealPath(path);
            File f = new File(absPath);
            if(f.exists()){
                return sc.getContextPath() + path;
            }
        }
        return sc.getContextPath() + "/question.jpg";
    }

    //设置标记，用于radio/checkbox/select答案回显,判断选项是否被选中，选中标记为tag（checked/selected）
    public String setTag(String name,String value,String tag){
        Map<String,String[]> map = getAllParamsMap().get(currPage.getId());
        String[] values = map.get(name);
        if(StringUtil.contains(values,value)){
            return tag;
        }
        return "";
    }

    //设置标记，用于文本框答案回显
    public String setText(String name){
        Map<String,String[]> map = getAllParamsMap().get(currPage.getId());
        String[] values = map.get(name);
        return "value='"+values[0]+"'";
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.sc = servletContext;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = map;
    }

    @Override
    //注入提交的所有参数的map
    public void setParameters(Map<String, String[]> map) {
        this.paramsMap = map;
    }
}
