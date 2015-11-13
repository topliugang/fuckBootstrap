<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<link href="<%=request.getContextPath()%>/jsp/css/zgyx.css" rel="stylesheet" type="text/css" />
		<script src="<%=request.getContextPath()%>/js/jquery-1.4.3.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/js/zgyx.js" type="text/javascript"></script>
		

<script>
var  highlightcolor='#eafcd5';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[0].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[0].style.backgroundColor!=clickcolor)
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=highlightcolor;
}
}

function  changeback(){
if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
return
if  (event.toElement!=source&&cs[0].style.backgroundColor!=clickcolor)
//source.style.backgroundColor=originalcolor
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}

function  clickto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[0].style.backgroundColor!=clickcolor&&source.id!="nc")
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=clickcolor;
}
else
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}

function myClose()
{   
	event.returnValue="确定离开当前页面吗？"; 
}


</script>
	</head>

	<!-- onbeforeunload ="myClose()" -->
	<body>
<!--	<form name="scoreForm" action="score_departmentScore" onsubmit="return checkScore(scoreForm)">-->
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" height="3" style="height: 3px;">
							<tr>
				        		<td width="15" height="30">
				        			<img src="<%=request.getContextPath()%>/jsp/images/tab_03.gif" width="15" height="30" />
				        		</td>
				        		<td width="1101" background="<%=request.getContextPath()%>/jsp/images/tab_05.gif">
				        			<img src="<%=request.getContextPath()%>/jsp/images/311.gif" width="16" height="16" /> 
				        			<span class="STYLE4">任务进度</span>
				        		</td>
				        		<td width="281" background="<%=request.getContextPath()%>/jsp/images/tab_05.gif">
				        			<table border="0" align="right" cellpadding="0" cellspacing="0">
				           				<tr>
				              				<td width="60">&nbsp;</td>
				              				<td width="60">
				              					<table width="90%" border="0" cellpadding="0" cellspacing="0">
				                  					<tr>
				                    					<td class="STYLE1">
				                    						<div align="center">
				                    							<img src="<%=request.getContextPath()%>/jsp/images/001.gif" width="14" height="14" />
				                    						</div>
				                    					</td>
				                    					<td class="STYLE1">
				                    						<div align="center">
				                    							<a href="taskProgress_toInput">录入</a>
				                    						</div>
				                    					</td>
				                  					</tr>
				              					</table>
				              				</td>
				              				<td width="60">&nbsp;
				              				</td>
				             			 </tr>
				       				 </table>
				       			</td>
				        		<td width="14">
				        			<img src="<%=request.getContextPath()%>/jsp/images/tab_07.gif" width="14" height="30" />
				        		</td>
				      		</tr>
					</table>
				</td>
			</tr>




			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="9"
								background="<%=request.getContextPath()%>/jsp/images/tab_12.gif">
								&nbsp;
							</td>
							<td bgcolor="#f3ffe3">
								<table width="100%" border="0" align="center" cellpadding="0"
										cellspacing="1" bgcolor="#c0de98" onmouseover="changeto()"
										onmouseout="changeback()" class="DGTableStyle">
										<tr class="DGHeaderStyle">
											<td width="25%">
												<div>
													任务进度
												</div>
											</td>
											<td width="25%">
												<div>
													年份
												</div>
											</td>
											<td width="25%">
												<div>
													月份
												</div>
											</td>
											<td width="25%">
												<div>
													录入日期
												</div>
											</td>
											
										</tr>
										<s:iterator value="taskProgressessss" var="item" status="st">
											<tr>
												<td >
													<div >
														${item.taskProgress}
													</div>
												</td>
												<td >
													<div >
														${item.whichYear}
													</div>
												</td>
												<td >
													<div >
														${item.whichMonth}
													</div>
												</td>
												<td >
													<div >
														${item.date}
													</div>
												</td>
											</tr>
										</s:iterator>

									</table>
							</td>
							<td width="9"
								background="<%=request.getContextPath()%>/jsp/images/tab_16.gif">
								&nbsp;

							</td>
						</tr>
					</table>
				</td>
			</tr>


			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="9"
								background="<%=request.getContextPath()%>/jsp/images/tab_12.gif">
								&nbsp;
							</td>
							<td bgcolor="#f3ffe3">
								<!-- here-->

								<s:if test="scoringMarker">
									<form action="taskProgress_input">
										<input  name="result" type="hidden" value="${result}"/>
										本月任务进度：
										<input style="width:100px;border:1px solid #8B8989;" name="taskProgress.taskProgress" type="text"  style="width:50px;//宽度 height=20px;//高度"/ value="0"/>
										%
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="submit" type="submit" value="提交" />	
									</form>
								</s:if>


								
								
							</td>
							<td width="9"
								background="<%=request.getContextPath()%>/jsp/images/tab_16.gif">
								&nbsp;

							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="29">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="15" height="29">
								<img src="<%=request.getContextPath()%>/jsp/images/tab_20.gif"
									width="15" height="29" />
							</td>
							<td
								background="<%=request.getContextPath()%>/jsp/images/tab_21.gif">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="25%" height="29" nowrap="nowrap">
										</td>
										<td width="75%" valign="top" class="STYLE1">
										</td>
									</tr>
								</table>
							</td>
							<td width="14">
								<img src="<%=request.getContextPath()%>/jsp/images/tab_22.gif"
									width="14" height="29" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
<!--		</form>-->
	</body>
</html>
