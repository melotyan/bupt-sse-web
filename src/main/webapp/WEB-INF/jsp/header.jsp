<%--
  Created by IntelliJ IDEA.
  User: melot
  Date: 2016/3/24
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href='http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Great+Vibes' rel='stylesheet' type='text/css'>
<link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="/resources/css/fontello.css" rel="stylesheet" type="text/css"/>
<link href="/resources/css/flexslider.css" rel="stylesheet" type="text/css"/>
<link href="/resources/css/settings.css" rel="stylesheet" type="text/css" media="screen" />
<link href="/resources/css/owl.carousel.css" rel="stylesheet" type="text/css"/>
<link href="/resources/css/responsive-calendar.css" rel="stylesheet" type="text/css"/>
<link href="/resources/css/chosen.css" rel="stylesheet" type="text/css"/>
<link href="/resources/css/jackbox.min.css" rel="stylesheet" type="text/css" />
<link href="/resources/css/cloud-zoom.css" rel="stylesheet" type="text/css" />
<link href="/resources/css/style.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
  .no-fouc {display:none;}
</style>
<link href="/resources/css/header.css" rel="stylesheet" type="text/css"/>


<header id="header" class="sticky-header" style="opacity: 1;">

  <!-- Main Header -->
  <div id="main-header">
    <div class="container">
      <div class="row">
        <div id="logo" class="col-lg-3 col-md-3 col-sm-3">
          <a href="main-v1.html"><img src="/resources/img/logo.png" alt="Logo"></a>
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
      <ul id="navigation" style="">
        <!-- Home -->
        <li class="home-button ">
          <a href="main-v1.html"><i class="icons icon-home"></i></a>
        <!-- Pages -->
        <li>
          <span>政府新闻</span>
        <li>
          <span>招商引资</span>
          <ul>
            <li><a href="event-calendar.html">招标信息</a></li>
            <li><a href="event-post-v1.html">竞标</a></li>
            <li><a href="event-post-v2.html">发布招标信息</a></li>
          </ul>
          <div class="dropdown-button"></div></li>
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
              <div class="dropdown-button"></div></li>
            <li>
              <span>招标文件</span>
              <ul>
                <li><a href="media-grid-1column-sidebar.html">1 Column with right sidebar</a></li>
                <li><a href="media-grid-2columns.html">2 Columns</a></li>
                <li><a href="media-grid-3columns.html">3 Columns</a></li>
                <li><a href="media-grid-3columns-sidebar.html">3 Columns with left sidebar</a></li>
                <li><a href="media-grid-4columns.html">4 Columns</a></li>
              </ul>
              <div class="dropdown-button"></div></li>
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
              <div class="dropdown-button"></div></li>
            <li>
              <span>上传文件</span>
              <ul>
                <li><a href="portfolio-single-fullwidth.html">Fullwidth</a></li>
                <li><a href="portfolio-single-sidebar.html">With Sidebar</a></li>
                <li><a href="portfolio-single-extended.html">Extended Image Slideshow</a></li>
              </ul>
              <div class="dropdown-button"></div></li>
          </ul>
          <div class="dropdown-button"></div></li>
        <li>
          <a href="get-involved.html">便民信息</a>
        </li>
        <li class="current-menu-item">
          <span>办事指南</span>
          <ul>
            <li><a href="features-typography.html">竞标申请流程</a></li>
            <li><a href="features-shortcodes.html">意见反馈流程</a></li>
            <li><a href="features-shortcodes.html">投诉举报流程</a></li>
          </ul>
          <div class="dropdown-button"></div></li>
        <li>
          <span>意见反馈</span>
          <ul>
            <li><a href="blog-v1.html">领导信箱</a></li>
            <li><a href="blog-v2.html">违纪举报</a></li>
            <li><a href="blog-fullwidth.html">提建议</a></li>
            <li><span>提出政府工作建议</span>
              <ul>
                <li><a href="blog-single-sidebar.html">With sidebar</a></li>
                <li><a href="blog-single-fullwidth.html">Full width</a></li>
              </ul>
              <div class="dropdown-button"></div></li>
          </ul>
          <div class="dropdown-button"></div></li>
        <li>
          <span>个人中心</span>
          <ul>
            <li><a href="shop-frontpage.html">个人信息</a></li>
            <li><a href="shop-productpage.html">修改密码</a></li>
          </ul>
          <div class="dropdown-button"></div></li>
        <%--<li class="donate-button ">--%>
          <%--<a href="#">Donate Today</a>--%>
        <%--</li>--%>
      </ul>
    </div>
  </div>
</header>

<script src="/resources/js/jquery/jquery-1.11.0.min.js"></script>
<script src="/resources/js/jquery/jquery-ui-1.10.4.min.js"></script>
<script src="/resources/js/jquery/jquery.queryloader2.min.js"></script>
<script type="text/javascript">
  $('html').addClass('no-fouc');

  $(document).ready(function(){

    $('html').show();

    var window_w = $(window).width();
    var window_h = $(window).height();
    var window_s = $(window).scrollTop();

    $("body").queryLoader2({
      backgroundColor: '#f2f4f9',
      barColor: '#63b2f5',
      barHeight: 4,
      percentage:false,
      deepSearch:true,
      minimumTime:1000,
      onComplete: function(){

        $('.animate-onscroll').filter(function(index){

          return this.offsetTop < (window_s + window_h);

        }).each(function(index, value){

          var el = $(this);
          var el_y = $(this).offset().top;

          if((window_s) > el_y){
            $(el).addClass('animated fadeInDown').removeClass('animate-onscroll');
            setTimeout(function(){
              $(el).css('opacity','1').removeClass('animated fadeInDown');
            },2000);
          }

        });

      }
    });

  });
</script>