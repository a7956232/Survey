<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/11/24
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <s:form action="question_saveOrUpdateQuestion" method="POST">
        <s:hidden name="id"/>
        <s:hidden name="questionType"/>
        <s:hidden name="pid"/>
        <s:hidden name="sid"/>
        <table>
            <tr>
                <td colspan="2">非矩阵问题设计：</td>
            </tr>
            <tr>
                <td>问题标题：</td>
                <td><s:textfield name="title"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" name="ok" value="确定"></td>
            </tr>
        </table>
    </s:form>
</body>
</html>
