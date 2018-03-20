<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>充值</title>
</head>
<%@include file="base.jsp" %>
<body>
<div data-role="page" id="recharge">
    <div data-role="header" style="border-width: 0; background: #28292E;">
        <a data-role="button" data-rel="back"><img src="img/mobile/icon_nav_back.png"/></a>
        <h4 class="color-w">充值结果</h4>
    </div>
    <div data-role="content">
        <%--	<div>
                <h4 class="fl">自有资金</h4>
                <h4 style="margin-left: 1em;" class="color-js">${userAccount.money}元</h4>
            </div>--%>
        <div>
            <h4 class="fl">充值金额</h4>
            <h4 style="float: right;" class="fr">元</h4>
            <input style="margin-left: 1em;" data-role="none" class="fl color-w size-9" type="number"
                   placeholder="输入充值金额" id="money"/>
        </div>

    </div>

</div>
</body>
</html>
