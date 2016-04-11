<%--
  Created by IntelliJ IDEA.
  User: melot
  Date: 2016/4/5
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>招标项目列表</title>
    <link href="/resources/css/content.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<%@include file="../header.jsp"%>
<div class="list-div">
    <div class="content-title"><h2>招标项目列表</h2></div>
    <c:forEach items="${list}" var="tender">
      <a href="/inutatccmOfTenderService/viewTenderDetail/${tender.id}"><div class="list-detail">${tender.title}<span><fmt:formatDate value="${tender.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></div></a>
  </c:forEach>
</div>
<%@include file="../footer.jsp"%>
<script src="/resources/js/tender.js" type="text/javascript"></script>
</body>
</html>
