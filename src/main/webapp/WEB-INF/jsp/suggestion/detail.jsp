<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2016/1/13
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <c:if test="${type == 0}">
    <title>建议详情</title>
  </c:if>
  <c:if test="${type == 1}">
    <title>举报详情</title>
  </c:if>
  <c:if test="${type == 2}">
    <title>求助详情</title>
  </c:if>
    <link rel="stylesheet" href="/resources/css/content.css" type="text/css"/>
  <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body class="sticky-header-on tablet-sticky-header">
  <%@include file="../header.jsp" %>
  <div class="content-div">
    <div class="content-title"><h2>主题</h2></div>
    <p>${suggestion.title}</p>
    <div class="content-title"><h2>内容</h2></div>
    <div class="content" id="detail-text">${suggestion.content}</div>
  </div>
  <%@include file="../footer.jsp"%>
</body>
</html>
