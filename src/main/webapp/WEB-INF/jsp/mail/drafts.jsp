<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2016/1/7
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <title>草稿箱</title>
  <link href="/resources/css/content.css" rel="stylesheet" type="text/css"/>
  <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body class="sticky-header-on tablet-sticky-header">

<%@include file="../header.jsp"%>

<div class="list-div">
  <div class="content-title"><h2>草稿列表</h2></div>
  <c:forEach items="${list}" var="mail" varStatus="status">
    <a href="/mailboxService/preEditDraft/id/${mail.id}" ><div class="list-detail">${mail.title}<span><fmt:formatDate value="${mail.sendTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span></div></a>
    <c:if test="${status.count % 5 == 0}">
      <p>-------------------------------------------------------------------------------</p>
    </c:if>
  </c:forEach>
</div>
<%@include file="../footer.jsp"%>
<script type="text/javascript" src="/resources/js/notice.js"></script>
</body>
</html>
