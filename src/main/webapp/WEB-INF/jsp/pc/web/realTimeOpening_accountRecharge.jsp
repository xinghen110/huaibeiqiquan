<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<%@include file="header.jsp" %>
	<body>
	<%@include file="header_title.jsp" %>
	<div class="ydl"><!-- 已登录 -->
		<%@include file="header_personal.jsp" %>
		<div class="index1"><!-- 账户充值 -->
				<div class="index1-div">
					<form action="web/stock/payeasy/deposit" method="post">
					<h4 class="text-align color-white title">银行卡支付</h4>
					<div class="index1-div-div">
						<div>
							<h4 class="fl color-white">自有资金：</h4>   
							<h5 class="fl color-white">${userAccount.money}</h5>
						</div>
						<div>
							<h4 class="fl color-white">充值金额：</h4>   
							<input class="fl" type="number" placeholder="请输入充值金额" name="money" />
							<h6 style="float: left; margin-left: -30px; margin-top: 3px;color: white;">元</h6>
						</div>
						<div>
							<h4 class="fl color-white">选择充值方式：</h4>   
							<select name="payType">
								<%--<option>请选择</option>--%>
								<%--<option>微信</option>--%>
								<option value="alipay">支付宝</option>
								<%--<option>银行卡</option>--%>
							</select>
						</div>
					</div>
					<div style="width: 500px; margin: 0 auto;">
						<h5 class="color-white size-9" style="padding: 0px 55px;margin-bottom: 10px;">温馨提示:</h5>
						<div class="color-white size-8" style="padding: 0px 55px;margin-bottom: 17px;line-height: 30px;">
							<p><span style="color: red;">*</span>&nbsp;&nbsp;因线上充值暂未开放，如需充值请使用绑定银行卡汇款至公司法人账户，汇款信息如下：</p>
							<p><span style="color: red;">*</span>&nbsp;&nbsp;户名：镇江满茂商贸有限公司</p>
							<p><span style="color: red;">*</span>&nbsp;&nbsp;银行卡号：客服索要</p>
							<p><span style="color: red;">*</span>&nbsp;&nbsp;开户行：客服索要</p>
							<p><span style="color: red;">*</span>&nbsp;&nbsp;我们会在到账后15分钟内帮您进行充值处理。因此给您带来不便，敬请谅解！</p>
						</div>
					</div>

					<div class="text-align">
						<button type="submit" disabled>确认</button>
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
