<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/12/1
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色列表</title>
</head>
<body>
<s:include value="/WEB-INF/jsp/header.jsp"/>
<table>
    <tr>
        <td colspan="10"><s:a action="role_toAddRole" namespace="/">添加角色</s:a></td>
    </tr>
</table>
<s:if test="allRoles.isEmpty() == true">目前您没有任何角色！</s:if>
<s:else>
<table>
    <tr>
        <td colspan="4">角色管理：</td>
    </tr>
    <tr>
        <th>ID</th>
        <th>角色名称</th>
        <th>修改</th>
        <th>删除</th>
    </tr>
    <s:iterator value="allRoles">
        <s:set var="roleId" value="id"/>
        <tr>
            <td><s:property value="id"/></td>
            <td><s:property value="roleName"/></td>
            <td><s:a action="role_editRole?roleId=%{#roleId}" namespace="/">修改</s:a></td>
            <td><s:a action="role_deleteRole?roleId=%{#roleId}" namespace="/">删除</s:a></td>
        </tr>
    </s:iterator>
</table>
</s:else>
</body>
</html>
