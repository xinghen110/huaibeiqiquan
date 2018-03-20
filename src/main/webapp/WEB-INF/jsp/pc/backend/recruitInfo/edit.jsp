<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>
 <ry:binding type="2"></ry:binding>
  <ry:binding type="3"></ry:binding>
<div class="pageContent">

<form method="post" action="recruitInfo/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this, navTabAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">

	<dl style="width: 750px;" >
			<dt><span style="color: red;">*</span>招聘标题：</dt>
				<dd style="width: 500px">
				<input type="text" name="title" value="${bean.title }" class="mustFill" maxlength="30" style="width: 500px" alt="最多输入30个字"></input>
				</dd>
				</dl>
	<dl style="width: 750px;" >
			<dt><span style="color: red;">*</span>联系电话：</dt>
				<dd>
				<input type="text" name="linkTel" value="${bean.linkTel }" class="mustFill number"></input>
				</dd>
				
				<dt>是否置顶：</dt>
				<dd>
			<input type="radio" name="isTop" value="1" <c:if test="${bean.isTop== 1}">checked="checked"</c:if>>是
			<input type="radio" name="isTop" value="2" <c:if test="${bean.isTop == 2 || empty bean.isTop}">checked="checked"</c:if>>否
			</dd>	
	</dl>
	<dl style="width: 750px;" >
			<dt><span style="color: red;">*</span>单位名称：</dt>
				<dd>
				<input type="text" name="unitName" value="${bean.unitName}" class="mustFill"></input>
				</dd>
	
			<dt><span style="color: red;">*</span>单位地址：</dt>
				<dd>
				<input type="text" name="address" value="${bean.address}" class="mustFill"></input>
				</dd>
	</dl>
	
	<ry:authorize ifAllGranted="${authMap.SYSTEM_AUTH}">
		<dl style="width:750px;" >	
		<dt ><span style="color: red;">*</span>区域：${fn:substring(bean.city, 0,2) }</dt>
		<dd style="width: 300px">
			<%@include file="/WEB-INF/jsp/inc/auserRegionalCascadeEdit.jsp" %>
			 <input type="hidden" id="provinceid_edit"  value="${fn:substring(bean.city, 0,2) }0000" />
			<input type="hidden" id="citiesid_edit"   value="${bean.city}" />
			<input type="hidden" id="areaid_edit"   value="${bean.area}" />
		</dd>
		</dl>	
	</ry:authorize>
	<dl style="width: 750px;" >
	<dt><span style="color: red;">*</span>招聘类型：</dt>
				<dd>
					<select name="shopTypeNum">
						<option value="">--请选择--</option>
						<c:forEach items="${shopTypeList}"  var="hy">
							<option value="${hy.shopTypeNum }" ${hy.shopTypeNum==bean.shopTypeNum?'selected':'' }>${hy.shopTypeName }</option>
						</c:forEach>
					</select>
				</dd>
	</dl>
	
	<dl id="icon" style="width: 800px;height: 140px;" >
		<dt>主图：</dt>
		<dd style="width: 130px;height: 125px;">
		<c:if test="${not empty bean.mainPhoto}"> <input type="hidden" name="mainPhoto" readonly="readonly" value="${bean.mainPhoto}" /></c:if>
		 <img id="mainPhotoview" src="${constants.QINIU_USER_IMGURL}${not empty bean.mainPhoto ? bean.mainPhoto :'default.png'}" width="100" height="100" />
		 <input id="mainPhotoIE" style="display: none;">
		 <input name="mainphoto" id="mainphoto" type="file" style="width: 180px;padding-top: 4px;" multiple="multiple" onchange="javascript:setImagePreview('mainphoto','100px','100px','mainPhotoview','mainPhotoIE');" value="上传附件">
		 </dd>
	</dl>

<dl style="width: 750px;height: 330px;" >
	<dt>招聘内容：</dt>
	<dd>
	<div id="myeditor1"  style="width:600px;height: 200px;" title="招聘内容"></div>
	<input type="hidden" id="content" name="content"" title="招聘内容" value='${bean.content }' />
		
	</dd>
</dl>
<%@include file="/WEB-INF/jsp/inc/display_picture_js.jsp"%>
</div>
<input type="hidden" name="recruitInfoNum" value="${bean.recruitInfoNum}"></input>
<input type="hidden" id="isDraft"  name="isDraft"></input>

<div class="formBar">
<ul>
	<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="todo1()">暂存</button></div></div></li>
	<li>
	<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="todo()">发布</button></div></div></li>
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
	$("#isDraft").val(2)
	if(check()){
		$("#forms").submit();
	}
}

function todo1(){
	$("#content").val(ue.getContent());
	$("#isDraft").val(1)
	if(check()){
		$("#forms").submit();
	}
}

</script>