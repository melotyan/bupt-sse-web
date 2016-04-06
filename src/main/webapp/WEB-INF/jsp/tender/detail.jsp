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
</head>
<body>
<%@include file="../header.jsp"%>
<div class="content-div">
  <div class="content-title">
    ${model.title}
  </div>
  <div class="content">
    ${model.content}
  </div>
  <div class="content-file">
    ${model.fileUrl}
  </div>
</div>
<%@include file="../footer.jsp"%>
</body>
</html>
