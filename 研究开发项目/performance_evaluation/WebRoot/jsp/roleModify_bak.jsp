<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>post modify</title>

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
	#select_a{
		border:1px solid #CCCCCC;
	}
</style>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
</head>

<body bgcolor="#f5f5f5">
	<div id="div" style="background:#FFFFFF; color:#666666; width:800px; height:450px;border:1px solid #CCCCCC;">
		<div style="margin : 10px 10px 10px 40px;">
			<h2 style="color:#7ABD54;">角色信息修改</h2>
		</div>
		<div style="margin : 10px 10px 10px 80px;">
			<form action="role_modify" method="post">
			<table>
		<tr>
			<td align="right">角色编号：</td><td><input style="border:1px solid #CCCCCC;" type="text" name="roleWillModify.roleId" value=${roleToModify.roleId} /></td>
		</tr>
		<tr><td><br/></td><td><br/></td></tr>
		<tr>
			<td align="right">角色名称：</td><td><input style="border:1px solid #CCCCCC;" type="text" name="roleWillModify.roleName" value=${roleToModify.roleName} /></td>
		</tr>
		<tr><td><br/></td><td><br/></td></tr>
		<tr>
			<td align="right">角色描述：</td><td><input style="border:1px solid #CCCCCC;" type="text" name="roleWillModify.roleDescription" value=${roleToModify.roleDescription} /></td>
		</tr>
		<tr><td><br/></td><td><br/></td></tr>
		<tr><td align="right">功能页面：</td><td><br/></td></tr>
		<tr>
			<td align="right"></td><td>
				<s:select id="select_a" theme ="simple" multiple="true" list="funcpages" listValue="name"
					listKey="id" name="funcpages">
				</s:select>
			</td>
		</tr>
		<tr><td><br/></td><td><br/></td></tr>
		<tr>
			<td align="right">
				<input type="submit" value="提交">
			</td><td>
				<input type="reset" value="重置">
			</td>
		</tr>
	</table>

	</form>
		</div>
	</div>
</body>
</html>