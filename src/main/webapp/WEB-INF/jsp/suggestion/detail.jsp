<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2016/1/13
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>意见详情</title>
</head>
<body class="sticky-header-on tablet-sticky-header">
  <%@include file="../header.jsp" %>
  <div>
  标题：${suggestion.title}
  <p/>
  内容: ${suggestion.content}
  </div>
  <%@include file="../footer.jsp"%>
</body>
</html>
