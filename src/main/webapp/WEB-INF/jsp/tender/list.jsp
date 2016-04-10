<%--
  Created by IntelliJ IDEA.
  User: melot
  Date: 2016/4/5
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>招标项目列表</title>
    <link href="/resources/css/content.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<%@include file="../header.jsp"%>
<div class="list-div">
  <c:forEach items="${list}" var="tender">
    <div class="list-detail"><a href="/inutatccmOfTenderService/viewTenderDetail/${tender.id}">${tender.title}</a></div>
  </c:forEach>
</div>
<%@include file="../footer.jsp"%>
<script src="/resources/js/tender.js" type="text/javascript"></script>
</body>
</html>
