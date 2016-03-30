<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2016/1/13
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>建议列表</title>
</head>
<body class="sticky-header-on tablet-sticky-header">
  <%@include file="../header.jsp"%>
  <div>
  <c:forEach items="${list}" var="suggestion">
    <a href="viewSuggestion/${suggestion.id}">${suggestion.title}</a>      ${suggestion.createDate}
    <p/>
  </c:forEach>
  </div>
  <%@include file="../footer.jsp"%>
</body>
</html>
