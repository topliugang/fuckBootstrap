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


function checkScore(form)
{
	var maxScores = new Array()
	var departmentScores = new Array()

	for(i=0;i<form.length;i++)
	{ 
		if(form.elements[i].id=="departmentToId")//放入departmentToId
		{
			var select = document.getElementById("querySelect");
			var selectedId = select.value; 
			form.elements[i].value = selectedId;			
			continue;
		}
		
		if(form.elements[i].name == "departmentScoreComments")
		{
			continue;
		}
	
		if(form.elements[i].value == "")
		{       
			alert(form.elements[i].value+"很抱歉,打分项目不能为空!");  
			return false;  
		}
	//取得input的内容 , 放到3个数组中
		if(form.elements[i].name == "maxScores")
			maxScores.push(form.elements[i].value);
		else if(form.elements[i].name == "departmentScores")
		{
			departmentScores.push(parseInt(form.elements[i].value, 10));
			if(isNaN(parseInt(form.elements[i].value, 10)))
			{
				alert(form.elements[i].value+"很抱歉,分数必须是数字!");  
				return false;  
			}
		}
	}
	for(i=0;i<departmentScores.length;i++)
	{
		if(departmentScores[i]<=0 || departmentScores[i]>maxScores[i])
		{
			alert(departmentScores[i]+"很抱歉,所打分数超出范围!");  
			return false;  
		}
	}
	//设置departmentToId
	form.submit();
	alert("打分成功！");
	return true;
}

function query()
{
    var select=document.getElementById('querySelect');
    var val=select.value;
    var form = document.getElementById("departmentForm");
    form.action="score_listDepartmentNoScoredItem?departmentIdChosen="+val;
    form.submit();
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
								<img src="<%=request.getContextPath()%>/jsp/images/tab_03.gif"
									width="15" height="30" />
							</td>
							<td width="241"
								background="<%=request.getContextPath()%>/jsp/images/tab_05.gif">
								<img src="<%=request.getContextPath()%>/jsp/images/311.gif"
									width="16" height="16" />
								<span class="little_title">部门互评</span>
							</td>

							<td width="999"
								background="<%=request.getContextPath()%>/jsp/images/tab_05.gif">
							</td>

							<td width="281"
								background="<%=request.getContextPath()%>/jsp/images/tab_05.gif">
								<table border="0" align="right" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60">
											&nbsp;
										</td>
										<td width="60">
											&nbsp;

										</td>
									</tr>
								</table>
							</td>
							<td width="14">
								<img src="<%=request.getContextPath()%>/jsp/images/tab_07.gif"
									width="14" height="30" />
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
								<form action="${goBackOrder}" method="post">
									<table width="99%" >
										<tr>
											<td align="right" valign="top" style="font-weight:bold;color:#33CC99;">发送者：</td>
											<td align="left" valign="top">
												<%--<table id="message-table">
									
													<tr>
														<!-- 源数据选择开始 -->
														<td id="t-1-1" valign="top">
											
															<div id="first_div">
															<div class="menu" onclick="toggleMenu('mul-div')">
																<img class="menu-img" src="images/department.png" /><span>选择接收部门</span>
															</div>
															<div id="mul-div">
																<select id="mul-department" multiple="multiple" ondblclick="moveTodep();">
																<s:iterator value="departments" var="department">
																	<option value="${departmentId}">
																		<s:property value="departmentName" />
																	</option>
																</s:iterator>
																</select>
															</div>
															<div class="menu" onclick="toggleMenu('staff-div')">
																<img class="menu-img" src="images/people.png" /><span>根据部门选择接收人</span>
															</div>
															<div id="staff-div">
																<div id="sel-div">
																	<select id="sel" class="sel" onchange="change(this.value);">
																	<s:iterator value="departments" var="department">
																		<option value="${departmentId}">
																			<s:property value="departmentName" />
																		</option>
																	</s:iterator>
																	</select>
																</div>
																<div id="mul-staff-div">
																	<select id="mul-staff" class="sel" multiple="multiple" ondblclick="moveTostaff();">
																	</select>
																</div>
															</div>
															</div>
											
														</td>
														<!-- 源数据选择结束 -->
											
														<!-- 功能按钮群开始 -->
														<td>
															<p>
																<input type="button" value="--&gt;" style="width:120px" onclick="moveToList();"/>
															</p>
															<p>
																<input type="button" value="--&gt;&gt;" style="width:120px" onclick="moveAllOpt();"/>
															</p>
															<p>
																<input type="button" value="&lt;--" style="width:120px"  onclick="backToList();"/>
															</p>
															<p>
																<input type="button" value="&lt;&lt;--" style="width:120px" onclick="backAllOpt();"/>
															</p>
														</td>
														<!-- 功能按钮群结束 -->
											
														<!-- 目标数据选择开始 -->
														<td id="t-1-3">
															<div id="second-div">
																<div id="obj-department-div" class="obj-div">
																	<select id="mul-obj-department" class="mul-obj" multiple="multiple" name="departmentidstoreceiveToSend" ondblclick="backToDep();" >
																	</select>
																</div>
															<div id="obj-staff-div" class="obj-div">
																<select id="mul-obj-staff" class="mul-obj" multiple="multiple" name="staffidstoreceiveToSend" ondblclick="backTostaff();">
																</select>
															</div>
															</div>
														</td>
														<!-- 目标数据选择结束 -->
										
													</tr>
									
												</table>
											--%>
											<input type="text" style="border:1px solid #CCCCCC;width:60%;" 
											value="${messageToRead.sender.department.departmentName}${messageToRead.sender.staffName}"/>
											</td>
										</tr>
										<tr><td><br/></td><td><br/></td></tr>
										<tr>
											<td align="right" valign="top" style="font-weight:bold;color:#33CC99;">发送时间：</td>
											<td align="left" valign="top"><input type="text" style="border:1px solid #CCCCCC;width:60%;" value="${messageToRead.messageDate}"/></td>
										</tr>
										<tr><td><br/></td><td><br/></td></tr>
										<tr><td><br/></td><td><br/></td></tr>
										<tr><td><br/></td><td><br/></td></tr>
										<tr><td><br/></td><td><br/></td></tr>
										<tr>
											<td align="right" valign="top" style="font-weight:bold;color:#33CC99;">消息标题：</td>
											<td align="left" valign="top">
												<input type="text" style="border:1px solid #CCCCCC;width:610px" value="${messageToRead.title}"/>
											</td>
										</tr>
										<tr><td><br/></td><td><br/></td></tr>
										<s:if test="uploadFileList!=null&&uploadFileList.size()>0">
											<tr>
											<td align="right" valign="top" style="font-weight:bold;color:#33CC99;">消息附件：</td>
											<td id="uploadColumn" align="left" valign="top">
												<%--<input type="file" name="uploads" onchange="showoneupload();"/><br/>
											--%>
												<table style="width:90%;border:1px #CCCCCC solid;text-align:center;">
													<tr style="border:1px #CCCCCC solid;color:#4169e1;">
														<td style="border:1px #CCCCCC solid">附件ID</td>
														<td style="border:1px #CCCCCC solid">附件名称</td>
														<td style="border:1px #CCCCCC solid">附件大小</td>
														<td style="border:1px #CCCCCC solid">操作</td>
													</tr>
													<s:iterator value="uploadFileList">
													<tr style="border:1px #CCCCCC solid;">
														<td style="border:1px #CCCCCC solid">&nbsp;&nbsp;${fileId}&nbsp;&nbsp;</td>
														<td style="border:1px #CCCCCC solid">${fileName}</td>
														<td style="border:1px #CCCCCC solid">&nbsp;<fmt:formatNumber value="${fileSize/(1024*1024)}" pattern="0.000"></fmt:formatNumber>&nbsp;</td>
														<td style="border:1px #CCCCCC solid">&nbsp;<a href="message_downLoad?fileId=${fileId}&fileName=${fileName}" style="text-decoration: none;">下载</a>&nbsp;</td>
													</tr>
													</s:iterator>
												</table>
											</td>
										</tr>
										<tr><td><br/></td><td><br/></td></tr>
										</s:if>
										<tr>
											<td align="right" valign="top" style="font-weight:bold;color:#33CC99;">消息内容：</td>
											<td align="left" valign="top">	
											<div style="width:720px;heigth:400px;border:1px solid #CCCCCC;">
												${messageToRead.content}
											</div>
											</td>
										</tr>
										<tr><td><br/></td><td><br/></td></tr>
										<tr>
											<td align="right" valign="top">
												<input type="submit" value="返回" />
											</td>
											<td align="left" valign="top">
											</td>
										</tr>
										
									</table>
								</form>
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
