<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>

 <link rel="stylesheet" href="select/css/chose.css"/>
 <script type="text/javascript" src="select/chosen.jquery.min.js"></script> 
  <script type="text/javascript">
            $(".chosen-select").chosen();
        </script>
<div class="pageContent">
<form method="post" action="cardfee/saveCardFee" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">

<div class="pageFormContent nowrap" layoutH="60">
	
		<dl  style="width: 400px;">
		<c:if test="${bean.cardType==1}">
			<dt ><span style="color: red;">*</span>年费金额：</dt>
			<dd><input type="text" name="cardFee" id="cardFee1"  maxlength="4" class="number" title="请输入年费金额" onchange="cardFees(1)"  value="${bean.cardFee}"></dd>
		</c:if>
		<c:if test="${bean.cardType==2}">
			<dt ><span style="color: red;">*</span>分销比例：</dt>
			<dd><input type="text" id="cardFee2"  name="cardFee" class="number" title="请输入分销比例" onchange="cardFees(2)" value="${bean.cardFee}"></dd>
		</c:if>
		</dl>
		<input type="hidden" name=cardType value="${bean.cardType}">
		<input type="hidden" name=cardFeeNum value="${bean.cardFeeNum}">
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
var isVlea =false;
function cardFees(num){
	if(num==1){
		var cardFee1=$.trim($("#cardFee1").val());
		 if(cardFee1==null || cardFee1==""){
			 alertMsg.warn("请填写年费金额")
			 return isVlea;
		 }else{
			 	isVlea=true;
				return  isVlea;
		 }
	}
	if(num==2){
		var cardFee=$.trim($("#cardFee2").val());
		if(cardFee==null || cardFee==""){
			alertMsg.warn("请填写分销比例");
			return isVlea;
		}else{
			if(cardFee<0 ||cardFee>100){
				alertMsg.warn("请输入0~100的值");
				return isVlea;
			}else{
				isVlea=true;
				return  isVlea;
			}
		}

	}
}

	
function todo(){
	if(isVlea==true){
	   if(check())	
			$("#forms").submit();
		}else{
			alertMsg.warn("请检查你所填写的值");
		}
	
}

</script>