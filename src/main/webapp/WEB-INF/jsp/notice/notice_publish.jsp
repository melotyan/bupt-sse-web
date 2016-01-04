<%--
  Created by IntelliJ IDEA.
  User: hao.yan
  Date: 2016/1/4
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发布公告</title>
  <script type="text/javascript">
    i = 1;
    j = 1;
    $(document).ready(function(){

      $("#btn_add1").click(function(){
        document.getElementById("newUpload1").innerHTML+='<div id="div_'+i+'"><input  name="file" type="file"  /><input type="button" value="删除"  onclick="del_1('+i+')"/></div>';
        i = i + 1;
      });

      $("#btn_add2").click(function(){
        document.getElementById("newUpload2").innerHTML+='<div id="div_'+j+'"><input  name="file_'+j+'" type="file"  /><input type="button" value="删除"  onclick="del_2('+j+')"/></div>';
        j = j + 1;
      });
    });

    function del_1(o){
      document.getElementById("newUpload1").removeChild(document.getElementById("div_"+o));
    }

    function del_2(o){
      document.getElementById("newUpload2").removeChild(document.getElementById("div_"+o));
    }

  </script>
</head>
<body>
    <form id="notice_publish" action="/noticeService/publishNotice" method="post">
      <div>
        <div>标题</div>
        <input class="title" type="text" name="title"/>
      </div>
      <div>
        <div>内容</div>
        <textarea class="notice_content" name="content"></textarea>
      </div>
      <div class="upload">
        <%--<div class="upl"--%>
      </div>
    </form>
</body>
</html>
