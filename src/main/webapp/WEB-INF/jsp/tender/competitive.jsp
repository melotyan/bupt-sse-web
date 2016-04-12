<%--
  Created by IntelliJ IDEA.
  User: melot
  Date: 2016/4/12
  Time: 8:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>竞标</title>
    <link href="/resources/img/favicon.gif" rel="shortcut icon">
    <link href="/resources/css/content.css" rel="stylesheet" type="text/css"/>
</head>
<body>
  <%@include file="../header.jsp" %>
    <div class="content-div">
      <div class="content-title"><h2>竞标项目名称</h2></div>
      <p/>
      <h2>${title}</h2>
      <p/>
      <div class="content-title"><h2>请上传您的竞标文件</h2></div>
      <%--<div class="content">--%>
      <form id="upload-form" enctype="multipart/form-data">
        <input type="hidden" name="type" value="1"/>
        <input type="file" name="files"/>
      </form>
      <input type="button" id="btn-competitive" value="竞标"/>
      <form id="competitive-form">
        <input type="hidden" name="tid" value="${tid}"/>
        <input type="hidden" name="title" value="${title}"/>
        <input type="hidden" name="fileUrl" id="fileUrl"/>
      </form>
    </div>
  <%@include file="../footer.jsp" %>
  <script src="/resources/js/tender.js" type="text/javascript"></script>
</body>
</html>
