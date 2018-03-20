<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>

<div class="pageContent">
<form method="post" action="market/servicecharge/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="63">

			<p style="float: none;">
				<label>名称：</label>
				<input name="itemName" style="width: 180px;" readonly="readonly" class="required" type="text" size="30" value="${bean.itemName }" />
			</p>
			<p style="float: none;">
				<label>增加比例：</label>
				<input name="itemCode" style="width: 180px;" class="required" type="text" size="30" value="${bean.itemCode }" />
			</p>
			<%--<div style="margin: 90px 0 0 140px;">--%>
				<%--<c:if test="${bean.status == 1}">--%>
					<%--<p>你确定要开启<span style="color: red;">${bean.itemName}报价模式</span>吗？</p>--%>
				<%--</c:if>--%>
				<%--<c:if test="${bean.status == 0}">--%>
					<%--<p>你确定要关闭<span style="color: red;">${bean.itemName}报价模式</span>吗？</p>--%>
				<%--</c:if>--%>
			<%--</div>--%>

			<p style="float: none;">
				<input name="orderby" style="width: 180px;" class="required number" type="hidden" size="30" value="${not empty bean.orderby?bean.orderby : num+1 }" />
			</p>
			
			
		<!-- 隐藏值 -->
		<input type="hidden" value="${bean.parentCode }" name="parentCode">
		<input type="hidden" value="${bean.parentName }" name="parentName">
		<input type="hidden" value="${bean.isRead }" name="isRead">
		<input type="hidden" value="${bean.id }" name="id">
		<input type="hidden" value="1" name="status">

</div>

<div class="formBar">
<ul>
	<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
	<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>

	<%--<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>--%>
	<%--<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>--%>
</ul>
</div>

</form>
</div>