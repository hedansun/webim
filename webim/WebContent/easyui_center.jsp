<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<div title="首页" data-options="tabWidth:112" style="">
		This is the help content.<br>
	</div>
	<script type="text/javascript">
		function addPanel(title,content){
			$('#tt').tabs('add',{
				title: title,
				content: content,
				closable: true
			});
			var tabs = $('#tt').tabs().tabs('tabs');
			for(var i=0; i<tabs.length; i++){
				tabs[i].panel('options').tab.unbind().bind('mouseenter',{index:i},function(e){
					$('#tt').tabs('select', e.data.index);
				});
			}
		}
	</script>
</body>
</html>