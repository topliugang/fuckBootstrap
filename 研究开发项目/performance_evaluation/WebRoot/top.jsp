<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="com.chinanetwork.performance.bean.Staff"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>top</title>
		<style type="text/css">
		<!--
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
		}
		.STYLE1 {
			color: #43860c;
			font-size: 12px;
		}
		-->
		</style>
		<script>
			function rc(){
				top.location.href="exit";
			}
		</script>
	</head>
	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			style="table-layout: fixed;">
			<tr>
				<td height="9" style="line-height: 9px; background-image: url(images/main_04.gif)">
				</td>
			</tr>
		<tr>
			<td height="94" background="images/main_09_bigger.gif">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="38" height="94" background="images/main_06_bigger.gif"></td>
							
							<td>
								<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">	
									<tr>
										<td>
											<table width="100%" height="50%" border="0" cellspacing="0" cellpadding="0">	
												<tr>
													<td height="47" width="40%" background="images/jixiaokaoping.png" style="background-repeat:no-repeat;">
													</td>
													<td height="47" width="30%" background="images/gaoxiaoyuanyuxietong.png" style="background-repeat:no-repeat;">
													</td>
													
													<td width="30%" height="23">
														<span class="STYLE1"> <%
															java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
															"yyyy-MM-dd HH:mm:ss");
															java.util.Date currentTime = new java.util.Date();//得到当前系统时间
															String str_date1 = formatter.format(currentTime); //将日期时间格式化
															//String str_date2 = currentTime.toString(); //将Date型日期时间转换成字符串形式
															out.println(str_date1 );
														%> </span>
													</td>
										
													
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<table width="100%" height="50%" border="0" cellspacing="0" cellpadding="0">	
												<tr>
													<td height="23" width="70%" valign="bottom">
														<img src="images/main_12.gif" width="367" height="23" border="0" usemap="#Map">
													</td>

													<td width=30%>
														<span class="STYLE1"> <%
															//获取session 显示姓名
															HttpSession sessions = request.getSession();
															Staff staff = (Staff) session.getAttribute("staff");
															out.print("欢迎您: ");
															out.print(staff.getStaffName());
															out.print("&nbsp;&nbsp;&nbsp;&nbsp;您的职位是: ");
															out.print(staff.getPost().getPostName());
														%> </span>
													</td>	

												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
		</tr>

	<tr>
		<td height="5"
			style="line-height: 5px; background-image: url(images/main_18.gif)">
			<!--table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
						<td width="180" background="images/main_16.gif"
								style="line-height: 5px;">
								&nbsp;
						</td>
						<td>
								&nbsp;
						</td>
				</tr>
		</table-->
	</td>
</tr>
</table>
<map name="Map" id="Map">
<area shape="rect" coords="3,1,49,22" href="
javascript:window.top.frames['mainFrame'].document.location='center.html';
javascript:window.top.frames['topFrame'].document.location='top.jsp';
javascript:window.top.frames['bottomFrame'].document.location='down.html';
" />
<area shape="rect" coords="52,2,95,21" href="javascript:window.top.frames['mainFrame'].history.back();"  />
	<area shape="rect" coords="102,2,144,21" href="javascript:window.top.frames['mainFrame'].history.forward();"  />
		<area shape="rect" coords="150,1,197,22" href="javascript:window.top.frames['mainFrame'].document.location.reload();"  />
			<area shape="rect" coords="210,2,304,20" href="javascript:window.top.frames['mainFrame'].document.location='staff_topasswordmodify';
				javascript:window.top.frames['topFrame'].document.location='top.jsp';
				javascript:window.top.frames['bottomFrame'].document.location='down.html';
				" />
				<!--<area shape="rect" coords="314,1,361,23" href="#" />-->
				<area shape="rect" coords="314,1,361,23" href="#" onclick="rc();"/>
					</map>
				</body>
			</html>