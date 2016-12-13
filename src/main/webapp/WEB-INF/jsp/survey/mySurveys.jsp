<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/11/22
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的调查</title>
</head>
<body>
    <s:include value="/WEB-INF/jsp/header.jsp"/>
    <s:if test="mySurveys.isEmpty() == true">目前您没有任何调查</s:if>
    <s:else>
        <table border="1px">
            <tr>
                <td colspan="10">我的调查：</td>
            </tr>
            <tr>
                <th>ID</th>
                <th>调查标题</th>
                <th>创建时间</th>
                <th>状态</th>
                <th>设计</th>
                <th>收集信息</th>
                <th>分析</th>
                <th>打开/关闭</th>
                <th>清除调查</th>
                <th>删除</th>
            </tr>
            <s:iterator value="mySurveys">
                <s:set var="sId" value="id"/>
                <tr>
                    <td><s:property value="id"/></td>
                    <td><s:property value="title"/></td>
                    <td><s:date name="createTime" format="MM/dd/yy HH:mm"/></td>
                    <td>
                        <s:if test="closed">关闭</s:if>
                        <s:else>开放</s:else>
                    </td>
                    <td><s:a action="survey_designSurvey?sid=%{#sId}" namespace="/">设计</s:a></td>
                    <td>收集信息</td>
                    <td><s:a action="survey_analyzeSurvey?sid=%{#sId}" namespace="/">分析</s:a></td>
                    <td><s:a action="survey_toggleStatus?sid=%{#sId}" namespace="/">打开/关闭</s:a></td>
                    <td><s:a action="survey_clearAnswers?sid=%{#sId}" namespace="/" onclick="if(confirm('确定清除该调查所有答案？')==false)return false">清除答案</s:a></td>
                    <td><s:a action="survey_deleteSurvey?sid=%{#sId}" namespace="/" onclick="if(confirm('确定删除该调查？')==false)return false">删除</s:a></td>
                </tr>
            </s:iterator>
        </table>
    </s:else>
</body>
</html>
