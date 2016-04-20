<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
 <title>主页</title>
 <link href="/resources/css/style.css" rel="stylesheet" type="text/css"/>
 <link href="/resources/css/content.css" rel="stylesheet" type="text/css"/>
 <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body class="sticky-header-on tablet-sticky-header">
 <%@include file="header.jsp"%>
 <div class="content-div">
  <div class="index-img-div">
   <div class="img-head">热点新闻</div>
   <ul id="sItem">
   </ul>
  </div>
  <p></p>
  <div class="index-div" id="news-div">
   <div class="index-head">政府新闻</div>
    <ul>
    </ul>
  </div>
  <div class="index-div" id="notices-div">
   <div class="index-head">通知公告</div>
   <ul>
    </ul>
  </div>
  <div class="index-div" id="tender-div">
   <div class="index-head">招标信息</div>
   <ul>
   </ul>
  </div>
 </div>
 <script src="/resources/js/index.js" type="text/javascript"></script>
 <%@include file="footer.jsp"%>
</body>
</html>