<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2016/1/6
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更改密码</title>
    <link rel="stylesheet" href="/resources/css/content.css" type="text/css"/>
    <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body class="sticky-header-on tablet-sticky-header">

<%@include file="../header.jsp" %>
<div class="login-div">
    <form id="password-form">
        <table class="register-table">
            <tr>
                <td>旧密码:</td>
                <td><input type="password" name="oldPassword"/></td>
            </tr>
            <tr>
                <td>新密码:</td>
                <td><input type="password" name="newPassword"/></td>
            </tr>
            <tr>
                <td>确认新密码:</td>
                <td><input type="password" name="repassword"/></td>
            </tr>
            <tr>
                <td colspan="2"><input id="change-pw" type="button" onclick="changePW()" value="提交"/></td>
            </tr>
        </table>
    </form>
</div>
<%@include file="../footer.jsp" %>
<script src="/resources/js/user.js" type="text/javascript"></script>
</body>
</html>
