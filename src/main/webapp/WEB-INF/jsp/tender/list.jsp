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
    <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body>
<%@include file="../header.jsp"%>
<div class="list-div">
    <div class="content-title"><h2>招标项目列表</h2></div>
    <c:forEach items="${list}" var="tender" varStatus="status">

  <div class="list-detail"><a id="tender-detail-a" href="/inutatccmOfTenderService/viewTenderDetail/${tender.id}">[招标]${tender.title}</a>
    <span><fmt:formatDate value="${tender.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
        &nbsp;&nbsp;
        <c:choose>
        <c:when test="${sessionScope.user != null && sessionScope.user.userType != 3}">
            <a href="/tenderService/listCompetitive/tid/${tender.id}">竞标情况</a>
        </c:when>
        <c:otherwise>
            <a id="tender-compe-a" href="/tenderService/preCompetitiveTender/tid/${tender.id}">竞标</a>
        </c:otherwise>
        </c:choose>
    </span>
  </div>
        <c:if test="${status.count % 5 == 0}">
            <p>-------------------------------------------------------------------------------</p>
        </c:if>
  </c:forEach>
</div>
<%@include file="../footer.jsp"%>
<script src="/resources/js/tender.js" type="text/javascript"></script>
</body>
</html>
