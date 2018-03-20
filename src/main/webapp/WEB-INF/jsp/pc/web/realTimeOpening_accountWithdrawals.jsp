<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<%@include file="header.jsp" %>
<body>
	<%@include file="header_title.jsp" %>
	<div class="ydl"><!-- 已登录 -->
		<%@include file="header_personal.jsp" %>
		<div class="index1"><!--账户提现-->
				<div class="index1-div" style="height: 450px!important;">
					<form action="web/stock/withdraw" method="post">
					<h4 class="text-align color-white title">银行卡提现</h4>
					<div class="index1-div-div">
						<div>
							<h4 class="fl color-white">可提现资金：</h4>   
							<h5 class="fl color-white">${userAccount.money}</h5>
						</div>
						<div>
							<h4 class="fl color-white">提现金额：</h4>   
							<input class="fl" type="number" placeholder="请输入提现金额" name="money" />
							<h6 style="float: left; margin-left: -30px; margin-top: 3px;color: white;">元</h6>
						</div>
					</div>
					<%--<h5 class="color-white size-9" style="padding: 0px 55px;margin-bottom: 10px;margin-top: -25px;">温馨提示:</h5>--%>
					<%--<p class="color-white size-8" style="padding: 0px 55px;margin-bottom: 17px;line-height: 30px;">--%>
						<%--1.为了您的资金安全，您的账号资金将专款专户管理<br />--%>
						<%--2.为了您的资金安全，您的账号资金将专款专户管理<br />--%>
						<%--3.为了您的资金安全，您的账号资金将专款专户管理<br />--%>
						<%--4.为了您的资金安全，您的账号资金将专款专户管理--%>
					<%--</p>--%>
					<div style="width: 500px; margin: 0 auto;">
						<h5 class="color-white size-9" style="padding: 0px 55px;margin-bottom: 10px;">温馨提示:</h5>
						<div class="color-white size-8" style="padding: 0px 55px;margin-bottom: 17px;line-height: 30px;">
							<p><span style="color: red;">*</span>&nbsp;&nbsp;1.为了您的资金安全，您的账号资金将专款专户管理</p>
							<p><span style="color: red;">*</span>&nbsp;&nbsp;2.为了您的资金安全，您的账号资金将专款专户管理</p>
							<p><span style="color: red;">*</span>&nbsp;&nbsp;3.为了您的资金安全，您的账号资金将专款专户管理</p>
							<p><span style="color: red;">*</span>&nbsp;&nbsp;4.为了您的资金安全，您的账号资金将专款专户管理</p>
							<%--<p><span style="color: red;">*</span>&nbsp;&nbsp;我们会在到账后15分钟内帮您进行充值处理。因此给您代理不便，敬请谅解！</p>--%>
						</div>
					</div>
					<div class="text-align">
						<button type="submit">确认</button>
					</div>
					</form>
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
			 $('.update-password').click(function(){//修改密码
		    	window.location.href = 'realTimeOpening_update_password.html'
		    });
			function hqkg(){
				var ydl_img_H = $(".ydl-img").height();
				var ydl_img_W = $(".ydl-img").width();
				$(".grzx").height(ydl_img_H);
				$(".grzx").css("margin-top",-(ydl_img_H));
			}
			$(function(){  /*调整窗口自动调整宽度*/   
		    	$(window).resize(function(){//实时获取宽高
		    		var ydl_img_H = $(".ydl-img").height();
					var ydl_img_W = $(".ydl-img").width();
					$(".grzx").height(ydl_img_H);
					$(".grzx").css("margin-top",-(ydl_img_H));
	    		});
			});
		</script>
	</body>
</html>
