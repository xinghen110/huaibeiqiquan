<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>

 <link rel="stylesheet" href="select/css/chose.css"/>
 <script type="text/javascript" src="select/chosen.jquery.min.js"></script> 
  <script type="text/javascript">
            $(".chosen-select").chosen();
        </script>
<ry:binding parentCode="BANK_NAME" bingdingName="banknames"></ry:binding>
<div class="pageContent">
<form method="post" action="bankBind/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">

<div class="pageFormContent nowrap" layoutH="60">
	<dl style="width:800px; ">
		<dt style="width: 130px"><span style="color: red;">*</span>店铺：</dt>
		<dd style="width: 300px;">
			<c:if test="${not empty bean.shopName}">${bean.shopName}</c:if>
			<c:if test="${empty bean.shopNum}">
			<input type="text" readonly="readonly" name="orgLookup.shopName" id="shopName" value="${bean.shopName}" suggestfields="shopName" lookupgroup="orgLookup" autocomplete="off"  >
			<input style="display:none;" type="text" title="店铺" readonly="readonly"  class="mustFill textInput" name="orgLookup.shopNum"   id="shopNum"  value="${bean.shopNum}" suggestfields="shopNum" lookupgroup="orgLookup" autocomplete="off" >
			<input style="display:none;" type="text" name="shopNum" id="shopNums" >
			<input style="display:none;" type="text" name="shopName" id="shopNames" >
			<a class="btnLook" href="bankBind/getFindBack" lookupgroup="orgLookup" >店铺信息</a>	
			</c:if>
			
		</dd> 
	</dl>
	

		<dl  style="width: 800px;">
			<dt style="width: 130px"><span style="color: red;">*</span>持卡人姓名：</dt>
			<dd><input type="text" name="userName" id="username"  maxlength="4" class="mustFill" title="请输入真实姓名" alt="请输入真实姓名"  value="${bean.userName}"></dd>
		</dl>

		
		<dl  style="width: 800px;">
			<dt style="width: 130px"><span style="color: red;">*</span>银行卡号：</dt>
			<dd><input type="text" id="cardNo"  name="cardNo" class="mustFill number" title="请输入银行卡号" value="${bean.cardNo}" ></dd>
		</dl>
		<dl  style="width: 800px;">
			<dt style="width: 130px"><span style="color: red;">*</span>银行开户行：</dt>
			<select name="bankName"  class="chosen-select" style="width:190px;">
			   <option value="">---请选择银行---</option>
				  <c:forEach items="${banknames}" var="item">
					 <option value="${item.itemCode }" <c:if test="${item.itemCode==bean.bankName}">selected="selected"</c:if> >${item.itemName }</option>
			       </c:forEach>
			</select>	
		</dl>
		<input type="hidden" name="bankBindNum" value="${bean.bankBindNum}">
</div>
<div class="formBar">
<ul>
	<li>
	<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="todo()" >提交</button></div></div>
	</li>
	<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
	
</ul>
</div>
</form>
</div>
<script>

	
	function todo(){
		var reg = /^[\u4e00-\u9fa5]{2,4}$/;//名字
		var reg1=/^(\d{16}|\d{19})$/;//银行卡
		var username=$("#username").val();
		var cardNo=$("#cardNo").val();
		var text ="";
		if(!reg.test(username)){
			text += "输入姓名有误  ";
		}if(!reg1.test(cardNo)){
			text += "输入银行卡有误  ";
		}
		var shopNum = $("#shopNum").val();
		if(shopNum!=''){
			$.ajax({
		    	 type:'post',//可选get
		    	 url:'bankBind/searchAjaxshopNum',//这里是接收数据的PHP程序
		    	 data:'shopNum='+shopNum,
		    	 dataType:'text',//服务器返回的数据类型 可选XML ,Json jsonp script html text等
		    	 success:function(msg){
		    	    if(msg=='fail')
		    	    	text += "店铺信息已经存在  ";
			    	
			    	if(text != "")
				    	return alertMsg.warn(text);

					$("#shopNums").val($("#shopNum").val());
					$("#shopNames").val($("#shopName").val());	
					if(check())	
						$("#forms").submit();

		    	 },
		    	 error:function(){
		    	 }
		    });
		}
	}

</script>