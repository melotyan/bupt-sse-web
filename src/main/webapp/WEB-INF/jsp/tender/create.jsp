<%--
  Created by IntelliJ IDEA.
  User: melot
  Date: 2016/4/5
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发布扫项信息</title>
    <link href="/resources/css/content.css" rel="stylesheet" type="text/css"/>
  <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body>
<%@include file="../header.jsp"%>
<div class="content-div">
  <form id="create-tender">
    <div class="content-title">
      <h2>招标项目名称</h2>
    </div>
    <input class="title" type="text" name="title"/>
    <div class="content-title">
      <h2>招标项目详情</h2>
    </div>
    <div class="content">
      <textarea class="content" name="content"></textarea>
    </div>
    <input name="fileUrl" type="hidden" id="file-urls"/>
  </form>
  <div class="content-title"><h2>添加附件</h2></div>
  <div class="content-file">
    <form id="upload-form" enctype="multipart/form-data">
      <input type="hidden" name="type" value="2"/>
    </form>
    <input type="button" id="btn_add" value="添加附件"/>
  </div>
  <p/>
  <input type="button" id="btn-notice" value="发  布" >
</div>
<%@include file="../footer.jsp"%>
<script type="text/javascript" src="/resources/js/tender.js"></script>
</body>
</html>
