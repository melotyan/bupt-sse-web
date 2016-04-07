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
        <h2>新闻标题</h2>
        <c:if test="${sessionScope.user != null && sessionScope.user.id == noticeModel.uid}">
            <span>
                <strong><a href="/noticeService/deleteNotice/${noticeModel.id}">删除</a></strong>
                <strong><a href="/noticeService/preUpdateNotice/${noticeModel.id}">编辑</a></strong>
            </span>
        </c:if>
    </div>
    <p>${noticeModel.title}</p>
    <div class="content-title"><h2>新闻内容</h2></div>
    <div class="content">
        <span>${noticeModel.content}</span>
    </div>
    <c:if test="${fileMap.keySet() != null}">
        <div class="content-title"><h2>附件</h2></div>
        <div class="content-file">
            <c:forEach items="${fileMap.keySet()}" var="key">
                <form action="/fileService/download" method="post">
                    <input name="filename" value="${fileMap.get(key)}" type="hidden"/>
                    <input name="path" value="${key}" type="hidden"/>
                    <a href="#" onclick="this.parentNode.submit()">${fileMap.get(key)}</a>
                </form>
            </c:forEach>
        </div>
    </c:if>
</div>
<%@include file="../footer.jsp"%>
<script type="text/javascript" src="/resources/js/notice.js"></script>
</body>
</html>
