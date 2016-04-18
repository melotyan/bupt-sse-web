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
  <title>文件下载</title>
  <link href="/resources/css/content.css" rel="stylesheet" type="text/css"/>
  <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body class="sticky-header-on tablet-sticky-header">

<%@include file="../header.jsp"%>

<div class="list-div">
  <div class="content-title">
    <c:if test="${type == 5}"><h2>政策文件</h2></c:if>
    <c:if test="${type == 6}"><h2>政府工作报告</h2></c:if>
    <c:if test="${tyep == 4}"><h2>法规文件</h2></c:if>
  </div>
  <c:forEach items="${list}" var="file" varStatus="status">
    <a href="${file.url}" download="${file.title}"><div class="list-detail">${file.title}</div></a>
    <c:if test="${status.count % 5 == 0}">
      <p>-------------------------------------------------------------------------------</p>
    </c:if>
  </c:forEach>
</div>
<%@include file="../footer.jsp"%>
</body>
</html>
