<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<title>提现</title>
	</head>
	<%@include file="base.jsp"%>
	<body>
		<div data-role="page" id="withdrawals">
			<div data-role = "header" style="border-width: 0; background: #28292E;">
				<a data-role="button" data-rel="back" ><img src="img/mobile/icon_nav_back.png"/></a>
				<h4 class="color-w">提现</h4>
			</div>
			<div data-role = "content">
				<form action="mobile/withdraw" method="post" data-ajax="false">
				<div>
					<h4 class="fl">可提金额</h4>
					<h4 style="margin-left: 1em;" class="color-js">${userAccount.money}元</h4>
				</div>
				<div>
					<h4 class="fl">提现金额</h4>
					<h4 style="float: right;" class="fr">元</h4>
					<input style="margin-left: 1em;" data-role = "none" class="fl color-w size-9" type="number" placeholder="输入提现金额" name="money"/>
				</div>
				<p style="padding: 1em;" class="size-9 color-w">
					温馨提示： <br />
					1.最快30分钟，一般3小时内到账，所有提款24小时内到账(节假日除外) <br />
					2.提款产生的银行手续费全免 <br />
					3.推荐您使用工商银行、建设银行、招商银行、农业银行提款，到账最快 <br />
					4.禁止洗钱、信用卡套现、虚假交易等行为，一经发现并确认，将终止该账户的使用 <br />
				</p>
				<%--<a class="ui-btn">确定</a>--%>
				<button type="submit" class="ui-btn">确定</button>
				</form>
			</div>
		</div>
	</body>
</html>
