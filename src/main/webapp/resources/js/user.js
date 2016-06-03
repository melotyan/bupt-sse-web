/**
 * Created by hao.yan on 2016/1/4.
 */

function changeCaptcha() {
    var $img = $(".imgObj");
    var url = chgUrl("/captcha.jpg");
    $img.attr("src", url);
}
$(".imgObj").click(function() {
    var $img = $(".imgObj");
    var url = chgUrl("/captcha.jpg");
    $img.attr("src", url);
});

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

function changeInfo() {
    $.ajax({
        url: "/userService/editPersonalInfo",
        type: "post",
        data: $("#person-info-form").serialize(),
        success: function(data) {
            alert(data.map.msg);
        }
    })
}

function changePW() {
    $.ajax({
        url: "/userService/changePassword",
        type: "post",
        data: $("#password-form").serialize(),
        success: function(data) {
            alert(data.map.msg);
        }
    })
}

function login() {
    $.ajax({
        url: "/userService/login",
        type: "post",
        data: $("#login-form").serialize(),
        success: function(data) {
            if (data.result == "SUCCESS")
                location.href = data.map.redirect;
            else {
                alert(data.map.msg);
                changeCaptcha();
            }
        }
    })
}

function register() {
    $.ajax({
        url: "/userService/register",
        type: "post",
        data: $("#register-form").serialize(),
        success: function(data) {
            alert(data.map.msg);
            if (data.result == "SUCCESS")
                location.href = "/userService/preLogin";
            else
                changeCaptcha();
        }
    });
}

function managerUser(userid, count) {
    $.ajax({
        url: "/userService/manageUser",
        type: "post",
        data: {"uid":userid, "userType":$("#userType"+count).val(), "accountStatus":$("#accountStatus"+count).val()},
        dataType: "json",
        success: function(data) {
            alert(data.map.msg);
        }
    })
}

