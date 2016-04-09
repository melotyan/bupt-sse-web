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
            北太平庄街道电子政务网
            <%--</blockquote>--%>
          </div>
        </div>
        <div class="login-header">
          <a class="link" href="/userService/preLogin">登陆</a>&nbsp&nbsp&nbsp<a class="link" href="/userService/preRegister">注册</a>
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
            <span>政府新闻</span>
              <ul>
                <li><a href="/noticeService/listAllNotices/1">查看新闻</a></li>
                <c:if test="${not empty sessionScope.user && sessionScope.user.userType != 3}">
                  <li><a href="/noticeService/prePublishNotice">发布新闻</a></li>
                </c:if>
              </ul>
          <li>
            <span>招商引资</span>
            <ul>
              <li><a href="/inutatccmOfTenderService/listTenderInfo/1">招标信息</a></li>
              <li><a href="event-post-v1.html">竞标</a></li>
              <c:if test="${not empty sessionScope.user && sessionScope.user.userType != 3}">
                <li><a href="/inutatccmOfTenderService/preCreateTenderInfo">发布招标信息</a></li>
              </c:if>
            </ul>
          <li>
            <span>文件下载</span>
            <ul>
              <li>
                <span>法规文件</span>
                <ul>
                  <li><a href="media-sortable-1column-sidebar.html">1 Column with right sidebar</a></li>
                  <li><a href="media-sortable-2columns.html">2 Columns</a></li>
                  <li><a href="media-sortable-3columns.html">3 Columns</a></li>
                  <li><a href="media-sortable-3columns-sidebar.html">3 Columns with left sidebar</a></li>
                  <li><a href="media-sortable-4columns.html">4 Columns</a></li>
                </ul>
              <li>
                <span>招标文件</span>
                <ul>
                  <li><a href="media-grid-1column-sidebar.html">1 Column with right sidebar</a></li>
                  <li><a href="media-grid-2columns.html">2 Columns</a></li>
                  <li><a href="media-grid-3columns.html">3 Columns</a></li>
                  <li><a href="media-grid-3columns-sidebar.html">3 Columns with left sidebar</a></li>
                  <li><a href="media-grid-4columns.html">4 Columns</a></li>
                </ul>
              <li>
                <span>政策文件</span>
                <ul>
                  <li><a href="media-classic-sortable-3columns.html">Sortable 3 Columns</a></li>
                  <li><a href="media-classic-sortable-3columns-sidebar.html">Sortable 3 Columns with right sidebar</a></li>
                  <li><a href="media-classic-sortable-4columns.html">Sortable 4 Columns</a></li>
                  <li><a href="media-classic-3columns.html">3 Columns</a></li>
                  <li><a href="media-classic-3columns-sidebar.html">3 Columns with left sidebar</a></li>
                  <li><a href="media-classic-4columns.html">4 Columns</a></li>
                </ul>
              <li>
                <span>上传文件</span>
                <ul>
                  <li><a href="portfolio-single-fullwidth.html">Fullwidth</a></li>
                  <li><a href="portfolio-single-sidebar.html">With Sidebar</a></li>
                  <li><a href="portfolio-single-extended.html">Extended Image Slideshow</a></li>
                </ul>
            </ul>
          <li>
            <span>意见征集</span>
            <ul>
              <li><a href="">参与投票</a></li>
              <li><a href="">发起投票</a></li>
            </ul>
          </li>
          <li>
            <span>办事指南</span>
            <ul>
              <li><a href="features-typography.html">竞标申请流程</a></li>
              <li><a href="features-shortcodes.html">意见反馈流程</a></li>
              <li><a href="features-shortcodes.html">投诉举报流程</a></li>
            </ul>
          <li>
            <span>意见反馈</span>
            <ul>
              <li><a href="/suggestionService/listSuggestions/1">群众的心声</a></li>
              <li><a href="blog-v1.html">给领导写信</a></li>
              <li><a href="blog-v2.html">举报违法违纪行为</a></li>
              <li><a href="blog-fullwidth.html">提出政府工作建议</a></li>
              <li><span>提出政府工作建议</span>
                <ul>
                  <li><a href="blog-single-sidebar.html">With sidebar</a></li>
                  <li><a href="blog-single-fullwidth.html">Full width</a></li>
                </ul>
            </ul>
          <li>
            <span>个人中心</span>
            <ul>
              <li><a href="/userService/getPersonalInfo">个人信息</a></li>
              <li><a href="/userService/preChangePassword">修改密码</a></li>
            </ul>
          <%--<li class="donate-button ">--%>
            <%--<a href="#">Donate Today</a>--%>
          <%--</li>--%>
        </ul>
      </div>
    </div>
  </header>
</div>


<script type="text/javascript" src="/resources/js/jquery/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery/modernizr.js"></script>
<script type="text/javascript" src="/resources/js/script.js"></script>
