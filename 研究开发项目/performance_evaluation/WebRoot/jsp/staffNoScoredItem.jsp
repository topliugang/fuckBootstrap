<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>无标题文档</title>
		<link href="<%=request.getContextPath()%>/jsp/css/zgyx.css" rel="stylesheet" type="text/css">
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

function checkScore(form)
{
	var maxScores = new Array()
	var staffScores = new Array()

	for(i=0;i<form.length;i++)
	{ 
		if(form.elements[i].id=="staffToId")//放入staffToId
		{
			var select = document.getElementById("querySelect");
			var selectedId = select.value; 
			form.elements[i].value = selectedId;			
			continue;
		}
	
	
		if(form.elements[i].value == "")
		{       
			alert(form.elements[i].name+"很抱歉,打分项目不能为空!");  
			return false;  
		}
	//取得input的内容 , 放到3个数组中
		if(form.elements[i].name == "maxScores")
	//		maxScores.push(form.elements[i].value);
			maxScores.push(100);
		else if(form.elements[i].name == "staffScores")
		{
			staffScores.push(parseInt(form.elements[i].value, 10));
			if(isNaN(parseInt(form.elements[i].value, 10)))
			{
				alert(form.elements[i].value+"很抱歉,分数必须是数字!");  
				return false;  
			}
		}
	}
	for(i=0;i<staffScores.length;i++)
	{
		if(staffScores[i]<=0 || staffScores[i]>maxScores[i])
		{
			alert(staffScores[i]+"很抱歉,所打分数超出范围!");  
			return false;  
		}
	}
	form.submit();
	alert("打分成功！");
	return true;
}

function query()
{
	var select=document.getElementById('querySelect');
	var val=select.value;
	var form = document.getElementById("staffForm");
	form.action="score_listStaffNoScoredItem?staffIdChosen="+val;
	form.submit();
}

</script>
	</head>
	<body>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="15" height="30">
								<img src="<%=request.getContextPath()%>/jsp/images/tab_03.gif"
									width="15" height="30" />
							</td>
							<td width="241"
								background="<%=request.getContextPath()%>/jsp/images/tab_05.gif">
								<img src="<%=request.getContextPath()%>/jsp/images/311.gif"
									width="16" height="16" />
								<span class="little_title">员工打分</span>
							</td>
							<td width="100"
								background="<%=request.getContextPath()%>/jsp/images/tab_05.gif">
								<span class="little_title">选择员工:</span>
							</td>

							<td width="899"
								background="<%=request.getContextPath()%>/jsp/images/tab_05.gif">
									<form action="score_listStaffNoScoredItem" id="staffForm">
									<!-- 
									<select name="staffNameChosen">
										<s:iterator value="staffsNoScored">
											<option>
												<s:property value="staffId" />
											</option>
										</s:iterator>
									</select>
									 -->
									<s:select theme ="simple" list="staffsNoScored" listValue="staffName"
										listKey="staffId" name="staffIdChosen" id="querySelect" onChange="query()">
									</s:select>
								</form>
							</td>


							<td width="281"
								background="<%=request.getContextPath()%>/jsp/images/tab_05.gif">
								<table border="0" align="right" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60">
											&nbsp;

										</td>
<!--										<td width="60">-->
<!--											<table width="90%" border="0" cellpadding="0" cellspacing="0">-->
<!--												<tr>-->
<!--													<td class="STYLE1">-->
<!--														<div align="center">-->
<!--															<img-->
<!--																src="<%=request.getContextPath()%>/jsp/images/001.gif"-->
<!--																width="14" height="14" />-->
<!--														</div>-->
<!--													</td>-->
<!--													<td class="STYLE1">-->
<!--														<div align="center">-->
<!--															<a href="item.departmentScoredItem_toAdd.action">新增</a>-->
<!--														</div>-->
<!--													</td>-->
<!--												</tr>-->
<!--											</table>-->
<!--										</td>-->
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
					<form name ="scoreForm" action="score_staffScore">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="9"
								background="<%=request.getContextPath()%>/jsp/images/tab_12.gif">
								&nbsp;

							</td>
							<td bgcolor="#f3ffe3">
								<table width="99%" border="0" align="center" cellpadding="0"
									cellspacing="1" bgcolor="#c0de98" onmouseover="changeto()"
									onmouseout="changeback()" class="DGTableStyle">
									<tr class="DGHeaderStyle">
										<td width="7%">
											<div>
												条目名称
											</div>
										</td>
										<td width="10%">
											<div>
												条目描述
											</div>
										</td>
										<td width="4%">
											<div>
												权重
											</div>
										</td>
										<td width="13%">
											<div>
												96~100分
											</div>
										</td>
										<td width="13%">
											<div>
												81~95分
											</div>
										</td>
										<td width="13%">
											<div>
												61~80分
											</div>
										</td>
										<td width="13%">
											<div>
												41~60分
											</div>
										</td>
										<td width="13%">
											<div>
												0~40分
											</div>
										</td>
										
										<td width="14%">
											<div>
												打分（满分100分）
											</div>
										</td>
									</tr>
									<s:iterator value="staffNoScoredItems" var="item" status="st">
										<tr>
											<td>
												<div>
													<s:property value="staffScoreName"></s:property>
												</div>
											</td>
											<td>
												<div align="center" class="STYLE2 STYLE1">
													<s:property value="scoreDescription"></s:property>
												</div>
											</td>
											<td>
												<div>
													<s:property value="scoreWeight"></s:property>%
												</div>
											</td>

											<td>
												<div>
													<s:property value="scoreStandard1"></s:property>
												</div>
											</td>
											<td>
												<div>
													<s:property value="scoreStandard2"></s:property>
												</div>
											</td>
											<td>
												<div>
													<s:property value="scoreStandard3"></s:property>
												</div>
											</td>
											<td>
												<div>
													<s:property value="scoreStandard4"></s:property>
												</div>
											</td>
											<td>
												<div>
													<s:property value="scoreStandard5"></s:property>
												</div>
											</td>
											
											<td>
												<input type="hidden" name="itemsIds"
													value="<s:property value="staffScoreItemId" />" />
												<input type="hidden" name="maxScores"
													value="<s:property value="scoreWeight" />" />
												<input type="text" name="staffScores"  size="4%"/>
											</td>
										</tr>
									</s:iterator>
									<tr>
										<td height="21" colspan="10" bgcolor="#FFFFFF" class="STYLE2">
											<div align="right">
											<input type="hidden" name="staffIdChosen" id="staffToId" />
								<!-- 		<input type="submit" 　value="打分">     -->
											<s:if test="staffNoScoredItems!=null||staffNoScoredItems.size()!=0">
												<input type="button" value="提交打分" onclick="checkScore(scoreForm)" />
											</s:if>
											</div>
										</td>
									</tr>
								</table>
							</td>
							<td width="9"
								background="<%=request.getContextPath()%>/jsp/images/tab_16.gif">
								&nbsp;

							</td>
						</tr>
					</table>
					</form>
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
										<!-- <span class="STYLE1">共120条纪录，当前第1/10页，每页10条纪录</span> -->
										</td>
										<td width="75%" valign="top" class="STYLE1">
										<!-- <div align="right">
												<table width="352" height="20" border="0" cellpadding="0"
													cellspacing="0">
													<tr>
														<td width="62" height="22" valign="middle">
															<div align="right">
																<img
																	src="<%=request.getContextPath()%>/jsp/images/first.gif"
																	width="37" height="15" />
															</div>
														</td>
														<td width="50" height="22" valign="middle">
															<div align="right">
																<img
																	src="<%=request.getContextPath()%>/jsp/images/back.gif"
																	width="43" height="15" />
															</div>
														</td>
														<td width="54" height="22" valign="middle">
															<div align="right">
																<img
																	src="<%=request.getContextPath()%>/jsp/images/next.gif"
																	width="43" height="15" />
															</div>
														</td>
														<td width="49" height="22" valign="middle">
															<div align="right">
																<img
																	src="<%=request.getContextPath()%>/jsp/images/last.gif"
																	width="37" height="15" />
															</div>
														</td>
														<td width="59" height="22" valign="middle">
															<div align="right">
																转到第
															</div>
														</td>
														<td width="25" height="22" valign="middle">
															<span class="STYLE7"> <input name="textfield"
																	type="text" class="STYLE1"
																	style="height: 10px; width: 25px;" size="5" /> </span>
														</td>
														<td width="23" height="22" valign="middle">
															页
														</td>
														<td width="30" height="22" valign="middle">
															<img
																src="<%=request.getContextPath()%>/jsp/images/go.gif"
																width="37" height="15" />
														</td>
													</tr>
												</table>
											</div> -->
											
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
	</body>
</html>
