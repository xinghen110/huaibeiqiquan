<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
 <ry:binding type="2"></ry:binding>
<div class="pageContent">
	<form method="post" targetType="dialog" action="versionUpdate/saveOrUpdate?type=${type}&num=${num}"
		 id="forms" class="pageForm required-validate" 
			onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">
<c:if test="${type==1}">
<c:if test="${num==1}">
<dl style="width: 1000px;" >
<dt>客户端：</dt>
		<dd id="photoDD" >
			<div id="photoDiv" style="text-align: left;">
				<input name="fileName" id="photoFile" type="file" 
					multiple="multiple" onchange="javascript:setImagePreview('photoFile','photoFileview','photoFileIE');" 
					value="上传附件" style="height: 25px;" >${bean.url }
			</div>
			
		</dd>
	<dt><span style="color: red;">*</span>版本编号：</dt>
	<dd style="width: 550px;"><input type="text"   name="versionCode" value="${bean.versionCode}" class="mustFill number" title="请输入版本编号" maxlength="11" ></dd>
</dl>
</c:if>



<c:if test="${num==2}">
<dl style="width: 1000px;" >
<dt>
商家端：
</dt>
<dd id="photoDD" >
			<div id="photoDiv" style="text-align: left;">
				<input name="fileName" id="photoFile" type="file" 
					multiple="multiple" onchange="javascript:setImagePreview('photoFile','photoFileview','photoFileIE');" 
					value="上传附件" style="height: 25px;">${bean.shopUrl }
			</div>
			
		</dd>
	<dt><span style="color: red;">*</span>版本编号：</dt>
	<dd style="width: 550px;"><input type="text"   name="shopCode" value="${bean.shopCode}" class="mustFill number" title="请输入版本编号" maxlength="11" ></dd>
</dl>
</c:if>

<c:if test="${num==3}">
<dl style="width: 1000px;" >
<dt>
物流端：
</dt>
<dd id="photoDD" >
			<div id="photoDiv" style="text-align: left;">
				<input name="fileName" id="photoFile" type="file" 
					multiple="multiple" onchange="javascript:setImagePreview('photoFile','photoFileview','photoFileIE');" 
					value="上传附件" style="height: 25px;">${bean.wuliuUrl }
			</div>
			
		</dd>
	<dt><span style="color: red;">*</span>版本编号：</dt>
	<dd style="width: 550px;"><input type="text"   name="wuliuCode" value="${bean.wuliuCode}" class="mustFill number" title="请输入版本编号" maxlength="11" ></dd>
</dl>
</c:if>
</c:if>

<c:if test="${type==2}">
<dl style="width: 1000px;" >
<dt>
客户端：
</dt>
	<dd style="width: 350px;"><input type="text" style="width: 350px"   name="url" value="${bean.url}" class="mustFill "  ></dd>
	<dt><span style="color: red;">*</span>版本编号：</dt>
	<dd ><input type="text"   name="versionCode" value="${bean.versionCode}" class="mustFill number" title="请输入版本编号" maxlength="11" ></dd>
</dl>


<dl style="width: 1000px;" >
<dt>
商家端：
</dt>
	<dd style="width: 350px;"><input type="text" style="width: 350px"   name="shopUrl" value="${bean.shopUrl}" class="mustFill " ></dd>
	<dt><span style="color: red;">*</span>版本编号：</dt>
	<dd ><input type="text"   name="shopCode" value="${bean.shopCode}" class="mustFill number" title="请输入版本编号" maxlength="11" ></dd>
</dl>


<dl style="width: 1000px;" >
<dt>
物流端：
</dt>
	<dd style="width: 350px;"><input type="text" style="width: 350px"   name="wuliuUrl" value="${bean.wuliuUrl}" class="mustFill "  ></dd>
	<dt><span style="color: red;">*</span>版本编号：</dt>
	<dd ><input type="text"   name="wuliuCode" value="${bean.wuliuCode}"  class="mustFill number" title="请输入版本编号" maxlength="11" ></dd>
</dl>

</c:if>
<input type="hidden" name="versionUpdateNum" value="${bean.versionUpdateNum}">

</div>
<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
		
</form>
</div>

