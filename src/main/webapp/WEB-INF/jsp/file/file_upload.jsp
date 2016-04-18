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
  <title>文件上传</title>
  <link href="/resources/css/content.css" rel="stylesheet" type="text/css"/>
  <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body class="sticky-header-on tablet-sticky-header">

<%@include file="../header.jsp"%>
<div class="content-div">
  <div class="file-upload-div">
    <select id="file-type-selected">
      <option value ="5">政策文件</option>
      <option value ="4">法规文件</option>
      <option value="6">政府工作报告</option>
    </select>
    <form id="upload-form" enctype="multipart/form-data">
    </form>
  </div>
  <p></p>
  <input type="button" id="btn_add" value="添加文件"/>
  <p></p>
  <input type="button" id="btn-notice" value="上传" >
</div>
<script type="text/javascript" src="/resources/js/file.js"></script>
<%@include file="../footer.jsp"%>
</body>
</html>
