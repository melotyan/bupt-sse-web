<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2015/12/16
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
  <form action="/egovernment/userService/register" method="post">
    <table>
      <tr>
        <td>用户名：</td>
        <td><input type="text" name="username"/></td>
      </tr>
      <tr>
        <td>密码：</td>
        <td><input type="password" name="password"/></td>
      </tr>
      <tr>
        <td>确认密码：</td>
        <td><input type="password" name="repassword"/></td>
      </tr>
      <tr>
        <td>姓名：</td>
        <td><input type="text" name="nickname"/></td>
      </tr>
      <tr>
        <td>邮箱：</td>
        <td><input type="text" name="email"/></td>
      </tr>
      <tr>
        <td>电话：</td>
        <td><input type="text" name="phone"/></td>
      </tr>
      <tr>
        <td>住址：</td>
        <td><input type="textarea" name="address"/></td>
      </tr>
      <tr>
        <td><input type="submit" name="注册"/></td>
      </tr>
    </table>
  </form>
</body>
</html>