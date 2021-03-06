/**
 * Created by melot on 2016/4/18.
 */
$(document).ready(function() {
    getNews();
    getNotices();
    getTenderInfos();
    //mouseHover();
    function mouseHover() {
        var link = $(".index-div ul li");
        link.hover(function() {
            link.html().hide();
        })

    }

    function showNewsPic() {
        $("#sItem li:not(:first)").css("display","none");
        var B=$("#sItem li:last");
        var C=$("#sItem li:first");
        setInterval(function() {
            if(B.is(":visible")){
                C.show().addClass("in");
                B.hide();
            }else{
                $("#sItem li:visible").addClass("in");
                $("#sItem li.in").next().show();
                $("li.in").hide().removeClass("in");
            }
        },3500); //每3秒钟切换一条，你可以根据需要更改
    }

    function getNews() {
        $.ajax({
            url: "/newsService/listNews/data",
            type: "post",
            dataType: "text",
            success: function(data) {
                var dataObj = eval("("+data+")");
                var countImg = 0;
                var count = 0;
                var PIC_NUM = 4;
                for (var i = 0; i < dataObj.length; i++) {
                    if (countImg < PIC_NUM && dataObj[i].firstPic != "") {
                        var img = $("<img>");
                        img.attr("src", dataObj[i].firstPic);
                        img.addClass("index-img");
                        var span = $("<span>");
                        span.html(dataObj[i].title);
                        span.addClass("img-span");
                        var link = $("<a>");
                        link.attr("href", "/newsService/viewNewsDetail/id/" + dataObj[i].id);
                        link.append(img);
                        link.append(span);
                        var li = $("<li>");
                        li.append(link);
                        $("#sItem").append(li);
                        countImg++;
                    }
                    if (countImg == PIC_NUM)
                        showNewsPic();
                    if (count < 10) {
                        var li = $("<li>");
                        var link = $("<a>");
                        link.attr("href", "/newsService/viewNewsDetail/id/" + dataObj[i].id);
                        link.html(dataObj[i].title);
                        li.append(link);
                        $("#news-div ul").append(li);
                    }
                    count++;
                }
            }
        })
    }

    function getNotices() {
        $.ajax({
            url: "/noticeService/listAllNotices/data",
            type: "post",
            dataType: "text",
            success: function(data) {
                var dataObj = eval("(" + data + ")");
                for (var i = 0; i < dataObj.length; i++) {
                    var li = $("<li>");
                    var link = $("<a>");
                    link.attr("href", "/noticeService/viewNoticeDetail/" + dataObj[i].id);
                    link.html(dataObj[i].title);
                    li.append(link);
                    $("#notices-div ul").append(li);
                }
            }
        })
    }

    function getTenderInfos() {
        $.ajax({
            url: "/inutatccmOfTenderService/listTenderInfo/data",
            type: "post",
            dataType: "text",
            success: function(data) {
                var dataObj = eval("(" + data + ")");
                for (var i = 0; i < dataObj.length; i++) {
                    var li = $("<li>");
                    var link = $("<a>");
                    link.attr("href", "/inutatccmOfTenderService/viewTenderDetail/" + dataObj[i].id);
                    link.html(dataObj[i].title);
                    li.append(link);
                    $("#tender-div ul").append(li);
                }
            }
        })
    }
});

