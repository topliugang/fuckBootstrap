<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="height:100%;">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<link href="<%=request.getContextPath()%>/jsp/css/zgyx.css" rel="stylesheet" type="text/css" />
		<script src="<%=request.getContextPath()%>/js/jquery-1.4.3.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/js/zgyx.js" type="text/javascript"></script>
		<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
		

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
	<body style="height:100%;">
<!--	<form name="scoreForm" action="score_departmentScore" onsubmit="return checkScore(scoreForm)">-->
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" style="height:100%;">
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
								<span class="little_title">时间信息录入</span>
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
					<table width="100%" border="0" cellspacing="0" cellpadding="0" style="height:100%;">
						<tr>
							<td width="9"
								background="<%=request.getContextPath()%>/jsp/images/tab_12.gif">
								&nbsp;
							</td>
							<td bgcolor="#f3ffe3">
								<!-- here-->
								<s:if test="${result=='true'}">
									<p style="color:#79A813;font-size:20px;">绩效考评已经开始：</p>
									<p style="color:#1A648F;font-size:15px;">
										
										1、本次考评年份是：${whichYear}年<br/>
										2、本次考评月份是：${whichMonth}月<br/>
										3、本次绩效考核的开始时间为：${startDate}<br/>
										4、本次绩效考核的结束时间为：${endDate}<br/>
										

									</p>
									<br/>
								</s:if>
								
								
								<s:if test="${result=='false'}">
									<form action="startScore_input" method="post" >
									<table width="99%">
									<tr>
										<td style="color:#666666;" align="right">
										绩效年份：</td><td><input style="border:1px solid #CCCCCC;" name='whichYear' type="text" /></td>
									</tr>
							
									<tr>
										<td style="color:#666666;" align="right">
										绩效月份：</td><td><input style="border:1px solid #CCCCCC;" name='whichMonth' type="text" /></td>
									</tr>
									<tr><td><br/></td></tr>
									<tr>
										<td style="color:#666666;" align="right">绩效考评开始时间：</td><td>
										<!--input style="border:1px solid #CCCCCC;" class="Wdate" name='startDate' type="text" onClick="WdatePicker();" /-->
										<input autocomplete="off" style="border:1px solid #CCCCCC;width:300px;" id="startDate" class="Wdate"  name="startDate" type="text" onclick="WdatePicker();" />
										</td>
									</tr>
									<tr><td><br/></td></tr>
									<tr>
										<td style="color:#666666;" align="right">绩效考评结束时间：</td><td>
											<!--input style="border:1px solid #CCCCCC;" class="Wdate"  name="endDate" type="text" onClick="WdatePicker();" /-->
											<input autocomplete="off" style="border:1px solid #CCCCCC;width:300px;" id="endDate" class="Wdate"  name="endDate" type="text" onclick="WdatePicker();" />
										</td>
									</tr>
									<tr><td><br/></td></tr>
									<tr><td><br/></td></tr>
									<tr><td><br/></td></tr>
									<tr>
										<td align="right"><input type="submit" value="提交"></td><td>
											<input type="reset" value="重置">
										</td>
									</tr>
								</table>
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
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" style="height:100%;">
						<tr>
							<td width="9" background="tab_12.gif">
								&nbsp;
							</td>
							<td bgcolor="#f3ffe3">
							</td>
							<td width="9" background="tab_16.gif">
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
