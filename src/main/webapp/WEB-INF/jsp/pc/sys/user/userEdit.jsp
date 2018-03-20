<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>

<ry:binding parentCode="USERSEX,ORG_TYPE" bingdingName="usersexs,orgTypeList"></ry:binding>
<div class="pageContent">
	<form method="post" action="admin/user/update?userType=${user.userType}&urlType=${param.invite}" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this, navTabAjaxDone);" enctype="multipart/form-data">
		<div class="pageFormContent nowrap" layoutH="57">
			<input type="hidden" name="urlType" value="${urlType}"/>
			<dl>
				<dt><c:if test="${empty user.loginName}"><span style="color: red;">*</span></c:if>登录名称：</dt>
				<dd>
				<c:if test="${not empty user.loginName}">${user.loginName}</c:if>
				<c:if test="${empty user.loginName}">
					<input type="text" id="loginName" name="loginName"  value="${user.loginName}" maxlength="20" size="30" class="mustFill" title="登录名称" alt="请输入登录名称" onblur="searchAjaxName();" />
					<span id="fali_show" class="info" style="color: red;display:none;">登录名已被使用，请重新输入</span>
				</c:if>
				</dd>
			</dl>
			<c:if test="${param.userType<5}">
			<dl>
				<dt><span style="color: red;">*</span>用户昵称：</dt>
				<dd>
					<input type="text" name="nickName" value="${user.nickName}" size="30" class="mustFill" title="真实姓名" alt="请输入真实姓名"/>
				</dd>
			</dl>
			</c:if>
			<dl>
				<dt>用户性别：</dt>
				<dd>
				    <c:forEach items="${usersexs}" var="item">
					   <input type="radio" value="${item.itemCode }" name="userSex" ${item.itemCode==user.userSex ? 'checked':(empty user.userSex ? 'checked':'') } >${item.itemName }
		            </c:forEach>
				</dd>
			</dl>
			<dl>
				<dt><span style="color: red;">*</span>电话号码：</dt>
				<dd>
					<input type="text" name="userPhone" value="${user.userPhone}" size="30" class="mustFill phone " maxlength="11" title="电话号码" alt="请输入电话号码" />
				</dd>
			</dl>
			
		<%--<dl style="width:750px;" >	--%>
		<%--<dt ><span style="color: red;">*</span>区域：</dt>--%>
		<%--<dd style="width: 300px">--%>
			<%--<%@include file="/WEB-INF/jsp/inc/auserRegionalCascadeEdit.jsp" %>--%>
			<%--<input type="hidden" id="provinceid_edit"  value="${user.province}" />--%>
			<%--<input type="hidden" id="citiesid_edit"   value="${user.city}" />--%>
			<%--<input type="hidden" id="areaid_edit"   value="${user.area}" />--%>
		<%--</dd>--%>
		<%--</dl>	--%>
			<c:if test="${param.userType==1}">
				<dl style="width:750px;" >
					<dt ><span style="color: red;">*</span>用户角色：</dt>
					<dd style="width: 300px">
						<select name="roleId">
							<option value="">请选择用户角色</option>
						<c:forEach items="${roleList}" var="item">
								<c:if test="${item.roleId > 5 ||item.roleId ==1}">
									<option ${item.roleId==userRole.roleId ? "selected" :""} value="${item.roleId}">${item.roleName}</option>
								</c:if>
							</c:forEach>
						</select>
					</dd>
				</dl>
			</c:if>
			
			</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="formSubmit();">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
		<!-- 隐藏的值 -->
		<input type="hidden" name="userId" value="${user.userId}"/>
	</form>
</div>
<script>
var isVal=true;
  function searchAjaxName(){
	     var loginName=$('#loginName').val();
	     if(loginName!='${user.loginName}'){
		     $.ajax({
		    	 type:'post',//可选get
		    	 url:'user/searchAjaxName',//这里是接收数据的PHP程序
		    	 data:'loginName='+loginName,//传给PHP的数据，多个参数用&连接
		    	 dataType:'text',//服务器返回的数据类型 可选XML ,Json jsonp script html text等
		    	 success:function(msg){
		    	    if(msg=='fail'){
			    	    isVal=false;
			    	    $('#fali_show').show();
			    	}else{
			    		isVal=true;
			    		$('#fali_show').hide();
	
				    }
		    	 },
		    	 error:function(){
		    	 }
		    });
	   }else{
		   isVal=true;
		   $('#fali_show').hide();
		  }
	    	 	     
	}
	
	function formSubmit(){
		if(isVal==false){
			  $('#fali_show').show();
			  return false;
	    }else{
	    	if(check())
            $('#forms').submit();
		}

	}
</script>