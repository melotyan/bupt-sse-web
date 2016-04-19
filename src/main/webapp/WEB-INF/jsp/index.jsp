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
   <ul id="sItem">
    <%--<li><img src="/resources/file/1460866787000.jpg"/></li>--%>
    <%--<li><img src="/resources/file/1460876491977.jpg"/></li>--%>
    <%--<li><img src="/resources/file/1460876492228.jpg"/></li>--%>
   </ul>
  </div>
  <div class="index-div" id="news-div">
    <ul>
    </ul>
  </div>
  <div class="index-div" id="notices-div">
    <ul>
    </ul>
  </div>
  <div class="index-div" id="tender-div">
   <ul>
   </ul>
  </div>
 </div>
 <script src="/resources/js/index.js" type="text/javascript"></script>
 <%@include file="footer.jsp"%>
</body>
</html>