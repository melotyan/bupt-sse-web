/**
 * Created by melot on 2016/5/2.
 */
$(document).ready(function() {
    listInbox();

    function listInbox() {
        $(".mail-list").html("");
        $.ajax({
            url: "/mailboxService/viewInbox",
            type: "get",
            dataType: "text",
            success: function(data) {
                var dataObj = eval("(" + data + ")");
                for (var i = 0; i < dataObj.length; i++) {
                    var id = dataObj[i].id;
                    if (i == 0) {
                        readMail(id);
                    }
                    var div = $("<div>");
                    div.addClass("mail-box");
                    div.html(dataObj[i].title);
                    div.click(function () {
                        readMail(id);
                    });
                    var link = $("<a>");
                    $(".mail-list").append(div);
                }
            },
            error: function(data) {
                location.href = "/userService/preLogin";
            }
        });
    }

    function readMail(id) {
        $(".mail-content").html("");
        $.ajax({
            url: "/mailboxService/readMail/id/" + id,
            type: "get",
            dataType: "json",
            success: function (data) {
                var title = $("<div>");
                title.html("信件标题: " + data.title);
                var content = $("<div>");
                var div1 = $("<div>");
                div1.html("信件内容");
                var div2 = $("<div>");
                div2.html(data.content);
                content.append(div1);
                content.append(div2);
                $(".mail-content").append(title);
                $(".mail-content").append(content);
            }
        })
    }

    function newMail() {
        $(".mail-list").hide();
        $(".mail-content").hide();
        //var div = $("<div>");
        //div.add
        //$(".mail-div").append()
    }
})





