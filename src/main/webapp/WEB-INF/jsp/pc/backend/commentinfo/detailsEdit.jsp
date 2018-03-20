<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>

<link rel="stylesheet" href="select/css/chose.css"/>
<script type="text/javascript" src="select/chosen.jquery.min.js"></script>
<div class="pageContent">
	<form method="post" action="commentinfo/edit" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this, navTabAjaxDone);" enctype="multipart/form-data">
		<div class="pageFormContent nowrap" layoutH="60">
			<input name="commentNum" type="hidden" value="${bean.commentNum}">
			<dl  style="width: 400px;">
				<dt ><span style="color: red;">*</span>评论星级：</dt>
				<dd><input type="number" value="${bean.score}" name="score" max="5.0" min="0.0"></dd>
			</dl>

			<dl  style="width: 400px;">
				<dt ><span style="color: red;">*</span>评论内容：</dt>
				<dd><textarea name="content" maxlength="200" style="height: 50px; width: 320px; resize:none;">${bean.content}</textarea></dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="todo()">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	<script type="text/javascript">
		function todo() {
			var score = $("input[name=score]").val();
			var content = $("textarea[name=content]").val();
			if (score == '' || content == '') {
			    alert("请输入内容！");
			    return false;
			}
			$("#forms").submit();
        }
	</script>
</div>