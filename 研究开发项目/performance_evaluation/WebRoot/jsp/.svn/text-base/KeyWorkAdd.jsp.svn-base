<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
		function trim(str){ 
            return str.replace(/(^\s*)|(\s*$)/g, ""); 
    	}
		function mysubmit(){
			var content=document.getElementById('content').value;
			var startTime=document.getElementById('startTime').value;
			var endTime=document.getElementById('endTime').value;
			if(trim(content)==''){
				alert('重点工作内容不能为空');
			}else{
				if(trim(startTime)==''){
					alert('开始日期不能为空');
				}else{
					if(trim(endTime)==''){
						alert('结束日期不能为空');
					}else{
						var startTimeArr=startTime.split('-');
						var endTimeArr=endTime.split('-');
						if(endTimeArr[0]<startTimeArr[0]){
							alert('开始日期不能超过结束日期');
							document.getElementById('endTime').value='';
						}else{
							if(endTimeArr[0]=startTimeArr[0]){
								if(endTimeArr[1]<startTimeArr[1]){
									alert('开始日期不能超过结束日期');
									document.getElementById('endTime').value='';
								}else{
									if(endTimeArr[1]==startTimeArr[1]){
										if(endTimeArr[2]<startTimeArr[2]){
											alert('开始日期不能超过结束日期');
											document.getElementById('endTime').value='';
										}else{
											if(endTimeArr[2]==startTimeArr[2]){
												alert('开始日期不能和结束日期相同');
												document.getElementById('endTime').value='';
											}else{
												document.getElementById('myForm').submit();
											}
										}
									}else{
										document.getElementById('myForm').submit();
									}
								}
							}else{
								document.getElementById('myForm').submit();
							}
						}
					}
				}
			}
		}
	</script>
	</head>

	<body style="height:100%;">
<!--	<form name="scoreForm" action="score_departmentScore" onsubmit="return checkScore(scoreForm)">-->
		<table width="100%" border="0" style="height:100%;" align="center" cellpadding="0"	cellspacing="0" >
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
								<span class="little_title">添加重点工作信息</span>
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
				<table width="100%" style="height:100%;" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="9"
									background="<%=request.getContextPath()%>/jsp/images/tab_12.gif">
									&nbsp;
								</td>
								<td bgcolor="#f3ffe3" style="padding:0px 250px 0px 250px;">
									<form id="myForm" action="keyWork_addDepartmentKeyWork" method="post" >
										<table width="99%">
											<tr><td><br/></td><td><br/></td></tr>
											<tr>
												<td align="right" style="color:#1F4A65;font-weight:bold;">编号：</td><td><input style="border:1px solid #CCCCCC;width:300px;" name='keyWorkId' type="text" value="${keyWorkId}" readonly/></td>
											</tr>
											<tr><td><br/></td></tr>
											<tr>
												<td align="right" style="color:#1F4A65;font-weight:bold;">申请部门：</td><td><input style="border:1px solid #CCCCCC;width:300px;" name='departmentName' type="text" value="${departmentName}" readonly/></td>
											</tr>
											<tr><td><br/></td></tr>
											<tr><td align="right" style="color:#1F4A65;font-weight:bold;">工作内容：</td><td><br/></td></tr>
											<tr>
												<td></td><td>
														<textarea style="border:1px solid #CCCCCC;" id="content" name="keyWorkContent" cols="50" rows="10">${keyWorkContent}</textarea></td>
											</tr>
											<tr><td><br/></td></tr>
											<tr>
												<td align="right" style="color:#1F4A65;font-weight:bold;">开始时间：</td><td>
													<input autocomplete="off" style="border:1px solid #CCCCCC;width:300px;" id="startTime" class="Wdate"  name="startTime" type="text" onclick="WdatePicker();" />
												</td>
											</tr>
											<tr><td><br/></td></tr>
											<tr>
												<td align="right" style="color:#1F4A65;font-weight:bold;">结束时间：</td><td>
													<input autocomplete="off" style="border:1px solid #CCCCCC;width:300px;" id="endTime" class="Wdate"  name="endTime" type="text" onclick="WdatePicker();" />
												</td>
											</tr>
											<tr><td><br/></td></tr>
											<tr>
												<td align="right"><input type="button" value="提交" onclick="mysubmit();"/></td><td>
													<input type="reset" value="重置" />
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
<!--	</form>	-->
	</body>
</html>
