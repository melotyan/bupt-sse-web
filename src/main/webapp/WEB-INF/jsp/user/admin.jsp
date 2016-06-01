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
    <c:forEach items="${list}" var="user" varStatus="status">
        <div class="list-detail">
            <span>${user.username}</span>
            <span>
            <select class="user-selected">
                <option value="3">普通用户</option>
                <option value="2" <c:if test="${user.userType == 2}">selected</c:if>>政府职员</option>
                <option value="1" <c:if test="${user.userType == 1}">selected</c:if>>政府领导</option>
                <option value="0" <c:if test="${user.userType == 0}">selected</c:if>>系统管理员</option>
            </select>
            </span>
            <span>
            <select class="user-selected">
                <option value="3">普通用户</option>
                <option value="2" <c:if test="${user.userType == 2}">selected</c:if>>政府职员</option>
                <option value="1" <c:if test="${user.userType == 1}">selected</c:if>>政府领导</option>
                <option value="0" <c:if test="${user.accountStatus == 0}">selected</c:if>>系统管理员</option>
            </select>
            </span>
        </div>
        <c:if test="${status.count % 5 == 0}">
            <p>-------------------------------------------------------------------------------</p>
        </c:if>
    </c:forEach>
</div>
<%@include file="../footer.jsp"%>
<script type="text/javascript" src="/resources/js/notice.js"></script>
</body>
</html>
