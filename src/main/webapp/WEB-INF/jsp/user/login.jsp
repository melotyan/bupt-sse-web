<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2015/12/15
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
    <link rel="stylesheet" href="/resources/css/content.css" type="text/css"/>
    <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>

<body class="sticky-header-on tablet-sticky-header">
  <%@include file="../header.jsp"%>
  <div class="login-div">
      <p></p>
      <div class="img-head">登&nbsp;&nbsp;陆</div>
      <form id="login-form">
      <table class="login-table">
          <tr>
            <td>用户名:</td>
            <td><input type="text" name="username"/></td>
          </tr>
          <tr>
            <td>密码:</td>
            <td><input type="password" name="password"/></td>
          </tr>
          <tr class="captcha-tr">
              <td>验证码:</td>
              <td><input class="captcha-text" type="text" name="captcha"/>
              <img class="imgObj" alt="验证码" src="/captcha.jpg" /></td>
          </tr>
          <tr>
            <td colspan="2"><input type="button" onclick="login()" value="登陆"/>
                <a href="/userService/preRegister"><input type="button" value="注册" id="register-button"/></a>
            </td>
          </tr>
      </table>
      </form>
  </div>
  <%@include file="../footer.jsp"%>
</body>
<script type="text/javascript" src="/resources/js/user.js"></script>
</html>
