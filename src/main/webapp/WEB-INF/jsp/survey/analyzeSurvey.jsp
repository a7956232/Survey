<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/11/29
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分析调查</title>
</head>
<body>
<s:include value="/WEB-INF/jsp/header.jsp"/>
<s:set var="sId" value="id"/>
<table>
    <tr>
        <td colspan="2">分析调查：</td>
    </tr>
    <tr>
        <td colspan="2"><s:property value="title"/></td>
    </tr>
    <s:iterator var="p" value="pages" status="pst">
        <s:set var="pId" value="#p.id"/>
        <tr>
            <td colspan="2"><s:property value="#p.title"/></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <table>
                    <s:iterator var="q" value="#p.questions" status="qst">
                        <s:set var="qId" value="#q.id"/>
                        <s:set var="qt" value="#q.questionType"/>
                        <tr>
                            <%--count:从1开始，index:从0开始--%>
                            <td width="60%"><s:property value="#qst.count+'.'+#q.title"/></td>
                            <td align="right">
                                <s:form action="chartOutput" namespace="/" method="POST" target="_blank">
                                    <input type="hidden" name="qid" value="<s:property value='#qId'/>">
                                    <s:if test="#qt > 5">
                                        <%--提交给另外一个action，改变form的提交地址--%>
                                        <s:submit action="matrixStatistics" value="查看矩阵式问题统计结果"/>
                                    </s:if>
                                    <s:elseif test="#qt lt 5">
                                        <s:set var="chartList" value="#{0:'平面饼图',
                                                                           1:'立体饼图',
                                                                           2:'横向平面柱状图',
                                                                           3:'纵向平面柱状图',
                                                                           4:'横向立体柱状图',
                                                                           5:'纵向立体柱状图',
                                                                           6:'平面折线图',
                                                                           7:'立体折线图'}"/>
                                        <s:select name="chartType" list="#chartList" listKey="key" listValue="value"/>
                                        <s:submit value="查看"/>
                                    </s:elseif>
                                </s:form>
                            </td>
                        </tr>
                    </s:iterator>
                </table>
            </td>
        </tr>
    </s:iterator>
</table>
</body>
</html>
