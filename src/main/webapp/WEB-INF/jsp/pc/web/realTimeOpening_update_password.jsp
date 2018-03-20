<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<%@include file="header.jsp" %>
<body>
	<%@include file="header_title.jsp" %>
	<div class="ydl"><!-- 已登录 -->
		<%@include file="header_personal.jsp" %>
		<div class="index1">
				<div class="index1-div">
					<div class="update-xgmm"><!--修改密码-->
						<form action="web/stock/password/update" method="post">
							<h4 style="width: 100%;margin: 0;" class="text-align color-white title">修改密码</h4>
							<img class="fl break-data" src="img/left-bark.png" style="width: 18px;cursor: pointer; margin-top: -28px;margin-left: 20px;" />
							<h4 class="color-white size-9 clear">手机号</h4>
							<input id="loginName" name="loginName" type="text" placeholder="请输入手机号" />
							<h4 class="color-white size-9">验证码</h4>
							<input name="identityCode" type="text" placeholder="请输入收到的验证码" />
							<a class="zhmm-yzm" onclick="sendMessage2()">获取验证码</a>
							<h4 class="color-white size-9">登录密码</h4>
							<input name="password" type="password" placeholder="请输入登录密码" />
							<h4 class="color-white size-9">确认密码</h4>
							<input type="password" placeholder="请确认登录密码" />
							<button class="color-red">确定修改</button>
						</form>
					</div>
				</div>	
			</div>
		</div>
		
		<%@include file="footer.jsp"%>
		<script type="text/javascript">
			var wH = $(window).height(); //获取屏幕高度
			hqkg();
			/**************************************登录后**************************************/
			$(".grzx .exit-login").click(function(){//退出登录
				window.location.href='realTimeOpening_login.html';
			});
			$('.break-data').click(function(){//修改密码-返回按钮
		    	window.location.href = 'realTimeOpening_realName_authentication.html';
		    });
			function hqkg(){
				var ydl_img_H = $(".ydl-img").height();
				var ydl_img_W = $(".ydl-img").width();
				$(".grzx").height(ydl_img_H);
				$(".grzx").css("margin-top",-(ydl_img_H));
			}
			
			//修改密码的验证码获取
			var InterValObj2; //timer变量，控制时间
			var count2 = 60; //间隔函数，1秒执行
			var code2 = ""; //验证码
			function sendMessage2() {
                	sendIdentityCodeToTelphone("loginName");
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
			
			function clickFile(obj){ //点击触发input file
				$(obj).parent().find("input[type='file']").click();
			}
			function qrFile(obj){//选中图片时的判断
				var file = obj.files[0]; 
		   		//判断类型是不是图片  
			    if(!/image\/\w+/.test(file.type)){     
		            alert("请确保文件为图像类型");   
		            return false;
			    }    
				var fileName="";  
	            if(typeof(fileName) != "undefined")  
	            {  
	                fileName = $(obj).val().split("\\").pop();  
	                fileName=fileName.substring(0, fileName.lastIndexOf("."));  
	            }  
            	$(obj).parent().find("input[type='text']").val(fileName);
			}
		    
			$(function(){  /*调整窗口自动调整宽度*/   
		    	$(window).resize(function(){//实时获取宽高
		    		var ydl_img_H = $(".ydl-img").height();
					var ydl_img_W = $(".ydl-img").width();
					$(".grzx").height(ydl_img_H);
					$(".grzx").css("margin-top",-(ydl_img_H));
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
		</script>
	</body>
</html>
