<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>
<ry:binding type="3"></ry:binding>
<div class="pageContent">

<form method="post" action="localnew/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this, navTabAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">

	<dl style="width: 750px;" >
			<dt><span style="color: red;">*</span>标题：</dt>
				<dd style="width: 550px;">
				<input type="text" name="title" value="${bean.title }" title="标题" class="mustFill" maxlength="30" style="width: 365px">
				<span style="color: gray;">【标题请在30字以内】</span>
				</dd>
				
	</dl>
	<dl style="width: 750px;" >
	<dt><span style="color: red;">*</span>新闻类型：</dt>
				<dd>
					<select name="shopTypeNum" class="mustFill" title="新闻类型">
						<option value="">--请选择--</option>
						<c:forEach items="${shopTypeList}"  var="hy">
							<option value="${hy.shopTypeNum }" ${hy.shopTypeNum==bean.shopTypeNum?'selected':'' }>${hy.shopTypeName }</option>
						</c:forEach>
					</select>
				</dd>
			
				
				<dt><span style="color: red;">*</span>来源：</dt>
				<dd>
				<input type="text" name="source" value="${bean.source}" title="来源" class="mustFill" alt="一天同城 " maxlength="10"></input>
				</dd>
				</dl>
	<dl style="width: 750px;" >	
			<dt>是否推荐：</dt>
			<dd>
			<input type="radio" name="isRecommend" value="1" <c:if test="${bean.isRecommend== 1}">checked="checked"</c:if>>是
			<input type="radio" name="isRecommend" value="2" <c:if test="${bean.isRecommend == 2 || empty bean.isRecommend}">checked="checked"</c:if>>否
			</dd>	
			
			<dt>是否置顶：</dt>
			<dd>
			<input type="radio" name="isTop" value="1" <c:if test="${bean.isTop== 1}">checked="checked"</c:if>>是
			<input type="radio" name="isTop" value="2" <c:if test="${bean.isTop == 2 || empty bean.isTop}">checked="checked"</c:if>>否
			</dd>	
	</dl>
			

	<ry:authorize ifAllGranted="${authMap.SYSTEM_AUTH}">
		<dl style="width:750px;" >	
		<dt ><span style="color: red;">*</span>所在区域：</dt>
		<dd style="width: 300px">
			<%@include file="/WEB-INF/jsp/inc/auserRegionalCascadeEdit.jsp" %>
			 <input type="hidden" id="provinceid_edit"  value="${fn:substring(bean.city, 0,2) }0000" />
			<input type="hidden" id="citiesid_edit"   value="${bean.city}" />
			<input type="hidden" id="areaid_edit"   value="${bean.area}" />
		</dd>
		</dl>	
	</ry:authorize>
	


<dl style="width: 750px;height: 330px;" >
	<h3 style="margin:0 0 1rem 66px;">新闻内容：<span style="color: red">(请上传至少一张本地图片，上传的第一张本地图片为封面图片 大小:500KB 类型:jpg,png)</span></h3>
	<dd style="margin-left:66px">
	<div id="myeditor1"  style="width:600px;height: 200px;" title="招聘内容"></div>
	<input type="hidden" id="content" name="content"" title="招聘内容" value='${bean.content }' />
		
	</dd>
</dl>
</div>
<input type="hidden" name="localNewNum" value="${bean.localNewNum}"></input>

<div class="formBar">
<ul>
	<li>
	<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="todo()">提交</button></div></div></li>
	<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
</ul>
</div>

</form>
</div>
<script>
var ue=UE.getEditor("myeditor1",{
	autoHeightEnabled: false});
	ue.ready(function() {
	ue.setContent($("#content").val());
	
});
function todo(){
	$("#content").val(ue.getContent());
	var totalStr = ue.getContent(); 
	var re = /<img[^>]+>/g;  
	var a = totalStr.match(re); 
	if(a==null){
		alert("本地新闻内容请至少上传一张图片！");
		return false;
	}else{
		if(check()){
			$("#forms").submit();
		}
	}
	
}

</script>