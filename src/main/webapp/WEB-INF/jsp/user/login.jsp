<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2015/12/15
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
    <script type="text/javascript" src="/resources/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="/resources/js/user.js"></script>
</head>

<body>
  <form action="/userService/login" method="post">
  <table class="login-table">
      <tr>
        <td>用户名：</td>
        <td><input type="text" name="username"/></td>
      </tr>
      <tr>
        <td>密码: </td>
        <td><input type="password" name="password"/></td>
      </tr>
      <tr>
          <td>验证码: </td>
          <td><input type="text" name="captcha"/></td>
          <td><a href="#" onclick="changeImg()"><img id="imgObj" alt="验证码" src="captcha.jsp" /></a></td>
      </tr>
      <tr>
        <td><input type="submit" value="登陆"/></td>
      </tr>
  </table>
  </form>

</body>
</html>
