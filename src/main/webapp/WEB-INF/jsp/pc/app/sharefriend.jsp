	<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
    <title>分享</title>
	</head>
	<body>
	<style>


	</style>
		<form  action="" id="forms" >
			<img src="${constants.QINIU_USER_IMGURL}${user.userPhoto}" style="width: 100%;"/>
			<div class="login_box">
				<input type="hidden" name="parentCode" value="${user.userNum}"/>
				<label style="top: 24.9rem;left: 6%;position: absolute;">姓名</label>
				<input type="text" name="nickName" id="nickName" maxlength="11" placeholder="请输入姓名"  style="text-indent: 5.5rem;width: 90%;margin: .22rem 5% .22rem"/>
				<label style="top: 28.1rem;left: 6%;position: absolute;">手机号</label>
				<input type="number" name="loginName" id="loginName" onkeyup="this.value=this.value.replace(/\D/g,'')"   maxlength="11" placeholder="请输入手机号"  style="text-indent: 5.5rem;width: 90%;margin: .22rem 5% .22rem"/>
				<label style="top: 31.5rem;left: 6%;position: absolute;">验证码</label>
				<input type="number" name="yanzhengma" id="yanzhengma"   placeholder="请输入验证码" style="text-indent: 5.5rem; width: 90%;margin: .22rem 5% .22rem"/>
				<a style=" text-shadow: none;font-weight: normal;position: absolute;right: 0; margin-top: -4em;  background: #31B16C; color: white;  font-size: .8em; height: 2.6rem; line-height: 2.6rem; padding: 0 .5rem;" onclick="sendMessage2()" class="yzm">获取验证码</a>
				<label style="top: 34.7rem;left: 6%;position: absolute;">登录密码</label>
				<input type="password" name="loginPass" id="loginPass"   placeholder="请输入登录密码" style="text-indent: 5.5rem; width: 90%;margin: .22rem 5% .22rem"/>
				<label style="top: 37.95rem;left: 6%;position: absolute;">确认密码</label>
				<input type="password" name="qrloginPass" id="qrloginPass"   placeholder="请输入确认密码" style="text-indent: 5.5rem;width: 90%;margin: .22rem 5% .22rem"/>
			</div>
			<button style="background: #31B16C;color: white;font-weight: normal; text-shadow: none;border: none;border-radius: 0; margin: 0;margin-left: 2.05em; margin-top: 1em;width: 80%" class="login_btn" onclick="zc()">注&nbsp;册</button>
			<input type="hidden" id="trueYzm">
			</form>
		<div class="ui-form-explain" id="error" style="color: red;height: 0px;text-align: center; margin-top: 10px;">${error}</div>
		<script type="text/javascript">
		var InterValObj; //timer变量，控制时间
		var count = 30; //间隔函数，1秒执行
		var code = ""; //验证码
		var codeLength = 6;//验证码长度
		function sendMessage2() {
				var nickName = $("#nickName").val();
				var loginName = $("#loginName").val();
				if(loginName =="" || nickName == "" ){
					$("#error").text("请填写完整信息!");
					return false;
				}
				if(loginName.length != 11){
					$("#error").text("手机号格式错误!");
					return false;
				}
					$(".yzm").removeAttr('onclick');
					$(".yzm").html(count + "秒内输入");
					InterValObj = window.setInterval(SetRemainTime2, 1000); //启动计时器，1秒执行一次
					var re = /^1\d{10}$/;
			        if (re.test(loginName)) {  
			        	$.ajax({
							 type : "get",  
					         url : "app/external/"+loginName+"/user/share?userType=3&type=2&loginName="+loginName,  
					         dataType: "text",
					         success : function(data) {  
					         	if(data == "error"){
					         		$("#error").text("短信发送次数超过五次，请改日再进行操作");
							    }else{
						         	 $("#trueYzm").val(data);
							    }
					         	if(data == -1){
					         		alert("该手机号已经注册了");
					         	}
					         	if(data == 1){
					         		alert("发送成功");
					         	}
								    
					         },  
					         error : function() {  
					            alert("手机号已经注册了");  
					         }  
						});
			        } else {  
			        	$("#error").text("手机号格式错误！");  
			            $("#loginName").val('');
			        }  
		}
		
		//timer处理函数
		function SetRemainTime2() {
			if (count == 0) {                
				window.clearInterval(InterValObj);//停止计时器
				$(".yzm").removeAttr("disabled");//启用按钮
				$(".yzm").html("重新发送");
				$(".yzm").attr('onclick','sendMessage2()');
				count = 60;
			}
			else {
				count--;
				$(".yzm").html(count + "秒内输入");
			}
		}
		/* 注册 */
		function zc(){
			var trueYzm = $('#trueYzm').val();
			
			var nickName = $("#nickName").val();
			var loginName = $("#loginName").val();
			var password = $("#loginPass").val();
			var qrloginPass = $("#qrloginPass").val();
			var yanzhengma = $('#yanzhengma').val();
			if(nickName == "" || loginName.length < 1 || qrloginPass == "" || password == "" || yanzhengma == ""){
				alert("请填写完整信息!")
			}else if(loginName.length !=11){
				alert("手机号格式错误!")
			}else if(trueYzm != yanzhengma){
				alert("验证码不正确!")
			}else if(password != qrloginPass){
				alert("登陆密码与确认密码不一致!")
			}else{
				var trueYzm=$("#trueYzm").val();
				var formData = $("#forms").serialize();
				formData =decodeURIComponent(formData,true); 
				 var result = new MyJqueryAjax('app/external/'+loginName+'/user/add?trueYzm='+trueYzm+'&userType=3',formData,null,'text').request();//全民经纪人注册
				 if(result==-1){
					alert("此手机号已注册成为经纪人!"); 
				 }if(result==-2){
					 alert("验证码输入错误!"); 	
				 }
				 if(result==1){
					 alert("注册成功!")
				 }
				 if(result==0){
					 alert("注册失败!")
				 }
			}
		}

		</script>
	</body>
	
</html>