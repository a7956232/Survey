<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/11/26
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增加Logo</title>
</head>
<body>
    <s:include value="/WEB-INF/jsp/header.jsp"/>
    <s:form action="survey_AddLogo" namespace="/" method="POST" enctype="multipart/form-data">
        <s:hidden name="sid"/>
        <table>
            <tr>
                <td>上传Logo</td>
                <td>
                    <s:file name="logoPhoto"/>
                    <s:fielderror name="logoPhoto"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td><s:submit value="确定"/></td>
            </tr>
        </table>
    </s:form>
</body>
</html>
