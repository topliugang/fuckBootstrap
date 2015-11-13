<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>添加员工信息</title>

		<link href="../css/css.css" rel="stylesheet" type="text/css" />

		<link href="../css/style.css" rel="stylesheet" type="text/css" />
		<style>
			#div{
				position: absolute; 
				left: 200px; 
				top: 45px;
				}
			#select_d,#select_p,#select_r{
				border:1px solid #CCCCCC;
			}
		</style>
	</head>

	<body bgcolor="#f5f5f5">
		<div id="div" style="background:#FFFFFF; color:#666666; width:800px; height:450px;border:1px solid #CCCCCC;">
			<div style="margin : 10px 10px 10px 40px;">
				<h2 style="color:#7ABD54;">添加员工信息</h2>
			</div>
			<div style="margin : 10px 10px 10px 80px;">
				<form action="staff_add" method="post" >

				<table width="99%" >
					<tr>
						<td align="right">姓名：</td><td><input style="border:1px solid #CCCCCC;" name='staffWillAdd.staffName' type="text" /></td>
					</tr>
					<tr>
						<td align="right">工号：</td><td><input style="border:1px solid #CCCCCC;" name='staffWillAdd.staffId' type="text" /></td>
					</tr>
					<tr>
						<td align="right">性别：</td><td><input style="border:1px solid #CCCCCC;" name='staffWillAdd.staffSex' type="text" /></td>
					</tr>
					<tr>
						<td align="right">密码：</td><td><input style="border:1px solid #CCCCCC;" name='staffWillAdd.password' type="text" /></td>
					</tr>
					<tr><td><br/></td></tr>
					<tr>
						<td align="right">部门：</td><td>
							<s:select id="select_d" theme ="simple" list="departments" listValue="departmentName"
								listKey="departmentId" name="departmentIdChosen">
							</s:select>
						</td>
					</tr>
					<tr>
						<td align="right">职位：</td><td>
							<s:select id="select_p" theme ="simple" list="posts" listValue="postName"
								listKey="postId" name="postIdChosen">
							</s:select>
						</td>
					</tr>
					<tr>
						<td align="right">角色：</td><td>
							<s:select id="select_r" theme ="simple" list="roles" listValue="roleName"
								listKey="roleId" name="roleIdChosen">
							</s:select>
						</td>
					</tr>
					<tr><td><br/></td></tr>
					<tr>
						<td align="right">
							<input type="submit" value="提交"></td><td>
							<input type="reset" value="重置"></td>
					</tr>
			</table>
			</form>
			</div>
		</div>
	</body>
</html>