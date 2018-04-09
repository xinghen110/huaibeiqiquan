<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>实名绑卡</title>
	</head>
	<%@include file="base.jsp"%>
	<body>
		<div data-role="page" id="real_name_binding_card_success">
			<div data-role = "header" style="border-width: 0; background: #28292E;">
				<a data-role="button" data-rel="back" ><img src="img/mobile/icon_nav_back.png"/></a>
				<h4 class="color-w">实名绑卡</h4>
			</div>
			<div data-role = "content">
				<div>
					<h4 class="fl">姓名</h4>
					<h4 class="color-w img-name">${userInfo.userName}</h4>
				</div>
				<div>
					<h4 class="fl">身份证号码</h4>
					<h4 class="color-w img-name">${userInfo.idNumber}</h4>
				</div>
				<div>
					<h4 class="fl">银行卡卡号</h4>
					<h4 class="color-w img-name">${userInfo.bankCardNumber}</h4>
				</div>
				<div>
					<h4 class="fl">所属银行</h4>
					<h4 class="color-w img-name">${userInfo.bankId}</h4>
				</div>
				
				<a data-ajax = "false" href='mobile/update/bandingcard' class="ui-btn" style="background-color: #c33f00!important;">换绑银行卡</a>
			</div>
		</div>
	</body>
</html>
