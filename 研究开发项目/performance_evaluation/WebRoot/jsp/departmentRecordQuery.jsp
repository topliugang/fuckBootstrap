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
								<span class="little_title">部门打分查询</span>
							</td>

							<form action="statisticalQuery_departmentRecordQuery" id="departmentForm">
								<td width="120"
									background="<%=request.getContextPath()%>/jsp/images/tab_05.gif">
									<span class="little_title">选择部门:</span>
								</td>

								<td width="80"
									background="<%=request.getContextPath()%>/jsp/images/tab_05.gif">
										<s:select theme ="simple" list="departments" listValue="departmentName"
											listKey="departmentId" name="departmentIdChosen" id="querySelect" >
										</s:select> 
								</td>
								<td width="120"
									background="<%=request.getContextPath()%>/jsp/images/tab_05.gif">
									<span class="little_title">年份:</span>
								</td>

								<td width="80"
									background="<%=request.getContextPath()%>/jsp/images/tab_05.gif">
										<s:select theme ="simple" list="years" name="startyearChosen" id="querySelect" >
										</s:select> 
								</td>
								<td width="120"
									background="<%=request.getContextPath()%>/jsp/images/tab_05.gif">
									<span class="little_title">月份:</span>
								</td>

								<td width="80"
									background="<%=request.getContextPath()%>/jsp/images/tab_05.gif">
										<s:select theme ="simple" list="months" name="startmonthChosen" id="querySelect" >
										</s:select> 
								</td>
								
								<td>
									<input type="submit" value="查询" src="<%=request.getContextPath()%>/jsp/images/tab_05.gif">
								</td>
							</form>

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
								<table width="99%" border="0" align="center" cellpadding="0"
									cellspacing="1" bgcolor="#c0de98" onmouseover="changeto()"
									onmouseout="changeback()" class="DGTableStyle">
									<tr class="DGHeaderStyle">
										<td width="8%">
												<div>
													条目名称
												</div>
											</td>
											<td width="24%">
												<div>
													条目描述
												</div>
											</td>
											<td width="4%">
												<div>
													分数
												</div>
											</td>
											<td width="12%">
												<div>
													基准值
												</div>
											</td>
											<td width="24%">
												<div>
													计算方法
												</div>
											</td>
											<td width="8%">
												<div>
													打分来源
												</div>
											</td>
											<td width="8%">
												<div>
													类别
												</div>
											</td>
											<td width="4%">
												<div>
													打分
												</div>
											</td>
											<td width="8%">
												<div>
													描述
												</div>
											</td>
									</tr>
									<s:iterator value="departmentScoreRecords" var="record"
										status="st">
										<tr>
											<td>
												<div>
													<s:property value="departmentScoreItem.departmentScoreName"></s:property>
												</div>
											</td>
											<td>
												<div>
													<s:property value="departmentScoreItem.scoreDescription"></s:property>
												</div>
											</td>

											<td>
												<div>
													<s:property value="departmentScoreItem.scoreValue"></s:property>
												</div>
											</td>
											<td>
												<div>
													<s:property value="departmentScoreItem.scoreReference"></s:property>
												</div>
											</td>
											<td>
												<div>
													<s:property
														value="departmentScoreItem.scoreCalculationMethod"></s:property>
												</div>
											</td>
											<td>
												<div>
													<s:property
														value="departmentFrom.departmentName"></s:property>
												</div>
											</td>
											<td>
												<div>
													<s:property
														value="departmentScoreItem.scoreItemCategory.departmentScoreCategoryName"></s:property>
												</div>
											</td>
											<td>
												<div>
													<input type="text" name="departmentScores" value="<s:property value="score" />" class="myscore" disabled="disabled" size="4%"/>
												</div>
											</td>
											<td>
												<div>
													<input type="text" name="departmentScoreComments" value="<s:property value="scoreComment" />" class="mycomm" disabled="disabled" size="8%"/>
												</div>
												<input type="hidden" name="itemsIds" class="myitems" disabled="disabled" value="<s:property value="departmentScoreItem.departmentScoredItemId"/>" />
												<input type="hidden" name="maxScores" value="<s:property value="scoreValue"/>" />
												<input type="hidden" class="modiTimes" name="modifyTimes" value="<s:property value="modifyTimes"/>" />
												<input type="hidden" name="recordId" value="<s:property value="id"/>" />
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
				<td height="29">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="15" height="29">
								<img src="<%=request.getContextPath()%>/jsp/images/tab_20.gif" width="15" height="29" />
							</td>
							<td background="<%=request.getContextPath()%>/jsp/images/tab_21.gif">
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
								<img src="<%=request.getContextPath()%>/jsp/images/tab_22.gif" width="14" height="29" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
<!--		</form>-->
	</body>
</html>
