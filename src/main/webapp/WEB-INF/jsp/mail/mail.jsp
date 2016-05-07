<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2016/1/7
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>个人信箱</title>
  <link rel="stylesheet" href="/resources/css/content.css" type="text/css"/>
  <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body class="sticky-header-on tablet-sticky-header">
<%@include file="../header.jsp"%>
<div class="mail-div">
  <div class="mail-nav">
    <div id="new-mail" class="mail-box" onclick="newMail()">新建邮件</div>
    <div id="inbox" class="mail-box" onclick="listInbox()">收件箱</div>
    <div id="outbox" class="mail-box">已发送</div>
    <div id="drafts" class="mail-box">草稿箱</div>
  </div>
  <div class="mail-list"></div>
  <div class="mail-content">
  </div>
  <div class="new-mail">
  </div>
</div>
<script type="text/javascript" src="/resources/js/mail.js"></script>
<%@include file="../footer.jsp"%>
</body>
</html>
