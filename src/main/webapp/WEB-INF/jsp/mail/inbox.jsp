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
  <title>收件箱</title>
  <link href="/resources/css/content.css" rel="stylesheet" type="text/css"/>
  <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body class="sticky-header-on tablet-sticky-header">

<%@include file="../header.jsp"%>

<div class="list-div">
  <div class="content-title"><h2>收到的信件<span class="mail-num"></span></h2></div>
  <c:forEach items="${list}" var="mail" varStatus="status">
    <c:if test="${mail.receiverStatus == 0}">
      <a href = "javascript:void(0)" onclick="readMail(${mail.id})"><div class="list-detail">${mail.title}<span><fmt:formatDate value="${mail.sendTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span></div></a>
    </c:if>
    <c:if test="${mail.receiverStatus == 1}">
      <a href="/mailboxService/readMail/id/${mail.id}" ><div class="list-detail" style="color:#999">${mail.title}<span><fmt:formatDate value="${mail.sendTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span></div></a>
    </c:if>
    <c:if test="${status.count % 5 == 0}">
      <p>-------------------------------------------------------------------------------</p>
    </c:if>
  </c:forEach>
</div>
<%@include file="../footer.jsp"%>
</body>
</html>
