<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>
 <ry:binding type="2"></ry:binding>
 <ry:binding parentCode="JUMP_MODULE" bingdingName="jumpModuleList" type="1"></ry:binding>
<div class="pageContent">
<form method="post" action="shoptype/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">


<dl style="width: 750px;float: none;" >
	<dt><span style="color: red;">*</span>名称：</dt>
	<dd>
		<input type="text" name="shopTypeName" value="${bean.shopTypeName }" class="mustFill" title="名称">
	</dd>
	
</dl>
 

<dl style="width: 750px;" >
	 <dt><span style="color: red;">*</span>排序：</dt>
	<dd>
		<input type="text" name="sortNum" value="${not empty bean.sortNum? bean.sortNum :(MaxSort2+1) }" class="mustFill" title="等级">
	</dd>
</dl>

<!--隐藏值-->
<input type="hidden" name="shopTypeNum" value="${bean.shopTypeNum}"  >
<input type="hidden" name="shopLevel" value="${bean.shopLevel }"  >		
<input type="hidden" name="parentNum" value="${parentNum }" >
<input type="hidden" name="homePosition" value="${bean.homePosition }">



<%@include file="/WEB-INF/jsp/inc/display_picture_js.jsp"%>

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