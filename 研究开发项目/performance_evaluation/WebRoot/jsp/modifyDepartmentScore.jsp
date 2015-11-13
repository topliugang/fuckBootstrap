<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
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
	<div align="center">
  		
  			<table width="100%"  border="0" cellspacing="1"  cellpadding="1">
  				<tr>
  			<td width="11%" height="26" background="<%=request.getContextPath()%>/jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">序号</div></td>
            <td width="17%" height="26" background="<%=request.getContextPath()%>/jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">部门编号</div></td>
            <td width="16%" height="26" background="<%=request.getContextPath()%>/jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">部门名称</div></td>
            <td width="11%" height="26" background="<%=request.getContextPath()%>/jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">部门绩效</div></td>
            <td width="11%" height="26" background="<%=request.getContextPath()%>/jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">绩效年份</div></td>
            <td width="11%" height="26" background="<%=request.getContextPath()%>/jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">绩效月份</div></td>
            <td width="11%" height="26" background="<%=request.getContextPath()%>/jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">绩效说明</div></td>
            <td width="11%" height="26" background="<%=request.getContextPath()%>/jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">操作</div></td>
  				</tr>
  			<s:iterator value="departmentScores" var="departmentScore" status="st">
  			<tr>
  			<form action="statisticalQuery_ModifyDepartmentScore?departmentName=<s:property value="department.departmentName"/>" method="post">
  			<input name="departmentIdStr" type="hidden" value="<s:property value="department.departmentId"/>"/>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1">${st.index+1}</div></td>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1"><s:property value="department.departmentId"/></div></td>
           	<td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1"><s:property value="department.departmentName"/></div></td>
           	<td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1"><input name="scoreStr" type="text" value="<s:property value="score"/>"/></div></td>
           	<td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1"><s:property value="year"/></div></td>
           	<td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1"><s:property value="month"/></div></td>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1"><input name="instruction" type="text" value="<s:property value="instruction"/>"/></div></td>
           	<td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1"><input name="" type="submit" value="修改"/></div></td>
           	</form>
          	</tr>
         
  			</s:iterator>
  			</table>
  			 <form name="departmentScore" action="statisticalQuery_goBack" method="post">
  			 	<div align="right">
					<span style="font-size:12px;color:red;"><s:property value="errorInfo"/></span>
				</div>
  				<input name="" type="submit" value="返回"/>
  	 		</form>
	</div>
</body>
</html>
