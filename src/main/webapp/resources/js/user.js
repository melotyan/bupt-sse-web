/**
 * Created by hao.yan on 2016/1/4.
 */

$(".imgObj").click(function() {
    var $img = $(".imgObj");
    var url = chgUrl("/captcha.jpg");
    $img.attr("src", url);
    //$.get(url, function() {
    //    $img.attr("src", url);
    //})
})

//时间戳
//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
function chgUrl(url) {
    var timestamp = (new Date()).valueOf();
    url = url.substring(0, 17);
    if ((url.indexOf("&") >= 0)) {
        url = url + "×tamp=" + timestamp;
    } else {
        url = url + "?timestamp=" + timestamp;
    }
    return url;
}



