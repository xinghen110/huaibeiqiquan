<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>

<div class="pageContent">
<form method="post" action="payInfo/saveOrUpdate?payType=${payType}" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">

<div class="pageFormContent nowrap" layoutH="60">

<dl  style="width: 750px;">
	
		<dt><span style="color: red;">*</span>账号：</dt>
		<dd><input type="text" name="partner" class="mustFill number" title="请输入数字"  value="${bean.partner}"></dd>
</dl>
<dl></dl>

<dl style="width: 750px;" >
	<dt><span style="color: red;">*</span>公匙：</dt>
	
	<dd>
		<textarea type="text"  style="width: 900px;height:100px" name="publicKey"  class="mustFill"  title="请填写内容">${bean.publicKey}</textarea>
	</dd>
</dl>
<dl style="height:100px;"></dl>
<dl style="width: 750px;" >
	<dt><span style="color: red;">*</span>安卓私匙：</dt>
	
	<dd>
		<textarea type="text" style="width: 900px;height:180px" name="privateKeyAndroid"  class="mustFill"  title="请填写内容">${bean.privateKeyAndroid}</textarea>
	</dd>
</dl>

<dl style="height:180px;"></dl>
<dl style="width: 750px;" >
	<dt><span style="color: red;">*</span>ios私匙：</dt>
	
	<dd>
		<textarea type="text" style="width: 900px;height:180px" name="privateKeyIos"  class="mustFill"  title="请填写内容">${bean.privateKeyIos}</textarea>
	</dd>
</dl>
<dl style="height:180px;"></dl>
<dl style="width: 750px;" >
	<dt><span style="color: red;">*</span>公共key：</dt>
	
	<dd>
		<textarea type="text" style="width: 900px;height:180px" name="commonKey"  class="mustFill"  title="请填写内容">${bean.commonKey}</textarea>
	</dd>
</dl>




<input  type="hidden" name="payType" value="${payType }">
<input  type="hidden" name="payInfoId" value="${bean.payInfoId }">
</div>
<div class="formBar">
<ul>
	<li>
	<div class="buttonActive"><div class="buttonContent"><button type="submit" >提交</button></div></div>
	</li>
	
</ul>
</div>
</form>
</div>