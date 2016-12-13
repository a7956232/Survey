<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/11/24
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>选择题型</title>
</head>
<body>
    <s:include value="/WEB-INF/jsp/header.jsp"/>
    <s:form action="question_toDesignQuestion" method="POST">
        <s:hidden name="pid"/>
        <s:hidden name="sid"/>
        <table>
            <tr>
                <td>
                    <select name="questionType" onchange="this.form.submit();">
                        <option selected="selected">--请选择问题类型--</option>
                        <option value="0">非矩阵式横向单选按钮</option>
                        <option value="1">非矩阵式纵向单选按钮</option>
                        <option value="2">非矩阵式横向复选按钮</option>
                        <option value="3">非矩阵式纵向复选按钮</option>
                        <option value="4">非矩阵式下拉列表</option>
                        <option value="5">非矩阵式文本框</option>
                        <option value="6">矩阵式单选按钮</option>
                        <option value="7">矩阵式复选按钮</option>
                        <option value="8">矩阵式下拉列表</option>
                    </select>
                </td>
            </tr>
        </table>
    </s:form>
</body>
</html>
