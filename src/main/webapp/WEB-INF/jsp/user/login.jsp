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
    <link rel="stylesheet" href="/resources/css/content.css" type="text/css"/>
</head>

<body class="sticky-header-on tablet-sticky-header">
  <%@include file="../header.jsp"%>
  <div class="login-div">
      <form action="/userService/login" method="post">
      <table class="login-table">
          <tr>
            <td>用户名:</td>
            <td><input type="text" name="username"/></td>
          </tr>
          <tr>
            <td>密码:</td>
            <td><input type="password" name="password"/></td>
          </tr>
          <tr>
              <td>验证码:</td>
              <td><input type="text" name="captcha"/>
              <a class="captcha" href="#" onclick="changeImg()"><img id="imgObj" alt="验证码" src="/captcha.jpg" /></a></td>
          </tr>
          <tr class="login-register">
            <td><input type="submit" value="登陆"/></td>
              <td><a href="/userService/preRegister">注册</a></td>
          </tr>
      </table>
      </form>
  </div>
  <%@include file="../footer.jsp"%>
</body>
<script type="text/javascript" src="/resources/js/user.js"></script>
</html>
