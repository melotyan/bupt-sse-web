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
</head>
<body>
  <c:forEach items="${notices}" var="notice">
    <p/>
    <div><a href="/noticeService/viewNoticeDetail/${notice.id}" >${notice.title}</a></div>
  </c:forEach>
</body>
</html>
