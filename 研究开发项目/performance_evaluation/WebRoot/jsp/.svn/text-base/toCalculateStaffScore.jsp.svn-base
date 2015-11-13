<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<link href="<%=request.getContextPath()%>/jsp/css/zgyx.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			#mainpage_div{
			border:1px solid #CCCCCC;
			width:630px;
			height:520px;
			padding:10px 10px 10px 10px;
			margin-top:20px;
		}
		a{
			text-decoration: none;
		}
		a:link {color:#80AE12;}
		a:visited {color: #80AE12;}
		a:hover {color: #80AE12;text-decoration: none;}
		#check{
			padding:5px 5px 5px 5px;
			color:#80AE12;
			border:1.5px solid #175D87;
			width:78px;
			height:14px;
			font-weight:bold;
			float:left
		}
		#calculate{
			padding:5px 5px 5px 5px;
			color:#80AE12;
			border:1.5px solid #175D87;
			width:78px;
			height:14px;
			font-weight:bold;
			float:left;
		}
		#calculateNo{
			padding:5px 5px 5px 5px;
			color:#999999;
			border:1.5px solid #999999;
			width:78px;
			height:14px;
			font-weight:bold;
			float:left;
		}
		#info{
			border:1.5px solid #175D87;
			overflow:auto;
			width:500px;
			height:400px;
		}
		#mainpage_tab{
			width:100%;
			height:100%;
		}
		.f{
			color:#000080;
			width:80px;
			height:30px;
			float:left;
		}
		</style>
		<script src="<%=request.getContextPath()%>/js/jquery-1.4.3.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/js/zgyx.js" type="text/javascript"></script>
<script>
//
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

function checkProgress(){
	document.getElementById("info"). innerHTML ='正在检测，请等待... ...';
	window.location.href='staffScore_confirmAccomplishStaffScore';
}
function calStafScore(){
	document.getElementById("info"). innerHTML ='正在计算，请等待... ...';
	window.location.href='staffScore_calculateStaffScore';
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
							<td bgcolor="#f3ffe3" align="center">
								<!-- here-->
								<div id="mainpage_div">
			<table id="mainpage_tab">
				<tr>
					<td style="color:#175D87;font-weight:bold;" align="right" valign="top">
						功能选择：
					</td>
					<td>
						<!--<div style="width:40px;height:30px;float:left">-->
						<div class="f"></div>
						<div id="check" >
						<a href="javascript:checkProgress();" style=" text-decoration none;">检测评分过程</a></div>
						<!--<div style="width:40px;height:30px;float:left">-->
						<div class="f"></div>
						<div class="f"></div>
						<s:if test="scoringMarker">
							<div id="calculate"><a href="javascript:calStafScore();" style=" text-decoration none;">计算员工得分</a></div>
						</s:if>
						<s:else>
							<div id="calculateNo">计算员工得分</div>
						</s:else>
					</td>
				</tr>
				<tr><td><br/></td><td><br/></td></tr>
				<tr>
					<td style="color:#175D87;font-weight:bold;" align="right" valign="top">
						提示信息：
					</td>
					<td style="color:#175D87;font-weight:bold;" align="center" valign="top">
						<div id="info">
							<s:if test="scoringMarker">
								<span>评分过程已经结束，可以计算员工得分了</span>
							</s:if>
							 <s:else>
							 <s:iterator value="messageList" var="message">
  								<span class="STYLE2"><s:property value="message"/></span><br/><br/>
  	 							</s:iterator>
							 </s:else>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						
					</td>
					<td align="right" valign="top"><input id="button_goback" type="submit" value="返回" style="margin-right:15px;"/></td>
				</tr>
			</table>
		</div>
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