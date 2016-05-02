/**
 * Created by melot on 2016/5/2.
 */
$(document).ready(function() {


})
function listInbox() {
    $.ajax({
        url: "/mailboxService/viewInbox",
        type: "get",
        success: function(data) {
            var dataObj = eval("(" + data + ")");
            $("#mail-list").clear();
            for (var i = 0; i < dataObj.length; i++) {
                var div = $("<div>");
                div.html(dataObj[i].title);
                div.onclick(readMail(dataObj[i].id));
                $("#mail-list").append(div);
            }
            var div = $("<div>");
        }
    })
}

function readMail(id) {
    $.ajax({
        url: "/mailboxService/readeMail/" + id,
        type: "get",
        dataType: "json",
        success: function (data) {
            var title = $("<div>");
            var content = $("<div>");
        }
    })
}