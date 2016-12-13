<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/11/30
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>矩阵式问题统计</title>
</head>
<body>
<s:include value="/WEB-INF/jsp/header.jsp"/>
<table>
    <tr>
        <td colspan="2">矩阵式题型调查结果分析：</td>
    </tr>
    <tr>
        <td colspan="2"><s:property value="qsm.question.title"/></td>
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
                            <td>
                                <s:property value="getScale(#rst.index,#cst.index)"/>
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
