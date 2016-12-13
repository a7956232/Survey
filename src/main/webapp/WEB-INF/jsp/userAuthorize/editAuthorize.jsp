<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/12/2
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户授权</title>
</head>
<body>
<s:include value="/WEB-INF/jsp/header.jsp"/>
<table>
    <tr>
        <td>修改用户授权：</td>
    </tr>
    <tr>
        <td>
            <table>
                <tr>
                    <td>
                        <s:form action="userAuthorize_updateAuthorize" method="POST">
                            <s:hidden name="id"/>
                            <table>
                                <tr>
                                    <td>用户名</td>
                                    <td><s:textfield name="username" disabled="true"/></td>
                                </tr>
                                <tr>
                                    <td>昵称</td>
                                    <td><s:textfield name="nickname" disabled="true"/></td>
                                </tr>
                                <tr>
                                    <td>拥有角色</td>
                                    <td>
                                        <s:iterator value="allRoles" var="r">
                                            <input type="checkbox"
                                                   name="ownRoleIds"
                                                   value="<s:property value='#r.id'/>"
                                                <s:property value="setTag(#r.id)"/>
                                            >
                                            <s:property value="#r.roleName"/><br>
                                        </s:iterator>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><s:submit value="提交"/></td>
                                </tr>
                            </table>
                        </s:form>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
