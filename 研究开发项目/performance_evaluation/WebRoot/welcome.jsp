<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.chinanetwork.performance.bean.Staff"%>
<%@ page import="java.lang.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
  <title>无标题文档</title>

  <link href="css/reset.css" rel="stylesheet" type="text/css" />
  <!--script type="text/javascript" src="js/jquery.1.4.4.min.js"></script>
  <script type="text/javascript" src="js/lrtk.js"></script-->
  <style type="text/css">
#div1{
  -moz-transform:rotate(180deg); 
  -webkit-transform:rotate(180deg); 
  transform:rotate(180deg); 
  filter:progid:DXImageTransform.Microsoft.BasicImage(rotation=2);
  position: absolute; 
  left: 0px; 
  top: 0px;
}

#div2{
  position: absolute; 
  left: 1px; 
  bottom: 1px;
}

#div_welcome{
  position: absolute; 
  left: 430px; 
  top: 200px;
}

#blink{
  position: absuolute;
  left:430px;
  top:1px;
}

.linear{   
    width:100%;   
    height:600px;   
    FILTER: progid:DXImageTransform.Microsoft.Gradient(gradientType=0,startColorStr=##15A216,endColorStr=#fafafa); /*IE*/   
    background:-moz-linear-gradient(top,#15A216,#fafafa);/*火狐*/   
    background:-webkit-gradient(linear, 0% 0%, 0% 100%,from(#15A216), to(#fafafa));/*谷歌*/   
  
      
    background-image: -webkit-gradient(linear,left bottom,left top,color-start(0, #15A216),color-stop(1, #fafafa));/* Safari & Chrome*/  
    filter:  progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#15A216', endColorstr='#fafafa'); /*IE6 & IE7*/  
    -ms-filter: "progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#15A216', endColorstr='#fafafa')"; /* IE8 */  
  
}  

.jianbian{ 
margin:0px auto; 
background-image: -moz-linear-gradient(top, #699b28, #b9db8c); /*火狐*/
background: -o-linear-gradient(top, #699b28 0%,#b9db8c 100%);/*Opera*/
background-image: -webkit-gradient(linear, left top, right top, color-stop(0, #f3ffe3), color-stop(1,#c9f491)); /*Chrome*/
/*filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#699b28', endColorstr='#b9db8c', GradientType='0'); /*IE*/
FILTER: progid:DXImageTransform.Microsoft.Gradient(gradientType=1,startColorStr=#f3ffe3,endColorStr=#c9f491);
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

function load(){  
    //下面两种方法效果是一样的  
    document.getElementById("tog").click();  
} 
</script>

  <!--
<script type="text/javascript" src="welcome/buffalo.js"></script>
<script type="text/javascript" src="welcome/desktop.js"></script>

<script type="text/javascript" src="welcome/desktopFunc.js"></script>
<script type="text/javascript" src="welcome/desktopMove.js"></script>
<script type="text/javascript" src="welcome/desktopTitle.js"></script>
<script type="text/javascript" src="welcome/Menu000FILE14969133573CM8J.js"></script>
<script type="text/javascript" src="welcome/Menu000FILE15142872563IL2S.js"></script>
<script type="text/javascript" src="welcome/pageCommon.js"></script>
<script type="text/javascript" src="welcome/pictureNews.js"></script>
<script type="text/javascript" src="welcome/prototype.js"></script>
<script type="text/javascript" src="welcome/status.js"></script>
<script type="text/javascript" src="welcome/stm31.js"></script>
<script type="text/javascript" src="welcome/systemMsg.js"></script>
<script type="text/javascript" src="welcome/top.js"></script>
-->
<script type="text/javascript" src="welcome/desktopData.js"></script>
  <link rel="stylesheet" type="text/css" href="welcome/desktop.css" />
  <link rel="stylesheet" type="text/css" href="welcome/menu.css" />
  <link rel="stylesheet" type="text/css" href="welcome/statusbar.css" />
  <link rel="stylesheet" type="text/css" href="welcome/mode.css" />
  <!--link rel="stylesheet" type="text/css" href="welcome/top.css" /-->
<!--
<link rel="stylesheet" type="text/css" href="welcome/menu.css" />
<link rel="stylesheet" type="text/css" href="welcome/mode.css" />
<link rel="stylesheet" type="text/css" href="welcome/statusbar.css" />
<link rel="stylesheet" type="text/css" href="welcome/top.css" />
-->
</head>
<!--
<s:if test="totalRecord>0">
<body onload="load()"></s:if>
<s:else>
<body></s:else>
-->

<body class="jianbian" >
<!--div class="tog_contact">
  <div class="t_con_box">

    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td bgcolor="#f3ffe3">
          <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#c0de98" onmouseover="changeto()"  onmouseout="changeback()">
            <tr>
              <td width="11%" height="26" background="<%=request.getContextPath()%>
                /jsp/images/tab_14.gif" class="STYLE1">
                <div align="center" class="STYLE2 STYLE1">编号</div>
              </td>
              <td width="11%" height="26" background="<%=request.getContextPath()%>
                /jsp/images/tab_14.gif" class="STYLE1">
                <div align="center" class="STYLE2 STYLE1">消息类型</div>
              </td>
              <td width="17%" height="26" background="<%=request.getContextPath()%>
                /jsp/images/tab_14.gif" class="STYLE1">
                <div align="center" class="STYLE2 STYLE1">消息标题</div>
              </td>
              <td width="11%" height="26" background="<%=request.getContextPath()%>
                /jsp/images/tab_14.gif" class="STYLE1">
                <div align="center" class="STYLE2">消息发送者</div>
              </td>
              <td width="14%" height="26" background="<%=request.getContextPath()%>
                /jsp/images/tab_14.gif" class="STYLE1">
                <div align="center" class="STYLE2">附件</div>
              </td>
              <td width="14%" height="26" background="<%=request.getContextPath()%>
                /jsp/images/tab_14.gif" class="STYLE1">
                <div align="center" class="STYLE2">发送时间</div>
              </td>
              <td width="14%" height="26" background="<%=request.getContextPath()%>
                /jsp/images/tab_14.gif" class="STYLE1">
                <div align="center" class="STYLE2">查看</div>
              </td>

            </tr>
            <s:iterator value="unreadList" var="v_sentList" status="st">
              <tr>
                <td height="18" bgcolor="#FFFFFF" class="STYLE2">
                  <div align="center" class="STYLE2 STYLE1">${st.index+1}</div>
                </td>
                <td height="18" bgcolor="#FFFFFF">
                  <div align="center" class="STYLE2 STYLE1">普通</div>
                </td>
                <td height="18" bgcolor="#FFFFFF">
                  <div align="center" class="STYLE2 STYLE1">${v_sentList.title}</div>
                </td>
                <td height="18" bgcolor="#FFFFFF">
                  <div align="center" class="STYLE2 STYLE1">
                    ${v_sentList.sender.department.departmentName}${v_sentList.sender.staffName}
                  </div>
                </td>
                <s:if test="attachment!=null&&attachment.size()!=0">
                  <td height="18" bgcolor="#FFFFFF">
                    <div align="center" class="STYLE2 STYLE1">有</div>
                  </td>
                </s:if>
                <s:else>
                  <td height="18" bgcolor="#FFFFFF">
                    <div align="center" class="STYLE2 STYLE1">无</div>
                  </td>
                </s:else>
                <td height="18" bgcolor="#FFFFFF">
                  <div align="center" class="STYLE2 STYLE1">
                    <s:date name="messageDate" format="yyyy-MM-dd HH:mm:ss"/>
                  </div>
                </td>
                <td height="18" bgcolor="#FFFFFF">
                  <div align="center">
                    <img src="<%=request.getContextPath()%>
                    /jsp/images/037.gif" width="9" height="9" />
                    <span class="STYLE1">[</span>
                    <a href="message_read?messageId=${v_sentList.id}&goBackMark=2">查看</a>
                    <span class="STYLE1">]</span>
                  </div>
                </td>
              </tr>
            </s:iterator>

          </table>
        </td>
      </tr>
    </table>

  </div>
</div>
<div class="tog" id="tog">
  <span>我的通知</span>
</div-->

<!--div id="div1">
<img src="images/welcom.jpg" />
</div>

<div id="div_welcome" align="center">
<img src="images/ww.png" />
</div>

<div id="div2">
<img src="images/welcom.jpg" />
</div-->

<!--div>
<img src="images/info.png"></img>
</div-->

<!--
    <div style="border:1px solid #CCCCCC;margin: 10px 10px 10px 100px; width:800px;">
<br/>

<p style="color:#79A813;font-size:20px;">使用说明：</p>
<ol style="color:#1A648F;font-size:15px;">
<li>&nbsp;1. 请各领导、部门负责人在规定的时间内完成对部门以及员工的绩效打分。</li>
<li>&nbsp;2. 部门打分中，每一条目的分值由此条目中“分数”项规定，未打满分的条目请于此条目的"描述"项写明扣分原因。</li>
<li>&nbsp;3. 个人打分中，每一条目的分值为100分。</li>
<li>
&nbsp;4. 当出现违法乱纪、造成人员伤亡、因播出事故造成不良影响、因传输中断超过维修时限造成不良影响、因人为责任造成公司财产重大损失达万元以上等现象时，公司对该部门的绩效实行一票否决。
</li>
<li>
&nbsp;5. 首次登陆系统请点击
<a href="staff_topasswordmodify.action">此处</a>
修改密码。
</li>
</ol>
<br/>
</div>
<div style="border:1px solid #CCCCCC;margin: 10px 10px 10px 100px; width:800px;">
<br/>
<p style="color:#79A813;font-size:20px;">本次考评提示信息：</p>
<ol style="color:#1A648F;font-size:15px;">
<li>
&nbsp;1. 本次考评是对&nbsp;&nbsp;${performanceEvaluationYear}&nbsp;&nbsp;年&nbsp;&nbsp;${performanceEvaluationMonth}&nbsp;&nbsp;月分的绩效进行考核；
</li>
<li>
&nbsp;2. 本次绩效考核的开始时间为：&nbsp;&nbsp;${performanceEvaluationStartTime}&nbsp;&nbsp;；
</li>
<li>
&nbsp;3. 本次绩效考核的结束时间为：&nbsp;&nbsp;${performanceEvaluationEndTime}&nbsp;&nbsp;；
</li>
<br/>

</div>
-->
<!--div style="magin:0 auto; text-align:center;">

<img src="images/zgyx.jpg" />
</div-->

<div id="Content" style="height:673px;">
    <div style="width:60%; float:left;" id="sub">

      <div functionid="FUNC00012" urlname="绩效考评" recordcount="5" minflg="0" class="DesktopBlock" id="FUNC00012" >
        <div id="Block">
          <div id="BlockHead">
            <table border="0" cellspacing="0" cellpadding="0" height="100%" width="100%">
              <tbody>
                <tr valign="center">
                  <td width="25">
                    <img border="0" width="15" height="15" src="welcome/images/FUNC00012.gif"></td>
                  <td onmousedown="readyDrag('FUNC00012');" style="cursor:move;"style="font-family:楷体;">绩效考评</td>
                  <!--td width="14">
                    <img width="11" height="11" border="0" onclick="minMode('FUNC00012');" alt="最小化" id="FUNC00012Min" src="welcome/images/min.gif"></td>
                  <td width="14">
                    <img width="11" height="11" border="0" onclick="refreshMode('FUNC00012');" alt="刷新" src="welcome/images/refresh.gif"></td>
                  <td width="14">
                    <img width="11" height="11" border="0" onclick="closeMode('FUNC00012');" alt="关闭" src="welcome/images/close.gif"></td>
                  <td width="5"></td-->
                </tr>
              </tbody>
            </table>
          </div>
          <div class="BlockMemo" id="FUNC00012MAX" recordcount="5" urlvalue="Oasp2903" urlname="绩效考评" style="padding: 0px;">
            <div class="BlockDetailByAwoke" style="height:150px;text-align:center;">
              <table border="0" cellspacing="0" cellpadding="5" height="100%" style="width: 60%;margin:auto">
                <tbody>
                  <tr>
                    <td>
                      <!--div class="AwokeButton" onclick="actMoreBtn('Oasp9006', '等待审批');">
                      等待审批 (
                      <span class="AwokeNum">0</span>
                      )
                    </div-->
                    <div class="AwokeButton">
                      <a href="score_listDepartmentNoScoredItem" >部门打分</a>
                      </div></td>
                      <td>
                        <div class="AwokeButton">
                          <a href="score_listDepartmentScoredItem" >部门打分记录</a>
                          </div></td>
                        </tr>
                        <tr>
                          <td>
                            <div class="AwokeButton">
                              <a href="score_listStaffNoScoredItem" >员工打分</a>
                              </div></td>
                              <td>
                                <div class="AwokeButton">
                                  <a href="score_listStaffScoredItem" >员工打分记录</a>
                                  </div></td>
                                </tr>
                              </tbody>
                            </table>
                          </div>
                        </div>
                      </div>
                    </div>

                    <div functionid="FUNC00012" urlname="消息中心" recordcount="5" minflg="0" class="DesktopBlock" id="FUNC00012">
                      <div id="Block">
                        <div id="BlockHead">
                          <table border="0" cellspacing="0" cellpadding="0" height="100%" width="100%">
                            <tbody>
                              <tr valign="center">
                                <td width="25">
                                  <img border="0" width="15" height="15" src="welcome/images/FUNC00012.gif" /></td>
                                <td onmousedown="readyDrag('FUNC00012');" style="cursor:move;">消息中心</td>
                                
                                <td width="5"></td>
                              </tr>
                            </tbody>
                          </table>
                        </div>
                        <div class="BlockMemo" id="FUNC00012MAX" recordcount="5" urlvalue="null" urlname="消息中心" style="padding: 0px;">
                          <div class="BlockDetailByAwoke" style="height:150px;text-align:center;">
                            <table border="0" cellspacing="0" cellpadding="5" height="100%" style="width: 60%;margin:auto">
                              <tbody>
                                <tr>
                                  <td>
                                    <div class="AwokeButton">
                                      <a href="message_write" >编写消息</a>
                                      </div></td>
                                      <td>
                                        <div class="AwokeButton">
                                          <a href="message_listReceived" >收件箱</a>
                                          </div></td>
                                        </tr>
                                        <tr>
                                          <td>
                                            <div class="AwokeButton">
                                              <a href="message_listUnread" >未读消息</a>
                                              </div></td>
                                              <td>
                                                <div class="AwokeButton">
                                                  <a href="message_listSent" >已发送</a>
                                                  </div></td>
                                                </tr>
                                              </tbody>
                                            </table>
                                          </div>
                                        </div>
                                      </div>
                                    </div>

                                   <div urlvalue="Oasp2903" functionid="FUNC00005" urlname="新闻" recordcount="5" minflg="0" class="DesktopBlock" id="FUNC00005">
                                        <div id="Block">
                                          <div id="BlockHead">
                                            <table border="0" cellspacing="0" cellpadding="0" height="100%" width="100%">
                                              <tbody>
                                                <tr valign="center">
                                                  <td width="25">
                                                    <img border="0" width="15" height="15" src="welcome/images/FUNC00005.gif" /></td>
                                                  <td onmousedown="readyDrag('FUNC00005');" style="cursor:move;">新闻</td>
                                                 
                                                  <td width="5"></td>
                                                </tr>
                                              </tbody>
                                            </table>
                                          </div>
                                          <div class="BlockMemo" id="FUNC00005MAX" recordcount="5" urlvalue="Oasp2903" urlname="新闻" style="padding: 0px;">
                                            <div id="BlockDetail" style="height:150px">
                                              <ul>
                                                <li class="DetailLine">
                                                  <div class="DetailLineInfo">
                                                    <img border="0" width="8" height="8" src="welcome/images/list.gif" />
                                                    <a onclick="downloadFile2 ('FILE1112250011N18W');" href="#">公司2011.10.doc</a>
                                                    (70.0KB)
                                                  </div>
                                                  <div class="DetailLineTime">12-25</div>
                                                  <div class="DetailLineName">马文超</div>

                                                  <div></div>
                                                </li>
                                                <li class="DetailLine">
                                                  <div></div>
                                                </li>
                                                <li class="DetailLine">
                                                  <div></div>
                                                </li>
                                                <li class="DetailLine">
                                                  <div></div>
                                                </li>
                                                <li class="EndDetailLine">
                                                  <div></div>
                                                </li>
                                              </ul>
                                            </div>
                                            <div id="BlockFoot"></div>
                                          </div>
                                        </div>
                                      </div>



                                    </div>

                                    <div style="width:40%; float:right;" id="right">

                                       <div style="height:50%;" functionid="FUNC00007" urlname="绩效考核信息" recordcount="5" minflg="0" class="DesktopBlock" id="FUNC00007">
                                      <div id="Block">
                                        <div id="BlockHead">
                                          <table border="0" cellspacing="0" cellpadding="0" height="100%" width="100%">
                                            <tbody>
                                              <tr valign="center">
                                                <td width="25">
                                                  <img border="0" width="15" height="15" src="welcome/images/FUNC00007.gif" /></td>
                                                <td onmousedown="readyDrag('FUNC00007');" style="cursor:move;">绩效考核信息</td>
                                                
                                                <td width="5"></td>
                                              </tr>
                                            </tbody>
                                          </table>
                                        </div>
                                        <div class="BlockMemo" id="FUNC00007MAX" recordcount="5" urlvalue="null" urlname="绩效考核信息" style="/*height: auto;*/ padding: 0px;">
                                          <table border="0" cellspacing="0" cellpadding="0" width="100%" >
                                            <tbody>
                                              <!--tr height="3">
                                                <td colspan="5"></td>
                                              </tr>
                                              <tr height="5">
                                                <td width="3px"></td>
                                                <td width="5" class="AdageTopLeft"></td>
                                                <td class="BlockDetailByAdage"></td>
                                                <td width="5" class="AdageTopRight"></td>
                                                <td width="3"></td>
                                              </tr-->
                                              <tr>
                                                <!--td width="3px"></td>
                                                <td class="BlockDetailByAdage"></td-->
                                                <td class="BlockDetailByAdage" align="left" style="height:150px;padding:6px;"> <font size="3" face="黑体"><p style="color:#79A813;font-size:20px;">本次考评提示信息：</p>
                                                    <ul>
                                                      <li>
                                                        &nbsp;1. 本次考评是对&nbsp;&nbsp;${performanceEvaluationYear}&nbsp;&nbsp;年&nbsp;&nbsp;${performanceEvaluationMonth}&nbsp;&nbsp;月份的绩效进行考核；
                                                      </li>
                                                      <li>
                                                        &nbsp;2. 本次绩效考核的开始时间为：&nbsp;&nbsp;${performanceEvaluationStartTime}&nbsp;&nbsp;；
                                                      </li>
                                                      <li>
                                                        &nbsp;3. 本次绩效考核的结束时间为：&nbsp;&nbsp;${performanceEvaluationEndTime}&nbsp;&nbsp;；
                                                      </li>
                                                      </ul>
                                                      <br/></font> 
                                                  </td>
                                                  <!--td class="BlockDetailByAdage"></td>
                                                  <td width="3px"></td-->
                                                </tr>
                                                <!--tr height="5">
                                                  <td width="3px"></td>
                                                  <td width="5" class="AdageBottomLeft"></td>
                                                  <td class="BlockDetailByAdage"></td>
                                                  <td width="5" class="AdageBottomRight"></td>
                                                  <td width="3"></td>
                                                </tr>
                                                <tr>
                                                  <td colspan="5" height="3"></td>
                                                </tr-->
                                              </tbody>
                                            </table>
                                          </div>
                                        </div>
                                      </div>

                                      <div functionid="FUNC00007" urlname="使用说明" recordcount="5" minflg="0" class="DesktopBlock" id="FUNC00007">
                                        <div id="Block">
                                          <div id="BlockHead">
                                            <table border="0" cellspacing="0" cellpadding="0" height="100%" width="100%">
                                              <tbody>
                                                <tr valign="center">
                                                  <td width="25">
                                                    <img border="0" width="15" height="15" src="welcome/images/FUNC00007.gif" /></td>
                                                  <td onmousedown="readyDrag('FUNC00007');" style="cursor:move;">使用说明</td>
                                                  
                                                  <td width="5"></td>
                                                </tr>
                                              </tbody>
                                            </table>
                                          </div>
                                          <div class="BlockMemo" id="FUNC00007MAX" recordcount="5" urlvalue="null" urlname="使用说明" style="height: auto; padding: 0px;">
                                            <table border="0" cellspacing="0" cellpadding="0" width="100%" >
                                              <tbody>
                                                <!--tr height="3">
                                                  <td colspan="5"></td>
                                                </tr>
                                                <tr height="5">
                                                  <td width="3px"></td>
                                                  <td width="5" class="AdageTopLeft"></td>
                                                  <td class="BlockDetailByAdage"></td>
                                                  <td width="5" class="AdageTopRight"></td>
                                                  <td width="3"></td>
                                                </tr-->
                                                <tr>
                                                  <!--td width="3px"></td>
                                                  <td class="BlockDetailByAdage"></td-->
                                                  <td class="BlockDetailByAdage" align="left" style="height:376px;pading:6px;"> <font size="3" face="黑体">
                                                    <ul>
                                                    <li>&nbsp;1. 请各领导、部门负责人在规定的时间内完成对部门以及员工的绩效打分。</li>
                                                      <li>&nbsp;2. 部门打分中，每一条目的分值由此条目中“分数”项规定，未打满分的条目请于此条目的"描述"项写明扣分原因。</li>
                                                      <li>&nbsp;3. 个人打分中，每一条目的分值为100分。</li>
                                                      <li>
                                                        &nbsp;4. 当出现违法乱纪、造成人员伤亡、因播出事故造成不良影响、因传输中断超过维修时限造成不良影响、因人为责任造成公司财产重大损失达万元以上等现象时，公司对该部门的绩效实行一票否决。
                                                      </li>
                                                      <li>
                                                        &nbsp;5. 首次登陆系统请点击
                                                        <a href="staff_topasswordmodify.action">此处</a>
                                                        修改密码。
                                                      </li>
                                                    </ul></font> 
                                                  </td>
                                                  <!--td class="BlockDetailByAdage"></td>
                                                  <td width="3px"></td-->
                                                </tr>
                                                <!--tr height="5">
                                                  <td width="3px"></td>
                                                  <td width="5" class="AdageBottomLeft"></td>
                                                  <td class="BlockDetailByAdage"></td>
                                                  <td width="5" class="AdageBottomRight"></td>
                                                  <td width="3"></td>
                                                </tr>
                                                <tr>
                                                  <td colspan="5" height="3"></td>
                                                </tr-->
                                              </tbody>
                                            </table>
                                          </div>
                                        </div>
                                      </div>

                                      

                                    </div>
                                    <p></p>
                                    <p></p>
                                    <p></p>
                                  </div>



          <img src="images/zgyx.gif" style="margin:auto;display:block;border:1px solid black;"/> 


</body>
</html>