<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
 <title>主页</title>
 <link href="/resources/css/style.css" rel="stylesheet" type="text/css"/>
 <link href="/resources/css/content.css" rel="stylesheet" type="text/css"/>
</head>
<body class="sticky-header-on tablet-sticky-header">
 <%@include file="header.jsp"%>
 <div class="content-div">
  <p/>
  <a href="/userService/preLogin">登陆页面</a>
  <p/>
  <a href="/userService/preRegister">注册页面</a>
  <p/>
  <a href="/userService/preChangePassword">改密码</a>
  <p/>
  <a href="/userService/getPersonalInfo">查看个人信息</a>
  <p/>
  <a href="/userService/logout">登出</a>
  <p/>
  <a href="/noticeService/prePublishNotice">发布公告</a>
 </div>
 <%@include file="footer.jsp"%>
</body>
</html>