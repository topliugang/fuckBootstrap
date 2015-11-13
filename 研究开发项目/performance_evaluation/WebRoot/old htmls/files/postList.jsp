<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>职位管理</title>

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
		<td><a href="post_toAdd.action">添加职位</a></td>
	</tr>
	
	<tr>
		<td>职位信息</td>
	</tr>
	
	 <tr>
		<td width="5%" align="center" bgcolor="#EEEEEE">序列</td>
	    <td width="8%" height="20" align="center" bgcolor="#EEEEEE">职位编号</td>
	    <td width="10%" align="center" bgcolor="#EEEEEE">职位名称</td>
	    <td width="16%" align="center" bgcolor="#EEEEEE">详情</td>
	</tr>
		<s:iterator value="postsToList" var="v_post" status="st">
		<tr>
			<td bgcolor="#FFFFFF"><div align="center">${st.index+1}</div></td>
	    	<td height="20" bgcolor="#FFFFFF"><div align="center" >${v_post.postId}</div></td>
	    	<td bgcolor="#FFFFFF"><div align="center">${v_post.postName}</div></td>
	    	<td bgcolor="#FFFFFF"><div align="center"><a href="post_toDetail?postIdToDetail=${v_post.postId}">查看</a>
	    	&nbsp;|&nbsp;<a href="post_toModify?postIdToModify=${v_post.postId}">编辑</a>
	   		&nbsp;|&nbsp;<a href="post_delete?postIdToDelete=${v_post.postId}">删除</a></div></td>
	 
		</tr>
	   </s:iterator>
</table>

</body>
</html>