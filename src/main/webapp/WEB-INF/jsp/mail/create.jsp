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
  <title>发送信件</title>
  <link href="/resources/css/content.css" rel="stylesheet" type="text/css"/>
  <link href="/resources/img/favicon.gif" rel="shortcut icon">
</head>
<body class="sticky-header-on tablet-sticky-header">

<%@include file="../header.jsp"%>
<div class="content-div">
  <form id="mail-create-form">
  <div class="content-title">
    <c:if test="${mail != null}">
      <h2>信件回复</h2>
    </c:if>
    <c:if test="${mail == null}">
      <h2>写信</h2>
    </c:if>

    <c:if test="${sessionScope.user != null}">
            <span>
                <a href="#" onclick="saveDraft()"><strong>存草稿</strong></a>
            </span>
    </c:if>
  </div>
  <div class="m-head">
    <div class="m-title"></div>
    <div class="m-person-info">
      <ul id="m-person-ul">
        <li>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题:&nbsp;&nbsp;&nbsp;
          <c:if test="${mail != null}">
            <input type="text" name="title" value="回复：${mail.title}"/>
          </c:if>
          <c:if test="${mail == null}">
            <input type="text" name="title"/>
          </c:if>
        </li>
        <li id="receiver-li">
          收&nbsp;件&nbsp;人:&nbsp;&nbsp;
          <c:if test="${mail != null}">
            <input type="text" name="receiver" value="${mail.senderName}"/>
          </c:if>
          <c:if test="${mail == null}">
            <input type="text" name="receiver" value="${receiver}"/>
          </c:if>
        </li>
      </ul>
    </div>
  </div>
  <div class="content">
    <c:if test="${mail != null}">
      <textarea class="m_content" name="content">


      --------------------------------------- 原 信 件 --------------------------------------------
      ${mail.content}
      </textarea>
    </c:if>
    <c:if test="${mail == null}">
      <textarea class="m_content" name="content"></textarea>
    </c:if>
  </div>
  </form>
  <p/>
  <input type="button" id="btn-notice" onclick="sendMail()" value="发  送" >
</div>
<%@include file="../footer.jsp"%>
</body>
</html>
