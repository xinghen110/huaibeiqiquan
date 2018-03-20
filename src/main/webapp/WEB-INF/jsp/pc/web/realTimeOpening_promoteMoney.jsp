<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<%@include file="header.jsp" %>
	<body>
		<%@include file="header_title.jsp" %>
		<div class="ydl"><!-- 已登录 -->
			<%@include file="header_personal.jsp" %>

			<div class="index1"><!--推广链接-->
				<div class="index1-div" style="height: auto!important;padding-bottom: 30px;">
					<h4 class="text-align color-white title">推广链接</h4>
					<input id="copy-text" type="text" readonly="readonly" value="<%=basePath%>web/stock/register?parentCode=${sessionScope.systemUser.userId}" />
					<div class="text-align">
						<button id="btn">点击复制</button>
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
			 $('.update-password').click(function(){//修改密码
		    	window.location.href = 'realTimeOpening_update_password.html'
		    });
			function hqkg(){
				var ydl_img_H = $(".ydl-img").height();
				var ydl_img_W = $(".ydl-img").width();
				$(".grzx").height(ydl_img_H);
				$(".grzx").css("margin-top",-(ydl_img_H));
			}
			
			 var btn = document.getElementById('btn');
		    btn.addEventListener('click', function(){//点击复制链接地址
		        document.getElementById("copy-text").focus();
				document.getElementById("copy-text").select();
		        document.execCommand('copy', true);
		        alert("复制成功！")
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
