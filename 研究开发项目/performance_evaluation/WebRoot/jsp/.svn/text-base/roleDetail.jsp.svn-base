<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>role详细信息</title>

		<link href="../css/css.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<table>
			<tr>
				<td>
					role信息
				</td>
			</tr>
			<tr>
				<td>
					role编号
				</td>
				<td>
					${roleToDetail.roleId}
				</td>
			</tr>
			<tr>
				<td>
					role名称
				</td>
				<td>
					${roleToDetail.roleName}
				</td>
			</tr>
			<tr>
				<td>
					描述
				</td>
				<td>
					${roleToDetail.roleDescription}
				</td>
			</tr>
			
			<s:select theme ="simple" multiple="true" list="funcpages" listValue="name"
				listKey="id" name="funcpages">
			</s:select> 
		</table>

	</body>

</html>