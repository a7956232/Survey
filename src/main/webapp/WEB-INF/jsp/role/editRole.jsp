<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/12/2
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加/修改角色</title>
</head>
<body>
<s:include value="/WEB-INF/jsp/header.jsp"/>
<table>
    <tr>
        <td>保存/更新角色：</td>
    </tr>
    <tr>
        <td>
            <table>
                <tr>
                    <td>
                        <s:form action="role_saveOrUpdateRole" method="POST">
                            <s:hidden name="id"/>
                            <table>
                                <tr>
                                    <td>角色名称</td>
                                    <td><s:textfield name="roleName"/></td>
                                </tr>
                                <tr>
                                    <td>角色值</td>
                                    <td><s:textfield name="roleValue"/></td>
                                </tr>
                                <tr>
                                    <td>权限描述</td>
                                    <td><s:textarea name="roleDesc" rows="10" cols="41"/></td>
                                </tr>
                                <tr>
                                    <td>拥有权限</td>
                                    <td>
                                        <s:iterator value="allRights" var="r">
                                            <input type="checkbox"
                                                    name="ownRightIds"
                                                    value="<s:property value='#r.id'/>"
                                                    <s:property value="setTag(#r.id)"/>
                                                    >
                                            <s:property value="#r.rightName"/><br>
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
