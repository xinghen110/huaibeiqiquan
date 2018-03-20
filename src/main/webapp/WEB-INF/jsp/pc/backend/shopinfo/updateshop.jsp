<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>
 <ry:binding type="2"></ry:binding>
<div class="pageContent">
<form method="post" action="shopinfo/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">

<dl style="width: 900px;" >
	<dt>登录名：</dt>
	<dd>
		 ${user.loginName}
	</dd>
</dl>
<dl style="width: 900px;" >
<dt >店铺名称：</dt>
	<dd>
		${bean.shopName }
		<input type="hidden" name="shopName" value="${bean.shopName}"/>
	</dd>
</dl>
<dl style="width: 900px;" >
	<dt>是否推荐：</dt>
	<dd>
	<input type="hidden" name="isRecommend" value="${bean.isRecommend}"/>
		<c:if test="${bean.isRecommend == 1}">是</c:if>
		<c:if test="${bean.isRecommend == 2}">否</c:if>
	</dd>
</dl>
<dl style="width: 900px;" >
	<dt>是否热门：</dt>
	<dd>
	<input type="hidden" name="isHot" value="${bean.isHot}"/>
		<c:if test="${bean.isHot == 1}">是</c:if>
		<c:if test="${bean.isHot == 2}">否</c:if>
	</dd>
</dl>
<dl style="width: 900px;" >
<dt>店铺类型：</dt>
	<dd style="width: 205px;">
		${shopTypeName }
		<input type="hidden" name="shopTypeNum" value="${bean.shopTypeNum}"/>
	</dd>
</dl>
<dl style="width: 900px;">
	<dt>起送价格：</dt>
	<dd style="width: 205px;">
		<input type="text" style="width: 180px;"  name="startPrice" value="${bean.startPrice}"  maxlength="5" size="15" class="number" title="起送价格"    />
	</dd>
</dl>
<dl style="width: 900px;">
	<dt>费率：</dt>
	<dd style="width: 205px;">
		<input type="text" style="width: 180px;"  name="rate" value="${bean.rate }"  maxlength="5" size="30" class="mustFill" title="费率"    />
	</dd>

	 
</dl>
<dl style="width:900px;" >	
	<dt >区域：</dt>
	<dd >
		<input type="hidden" name="province" value="${bean.province}"/>
		<input type="hidden" name="city" value="${bean.city}"/>
		<input type="hidden" name="areas" value="${bean.areas}"/>
	<ry:show parentCode="${bean.province}" itemCode="${bean.province}" type="2"></ry:show>
	<ry:show parentCode="${bean.province}" itemCode="${bean.city}" type="3"></ry:show>
	<ry:show parentCode="${bean.city}" itemCode="${bean.areas}" type="4"></ry:show>
		</dd>
 </dl>

<dl style="width:900px;">
	<dt>详细地址：</dt>
	<dd>
	<input type="hidden" name="address" value="${bean.address}"/>
		${bean.address}
	</dd>
</dl>
<dl style="width: 900px;" >
	<dt><span style="color: red;">*</span>联系人：</dt>
	<dd>
		<input type="text" style="width: 180px;" id="linkMan" name="linkMan" value="${bean.linkMan }"  maxlength="20" size="30" class="mustFill" title="联系人呢"    />
	</dd>
</dl>
<dl style="width: 900px;" >
<dt ><span style="color: red;">*</span>联系电话：</dt>
	<dd>
		<input type="text" name="linkTel" value="${bean.linkTel }" id="linkTel" class="mustFill" style="width: 180px;" title="联系电话" maxlength="100">
	</dd>
</dl>
<dl style="width:900px; display: none;" >	
		<dt><span style="color: red;">*</span>经纬X：</dt>
		<dd><input type="text" readonly="readonly" title="经纬X" style="width: 180px;" id="longitude" name="longitude"  value="${bean.longitude}" /></dd>
		
		<dt  style="width: 180px;" ><span style="color: red;">*</span>经纬Y：</dt>
		<dd><input type="text" readonly="readonly" title="经纬Y" style="width: 180px;" id="latitude" name="latitude"  value="${bean.latitude}" /></dd>
 </dl>

<!--隐藏值-->
<input type="hidden" name="shopId" value="${bean.shopId }"  >
<input type="hidden" name="shopStatus" value="${bean.shopStatus }"  >
			
<dl id="icon" style="width: 900px;height: 140px;" >
	<dt>店铺图片：</dt>
	<dd style="width: 130px;height: 125px;">
	<c:if test="${not empty bean.mainPhoto}"> <input type="hidden" name="mainPhoto" readonly="readonly" value="${bean.mainPhoto}" /></c:if>
	 <img id="mainPhotoview" src="${constants.QINIU_USER_IMGURL}${not empty bean.mainPhoto ? bean.mainPhoto :'default.png'}" width="100" height="100" />
	 <input id="mainPhotoIE" style="display: none;">
	 <input name="mainphoto" id="mainphoto" type="file" style="width: 180px;padding-top: 4px;" multiple="multiple" onchange="javascript:setImagePreview('mainphoto','100px','100px','mainPhotoview','mainPhotoIE');" value="上传附件">
	 </dd>
	 
	
</dl>

<dl id="icon" style="width: 900px;height: 140px;" >
<dt>背景图片：</dt>
	<dd style="width: 130px;height: 125px;">
	<c:if test="${not empty bean.backgroudImg}"> <input type="hidden" name="backgroudImg" readonly="readonly" value="${bean.backgroudImg}" /></c:if>
	 <img id="backgroudImgview" src="${constants.QINIU_USER_IMGURL}${not empty bean.backgroudImg ? bean.backgroudImg :'default.png'}" width="300" height="100" />
	 <input id="backgroudImgIE" style="display: none;">
	 <input name="backgroudimg" id="backgroudimg" type="file" style="width: 180px;padding-top: 4px;" multiple="multiple" onchange="javascript:setImagePreview('backgroudimg','300px','100px','backgroudImgview','backgroudImgIE');" value="上传附件">
	 </dd>
</dl>
<%@include file="/WEB-INF/jsp/inc/display_picture_js.jsp"%>

</div>

<div class="formBar">
<ul>
	<li>
	<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="todo()">提交</button></div></div></li>
	<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
</ul>
</div>

</form>
</div>
<script type="text/javascript">
function getTypeNum(parentNum){
	if(parentNum == ''){
		$("#typenum").html("<option value=''>请选择</option>");
		return;
	}
	$.ajax({
		type:'post',
		url:'shopinfo/getShopTypeNum',
		data:'parentNum='+parentNum,
		dataType:'json',
		success:function(data){
		var html="<option value=''>请选择</option>";
		for(var i=0;i<data.length;i++){
			var status="";
			if('${bean.shopTypeNum}'==data[i].shopTypeNum)
				status="selected";
			html+="<option "+status+" value="+data[i].shopTypeNum+">"+data[i].shopTypeName+"</option>";
			}
		$("#typenum").html(html);
			},
			error:function(){
			alert("没有信息");
			}
		});
}
window.onload = getTypeNum('${bean.parentNum}');

	function ismail(num){
		if(num == 2){
			$(".dtdd").show();
		}else{
			$(".dtdd").hide();
			$("#postage").val('');
		}
	}
	window.onload = ismail('${bean.isMail}');

function map(){
	 var provinceCode=$("#provinceEdit option:selected").text();
	 var cityCode=$("#cityEdit option:selected").text();
	 var areaEdit=$("#areaEdit option:selected").text();
	 var address=provinceCode+cityCode+areaEdit;
	 if($('#areaEdit').val() ==''){
		 alert("请选择区域");
		 return;
	 }
	 window.open("shopinfo/toMap?address="+address,"_blank");
	}

var flag = false;
function searchAjaxName(){
	 var loginName=$('#loginName').val();
		 if(loginName!=""){
		     $.ajax({
		    	 type:'post',//可选get
		    	 url:'user/searchAjaxName',//这里是接收数据的PHP程序
		    	 data:'loginName='+loginName,
		    	 dataType:'text',//服务器返回的数据类型 可选XML ,Json jsonp script html text等
		    	 success:function(msg){
		    	    if(msg=='fail'){
		    	    	alertMsg.warn("该用户信息已经存在！");
			    	    return false;
			    	}else{
			    		flag = true;
			    	}
		    	 },
		    	 error:function(){
		    	 }
		    });
	     }
	}

function todo(){
	var userId = $("#userId").val();
	if(userId != ''){
		flag = true;
	}else{
		if($('#loginName').val() == '')
		return alertMsg.warn("无登录名");
	}
	
	if(flag){
		if(check()){
			$("#forms").submit();
		}
	}else{
		alertMsg.warn("该登录名已经存在！");
	}
}
</script>
