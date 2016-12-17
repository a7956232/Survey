package com.yxj.action;

import com.yxj.dataSource.SurveyToken;
import com.yxj.entity.Page;
import com.yxj.entity.Question;
import com.yxj.entity.Survey;
import com.yxj.entity.statistics.OptionStatisticsModel;
import com.yxj.entity.statistics.QuestionStatisticsModel;
import com.yxj.service.StatisticsService;
import com.yxj.service.SurveyService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.text.DecimalFormat;

/**
 * Created by 95 on 2016/11/30.
 */
@Controller("matrixStatisticsAction")
@Scope("prototype")
public class MatrixStatisticsAction extends BaseAction<Page>{
    private static final long serialVersionUID = -7936963347293412824L;

    private Integer qid;
    private Integer sid;
    private QuestionStatisticsModel qsm;
    private String[] colors = {
            "#ff0000",
            "#00ff00",
            "#0000ff",
            "#ffff00",
            "#ff00ff",
            "#00ffff"
    };
    @Resource
    private StatisticsService ss;
    @Resource
    private SurveyService surveyService;

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public QuestionStatisticsModel getQsm() {
        return qsm;
    }

    public void setQsm(QuestionStatisticsModel qsm) {
        this.qsm = qsm;
    }

    @Override
    public String execute() throws Exception {
        //先进行统计
        Question q = surveyService.getQuestion(qid);
        Survey survey = surveyService.getSurvey(sid);
        //绑定token到当前线程
        SurveyToken token = new SurveyToken();
        //设置当前survey到token中
        token.setSurvey(survey);
        //绑定令牌
        SurveyToken.bindToken(token);
        this.qsm = ss.statistics(q);
        return ""+qsm.getQuestion().getQuestionType();
    }

    //计算每个选项的统计结果
    public String getScale(int rowIndex,int colIndex){
        //问题回答人数
        int qcount = qsm.getCount();
        //选项回答人数
        int ocount = 0;
        for (OptionStatisticsModel osm : qsm.getOsms()){
            if(osm.getMatrixRowIndex() == rowIndex && osm.getMatrixColIndex() == colIndex){
                ocount = osm.getCount();
                break;
            }
        }
        float scale = 0;
        if(qcount != 0){
            scale = (float)ocount/qcount*100;
        }
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("#,###.00");
        return ""+ocount+"("+df.format(scale)+"%)";
    }

    public String getScale(int rowIndex,int colIndex,int optIndex){
        //问题回答人数
        int qcount = qsm.getCount();
        //选项回答人数
        int ocount = 0;
        for (OptionStatisticsModel osm : qsm.getOsms()){
            if(osm.getMatrixRowIndex() == rowIndex && osm.getMatrixColIndex() == colIndex && osm.getMatrixSelectIndex() == optIndex){
                ocount = osm.getCount();
                break;
            }
        }
        float scale = 0;
        if(qcount != 0){
            scale = (float)ocount/qcount*100;
        }
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("#,###.00");
        return ""+ocount+"("+df.format(scale)+"%)";
    }

    //获得百分比的整数部分，作为选项的显示长度
    public int getPercent(int rowIndex,int colIndex,int optIndex){
        //问题回答人数
        int qcount = qsm.getCount();
        //选项回答人数
        int ocount = 0;
        for (OptionStatisticsModel osm : qsm.getOsms()){
            if(osm.getMatrixRowIndex() == rowIndex && osm.getMatrixColIndex() == colIndex && osm.getMatrixSelectIndex() == optIndex){
                ocount = osm.getCount();
                break;
            }
        }
        float scale = 0;
        if(qcount != 0){
            scale = (float)ocount/qcount*100;
        }
        return (int)scale;
    }
}
