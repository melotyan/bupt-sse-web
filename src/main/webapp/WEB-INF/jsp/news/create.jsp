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
  <title>发布新闻</title>
  <link href="/resources/css/content.css" rel="stylesheet" type="text/css"/>
  <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body class="sticky-header-on tablet-sticky-header">

<%@include file="../header.jsp"%>
<div class="content-div">
  <form id="news-publish-form">
    <div class="content-title">
      <h2>新闻标题</h2>
    </div>
    <input type="text" name="title"/>
    <div class="content-title">
      <h2>新闻内容</h2>
    </div>
    <div class="content">
      <textarea name="content"></textarea>
    </div>
    <input type="hidden" name="files" id="file-urls"/>
    <div class="content-title">
      <h2>添加图片</h2>
    </div>
  </form>
  <div class="content-file">
    <form id="upload-form" enctype="multipart/form-data">
      <input type="hidden" name="type" value="0"/>
    </form>
  </div>
  <p></p>
  <input type="button" id="btn_add" value="添加图片"/>
  <p/>
  <input type="button" id="btn-notice" value="发  布" >
</div>
<%@include file="../footer.jsp"%>
<script type="text/javascript" src="/resources/js/news.js"></script>
</body>
</html>
