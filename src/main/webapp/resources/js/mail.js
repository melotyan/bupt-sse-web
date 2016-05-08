/**
 * Created by melot on 2016/5/2.
 */
$(document).ready(function () {
    var element = $("#m-content");
    var temp = element.text().replace(/\n/g, '<br/>');
    element.html(temp);
    listInbox();
})

function listInbox() {
    $(".mail-list").html("");
    $.ajax({
        url: "/mailboxService/viewInbox",
        type: "get",
        dataType: "text",
        success: function (data) {
            var dataObj = eval("(" + data + ")");
            for (var i = 0; i < dataObj.length; i++) {
                var id = dataObj[i].id;
                if (i == 0) {
                    readMail(id);
                }
                var div = $("<div>");
                div.addClass("mail-box");
                div.val(id);
                div.html(dataObj[i].title);
                div.click(function () {
                    readMail($(this).val());
                });
                $(".mail-list").append(div);
            }
        },
        error: function (data) {
            location.href = "/userService/preLogin";
        }
    });
}

function readMail(id) {
    $.ajax({
        url: "/mailboxService/readMail/id/" + id,
        type: "get",
        dataType: "json",
        success: function (data) {
            $(".m-title").html(data.title);
            $("#sender-li").html('发&nbsp;件&nbsp;人:&nbsp;&nbsp;' + data.senderName);
            $("#receiver-li").html('收&nbsp;件&nbsp;人:&nbsp;&nbsp;' + data.receiverName);
            $("#send-time").html('时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间:&nbsp;&nbsp;' + formatDate(data.sendTime));
            $("#m-content").html(data.content);
        }
    })
}

function formatDate (strTime) {
    var date = new Date(strTime);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate() + " " + date.getHours() +
        ":" + date.getMinutes() + ":" + date.getSeconds();
}

function newMail() {
    //$(".mail-list").hide();
    //$(".mail-content").hide();
    //var div = $("<div>");
    //div.add
    //$(".mail-div").append()
}

function listOutbox() {
    $(".mail-list").html("");
    $.ajax({
        url: "/mailboxService/viewOutbox",
        type: "get",
        dataType: "text",
        success: function (data) {
            var dataObj = eval("(" + data + ")");
            for (var i = 0; i < dataObj.length; i++) {
                var id = dataObj[i].id;
                if (i == 0) {
                    readMail(id);
                }
                var div = $("<div>");
                div.addClass("mail-box");
                div.val(id);
                div.html(dataObj[i].title);
                div.click(function () {
                    readMail($(this).val());
                });
                $(".mail-list").append(div);
            }
        },
        error: function (data) {
            location.href = "/userService/preLogin";
        }
    });
}

function listDrafts() {
    $(".mail-list").html("");
    $.ajax({
        url: "/mailboxService/viewDrafts",
        type: "get",
        dataType: "text",
        success: function (data) {
            var dataObj = eval("(" + data + ")");
            for (var i = 0; i < dataObj.length; i++) {
                var id = dataObj[i].id;
                if (i == 0) {
                    readMail(id);
                }
                var div = $("<div>");
                div.addClass("mail-box");
                div.val(id);
                div.html(dataObj[i].title);
                div.click(function () {
                    readMail($(this).val());
                });
                $(".mail-list").append(div);
            }
        },
        error: function (data) {
            location.href = "/userService/preLogin";
        }
    });
}





