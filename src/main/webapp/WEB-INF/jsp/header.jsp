<%--
  Created by IntelliJ IDEA.
  User: melot
  Date: 2016/3/24
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="/resources/css/style.css" rel="stylesheet" type="text/css"/>
<link href="/resources/css/fontello.css" rel="stylesheet" type="text/css"/>
<link href="/resources/css/header.css" rel="stylesheet" type="text/css"/>


<!-- Container -->
<div class="container">
    <header id="header" class="animate-onscroll">
    <div id="main-header">
      <div class="container">
        <div class="row">
          <div id="logo" class="col-lg-3 col-md-3 col-sm-3">
            <a href="/"><img src="/resources/img/logo.png" alt="Logo"></a>
          </div>
          <div class="head-title">
            海淀区政府电子政务网
            <%--</blockquote>--%>
          </div>
        </div>
        <div class="login-header">
          <c:if test="${sessionScope.user == null}">
            <a class="link" href="/userService/preLogin">登陆</a>
            &nbsp&nbsp&nbsp
            <a class="link" href="/userService/preRegister">注册</a>
          </c:if>
          <c:if test="${sessionScope.user != null}">
            <a class="link" href="#">用户名: ${sessionScope.user.username}</a>
            &nbsp&nbsp&nbsp
            <a class="link" href="/userService/logout">退出登陆</a>
          </c:if>

        </div>
      </div>
    </div>
    <!-- Lower Header -->
    <div id="lower-header">
      <div class="container">
        <div id="menu-button">
          <div>
            <span></span>
            <span></span>
            <span></span>
          </div>
          <span>Menu</span>
        </div>
        <ul id="navigation">
          <!-- Home -->
          <li class="home-button  current-menu-item">
            <a href="/"><i class="icons icon-home"></i></a>
          <!-- Pages -->
          <li>
            <span>政府新闻动态</span>
            <ul>
              <li><a href="/newsService/listNews/page/1">新闻动态</a></li>
              <c:if test="${not empty sessionScope.user && sessionScope.user.userType != 3}">
                <li><a href="/newsService/prePublishNews">发布新闻</a></li>
              </c:if>
            </ul>
          <li>
          <li>
            <span>通知公告</span>
              <ul>
                <li><a href="/noticeService/listAllNotices/1">查看公告</a></li>
                <c:if test="${not empty sessionScope.user && sessionScope.user.userType != 3}">
                  <li><a href="/noticeService/prePublishNotice">发布公告</a></li>
                </c:if>
              </ul>
          <li>
            <span>招商引资</span>
            <ul>
              <li><a href="/inutatccmOfTenderService/listTenderInfo/1">招标信息</a></li>
              <c:if test="${not empty sessionScope.user && sessionScope.user.userType != 3}">
                <li><a href="/inutatccmOfTenderService/preCreateTenderInfo">发布招标信息</a></li>
              </c:if>
            </ul>
          <li>
            <span>文件下载</span>
            <ul>
              <li><a href="/fileService/listFiles/type/6">政府工作报告</a></li>
              <li><a href="/fileService/listFiles/type/5">政策文件</a></li>
              <li><a href="/fileService/listFiles/type/4">法规文件</a></li>
              <li>
              <c:if test="${sessionScope.user != null && sessionScope.user.userType != 3}">
                <a href="/fileService/preUploadFile"><span>上传文件</span></a>
              </c:if>
              </li>
            </ul>
          <li>
            <span>办事指南</span>
            <ul>
              <li><a href="/guide/tenderGuide">竞标申请流程</a></li>
              <li><a href="/guide/suggestGuide">意见反馈流程</a></li>
              <li><a href="/guide/complainGuide">投诉举报流程</a></li>
            </ul>
          <li>
            <span>投诉举报</span>
            <ul>
              <li><a href="/suggestionService/listSuggestions/type/1/1">群众的举报</a></li>
              <li><a href="/suggestionService/preMakeSuggestion/type/1">举报违法违纪行为</a></li>
              <%--<li><a href="#">给领导写信</a></li>--%>
            </ul>
          <li>
            <span>建议&求助</span>
            <ul>
              <li><a href="/suggestionService/listSuggestions/type/0/1">群众的建议</a></li>
              <li><a href="/suggestionService/listSuggestions/type/2/1">群众遇到的困难</a></li>
              <li><a href="/suggestionService/preMakeSuggestion/type/0">提出建议</a></li>
              <li><a href="/suggestionService/preMakeSuggestion/type/2">寻求帮助</a></li>
            </ul>
          <li>
            <span>个人中心</span>
            <ul>
              <li><a href="/userService/getPersonalInfo">我的信息</a></li>
              <li><a href="/userService/preChangePassword">修改密码</a></li>
              <li><a href="/tenderService/listMyCompetitive">我竞标的项目</a></li>
              <%--<li><a href="#">参与投票</a></li>--%>
            </ul>
          <li>
          <span>我的信箱</span>
          <ul>
            <li><a href="/mailboxService/viewInbox">收件箱</a></li>
            <li><a href="/mailboxService/viewOutbox">已发送邮件</a></li>
            <li><a href="/mailboxService/viewDrafts">草稿箱</a></li>
            <li><a href="/mailboxService/preMakeMail">写信</a></li>
            <li><a href="/mailboxService/preMakeMail?receiver=leader">给区长写信</a></li>
          </ul>
        </ul>
      </div>
    </div>
  </header>
</div>


<script type="text/javascript" src="/resources/js/jquery/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery/modernizr.js"></script>
