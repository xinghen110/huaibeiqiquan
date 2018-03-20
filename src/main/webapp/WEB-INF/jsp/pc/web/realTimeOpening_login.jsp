<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<style>
	#loginName-error {
		color: red;
	}
	.login-reg{
		height: 600px!important;
	}

</style>
<html>
	<%@include file="header.jsp" %>
	<body>
		<%@include file="header_title.jsp" %>
		<div class="login-reg">
			<div class="black_bg">
				<h4 class="login-title color-white"><span class="fl border-bottom">快速登录</span><span id="regester" class="fl">快速注册</span></h4>
				<h4 class="color-white zhmm-title text-align"><img class="fl break" src="img/web/left-bark.png" />找回密码</h4>
				<div class="login"><!--登录-->
					<form id="login_form" action="web/stock/login" method="post">
					<h4 class="color-white size-9">账号</h4>
					<input id="login_loginName" type="text" placeholder="请输入手机号码" name="loginName" />
					<h4 class="color-white size-9">密码</h4>
					<input id="login_password" type="password" placeholder="请输入登录密码" name="loginPass" />
					<a class="fr color-white size-9 wjmm" style="margin-top: 10px; margin-right: 10px;">忘记密码?</a>
					<button id="login_button" style="background: #a97b52!important;color: white!important;" class="color-red" type="button">登录</button>
					</form>
				</div>
				
				<div class="reg" id="reg" style="display: none;" ><!--注册-->
					<form id="register" action="web/stock/register" method="post">
						<%--<input type="hidden" name="parentCode" id="parentCode"  value="">--%>
						<%--<h4 class="color-white size-9">姓名</h4>--%>
						<%--<input type="text" placeholder="请输入2-5个中文名称" name="nickName" />--%>
						<h4 class="color-white size-9">手机号</h4>
						<input id="loginName" type="text" maxlength="11" placeholder="请输入11位手机号" name="loginName"/>
						<h4 class="color-white size-9">登录密码</h4>
						<input id="password" type="password" placeholder="请输入登录密码" name="loginPass" required  minlength="6" data-msg-minlength="密码必须为6位以上"/>
						<h4 class="color-white size-9">确认密码</h4>
						<input id="confirm_password" type="password" placeholder="请确认登录密码" required equalTo="#password" />
						<h4 class="color-white size-9">邀请码</h4>
						<input id="parentCode" readonly type="text" placeholder="请输入邀请码" name="parentCode" value="${sessionScope.parentCode}" required />
						<h4 class="color-white size-9">验证码</h4>
						<input name="identityCode" type="text" placeholder="请输入收到的验证码" />
						<a class="reg-yzm" onclick="sendMessage()">获取验证码</a>
						<h4 class="color-white size-8">
							我已阅读并同意
							<a style="color: #a97b52!important;" class="color-red" <%--href="web/network/service/protocol"--%> target="_blank">《金点护航服务协议》</a>
							<input type="checkbox" style="width: 15px; height: 15px;" id="serviceProtocol" name="serviceProtocol" value="">
						</h4>
						<button style="background: #a97b52!important; color: white!important;" class="color-red" id="regist" type="button">注册</button>
					</form>
				</div>
				
				<div  class="zhmm-div" style="display: none;" ><!--找回密码-->
					<form id="zhmm-div" action="web/stock/password/forget" method="post">
					<h4 class="color-white size-9">手机号</h4>
					<input name="userTel"  id="login_name_find_password"  type="text" placeholder="请输入手机号" />
					<h4 class="color-white size-9">验证码</h4>
					<input id="identityCode" name="identityCode" type="text" placeholder="请输入收到的验证码" />
					<a class="zhmm-yzm" onclick="sendMessage2()">获取验证码</a>
					<h4 class="color-white size-9">登录密码</h4>
					<input id="newPassword" name="newPassword" type="password" placeholder="请输入登录密码" />
					<h4 class="color-white size-9">确认密码</h4>
					<input id="reNewPass" type="password" placeholder="请确认登录密码" />
					<button type="button" onclick="forget_password_submit_check();" style="background: #a97b52!important; color: white!important;" class="color-red">找回密码</button>
					</form>
				</div>
			</div>
		</div>
		<script>
			function forget_password_submit_check() {
				var isOk = false;
				if(!$("#login_name_find_password").val()){
				    isOk = false;
				    alert("请输入手机号");
				    return false;
				}else {
				    isOk = true;
				}
                if(!$("#identityCode").val()){
                    isOk = false;
                    alert("请输入验证码");
                    return false;
                }else {
                    isOk = true;
                }
                if(!$("#newPassword").val()){
                    isOk = false;
                    alert("请输入新密码");
                    return false;
                }else {
                    isOk = true;
                }
                if($("#reNewPass").val()!=$("#newPassword").val()){
                    isOk = false;
                    alert("两次密码不一致");
                    return false;
                }else {
                    isOk = true;
                }
                if(isOk){
                    $("#zhmm-div").submit();
				}

            }
		</script>
		
		<%@include file="footer.jsp" %>
		<script type="text/javascript">
			/**************************************登录前**************************************/
            var wH = window.screen.height; //获取屏幕高度
//			$(".login-reg").height(parseInt(wH)-338);
			$(".login-title span").click(function(){//点击切换
				$(".login-title span").removeClass("border-bottom")
				$(this).addClass('border-bottom')
				$t = $(this).index();
				$(this).parent().parent().find("div").hide()
				$(this).parent().parent().find("div").eq($t).show();
			});

			function login(){//登录跳转
				window.location.href='realTimeOpening_realName_authentication.html';
			}

			$(".wjmm").click(function(){//忘记密码
				$(".login-title").hide();
				$(".login").hide();
				$(".reg").hide();
				$(".zhmm-title").show();
				$(".zhmm-div").show();
			})
			
			$(".break").click(function(){//忘记密码-返回按钮
				$(".login-title").show();
				$(".login").show();
				$(".reg").hide();
				$(".zhmm-title").hide();
				$(".zhmm-div").hide();
			});
			
			//注册的验证码获取
			var InterValObj; //timer变量，控制时间
			var count = 60; //间隔函数，1秒执行
			var code = ""; //验证码
			function sendMessage() {
                	sendIdentityCodeToTelphone("loginName");
					$(".reg-yzm").removeAttr("onclick");
					//设置button效果，开始计时
					$(".reg-yzm").html("(" + count + ")秒内输入");
					InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
			}
			//timer处理函数
			function SetRemainTime() {
				if (count == 0) {        
					window.clearInterval(InterValObj);//停止计时器
					$(".reg-yzm").removeAttr("disabled");//启用按钮
					$("reg-yzm").html("请重新发送");
					count=60;
					$(".reg-yzm").attr("onclick","sendMessage()");
					code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效    
				}
				else {
					count--;
					$(".reg-yzm").html("(" + count + ")秒内输入");
				}
			}
			
			//找回密码的验证码获取
			var InterValObj2; //timer变量，控制时间
			var count2 = 60; //间隔函数，1秒执行
			var code2 = ""; //验证码
			function sendMessage2() {
                	sendIdentityCodeToTelphone("login_name_find_password");
					$(".zhmm-yzm").removeAttr("onclick");
					//设置button效果，开始计时
					$(".zhmm-yzm").html("(" + count2 + ")秒内输入");
					InterValObj2 = window.setInterval(SetRemainTime2, 1000); //启动计时器，1秒执行一次
			}
			//timer处理函数
			function SetRemainTime2() {
				if (count2 == 0) {        
					window.clearInterval(InterValObj2);//停止计时器
					$(".zhmm-yzm").removeAttr("disabled");//启用按钮
					$("zhmm-yzm").html("请重新发送");
					count2=60;
					$(".zhmm-yzm").attr("onclick","sendMessage2()");
					code2 = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效    
				}
				else {
					count2--;
					$(".zhmm-yzm").html("(" + count2 + ")秒内输入");
				}
			}
			$(function(){  /*调整窗口自动调整宽度*/   
		    	$(window).resize(function(){//实时获取宽高
		    		var ydl_img_H = $(".ydl-img").height();
					var ydl_img_W = $(".ydl-img").width();
					$(".grzx").height(ydl_img_H);
					$(".grzx").css("margin-top",-(ydl_img_H));
	    		});
			});

            $("#regist").click(function () {
                if(!($("#serviceProtocol").get(0).checked)){
                    alert("请先同意服务协议");
                    return;
                }else {
                    $("#register").submit();
				}
            });
//			function getUrlParam(name)
//			{
//				var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
//				var r = window.location.search.substr(1).match(reg);  //匹配目标参数
//				if (r!=null) return unescape(r[2]); return null; //返回参数值
//			}
//
//			var parentCode = getUrlParam('parentCode');
//			alert(parentCode);
			// 获取url请求参数的值
			(function($){
				$.getUrlParam
						= function(name)
				{
					var reg
							= new RegExp("(^|&)"+
							name +"=([^&]*)(&|$)");
					var r
							= window.location.search.substr(1).match(reg);
					if (r!=null) return unescape(r[2]); return null;
				}
			})(jQuery);

			var parentCode = $.getUrlParam('parentCode');
//			$("#parentCode").val(parentCode);

			if(parentCode !== null && parentCode !== undefined && parentCode !== "") {
				$(".login-title span").removeClass("border-bottom")
				$(".login-title #regester").addClass('border-bottom')
				$(".login-title span").parent().parent().find("div").hide()
				$(".black_bg #reg").show();
			}
//			if(msg === "推广链接失效，请重新获取链接") {
//				//			window.location.href = 'web/stock/login?parentCode='+parentCode;
//				$(".login-title span").removeClass("border-bottom")
//				$(".login-title #regester").addClass('border-bottom')
//				$(".login-title span").parent().parent().find("div").hide()
//				$(".black_bg #reg").show();
//			}

		</script>
		<script>
//			$.validator.setDefaults({
//				submitHandler: function() {
//					alert("submitted!");
//				}
//			});

			//配置错误提示的节点，默认为label，这里配置成 span （errorElement:'span'）
			$.validator.setDefaults({
				errorElement:'span'
			});

			//配置通用的默认提示语
			$.extend($.validator.messages, {
				required: '必填',
				equalTo: "请再次输入相同的值"
			});

			$().ready(function() {
				// 验证表单提交时
//				$("#register").validate();

				// 在提交signup KeyUp和形式验证
				$("#register").validate({
					rules: {
						loginName: {
							required: true,
							minlength: 11,
							maxlength:11
						},
						password: {
							required: true,
							minlength: 6
						},
						confirm_password: {
							required: true,
							minlength: 6,
							equalTo: "#password"
						}
					},
					messages: {
						loginName: {
							required: "登录名不能为空！",
							minlength: "登录名必须为11位数字",
							maxlength: "登录名必须为11位数字"
						},
						password: {
							required: "密码不能为空！",
							minlength: "密码必须为6位以上"
						},
						confirm_password: {
							required: "密码不能为空！",
							minlength: "密码必须为6位以上",
							equalTo: "密码必须一致"
						},
					}
				});

				//code to hide topic selection, disable for demo
				var newsletter = $("#newsletter");
				// newsletter topics are optional, hide at first
				var inital = newsletter.is(":checked");
				var topics = $("#newsletter_topics")[inital ? "removeClass" : "addClass"]("gray");
				var topicInputs = topics.find("input").attr("disabled", !inital);
				// show when newsletter is checked
				newsletter.click(function() {
					topics[this.checked ? "removeClass" : "addClass"]("gray");
					topicInputs.attr("disabled", !this.checked);
				});
			});


			function sendIdentityCodeToTelphone(idName) {
                $.ajax({
                    type: "post",
                    url: "web/user/register/send/identity/code",
                    data: {userTel:$("#"+idName).val()},
                    dataType: "json",
                    success: function(data){
                        console.log(data);
                    }
                });
            }

            $("#login_button").click(function () {
				var isOk = false;
				if(!$("#login_loginName").val()){
				    isOk = false;
				    alert("请先输入登录名");
				    return false;
				}else {
				    isOk = true;
				}

                if(!$("#login_password").val()){
                    isOk = false;
                    alert("请先输入密码");
                    return false;
                }else {
                    isOk = true;
                }

                if(isOk){
					$("#login_form").submit();
				}
            });
		</script>
	</body>
</html>
