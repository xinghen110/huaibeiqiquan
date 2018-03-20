<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>
 <ry:binding type="2"></ry:binding>
<link rel="stylesheet" href="big/css/layer.css" />
<script type="text/javascript" src="big/layer.js"></script>


<div class="pageContent">
<form method="post" action="shopinfo/saveExamine" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">

<dl style="width: 750px;" >
	<dt>登录名：</dt>
	<dd>${user.loginName}</dd>

	<dt style="width: 180px;">店铺名称：</dt>
	<dd>${bean.shopName }</dd>
</dl>

<dl style="width: 750px;" >
	<dt>是否推荐：</dt>
	<dd>${bean.isRecommend == 1? '是' : '否'}</dd>
	
	<dt style="width: 180px;">店铺类型：</dt>
	<dd style="width: 205px;">
		${parentName }~${shopTypeName }
	</dd>
</dl>

<dl style="width: 750px;" >
	<dt>联系人：</dt>
	<dd>${bean.linkMan }</dd>

	<dt style="width: 180px;">联系手机：</dt>
	<dd>${bean.linkTel }</dd>
</dl>

<dl style="width: 750px;" >
	<dt>起送价格：</dt>
	<dd>${bean.startPrice}</dd>

	<dt style="width: 180px;">费率：</dt>
	<dd>${bean.rate}</dd>
</dl>
 

<dl style="width:700px;" >	
	<dt >所在地区：</dt>
	<dd style="width: 400px;margin-top: -4px;">
		<ry:show parentCode="${bean.province}" itemCode="${bean.province}" type="2"></ry:show>
	<ry:show parentCode="${bean.province}" itemCode="${bean.city}" type="3"></ry:show>
	<ry:show parentCode="${bean.city}" itemCode="${bean.areas}" type="4"></ry:show>
		</dd>
 </dl>

<dl style="width:700px;">
	<dt>详细地址：</dt>
	<dd>${bean.address}</dd>
</dl>

 

<!--隐藏值-->
<input type="hidden" name="shopNum" value="${bean.shopNum}"  >

<div id="imgs" class="imgs">
<dl id="icon" style="width: 800px;height: 140px;" >
	<dt>店铺图片：</dt>
		<dd style="width: 130px;height: 125px;">
		<c:if test="${not empty bean.mainPhoto}">
		 <img id="showimg" src="${constants.QINIU_USER_IMGURL}${ bean.mainPhoto }" width="100" height="100" onclick="big('big1')" />
		  <div id="big1" hidden="" style="text-align: center" ><img id="showimgbig" src="${constants.QINIU_USER_IMGURL}${ bean.mainPhoto }" width="600" height="600"  ></div>
		</c:if>
		
	 </dd>
 
 	<dt>背景图片：</dt>
	<dd style="width: 130px;height: 125px;">
		<c:if test="${not empty bean.backgroudImg}">
		 <img  src="${constants.QINIU_USER_IMGURL}${ bean.backgroudImg }" width="300" height="100" onclick="big('big2')" />
		 <div id="big2" hidden="" style="text-align: center" ><img id="showimgbig" src="${constants.QINIU_USER_IMGURL}${ bean.backgroudImg }" width="600" height="300"  ></div>
		 </c:if>
		 
	 </dd>
</dl>

<dl id="icon" style="width: 350px;height: 140px;" >
		<dt>营业执照上传：</dt>
		<dd  style="width: 130px;height: 125px;">	
		<c:if test="${not empty bean.licensePhoto}">
		<img  src="${constants.QINIU_USER_IMGURL}${bean.licensePhoto }"  width="100" height="150" onclick="big('big3')" />
		<div id="big3" hidden="" style="text-align: center" ><img id="showimgbig" src="${constants.QINIU_USER_IMGURL}${bean.licensePhoto }" width="600" height="900"  ></div>
		</c:if>
	 	</dd>
</dl>
<script type="text/javascript">
function big(bigid){
			//查看照片
			layer.open({
			  type: 1,
			  title: false,
			  closeBtn: 0,
			  area: 'auto',
			  skin: 'layui-layer-nobg', //没有背景色
			  shadeClose: true,
			  content: $("#"+bigid)
			 
	
	})
};


</script>


<dl>
			<dt >营业执照名称：</dt>
			<dd>
			${bean.licenseName}
			</dd>
		</dl>
		
		
		<dl>	
			<dt >营业执照注册号：</dt>
			<dd>
			${bean.licenseZzh}
			</dd>
		</dl>
		
		<dl>	
			<dt >地址：</dt>
			<dd>
			${bean.licenseSzd}
			</dd>
		</dl>
		
		
		<dl>
		<dt>有效期：</dt>
		<dd >
		
		<ry:formatDate date="${bean.licenseStart}" toFmt="yyyy-MM-dd"/>
		
		～
		
		<ry:formatDate date="${bean.licenseEnd}" toFmt="yyyy-MM-dd"/>
		</dd>
	</dl>

<dl style="width: 750px;height: 30px;" >
	<dt><span style="color: red;">*</span>审核选择：</dt>
	<dd style="width: 205px;">
		<select style="width: 100px;" name="auditStatus" id="auditStatus" onchange="checkshopStatus()">
			<option value="">请选择</option>
			<option value="1">通过</option>
			<option value="3">审核中</option>
			<option value="2">未通过</option>	
		</select>
	</dd>
</dl>


<dl   style="width: 750px;height: 140px;" >
	<dt hidden="" id="auditreaseon"><span style="color: red;">*</span>审核说明：</dt>
	<dd >
		<textarea hidden="" type="text" id="auditReasons" style="width: 500px;height:100px" name="auditReason"  class="mustFill"  title="请填写内容"></textarea>
	</dd>
</dl>

<%@include file="/WEB-INF/jsp/inc/display_picture_js.jsp"%>
<input type="hidden" name="type" value="${type}">
</div>


<div class="formBar">
<ul>
	<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="todo()">保存</button></div></div></li>
	<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
</ul>
</div>

</form>
</div>
<script type="text/javascript">
function checkshopStatus(){
		var auditStatus=document.getElementById("auditStatus").value ;
		if(auditStatus==1){
			$("#auditreaseon").show();
			$("#auditReasons").show();
			}
		if(auditStatus==2){
			$("#auditreaseon").show();
			$("#auditReasons").show();
			}
		if(auditStatus==3){
			$("#auditreaseon").hide();
			$("#auditReasons").hide();
			}
}

function todo(){
	$("#forms").submit();
}
</script>
