<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2016/1/13
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <c:if test="${suggestion.type == 0}">
    <title>建议详情</title>
  </c:if>
  <c:if test="${suggestion.type == 1}">
    <title>举报详情</title>
  </c:if>
  <c:if test="${suggestion.type == 2}">
    <title>求助详情</title>
  </c:if>
    <link rel="stylesheet" href="/resources/css/content.css" type="text/css"/>
  <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body class="sticky-header-on tablet-sticky-header">
  <%@include file="../header.jsp" %>
  <div class="content-div">
    <div class="content-title">
      <c:if test="${suggestion.type == 0}">
        <h2>建议标题</h2>
      </c:if>
      <c:if test="${suggestion.type == 1}">
        <h2>投诉举报标题</h2>
      </c:if>
      <c:if test="${suggestion.type == 2}">
        <h2>求助标题</h2>
      </c:if>
      <c:if test="${sessionScope.user != null && sessionScope.user.id == suggestion.uid}">
            <span>
                <a href="#" onclick="delNotice('/suggestionService/deleteSuggestion/${suggestion.id}')"><strong>删除</strong></a>
                <a href="/suggestionService/preEditSuggestion/id/${suggestion.id}"><strong>编辑</strong></a>
            </span>
      </c:if>
    </div>
    <p>${suggestion.title}</p>
    <div class="content-title">
      <c:if test="${suggestion.type == 0}">
        <h2>建议内容</h2>
      </c:if>
      <c:if test="${suggestion.type == 1}">
        <h2>投诉举报内容</h2>
      </c:if>
      <c:if test="${suggestion.type == 2}">
        <h2>求助内容</h2>
      </c:if>
    </div>
    <div class="content" id="detail-text">${suggestion.content}</div>
  </div>
  <%@include file="../footer.jsp"%>
</body>
</html>
