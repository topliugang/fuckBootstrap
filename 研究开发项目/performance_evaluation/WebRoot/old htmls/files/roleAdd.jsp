<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>role管理</title>

<link href="../css/css.css" rel="stylesheet" type="text/css" />

<link href="../css/style.css" rel="stylesheet" type="text/css" />
</head>




<body >
  <form action="role_add" method="post" >

	<table width="99%" >
	<tr><td>role信息</td></tr>
	<tr>
		<td>role名称</td>
		<td><input name='roleWillAdd.postName' type="text" /></td>
	</tr>
	
	<tr>
		<td><input type="submit" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" value="重置"></td>
	</tr>
	</table>
 



</form>
</body>
</html>