<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/11/27
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>参与调查</title>
</head>
<body>
<s:include value="/WEB-INF/jsp/header.jsp"/>
<s:if test="surveys.isEmpty()">目前没有可参与的调查</s:if>
<s:else>
    <s:set var="cells" value="5"/>
    <table>
        <tr>
            <td colspan="<s:property value='#cells'/>">参与调查</td>
        </tr>
        <tr>
            <td colspan="<s:property value='#cells'/>">请选择要参与的调查</td>
        </tr>
        <s:iterator var="i" begin="0" end="%{surveys.size-1}" step="#cells">
            <s:set var="sId" value="id"/>
            <tr>
                <s:iterator var="j" begin="0" end="%{#cells-1}" step="1">
                    <s:set var="idx" value="#i+#j"/>
                    <td width="<s:property value='100/#cells'/>%" align="center">
                        <s:if test="#idx < surveys.size">
                            <s:a action="engageSurvey_entry?sid=%{surveys[#idx].id}">
                                <img src='<s:property value="getImageUrl(surveys[#idx].logoPhotoPath)"/>'
                                     alt='<s:property value="surveys[#idx].title"/>'
                                     height="80px"
                                     width="80px"
                                     border="1px">
                                <br>
                                <div align="center"><s:property value="#idx+1"/>.<s:property value="surveys[#idx].title"/></div>
                            </s:a>
                        </s:if>
                    </td>
                </s:iterator>
            </tr>
        </s:iterator>
    </table>
</s:else>
</body>
</html>
