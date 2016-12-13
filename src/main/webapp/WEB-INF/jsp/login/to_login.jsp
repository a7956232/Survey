<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/11/21
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<s:include value="/WEB-INF/jsp/header.jsp"/>
<a href="reg_to_reg.action">新用户注册</a></h2>
<div align="center">
    <s:actionerror/>
    <form action="login_login" method="post">
        用户名：<input type="text" name="username" value="ls"><br>
        密码：<input type="password" name="password" value="123"><br>
        <input type="submit" value="登录">
    </form>
</div>
</body>
</html>
