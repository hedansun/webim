<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>即时通讯示例--自动登录</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jQuery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/im.plus.js?skin=im"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jQuery.im.js"></script>
<script type="text/javascript">
	var time1;
    $(function() {
        $("a.oWindow").click(function(){
            var uid=$(this).attr("uid");
             $(document).FnWebIM({
                 autoLogin          :true,      //boolean型，默认是否自动登录，true：自动登录，false：手动登录，默认为true
                 msgRefreshTime    :1000,       //number型，消息刷新时间，单位为ms
                 friendRefreshTime :10000,     //number型，好友刷新时间，单位为ms
                 showSecretary     :true,      //boolean型，默认是否显示小秘书，true：显示，false：不显示，默认为true
                 noticeContent     :"唐僧师徒历经千辛万苦，终于见到了佛祖……",        //string型，公告内容 为空时不显示公告
                 sendPicture       :true,      //boolean型，是否允许发送图片，true：允许，false：不允许，默认为true
                 msgMaxSize        :300,        //number型，单条消息最大允许字符
                 msgSound           :false,     //boolean型，是否开启声音提醒，true：开启，false:关闭，默认为true
                 defaultWindow     :uid   //string型，登录后打开新聊天窗口，此处接收的参数为联系人的uid，否则会出错
             });
        });
    });
</script>
</head>
<body>
<a class="oWindow" uid="1" style="display:block;width:90px;margin:20px;cursor: pointer;"><img src="img/imonline.png" /></a>
</body>
</html>