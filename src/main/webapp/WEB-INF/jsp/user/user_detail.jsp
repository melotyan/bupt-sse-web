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
</head>
<body>
<form action="/egovernment/userService/editPersonal" method="post">
    <table>
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
            <td><input type="text" name="email" value="${sessionScope.user.email}"/></td>
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
            <td><input type="submit" value="修改"/></td>
        </tr>
        <input type="hidden" name="uid" value="${sessionScope.user.id}"/>
    </table>
</form>
</body>
</html>
