<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@include file="header.jsp" %>
<body>
	<%@include file="header_title.jsp" %>
		<div class="ydl"><!-- 已登录 -->
			<%@include file="header_personal.jsp" %>
			<div class="index1">
				<div class="index1-div"><!--个人资料-->
					<div class="smrz">
						<h4 class="text-align color-white title">个人资料</h4>
						<div class="index1-div-div">
							<div>
								<h4 class="fl color-white">姓名：</h4>
								<h5 style="margin-top: 2px;" class="fl color-white">${userInfo.userName}</h5>
							</div>
							<div>
								<h4 class="fl color-white">身份证号：</h4>   
								<h5 style="margin-top: 2px;" class="fl color-white">${userInfo.idNumber}</h5>
							</div>
							<div>
								<h4 class="fl color-white">所属银行：</h4>
								<h5 style="margin-top: 2px;" class="fl color-white">${userInfo.bankId}</h5>
							</div>
							<div>
								<h4 class="fl color-white">银行卡号：</h4>   
								<h5 style="margin-top: 2px;" class="fl color-white">${userInfo.bankCardNumber}</h5>
							</div>
						</div>
						<div style="margin-top: 250px;" class="text-align">
							<a href="web/stock/authentication" class="changingBank_cards color-white">绑定银行卡</a>
						</div>
					</div>
				</div>	
			</div>
		</div>


		<%@include file="footer.jsp" %>

		<script type="text/javascript">
			var wH = $(window).height(); //获取屏幕高度
			hqkg();
			/**************************************登录后**************************************/
			$(".grzx .exit-login").click(function(){//退出登录
				window.location.href='realTimeOpening_login.html';
			});
			function hqkg(){//获取个人资料头部的img宽高
				var ydl_img_H = $(".ydl-img").height();
				var ydl_img_W = $(".ydl-img").width();
				$(".grzx").height(ydl_img_H);
				$(".grzx").css("margin-top",-(ydl_img_H));
			}
			$('.changingBank_cards').click(function(){//换绑银行卡
				window.location.href = 'realTimeOpening_changingBank_cards.html'
		    });
		    $('.update-password').click(function(){//修改密码
		    	window.location.href = 'realTimeOpening_update_password.html'
		    });
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
