<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>
<style>
span.error{left: 375px;}
</style>
<style type="text/css">
.pageFormContent .textInput{float: none;}
</style>
<div class="pageContent">
<form method="post" action="couponinfo/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">
<input type="hidden" value="<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" id="date"> 
<dl style="width: 650px;" >

<dt><span style="color: red;">*</span>优惠券名称：</dt>
	<dd style="width: 500px;"><input style="width: 236px;" type="text" title="优惠券名称" value="${bean.couponName }" name="couponName" class="mustFill" maxlength="30"><span style="color: gray;">请在30字以内</span> </dd>
	
</dl>

<dl style="width: 650px;" id="price">
	<dt><span style="color: red;">*</span>优惠金额：</dt>
	<dd style="width: 400px;">
		满<input type="text" id="fullMoney"  name="fullMoney" value="${bean.fullMoney }" style="width: 98px;" class="mustFill number digits" /> 减 <input type="text" id="lessMoney"  name="lessMoney" value="${bean.lessMoney }" style="width: 99px;" class="mustFill number digits" />
	</dd>
</dl>

<dl style="width: 650px;" >
	<dt><span style="color: red;">*</span>有效期：</dt>
	<dd style="width: 380px;">
	<c:if test="${not empty bean.startTime}">
	<input id="startTime" class="mustFill" title="有效期开始时间" style="width: 105px" type="text" name="startTime" value="<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'date\')}'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'date\')}'})"/>
	</c:if>
	<c:if  test="${empty bean.startTime}">
	<input id="startTime" class="mustFill" title="有效期开始时间" style="width: 105px" type="text" name="startTime" value="<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'date\')}'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'date\')}'})"/>
	</c:if>
	
	～
	<input id="endTime" class="mustFill" title="有效期结束时间" style="width: 105px" type="text" name="endTime" value="<fmt:formatDate value="${bean.endTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})"/>
	</dd>
</dl>

 

<%@include file="/WEB-INF/jsp/inc/display_picture_js.jsp"%>

<!--隐藏值-->
<input type="hidden" name="couponId" value="${bean.couponId }"  >
<input type="hidden" name="couponNum" value="${bean.couponNum }"  >
			
</div>

<div class="formBar">
<ul>
	<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="todo()">提交</button></div></div></li>
	<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
</ul>
</div>

</form>
</div>
<script type="text/javascript">

function todo(){
	if(check()){
		var fullMoney=parseInt($("#fullMoney").val());
		var lessMoney=parseInt($("#lessMoney").val());	
		if(fullMoney > lessMoney){
			$("#forms").submit();
		}else{
			alert("优惠卷输入有误！");
		}
		
	}	
	
}
</script>
