<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2016/1/11
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑</title>
</head>
<body class="sticky-header-on tablet-sticky-header">

  <%@include file=""%>
  <form action="/noticeService/updateNotice" method="post">
    <input type="hidden" name="id" value="${notice.id}"/>
    <div>
      <div>标题</div>
      <input class="title" type="text" name="title" value="${notice.title}"/>
    </div>
    <div>
      <div>内容</div>
      <textarea class="notice_content" name="content">${notice.content}</textarea>
    </div>
    <div id="upload">
      <%--<input type="file" name="files"/>--%>
    </div>
    <input type="submit" value="修改" >
  </form>
</body>
</html>
