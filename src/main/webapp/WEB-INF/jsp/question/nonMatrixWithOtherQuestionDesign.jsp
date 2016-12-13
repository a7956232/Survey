<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/11/24
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>非矩阵问题设计</title>
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
                <td>问题选项：</td>
                <td><s:textarea cols="41" rows="8" name="options"/></td>
            </tr>
            <tr>
                <td>是否含有其他项：</td>
                <td><s:checkbox name="other"/></td>
            </tr>
            <tr>
                <td>其他项类型：</td>
                <td>
                    <s:radio list="#{0:'无',1:'文本框',2:'下拉列表'}" listKey="key" listValue="value" name="otherStyle"/>
                </td>
            </tr>
            <tr>
                <td>其他项下拉列表选项</td>
                <td><s:textarea cols="41" rows="8" name="otherSelectOptions"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" name="ok" value="确定"></td>
            </tr>
        </table>
    </s:form>
</body>
</html>
