<%--
  Created by IntelliJ IDEA.
  User: melot
  Date: 2016/4/5
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>提出建议</title>
    <link href="/resources/css/content.css" rel="stylesheet" type="text/css"/>
  <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body>
<%@include file="../header.jsp"%>
<div class="content-div">
  <form id="create-tender">
    <div class="content-title">
      <h2>建议名称</h2>
    </div>
    <input class="title" type="text" name="title"/>
    <div class="content-title">
      <h2>具体的建议</h2>
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
