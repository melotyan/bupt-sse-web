<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2015/12/30
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta content="" name="description">
  <meta content="" name="keywords">
  <title>404</title>
  <link href="/resources/img/favicon.gif" rel="shortcut icon">
  <style>
    body, div, p, a, span {
      margin: 0;
      padding: 0;
    }
    body{
      background:url("/resources/img/background.png") repeat-x scroll 0 0 ;
    }
    .wrapper {
      width:960px;
      margin:0 auto;
      border-left:none;
      min-height: 400px;
      position:relative;
    }
    .logo {
      width:123px;
      height:37px;
      margin-top:20px;
      /*background:url("http://assets.dajieimg.com/up/404/img/logo.png") no-repeat scroll 0 bottom transparent;*/
    }
    .cloud{
      width:440px;
      height:235px;
      background:url("/resources/img/cloud.png") no-repeat scroll 0 bottom transparent;
      margin:15px auto 0px;
    }
    .error-404 {
      width:818px;
      height:300px;
      background:url("/resources/img/404error.png") no-repeat scroll 0 bottom transparent;
      margin-left:70px;
    }
    .content{
      padding:15px 0 0 15px;
    }
    .text{
      font-family:"微软雅黑";
      padding-top:40px;
      margin-left:57px;
      font-size:22px;
    }
    .button {
      font-size: 14px;
      color:#333333;
      height: 30px;
      vertical-align: middle;
      display:inline-block;
      text-decoration:none;
      text-align:center;
    }
    .guest{
      background:url("/resources/img/button1.png") no-repeat scroll 0 bottom transparent;
      width:95px;
    }
    .report{
      background:url("/resources/img/button2.png") no-repeat scroll 0 bottom transparent;
      width:115px;
      margin-left:10px;
    }
    .button b {
      display:inline-block;
      font-weight: 400;
      line-height:30px;
    }
    .buttonwrap {
      font-size:0;
      margin-top:19px;
      margin-left:72px;
    }
  </style>
</head>
<body>
<div class="wrapper">
  <div class="logo"></div>
  <div class="cloud">
    <div class="content">
      <div class="text">喵~，这个页面跟程序猿私奔了~</div>
      <div class="buttonwrap">
        <a class="button guest" id="guest" title="" href="http://egovernment.melotyan.com/"><b>去首页看看</b></a>
        <a class="button report" id="report" title="" href="egovernment_sse@126.com"><b>报告管理员</b></a>
      </div>
    </div>
  </div>
  <div class="error-404"></div>
</div>
</body></html>
