<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2016/1/4
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发布公告</title>
  <link href="/resources/css/content.css" rel="stylesheet" type="text/css"/>
</head>
<body class="sticky-header-on tablet-sticky-header">

  <%@include file="../header.jsp"%>
    <div class="content-div">
        <form id="notice_publish" action="/noticeService/publishNotice" method="post" enctype="multipart/form-data">
          <div class="content-title">
            <h2>新闻标题</h2>
          </div>
          <input class="title" type="text" name="title" value="${notice.title}"/>
          <div class="content-title">
            <h2>新闻内容</h2>
          </div>
          <div class="content">
            <textarea class="notice_content" name="content" value="${notice.content}"></textarea>
          </div>
          <div id="upload">
            <%--<input type="file" name="files"/>--%>
          </div>
          <input type="button" id="btn_add" value="添加附件" >
          <input type="submit" value="发布" >
        </form>
    </div>
  <%@include file="../footer.jsp"%>
  <script type="text/javascript" src="/resources/js/notice.js"></script>
</body>
</html>
