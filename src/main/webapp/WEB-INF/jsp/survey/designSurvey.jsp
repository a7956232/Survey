<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 95
  Date: 2016/11/22
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>设计调查</title>
</head>
<body>
    <s:include value="/WEB-INF/jsp/header.jsp"/>
    <s:set var="sId" value="id"/>
    <table width="1000px">
        <tr>
            <td colspan="2">设计调查</td>
        </tr>
        <tr bgcolor="aqua">
            <td>
                <%--logo--%>
                <s:if test="photoExist()">
                    <img src="<s:url value="%{logoPhotoPath}"/>" height="25px" width="50px">
                </s:if>
                <%--调查标题--%>
                <s:property value="title"/>
            </td>
            <td>
                <s:a action="survey_to_AddLogo?sid=%{#sId}" namespace="/">增加Logo</s:a>&nbsp;
                <s:a action="survey_editSurvey?sid=%{#sId}" namespace="/">编辑调查</s:a>&nbsp;
                <s:a action="page_toAddPage?sid=%{#sId}" namespace="/">增加页</s:a>&nbsp;
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <table width="100%">
                    <tr>
                        <td></td>
                        <td>
                            <table width="100%">
                                <%--迭代页面集合--%>
                                <s:iterator var="p" value="pages">
                                    <s:set var="pId" value="#p.id"/>
                                    <tr bgcolor="#faebd7">
                                        <%--页面标题--%>
                                        <td><s:property value="#p.title"/></td>
                                        <td>
                                            <s:a action="page_editPage?sid=%{#sId}&pid=%{#pId}" namespace="/">编辑页标题</s:a>&nbsp;
                                            <s:a action="moveOrCopyPage_toSelectTargetPage?srcPid=%{#pId}" namespace="/">移动/复制页</s:a>&nbsp;
                                            <s:a action="question_toSelectQuestionType?sid=%{#sId}&pid=%{#pId}" namespace="/">增加问题</s:a>&nbsp;
                                            <s:a action="page_deletePage?sid=%{#sId}&pid=%{#pId}" namespace="/">删除页</s:a>&nbsp;
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <table width="100%">
                                                <tr>
                                                    <td></td>
                                                    <td>
                                                        <table width="100%">
                                                            <%--迭代问题集合--%>
                                                            <s:iterator var="q" value="#p.questions">
                                                                <s:set var="qId" value="#q.id"/>
                                                                <tr bgcolor="#adff2f">
                                                                    <%--问题题干--%>
                                                                    <td><s:property value="#q.title"/></td>
                                                                    <td>
                                                                        <s:a action="question_editQuestion?sid=%{#sId}&pid=%{#pId}&qid=%{#qId}" namespace="/">编辑问题</s:a>&nbsp;
                                                                        <s:a action="question_deleteQuestion?sid=%{#sId}&qid=%{#qId}" namespace="/">删除问题</s:a>&nbsp;
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td colspan="2">
                                                                        <%--定义变量，设置第一大类的题型--%>
                                                                        <s:set var="qt" value="#q.questionType"/>
                                                                        <%--判断当前题型是否属于第一大类型（0,1,2,3）--%>
                                                                        <s:if test="#qt lt 4">
                                                                            <s:iterator value="#q.optionArr">
                                                                                <input type="<s:property value='#qt < 2?"radio":"checkbox"'/>"><s:property/>
                                                                                <s:if test="#qt == 1||#qt == 3"><br></s:if>
                                                                            </s:iterator>
                                                                            <%--处理other问题--%>
                                                                            <s:if test="#q.other">
                                                                                <input type="<s:property value='#qt < 2?"radio":"checkbox"'/>">其他
                                                                                <%--文本框--%>
                                                                                <s:if test="#q.otherStyle == 1">
                                                                                    <input type="text">
                                                                                </s:if>
                                                                                <%--下拉列表--%>
                                                                                <s:elseif test="#q.otherStyle == 2">
                                                                                    <select>
                                                                                        <s:iterator value="#q.otherSelectOptionArr">
                                                                                            <option><s:property/></option>
                                                                                        </s:iterator>
                                                                                    </select>
                                                                                </s:elseif>
                                                                            </s:if>
                                                                        </s:if>
                                                                        <%--下拉列表--%>
                                                                        <s:if test="#qt == 4">
                                                                            <select>
                                                                                <s:iterator value="#q.optionArr">
                                                                                    <option><s:property/></option>
                                                                                </s:iterator>
                                                                            </select>
                                                                        </s:if>
                                                                        <%--文本框--%>
                                                                        <s:if test="#qt == 5">
                                                                            <input type="text">
                                                                        </s:if>
                                                                        <%--矩阵问题（6,7,8）--%>
                                                                        <s:if test="#qt > 5">
                                                                            <table width="100%">
                                                                                <%--列头--%>
                                                                                <tr>
                                                                                    <td></td>
                                                                                    <s:iterator value="#q.matrixColTitleArr">
                                                                                        <td><s:property/></td>
                                                                                    </s:iterator>
                                                                                </tr>
                                                                                <%--输出n行--%>
                                                                                <s:iterator value="#q.matrixRowTitleArr">
                                                                                    <tr>
                                                                                        <td><s:property/></td>
                                                                                        <%--套打控件--%>
                                                                                        <s:iterator value="#q.matrixColTitleArr">
                                                                                            <td>
                                                                                                <s:if test="#qt == 6"><input type="radio"></s:if>
                                                                                                <s:if test="#qt == 7"><input type="checkbox"></s:if>
                                                                                                <s:if test="#qt == 8">
                                                                                                    <select>
                                                                                                        <s:iterator value="#q.matrixSelectOptionArr">
                                                                                                            <option><s:property/></option>
                                                                                                        </s:iterator>
                                                                                                    </select>
                                                                                                </s:if>
                                                                                            </td>
                                                                                        </s:iterator>
                                                                                    </tr>
                                                                                </s:iterator>
                                                                            </table>
                                                                        </s:if>
                                                                    </td>
                                                                </tr>
                                                            </s:iterator>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</body>
</html>
