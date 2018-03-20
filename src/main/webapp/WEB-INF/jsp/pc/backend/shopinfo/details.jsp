<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>
 <ry:binding type="2"></ry:binding>
 <link rel="stylesheet" href="big/css/layer.css" />
<script type="text/javascript" src="big/layer.js"></script>
<div class="pageContent">
<form method="post" action="shopinfo/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">


<dl style="width: 800px;" >
<dt >登陆名称：</dt>
	<dd>
		${user.loginName }
		
	</dd>
<dt >店铺名称：</dt>
	<dd>
		${bean.shopName }
	</dd>
</dl>
<dl style="width: 800px;" >
	<dt>是否推荐：</dt>
	<dd>
	<input type="hidden" name="isRecommend" value="${bean.isRecommend}"/>
		<c:if test="${bean.isRecommend == 1}">是</c:if>
		<c:if test="${bean.isRecommend == 2}">否</c:if>
	</dd>
	<dt>是否热门：</dt>
	<dd>
	<input type="hidden" name="isHot" value="${bean.isHot}"/>
		<c:if test="${bean.isHot == 1}">是</c:if>
		<c:if test="${bean.isHot == 2}">否</c:if>
	</dd>
</dl>
<dl style="width: 800px;" >
<dt>店铺类型：</dt>
	<dd style="width: 205px;">
		${shopTypeName }
	</dd>
</dl>
<dl style="width:800px;" >	
	<dt >区域：</dt>
	<dd >
		<input type="hidden" name="province" value="${bean.province}"/>
		<input type="hidden" name="city" value="${bean.city}"/>
		<input type="hidden" name="areas" value="${bean.areas}"/>
	<ry:show parentCode="${bean.province}" itemCode="${bean.province}" type="2"></ry:show>
	<ry:show parentCode="${bean.province}" itemCode="${bean.city}" type="3"></ry:show>
	<ry:show parentCode="${bean.city}" itemCode="${bean.areas}" type="4"></ry:show>
		</dd>

	<dt>详细地址：</dt>
	<dd>
	<input type="hidden" name="address" value="${bean.address}"/>
		${bean.address}
	</dd>
</dl>
<dl style="width: 800px;" >
	<dt>联系人：</dt>
	<dd>
		${bean.linkMan }
	</dd>

<dt >联系手机：</dt>
	<dd>
		${bean.linkTel }
	</dd>
</dl>

<dl style="width: 800px;" >
	<dt>起送价格：</dt>
	<dd>
		${bean.startPrice}
	</dd>

	<dt>费率：</dt>
	<dd>
		${bean.rate}
	</dd>
</dl>




<!--隐藏值-->
<input type="hidden" name="shopId" value="${bean.shopId }"  >
<input type="hidden" name="shopStatus" value="${bean.shopStatus }"  >
			
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
<%@include file="/WEB-INF/jsp/inc/display_picture_js.jsp"%>

</div>

<div class="formBar">
<ul>
	<li>
	<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
</ul>
</div>

</form>
</div>
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
