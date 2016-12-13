<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/11/21
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功</title>
</head>
<body>
<div align="center">
    <h3>欢迎您，<s:property value="#session.existUser.nickname"/></h3><br>
    <s:include value="/WEB-INF/jsp/header.jsp"/>
</div>
</body>
</html>
