<%--
  Created by IntelliJ IDEA.
  User: melot
  Date: 2016/4/5
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <c:if test="${type == 0}">
      <title>提出建议</title>
    </c:if>
    <c:if test="${type == 1}">
      <title>投诉举报</title>
    </c:if>
    <c:if test="${type == 2}">
      <title>寻求帮助</title>
    </c:if>
    <link href="/resources/css/content.css" rel="stylesheet" type="text/css"/>
  <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body>
<%@include file="../header.jsp"%>
<div class="content-div">
  <form id="create-tender">
    <input type="hidden" value="${type}" name="type"/>
    <div class="content-title">
        <c:if test="${type == 0}">
            <h2>建议标题</h2>
        </c:if>
        <c:if test="${type == 1}">
            <h2>投诉举报标题</h2>
        </c:if>
        <c:if test="${type == 2}">
            <h2>求助标题</h2>
        </c:if>
    </div>
    <input class="title" type="text" name="title"/>
    <div class="content-title">
        <c:if test="${type == 0}">
            <h2>建议内容</h2>
        </c:if>
        <c:if test="${type == 1}">
            <h2>投诉举报内容</h2>
        </c:if>
        <c:if test="${type == 2}">
            <h2>求助内容</h2>
        </c:if>
    </div>
    <div class="content">
      <textarea class="content" name="content"></textarea>
    </div>
  </form>
  <input type="button" id="btn-notice" value="提  交" >
</div>
<%@include file="../footer.jsp"%>
<script type="text/javascript" src="/resources/js/suggestion.js"></script>
</body>
</html>
