/**
 * Created by melot on 2016/4/7.
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
            alert("文件上传失败");
        }
    });

});

function publish() {
    $.ajax( {
        url: '/inutatccmOfTenderService/createTenderInfo',
        type: 'POST',
        dataType: 'json',
        data: $('#create-tender').serialize(),
        success: function (json) {
            alert("发布成功");
            location.href='/inutatccmOfTenderService/listTenderInfo/1';
        },
        err: function(err) {
            alert("发布失败");
        }
    })
}

function delTender(url) {
    $.get(url,
        function(data) {
            if (data.result == "SUCCESS") {
                alert("删除成功");
                location.href="/inutatccmOfTenderService/listTenderInfo/1";
            }else
                alert(data.map.msg);
        }
    )
}

$("#btn-edit-notice").click(function(){
    $.ajax({
        url: "/inutatccmOfTenderService/editTenderInfo",
        type: "post",
        data: $("#eidt-notice-form").serialize(),
        success: function(data) {
            if (data.result == "SUCCESS") {
                alert("修改成功");
                location.href="/inutatccmOfTenderService/listTenderInfo/1";
            } else {
                alert(data.map.msg);
            }
        }
    })
})

$("#btn-competitive").click(function() {
    var formData = new FormData($("#upload-form")[0]);
    $.ajax({
        url: "/fileService/uploadFiles",
        type: "POST",
        dataType: "json",
        contentType: false,
        processData: false,
        cache: false,
        data: formData,
        success: function (strVal) {
            $("#fileUrl").val(JSON.stringify(strVal));
            competitive();
        },
        error: function (err) {
            alert("文件上传失败" + JSON.stringify(err));
        }
    })
})

function competitive() {
    $.ajax({
        url: "/tenderService/competitiveTender",
        data: $("#competitive-form").serialize(),
        type: "post",
        success: function(data) {
            alert(data.map.msg);
        }
    })
}


