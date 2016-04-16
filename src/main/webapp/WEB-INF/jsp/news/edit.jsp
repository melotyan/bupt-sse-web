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
  <title>编辑新闻</title>
  <link href="/resources/css/content.css" rel="stylesheet" type="text/css"/>
  <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body class="sticky-header-on tablet-sticky-header">
<%@include file="../header.jsp"%>
<div class="content-div">
  <form id="edit-news-form">
    <input type="hidden" name="id" value="${news.id}"/>
    <div class="content-title">
      <h2>新闻标题</h2>
    </div>
    <input class="title" type="text" name="title" value="${news.title}"/>
    <div class="content-title"><h2>新闻内容</h2></div>
    <div class="content">
      <textarea class="notice_content" name="content">${news.content}</textarea>
    </div>
  </form>
  <%--<c:if test="${fileMap.keySet() != null}">--%>
    <%--<div class="content-file">--%>
      <%--<c:forEach items="${fileMap.keySet()}" var="key">--%>
        <%--<img src="${key}"/>--%>
      <%--</c:forEach>--%>
  <%--</c:if>--%>
  <input id="btn-edit-notice" type="button" value="修改">
</div>
<%@include file="../footer.jsp"%>
<script src="/resources/js/news.js" type="text/javascript"></script>
</body>
</html>
