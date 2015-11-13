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
</style>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

<!-- main objs -->
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td><a href="role_toAdd.action">添加角色</a></td>
	</tr>
	
	<tr>
		<td>角色信息</td>
	</tr>
	
	 <tr>
		<td width="5%" align="center" bgcolor="#EEEEEE">序列</td>
	    <td width="8%" height="20" align="center" bgcolor="#EEEEEE">角色编号</td>
	    <td width="10%" align="center" bgcolor="#EEEEEE">角色名称</td>
	    <td width="16%" align="center" bgcolor="#EEEEEE">详情</td>
	</tr>
		<s:iterator value="rolesToList" var="v_role" status="st">
		<tr>
			<td bgcolor="#FFFFFF"><div align="center">${st.index+1}</div></td>
	    	<td height="20" bgcolor="#FFFFFF"><div align="center" >${v_role.roleId}</div></td>
	    	<td bgcolor="#FFFFFF"><div align="center">${v_role.roleName}</div></td>
	    	<td bgcolor="#FFFFFF"><div align="center"><a href="role_toDetail?roleIdToDetail=${v_role.roleId}">查看</a>
	    	&nbsp;|&nbsp;<a href="role_toModify?roleIdToModify=${v_role.roleId}">编辑</a>
	   		&nbsp;|&nbsp;<a href="role_delete?roleIdToDelete=${v_role.roleId}">删除</a></div></td>
	 
		</tr>
	   </s:iterator>
</table>

</body>
</html>