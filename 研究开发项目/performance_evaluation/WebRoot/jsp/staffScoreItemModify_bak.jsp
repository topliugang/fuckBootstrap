<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
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
.STYLE7 {font-size: 12}

-->
</style>
<style>
		#div{
			position: absolute; 
			left: 200px; 
			top: 20px;
			margin : 0px 0px 50px 0px;
		}
		#select_1,#select_2,#select_3,#select_4,#select_5,#select_6{
			border:1px solid #CCCCCC;
		}
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


<body bgcolor="#f5f5f5">
	<div id="div" style="background:#FFFFFF; color:#666666; width:800px; height:700px; border:1px solid #CCCCCC;">
		<div style="margin : 10px 10px 10px 40px;">
			<h2 style="color:#7ABD54;">修改员工评分条目</h2>
		</div>
		<div style="margin : 10px 10px 10px 80px;">
			<form action="staffScoreItem_modify" method="post" >
  			<input name="staffScoreItemWillModify.staffScoreItemId" type="hidden" 
				value="${staffScoreItemToModify.staffScoreItemId}"/>

			<table width="99%" >
			<tr>
				<td align="right">条目名称：</td><td>
					<input style="border:1px solid #CCCCCC;" name="staffScoreItemWillModify.staffScoreName" type="text" 
						value="${staffScoreItemToModify.staffScoreName}"/>
				</td>
			</tr>
	
			<tr>
				<td align="right">权重：</td><td>
					<input style="border:1px solid #CCCCCC;" name="staffScoreItemWillModify.scoreWeight" type="text" 
						value="${staffScoreItemToModify.scoreWeight}"/></td>
			</tr>
			<tr><td align="right">条目描述：</td><td><br/></td></tr>
			<tr>
				<td></td><td>
					<textarea id="select_1" name="staffScoreItemWillAdd.scoreDescription" rows="3" cols="30" >${staffScoreItemToModify.scoreDescription}
					</textarea>
				</td>
			</tr>
			<tr><td align="right">标准（96~100分）：</td><td><br/></td></tr>
			<tr>
				<td></td><td>
					<textarea id="select_2" name="staffScoreItemWillModify.scoreStandard1" rows="3" cols="30" >${staffScoreItemToModify.scoreStandard1}
					</textarea>
				</td>
			</tr>
			<tr><td align="right">标准（81~95分）：</td><td><br/></td></tr>
			<tr>
				<td></td><td>
					<textarea id="select_3" name="staffScoreItemWillModify.scoreStandard2" rows="3" cols="30" >${staffScoreItemToModify.scoreStandard2}
					</textarea>
				</td>
			</tr>
			<tr><td align="right">标准（61~80分）：</td><td><br/></td></tr>
			<tr>
				<td></td><td>
					<textarea id="select_4" name="staffScoreItemWillModify.scoreStandard3" rows="3" cols="30" >${staffScoreItemToModify.scoreStandard3}
					</textarea>
				</td>
			</tr>
			<tr><td align="right">标准（41~60分）：</td><td><br/></td></tr>
			<tr>
				<td></td><td>
					<textarea id="select_5" name="staffScoreItemWillModify.scoreStandard4" rows="3" cols="30" >${staffScoreItemToModify.scoreStandard4}
					</textarea>
				</td>
			</tr>
			<tr><td align="right">标准（0~40分）：</td><td><br/></td></tr>
			<tr>
				<td></td><td>
					<textarea id="select_6" name="staffScoreItemWillModify.scoreStandard5" rows="3" cols="30" >${staffScoreItemToModify.scoreStandard5}</textarea></td>
			</tr>
			<tr><td><br/></td><td><br/></td></tr>
			<tr>
				<td align="right">
					<input type="submit" value="提交" /></td><td>
					<input type="reset" value="重置" /></td>
			</tr>
			</table>
 
			</form>
		</div>
	</div>
  
</body>
</html>
