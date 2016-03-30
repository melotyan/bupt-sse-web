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
</head>
<body>
<%@include file="../header.jsp" %>
<div class="main-content">
    <form action="/userService/changePassword" method="post">
        <table class="register-table">
            <tr>
                <td>旧密码:</td>
                <td><input type="password" name="oldPassword"/></td>
            </tr>
            <tr>
                <td>确认密码:</td>
                <td><input type="password" name="repassword"/></td>
            </tr>
            <tr>
                <td>新密码:</td>
                <td><input type="password" name="newPassword"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="提交"/></td>
            </tr>
        </table>
    </form>
</div>
<%--<%@include file="../footer.jsp" %>--%>
</body>
</html>
