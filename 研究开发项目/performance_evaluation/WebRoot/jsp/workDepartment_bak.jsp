<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px
}

.STYLE4 {
	font-size: 12px;
	color: #1F4A65;
	font-weight: bold;
}

a:link {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}

a:visited {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}

a:hover {
	font-size: 12px;
	color: #FF0000;
	text-decoration: underline;
}

a:active {
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}

.STYLE7 {
	font-size: 12
}
-->
</style>

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
if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=highlightcolor;
}
}

function  changeback(){
if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
return
if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
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
if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=clickcolor;
}
else
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
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
							<td width="241" background="<%=request.getContextPath()%>/jsp/images/tab_05.gif">
								<img src="<%=request.getContextPath()%>/jsp/images/311.gif"
									width="16" height="16" />
								<span class="STYLE4">重点工作</span>
							</td>

							<td width="999"
								background="<%=request.getContextPath()%>/jsp/images/tab_05.gif">
								<form action="workScore_listDepartmentWorkScoredItem">
									<select name="departmentNameChosen">
										<option selected="selected"></option>
										<s:iterator value="workDepartmentList">
											<option>
												<s:property value="departmentName" />
											</option>
										</s:iterator>
									</select>
									<input type="submit" value="查询">
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
											<table width="90%" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<!--<td class="STYLE1">
														<div align="center">
															<img
																src="<%=request.getContextPath()%>/jsp/images/001.gif"
																width="14" height="14" />
														</div>
													</td>
													<td class="STYLE1">
														<div align="center">
															<a href="item.departmentScoredItem_toAdd.action">新增</a>
														</div>
													</td>
												--></tr>
											</table>
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
					<form name="submitScore" action="workScore_submitDepartmentWorkScore">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<!--<td width="9"
									background="<%=request.getContextPath()%>/jsp/images/tab_12.gif">
									&nbsp;
								</td>
								--><td bgcolor="#f3ffe3">
									<table width="99%" border="0" align="center" cellpadding="0"
										cellspacing="1" bgcolor="#c0de98" onmouseover="changeto()"
										onmouseout="changeback()">
										<tr>
											<td width="8%" height="26"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													序号
												</div>
											</td>
											<td width="8%" height="26"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													编号
												</div>
											</td>
											<td width="14%"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													申请部门
												</div>
											</td>
											<td width="8%" height="26"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													重点工作内容
												</div>
											</td>
											<td width="14%" height="26"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													开始年份
												</div>
											</td>
											<td width="14%"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													开始月份
												</div>
											</td>
											<td width="14%"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													结束年份
												</div>
											</td>
											<td width="14%"
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
								</td>
							</tr>
							<tr>
								<td>
									<br/>
								</td>
							</tr>
							<tr>
								<td>
									<br/>
								</td>
							</tr>
							<tr>
								<td width="241">
									<img src="<%=request.getContextPath()%>/jsp/images/311.gif" width="16" height="16" />
									<span class="STYLE4">重点工作打分</span>
								</td>
							</tr>
							<tr>
								<!--<td width="9"
									background="<%=request.getContextPath()%>/jsp/images/tab_12.gif">
									&nbsp;
								</td>
								--><td bgcolor="#f3ffe3">
									<table width="99%" border="0" align="center" cellpadding="0"
										cellspacing="1" bgcolor="#c0de98" onmouseover="changeto()"
										onmouseout="changeback()">
										<tr>
											<td width="8%" height="26"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													序号
												</div>
											</td>
											<td width="8%" height="26"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													条目编号
												</div>
											</td>
											<td width="14%"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													所属部门
												</div>
											</td>
											<td width="8%" height="26"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													条目名称
												</div>
											</td>
											<td width="14%" height="26"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													条目描述
												</div>
											</td>
											<td width="14%"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													分数
												</div>
											</td>
											<td width="14%"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													基准值
												</div>
											</td>
											<td width="14%"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													计算方法
												</div>
											</td>
											<td width="14%"
												background="<%=request.getContextPath()%>/jsp/images/tab_14.gif"
												class="STYLE1">
												<div align="center" class="STYLE2 STYLE1">
													类别
												</div>
											</td>
											<td width="11%" height="26"
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
														<s:property value="scoreValue"/>
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
													<input type="text" name="departmentScores"/>
												</td>
											</tr>
										</s:iterator>
										<tr>
											<td height="21" colspan="10" bgcolor="#FFFFFF" class="STYLE2">
												<div align="right">
													<span style="font-size:12px;color:red;"><s:property value="errorInfo"/></span>
													<input type="submit" value="提交"/>
												</div>
											</td>
										</tr>


									</table>
								</td>
								<!--<td width="9"
									background="<%=request.getContextPath()%>/jsp/images/tab_16.gif">
									&nbsp;
								</td>
								
							--></tr>
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