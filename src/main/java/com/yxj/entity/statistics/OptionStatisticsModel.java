package com.yxj.entity.statistics;

/**
 * Created by 95 on 2016/11/29.
 */
//选项统计模型
public class OptionStatisticsModel {
    //选项索引
    private int optionIndex;
    //选项标签
    private String optionLabel;
    //矩阵行索引
    private int matrixRowIndex;
    //矩阵行标签
    private String matrixRowLabel;
    //矩阵列索引
    private int matrixColIndex;
    //矩阵列标签
    private String matrixColLabel;
    //矩阵下拉列表索引
    private int matrixSelectIndex;
    //矩阵下拉列表标签
    private String matrixSelectLabel;
    //选择某选项的人数
    private int count;

    public int getMatrixSelectIndex() {
        return matrixSelectIndex;
    }

    public void setMatrixSelectIndex(int matrixSelectIndex) {
        this.matrixSelectIndex = matrixSelectIndex;
    }

    public String getMatrixSelectLabel() {
        return matrixSelectLabel;
    }

    public void setMatrixSelectLabel(String matrixSelectLabel) {
        this.matrixSelectLabel = matrixSelectLabel;
    }

    public int getOptionIndex() {
        return optionIndex;
    }

    public void setOptionIndex(int optionIndex) {
        this.optionIndex = optionIndex;
    }

    public String getOptionLabel() {
        return optionLabel;
    }

    public void setOptionLabel(String optionLabel) {
        this.optionLabel = optionLabel;
    }

    public int getMatrixRowIndex() {
        return matrixRowIndex;
    }

    public void setMatrixRowIndex(int matrixRowIndex) {
        this.matrixRowIndex = matrixRowIndex;
    }

    public String getMatrixRowLabel() {
        return matrixRowLabel;
    }

    public void setMatrixRowLabel(String matrixRowLabel) {
        this.matrixRowLabel = matrixRowLabel;
    }

    public int getMatrixColIndex() {
        return matrixColIndex;
    }

    public void setMatrixColIndex(int matrixColIndex) {
        this.matrixColIndex = matrixColIndex;
    }

    public String getMatrixColLabel() {
        return matrixColLabel;
    }

    public void setMatrixColLabel(String matrixColLabel) {
        this.matrixColLabel = matrixColLabel;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
