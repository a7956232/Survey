<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/11/23
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        table{border: solid #000 1px;padding: 0px;width: 1500px;margin: 0 auto;}
        td{border: solid #c4372e 1px;}
        th{border: solid #c4372e 1px;}
    </style>
</head>
<body>
<div align="center">
    <s:a action="login_toFirstPage">首页</s:a>&nbsp;
    <s:a action="survey_newSurvey">新建调查</s:a>&nbsp;
    <s:a action="survey_mySurveys">我的调查</s:a>&nbsp;
    <s:a action="engageSurvey_toEngageSurveyList">参与调查</s:a>&nbsp;
    <s:a action="userAuthorize_findAllUsers">用户授权</s:a>&nbsp;
    <s:a action="role_findAllRoles">角色管理</s:a>&nbsp;
    <s:a action="right_findAllRights">权限管理</s:a>&nbsp;
    <s:a action="log_findNearestLogs">日志管理</s:a>&nbsp;
    <s:a action="login_logout" namespace="/">注销登录</s:a>&nbsp;

</div>
</body>
</html>
