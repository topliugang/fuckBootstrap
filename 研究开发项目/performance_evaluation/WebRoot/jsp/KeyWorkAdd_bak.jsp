<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重点工作管理</title>

<link href="../css/css.css" rel="stylesheet" type="text/css" />

<link href="../css/style.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
window.onload=function showDialog(){
	var flag="${mark}";
	var markInfo="${markInfo}";
	if(flag=='false'){
		alert(markInfo);
	}
}
</script>
<style>
	#div{
		position: absolute; 
		left: 200px; 
		top: 45px;
	}
</style>
</head>




<body bgcolor="#f5f5f5">
<br />
<div id="div" style="background:#FFFFFF; color:#666666; width:800px; height:450px;border:1px solid #CCCCCC;">
	<br />
	<div style="margin : 10px 10px 10px 40px;">
		<h2 style="color:#7ABD54;">添加重点工作信息</h2>
	</div>
	<div style="margin : 10px 10px 10px 80px;">
		<form action="keyWork_addDepartmentKeyWork" method="post" >
		<table width="99%" >
			<tr>
				<td align="right">编号：</td><td><input style="border:1px solid #CCCCCC;" name='keyWorkId' type="text" value="${keyWorkId}" readonly/></td>
			</tr>
	
			<tr>
				<td align="right">申请部门：</td><td><input style="border:1px solid #CCCCCC;" name='departmentName' type="text" value="${departmentName}" readonly/></td>
			</tr>
			<tr><td align="right">工作内容：</td><td><br/></td></tr>
			<tr>
				<td></td><td>
						<textarea style="border:1px solid #CCCCCC;" name="keyWorkContent" cols="20" rows="5">${keyWorkContent}</textarea></td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
				<td align="right">开始时间：</td><td>
					<input style="border:1px solid #CCCCCC;" class="Wdate"  name="startTime" type="text" onClick="WdatePicker();" />
				</td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
				<td align="right">结束时间：</td><td>
					<input style="border:1px solid #CCCCCC;" class="Wdate"  name="endTime" type="text" onClick="WdatePicker();" />
				</td>
			</tr>
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

</body>
</html>