<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/12/2
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户权限管理</title>
</head>
<body>
<s:include value="/WEB-INF/jsp/header.jsp"/>
<table>
    <tr>用户权限管理：</tr>
    <tr>
        <th>序号</th>
        <th>ID</th>
        <th>用户名</th>
        <th>昵称</th>
        <th>修改授权</th>
        <th>清除授权</th>
    </tr>
    <s:iterator value="allUsers" status="st">
        <s:set var="userId" value="id"/>
        <tr>
            <td><s:property value="#st.count"/></td>
            <td><s:property value="id"/></td>
            <td><s:property value="username"/></td>
            <td><s:property value="nickname"/></td>
            <td><s:a action="userAuthorize_editAuthorize?userId=%{#userId}" namespace="/">修改授权</s:a></td>
            <td><s:a action="userAuthorize_clearAuthorize?userId=%{#userId}" namespace="/">清除授权</s:a></td>
        </tr>
    </s:iterator>
</table>
</body>
</html>
