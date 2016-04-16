<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2016/1/7
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>新闻详情</title>
  <link rel="stylesheet" href="/resources/css/content.css" type="text/css"/>
  <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body class="sticky-header-on tablet-sticky-header">
<%@include file="../header.jsp"%>
<div class="content-div">
  <div class="content-title">
    <h2>新闻标题</h2>
    <c:if test="${sessionScope.user != null && sessionScope.user.userType != 3}">
            <span>
                <a href="#" onclick="delNews('/newsService/deleteNews/id/${newsModel.id}')"><strong>删除</strong></a>
                <a href="/newsService/preUpdateNews/id/${newsModel.id}"><strong>编辑</strong></a>
            </span>
    </c:if>
  </div>
  <p>${newsModel.title}</p>
  <div class="content-title"><h2>新闻内容</h2></div>
  <div class="content" id="detail-text">
    ${newsModel.content}
  </div>
  <c:if test="${fileMap.keySet() != null}">
    <div class="content-file">
      <c:forEach items="${fileMap.keySet()}" var="key">
        <img class="news-img" src="${key}"/>
      </c:forEach>
    </div>
  </c:if>
</div>
<%@include file="../footer.jsp"%>
<script type="text/javascript" src="/resources/js/news.js"></script>
</body>
</html>
