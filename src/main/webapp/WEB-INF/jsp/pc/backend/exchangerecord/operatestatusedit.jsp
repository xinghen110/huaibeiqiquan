<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>
<div class="pageContent">
<form method="post" action="exchangerecord/operateStatus" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this, navTabAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">

	<dl style="width: 550px;" >
			<dt>备注：</dt>
				<dd style="width: 400px">
				<textarea  name="operateRemark" title="备注"   style="width: 280px;"></textarea>
				</dd>
	
		</dl>
		<input type="hidden" name="userExchangeRecordNum" value="${bean.userExchangeRecordNum}">
</div>
<div class="formBar">
<ul>
	<li><div class="buttonActive"><div class="buttonContent"><button type="submit" >保存</button></div></div></li>
	<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
</ul>
</div>
</form>
</div>