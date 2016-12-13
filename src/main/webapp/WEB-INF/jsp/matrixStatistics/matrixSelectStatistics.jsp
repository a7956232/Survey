<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/11/30
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>矩阵式下拉列表问题统计</title>
</head>
<body>
<s:include value="/WEB-INF/jsp/header.jsp"/>
<s:set var="qt" value="qsm.question.questionType"/>
<table>
    <tr>
        <td colspan="2">矩阵式下拉列表题型调查结果分析：</td>
    </tr>
    <tr>
        <td colspan="2"><s:property value="qsm.question.title"/></td>
    </tr>
    <tr>
        <td colspan="2" style="text-align: left;display: inline">
            <s:iterator value="qsm.question.matrixSelectOptionArr" status="optst">
                <input type="text" readonly="readonly" style="border: 1px solid blue;background-color: <s:property value='colors[#optst.index]'/>;width: 12px;height: 12px "><s:property/>
            </s:iterator>
        </td>
    </tr>
    <tr>
        <td>
            <table>
                <tr>
                    <th></th>
                    <s:iterator value="qsm.question.matrixColTitleArr">
                        <th><s:property/></th>
                    </s:iterator>
                </tr>
                <s:iterator var="row" value="qsm.question.matrixRowTitleArr" status="rst">
                    <tr>
                        <td><s:property/></td>
                        <s:iterator var="col" value="qsm.question.matrixColTitleArr" status="cst">
                            <td style="text-align: left">
                                <s:iterator var="option" value="qsm.question.matrixSelectOptionArr" status="optst">
                                    <input type="text" style="border: 1px solid <s:property value='colors[#optst.index]'/>;
                                                                 background-color: <s:property value='colors[#optst.index]'/>;
                                                                 width: <s:property value='getPercent(#rst.index,#cst.index,#optst.index)'/>px;
                                                                 height: 12px" readonly="readonly">
                                    <s:property value="getScale(#rst.index,#cst.index,#optst.index)"/><br>
                                </s:iterator>
                            </td>
                        </s:iterator>
                    </tr>
                </s:iterator>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="2" style="text-align: left">共有&nbsp;<s:property value="qsm.count"/>&nbsp;人参与问卷！</td>
    </tr>
</table>
</body>
</html>
