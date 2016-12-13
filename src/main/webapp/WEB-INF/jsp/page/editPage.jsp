<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/11/24
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑页面</title>
</head>
<body>
    <s:include value="/WEB-INF/jsp/header.jsp"/>
    <s:form action="page_saveOrUpdatePage" namespace="/" method="POST">
        <s:hidden name="id"/>
        <s:hidden name="sid"/>
        <s:hidden name="orderno"/>
        <table>
            <tr>
                <td>页面标题</td>
                <td><s:textfield name="title"/></td>
            </tr>
            <tr>
                <td></td>
                <td><s:submit value="确定"/></td>
            </tr>
        </table>
    </s:form>
</body>
</html>
