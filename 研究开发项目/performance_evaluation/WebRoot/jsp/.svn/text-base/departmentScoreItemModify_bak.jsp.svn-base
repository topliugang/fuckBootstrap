<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>评分条目修改</title>
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
		}
		#select_c,#select_d,#select_m{
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
<div id="div" style="background:#FFFFFF; color:#666666; width:800px; height:590px; border:1px solid #CCCCCC;">
	<div style="margin : 10px 10px 10px 40px;">
		<h2 style="color:#7ABD54;">修改评分条目信息</h2>
	</div>
	<div style="margin : 10px 10px 10px 80px;">
		<form action="departmentScoreItem_modify" method="post">
  		<input name='departmentScoreItemWillModify.departmentScoredItemId' type="hidden" 
				value="${departmentScoreItemToModify.departmentScoredItemId}"/>

 		<table width="99%" >
			<tr>
				<td align="right">条目类别：</td><td>
					<s:select id="select_c" theme ="simple" list="categories" listValue="departmentScoreCategoryName"
						listKey="departmentScoreCategoryId" name="categoryId" value="%{departmentScoreItemToModify.scoreItemCategory.departmentScoreCategoryId}">
					</s:select>
				</td>
			</tr>
	
			<tr>
				<td align="right">条目名称：</td><td>
					<input style="border:1px solid #CCCCCC;" name='departmentScoreItemWillModify.departmentScoreName' type="text" 
						value="${departmentScoreItemToModify.departmentScoreName}"/>
				</td>
			</tr>
			<tr><td align="right">条目描述：</td><td><br/></td></tr>
			<tr>
				<td></td><td>
					<textarea style="border:1px solid #CCCCCC;" rows="5" cols="40" name='departmentScoreItemWillModify.scoreDescription'>${departmentScoreItemToModify.scoreDescription}</textarea>
				</td>
			</tr>
	
			<tr>
				<td align="right">分值：</td><td>
					<input style="border:1px solid #CCCCCC;" name='departmentScoreItemWillModify.scoreValue' type="text" 
					value="${departmentScoreItemToModify.scoreValue}"/>
				</td>
			</tr>
	
			<tr>
				<td align="right">基准值：</td><td>
					<input style="border:1px solid #CCCCCC;" name='departmentScoreItemWillModify.scoreReference' type="text" 
						value="${departmentScoreItemToModify.scoreValue}"/>
				</td>
			</tr>
			<tr><td align="right">计算方法：</td><td><br/></td></tr>
			<tr>
				<td></td>
				<td>
					<textarea style="border:1px solid #CCCCCC;" rows="5" cols="40" name='departmentScoreItemWillModify.scoreCalculationMethod'>${departmentScoreItemToModify.scoreCalculationMethod}</textarea>
				</td>
			</tr>
	
			<tr>
				<td align="right">所属部门：</td>
				<td>
					<s:select id="select_d" theme ="simple" list="departments" listValue="departmentName"
						listKey="departmentId" name="departmentId"
						value="%{departmentScoreItemToModify.department.departmentId}">
					</s:select>
				</td>
			</tr>
			<tr><td align="right">数据来源部门：</td><td><br/></td></tr>
			<tr>
				<td></td>
				<td>
					<s:select id="select_m" theme ="simple" list="departments" listValue="departmentName"
						listKey="departmentId" name="departmentsFromId" multiple="true"
						value="%{departmentsFromId}">
					</s:select>
				</td>
			</tr>
			<tr><td><br/></td><td><br/></td></tr>
			<tr>
				<td align="right">
					<input type="submit" value="提交" /></td>
				<td>
					<input type="reset" value="重置" />
				</td>
			</tr>
		</table>
 		</form>
	</div>
</div>
 	 
</body>
</html>
