<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>

<div class="pageContent">
<form method="post" action="goodsbrand/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">


<dl style="width: 550px;" >
	<dt><span style="color: red;">*</span>品牌名称：</dt>
	<dd>
		<input type="text" name="brandName" value="${bean.brandName }" class="mustFill" title="品牌名称">
	</dd>
</dl>

 <input type="hidden" name="status" value="1">

<dl style="width: 550px;" >
	<dt>备注：</dt>
	<dd>
		<textarea rows="" cols="" style="width: 300px;height: 80px;" name="remark">${bean.remark }</textarea>
	</dd>
</dl>

<!--隐藏值-->
<input type="hidden" name="goodsBrandId" value="${bean.goodsBrandId }"  >
<input type="hidden" name="goodsBrandNun" value="${bean.goodsBrandNun }"  >

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
