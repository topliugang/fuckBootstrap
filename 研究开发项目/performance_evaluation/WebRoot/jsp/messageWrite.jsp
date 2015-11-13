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
		//源数据选择模块样式
			#accepterChoice_div{
				width:250px;
				height:500px;
				border:1px solid #CCCCCC;
			}
				
			.menu{
				border:2px solid #add8e6;
				margin-top:5px;
				margin-left:10px;
				margin-right:10px;
				margin-bottom:5px;
				font-size:16px;
				font-weight:bold;
				color:#add8e6;
				text-align:center;
				width:200px;
			}
			.menu-img{
				vertical-align:bottom;
			}
			
			#departmentSource_div{
				margin-top:5px;
				margin-left:10px;
				margin-right:10px;
				margin-bottom:15px;
			}
			#mul_sel_departmentSource{
				width:200px;
				height:180px;
				border:1px solid #CCCCCC;
				font-size:15px;
			}
			
			#staffChoiceConditions_div{
				margin-top:5px;
				margin-left:10px;
				margin-right:10px;
				margin-bottom:5px;
			}
			#sel{
				width:200px;
				border:1px solid #CCCCCC;
				font-size:15px;
			}
			
			#staffSource_div{
				margin-top:5px;
				margin-left:10px;
				margin-right:10px;
				margin-bottom:15px;
			}
			#mul_sel_staffSource{
				width:200px;
				height:180px;
				border:1px solid #CCCCCC;
				font-size:15px;
			}
			#classificationSource_div{
				margin-top:5px;
				margin-left:10px;
				margin-right:10px;
				margin-bottom:5px;
			}
			#mul_sel_classificationSource{
				width:200px;
				height:180px;
				border:1px solid #CCCCCC;
				font-size:15px;
			}
		//目标数据选择模块样式
		#second-div{
			width:250px;
			height:500px;
			border:1px solid #CCCCCC;
		}
		
		.obj-div{
			margin-top:5px;
			margin-left:10px;
			margin-right:10px;
			margin-bottom:15px;
			font-size:16px;
			font-weight:bold;
		}
		
		.mul-obj{
			width:200px;
			height:180px;
		}
		#mul-obj-staff{
			width:200px;
			height:235px;
		}
		#accepterSource_column{
			border-right:#cccccc solid 1px;
			border-left:#cccccc solid 1px;
			border-top:#cccccc solid 1px;
			border-bottom:#cccccc solid 1px;
		}
		#target_column{
			border-right:#cccccc solid 1px;
			border-left:#cccccc solid 1px;
			border-top:#cccccc solid 1px;
			border-bottom:#cccccc solid 1px;
		}
		
		#myTest {
			z-index:9998;
    		width: 100%;
    		height: 150%;
   			position: absolute;
    		top: 0px;
    		left: 0px;
    		right: 0px;
    		background-color: #777;
    		filter: progid: DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75);
    		filter: alpha(opacity=70);
    		opacity: 0.7;
		}
		</style>
<script type="text/javascript" src="js/jquery-1.4.3.js"></script>
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

//-------------------------------------------------------选择收件人的js开始--------------------------------------------------------------
//----------------------------------------------加载数据源代码开始
		//获取ajax对象
		function getXhr(){
			var xhr=null;
			if(window.XMLHttpRequest){
				//非IE浏览器
				xhr=new XMLHttpRequest();
			}else{
				xhr=new ActiveXObject('Microsoft.XMLHttp');
			}
			return xhr;
		}
		
		//根据所选部门查询对应的员工，利用Ajax
		function getStaffsByDep(obj){
			//获取ajax对象
			var xhr=getXhr();
			//凭借请求参数
			var info="departmentIdStr="+obj;
			//设置请求命令，方式以及消息头
			xhr.open('post','message_getStaffsByDep',true);
			xhr.setRequestHeader('content-type','application/x-www-form-urlencoded');
			//编写回调函数
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4){
					var txt=xhr.responseText;
					document.getElementById('mul_sel_staffSource').innerHTML = '';
					var staffArr = txt.split(';');
					for(var i=0;i<staffArr.length-1;i++){
						var staffInfoArr = staffArr[i].split(',');
						var op = new Option(staffInfoArr[1],staffInfoArr[0]);
						document.getElementById('mul_sel_staffSource').options[i]=op;
					}
				}
			}
			//向服务器发送请求
			xhr.send(info);
		}

		//根据所选条件查询对应的员工，利用Ajax
		function getStaffsByCla(obj){
			//获取ajax对象
			var xhr=getXhr();
			//凭借请求参数
			var info="classIdStr="+obj;
			//设置请求命令，方式以及消息头
			xhr.open('post','message_getStaffByCla',true);
			xhr.setRequestHeader('content-type','application/x-www-form-urlencoded');
			//编写回调函数
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4){
					var txt=xhr.responseText;
					document.getElementById('mul_sel_staffSource').innerHTML = '';
					var staffArr = txt.split(';');
					for(var i=0;i<staffArr.length-1;i++){
						var staffInfoArr = staffArr[i].split(',');
						var op = new Option(staffInfoArr[1],staffInfoArr[0]);
						document.getElementById('mul_sel_staffSource').options[i]=op;
					}
				}
			}
			//向服务器发送请求
			xhr.send(info);
		}

		function depClick(){
			document.getElementById('depSpan').style.color='#1e90ff';
			document.getElementById('classSpan').style.color='#CCCCCC';
			document.getElementById('depS').disabled='';
			document.getElementById('calS').disabled='disabled';
		}
		function claClick(){
			document.getElementById('classSpan').style.color='#1e90ff';
			document.getElementById('depSpan').style.color='#CCCCCC';
			document.getElementById('depS').disabled='disabled';
			document.getElementById('calS').disabled='';
		}
		function toggleMenu(obja,objb,srcc,objc,srcd,objd){
				var ulElea = document.getElementById(obja);
				ulElea.style.display = ulElea.style.display == "" ? "none" : "";
				var ulEleb = document.getElementById(objb);
				ulEleb.style.display = ulEleb.style.display == "" ? "none" : "";
				var ulElec=document.getElementById(srcc);
				ulElec.style.display='none';
				var ulEled=document.getElementById(objc);
				ulEled.style.display='none';
				var ulElee=document.getElementById(srcd);
				ulElee.style.display='none';
				var ulElef=document.getElementById(objd);
				ulElef.style.display='none';
		}
//----------------------------------------------加载数据源代码结束
//----------------------------------------------选择功能代码开始
		//双击选择，过去/回来
		function moveOption(src,obj){
			var flag=false;
			$('#'+src+' option').each(function(i){
				if(this.selected){
					flag=true;
					$('#'+obj).append(this);
				}
			});
			return flag;
		}
		//功能按钮：--------> / <---------
		function moveToList(src1,src2,src3,obj1,obj2,obj3){
			var flag1=false;var flag2=false;var flag3=false;
			if(!($('#departmentSource_div').is(":hidden")&&$('#obj-department-div').is(":hidden"))){
				flag1=moveOption(src1,obj1);
			}
			if(!($('#staffChoice_div').is(":hidden")&&$('#obj-staff-div').is(":hidden"))){
				flag2=moveOption(src2,obj2);
			}
			if(!($('#classificationSource_div').is(":hidden")&&$('#obj-classification-div').is(":hidden"))){
				flag3=moveOption(src3,obj3);
			}
			if(!(flag1||flag2||flag3)){
				alert('请选择接收人');
			}
		}
		//功能按钮：-------->>/<<----------
		function unitMoveAll(src,obj){
			if($('#'+src+' option')!=null){
				$('#'+src+' option').each(function(i){
						$('#'+obj).append(this);
				});
			}
		}
		function moveAll(src1,src2,src3,obj1,obj2,obj3){
			if(!($('#departmentSource_div').is(":hidden")&&$('#obj-department-div').is(":hidden"))){
				unitMoveAll(src1,obj1);
			}
			if(!($('#staffChoice_div').is(":hidden")&&$('#obj-staff-div').is(":hidden"))){
				unitMoveAll(src2,obj2);
			}
			if(!($('#classificationSource_div').is(":hidden")&&$('#obj-classification-div').is(":hidden"))){
				unitMoveAll(src3,obj3);
			}
		}
//----------------------------------------------选择功能代码结束
	function toChoiceAccepter(){
		document.getElementById('myTest').style.display='';
		document.getElementById('acc').style.display='';
		document.getElementById('cc').style.display='';
	}
	function close(){
		document.getElementById('myTest').style.display='none';
		document.getElementById('acc').style.display='none';
		document.getElementById('cc').style.display='none';
		
	}
//---------------------------------------------提交功能
	function submitChoice(){
		//department1,department2;staff1,staff2,staff3;class1,class2,class3
		var accepterName='';
		var departmentOpts=document.getElementById('mul-obj-department').options;
		var staffOpts=document.getElementById('mul-obj-staff').options;
		var classOpts=document.getElementById('mul-obj-classification').options;

		for(var i=0;i<departmentOpts.length;i++){
			accepterName=accepterName+departmentOpts[i].text;
			if(i==departmentOpts.length-1){
				accepterName=accepterName+';';
			}else{
				accepterName=accepterName+','
			}

			$('#selector-div').append("<input type='hidden' name='departmentidstoreceiveToSend' value='"+departmentOpts[i].value+"'>");
		}
		for(var i=0;i<staffOpts.length;i++){
			accepterName=accepterName+staffOpts[i].text;
			if(i==staffOpts.length-1){
				accepterName=accepterName+';'
			}else{
				accepterName=accepterName+','
			}
			$('#selector-div').append("<input type='hidden' name='staffidstoreceiveToSend' value='"+staffOpts[i].value+"'>");
		}
		for(var i=0;i<classOpts.length;i++){
			accepterName=accepterName+classOpts[i].text;
			if(i==classOpts.length-1){
				accepterName=accepterName+';'
			}else{
				accepterName=accepterName+','
			}
			$('#selector-div').append("<input type='hidden' name='classidstoreceiveToSend' value='"+classOpts[i].value+"'>");
		}
		
		document.getElementById('myTest').style.display='none';
		document.getElementById('acc').style.display='none';
		document.getElementById('cc').style.display='none';
		document.getElementById('infoAccepterName').value=accepterName;
	}
//-------------------------------------------------------选择收件人的js结束--------------------------------------------------------------
//文件上传
		function showoneupload(){
			$('#uploadColumn').append("<input name='uploads' type='file' onchange='showoneupload();'><br/>");
		}

		$(function(){
			var info="${fileuploadInfo}";
			if(!info==""){
				alert(info);
			}
		});
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
								<span class="little_title">写邮件</span>
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
					<form  action="message_send" enctype="multipart/form-data" method="post">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="9"
									background="<%=request.getContextPath()%>/jsp/images/tab_12.gif">
									&nbsp;
								</td>
								<td bgcolor="#f3ffe3">
									<table width="99%" style="padding-left:60px;">	
									<tr><td><br/></td><td><br/></td></tr>
									<tr>
										<td align="right" valign="top" style="font-weight:bold;color:#1F4A65;">消息标题：</td>
										<td align="left" valign="top">
											<input name="titleToSend" type="text" style="border:1px solid #CCCCCC;width:610px" />
										</td>
									</tr>
									<tr><td><br/></td><td><br/></td></tr>
									
									<tr>
										<td align="right" valign="top" style="font-weight:bold;color:#1F4A65;">接收人：</td>
										<td align="left" valign="top">
											<input id="infoAccepterName"  type="text" style="border:1px solid #CCCCCC;width:610px" />
											<input type="button" value="选择.." style="width:60px;" onclick="toChoiceAccepter()" />
											<div id="selector-div">	
											</div>
										</td>
									</tr>
									<tr><td><br/></td><td><br/></td></tr>

									
									<tr>
										<td align="right" valign="top" style="font-weight:bold;color:#1F4A65;">消息附件：</td>
										<td id="uploadColumn" align="left" valign="top">
											<input type="file" name="uploads"  multiple="" onchange="showoneupload();"/><br/>
										</td>
									</tr>
									<tr><td><br/></td><td><br/></td></tr>
									<tr>
										<td align="right" valign="top" style="font-weight:bold;color:#1F4A65;">消息内容：</td>
										<td align="left" valign="top">	
											<%--<textarea rows="10" cols="74" style="border:1px solid #CCCCCC;" id="myEditor" 
											name='contentToSend'></textarea>
											--%>
											<script id="container" name="contentToSend" type="text/plain" style="width:720px;height:400px;">
											</script>
											<!-- 配置文件 -->
											<script type="text/javascript" src="ueditor/ueditor.config.js"></script>
											<!-- 编辑器源码文件 -->
											 <script type="text/javascript" src="ueditor/ueditor.all.js"></script>
											<!-- 实例化编辑器 -->
											<script type="text/javascript">
												 var ue = UE.getEditor('container');
											</script>
										</td>
									</tr>
									<tr><td><br/></td><td><br/></td></tr>
									<tr>
										<td align="left" valign="top">
										</td>
										
										<td align="left" valign="top">
											<input  type="submit" value="提交" style="margin-left:678px; width:42px;" />
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
		<!-- 收件人选择页面 -->
		
		<!-- 修改区域 -->
		<div id="acc" style=" padding-left:50px;padding-top:5px;z-index:9999;display:none;POSITION:absolute; left:45%; top:40%; width:650px; height:500px; margin-left:-300px; margin-top:-200px;background-color:#FFFFFF;">
	<div id="cc" align="right" style="margin-right:5px;margin-bottom:5px;"><a href="javascript:close();"><img src="images/close.png"/></a></div>
	<div style="overflow:auto;height:460px;">
	<table id="accepterChoice_table">
		<tr>
			<!-- 源数据选择开始 -->
			<td id="accepterSource_column" valign="top">
				<div id="accepterChoice_div">
					<div id="departmentMenu_div" class="menu" onclick="toggleMenu('departmentSource_div','obj-department-div','staffChoice_div','obj-staff-div','classificationSource_div','obj-classification-div')">
						<span>接收部门选择</span>
					</div>
					<div id="departmentSource_div" >
						<select id="mul_sel_departmentSource" multiple="multiple" ondblclick="moveOption('mul_sel_departmentSource','mul-obj-department');">
						<s:iterator value="departmentList" var="department">
							<option value="${departmentId}">
							<s:property value="departmentName" />
							</option>
						</s:iterator>
						</select>
					</div>
					<div id="staffMenu_div" class="menu" onclick="toggleMenu('staffChoice_div','obj-staff-div','departmentSource_div','obj-department-div','classificationSource_div','obj-classification-div')">
						<span>接收员工选择</span>
					</div>
					<div id="staffChoice_div" style="display:none;">
						<div id="staffChoiceConditions_div">
							<table>
								<tr>
									<td><input name="sel" type="radio" onclick="depClick();"/><span id="depSpan" style="color:#CCCCCC">部门&nbsp;</span></td>
									<td>
										<select id="depS" style="width:130px;"  disabled="disabled" onchange="getStaffsByDep(this.value);" >
											<option></option>
											<s:iterator value="departmentList" var="department">
											<option value="${departmentId}">
											<s:property value="departmentName" />
											</option>
											</s:iterator>
										</select>
									</td>
								</tr>
								<tr>  
									<td><input name="sel" type="radio" onclick="claClick();"/><span id="classSpan" style="color:#CCCCCC">分类&nbsp;</span></td>
									<td>
										<select id="calS" style="width:130px;" disabled="disabled" onchange="getStaffsByCla(this.value);">
											<option></option>
											<s:iterator value="staffClassList" var="staffClass">
											<option value="${classId}">
											<s:property value="className" />
											</option>
											</s:iterator>
										</select>
									</td>
								</tr>
							</table>
						</div>
						<div id="staffSource_div">
							<select id="mul_sel_staffSource" class="sel" multiple="multiple" ondblclick="moveOption('mul_sel_staffSource','mul-obj-staff');">
							</select>
						</div>
					</div>
					<div id="classificationMenu_div" class="menu" onclick="toggleMenu('classificationSource_div','obj-classification-div','staffChoice_div','obj-staff-div','departmentSource_div','obj-department-div')">
						<span>其他分类选择</span>
					</div>
					<div id="classificationSource_div" style="display:none;">
						<select id="mul_sel_classificationSource" multiple="multiple" ondblclick="moveOption('mul_sel_classificationSource','mul-obj-classification');">
							<s:iterator value="staffClassList" var="staffClass">
								<option value="${classId}">
									<s:property value="className" />
								</option>
							</s:iterator>
						</select>
					</div>
				</div>
			</td>
			<!-- 源数据选择结束 -->
				
				<!-- 功能按钮群开始 -->
				<td>
					<p>
						<input type="button" value="--&gt;" style="width:120px" 
						onclick="moveToList('mul_sel_departmentSource','mul_sel_staffSource','mul_sel_classificationSource','mul-obj-department','mul-obj-staff','mul-obj-classification');"/>
					</p>
					<p>
						<input type="button" value="--&gt;&gt;" style="width:120px" onclick="moveAll('mul_sel_departmentSource','mul_sel_staffSource','mul_sel_classificationSource','mul-obj-department','mul-obj-staff','mul-obj-classification');"/>
					</p>
					<p>
						<input type="button" value="&lt;--" style="width:120px"  
						onclick="moveToList('mul-obj-department','mul-obj-staff','mul-obj-classification','mul_sel_departmentSource','mul_sel_staffSource','mul_sel_classificationSource');"/>
					</p>
					<p>
						<input type="button" value="&lt;&lt;--" style="width:120px" onclick="moveAll('mul-obj-department','mul-obj-staff','mul-obj-classification','mul_sel_departmentSource','mul_sel_staffSource','mul_sel_classificationSource');"/>
					</p>
				</td>
				<!-- 功能按钮群结束 -->
				
				<!-- 目标数据选择开始 -->
				<td id="target_column" valign="top">
					<div id="second-div">
						<div id="departmentMenu_div" class="menu" >
						<span>接收部门选择</span>
						</div>
						<div id="obj-department-div" class="obj-div" >
							<select id="mul-obj-department" class="mul-obj" multiple="multiple" name="departmentList" ondblclick="moveOption('mul-obj-department','mul_sel_departmentSource');">
							</select>
						</div>
						<div id="staffMenu_div" class="menu">
						<span>接收员工选择</span>
						</div>
						<div id="obj-staff-div" class="obj-div" style="display:none;">
							<select id="mul-obj-staff" multiple="multiple" name="staffList" ondblclick="moveOption('mul-obj-staff','mul_sel_staffSource');">
							</select>
						</div>
						<div id="classificationMenu_div" class="menu" >
						<span>其他分类选择</span>
						</div>
						<div id="obj-classification-div" class="obj-div" style="display:none;">
							<select id="mul-obj-classification" class="mul-obj" multiple="multiple" name="staffList" ondblclick="moveOption('mul-obj-classification','mul_sel_classificationSource');">
							</select>
						</div>
						<div style="width:230px;height:5px;" ></div>
					</div>
				</td>
				<!-- 目标数据选择结束 -->
			</tr>
		
		</table>
		<!-- 修改区域 -->
		
		<br/>
		<br/>
		<br/>
		<input type="button" onclick="submitChoice();" value="提交" />
		</div>
		</div>
		<div id="myTest" style="display:none"></div>
	</body>
</html>
