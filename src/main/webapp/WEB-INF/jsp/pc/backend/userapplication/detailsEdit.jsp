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
			     <td colspan="4"  class="cue"><h2>提现详情</h2></td>
		     </tr>
			 
		     <tr>
				 <td  width="112" align="right" class="cue">申请人姓名：</td>
				 <td  width="200">${bean.userName}</td>

			     <td  width="116" align="right" class="cue">申请人帐号：</td>
			     <td  width="200">${bean.accountNumber }</td>
		     </tr>
		     
		     <tr>
			     <td width="100" align="right" class="cue">金额：</td>
			     <td  width="200">${bean.money}</td> 
			     <td  width="100" align="right" class="cue">提现去向：</td>
			     <td  width="200"><ry:show parentCode="BANK_NAME" itemCode="${bean.bankName}"></ry:show></td>
		     </tr>
		     
		     <tr>
			     <td width="100" align="right" class="cue">状态：</td>
			     <td  width="200"><ry:show parentCode="APPLICATION_STATUS" itemCode="${bean.status}"></ry:show></td> 
			     <td  width="100" align="right" class="cue">申请时间：</td>
			     <td  width="200"><ry:formatDate date="${bean.createTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
		     </tr>
		     <c:if test="${not empty bean.handleTime}">
		     <tr>
			     <td width="100" align="right" class="cue">处理人：</td>
			     <td  width="200">${user.nickName }</td> 
			     <td  width="100" align="right" class="cue">处理时间：</td>
			     <td  width="200"><ry:formatDate date="${bean.handleTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
		     </tr>
		     </c:if>
		     <c:if test="${bean.status==-1}">
		     <tr>
		     <td width="100" align="right" class="cue">失败原因：</td>
		     <td width="200" colspan="3">${bean.handleRemark }</td>
		     </tr>
		     </c:if>
			
	    </table>
	 </div>
  </div>
</div>

<div class="formBar">
	<ul>
		<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
	</ul>
</div>
