<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>
<ry:binding parentCode="PROBLEM_TYPE" bingdingName="problemtype"></ry:binding>
<div class="pageContent">
<form method="post" action="commonproblem/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">

<dl style="width: 750px;height: 520px;" >
	<dt>协议详情：</dt>
	<dd><div id="myeditor"  style="width:600px;height: 400px;" title="协议详情"></div>
	<input type="hidden" id="content" name="content" title="协议详情" value='${bean.content}' />
	</dd>
</dl>

<input type="hidden" name="commonProblemId " value="${bean.commonProblemId }"  >
<input type="hidden" name="commonProblemNum" value="${bean.commonProblemNum}"  >

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
var ue=UE.getEditor("myeditor",{
	autoHeightEnabled: false});
	ue.ready(function() {
	ue.setContent($("#content").val());
});

function todo(){

	$("#content").val(ue.getContent());
		if(check())
		$("#forms").submit();
}
</script>