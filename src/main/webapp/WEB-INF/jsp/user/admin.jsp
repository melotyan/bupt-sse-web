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
    <div class="userinfo-div-head"><span>用户名</span><span>用户类型</span><span>账户状态</span><span>操作</span></div>
    <c:forEach items="${list}" var="user" varStatus="status">
        <div class="userinfo-div">
            <span>${user.username}</span>
            <span>
            <select class="user-selected" id="userType${status.count}">
                <option value="3">普通用户</option>
                <option value="2" <c:if test="${user.userType == 2}">selected</c:if>>政府职员</option>
                <option value="1" <c:if test="${user.userType == 1}">selected</c:if>>政府领导</option>
                <option value="0" <c:if test="${user.userType == 0}">selected</c:if>>管理员</option>
            </select>
            </span>
            <span>
            <select class="user-selected" id="accountStatus${status.count}">
                <option value="2" <c:if test="${user.accountStatus == 2}">selected</c:if>>被封号</option>
                <option value="1" <c:if test="${user.accountStatus == 1}">selected</c:if>>已激活</option>
                <option value="0" <c:if test="${user.accountStatus == 0}">selected</c:if>>未激活</option>
            </select>
            </span>
            <span><button id="manage-user-button" onclick="managerUser(${user.id}, ${status.count})">修 改</button></span>
        </div>
        <c:if test="${status.count % 5 == 0}">
            <p>-------------------------------------------------------------------------------</p>
        </c:if>
    </c:forEach>
</div>
<script type="text/javascript" src="/resources/js/user.js"></script>
<%@include file="../footer.jsp"%>
</body>
</html>
