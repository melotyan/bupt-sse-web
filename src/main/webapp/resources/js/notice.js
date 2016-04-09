/**
 * Created by hao.yan on 2016/1/7.
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

//$(".list-detail:even").css("background", "#FBF5D9");
//$(".list-detail:odd").css("background", "#F2F6F9");
//$(".list-detail:even").hover(function() {
//    $('.list-detail').css('background', 'yellow');
//}, function() {
//    $(".list-detail:even").css("background", "#FBF5D9");
//});
//$(".list-detail:odd").hover(function() {
//    $('.list-detail:odd').css('background', 'yellow');
//}, function() {
//    $(".list-detail:odd").css("background", "#F2F6F9");
//})


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
            alert("文件上传失败");
        }
    });

});

function publish() {
    $.ajax( {
        url: '/noticeService/publishNotice',
        type: 'POST',
        dataType: 'json',
        data: $('#notice_publish').serialize(),
        success: function (json) {
            alert("发布成功");
            location.href='/noticeService/listAllNotices/1';
        },
        err: function(err) {
            alert("发布失败");
        }
    })
}




