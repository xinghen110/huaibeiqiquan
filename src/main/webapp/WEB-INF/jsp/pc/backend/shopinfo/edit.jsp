<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>

 <ry:binding type="2"></ry:binding>
  <ry:binding type="3"></ry:binding>
<div class="pageContent">
<form method="post" action="shopinfo/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this, navTabAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">

<dl style="width: 750px;" >

	<dt><c:if test="${empty user.loginName}"><span style="color: red;">*</span></c:if>登录名称：</dt>
				<dd>
				<c:if test="${not empty user.loginName}">${user.loginName}</c:if>
				<c:if test="${empty user.loginName}">
					<input type="text" id="loginName" name="loginName"  value="${user.loginName}" maxlength="11" size="30" class="mustFill number" title="登录名称输入有误" alt="请输入您的手机号" onblur="searchAjaxName();" />
					<span id="fali_show" class="info" style="color: red;display:none;">登录名已被使用，请重新输入</span>
				</c:if>
				</dd>
	

	<dt style="width: 180px;"><span style="color: red;">*</span>店铺名称：</dt>
	<dd>
	<c:if test="${not empty bean.shopName}">${bean.shopName}
	<input type="hidden" name="shopName" value="${bean.shopName }" ></input>
	</c:if>
	<c:if test="${empty bean.shopName}">
		<input type="text" name="shopName" value="${bean.shopName }" class="mustFill" style="width: 180px;" title="店铺名称" maxlength="100">
	</c:if>
	</dd>
</dl>

<dl style="width: 750px;" >
	<dt>是否推荐：</dt>
	<dd>
		<input type="radio" name="isRecommend" value="1" <c:if test="${bean.isRecommend == 1}">checked="checked"</c:if>>是
		<input type="radio" name="isRecommend" value="2" <c:if test="${bean.isRecommend == 2 || empty bean.isRecommend}">checked="checked"</c:if>>否
	</dd>
	
	<dt style="width: 180px;">是否热门：</dt>
	<dd>
		<input  type="radio" name="isHot" value="1" <c:if test="${bean.isHot == 1}">checked="checked"</c:if>>是
		<input  type="radio" name="isHot" value="2" <c:if test="${bean.isHot == 2 || empty bean.isHot}">checked="checked"</c:if>>否
	</dd>
		
	
</dl>
<dl style="width: 750px;" >
	<dt><span style="color: red;">*</span>联系人：</dt>
	<dd>
		<input type="text" style="width: 180px;" id="linkMan" name="linkMan" value="${bean.linkMan }"  maxlength="20" size="30" class="mustFill" title="联系人"    />
	</dd>

	<dt style="width: 180px;"><span style="color: red;">*</span>联系手机：</dt>
	<dd>
		<input type="text" name="linkTel" value="${bean.linkTel }" id="linkTel" class="mustFill" style="width: 180px;" title="联系手机" maxlength="100">
	</dd>
</dl>
<dl style="width: 750px;" >
	<dt><span style="color: red;">*</span>起送价格：</dt>
	<dd>
		<input type="text" style="width: 180px;" id="startPrice" name="startPrice" value="${bean.startPrice}"  maxlength="5" size="15" class="number" title="起送价格"    />
	</dd>

	<dt style="width: 180px;"><span style="color: red;">*</span>费率：</dt>
	<dd>
		<input type="text" style="width: 180px;" id="rate" name="rate" value="${bean.rate }"  maxlength="5" size="30" class="mustFill" title="费率"    />
	</dd>

	 
</dl>
<dl style="width:750px;" >	
	
		<dt ><span style="color: red;">*</span>区域：</dt>
		<dd style="width: 300px">
			
			<ry:authorize ifAllGranted="${authMap.SYSTEM_AUTH}">
			<%@include file="/WEB-INF/jsp/inc/regionalCascadeEdit.jsp" %>
			<input type="hidden" id="provinceid_edit"  value="${bean.province}" />
			<input type="hidden" id="citiesid_edit"   value="${bean.city}" />
			<input type="hidden" id="areaid_edit"   value="${bean.areas}" />
			</ry:authorize>
			<p><img src="img/map.png" onclick="map();"/><span style="color: red;">请点击选择你所填写的商铺的地址坐标</span></p>

		</dd>
	</dl>
	<dl style="width:750px;" >
	<dt  ><span style="color: red;">*</span>店铺类型：</dt>
	<dd style="width: 300px;">
		<select name="parentNum" onchange="getTypeNum(this.options[this.options.selectedIndex].value)" style="width: 86px;" class="mustFill" title="店铺类型">
			<option value="">请选择</option>
			<option value="STI010000000000" <c:if test="${'STI010000000000' == bean.parentNum}">selected="selected"</c:if>>同城商铺</option>
			<option value="STI020000000000" <c:if test="${'STI020000000000' == bean.parentNum}">selected="selected"</c:if>>美食外卖</option>
			<option value="STI050000000000" <c:if test="${'STI050000000000' == bean.parentNum}">selected="selected"</c:if>>农特产</option>
			<!--<c:forEach items="${shopTypeList}" var="item">
				<option value="${item.shopTypeNum }" <c:if test="${bean.parentNum == item.shopTypeNum}">selected="selected"</c:if>>${item.shopTypeName }</option>
			</c:forEach>
		--></select>
		<select name="parentNumsecend" id="typenum" class="mustFill" title="店铺类型" style="width: 86px;" onchange="queryshopTyeNum(this.value,'')" >
			<option></option>
		</select>
		<select name="shopTypeNum"  id="shopTypeEdit" class="mustFill" title="店铺类型"  style="width: 86px">
		<option></option>
	</select>
			<input type="hidden" id="parentNumsecend_edit"   value="${bean.parentNumsecend}" />
			<input type="hidden" id="shop_type_num_edit"   value="${bean.shopTypeNum}"/>
	</dd>
	<span style="color: red">【注：店铺类型请选择完整！】</span>
 </dl>

<dl style="width:700px;">
	<dt>详细地址：</dt>
	<dd>
		<input type="text" name="address" id="address" value="${bean.address}" maxlength="100" style="width: 555px;">
	</dd>
</dl>

<dl style="width:700px; display: none;" >	
		<dt><span style="color: red;">*</span>经纬X：</dt>
		<dd><input type="text" readonly="readonly" title="经纬X" style="width: 180px;" id="longitude" name="longitude"  value="${bean.longitude}" /></dd>
		
		<dt  style="width: 180px;" ><span style="color: red;">*</span>经纬Y：</dt>
		<dd><input type="text" readonly="readonly" title="经纬Y" style="width: 180px;" id="latitude" name="latitude"  value="${bean.latitude}" /></dd>
 </dl>

<!--隐藏值-->
<input type="hidden" name="shopId" value="${bean.shopId }"  >
<input type="hidden" name="shopStatus" value="${bean.shopStatus }"  >
			
<dl id="icon" style="width: 800px;height: 140px;" >
	<dt>店铺图片：</dt>
	<dd style="width: 130px;height: 125px;">
	<c:if test="${not empty bean.mainPhoto}"> <input type="hidden" name="mainPhoto" readonly="readonly" value="${bean.mainPhoto}" /></c:if>
	 <img id="mainPhotoview" src="${constants.QINIU_USER_IMGURL}${not empty bean.mainPhoto ? bean.mainPhoto :'default.png'}" width="100" height="100" />
	 <input id="mainPhotoIE" style="display: none;">
	 <input name="mainphoto" id="mainphoto" type="file" style="width: 180px;padding-top: 4px;" multiple="multiple" onchange="javascript:setImagePreview('mainphoto','100px','100px','mainPhotoview','mainPhotoIE');" value="上传附件">
	 </dd>
	 
	<dt>背景图片：</dt>
	<dd style="width: 130px;height: 125px;">
	<c:if test="${not empty bean.backgroudImg}"> <input type="hidden" name="backgroudImg" readonly="readonly" value="${bean.backgroudImg}" /></c:if>
	 <img id="backgroudImgview" src="${constants.QINIU_USER_IMGURL}${not empty bean.backgroudImg ? bean.backgroudImg :'default.png'}" width="300" height="100" />
	 <input id="backgroudImgIE" style="display: none;">
	 <input name="backgroudimg" id="backgroudimg" type="file" style="width: 180px;padding-top: 4px;" multiple="multiple" onchange="javascript:setImagePreview('backgroudimg','300px','100px','backgroudImgview','backgroudImgIE');" value="上传附件">
	 </dd>
</dl>

<dl id="icon" style="width: 350px;height: 140px;" >
		<dt>营业执照上传：</dt>
		<dd  style="width: 130px;height: 125px;">
		<c:if test="${not empty bean.licensePhoto}"> <input type="hidden" name="licensePhoto" readonly="readonly" value="${bean.licensePhoto}" /></c:if>
		<img id="licensePhotoview" src="${constants.QINIU_USER_IMGURL}${not empty bean.licensePhoto? bean.licensePhoto :'default.png'}" width="100" height="200" />
	 	<input id="licensePhotoIE" style="display: none;">
	 	<input name="licensephoto" id="licensephoto" type="file" style="width: 180px;padding-top: 4px;" multiple="multiple" onchange="javascript:setImagePreview('licensephoto','100px','200px','licensePhotoview','licensePhotoIE');" value="上传附件">
		</dd>
</dl>
<%@include file="/WEB-INF/jsp/inc/display_picture_js.jsp"%>
		<dl>
			<dt >营业执照名称：</dt>
			<dd>
			<input type="text" name="licenseName" value="${bean.licenseName}"  style="width: 200px;" title="营业执照名称" maxlength="100">
			</dd>
		</dl>
		
		
		<dl>	
			<dt >营业执照注册号：</dt>
			<dd>
			<input type="text" name="licenseZzh" value="${bean.licenseZzh}"  style="width: 200px;" title="营业执照注册号" maxlength="100">
			</dd>
		</dl>
		
		<dl>	
			<dt >地址：</dt>
			<dd>
			<input type="text" name="licenseSzd" value="${bean.licenseSzd}"  style="width: 200px;" title="地址" maxlength="100">
			</dd>
		</dl>
		
		
		<dl>
		<dt>有效期：</dt>
		<dd style="width:100px;height: 100px;">
		<input id="licenseStart"  title="有效期开始时间" style="width: 200px" type="text" name="licenseStart" value="<fmt:formatDate value="${bean.licenseStart}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'licenseEnd\')}'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'licenseEnd\')}'})"/>
	
		～
		<input id="licenseEnd"  title="有效期结束时间" style="width: 200px" type="text" name="licenseEnd" value="<fmt:formatDate value="${bean.licenseEnd}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'licenseStart\')}'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'licenseStart\')}'})"/>
		</dd>
	</dl>




<input type="hidden"  name="type" value="${type}">
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
var parentNumsecend_edit=document.getElementById("parentNumsecend_edit").value;
var shop_type_num_edit=document.getElementById("shop_type_num_edit").value;
function getTypeNum(parentNum,shoptypenum){
	
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
			if('${bean.parentNumsecend}'==data[i].shopTypeNum){
				status="selected";
				
				queryshopTyeNum(data[i].shopTypeNum,shoptypenum);
			}
			html+="<option "+status+" value="+data[i].shopTypeNum+">"+data[i].shopTypeName+"</option>";
			}
		$("#typenum").html(html);
			},
			error:function(){
			alert("没有信息");
			}
		});
}
function queryshopTyeNum(parentNum,shoptypenum){
	
	$("#shopTypeEdit").html("<option value=''>请选择</option>");
	if(parentNum == ""){
		$("#shopTypeEdit").html("<option value=''>请选择</option>");
		return;
	}
	$.ajax({
		type:'post',
		url:'shopinfo/getShopTypeNum',
		data:'parentNum='+parentNum,
		dataType:'json',
		success:function(data){
		var shoptype="<option value=''>请选择</option>";
		for(var i=0;i<data.length;i++){
			var status="";
			if('${bean.shopTypeNum}'==data[i].shopTypeNum){
				status="selected";
			}
			
			shoptype+="<option "+status+" value="+data[i].shopTypeNum+">"+data[i].shopTypeName+"</option>";
			}
		$("#shopTypeEdit").html(shoptype);
			},error:function(){
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
	 if($('#areaEdit').val() !=''){
	 var address=provinceCode+cityCode+areaEdit;
	 }
		if($('#areaEdit').val() ==''){
	 var address=provinceCode+cityCode
		}
	 if(!address){
		 address="${address}";
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
		    	 data:'loginName='+loginName+"&userType=2",
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
			var te = /^1[35784]\d{9}$/;
			if(${empty user.loginName}){
		
			var result = te.test($("#loginName").val());
			if(!result)
				return alertMsg.warn("登陆名称输入有误，请输入手机号！");
			}
			$("#forms").submit();
		}
	}else{
		alertMsg.warn("该登录名已经存在！");
	}
}
</script>
