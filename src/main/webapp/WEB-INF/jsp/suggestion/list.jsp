<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2016/1/13
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <c:if test="${type == 0}">
     <title>建议列表</title>
  </c:if>
  <c:if test="${type == 1}">
    <title>举报列表</title>
  </c:if>
  <c:if test="${type == 2}">
    <title>求助列表</title>
  </c:if>
    <link href="/resources/css/content.css" type="text/css" rel="stylesheet"/>
  <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body class="sticky-header-on tablet-sticky-header">
  <%@include file="../header.jsp"%>
  <div class="list-div">
    <div class="content-title">
      <c:if test="${type == 0}">
        <h2>建议列表</h2>
      </c:if>
      <c:if test="${type == 1}">
        <h2>举报列表</h2>
      </c:if>
      <c:if test="${type == 2}">
        <h2>寻求帮助列表</h2>
      </c:if>
    </div>
    <c:forEach items="${list}" var="suggestion" varStatus="status">
      <a href="/suggestionService/viewSuggestion/${suggestion.id}"><div class="list-detail">${suggestion.title}<span><fmt:formatDate value="${suggestion.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></div></a>
      <c:if test="${status.count % 5 == 0}">
        <p>-------------------------------------------------------------------------------</p>
      </c:if>
    </c:forEach>
  </div>
  <%@include file="../footer.jsp"%>
</body>
</html>
