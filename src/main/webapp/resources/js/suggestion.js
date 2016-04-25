/**
 * Created by melot on 2016/4/11.
 */
$("#btn-notice").click(function () {
    $.ajax( {
        url: '/suggestionService/makeSuggestion',
        type: 'POST',
        dataType: 'json',
        data: $('#create-tender').serialize(),
        success: function (json) {
            alert("发布成功");
            location.href='/suggestionService/listSuggestions/1';
        },
        err: function(err) {
            alert("发布失败");
        }
    })
})

$("#btn-edit-notice").click(function () {
    $.ajax({
        url: "/suggestionService/editSuggestion",
        type: "post",
        data: $("#edit-suggestion-form").serialize(),
        dataType: "json",
        success: function (data) {
            alert(data.map.msg);
        }
    })
})
