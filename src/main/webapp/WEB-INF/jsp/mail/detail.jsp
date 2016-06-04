<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2016/1/7
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
  <title>信件内容</title>
  <link rel="stylesheet" href="/resources/css/content.css" type="text/css"/>
  <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body class="sticky-header-on tablet-sticky-header">
<%@include file="../header.jsp"%>
<div class="content-div">
  <div class="content-title">
    <h2>信件详情</h2>
    <c:if test="${sessionScope.user != null}">
            <span>
              <c:if test="${sessionScope.user.username eq mail.receiverName}">
                <a href="#" onclick="delMail('/mailboxService/deleteReceivedMail/id/${mail.id}', 0)"><strong>删除</strong></a>
                <a href="/mailboxService/preResponseMail/id/${mail.id}"><strong>回复</strong></a>
              </c:if>
              <c:if test="${sessionScope.user.username eq mail.senderName}">
                <a href="#" onclick="delMail('/mailboxService/deleteSendedMail/id/${mail.id}', 1)"><strong>删除</strong></a>
                <a href="/mailboxService/preEditDraft/id/${mail.id}"><strong>编辑再次发送</strong></a>
              </c:if>
            </span>
    </c:if>
  </div>
  <div class="m-head">
    <div class="m-title">${mail.title}</div>
    <div class="m-person-info">
      <ul id="m-person-ul">
        <li id="sender-li">发&nbsp;件&nbsp;人:&nbsp;&nbsp;${mail.senderName}</li>
        <li id="receiver-li">收&nbsp;件&nbsp;人:&nbsp;&nbsp;${mail.receiverName}</li>
        <li id="send-time">时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间:&nbsp;&nbsp;<fmt:formatDate value="${mail.sendTime}" pattern="yyyy-MM-dd HH:mm:ss" /></li>
      </ul>
    </div>
  </div>
  <div class="content" id="m-content">${mail.content}</div>
</div>
<%@include file="../footer.jsp"%>
</body>
</html>
