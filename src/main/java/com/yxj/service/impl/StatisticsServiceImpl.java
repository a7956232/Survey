package com.yxj.service.impl;

import com.yxj.dao.BaseDao;
import com.yxj.entity.Answer;
import com.yxj.entity.Question;
import com.yxj.entity.statistics.OptionStatisticsModel;
import com.yxj.entity.statistics.QuestionStatisticsModel;
import com.yxj.service.StatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 95 on 2016/11/29.
 */
//统计服务实现
@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService{

    @Resource(name = "questionDao")
    private BaseDao<Question> questionDao;
    @Resource(name = "answerDao")
    private BaseDao<Answer> answerDao;

    @Override
    public QuestionStatisticsModel statistics(Question q) {
        //设置被统计的问题
        Integer qid = q.getId();
        QuestionStatisticsModel qsm = new QuestionStatisticsModel();
        qsm.setQuestion(q);

        //统计问题回答人数
        String qhql = "select count(*) from Answer a where a.questionId = ?";
        Long qcount = (Long) answerDao.uniqueResult(qhql,qid);
        qsm.setCount(qcount.intValue());

        String ohql = "select count(*) from Answer a where a.questionId = ? and concat(',',a.answerIds,',') like ?";
        Long ocount;

        //统计每个选项的情况
        int qt = q.getQuestionType();
        switch (qt){
            //非矩阵式问题
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                String[] arr = q.getOptionArr();
                OptionStatisticsModel osm;
                for(int i=0;i<arr.length;i++){
                    osm = new OptionStatisticsModel();
                    osm.setOptionIndex(i);
                    osm.setOptionLabel(arr[i]);
                    //统计选择该选项的人数
                    ocount = (Long) answerDao.uniqueResult(ohql,qid,"%,"+i+",%");
                    osm.setCount(ocount.intValue());
                    qsm.getOsms().add(osm);
                }
                //other
                if(q.isOther()){
                    osm = new OptionStatisticsModel();
                    osm.setOptionLabel("其他");
                    //统计选择该选项的人数
                    ocount = (Long) answerDao.uniqueResult(ohql,qid,"%other%");
                    osm.setCount(ocount.intValue());
                    qsm.getOsms().add(osm);
                }
                break;
            //矩阵式问题
            case 6:
            case 7:
            case 8:
                String[] rows = q.getMatrixRowTitleArr();
                String[] cols = q.getMatrixColTitleArr();
                String[] opts = q.getMatrixSelectOptionArr();
                for(int i=0;i<rows.length;i++){
                    for (int j=0;j<cols.length;j++){
                        if(qt != 8){
                            //矩阵式单选/复选题型
                            osm = new OptionStatisticsModel();
                            osm.setMatrixRowIndex(i);
                            osm.setMatrixRowLabel(rows[i]);
                            osm.setMatrixColIndex(j);
                            osm.setMatrixColLabel(cols[j]);
                            //统计选择该选项的人数
                            ocount = (Long) answerDao.uniqueResult(ohql,qid,"%,"+i+"_"+j+",%");
                            osm.setCount(ocount.intValue());
                            qsm.getOsms().add(osm);
                        }else {
                            //矩阵式下拉列表题型
                            for(int k=0;k<opts.length;k++){
                                osm = new OptionStatisticsModel();
                                osm.setMatrixRowIndex(i);
                                osm.setMatrixRowLabel(rows[i]);
                                osm.setMatrixColIndex(j);
                                osm.setMatrixColLabel(cols[j]);
                                osm.setMatrixSelectIndex(k);
                                osm.setMatrixSelectLabel(opts[k]);
                                //统计选择该选项的人数
                                ocount = (Long) answerDao.uniqueResult(ohql,qid,"%,"+i+"_"+j+"_"+k+",%");
                                osm.setCount(ocount.intValue());
                                qsm.getOsms().add(osm);
                            }
                        }
                    }
                }
                break;
        }
        return qsm;
    }
}
