<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<div class="pageContent">
 <ry:binding type="2"></ry:binding>
<form method="post" action="goodstype/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">
<dl style="width: 750px;float: none;" >
	<dt><span style="color: red;">*</span>名称：</dt>
	<dd>
		<input type="text" name="goodsTypeName" value="${bean.goodsTypeName }" class="mustFill"  maxlength="20" title="名称">
	</dd>
</dl>
<dl style="width: 750px;float: none;" >
<dt >上级类目：</dt>
	<dd style="width: 320px">

	<select name="parentNum"  <c:if test="${not empty bean.goodsTypeNum}">disabled</c:if> >
			<c:if test="${empty bean.goodsTypeNum}">
				<option value="">无</option>
				<c:forEach items="${parentNumList}" var="item">
				<c:if test="${item.goodsTypeNum != bean.goodsTypeNum}">
					<option value="${item.goodsTypeNum }" ${item.goodsTypeNum==bean.parentNum?'selected':'' }>${item.goodsTypeName }</option>
				</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${not empty bean.goodsTypeNum}">
			<c:forEach items="${parentNumList}" var="item">
				<c:if test="${item.goodsTypeNum == bean.goodsTypeNum}">
				<option value="">无</option>
				</c:if>
				<c:if test="${item.goodsTypeNum != bean.goodsTypeNum}">
					<option value="${item.goodsTypeNum }" ${item.goodsTypeNum==bean.parentNum?'selected':'' }>${item.goodsTypeName }</option>
				</c:if>
			</c:forEach>
			</c:if>
	</select>
&nbsp;&nbsp;&nbsp;&nbsp;
	<c:if test="${ empty bean.goodsTypeNum}">顶级类目请选择"无"</c:if>
	</dd>
</dl>
<dl style="width: 750px;" >
	<dt><span style="color: red;">*</span>排序：</dt>
	<dd>
		<input type="text" name="sortNum" value="${not empty bean.sortNum? bean.sortNum :(MaxSort+1) }" class="mustFill" title="等级">
	</dd>
</dl>
<c:if test="${user.shopNum =='SP16480000000310'}">
<dl id="icon" style="width: 800px;height: 140px;" >
	<dt>分类图标：</dt>
<dd style="width: 170px;height: 170px;">
<c:if test="${not empty bean.flag1}"> <input type="hidden" name="flag1" readonly="readonly" value="${bean.flag1}" /></c:if>
 <img id="flag1view" src="${constants.QINIU_USER_IMGURL}${not empty bean.flag1 ? bean.flag1 :'default.png'}" width="100" height="100" />
 <input id="flag1IE" style="display: none;">
 <input name="flag1s" id="flag1s" type="file" style="width: 180px;padding-top: 4px;" multiple="multiple" onchange="javascript:setImagePreview('flag1s','100px','100px','flag1view','flag1IE');" value="上传附件">
 <span style="color: red; width: 100px">上传图片170px*170px最佳</span>
 </dd>
</dl>
<%@include file="/WEB-INF/jsp/inc/display_picture_js.jsp"%>
</c:if>
<!--隐藏值-->
<input type="hidden" name="goodsTypeId" value="${bean.goodsTypeId }"  >
<input type="hidden" name="goodsLevel" value="${bean.goodsLevel }"  >
<input type="hidden" name="goodsTypeNum" value="${bean.goodsTypeNum }"  >
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
