<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>
 <ry:binding type="2"></ry:binding>
<div class="pageContent">
<form method="post" action="exchangeinfo/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">


<dl style="width: 750px;" >
	<dt><span style="color: red;">*</span>标题：</dt>
	<dd style="width: 550px;"><input type="text" name="title" value="${bean.title }" class="mustFill" title="标题" maxlength="30" style="width: 390px;"><span style="color: gray;">【请控制在30字以内】</span></dd>
</dl>

<dl style="width: 750px;" >	
	<dt>副标题：</dt>
	<dd style="width: 550px;"><input type="text" name="viceTitle" value="${bean.viceTitle }" maxlength="30" title="副标题" style="width: 390px;"><span style="color: gray;">【请控制在30字以内】</span></dd>
</dl>

<dl style="width: 750px;" >
	<dt><span style="color: red;">*</span>所需积分：</dt>
	<dd>
		<input type="text" name="point" value="${bean.point}" class="mustFill number" title="所需积分">
	</dd>
</dl>

<!--隐藏值-->
<input type="hidden" name="exchangeInfoId" value="${bean.exchangeInfoId }"  >
<input type="hidden" name="exchangeInfoNum" value="${bean.exchangeInfoNum }"  >
			
<dl id="icon" style="width: 800px;height: 122px;" >
	<dt>商品图片：</dt>
<dd style="width: 130px;height: 125px;">
<c:if test="${not empty bean.mainPhoto}"> <input type="hidden" name="mainPhoto" readonly="readonly" value="${bean.mainPhoto}" /></c:if>
 <img id="mainPhotoview" src="${constants.QINIU_USER_IMGURL}${not empty bean.mainPhoto ? bean.mainPhoto :'default.png'}" width="100" height="100" />
 <input id="mainPhotoIE" style="display: none;">
 <input name="mainphoto" id="mainphoto" type="file" style="width: 180px;padding-top: 4px;" multiple="multiple" onchange="javascript:setImagePreview('mainphoto','100px','100px','mainPhotoview','mainPhotoIE');" value="上传附件">
 </dd>
</dl>

<%@include file="/WEB-INF/jsp/inc/display_picture_js.jsp"%>

<dl style="width: 750px;height: auto;" >
	<dt>兑换流程：</dt>
	<dd style="height: auto;">
		<textarea rows="" cols="" name="exchangeProcess" title="兑换流程" style="width: 390px;height: 80px;">${bean.exchangeProcess}</textarea>
	</dd>
</dl>

<dl style="width: 750px;" >
	<dt>温馨提示：</dt>
	<dd>
		<textarea rows="" cols="" name="reminder" title="温馨提示" style="width: 390px;height: 80px;">${bean.reminder}</textarea>
	</dd>
</dl>
</div>

<div class="formBar">
<ul>
	<li>
	<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="todo()">提交</button></div></div></li>
	<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
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
