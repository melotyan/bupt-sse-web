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
    <title>公告详情</title>
</head>
<body>

  <div>
    标题: ${noticeModel.title}
  </div>
  <div>
    内容：${noticeModel.content}
  </div>
  <div>
    附件：
    <c:forEach items="${fileMap.keys()}" var="file">
      <p/><a href="">${file}</a>
    </c:forEach>
  </div>
</body>
</html>
