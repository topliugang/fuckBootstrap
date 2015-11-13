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
</style>
<link href="../css/style.css" rel="stylesheet" type="text/css" />

<style>
	#div2{
			position: absolute; 
			left: 170px; 
			top: 110px;
		}
		#div3{
			position: absolute;
			left: 560px; 
			top: 150px;
		}
</style>

</head>

<body>
<div>
	<img src="images/modifypost.png" />
</div>
<div id="div2">
	<img src="images/modifypostinfo.png" />
</div>
<div id="div3" style="color:#20769E;font-weight:bold;">
	<form action="post_modify" method="post">
<table>
<tr>
	<td>职位ID：</td>
	<td><input style="width:100px;border:1px solid #8B8989;" type="text" name="postWillModify.postId" value=${postToModify.postId} /></td>
</tr>
<tr>
	<td>职位名称：</td>
	<td><input style="width:100px;border:1px solid #8B8989;" type="text" name="postWillModify.postName" value=${postToModify.postName} /></td>
</tr>
<tr><td><br/></td></tr>
<tr align="right">
	<td>
		<input type="submit" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" value="重置">
	</td>
</tr>
</table>

</form>
</div>


</body>
</html>