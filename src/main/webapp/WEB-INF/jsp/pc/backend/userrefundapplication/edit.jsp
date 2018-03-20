<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>
<ry:binding parentCode="BANK_NAME,APPLICATION_STATUS" bingdingName="banknames,applicationstatus"></ry:binding>

<div class="pageContent">
<form method="post" action="userrefundapplication/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">

<dl style="width: 750px;" >
	<dt><span style="color: red;">*</span>订单编号：</dt>
	<dd style="width: 550px;"><input type="text"   name="orderNum" value="${bean.orderNum}" class="mustFill" title="请输入订单编号" maxlength="30" ><span style="color: gray;"></span></dd>
</dl>

<dl style="width: 750px;" >
	<dt><span style="color: red;">*</span>退款金额：</dt>
	<dd style="width: 550px;"><input type="text"   name="refundMoney" value="${bean.refundMoney}" class="mustFill number" title="请输入退款金额" maxlength="30" ></dd>
</dl>

<dl style="width: 750px;" >
	<dt>退款原因：</dt>
	<dd style="width: 560px;"><input type="text" name="refundReason"  value="${bean.refundReason}" class="mustFill" title="请输入退款原因" ></dd>

</dl>




<dl style="width: 750px;" >
	<dt>退款备注：</dt>
	<dd style="width: 560px;"><input type="text"  name="refundRemark" class="mustFill" value="${bean.refundRemark}" ><span style="color: gray;"></span></dd>

</dl>



</div>
<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
		<input type="hidden" name="userRefundApplicationNum" value="${bean.userRefundApplicationNum}">
		
</form>
</div>
