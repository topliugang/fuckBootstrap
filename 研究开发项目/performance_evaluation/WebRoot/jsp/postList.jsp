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

function gopage(){
	var input=document.getElementById("userPage");
	var pageNo=input.value;
	if(pageNo==null||pageNo==''){
		alert("不能为空");
	}
	if(pageNo>${totalPage}){
		alert("不能超过总页数");
	}
	if(pageNo==0){
		alert("不能为0");
	}
	if(!pageNo.match("^[0-9]*$")){
		alert("必须是正整数");
	}
	if(pageNo>=1){
		if(pageNo<=${totalPage}){
			window.location.href="post_list?pageNo="+pageNo;
		}
	}
}
</script>
</head>
<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="<%=request.getContextPath()%>/jsp/images/tab_03.gif" width="15" height="30" /></td>
        <td width="1101" background="<%=request.getContextPath()%>/jsp/images/tab_05.gif"><img src="<%=request.getContextPath()%>/jsp/images/311.gif" width="16" height="16" /> <span class="STYLE4">职位管理</span></td>
        <td width="281" background="<%=request.getContextPath()%>/jsp/images/tab_05.gif"><table border="0" align="right" cellpadding="0" cellspacing="0">
            <tr>
              <td width="60">&nbsp;</td>
              <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"><img src="<%=request.getContextPath()%>/jsp/images/001.gif" width="14" height="14" /></div></td>
                    <td class="STYLE1"><div align="center"><a href="post_toAdd.action">新增</a></div></td>
                  </tr>
              </table></td>
              <td width="60">&nbsp;</td>
              </tr>
        </table></td>
        <td width="14"><img src="<%=request.getContextPath()%>/jsp/images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="<%=request.getContextPath()%>/jsp/images/tab_12.gif">&nbsp;</td>
        <td bgcolor="#f3ffe3"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#c0de98" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td width="11%" height="26" background="<%=request.getContextPath()%>/jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">序号</div></td>
            <td width="11%" height="26" background="<%=request.getContextPath()%>/jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">职位编号</div></td>
            <td width="17%" height="26" background="<%=request.getContextPath()%>/jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">职位名称</div></td>
            <td width="11%" height="26" background="<%=request.getContextPath()%>/jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">编辑</div></td>
            <td width="14%" height="26" background="<%=request.getContextPath()%>/jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">删除</div></td>
          </tr>
		  <s:iterator value="postsToList" var="v_post" status="st">
          <tr>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1">${st.index+1}</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${v_post.postId}</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${v_post.postName}</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center"><img src="<%=request.getContextPath()%>/jsp/images/037.gif" width="9" height="9" /><span class="STYLE1"> [</span><a href="post_toModify?postIdToModify=${v_post.postId}">编辑</a><span class="STYLE1">]</span></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center"><span class="STYLE2"><img src="<%=request.getContextPath()%>/jsp/images/010.gif" width="9" height="9" /> </span><span class="STYLE1">[</span><a href="post_delete?postIdToDelete=${v_post.postId}">删除</a><span class="STYLE1">]</span></div></td>
          </tr>
          </s:iterator>
          
        </table></td>
        <td width="9" background="<%=request.getContextPath()%>/jsp/images/tab_16.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="<%=request.getContextPath()%>/jsp/images/tab_20.gif" width="15" height="29" /></td>
        <td background="<%=request.getContextPath()%>/jsp/images/tab_21.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="25%" height="29" nowrap="nowrap"><span class="STYLE1">共 ${totalRecorder}条纪录，当前第${pageNo}/${totalPage}页，每页${pageSize}条纪录</span></td>
            <td width="75%" valign="top" class="STYLE1"><div align="right">
              <table width="352" height="20" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="62" height="22" valign="middle"><div align="right"><a href="post_list?pageNo=1"><img src="<%=request.getContextPath()%>/jsp/images/first.gif" width="37" height="15" /></a></div></td>
                  <s:if test="pageNo>1">
                  <td width="50" height="22" valign="middle"><div align="right"><a href="post_list?pageNo=${pageNo-1}"><img src="<%=request.getContextPath()%>/jsp/images/back.gif" width="43" height="15" /></a></div></td>
                  </s:if>
                  <s:if test="pageNo<totalPage">
                  <td width="54" height="22" valign="middle"><div align="right"><a href="post_list?pageNo=${pageNo+1}"><img src="<%=request.getContextPath()%>/jsp/images/next.gif" width="43" height="15" /></a></div></td>
                  </s:if>
                  <td width="49" height="22" valign="middle"><div align="right"><a href="post_list?pageNo=${totalPage}"><img src="<%=request.getContextPath()%>/jsp/images/last.gif" width="37" height="15" /></a></div></td>
                  <td width="59" height="22" valign="middle"><div align="right">转到第</div></td>
                  <td width="25" height="22" valign="middle"><span class="STYLE7">
                    <input id="userPage" name="textfield" type="text" class="STYLE1" style="height:10px; width:25px;" size="5" />
                  </span></td>
                  <td width="23" height="22" valign="middle">页</td>
                  <td width="30" height="22" valign="middle"><a href="javascript:gopage()"><img src="<%=request.getContextPath()%>/jsp/images/go.gif" width="37" height="15" /></a></td>
                </tr>
              </table>
            </div></td>
          </tr>
        </table></td>
        <td width="14"><img src="<%=request.getContextPath()%>/jsp/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
