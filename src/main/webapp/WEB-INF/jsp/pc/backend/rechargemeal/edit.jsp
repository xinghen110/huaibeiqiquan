<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>

<div class="pageContent">
<form method="post" action="rechargemeal/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">


<!--<dl style="width: 550px;" >
	<dt><span style="color: red;">*</span>登陆账号：</dt>
	<dd>
	<c:if test="${not empty user.loginName}">${user.loginName}</c:if>
	<c:if test="${empty user.loginName}">
		<input type="text" name="userNum" value="${user.loginName }" class="mustFill" title="请填写">
	</c:if>
	</dd>
</dl>

--><dl style="width: 550px;" >
	<dt><span style="color: red;">*</span>充值金额：</dt>
	<dd>
		<input type="text" name="amount" value="${bean.amount }"    class="mustFill number"  title="请填写数字">
	</dd>
</dl>
<dl style="width: 550px;" >
	<dt><span style="color: red;">*</span>赠送金额：</dt>
	<dd>
		<input type="text" name="presentPrice" value="${bean.presentPrice }"   class="mustFill number"  title="请填写数字">
	</dd>
</dl>
 

<!--<dl style="width: 550px;" >
	<dt>备注：</dt>
	<dd>
		<textarea rows="" cols="" style="width: 300px;height: 80px;" name="remark">${bean.remark }</textarea>
	</dd>
</dl>
-->
<input type="hidden" name="rechargeMealNum" value="${bean.rechargeMealNum}"></input>
</div>

<div class="formBar">
<ul>
	<li>
	<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="todo()">保存</button></div></div></li>
	<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
</ul>
</div>

</form>
</div>
<script type="text/javascript">

function todo(){
		if(check())
		$("#forms").submit();
}
</script>
