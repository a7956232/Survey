<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/11/24
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>设计问题</title>
</head>
<body>
    <s:include value="/WEB-INF/jsp/header.jsp"/>
    <%--根据题型动态返回设计问题页面--%>
    <s:if test="questionType < 4"><s:include value="nonMatrixWithOtherQuestionDesign.jsp"/></s:if>
    <s:if test="questionType == 4"><s:include value="nonMatrixSelectQuestionDesign.jsp"/></s:if>
    <s:if test="questionType == 5"><s:include value="nonMatrixTextQuestionDesign.jsp"/></s:if>
    <s:if test="questionType == 6||questionType == 7"><s:include value="MatrixNormalQuestionDesign.jsp"/></s:if>
    <s:if test="questionType == 8"><s:include value="MatrixSelectQuestionDesign.jsp"/></s:if>
</body>
</html>
