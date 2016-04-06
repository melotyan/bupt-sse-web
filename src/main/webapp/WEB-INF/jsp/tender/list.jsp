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
</head>
<body>
<%@include file="../header.jsp"%>
<div class="list-div">
  <c:forEach items="${list}" var="tender">
    <span><a href="/inutatccmOfTenderService/viewTenderDetail/${tender.id}">${tender.title}</a></span>
  </c:forEach>
</div>
<%@include file="../footer.jsp"%>
</body>
</html>
