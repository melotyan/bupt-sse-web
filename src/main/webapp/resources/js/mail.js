/**
 * Created by melot on 2016/5/2.
 */
$(document).ready(function () {
    //对邮件内容排一下版
    var element = $("#m-content");
    var temp = element.text().replace(/\n/g, '<br/>');
    element.html(temp);
    //获取未读邮件的数量
    listUnreadMails();
})


function listUnreadMails() {
    $.ajax({
        url: "/mailboxService/listUnreadedMails",
        type: "get",
        dataType: "text",
        success: function (data) {
            var dataObj = eval("(" + data + ")");
            var count = 0;
            for (var i = 0; i < dataObj.length; i++) {
                if (dataObj[i].receiverStatus == 0)
                    count++;
            }
            if (count != 0) {
                $(".mail-num").html(" " + count)
            }
        }
    });
}

function readMail(id) {
    $.ajax({
        url: "/mailboxService/setMailReaded/id/" + id,
        type: "get",
        dataType: "json",
        success: function (data) {
            var num = Number($(".mail-num").val()) - 1;
            location.href = "/mailboxService/readMail/id/" + id;
            if (num != 0)
                $(".mail-num").html(" " + num)
            else
                $(".mail-num").html("");
        }
    })

}

function sendMail() {
    $.ajax({
        url: "/mailboxService/sendMail",
        type: "post",
        data: $("#mail-create-form").serialize(),
        dataType: "json",
        success: function (data) {
            alert(data.map.msg);
            if (data.result == "SUCCESS")
                location.href = "/mailboxService/viewOutbox";
        }
    })
}
function sendMailEdit() {
    $.ajax({
        url: "/mailboxService/sendMail",
        type: "post",
        data: $("#mail-edit-form").serialize(),
        dataType: "json",
        success: function (data) {
            alert(data.map.msg);
            if (data.result == "SUCCESS")
                location.href = "/mailboxService/viewOutbox";
        }
    })
}
function saveDraft() {
    $.ajax({
        url: "/mailboxService/saveDraft",
        type: "post",
        data: $("#mail-create-form").serialize(),
        dataType: "json",
        success: function (data) {
            alert(data.map.msg);
            if (data.result == "SUCCESS")
                location.href = "/mailboxService/viewDrafts";
        }
    })
}
function saveDraftEdit() {
    $.ajax({
        url: "/mailboxService/saveDraft",
        type: "post",
        data: $("#mail-edit-form").serialize(),
        dataType: "json",
        success: function (data) {
            alert(data.map.msg);
            if (data.result == "SUCCESS")
                location.href = "/mailboxService/viewDrafts";
        }
    })
}

function delMail(url, type) {
    $.ajax({
        url: url,
        type: "get",
        dataType: "json",
        success: function (data) {
            alert(data.map.msg);
            if (data.result == "SUCCESS") {
                if (type == 0) //回到收信箱
                    location.href = "/mailboxService/viewInbox";
                else if (type == 1) //回到发信箱
                    location.href = "/mailboxService/viewOutbox";
                else //回到草稿箱
                    location.href = "/mailboxService/viewDrafts";
            }
        }
    })
}
function editDraft(type) {
    $.ajax({
        url: "/mailboxService/editDraft",
        type: "post",
        data: $("#mail-edit-form").serialize(),
        dataType: "json",
        async: "false",
        success: function (data) {
            if (data.success == "FAILED")
                alert(data.map.msg);
            else {
                if (type == 0) {//此草稿不用发送
                    alert(data.map.msg);
                    location.href = "/mailboxService/viewDrafts";
                }
            }
        }
    })
}
function sendDraft(id) {
    editDraft(1);
    $.ajax({
        url: "/mailboxService/sendDraft/id/" + id,
        type: "get",
        dataType: "json",
        success: function (data) {
            alert(data.map.msg);
            if (data.result == "SUCCESS")
                location.href = "/mailboxService/viewOutbox";
        }
    })
}

function formatDate (strTime) {
    var date = new Date(strTime);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate() + " " + date.getHours() +
        ":" + date.getMinutes() + ":" + date.getSeconds();
}

//function newMail() {
//    //$(".mail-list").hide();
//    //$(".mail-content").hide();
//    //var div = $("<div>");
//    //div.add
//    //$(".mail-div").append()
//}

//function listOutbox() {
//    $(".mail-list").html("");
//    $.ajax({
//        url: "/mailboxService/viewOutbox",
//        type: "get",
//        dataType: "text",
//        success: function (data) {
//            var dataObj = eval("(" + data + ")");
//            for (var i = 0; i < dataObj.length; i++) {
//                var id = dataObj[i].id;
//                if (i == 0) {
//                    readMail(id, 0);
//                }
//                var div = $("<div>");
//                div.addClass("mail-box");
//                div.val(id);
//                div.html(dataObj[i].title);
//                div.click(function () {
//                    readMail($(this).val(), 0);
//                });
//                $(".mail-list").append(div);
//            }
//        },
//        error: function (data) {
//            location.href = "/userService/preLogin";
//        }
//    });
//}

//function listDrafts() {
//    $(".mail-list").html("");
//    $.ajax({
//        url: "/mailboxService/viewDrafts",
//        type: "get",
//        dataType: "text",
//        success: function (data) {
//            var dataObj = eval("(" + data + ")");
//            for (var i = 0; i < dataObj.length; i++) {
//                var id = dataObj[i].id;
//                if (i == 0) {
//                    readMail(id, 0);
//                }
//                var div = $("<div>");
//                div.addClass("mail-box");
//                div.val(id);
//                div.html(dataObj[i].title);
//                div.click(function () {
//                    readMail($(this).val(), 0);
//                });
//                $(".mail-list").append(div);
//            }
//        },
//        error: function (data) {
//            location.href = "/userService/preLogin";
//        }
//    });
//}





