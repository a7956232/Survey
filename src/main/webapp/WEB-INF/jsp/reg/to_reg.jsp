<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/11/21
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
    <form action="reg_reg" method="post">
        用户名：<input type="text" name="username"><s:fielderror><s:param>username</s:param></s:fielderror><br>
        密码：<input type="password" name="password"><s:fielderror><s:param>password</s:param></s:fielderror><br>
        确认密码：<input type="password" name="confirmPassword"><br>
        昵称：<input type="text" name="nickname"><s:fielderror><s:param>nickname</s:param></s:fielderror><br>
        <input type="submit" value="注册">
    </form>
</body>
</html>
