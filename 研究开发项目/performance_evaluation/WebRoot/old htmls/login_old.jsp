<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>登陆</title>
		<SCRIPT language=javascript src=""></SCRIPT>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

		<link href="css/css.css" rel="stylesheet" type="text/css" />
	</head>

<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
	    	<td height="147" background="images/top02.gif"><img src="images/top03.gif" width="776" height="147" /></td>
	  	</tr>
	</table><!-- tupia -->
	<table width="562" border="0" align="center" cellpadding="0" cellspacing="0" class="right-table03">
	  <tr>
	    <td width="221"><table width="95%" border="0" cellpadding="0" cellspacing="0" class="login-text01">
	      
	      <tr>
	        <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="login-text01">
	          <tr>
	            <td align="center"><img src="images/ico13.gif" width="107" height="97" /></td>
	          </tr>
	          <tr>
	            <td height="40" align="center">&nbsp;</td>
	          </tr>
	          
	        </table></td>
	        <td><img src="images/line01.gif" width="5" height="292" /></td>
	      </tr>
	    </table></td>
	    <td>
	    <form name=fgrLogon action="login" method=post>
		    <table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td width="31%" height="35" class="login-text02">工号 <br /></td>
		        <td width="69%"><input name="username" type="text" size="30" /> </td>
		      </tr>
		      <tr>
		        <td height="35" class="login-text02">密码<br /></td>
		        <td><input name="password" type="password" size="30" /></td>
		      </tr>
		      <tr>
		        <td height="35" class="login-text02">验证码<br /></td>
		        <td><img src="images/pic05.gif" width="109" height="40" /> <a href="#" class="login-text03">看不清点击</a></td>
		      </tr>
		      <tr>
		        <td height="35" class="login-text02">验证码</td>
		        <td><input name="textfield3" type="text" size="30" /></td>
		      </tr>
		      <tr>
		      	<font color="red">${error}</font>
		 <!-- <td height="35" class="login-text02"></td> -->  	
		      </tr>
		      <tr>
		        <td height="35">&nbsp;</td>
		        <td><input name="Submit2" type="submit" class="right-button01" value="登录"  />
		          <input name="Submit232" type="submit" class="right-button02" value="重置" /></td>
		      </tr>
		    </table>
	    </form>
	    </td>
	  </tr>
	</table>
</body>
</html>