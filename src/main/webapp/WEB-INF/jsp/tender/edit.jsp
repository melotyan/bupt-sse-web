<%--
  Created by IntelliJ IDEA.
  User: melot
  Date: 2016/4/6
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑招标信息</title>
    <link rel="stylesheet" href="/resources/css/content.css" type="text/css"/>
</head>
<body>
<%@include file="../header.jsp"%>
<div class="content-div">
  <form id="eidt-notice-form">
    <input type="hidden" name="id" value="${tender.id}"/>
    <div class="content-title">
      <h2>公告标题</h2>
    </div>
    <input class="title" type="text" name="title" value="${tender.title}"/>
    <div class="content-title"><h2>公告内容</h2></div>
    <div class="content">
      <textarea class="notice_content" name="content">${tender.content}</textarea>
    </div>
  </form>
  <c:if test="${fileMap.keySet() != null}">
    <div class="content-title"><h2>附件</h2></div>
  <div class="content-file">
    <c:forEach items="${fileMap.keySet()}" var="key">
      <p><a href="${key}" download="${fileMap.get(key)}">${fileMap.get(key)}</a></p>
    </c:forEach>
  </c:if>
  <input id="btn-edit-notice" type="button" value="修改">
</div>
<%@include file="../footer.jsp"%>
<script src="/resources/js/tender.js"></script>
</body>
</html>
