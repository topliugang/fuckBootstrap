<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="ContentType" content="html/text;charset=utf-8" />
		<title>接受者选择系统草稿</title>
		<style>
		//源数据选择模块样式
			#first_div{
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
				height:180px;
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
			#mul-staff{
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
			margin-top:10px;
			margin-left:10px;
			margin-right:10px;
			margin-bottom:10px;
			font-size:16px;
			font-weight:bold;
		}
		
		.mul-obj{
			width:230px;
			height:235px;
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

			function change(obj){
				//alert(obj);

				var xhr=getXhr();
				//alert(xhr);

				var info="departmentIdStr="+obj;
				//alert(info);
				
				xhr.open('post','choice_getStaff',true);
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

			// ---->
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
		</script>
	</head>
	<body>
	
		<!-- 消息接收者选择系统table -->
		<form action="choice_submitMeassage" method="post" onsubmit="return mysubmit();">
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
							<s:iterator value="departmentList" var="department">
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
								<s:iterator value="departmentList" var="department">
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
							<select id="mul-obj-department" class="mul-obj" multiple="multiple" name="departmentList" ondblclick="backToDep();" >
							</select>
						</div>
						<div id="obj-staff-div" class="obj-div">
							<select id="mul-obj-staff" class="mul-obj" multiple="multiple" name="staffList" ondblclick="backTostaff();">
							</select>
						</div>
					</div>
				</td>
				<!-- 目标数据选择结束 -->
			
			</tr>
		
		</table>
		<input type="submit" value="提交" />
		</form>
	</body>
</html>