<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2016/1/11
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>信件内容</title>
  <link href="/resources/css/content.css" rel="stylesheet" type="text/css"/>
  <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body class="sticky-header-on tablet-sticky-header">
<%@include file="../header.jsp"%>
<div class="content-div">
  <form id="mail-edit-form">
  <div class="content-title">
    <h2>信件编辑</h2>
    <c:if test="${sessionScope.user != null && sessionScope.user.id eq mail.uid}">
            <span>
              <c:if test="${mail.senderStatus eq 1}"> <!--这已经是一个草稿了-->
                <a href="#" onclick="delMail('/mailboxService/deleteSendedMail/id/${mail.id}', 2)"><strong>删除此草稿</strong></a>
                <a href="#" onclick="editDraft(0)"><strong>存草稿</strong></a>
              </c:if>
              <c:if test="${mail.senderStatus eq 0}"><!--这是已经发送出去的,准备再次发一次-->
                <a href="#" onclick="saveDraftEdit()"><strong>存草稿</strong></a>
              </c:if>
            </span>
    </c:if>
  </div>
  <div class="m-head">
    <div class="m-title"></div>
    <div class="m-person-info">
      <ul id="m-person-ul">
        <li>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题:&nbsp;&nbsp;&nbsp;<input type="text" name="title" value="${mail.title}"/></li>
        <li id="receiver-li">
          收&nbsp;件&nbsp;人:&nbsp;&nbsp;
          <input type="text" name="receiver" value="${mail.receiverName}"/>
        </li>
      </ul>
    </div>
  </div>
  <div class="content">
    <textarea class="m_content" name="content">
      ${mail.content}
    </textarea>
  </div>
    <input type="hidden" name="id" value="${mail.id}"/>
  </form>
  <c:if test="${mail.senderStatus eq 1}"> <!--这已经是一个草稿了-->
    <input id="btn-edit-notice" type="button" onclick="sendDraft(${mail.id})" value="发送"/>
  </c:if>
  <c:if test="${mail.senderStatus eq 0}"><!--这是已经发送出去的,准备再次发一次-->
  <input id="btn-edit-notice" type="button" onclick="sendMailEdit(${mail.id})" value="发送"/>
</c:if>
</div>
<script src="/resources/js/mail.js" type="text/javascript"></script>
<%@include file="../footer.jsp"%>
</body>
</html>
