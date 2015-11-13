<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.chinanetwork.performance.bean.FuncpageCategory"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.chinanetwork.performance.bean.Funcpage"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>管理页面</title>

		<script src="js/prototype.lite.js" type="text/javascript"></script>
		<script src="js/moo.fx.js" type="text/javascript"></script>
		<script src="js/moo.fx.pack.js" type="text/javascript"></script>
		<!-- CCFF99 -->
		<style>
body {
	font: 12px Arial, Helvetica, sans-serif;
	color: #000;
	background-color: #F3FFE3;
	margin: 0px;
}

#container {
	width: 182px;
}

H1 {
	font-size: 12px;
	margin: 0px;
	width: 182px;
	cursor: pointer;
	height: 30px;
	line-height: 20px;
}

H1 a {
	display: block;
	width: 182px;
	color: #000;
	height: 30px;
	text-decoration: none;
	moz-outline-style: none;
	background-image: url(images_grey/menu_bgs_bak.gif);
	background-repeat: no-repeat;
	line-height: 30px;
	text-align: center;
	margin: 0px;
	padding: 0px;
}

.content {
	width: 182px;
	height: 26px;
}

.MM ul {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	display: block;
}

.MM li {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	list-style-type: none;
	display: block;
	text-decoration: none;
	height: 26px;
	width: 182px;
	padding-left: 0px;
}

.MM {
	width: 182px;
	margin: 0px;
	padding: 0px;
	left: 0px;
	top: 0px;
	right: 0px;
	bottom: 0px;
	clip: rect(0px, 0px, 0px, 0px);
}

.MM a:link {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(images_grey/menu_bg1.gif );
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}

.MM a:visited {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(images_grey/menu_bg1.gif );
	background-repeat: no-repeat;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}

.MM a:active {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(images_grey/menu_bg1.gif );
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}

.MM a:hover {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	font-weight: bold;
	color: #006600;
	background-image: url(images_grey/menu_bg2.gif );
	background-repeat: no-repeat;
	text-align: center;
	display: block;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}
</style>

<script type="text/javascript">  
       function leftsub(sb){  
         alert("right");  
         parent.right.location.href=sb;  
         //right.do返回right.jsp  
       }  
  
    </script>  
</head>

<body>
		<%--<%
			HttpSession sessions = request.getSession();
			List<Funcpage> funcpages = (List<Funcpage>) session.getAttribute("funcpages");
			if( funcpages != null )
			{
				List<FuncpageCategory> categorys = new ArrayList<FuncpageCategory>();
				for( Funcpage funcpage : funcpages )
				{
					boolean contains = false;
					for( FuncpageCategory category : categorys )
					{
						if(category.equals(funcpage.getCategory()))
						{
							contains = true;
							break;
						}
					}
					if( !contains )
						categorys.add(funcpage.getCategory());
				}
		%>
		<table width="100%" height="280" border="0" cellpadding="0"
			cellspacing="0" bgcolor="#66CC33">
			<tr>
				<td width="182" valign="top">
					<div id="container">
						<%
				for(FuncpageCategory category : categorys)
				{
					out.println("<h1 class=\"type\">");
					out.println("<a href=\"javascript:void(0)\">"+ category.getName() +"</a>");
					out.println("</h1>");
					out.println("<div class=\"content\">");
					out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
					out.println("<tr>");
					out.println("<td>");
					%>
										<!-- out.println("<img src=\"<\%=request.getContextPath()\%>/images_grey/menu_topline.gif\" width=\"182\" height=\"5\" />"); -->
										<img
											src="<%=request.getContextPath()%>/images_grey/menu_topline.gif"
											width="182" height="5" />
										<%
					out.println("</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<ul class=\"MM\">");
					for(Funcpage funcpage : funcpages)
					{
						if( funcpage.getCategory().equals(category) )
							out.println("<li><a href=\"" +funcpage.getAction()+ "\" target=\"main\">" +funcpage.getName()+ "</a></li>");
					}
					out.println("</ul>");
					out.println("</div>");
				}
			}
				%>
						--%>
		<table width="100%" height="280" border="0" cellpadding="0" cellspacing="0" bgcolor="#F3FFE3">
			<tr>
				<td width="182" valign="top">
					<div id="container">
						<s:iterator  value="categoryList" var="cgy" id="outter">
						<h1 class="type">
							<a href="javascript:void(0)"><s:property value="name"/></a>
						</h1>
						<div class="content">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
										<img src="<%=request.getContextPath()%>/images_grey/menu_topline.gif" width="182" height="5" />
									</td>
								</tr>
							</table>
							<ul class="MM">
								<s:iterator value="funcpageList" var="funcpage" id="inner">
									<s:if test="#inner.category.name==#outter.name">
									<li>
									<a href="<s:property value="#inner.action"/>" target="caonimama"><s:property value="#inner.name" /></a>
									<!--button onclick="leftsub(<s:property value="#inner.action"/>)" type="submit"><s:property value="#inner.name" /></button--> 
									</li>
									 
									</s:if>
								</s:iterator>
							</ul>
						</div>
						</s:iterator>
					</div>
					<script type="text/javascript">
						var contents = document.getElementsByClassName('content');
						var toggles = document.getElementsByClassName('type');

						var myAccordion = new fx.Accordion(toggles, contents, {
							opacity : true,
							duration : 400
							});
						myAccordion.showThisHideOpen(contents[0]);
					</script>
					
					</td>
				</tr>
			</table>
						<%--<!-- 


						<h1 class="type">
							<a href="javascript:void(0)">网站常规管理</a>
						</h1>
						<div class="content">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
										<img src="<%=request.getContextPath()%>/images_grey/menu_topline.gif" width="182" height="5" />
									</td>
								</tr>
							</table>
							<ul class="MM">
								<%
								for(Funcpage funcpage : funcpages)
								{
									out.println("<li><a href=\"" +funcpage.getAction()+ "\" target=\"main\">" +funcpage.getName()+ "</a></li>");
								}
							 %>
							</ul>
						</div>





						<h1 class="type">
							<a href="javascript:void(0)">网站常规管理</a>
						</h1>
						<div class="content">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
										<img src="<%=request.getContextPath()%>/images_grey/menu_topline.gif" width="182" height="5" />
									</td>
								</tr>
							</table>
							<ul class="MM">
								<li>
									<a href="http://www.865171.cn" target="main">基本设置</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">邮件设置</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">广告设置</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">114增加</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">114管理</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">联系方式</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">汇款方式</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">增加链接</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">管理链接</a>
								</li>
							</ul>
						</div>
						<h1 class="type">
							<a href="javascript:void(0)">栏目分类管理</a>
						</h1>
						<div class="content">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
										<img src="<%=request.getContextPath()%>/images_grey/menu_topline.gif" width="182" height="5" />
									</td>
								</tr>
							</table>
							<ul class="MM">
								<li>
									<a href="http://www.865171.cn" target="main">信息分类</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">信息类型</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">资讯分类</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">地区设置</a>
								</li>
								<li>
									<a target="main" href="http://www.865171.cn">市场联盟</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">商家类型</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">商家星级</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">商品分类</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">商品类型</a>
								</li>
							</ul>
						</div>
						<h1 class="type">
							<a href="javascript:void(0)">栏目内容管理</a>
						</h1>
						<div class="content">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
										<img src="<%=request.getContextPath()%>/images_grey/menu_topline.gif" width="182" height="5" />
									</td>
								</tr>
							</table>
							<ul class="MM">
								<li>
									<a href="http://www.865171.cn" target="main">信息管理</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">张贴管理</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">增加商家</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">管理商家</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">发布资讯</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">资讯管理</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">市场联盟</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">名片管理</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">商城管理</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">商品管理</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">商城留言</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">商城公告</a>
								</li>
							</ul>
						</div>
						<h1 class="type">
							<a href="javascript:void(0)">注册用户管理</a>
						</h1>
						<div class="content">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
										<img src="<%=request.getContextPath()%>/images_grey/menu_topline.gif" width="182" height="5" />
									</td>
								</tr>
							</table>
							<ul class="MM">
								<li>
									<a href="http://www.865171.cn" target="main">会员管理</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">留言管理</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">回复管理</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">订单管理</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">举报管理</a>
								</li>
								<li>
									<a href="http://www.865171.cn" target="main">评论管理</a>
								</li>
							</ul>
						</div>
						
						 
					</div>
					<h1 class="type">
						<a href="javascript:void(0)">其它参数管理</a>
					</h1>
					<div class="content">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<img src="<%=request.getContextPath()%>/images_grey/menu_topline.gif" width="182" height="5" />
								</td>
							</tr>
						</table>
						<ul class="MM">
							<li>
								<a href="http://www.865171.cn" target="main">管理设置</a>
							</li>
							<li>
								<a href="http://www.865171.cn" target="main">主机状态</a>
							</li>
							<li>
								<a href="http://www.865171.cn" target="main">攻击状态</a>
							</li>
							<li>
								<a href="http://www.865171.cn" target="main">登陆记录</a>
							</li>
							<li>
								<a href="http://www.865171.cn" target="main">运行状态</a>
							</li>
						</ul>
					</div>
					
					-->--%>
	</body>
</html>
