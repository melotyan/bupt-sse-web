<%--
  Created by IntelliJ IDEA.
  User: melot
  Date: 2016/4/12
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>竞标列表</title>
  <link href="/resources/img/favicon.gif" rel="shortcut icon">
  <link href="/resources/css/content.css" rel="stylesheet" type="text/css"/>
</head>
<body>
  <%@include file="../header.jsp"%>
  <div class="list-div">
    <c:if test="${type == 'tid'}">
      <div class="content-title"><h2>项目《${tender.title}》竞标列表</h2></div>
      <c:forEach items="${list}" var="item">
        <div class="list-detail">竞标人: ${item.title}  <span id="file-span">竞标文件: <a href="${item.fileUrl}" download="${fileMap.get(item.fileUrl)}">${fileMap.get(item.fileUrl)}</a></span></div>
      </c:forEach>
    </c:if>
    <c:if test="${type == 'uid'}">
      <div class="content-title"><h2>您参与竞标的项目列表</h2></div>
      <c:forEach items="${list}" var="item" varStatus="status">
        <a href="/inutatccmOfTenderService/viewTenderDetail/${item.tid}"><div class="list-detail">${item.title}</div></a>
        <c:if test="${status.count % 5 == 0}">
          <p>-------------------------------------------------------------------------------</p>
        </c:if>
      </c:forEach>
    </c:if>
  </div>
  <%@include file="../footer.jsp"%>
  <script src="/resources/js/tender.js" type="text/javascript"></script>
</body>
</html>
