<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2016/1/11
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <c:if test="${type == 0}">
    <title>编辑建议内容</title>
  </c:if>
  <c:if test="${type == 1}">
    <title>编辑举报内容</title>
  </c:if>
  <c:if test="${type == 2}">
    <title>编辑求助内容</title>
  </c:if>
  <link href="/resources/css/content.css" rel="stylesheet" type="text/css"/>
  <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body class="sticky-header-on tablet-sticky-header">
<%@include file="../header.jsp"%>
<div class="content-div">
  <form id="edit-suggestion-form">
    <input type="hidden" name="id" value="${suggestion.id}"/>
    <div class="content-title">
      <h2>主题</h2>
    </div>
    <input class="title" type="text" name="title" value="${suggestion.title}"/>
    <div class="content-title"><h2>详情</h2></div>
    <div class="content">
      <textarea class="notice_content" name="content">${suggestion.content}</textarea>
    </div>
  </form>
  <input id="btn-edit-notice" type="button" value="修改">
</div>
<%@include file="../footer.jsp"%>
<script src="/resources/js/suggestion.js" type="text/javascript"></script>
</body>
</html>
