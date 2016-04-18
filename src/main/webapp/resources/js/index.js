/**
 * Created by melot on 2016/4/18.
 */
$(document).ready(function() {
    getNews();

    $("#sItem li:not(:first)").css("display","none");
    var B=$("#sItem li:last");
    var C=$("#sItem li:first");
    setInterval(function() {
        if(B.is(":visible")){
            C.fadeIn(500).addClass("in");
            B.hide();
        }else{
            $("#sItem li:visible").addClass("in");
            $("#sItem li.in").next().fadeIn(500);
            $("li.in").hide().removeClass("in")}
    },3000); //每3秒钟切换一条，你可以根据需要更改

    function getNews() {
        $.ajax({
            url: "/newsService/listNews/data",
            type: "post",
            dataType: "text",
            async: false,
            success: function(data) {
                var dataObj = eval("("+data+")");
                var count = 0;
                for (var i = 0; i < dataObj.length; i++) {
                    if (dataObj[i].firstPic != "") {
                        var img = $("<img>");
                        img.attr("src", dataObj[i].firstPic);
                        img.addClass("index-img");
                        var link = $("<a>");
                        link.attr("href", "/newsService/viewNewsDetail/id/" + dataObj[i].id);
                        link.append(img);
                        var li = $("<li>");
                        li.append(link);
                        $("#sItem").append(li);
                        count++;
                        if (count == 3)
                            break;
                    }
                }
            }
        })
    }
});

