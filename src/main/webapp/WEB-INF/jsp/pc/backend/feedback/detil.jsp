<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>

 <link rel="stylesheet" href="select/css/chose.css"/>
 <script type="text/javascript" src="select/chosen.jquery.min.js"></script> 
<div class="pageContent">

<div class="pageFormContent nowrap" layoutH="60">
	
		<dl  style="width: 400px;">
			<dt ><span style="color: red;">*</span>反馈内容：</dt>
			<dd>${bean.content}</dd>
		</dl>
</div>
<div class="formBar">
<ul>
	<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
	
</ul>
</div>
</div>
