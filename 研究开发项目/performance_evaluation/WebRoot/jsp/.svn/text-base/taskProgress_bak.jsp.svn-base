<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'taskProgress.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		#div1{
			position: absolute; 
			left: 200px; 
			top: 100px;
		}
		#div2{
			position: absolute;
			left: 530px; 
			top: 207px;
		}
		#div3{
			position:absolute;
			right:0px;
			bottom:0px
		}
	</style>
	</head>

	<body>
		<div>
			<img src="images/task_input.png" />
		</div>
		<div id="div1">
			<img src="images/task.png" />
		</div>
		<br/>
		<br/>
		<div id="div2" style="color:#9ACD32;font-weight:bold;">
		<form action="taskProgress_input">
			<input  name="result" type="hidden" value="${result}"/>
			本月任务进度：
			<input style="width:100px;border:1px solid #8B8989;" name="progress" type="text"  style="width:50px;//宽度 height=20px;//高度"/ value="${progress}"/>
			%
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="submit" type="submit" value="提交" />	
		</form>
		</div>
		<div id="div3">
			<img src="images/welcom.jpg" />
		</div>
	</body>
</html>
