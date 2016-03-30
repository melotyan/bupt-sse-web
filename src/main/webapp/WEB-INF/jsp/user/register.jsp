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
    <link rel="stylesheet" href="/resources/css/content.css" type="text/css"/>
</head>
<body class="sticky-header-on tablet-sticky-header">
  <%@include file="../header.jsp"%>
  <div class="login-div">
  <form action="/userService/register" method="post">
    <table class="register-table">
      <tr>
        <td>用户名：</td>
        <td>
          <input type="text" name="username"/>
          <span style="color:red">*</span><span id="userMessage"></span>&nbsp;&nbsp;&nbsp;<span class="messinfo">3~18个字符，包括字母、数字、下划线</span>
        </td>
      </tr>
      <tr>
        <td>密码：</td>
        <td>
          <input type="password" name="password"/>
          <span style="color:red">*</span><span id="passMessage"></span>&nbsp;&nbsp;&nbsp;<span class="messinfo">6～16个字符（字母、数字、特殊符号）,区分大小写</span>
        </td>
      </tr>
      <tr>
        <td>确认密码：</td>
        <td><input type="password" name="repassword"/></td>
      </tr>
      <tr>
        <td>邮箱：</td>
        <td><input type="text" name="email"/></td>
      </tr>
      <tr>
        <td>验证码: </td>
        <td><input type="text" name="captcha"/>
        <a href="#" onclick="changeImg()"><img id="imgObj" alt="验证码" src="/captcha.jpg"/> </a></td>
      </tr>
      <tr>
        <td><input type="submit" name="注册"/></td>
        <td><input type="reset" name="重置"/></td>
      </tr>
    </table>
  </form>
  </div>
  <%@include file="../footer.jsp"%>
</body>
<script type="text/javascript" src="/resources/js/user.js"></script>
</html>
