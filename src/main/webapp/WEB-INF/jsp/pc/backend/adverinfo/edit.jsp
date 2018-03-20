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

<ry:binding parentCode="JUMP_TYPE" bingdingName="jumpType"></ry:binding>
<div class="pageContent">
<form method="post" action="adverinfo/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,navTabAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">
<input  type="hidden" name="adverType" value="${adverType}"/>
<dl style="width: 800px;">
	<dt><span style="color: red;">*</span>广告名称 ：</dt>
	<dd>
		<input type="text" name="title" value="${bean.title }" title="广告名称" class="mustFill" maxlength="100" >
	</dd>
	
			<dt style="width: 130px"><span style="color: red;">*</span>广告类型：</dt>
			<select name="jumpType"  class="chosen-select" style="width:190px;" onchange="change(this.options[this.options.selectedIndex].value)">
				  <c:forEach items="${jumpType}" var="item">
					 <option value="${item.itemCode }" <c:if test="${item.itemCode==bean.jumpType}">selected="selected"</c:if> >${item.itemName }</option>
			       </c:forEach>
			</select>	
	
</dl>

 <dl style="width: 750px;display: none;" id="url"  class="url" >
	<dt>连接地址 ：</dt>
	<dd><input type="text" name="linkTel" id="linkTel" value="${bean.linkTel }" title="连接URL"  maxlength="100" style="width: 300px;"></dd>
</dl>

	<dl style="width: 800px;">
		<dt><span style="color: red;">*</span>排序 ：</dt>
		<dd>
			<input type="number" name="sortNum" value="${bean.sortNum }" title="排序" class="mustFill" maxlength="100" >
		</dd>
	</dl>

<dl style="width: 800px;height: 530px;display: none;" id="adverContentId">
	<dt>内容：</dt>
	<dd><div id="myeditor"  style="width:600px;height: 400px;"></div>
	<input type="hidden" id="adverContent" name="adverContent" value='${bean.adverContent}' />
	</dd>
</dl>

<dl style="width:800px; "class="url1">
		<dt><span style="color: red;">*</span>商家：</dt>
		<dd style="width: 300px;">
			<c:if test="${empty shopNum}">
			<input type="text" readonly="readonly" name="orgLookup.shopName" id="shopName" value="${shopName}" suggestfields="shopName" lookupgroup="orgLookup" autocomplete="off"  >
			<input  style="display:none;" type="text" title="商家" readonly="readonly"  class="textInput" name="orgLookup.shopNum"   id="shopNum"  value="${bean.shopNum}" suggestfields="shopNum" lookupgroup="orgLookup" autocomplete="off" >
			<input style="display:none;" type="text" name="shopNum" id="shopNums" >
			<a class="btnLook" href="adverinfo/getFindBack" lookupgroup="orgLookup" >店铺信息</a>	
			</c:if>
		</dd> 
	</dl>


<dl id="icon" style="width: 800px;height: 140px;" >
	<dt>广告图片：</dt>
	<dd style="width: 130px;height: 125px;">
	<c:if test="${not empty bean.mainPhoto}"> <input type="hidden" name="mainPhoto" readonly="readonly" value="${bean.mainPhoto}" /></c:if>
	 <img id="mainPhotoview" src="${constants.QINIU_USER_IMGURL}${not empty bean.mainPhoto ? bean.mainPhoto:'default.png'}" width="200" height="120" />
	 <input id="mainPhotoIE" style="display: none;">
	 <input name="mainphoto" id="mainphoto" type="file" style="width: 180px;padding-top: 4px;" multiple="multiple" onchange="javascript:setImagePreview('mainphoto','200px','120px','mainPhotoview','mainPhotoIE');" value="上传附件">
	  <span style="color: red; width: 150px">图片1242px*672px最佳</span>
	 </dd>
<%@include file="/WEB-INF/jsp/inc/display_picture_js.jsp"%>
</dl>
</div>

<div class="formBar">
<ul>
	<li><input type="hidden" name="adverInfoId" value="${bean.adverInfoId }">
	<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="todo()">提交</button></div></div></li>
	<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
</ul>
</div>

</form>
</div>
<script type="text/javascript">
function change(num){
		if(num==2){
			$("#url").hide();
			$(".url1").hide();
			$(".url1").val('');
			$("#adverContentId").hide();
			$("#adverContent").val('');
		}
		if(num==1){
			$(".url1").show();
			$("#url").hide();
			$("#url").val('');
			$("#adverContentId").hide();
			$("#adverContent").val('');
		}
		if(num==3){
			$("#url").hide();
			$(".url1").hide();
			$("#adverContentId").hide();
			$("#adverContent").val('');
			$(".url1").val('');
			$("#url").val('');
		}
		if(num==4){
			$("#url").hide();
			$(".url1").hide();
			$("#adverContentId").show();
			$(".url1").val('');
			$("#url").val('');
		}
		
}
window.onload=change('${bean.jumpType}');

var ue=UE.getEditor("myeditor",{
autoHeightEnabled: false});
ue.ready(function() {
ue.setContent($("#adverContent").val());
});
function todo(){
	$("#adverContent").val(ue.getContent());
	$("#shopNums").val($("#shopNum").val());
    if (${empty bean}) {
    }
	if(check())
	$("#forms").submit();
}


</script>
