<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/12/15
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>日志记录</title>
</head>
<body>
<s:include value="/WEB-INF/jsp/header.jsp"/>
<s:if test="logs.isEmpty() == true">没有日志！!</s:if>
<s:else>
    <table>
        <thead>
        <tr>
            <td colspan="5">日志记录</td>
            <td>
                <s:form namespace="/" action="log_findNearestLogs">
                    <select name="monthNum" onchange="this.form.submit()">
                        <option value="1" <s:if test="monthNum == 1">selected="selected"</s:if>>最近1月</option>
                        <option value="2" <s:if test="monthNum == 2">selected="selected"</s:if>>最近2月</option>
                        <option value="3" <s:if test="monthNum == 3">selected="selected"</s:if>>最近3月</option>
                    </select>
                </s:form>
            </td>
        </tr>
        <tr>
            <th>操作人</th>
            <th>操作名称</th>
            <th>参数</th>
            <th>操作结果</th>
            <th>消息</th>
            <th>时间</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="logs" status="st">
            <tr>
                <td><s:property value="operator"/></td>
                <td><s:property value="operName"/></td>
                <td>
                    <span title="<s:property value='operParams'/>"><s:property value="@com.yxj.util.StringUtil@getDescString(operParams)"/></span>
                </td>
                <td>
                    <span title="<s:property value='operResult'/>"><s:property value="@com.yxj.util.StringUtil@getDescString(operResult)"/></span>
                </td>
                <td>
                    <span title="<s:property value='resultMsg'/>"><s:property value="@com.yxj.util.StringUtil@getDescString(resultMsg)"/></span>
                </td>
                <td><s:date name="operTime" format="yy/MM/dd hh:mm:ss"/></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</s:else>
</body>
</html>
