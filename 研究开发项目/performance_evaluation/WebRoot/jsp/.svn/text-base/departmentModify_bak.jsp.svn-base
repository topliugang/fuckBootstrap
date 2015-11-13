<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>

<link href="../css/css.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript">

</script>
<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
#div{
		position: absolute; 
		left: 200px; 
		top: 45px;
	}
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
<body bgcolor="#f5f5f5">
<div id="div" style="background:#FFFFFF; color:#666666; width:800px; height:400px;border:1px solid #CCCCCC;">
		<div style="margin : 30px 10px 10px 60px;">
			<h2 style="color:#7ABD54;">修改部门信息</h2>
		</div>
		<div id="div1" style="margin : 30px 10px 10px 60px;">
			<form action="department_add" method="post" >
			<input type="hidden" name='departmentWillModify.departmentId' value="${departmentToModify.departmentId}"/>
			<table width="99%" >
			<tr>
				<td align="right">
				部门名称：</td><td><input style="border:1px solid #CCCCCC;" name='departmentWillAdd.departmentName' type="text" value="${departmentToModify.departmentName}"/></td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
				<td align="right">上级部门名称：</td><td>
					<s:select theme ="simple" list="departmentsToList" listValue="departmentName"
						listKey="departmentId" name="departmentWillModify.higherDepartmentId"
						value="%{departmentToModify.higherDepartmentId}">
					</s:select>
				</td>
			</tr>
	<tr><td><br/></td></tr>
			<tr>
				<td align="right">
					<input type="submit" value="提交">
				</td>
				<td>
					<input type="reset" value="重置"/>
				</td>
			</tr>
			</table>
			</form>
		</div>
	</div>
</body>
</html>