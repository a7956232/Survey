package com.yxj.service.impl;

import com.yxj.dao.BaseDao;
import com.yxj.entity.*;
import com.yxj.service.SurveyService;
import com.yxj.util.DataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 95 on 2016/11/22.
 */
@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {

    @Resource(name = "surveyDao")
    private BaseDao<Survey> surveyDao;
    @Resource(name = "pageDao")
    private BaseDao<Page> pageDao;
    @Resource(name = "questionDao")
    private BaseDao<Question> questionDao;
    @Resource(name = "answerDao")
    private BaseDao<Answer> answerDao;

    @Override
    public List<Survey> findMySurveys(User user) {
        String hql = "from Survey s where s.user.id = ?";
        return surveyDao.findEntityByHQL(hql,user.getId());
    }

    @Override
    public Survey newSurvey(User user) {
        Survey s = new Survey();
        Page p = new Page();
        //设置关联关系
        s.setUser(user);
        p.setSurvey(s);
        s.getPages().add(p);
        surveyDao.saveEntity(s);
        pageDao.saveEntity(p);
        return s;
    }

    @Override
    public Survey getSurvey(Integer sid) {
        return surveyDao.getEntity(sid);
    }

    @Override
    public Survey getSurveyWithChildren(Integer sid) {
        Survey s = this.getSurvey(sid);
        //初始化pages和questions集合
        for(Page p:s.getPages()){
            p.getQuestions().size();
        }
        return s;
    }

    @Override
    public void updateSurvey(Survey s) {
        surveyDao.updateEntity(s);
    }

    @Override
    public void saveOrUpdatePage(Page p) {
        pageDao.saveOrUpdateEntity(p);
    }

    @Override
    public Page getPage(Integer pid) {
        return pageDao.getEntity(pid);
    }

    @Override
    public void deletePage(Integer pid) {
        //删除答案
        String hql = "delete from Answer a where a.questionId in (select q.id from Question q where q.page.id = ?)";
        answerDao.batchEntityByHQL(hql,pid);
        //删除问题
        hql = "delete from Question q where q.page.id = ?";
        questionDao.batchEntityByHQL(hql,pid);
        //删除页面
        hql = "delete from Page p where p.id = ?";
        pageDao.batchEntityByHQL(hql,pid);
    }

    @Override
    public void saveOrUpdateQuestion(Question q) {
        questionDao.saveOrUpdateEntity(q);
    }

    @Override
    public void deleteQuestion(Integer qid) {
        //删除答案
        String hql = "delete from Answer a where a.questionId = ?";
        answerDao.batchEntityByHQL(hql,qid);
        //删除问题
        hql = "delete from Question q where q.id = ?";
        questionDao.batchEntityByHQL(hql,qid);
    }

    @Override
    public void deleteSurvey(Integer sid) {
        //删除答案
        String hql = "delete from Answer a where a.surveyId = ?";
        //删除问题
        answerDao.batchEntityByHQL(hql,sid);
        //hibernate在写操作中不允许两级以上的链接
        //hql = "delete from Question q where q.page.survey.id = ?";
        hql = "delete from Question q where q.page.id in (select p.id from Page p where p.survey.id = ?)";
        questionDao.batchEntityByHQL(hql,sid);
        //删除页面
        hql = "delete from Page p where p.survey.id = ?";
        pageDao.batchEntityByHQL(hql,sid);
        //删除调查
        hql = "delete from Survey s where s.id = ?";
        surveyDao.batchEntityByHQL(hql,sid);
    }

    @Override
    public Question getQuestion(Integer qid) {
        return questionDao.getEntity(qid);
    }

    @Override
    public void clearAnswers(Integer sid) {
        String hql = "delete from Answer a where a.surveyId = ?";
        answerDao.batchEntityByHQL(hql,sid);
    }

    @Override
    public void toggleStatus(Integer sid) {
        Survey s = this.getSurvey(sid);
        //hql中不支持s.closed = !s.closed
        //String hql = "update Survey s set s.closed = !s.closed where s.id = ?";
        String hql = "update Survey s set s.closed = ? where s.id = ?";
        surveyDao.batchEntityByHQL(hql,!s.isClosed(),sid);
    }

    @Override
    public void updateLogoPhotoPath(Integer sid, String s) {
        String hql = "update Survey s set s.logoPhotoPath = ? where s.id = ?";
        surveyDao.batchEntityByHQL(hql,s,sid);
    }

    @Override
    public List<Survey> getSurveyWithPages(User user) {
        List<Survey> list = this.findMySurveys(user);
        for(Survey s : list){
            s.getPages().size();
        }
        return list;
    }

    @Override
    public void moveOrCopyPage(Integer srcPid, Integer tarPid, int pos) {
        Page srcPage = this.getPage(srcPid);
        Survey srcSurvey = srcPage.getSurvey();
        Page tarPage = this.getPage(tarPid);
        Survey tarSurvey = tarPage.getSurvey();
        //判断移动/复制
        if(srcSurvey.getId().equals(tarSurvey.getId())){
            //移动
            setOrderno(srcPage,tarPage,pos);
        }else {
            //强行初始化原页面问题集合，否则深度复制页面对象没有问题集合
            srcPage.getQuestions().size();
            //深度复制
            Page copyPage = (Page) DataUtil.deeplyCopy(srcPage);
            //设置复制页面和目标调查关联
            copyPage.setSurvey(tarSurvey);
            //保存复制页面
            pageDao.saveEntity(copyPage);
            for(Question q : copyPage.getQuestions()){
                questionDao.saveEntity(q);
            }
            setOrderno(copyPage,tarPage,pos);
        }
    }

    @Override
    public List<Survey> findAllAvailableSurveys() {
        String hql = "from Survey s where s.closed = ?";
        return surveyDao.findEntityByHQL(hql,false);
    }

    @Override
    public Page getFirstPage(Integer sid) {
        String hql = "from Page p where p.survey.id = ? order by p.orderno asc";
        List<Page> list = pageDao.findEntityByHQL(hql,sid);
        Page p = list.get(0);
        //初始化问题集合
        p.getQuestions().size();
        //初始化上级调查对象
        p.getSurvey().getTitle();
        return p;
    }

    @Override
    public Page getPrePage(Integer currPid) {
        Page p = this.getPage(currPid);
        p = this.getPrePage(p);
        p.getQuestions().size();
        return p;
    }

    @Override
    public Page getNextPage(Integer currPid) {
        Page p = this.getPage(currPid);
        p = this.getNextPage(p);
        p.getQuestions().size();
        return p;
    }

    @Override
    public void saveAnswers(List<Answer> list) {
        Date date = new Date();
        String uuid = UUID.randomUUID().toString();
        for(Answer a : list){
            a.setUuid(uuid);
            a.setAnswerTime(date);
            answerDao.saveEntity(a);
        }
    }

    //设置页序
    private void setOrderno(Page srcPage, Page tarPage, int pos) {
        if(pos == 0){
            //之前
            if(isFirstPage(tarPage)){
                //目标页是首页（原页面页序 = 目标页页序 - 0.01）
                srcPage.setOrderno(tarPage.getOrderno() - 0.01f);
            }else {
                //目标页不是首页
                //取得目标页前一页（原页面页序 = (目标页页序 + 目标页前一页页序)/2）
                Page prePage = getPrePage(tarPage);
                srcPage.setOrderno((tarPage.getOrderno()+prePage.getOrderno())/2);
            }
        }else {
            //之后
            if(isLastPage(tarPage)){
                //目标页是尾页（原页面页序 = 目标页页序 + 0.01）
                srcPage.setOrderno(tarPage.getOrderno() + 0.01f);
            }else {
                //目标页不是尾页
                //取得目标页后一页（原页面页序 = (目标页页序 + 目标页后一页页序)/2）
                Page nextPage = getNextPage(tarPage);
                srcPage.setOrderno((tarPage.getOrderno()+nextPage.getOrderno())/2);
            }
        }
    }

    //查询目标页面的下一页
    private Page getNextPage(Page tarPage) {
        String hql = "from Page p where p.survey.id = ? and p.orderno > ? order by p.orderno asc";
        List<Page> list = pageDao.findEntityByHQL(hql,tarPage.getSurvey().getId(),tarPage.getOrderno());
        return list.get(0);
    }

    //查询目标页面的上一页
    private Page getPrePage(Page tarPage) {
        String hql = "from Page p where p.survey.id = ? and p.orderno < ? order by p.orderno desc";
        List<Page> list = pageDao.findEntityByHQL(hql,tarPage.getSurvey().getId(),tarPage.getOrderno());
        return list.get(0);
    }

    //判断目标页面是否是所在调查的尾页
    private boolean isLastPage(Page tarPage) {
        String hql = "select count(*) from Page p where p.survey.id = ? and p.orderno > ?";
        Long count = (Long) pageDao.uniqueResult(hql,tarPage.getSurvey().getId(),tarPage.getOrderno());
        return count == 0;
    }

    //判断目标页面是否是所在调查的首页
    private boolean isFirstPage(Page tarPage) {
        String hql = "select count(*) from Page p where p.survey.id = ? and p.orderno < ?";
        Long count = (Long) pageDao.uniqueResult(hql,tarPage.getSurvey().getId(),tarPage.getOrderno());
        return count == 0;
    }
}
