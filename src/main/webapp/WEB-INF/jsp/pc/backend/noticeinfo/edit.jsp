<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>

<div class="pageContent">
<form method="post" action="noticeInfo/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">

<dl style="width: 550px;" >
	<dt><span style="color: red;">*</span>标题：</dt>
	<dd>
	
		<input type="text" name="title" value="${bean.title}" class="mustFill" title="请填写">
	
	</dd>
</dl>


<dl style="width:550px:">
		<dt><span style="color: red;">*</span>接收类型</dt>
		<dd>
			<select name="receiveType">
					<option value="">--请选择--</option>
					<option value="99" <c:if test="${bean.receiveType == 99 }" >selected="selected"</c:if> >全系统</option>
					<option value="2" <c:if test="${bean.receiveType == 2 }" >selected="selected"</c:if> >店铺用户</option>
					<option value="4" <c:if test="${bean.receiveType == 4 }" >selected="selected"</c:if> >物流用户</option>
			</select>
		</dd>
</dl>
<dl style="width: 550px;" >
	<dt><span style="color: red;">*</span>通知内容：</dt>
	
	<dd>
		<textarea type="text" style="width: 600px;height:300px" name="content"  class="mustFill "  title="请填写公告内容">${bean.content}</textarea>
	</dd>
</dl>

</dl>

<input type="hidden" name="noticeInfoId" value="${bean.noticeInfoId }"  >
<input type="hidden" name="noticeInfoNum" value="${bean.noticeInfoNum}"  >

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