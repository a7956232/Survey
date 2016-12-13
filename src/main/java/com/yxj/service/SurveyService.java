package com.yxj.service;

import com.yxj.entity.*;

import java.util.List;

/**
 * Created by 95 on 2016/11/22.
 */
public interface SurveyService {
    //查询用户调查列表
    List<Survey> findMySurveys(User user);

    //新建调查
    Survey newSurvey(User user);

    //按照id查询Survey对象
    Survey getSurvey(Integer sid);

    //按照id查询Survey对象，同时携带所有子对象
    Survey getSurveyWithChildren(Integer sid);

    //更新调查
    void updateSurvey(Survey s);

    //保存或更新页面
    void saveOrUpdatePage(Page p);

    //根据id查询Page对象
    Page getPage(Integer pid);

    //删除页面，同时删除问题和答案
    void deletePage(Integer pid);

    //保存或更新问题
    void saveOrUpdateQuestion(Question q);

    //删除问题,同时删除答案
    void deleteQuestion(Integer qid);

    //删除调查，同时删除页面，问题，答案
    void deleteSurvey(Integer sid);

    //根据id查询问题
    Question getQuestion(Integer qid);

    //清除调查的答案
    void clearAnswers(Integer sid);

    //切换调查状态
    void toggleStatus(Integer sid);

    //更新logoPhoto路径
    void updateLogoPhotoPath(Integer sid, String s);

    //获得用户所有调查，携带所有页面
    List<Survey> getSurveyWithPages(User user);

    //移动/复制页
    void moveOrCopyPage(Integer srcPid, Integer tarPid, int pos);

    //查询所有开放的调查
    List<Survey> findAllAvailableSurveys();

    //查询调查的首页
    Page getFirstPage(Integer sid);

    //获得上一页
    Page getPrePage(Integer currPid);

    //获得下一页
    Page getNextPage(Integer currPid);

    //批量保存答案
    void saveAnswers(List<Answer> answers);
}
