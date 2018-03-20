<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>
<%@include file="/WEB-INF/jsp/inc/autocomplete.jsp"%>
 <ry:binding type="2"></ry:binding>
  <ry:binding type="3"></ry:binding>
<script>
$(function() {
	$('#selectmy').comboSelect();
});
</script>

<div class="pageContent">
<form method="post" action="oneCityShopType/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,navTabAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">
<input  type="hidden" name="position" value="${position}"/>
<dl style="width: 800px;">
	<dt><span style="color: red;">*</span>类型名称 ：</dt>
	<dd>
		<input type="text" name="shopTypeName" value="${bean.shopTypeName }" title="类型名称" class="mustFill" maxlength="100" >
	</dd>
	
	
</dl>



<dl id="icon" style="width: 800px;height: 140px;" >
	<dt>类型图片：</dt>
	<dd style="width: 130px;height: 125px;">
	<c:if test="${not empty bean.shopTypeIcon}"> <input type="hidden" name="shopTypeIcon" readonly="readonly" value="${bean.shopTypeIcon}" /></c:if>
	 <img id="shopTypeIconview" src="${constants.QINIU_USER_IMGURL}${not empty bean.shopTypeIcon ? bean.shopTypeIcon:'default.png'}" width="100" height="100" />
	 <input id="shopTypeIconIE" style="display: none;">
	 <input name="shoptypeicon" id="shoptypeicon" type="file" style="width: 180px;padding-top: 4px;" multiple="multiple" onchange="javascript:setImagePreview('shoptypeicon','100px','100px','shopTypeIconview','shopTypeIconIE');" value="上传附件">
	 </dd>
<%@include file="/WEB-INF/jsp/inc/display_picture_js.jsp"%>
</dl>
<input type="hidden" name="homePosition" value="${bean.homePosition }">
</div>

<div class="formBar">
<ul>
	<li><input type="hidden" name="shopTypeNum" value="${bean.shopTypeNum}">
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