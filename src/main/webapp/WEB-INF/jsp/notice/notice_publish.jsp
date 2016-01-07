<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2016/1/4
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发布公告</title>
  <script type="text/javascript" src="/resources/js/jquery/jquery-1.7.2.min.js"></script>
  <script type="text/javascript" src="/resources/js/notice.js"></script>
</head>
<body>
    <form id="notice_publish" action="/noticeService/publishNotice" method="post" enctype="multipart/form-data">
      <div>
        <div>标题</div>
        <input class="title" type="text" name="title"/>
      </div>
      <div>
        <div>内容</div>
        <textarea class="notice_content" name="content"></textarea>
      </div>
      <div id="upload">
        <%--<input type="file" name="files"/>--%>
      </div>
      <input type="button" id="btn_add" value="添加附件" >
      <input type="submit" value="上传" >
    </form>
</body>
</html>
