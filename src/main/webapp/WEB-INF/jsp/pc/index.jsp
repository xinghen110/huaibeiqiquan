<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html >
<html >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	<link href="css/centen.css" rel="stylesheet" type="text/css">
	<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
	<title>金点护航</title>
	<%@include file="/WEB-INF/jsp/inc/base-dwz.jsp" %>
	<link rel="shortcut icon" href="img/favicon.png" type="image/x-icon">
	 <ry:binding type="2"></ry:binding>
	  
</head>
<body scroll="no">
    <!-- 整个index主页显示区域 -->
	<div id="layout">
		<!-- 头部-->
		<%@include file="/WEB-INF/jsp/inc/header.jsp" %>
		<!-- 左边菜单栏 -->
		<%@include file="/WEB-INF/jsp/inc/left.jsp" %>
		
		<!-- main区域 -->
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
						    <li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
				    <li><a href="javascript:;">我的主页</a></li>
				</ul>
				<!-- 数据显示面板 -->
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox" >
					    <!-- 面板显示区域的头部 -->
						<div class="accountInfo" style="height: auto;background: none" >
					
					 
					</div>
					
				</div>
			</div>
		</div>
	</div>
	
	<!-- footer底部信息 -->
	<%--<div id="footer">Copyright &copy; 2017镇江满茂有限公司 <!-- Tel：400-1808871 --></div>--%>
	

</body>

</html>