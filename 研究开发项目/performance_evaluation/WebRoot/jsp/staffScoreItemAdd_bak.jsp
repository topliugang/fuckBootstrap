<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门条目添加</title>

<link href="../css/css.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript">

</script>
<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style>

<link href="../css/style.css" rel="stylesheet" type="text/css" />
</head>

<style>
		#div{
			position: absolute; 
			left: 200px; 
			top: 20px;
			margin : 0px 0px 50px 0px;
		}
		#select_1,#select_2,#select_3,#select_4,#select_5,#select_6{
			border:1px solid #CCCCCC;
		}
</style><SCRIPT language=JavaScript>
function sousuo(){
	window.open("gaojisousuo.htm","","depended=0,alwaysRaised=1,width=800,height=510,location=0,menubar=0,resizable=0,scrollbars=0,status=0,toolbar=0");
}
function selectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "delid"){
			obj[i].checked = true;
		}
	}
}

function unselectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "delid"){
			if (obj[i].checked==true) obj[i].checked = false;
			else obj[i].checked = true;
		}
	}
}
</SCRIPT>

<body bgcolor="#f5f5f5">
<div id="div" style="background:#FFFFFF; color:#666666; width:800px; height:700px; border:1px solid #CCCCCC;">
	<div style="margin : 10px 10px 10px 40px;">
		<h2 style="color:#7ABD54;">添加员工条目信息.</h2>
	</div>
	<div style="margin : 10px 10px 10px 80px;">
		<form action="staffScoreItem_add" method="post" >
		<table width="99%" >
		<tr>
			<td align="right">条目名称：</td><td><input style="border:1px solid #CCCCCC;" name='staffScoreItemWillAdd.staffScoreName' type="text" /></td>
		</tr>
	
		<tr>
			<td align="right">权重：</td><td><input style="border:1px solid #CCCCCC;" name='staffScoreItemWillAdd.scoreWeight' type="text" /></td>
		</tr>
		<tr><td align="right">条目描述：</td><td><br/></td></tr>
		<tr>
			<td></td><td>
			<textarea id="select_1" name='staffScoreItemWillAdd.scoreDescription' rows="3" cols="30">请输入描述</textarea></td>
		</tr>
		<tr><td align="right">标准（96~100分）：</td><td><br/></td></tr>
		<tr>
			<td></td><td>
			<textarea id="select_2" name='staffScoreItemWillAdd.scoreStandard1' rows="3" cols="30">请输入描述</textarea></td>
		</tr>
		<tr><td align="right">标准（81~95分）：</td><td><br/></td></tr>
		<tr>
			<td></td><td>
			<textarea id="select_3" name='staffScoreItemWillAdd.scoreStandard2' rows="3" cols="30">请输入描述</textarea></td>
		</tr>
		<tr><td align="right">标准（61~80分）：</td><td><br/></td></tr>
		<tr>
			<td></td><td>
			<textarea id="select_4" name='staffScoreItemWillAdd.scoreStandard3' rows="3" cols="30">请输入描述</textarea></td>
		</tr>
		<tr><td align="right">标准（41~60分）：</td><td><br/></td></tr>
		<tr>
			<td></td><td>
			<textarea id="select_5" name='staffScoreItemWillAdd.scoreStandard4' rows="3" cols="30">请输入描述</textarea></td>
		</tr>
		<tr><td align="right">标准（0~40分）：</td><td><br/></td></tr>
		<tr>
			<td></td><td>
			<textarea id="select_6" name='staffScoreItemWillAdd.scoreStandard5' rows="3" cols="30">请输入描述</textarea></td>
		</tr>
	<tr><td><br/></td><td><br/></td></tr>
	
	<tr>
		<td align="right">
		<input type="submit" value="提交"> </td><td>
		<input type="reset" value="重置"></td>
	</tr>
	</table>
 
</form>
	</div>
</div>
  
</body>
</html>