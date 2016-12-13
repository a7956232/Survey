<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/12/1
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>权限列表</title>
</head>
<body>
<s:include value="/WEB-INF/jsp/header.jsp"/>
<table>
    <tr>
        <td colspan="10"><s:a action="right_toAddRight" namespace="/">添加权限</s:a></td>
    </tr>
</table>
<s:if test="allRights.isEmpty() == true">目前您没有任何权限！</s:if>
<s:else>
    <s:form action="right_batchUpdateRights" namespace="/" method="POST">
        <table>
            <thead>
            <tr>
                <td colspan="10">权限管理：</td>
            </tr>
            <tr>
               <th>ID</th>
               <th>权限名称</th>
               <th>公共资源</th>
               <th>权限URL</th>
               <th>权限位</th>
               <th>权限码</th>
               <th>修改</th>
               <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="allRights" status="st">
                <s:set var="rightId" value="id"/>
                <tr>
                    <td>
                        <s:textfield name="allRights[%{#st.index}].id" readonly="true"/>
                    </td>
                    <td>
                        <s:textfield name="allRights[%{#st.index}].rightName" cssStyle="width: 100%"/>
                    </td>
                    <td style="text-align: left">
                        <s:checkbox name="allRights[%{#st.index}].common"/>
                    </td>
                    <td><s:property value="rightUrl"/></td>
                    <td><s:property value="rightPos"/></td>
                    <td><s:property value="rightCode"/></td>
                    <td><s:a action="right_editRight?rightId=%{#rightId}" namespace="/">修改</s:a></td>
                    <td><s:a action="right_deleteRight?rightId=%{#rightId}" namespace="/">删除</s:a></td>
                </tr>
            </s:iterator>
            </tbody>
            <tr>
                <td colspan="10"><s:submit value="提交"/></td>
            </tr>
        </table>
    </s:form>
</s:else>
</body>
</html>
