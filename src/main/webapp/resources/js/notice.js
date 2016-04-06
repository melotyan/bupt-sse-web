/**
 * Created by hao.yan on 2016/1/7.
 */
j = 1;
$("#btn_add").click(function(){
    //document.getElementById("upload").innerHTML+='<div id="div_'+j+'"><input  name="files" type="file"  /><input type="button" value="删除"  onclick="del('+j+')"/></div>';
    //document.getElementById("upload").innerHTML+='<div id="div_'+j+'"><input  name="files" type="file"  /><input type="button" value="删除"  onclick="del('+j+')"/></div>';
    var div = document.createElement("div");
    div.id = 'div_' + j;
    div.innerHTML+='<input  name="files" type="file"  /><input type="button" value="删除"  onclick="del('+j+')"/>';
    document.getElementById("upload").appendChild(div);
    j = j + 1;
});



function del(o){
    document.getElementById("upload").removeChild(document.getElementById("div_"+o));
}
$(".list-detail:even").css("background", "#FBF5D9");
$(".list-detail div:odd").css("background", "#F2F6F9");



