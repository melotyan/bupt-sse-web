/**
 * Created by melot on 2016/4/18.
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
    var formData = new FormData($("#upload-form")[0]);
    formData.append("type", $("#file-type-selected").val());
    $.ajax({
        url: "/fileService/uploadFiles",
        type: "post",
        data: formData,
        dataType: "json",
        contentType: false,
        processData: false,
        cache: false,
        success: function(data) {
            alert("上传成功");
            $("div[id^='div_']").remove();
        },
        error: function(data) {
            alert("文件上传失败,请先登陆");
        }
    })
})