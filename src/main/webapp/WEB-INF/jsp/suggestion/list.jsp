<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2016/1/13
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>建议列表</title>
</head>
<body>
  <c:forEach items="${list}" var="suggestion">
    <a href="viewSuggestion"${suggestion.title}      ${suggestion.createDate}
    <p/>
  </c:forEach>
</body>
</html>
