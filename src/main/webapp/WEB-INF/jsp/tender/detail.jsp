<%--
  Created by IntelliJ IDEA.
  User: melot
  Date: 2016/4/5
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>招标项目详情</title>
    <link rel="stylesheet" href="/resources/css/content.css" type="text/css"/>
</head>
<body>
<%@include file="../header.jsp"%>
<div class="content-div">
  <div class="content-title">
    <h2>招标项目名称</h2>
    <c:if test="${sessionScope.user != null && sessionScope.user.userType != 3}">
        <span>
            <strong><a href="#" onclick="delTender('/inutatccmOfTenderService/deleteTenderInfo/${tender.id}')">删除</a></strong>
            <strong><a href="/inutatccmOfTenderService/preEditTenderInfo/${tender.id}">编辑</a></strong>
        </span>
    </c:if>
  </div>
  <p>${tender.title}</p>
  <div class="content-title"><h2>招标项目具体内容</h2></div>
  <div class="content" id="detail-text">
    ${tender.content}
  </div>
  <c:if test="${fileMap.keySet() != null}">
    <div class="content-title"><h2>附件</h2></div>
    <div class="content-file">
      <c:forEach items="${fileMap.keySet()}" var="key">
        <p><a href="${key}" download="${fileMap.get(key)}">${fileMap.get(key)}</a></p>
      </c:forEach>
    </div>
  </c:if>
</div>
<%@include file="../footer.jsp"%>
<script src="/resources/js/tender.js" type="text/javascript"></script>
</body>
</html>
