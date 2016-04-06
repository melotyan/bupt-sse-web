<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2016/1/7
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>公告列表</title>
    <link href="/resources/css/content.css" rel="stylesheet" type="text/css"/>
</head>
<body class="sticky-header-on tablet-sticky-header">

  <%@include file="../header.jsp"%>
  <div class="list-div">
    <c:forEach items="${notices}" var="notice">
      <div class="list-detail"><a href="/noticeService/viewNoticeDetail/${notice.id}" >${notice.title}</a></div>
    </c:forEach>
  </div>
  <%@include file="../footer.jsp"%>
  <script type="text/javascript" src="/resources/js/notice.js"></script>
</body>
</html>
