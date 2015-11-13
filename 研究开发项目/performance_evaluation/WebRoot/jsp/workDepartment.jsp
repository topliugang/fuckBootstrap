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

function query()
{
    var form = document.getElementById("workDepartmentForm");
    form.submit();
}

function mysubmit(){
	var maxscore=document.getElementById('scoreValue').innerHTML;
	var myscore=document.getElementById('keyWorkScore').value;
	if(myscore==''){
		alert('分数不能为空');
	}else{
		if(isNaN(myscore)){
			alert('分数的格式不正确');
		}else{
			if(myscore<0){
				alert('分数不能小于0');
			}else{
				if(myscore>maxscore){
					alert('打分超过了最大值');
				}else{
					document.getElementById('myform').submit();
				}
			}
		}
	}
}
</script>
</head>
	<body>
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
								<span class="little_title">重点工作打分</span>
							</td>

							<td width="999"
								background="<%=request.getContextPath()%>/jsp/images/tab_05.gif">
							<form action="workScore_listDepartmentWorkScoredItem" id="workDepartmentForm">
									<s:select theme ="simple" list="workDepartmentList" listValue="departmentName"
										listKey="departmentId" name="departmentIdChosen" id="querySelect" onchange="query()">
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
								<!-- 内容1-->
									<form name="submitScore" action="workScore_submitDepartmentWorkScore" id="myform">				
					<table width="99%" border="0" align="center" cellpadding="0"
										cellspacing="1" bgcolor="#c0de98" onmouseover="changeto()"
										onmouseout="changeback()" class="DGTableStyle">
										<tr class="DGHeaderStyle"> 
											<td width="5%" height="26"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													序号
												</div>
											</td>
											<td width="10%" height="26"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													条目编号
												</div>
											</td>
											<td width="10%"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													所属部门
												</div>
											</td>
											<td width="10%" height="26"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													条目名称
												</div>
											</td>
											<td width="20%" height="26"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													条目描述
												</div>
											</td>
											<td width="5%"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													分数
												</div>
											</td>
											<td width="5%"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													基准值
												</div>
											</td>
											<td width="20%"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													计算方法
												</div>
											</td>
											<td width="10%"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													类别
												</div>
											</td>
											<td width="12%" height="26"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2">
													打分
												</div>
											</td>
										</tr>
										<s:iterator value="departmentScoreItemList" var="item"
											status="st">
											<tr>
												<td height="35" bgcolor="#FFFFFF" class="STYLE2">
													<div align="center" class="STYLE2 STYLE1">
														${st.index+1}
													</div>
												</td>

												<td height="35" bgcolor="#FFFFFF">
													<div align="center" class="STYLE2 STYLE1">
														<s:property value="departmentScoredItemId"/>
													</div>
												</td>
												<td bgcolor="#FFFFFF">
													<div align="center" class="STYLE2 STYLE1">
														<s:property value="department.departmentName"/>
													</div>
												</td>
												<td height="35" bgcolor="#FFFFFF">
													<div align="center" class="STYLE2 STYLE1">
														<s:property value="departmentScoreName"/>
													</div>
												</td>
												<td height="35" bgcolor="#FFFFFF">
													<div align="center" class="STYLE2 STYLE1">
														<s:property value="scoreDescription"/>
													</div>
												</td>

												<td bgcolor="#FFFFFF">
													<div align="center" class="STYLE2 STYLE1">
														<span id="scoreValue"><s:property value="scoreValue"/></span>
													</div>
												</td>
												<td bgcolor="#FFFFFF">
													<div align="center" class="STYLE2 STYLE1">
														<s:property value="scoreReference"/>
													</div>
												</td>
												<td bgcolor="#FFFFFF">
													<div align="center" class="STYLE2 STYLE1">
														<s:property value="scoreCalculationMethod"/>
													</div>
												</td>
												<td bgcolor="#FFFFFF">
													<div align="center" class="STYLE2 STYLE1">
														
													</div>
												<br /></td>
												<td>
													<input type="hidden" name="itemsIds" value="<s:property value="departmentScoredItemId"/>"/>
													<input type="hidden" name="departmentIdChosen" value="${departmentIdChosen}" />
													<input type="text" id="keyWorkScore" name="departmentScores" size="8%"/>
												</td>
											</tr>
										</s:iterator>
										<tr>
											<td height="21" colspan="10" bgcolor="#FFFFFF" class="STYLE2">
												<div align="right">
													<span style="font-size:12px;color:red;"><s:property value="errorInfo"/></span>
													<input type="button" value="提交" onclick="mysubmit();"/>
												</div>
											</td>
										</tr>

									</table>	
								</form>	
								<!-- /内容1-->
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
										<!-- <span class="STYLE1">共120条纪录，当前第1/10页，每页10条纪录</span> -->
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
			<tr><td><br/></td></tr>
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
								<span class="little_title">重点工作内容</span>
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
								<!-- 内容2-->	
								<table width="99%" border="0" align="center" cellpadding="0"
										cellspacing="1" bgcolor="#c0de98" onmouseover="changeto()"
										onmouseout="changeback()" class="DGTableStyle">
										<tr class="DGHeaderStyle">
											<td width="5%" height="26"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													序号
												</div>
											</td>
											<td width="5%" height="26"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													编号
												</div>
											</td>
											<td width="10%"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													申请部门
												</div>
											</td>
											<td width="40%" height="26"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													重点工作内容
												</div>
											</td>
											<td width="10%" height="26"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													开始年份
												</div>
											</td>
											<td width="10%"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													开始月份
												</div>
											</td>
											<td width="10%"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													结束年份
												</div>
											</td>
											<td width="10%"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													结束月份
												</div>
											</td>
										</tr>
										<s:iterator value="departmentKeyWorkList" var="item"
											status="st">
											<tr>
												<td height="35" bgcolor="#FFFFFF" class="STYLE2">
													<div align="center" class="STYLE2 STYLE1">
														${st.index+1}
													</div>
												</td>

												<td height="35" bgcolor="#FFFFFF">
													<div align="center" class="STYLE2 STYLE1">
														<s:property value="keyWorkId"/>
													</div>
												</td>
												<td bgcolor="#FFFFFF">
													<div align="center" class="STYLE2 STYLE1">
														<s:property value="department.departmentName"/>
													</div>
												</td>
												<td height="35" bgcolor="#FFFFFF">
													<div align="center" class="STYLE2 STYLE1">
														<s:property value="keyWorkContent"/>
													</div>
												</td>
												<td height="35" bgcolor="#FFFFFF">
													<div align="center" class="STYLE2 STYLE1">
														<s:property value="startYear"/>
													</div>
												</td>

												<td bgcolor="#FFFFFF">
													<div align="center" class="STYLE2 STYLE1">
														<s:property value="startMonth"/>
													</div>
												</td>
												<td bgcolor="#FFFFFF">
													<div align="center" class="STYLE2 STYLE1">
														<s:property value="endYear"/>
													</div>
												</td>
												<td bgcolor="#FFFFFF">
													<div align="center" class="STYLE2 STYLE1">
														<s:property value="endMonth"/>
													</div>
												</td>
											</tr>
										</s:iterator>
									</table>	
								<!-- /内容2-->		
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
										<!-- <span class="STYLE1">共120条纪录，当前第1/10页，每页10条纪录</span> -->
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

	</body>
</html>
