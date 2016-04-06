<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2016/1/7
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>公告详情</title>
    <link rel="stylesheet" href="/resources/css/content.css" type="text/css"/>
</head>
<body class="sticky-header-on tablet-sticky-header">
    <%@include file="../header.jsp"%>
    <div class="content-div">
    <div class="content-title">
        <h2>标题: ${noticeModel.title}</h2>
        <c:if test="${sessionScope.user != null && sessionScope.user.id == noticeModel.uid}">
            <span>
                <a href="/noticeService/deleteNotice/${noticeModel.id}">删除</a>
                <a href="/noticeService/preUpdateNotice/${noticeModel.id}">编辑</a>
            </span>
        </c:if>
    </div>
    <div class="content">
        <span>内容：${noticeModel.content}</span>
    </div>
    <div class="content-file">
        <c:forEach items="${fileMap.keySet()}" var="key">
            附件
            <form action="/fileService/download" method="post">
                <input name="filename" value="${fileMap.get(key)}" type="hidden"/>
                <input name="path" value="${key}" type="hidden"/>
                <a href="#" onclick="this.parentNode.submit()">${fileMap.get(key)}</a>
            </form>
        </c:forEach>
    </div>
</div>
<%@include file="../footer.jsp"%>
<script type="text/javascript" src="/resources/js/notice.js"></script>
</body>
</html>
