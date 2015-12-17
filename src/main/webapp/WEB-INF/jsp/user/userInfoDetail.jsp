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
  <table>
    <tr>
      <td>用户名：</td>
      <td>${sessionScope.user.username}</td>
    </tr>
    <tr>
      <td>姓名：</td>
      <td>${sessionScope.user.nickname}</td>
    </tr>
    <tr>
      <td></td>
    </tr>
  </table>
</body>
</html>
