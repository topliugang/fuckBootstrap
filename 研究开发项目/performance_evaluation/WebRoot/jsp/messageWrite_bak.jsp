<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta http-equiv="ContentType" content="html/text;charset=utf-8" />
    <title>填写新消息</title>
    <style>
		//源数据选择模块样式
			#first_div{
				width:250px;
				height:400px;
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
				align:left;
				width:200px;
			}
			.menu-img{
				vertical-align:bottom;
			}
			
			#mul-div{
				margin-top:5px;
				margin-left:10px;
				margin-right:10px;
				margin-bottom:15px;
			}
			#mul-department{
				width:200px;
				height:130px;
				border:1px solid #CCCCCC;
				font-size:15px;
			}
			
			#sel-div{
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
			
			#mul-staff-div{
				margin-top:5px;
				margin-left:10px;
				margin-right:10px;
				margin-bottom:5px;
			}
			#mul-class-div{
				margin-top:5px;
				margin-left:10px;
				margin-right:10px;
				margin-bottom:5px;
			}
			#mul-staff{
				width:200px;
				height:130px;
				border:1px solid #CCCCCC;
				font-size:15px;
			}
			#mul-class{
				width:200px;
				height:130px;
				border:1px solid #CCCCCC;
				font-size:15px;
			}
			
		//目标数据选择模块样式
		#second-div{
			width:250px;
			height:300px;
			border:1px solid #CCCCCC;
		}
		
		.obj-div{
			margin-top:10px;
			margin-left:10px;
			margin-right:10px;
			margin-bottom:10px;
			font-size:16px;
			font-weight:bold;
		}
		
		.mul-obj{
			width:230px;
			height:180px;
		}
		
		#t-1-1{
			border-right:#cccccc solid 1px;
			border-left:#cccccc solid 1px;
			border-top:#cccccc solid 1px;
			border-bottom:#cccccc solid 1px;
		}
		#t-1-3{
			border-right:#cccccc solid 1px;
			border-left:#cccccc solid 1px;
			border-top:#cccccc solid 1px;
			border-bottom:#cccccc solid 1px;
		}
		
		#action_info_div{
			list-style:none;
			color:red;
			font-size:16px;
		}
		</style>
		<script type="text/javascript" src="js/jquery-1.4.3.js"></script>
		<script>
		function getMyObject(obj){
			var object=document.getElementById(obj);
			return object;
		}
		
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
		
		function toggleMenu(objId){
			var ulEle = document.getElementById(objId);
			ulEle.style.display = ulEle.style.display == "" ? "none" : "";
		}
		
		function departmentChange(obj){
			//alert(obj);

			var xhr=getXhr();
			//alert(xhr);

			var info="departmentIdStr="+obj;
			//alert(info);
			
			xhr.open('post','message_getStaff',true);
			xhr.setRequestHeader('content-type',
			'application/x-www-form-urlencoded');
			xhr.onreadystatechange=function(){
					if(xhr.readyState==4){
							var txt=xhr.responseText;
							//alert(txt);
							var arr = txt.split(';');
							getMyObject('mul-staff').innerHTML = '';
							for(i=0;i<arr.length-1;i++){
								//alert(arr[i]);
								var arr1 = arr[i].split(',');
								//alert(arr1[1]+','+arr1[0]);
								
								var falg=false;
								
								var optArr=getMyObject('mul-obj-staff').options;
								for(j=0;j<optArr.length;j++){
									if(optArr[j].value==arr1[0]&&optArr[j].text==arr1[1]){
										falg=true;
										break;
									}
								}

								if(!falg){
									var op = new Option(arr1[1],arr1[0]);
									getMyObject('mul-staff').options[i] = op;
									falg=false;
								}
							}
						}
				}
			xhr.send(info);
		}	
			
		function myClass(obj){
			alert(obj);
			var info="classIdStr="+obj;
			alert(info);
			var xhr=getXhr();
			alert(xhr);
			xhr.open('post','message_getStaffByClass',true);
			xhr.setRequestHeader('content-type','application/x-www-form-urlencoded');
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4){
					var txt=xhr.responseText;
					alert(txt);

					var arr = txt.split(';');
					alert(arr);
					document.getElementById('mul-class').innerHTML = '';
					for(var i=0;i<arr.length-1;i++){
						var arr1 = arr[i].split(',');
						var op = new Option(arr1[1],arr1[0]);
						document.getElementById('mul-class').options[i] = op;
					}
				}
			}
			xhr.send(info);
		}

		function moveToList(){
			var flag1=false;
			$('#mul-department option').each(function(i){
					if(this.selected){
						flag1=true;
						$('#mul-obj-department').append(this);
					}
				});
			var flag2=false;
			$('#mul-staff option').each(function(i){
				if(this.selected){
					flag2=true;
					$('#mul-obj-staff').append(this);
				}
			});

			if(!flag1){
				if(!flag2){
					alert('选项不能为空!')
				}
			}
		}
//双击
		function moveTodep(){
			$('#mul-department option').each(function(i){
				if(this.selected){
					$('#mul-obj-department').append(this);
				}
			});
		}
		function moveTostaff(){
			$('#mul-staff option').each(function(i){
				if(this.selected){
					$('#mul-obj-staff').append(this);
				}
			});
		}
			
// --->>
		function moveAllOpt(){
			$('#mul-department option').each(function(i){
					$('#mul-obj-department').append(this);
			});
			if($('#mul-staff option')!=null){
				$('#mul-staff option').each(function(i){
					$('#mul-obj-staff').append(this);
				});
			}
		}	
//  <-----
		function backToList(){
			var flag1=false;
			$('#mul-obj-department option').each(function(i){
					if(this.selected){
						flag1=true;
						$('#mul-department').append(this);
					}
				});
			var flag2=false;
			$('#mul-obj-staff option').each(function(i){
				if(this.selected){
					flag2=true;
					$('#mul-staff').append(this);
				}
			});
			if(!flag1){
				if(!flag2){
					alert('选项不能为空!')
				}
			}
		}
		
		//双击
		function backToDep(){
			$('#mul-obj-department option').each(function(i){
				if(this.selected){
					$('#mul-department').append(this);
				}
			});
		}
		function backTostaff(){
			$('#mul-obj-staff option').each(function(i){
				if(this.selected){
					$('#mul-staff').append(this);
				}
			});
		}

// <<------
		function backAllOpt(){
			if($('#mul-obj-department option')!=null){
				$('#mul-obj-department option').each(function(i){
					$('#mul-department').append(this);
				});
			}
			if($('#mul-obj-staff option')!=null){
				$('#mul-obj-staff option').each(function(i){
					$('#mul-staff').append(this);
				});
			}
		}

//提交
		function mysubmit(){
			var depOptArr=getMyObject('mul-obj-department').options;
			var staffOptArr=getMyObject('mul-obj-staff').options;
			var dLength=depOptArr.length;
			var sLength=staffOptArr.length;
			if(dLength==0){
				if(sLength==0){
					alert("必须选择接收者");
					return false;
				}
			}
			if(dLength!=0){
				$('#obj-department-div option').each(function(i){
					this.selected = true;
				});
			}
			if(sLength!=0){
				$('#mul-obj-staff option').each(function(i){
					this.selected = true;
				});	
			}
		}

//文件上传
		function showoneupload(){
			alert('显示下一个文件上传控件。。');
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
  
  <body>
    <div>
    	<img src="images/writenewmessage.png" />
    </div> 
    <div style="width:800px;heigth:800px;border:1px solid #CCCCCC;position: absolute;left:200px;top:100px;">
    <br/>
	<form action="message_send" enctype="multipart/form-data" method="post" onsubmit="return mysubmit();">
		<table width="99%" >
		
			<tr>
				<td align="right" valign="top" style="font-weight:bold;color:#33CC99;">消息标题：</td>
				<td align="left" valign="top">
					<input name="titleToSend" type="text" style="border:1px solid #CCCCCC;width:610px" />
				</td>
			</tr>
			<tr><td><br/></td><td><br/></td></tr>
			<tr>
				<td align="right" valign="top" style="font-weight:bold;color:#33CC99;">收件人：</td>
				<td align="left" valign="top">
					<table id="message-table">
		
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
									<img class="menu-img" src="images/people.png" />
									<span>根据部门选择接收人</span>
								</div>
								<div id="staff-div">
									<div id="sel-div">
										<select id="sel" class="sel" onchange="departmentChange(this.value);">
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
								<div class="menu" onclick="toggleMenu('class-div')">
									<img class="menu-img" src="images/people.png" />
									<span>根据类别</span>
								</div>
								<div id="class-div">
									<div id="sel-div">
										<select id="sel" class="sel" onchange="myClass(this.value)">
										<s:iterator value="classes" var="class">
											<option value="${classId}">
												<s:property value="className" />
											</option>
										</s:iterator>
										</select>
									</div>
									<div id="mul-class-div">
										<select id="mul-class" class="sel" multiple="multiple" ondblclick="moveTostaff();">
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
				</td>
			</tr>
			<tr><td><br/></td><td><br/></td></tr>
			<tr>
				<td align="right" valign="top" style="font-weight:bold;color:#33CC99;">消息附件：</td>
				<td id="uploadColumn" align="left" valign="top">
					<input type="file" name="uploads"  multiple="" onchange="showoneupload();"/><br/>
				</td>
			</tr>
			<tr><td><br/></td><td><br/></td></tr>
			<tr>
				<td align="right" valign="top" style="font-weight:bold;color:#33CC99;">消息内容：</td>
				<td align="left" valign="top">	
					<%--<textarea rows="10" cols="74" style="border:1px solid #CCCCCC;" id="myEditor" 
					name='contentToSend'></textarea>
					--%>
					<script id="container" name="contentToSend" type="text/plain" style="width:610px;height:250px;">
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
				<td align="right" valign="top">
					<input type="submit" value="提交" />
				</td>
				<td align="left" valign="top">
				</td>
			</tr>
			
		</table>
	</form>
	</div>
	
	
  </body>
</html>
