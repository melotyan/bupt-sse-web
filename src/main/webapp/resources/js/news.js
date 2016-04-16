/**
 * Created by melot on 2016/4/16.
 */

j = 1;
$("#btn_add").click(function(){
    var div = $("<div></div>");
    div.attr("id", 'div_' + j);
    div.html('<input class="file" name="files" type="file"/><input class="del-btn" type="button" value="删除" onclick="del('+j+')"/>');
    $("#upload-form").append(div);
    j = j + 1;
});

function del(o){
    $("#div_" + o).remove();
}

$("#btn-notice").click(function() {
    $(".del-btn").remove();
    $("#btn_add").remove();
    var formData = new FormData($("#upload-form")[0]);
    $.ajax({
        url: "/fileService/uploadFiles",
        type: "POST",
        dataType: "json",
        contentType: false,
        processData: false,
        cache: false,
        data: formData,
        success: function(strVal) {
            $("#file-urls").val(JSON.stringify(strVal));
            publish();
        },
        error: function(err) {
            alert("文件上传失败,请先登陆");
        }
    });

});

function publish() {
    $.ajax( {
        url: '/newsService/publishNews',
        type: 'POST',
        dataType: 'json',
        data: $('#news-publish-form').serialize(),
        success: function (data) {
            alert(data.map.msg);
            if (data.result == "SUCCESS")
                location.href='/newsService/listNews/page/1';
        },
        err: function(data) {
            alert("发布失败");
        }
    })
}

function delNews(url) {
    $.get(url,
        function(data) {
            alert(data.map.msg);
            if (data.result == "SUCCESS")
                location.href="/newsService/listNews/page/1";
        }
    )
}

$("#btn-edit-notice").click(function () {
    $.ajax({
        url: "/newsService/updateNews",
        type: "post",
        data: $("#edit-news-form").serialize(),
        success: function(data) {
            alert(data.map.msg);
            if (data.result == "SUCCESS")
                location.href = "/newsService/listNews/page/1"
        },
        error: function(data) {
            alert("修改失败");
        }
    })
})

