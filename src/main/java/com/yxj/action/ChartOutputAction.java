package com.yxj.action;

import com.yxj.entity.Page;
import com.yxj.entity.statistics.OptionStatisticsModel;
import com.yxj.entity.statistics.QuestionStatisticsModel;
import com.yxj.service.StatisticsService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 95 on 2016/11/30.
 */
//图表输出action
@Controller("chartOutputAction")
@Scope("prototype")
public class ChartOutputAction extends BaseAction<Page>{

    private static final long serialVersionUID = -8084788772700839775L;
    private static final int CHARTTYPE_PIE_2D = 0;
    private static final int CHARTTYPE_PIE_3D = 1;
    private static final int CHARTTYPE_BAR_2D_H = 2;
    private static final int CHARTTYPE_BAR_2D_V = 3;
    private static final int CHARTTYPE_BAR_3D_H = 4;
    private static final int CHARTTYPE_BAR_3D_V = 5;
    private static final int CHARTTYPE_LINE_2D = 6;
    private static final int CHARTTYPE_LINE_3D = 7;
    //问题id
    private Integer qid;
    //图表类型
    private int chartType;
    @Resource
    private StatisticsService ss;

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public int getChartType() {
        return chartType;
    }

    public void setChartType(int chartType) {
        this.chartType = chartType;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

//    //采用流的形式输出图表chart--ByteArrayOutputStream--ByteArrayInputStream--servlet/OutputStream
//    public InputStream getIs(){
//        try {
//            //统计问题
//            QuestionStatisticsModel qsm = ss.statistics(qid);
//            //构造饼图数据集
//            DefaultPieDataset ds = new DefaultPieDataset();
//            for(OptionStatisticsModel osm : qsm.getOsms()){
//                ds.setValue(osm.getOptionLabel(),osm.getCount());
//            }
//            //生成饼图
//            JFreeChart chart = ChartFactory.createPieChart(qsm.getQuestion().getTitle(),ds,true,false,false);
//            //设置字体
//            chart.getTitle().setFont(new Font("宋体",Font.BOLD,25));
//            chart.getLegend().setItemFont(new Font("宋体",Font.BOLD,15));
//            PiePlot plot = (PiePlot) chart.getPlot();
//            plot.setLabelFont(new Font("宋体",Font.BOLD,15));
//
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ChartUtilities.writeChartAsJPEG(baos,chart,400,300);
//            return new ByteArrayInputStream(baos.toByteArray());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    //使用Struts2整合JfreeChart插件输出图表chart--servlet/OutputStream
    public JFreeChart getChart(){
        JFreeChart chart = null;
        try {
            //Font font = new Font("宋体",Font.BOLD,25);
            //统计问题
            QuestionStatisticsModel qsm = ss.statistics(qid);
            DefaultPieDataset pieds = null;//饼图数据集
            DefaultCategoryDataset cateds = null;//种类数据集
            //构造数据集
            if(chartType < 2){
                pieds = new DefaultPieDataset();
                for(OptionStatisticsModel osm : qsm.getOsms()){
                    pieds.setValue(osm.getOptionLabel(),osm.getCount());
                }
            }else {
                cateds = new DefaultCategoryDataset();
                for(OptionStatisticsModel osm : qsm.getOsms()){
                    cateds.addValue(osm.getCount(),osm.getOptionLabel(),"");
                }
            }
            //判断要求的图形
            switch (chartType){
                case CHARTTYPE_PIE_2D://平面饼图
                    chart = ChartFactory.createPieChart(qsm.getQuestion().getTitle(),pieds,true,false,false);
                    break;
                case CHARTTYPE_PIE_3D://3D饼图
                    chart = ChartFactory.createPieChart3D(qsm.getQuestion().getTitle(),pieds,true,false,false);
                    //设置前景色透明度
                    chart.getPlot().setForegroundAlpha(0.6f);
                    break;
                case CHARTTYPE_BAR_2D_H://平面条形图垂直
                    chart = ChartFactory.createBarChart(qsm.getQuestion().getTitle(),"","",cateds, PlotOrientation.HORIZONTAL,true,true,true);
                    break;
                case CHARTTYPE_BAR_2D_V://平面条形图水平
                    chart = ChartFactory.createBarChart(qsm.getQuestion().getTitle(),"","",cateds, PlotOrientation.VERTICAL,true,true,true);
                    break;
                case CHARTTYPE_BAR_3D_H://3D条形图垂直
                    chart = ChartFactory.createBarChart3D(qsm.getQuestion().getTitle(),"","",cateds, PlotOrientation.HORIZONTAL,true,true,true);
                    break;
                case CHARTTYPE_BAR_3D_V://3D条形图水平
                    chart = ChartFactory.createBarChart3D(qsm.getQuestion().getTitle(),"","",cateds, PlotOrientation.VERTICAL,true,true,true);
                    break;
                case CHARTTYPE_LINE_2D://平面折线图
                    chart = ChartFactory.createLineChart(qsm.getQuestion().getTitle(),"","",cateds, PlotOrientation.VERTICAL,true,true,true);
                    break;
                case CHARTTYPE_LINE_3D://3D折线图
                    chart = ChartFactory.createLineChart3D(qsm.getQuestion().getTitle(),"","",cateds, PlotOrientation.VERTICAL,true,true,true);
                    break;
            }
            //设置标题和提示条中文
            chart.getTitle().setFont(new Font("宋体",Font.BOLD,25));
            chart.getLegend().setItemFont(new Font("宋体",Font.BOLD,15));
            if(chart.getPlot() instanceof PiePlot){
                //设置饼图特效
                PiePlot piePlot = (PiePlot) chart.getPlot();
                piePlot.setLabelFont(new Font("宋体",Font.BOLD,15));
                piePlot.setExplodePercent(0,0.1);
                piePlot.setStartAngle(-15);
                piePlot.setDirection(Rotation.CLOCKWISE);
                piePlot.setNoDataMessage("No data to display");
                //定制标签{0}:标题；{1}:数量；{2}:百分比；{3}:总和
                piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({1}/{3})--{2}"));
            }else{
                //设置非饼图效果
                chart.getCategoryPlot().getRangeAxis().setLabelFont(new Font("宋体",Font.BOLD,15));
                chart.getCategoryPlot().getRangeAxis().setTickLabelFont(new Font("宋体",Font.BOLD,15));
                chart.getCategoryPlot().getDomainAxis().setLabelFont(new Font("宋体",Font.BOLD,15));
                chart.getCategoryPlot().getDomainAxis().setTickLabelFont(new Font("宋体",Font.BOLD,15));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chart;
    }
}
