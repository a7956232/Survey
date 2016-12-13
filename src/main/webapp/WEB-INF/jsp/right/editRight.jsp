<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/12/1
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加/修改权限</title>
</head>
<body>
<s:include value="/WEB-INF/jsp/header.jsp"/>
<table>
    <tr>
        <td>保存/更新权限：</td>
    </tr>
    <tr>
        <td>
            <table>
                <tr>
                    <td>
                        <s:form action="right_saveOrUpdateRight" method="POST">
                            <s:hidden name="id"/>
                            <table>
                                <tr>
                                    <td>权限名称</td>
                                    <td><s:textfield name="rightName"/></td>
                                </tr>
                                <tr>
                                    <td>权限URL</td>
                                    <td><s:textfield name="rightUrl"/></td>
                                </tr>
                                <tr>
                                    <td>权限位</td>
                                    <td><s:textfield name="rightPos" readonly="true"/></td>
                                </tr>
                                <tr>
                                    <td>权限码</td>
                                    <td><s:textfield name="rightCode" readonly="true"/></td>
                                </tr>
                                <tr>
                                    <td>公共资源</td>
                                    <td>
                                        <s:checkbox name="common"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>权限描述</td>
                                    <td><s:textarea name="rightDesc" rows="10" cols="41"/></td>
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
