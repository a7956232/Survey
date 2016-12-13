<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/11/27
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>移动/复制页</title>
</head>
<body>
<s:include value="/WEB-INF/jsp/header.jsp"/>
<table border="1px">
    <tr>
        <td colspan="3">移动/复制页[同一调查内为移动，不同调查间为复制]</td>
    </tr>
    <s:iterator var="s" value="mySurveys">
        <s:set var="sId" value="#s.id"/>
        <tr>
            <td colspan="3"><s:property value="title"/></td>
        </tr>
        <s:iterator var="p" value="#s.pages">
            <s:set var="pId" value="#p.id"/>
            <%--当前页面高亮--%>
            <s:if test="#pId == srcPid">
                <s:set var="bgcolor" value="'#deb887'"/>
            </s:if>
            <s:else>
                <s:set var="bgcolor" value='"bgcolor =\"white\""'/>
            </s:else>
            <tr bgcolor="<s:property value='#bgcolor'/>">
                <td></td>
                <td><s:property value="#p.title"/></td>
                <td>
                    <s:if test="#pId != srcPid">
                        <s:form name="form%{#pId}" action="moveOrCopyPage_doMoveOrCopyPage" method="POST">
                            <s:hidden name="srcPid"/>
                            <s:hidden name="tarPid" value="%{#pId}"/>
                            <s:hidden name="sid" value="%{#sId}"/>
                            <s:radio list="#{0:'之前',1:'之后'}" listKey="key" listValue="value" name="pos"/>
                            <input type="submit" value="确定">
                        </s:form>
                    </s:if>
                </td>
            </tr>
        </s:iterator>
    </s:iterator>
</table>
</body>
</html>
