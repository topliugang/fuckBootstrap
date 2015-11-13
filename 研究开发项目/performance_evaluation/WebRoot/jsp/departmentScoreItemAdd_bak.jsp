<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>评分条目添加</title>

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

<SCRIPT language=JavaScript>
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
<style>
		#div{
			position: absolute; 
			left: 200px; 
			top: 20px;
		}
		#select_c,#select_d,#select_m{
			border:1px solid #CCCCCC;
		}
</style>
<body bgcolor="#f5f5f5">
	<div id="div" style="background:#FFFFFF; color:#666666; width:800px; height:580px; border:1px solid #CCCCCC;">
		<div style="margin : 10px 10px 10px 40px;">
			<h2 style="color:#7ABD54;">添加评分条目信息</h2>
		</div>
		
		<div style="margin : 10px 10px 10px 80px;">
			<form action="departmentScoreItem_add" method="post" >

			<table width="99%" >
				<tr>
					<td align="right">条目类别：</td>
					<td>
						<s:select  id="select_c" theme ="simple" list="categories" listValue="departmentScoreCategoryName"
							listKey="departmentScoreCategoryId" name="categoryId">
						</s:select>
					</td>
				</tr>
	
				<tr>
					<td align="right">条目名称：</td>
					<td>
					<input style="border:1px solid #CCCCCC;" name='departmentScoreItemWillAdd.departmentScoreName' type="text" /></td>
				</tr>
				<tr><td align="right">条目描述：</td><td><br/></td></tr>
				<tr>
					<td></td>
					<td>
					<textarea rows="5" cols="40" style="border:1px solid #CCCCCC;" name='departmentScoreItemWillAdd.scoreDescription'>请输入描述</textarea></td>
				</tr>
	
				<tr>
					<td align="right">分值：</td>
					<td>
					<input style="border:1px solid #CCCCCC;" name='departmentScoreItemWillAdd.scoreValue' type="text" /></td>
				</tr>
	
				<tr>
					<td align="right">基准值：</td>
					<td>
					<input style="border:1px solid #CCCCCC;" name='departmentScoreItemWillAdd.scoreReference' type="text" /></td>
				</tr>
				<tr><td align="right">计算方法：</td><td><br/></td></tr>
				<tr>
					<td></td>
					<td>
					<textarea rows="5" cols="40" style="border:1px solid #CCCCCC;" name='departmentScoreItemWillAdd.scoreCalculationMethod'>请输入计算方法</textarea></td>
				</tr>
	
				<tr>
					<td align="right">所属部门：</td>
					<td>
						<s:select  id="select_d" theme ="simple" list="departments" listValue="departmentName"
							listKey="departmentId" name="departmentId">
						</s:select>
					</td>
				</tr>
				<tr><td align="right">数据来源部门：</td><td><br/></td></tr>
				<tr>
					<td></td>
					<td>
						<s:select id="select_m" theme ="simple" list="departments" listValue="departmentName"
							listKey="departmentId" name="departmentsFromId" multiple="true">
						</s:select>
					</td>
				</tr>
				<tr><td><br/></td><td><br/></td></tr>
				<tr>
					<td align="right">
					<input type="submit" value="提交"></td>
					<td>
					<input type="reset" value="重置"></td>
				</tr>
			</table>
 
			</form>
		</div>
	</div>
  
</body>
</html>