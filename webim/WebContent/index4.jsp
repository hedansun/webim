<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>即时通讯示例--自动登录</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jQuery-1.7.1.min.js"></script>
<script type="text/javascript">
$(function (){
	msgList(2,1);
});
function msgList(cid,uid){
	var time1=setInterval(function(){
    $.getJSON("servlet/ChatServlet?sAction=chats&cid="+cid+"&uid="+uid,function(json){
    	var msgList ="";
        $.each(json,function(i){
        	if(json[i].pid==uid){
            	msgList+='<div style="color: blue;">'+json[i].D+'</div><br><div>'+json[i].M+"</div><br><br>";
            }else if(json[i].pid==cid){
            	msgList+='<div style="color: gray;">我：'+json[i].D+'</div><br><div>'+json[i].M+"</div><br><br>";
            }
        });
        $("#messagelab").html(msgList);
        $('#messagelab').scrollTop( $('#messagelab')[0].scrollHeight );
    });
	},2000); 
}
function sendMsg(cid,uid){
	$.post('servlet/ChatServlet?sAction=sendMsg&cid='+cid+'&uid='+uid+'&msgContent='+encodeURI(encodeURI($("#msgContent").val())), function(data) {
		$("#msgContent").val("");
	});
}
</script>
</head>
<body style="font-size: 12px;">
<div>
<table border="1">
	<tr>
		<td><div style="width: 600px;height: 400px;overflow: scroll;" id="messagelab"></div></td>
	</tr>
	<tr>
		<td><textarea id="msgContent" rows="6" cols="8" style="width: 90%;height: 100%"></textarea><input type="button" value="发送" onclick="sendMsg(2,1)"></td>
	</tr>
</table>
</div>
</body>
</html>