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

<script type="text/javascript"><!--
window.onload=function showDialog(){
	var flag="${mark}";
	var markInfo="${markInfo}";
	if(flag=='false'){
		alert(markInfo);
	}
}
</script>
</head>




<body >
  <form action="keyWork_addDepartmentKeyWork" method="post" >
<h2>添加重点工作信息</h2>
<br/>
	<table width="99%" >
	<tr>
		<td>编号</td>
		<td><input name='keyWorkId' type="text" value="${keyWorkId}" readonly/></td>
	</tr>
	
	<tr>
		<td>申请部门</td>
		<td><input name='departmentName' type="text" value="${departmentName}" readonly/></td>
	</tr>
	
	<tr>
		<td>工作内容</td>
		<td><textarea name="keyWorkContent" cols="20" rows="5">${keyWorkContent}</textarea></td>
	</tr>
	<tr><td><br/></td></tr>
	<tr>
		<td>开始年份：
			<select name="startYear">
				<option value="2014">2014</option>
				<option value="2015">2015</option>
				<option value="2016">2016</option>
				<option value="2017">2017</option>
				<option value="2018">2018</option>
				<option value="2019">2019</option>
				<option value="2020">2020</option>
			</select>
		</td>
		<td>开始月份：
			<select name="startMonth">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
			</select>
		</td>
	</tr>
	<tr><td><br/></td></tr>
	<tr>
		<td>结束年份：
			<select name="endYear">
				<option value="2014">2014</option>
				<option value="2015">2015</option>
				<option value="2016">2016</option>
				<option value="2017">2017</option>
				<option value="2018">2018</option>
				<option value="2019">2019</option>
				<option value="2020">2020</option>
			</select>
		</td>
		<td>结束月份：
			<select name="endMonth">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
			</select>
		</td>
	</tr>
	<tr><td><br/></td></tr>
	<tr>
		<td><input type="submit" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" value="重置"></td>
	</tr>
	</table>
 



</form>
</body>
</html>