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
  <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body class="sticky-header-on tablet-sticky-header">
  <%@include file="../header.jsp"%>
  <div class="login-div">
    <p/>
    <div class="img-head">注&nbsp;&nbsp;册</div>
  <form id="register-form">
    <table class="register-table">
      <tr>
        <td>用户名：</td>
        <td>
          <input type="text" name="username"/>
          <%--<span style="color:red">*</span><span id="userMessage"></span>&nbsp;&nbsp;&nbsp;<span class="messinfo">3~18个字符，包括字母、数字、下划线</span>--%>
        </td>
      </tr>
      <tr>
        <td>密码：</td>
        <td>
          <input type="password" name="password"/>
          <%--<span style="color:red">*</span><span id="passMessage"></span>&nbsp;&nbsp;&nbsp;<span class="messinfo">3～16个字符（字母、数字、特殊符号）,区分大小写</span>--%>
        </td>
      </tr>
      <tr>
        <td>确认密码：</td>
        <td><input type="password" name="repassword"/></td>
      </tr>
      <tr>
        <td>邮箱：</td>
        <td><input type="text" name="email"/>
        <%--<span style="color:red">*</span><span id="emailMessage"></span>&nbsp;&nbsp;&nbsp;<span class="messinfo">输入有效邮箱</span>--%>
        </td>
      </tr>
      <tr class="captcha-tr">
        <td>验证码: </td>
        <td><input type="text" name="captcha"/>
        <img class="imgObj" alt="验证码" src="/captcha.jpg"/></td>
      </tr>
      <tr>
        <td colspan="2"><input id="btn-register" type="button" value="注册" onclick="register()"/>
        <input type="reset" name="重置"/></td>
      </tr>
    </table>
  </form>
  </div>
  <%@include file="../footer.jsp"%>
</body>
<script type="text/javascript" src="/resources/js/user.js"></script>
</html>
