<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2015/12/16
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息</title>
    <link rel="stylesheet" href="/resources/css/content.css" type="text/css"/>
    <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body class="sticky-header-on tablet-sticky-header">
<%@include file="../header.jsp" %>
<div class="login-div">
    <p></p>
    <div class="img-head">个人信息</div>
    <form id="person-info-form">
        <table class="register-table">
            <tr>
                <td>用户名：</td>
                <td>${sessionScope.user.username}</td>
            </tr>
            <tr>
                <td>姓名：</td>
                <td><input type="text" name="nickname" value="${sessionScope.user.nickname}"/></td>
            </tr>
            <tr>
                <td>邮箱：</td>
                <td>${sessionScope.user.email}</td>
            </tr>
            <tr>
                <td>手机：</td>
                <td><input type="text" name="phone" value="${sessionScope.user.phone}"/></td>
            </tr>
            <tr>
                <td>住址：</td>
                <td><input type="text" name="address" value="${sessionScope.user.address}"/></td>
            </tr>
            <tr>
                <td colspan="2"><input id="change-info" type="button" onclick="changeInfo()" value="修改"/></td>
            </tr>
        </table>
    </form>
</div>
<%@include file="../footer.jsp" %>
<script src="/resources/js/user.js" type="text/javascript"></script>
</body>
</html>
