<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>table切换</title>

<script language="javascript" src="js/table/common.js"></script>
<link  type="text/css" rel="stylesheet" href="js/table/style.css" />
</head>

<style>
.new_tab{border-collapse: collapse; margin-top: 25px;margin-left: 100px;margin-bottom: 20px}
.new_tab h2{text-align:center;font-weight:10 }
.new_tab tr td{height:30px;border:1px solid #e0e0e0; text-indent:10px}
.investment div{background:#f5f5f5;border-top:2px soild #e0e0e0}
.on{background:#f5f5f5;border-top:2px solid red}
.cue{background:#f5f5f5;}
</style>
<div class="investment_f" style="width:850px;height:492px;">
  <div class="investment_con" style="border:none;width:830px;height:460px;overflow: auto;">
    <div class="investment_con_list" style="display:block;">
   
	    <table class="new_tab">
	         <tr>
			     <td colspan="4"  class="cue"><h2>公告详情</h2></td>
		     </tr>
		     <tr>
			     <td width="112" align="right" class="cue">标题：</td>
			     <td colspan="3" width="200">${bean.title }</td> 
			 </tr>
		    
		     
		     <tr>
		     	<td align="right">接受类型：</td>
		     	<td><c:if test="${bean.receiveType==1}">全系统</c:if>
		     		<c:if test="${bean.receiveType==2}">店铺用户</c:if>
		     		<c:if test="${bean.receiveType==3}">物流用户</c:if>
		     	</td>
		     </tr>
				
		     <tr>
			     <td align="right" class="cue">创建时间时间：</td>
			     <td><ry:formatDate date="${bean.createTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
			     
			     
		     </tr>
		     
		     <tr>
			     <td align="right" class="cue" >内容：</td>
			     <td colspan="3" style="width: 500px;line-height: 20px;">${bean.content }</td>
			     
		     </tr>
		     
		     <tr>
		     	<td align="right">状态：</td>
		     	<td><c:if test="${bean.status==1}">开启</c:if>
		     		<c:if test="${bean.status==2}">关闭</c:if>
		     		
		     	</td>
		     </tr>
		     
	    </table>
	 </div>
  </div>
</div>

<div class="formBar">
	<ul>
		<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
	</ul>
</div>