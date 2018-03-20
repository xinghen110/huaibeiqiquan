<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>
<ry:binding type="3"></ry:binding>
<ry:binding parentCode="BANK_NAME,APPLICATION_STATUS" bingdingName="banknames,applicationstatus"></ry:binding>
<script type="text/javascript">
	var reg = /^[\u0391-\uFFE5]+$/;
		function checkuserName(){
		var userName= $("#userName").val();
		
		if(!reg.test(userName)){
				alert("请输入汉字");
			}else{
				
				}
	
	}
</script>
<div class="pageContent">
<form method="post" action="userapplication/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">

<dl style="width: 750px;" >
	<dt>申请人名称：</dt>
	<c:if test="${not empty bean.userName}">${bean.userName}</c:if>
	<c:if test="${empty bean.userName}">
	<dd style="width: 550px;"><input type="text" id="userName" onblur="checkuserName()" name="userName" value="${bean.userName }" class="required" title="提现人名称" maxlength="30" ><span style="color: gray;"></span></dd>
	</c:if>
</dl>

<dl style="width: 750px;" >	
	<dt>申请金额：</dt>
	<c:if test="${not empty bean.money}">${bean.money}</c:if>
	<c:if test="${empty bean.money}">
	<dd style="width: 560px;"><input type="text" name="money" class="required" value="${bean.money }" ><span style="color: gray;"></span>
	</dd>
	</c:if>
</dl>



<dl style="width: 750px;" >
	<dt>提现银行：</dt>
	<ry:show parentCode="BANK_NAME" itemCode="${bean.bankName}"></ry:show>
</dl>


<dl style="width: 750px;" >
	<dt>银行卡号：</dt>
	<c:if test="${not empty bean.accountNumber}">${bean.accountNumber}</c:if>
	<c:if test="${empty bean.accountNumber}">
	<dd style="width: 560px;"><input type="text" name="accountNumber" class="required" value="${bean.accountNumber}" class="creditcard" title="请输入正确的银行卡号" ><span style="color: gray;"></span></dd>
	</c:if>
</dl>
<ry:authorize ifAnyGranted="${authMap.SYSTEM_AUTH}" >
<input type="hidden" value="${userNum }"/>
<input type="hidden" value="${userType}"/>
<c:if test="${empty bean.handleTime}">
<dl>
<dt>申请状态：</dt>
<dd><span>已提交</span></dd>
</dl>
</c:if>
	<dl style="width: 750px;">
		<dt>处理状态：</dt>
		<select name="status" onchange="checkstatus()" id="status">
			<option value="" >请选择处理状态</option>
			<option value="1" <c:if test="${1 == bean.status}">selected="selected"</c:if>>提现成功</option>
			<option value="-1" <c:if test="${-1 == bean.status}">selected="selected"</c:if>>提现失败</option>
		</select>
	</dl>
	
	<dl   style="width: 750px;height: 140px;" >
	<dt hidden="" id="handleremark"><span style="color: red;">*</span>失败原因：</dt>
	<dd >
		<textarea hidden="" type="text" id="handleRemark" style="width: 500px;height:100px" name="handleRemark" onblur="checkremark();" ></textarea>
	</dd>
</dl>

</ry:authorize>
</div>
<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="todo()">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
		<input type="hidden" name="userApplicationId" value="${bean.userApplicationId }">
		
</form>
</div>
<script>
function checkstatus(){
	var status=document.getElementById("status").value ;
	if(status==-1){
		$("#handleremark").show();
		$("#handleRemark").show();
	}
	if(status==1){
		$("#handleremark").hide();
		$("#handleRemark").hide();
		$("#handleRemark").val("");
	}
}
function checkremark(){
var reg = /^\s*$/;
if(reg.test($("#handleRemark").val())){
	return false;
}else{
	return true;
}
}
function todo(){
	var status=document.getElementById("status").value;
	if(status==-1){
		if(checkremark()==true){
		$("#forms").submit();
		}else{
			alertMsg.warn("请填写备注");
		}
	}else{
		$("#forms").submit();
	}
	
}

</script>

