<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2016/1/7
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>用户管理</title>
    <link href="/resources/css/content.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body class="sticky-header-on tablet-sticky-header">

<%@include file="../header.jsp"%>

<div class="list-div">
    <div class="content-title"><h2>用户列表</h2></div>
    <c:forEach items="${notices}" var="notice" varStatus="status">
        <a href="/noticeService/viewNoticeDetail/${notice.id}" ><div class="list-detail">[公告]${notice.title}<span><fmt:formatDate value="${notice.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span></div></a>
        <c:if test="${status.count % 5 == 0}">
            <p>-------------------------------------------------------------------------------</p>
        </c:if>
    </c:forEach>
</div>
<%@include file="../footer.jsp"%>
<script type="text/javascript" src="/resources/js/notice.js"></script>
</body>
</html>
