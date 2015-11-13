<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="ContentType" content="html/text;charset=utf-8" />
		<title>绩效考评标记</title>
		
		<link href="../css/css.css" rel="stylesheet" type="text/css" />
		<link href="../css/style.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
		<style>
			#div1{
				position: absolute; 
				left: 150px; 
				top: 45px;
			}
			#div2{
				position: absolute; 
				left: 200px; 
				top: 45px;
			}
		</style>
		<script></script>
	</head>

	<body>
		<s:if test="${result=='true'}">
		<div id="div1">
			<div>&nbsp; 
				<img src="images/score_sign.png"></img>
			</div>
			<div style="border:1px solid #CCCCCC;margin: 10px 10px 10px 100px; width:750px;">
  				<p style="color:#79A813;font-size:20px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;绩效考评已经开始：</p>
  				<p style="color:#1A648F;font-size:15px;">
  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  					1、本次考评年份是：&nbsp;&nbsp;${whichYear}&nbsp;&nbsp;年<br/>
  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  					2、本次考评月份是：&nbsp;&nbsp;${whichMonth}&nbsp;&nbsp;月<br/>
  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  					3、本次绩效考核的开始时间为：&nbsp;&nbsp;&nbsp;&nbsp;${startDate}<br/>
  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  					4、本次绩效考核的结束时间为：&nbsp;&nbsp;&nbsp;&nbsp;${endDate}<br/>
  				</p>
  				<br/>
			</div>
		</div>
		</s:if>
		
		
		<s:if test="${result=='false'}">
		<div id="div2" style="background:#FFFFFF; width:750px; height:450px;border:1px solid #CCCCCC;">
			<div style="margin : 10px 10px 10px 40px;">
				<h2 style="color:#7ABD54;">时间信息录入</h2>
			</div>
			<div style="margin : 50px 50px 10px 50px;">
				<form action="startScore_input" method="post" >
			<table width="99%">
			<tr>
				<td style="color:#666666;" align="right">
				绩效年份：</td><td><input style="border:1px solid #CCCCCC;" name='whichYear' type="text" /></td>
			</tr>
	
			<tr>
				<td style="color:#666666;" align="right">
				绩效月份：</td><td><input style="border:1px solid #CCCCCC;" name='whichMonth' type="text" /></td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
				<td style="color:#666666;" align="right">绩效考评开始时间：</td><td>
				<input style="border:1px solid #CCCCCC;" class="Wdate" name='startDate' type="text" onClick="WdatePicker();" />
				</td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
				<td style="color:#666666;" align="right">绩效考评结束时间：</td><td>
					<input style="border:1px solid #CCCCCC;" class="Wdate"  name="endDate" type="text" onClick="WdatePicker();" />
				</td>
			</tr>
			<tr><td><br/></td></tr>
			<tr><td><br/></td></tr>
			<tr><td><br/></td></tr>
			<tr>
				<td align="right"><input type="submit" value="提交"></td><td>
					<input type="reset" value="重置">
				</td>
			</tr>
		</table>
		</form>
			</div>
		</div>
		</s:if>
	</body>
</html>
