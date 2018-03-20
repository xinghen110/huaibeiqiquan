<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>修改手机号</title>
</head>
	<body>
		<div data-role="page" id="modify-nickname">
			<div data-role="header" style="background: white;">
			 <a data-role="button" data-rel="back"><img src="app/img/icon_nav_back.png"/></a>
			 	<h4 class="text-shadow">修改手机号</h4>
			</div>
			<div data-role = "content">
				<div>
					<input type="number"  name="userPhone" id="userPhone" placeholder="修改手机号"  maxlength="11">
					<a data-role="button" onclick="update()">确认修改</a>
				</div>
		    </div>
		</div>
	</body>
<script type="text/javascript">
function update() {
	var userPhone = $("#userPhone").val();
	if (userPhone.length == 11) {
	    window.location.href="app/page/updateUser?userNum=${userNum}&userPhone=" + userPhone;
	} else {
	    alert("请输入正确格式的手机号！");
    }
}
</script>
</html>