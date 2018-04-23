<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ry" uri="http://www.ruanyun.com/taglib/ry" %>
<ry:binding type="1" bingdingName="STOCK_PLAN_CYCLE" parentCode="STOCK_PLAN_CYCLE"></ry:binding>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<base href="<%=basePath%>"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
		<link rel="stylesheet" href="http://gg.spqun.com/css/mobile/mystyle.css" />
		<link rel="stylesheet" href="http://gg.spqun.com/css/mobile/jquery.mobile-1.4.2.css" />
		<script type="text/javascript" src="http://gg.spqun.com/js/mobile/jquery.min.js" ></script>
		<script type="text/javascript" src="http://gg.spqun.com/js/mobile/jquery.mobile-1.4.2.js" ></script>
		<title></title>
	</head>
	<body>
		<div data-role="page" id="validation_scheme">
			<div data-role = "header" style="border-width: 0; background: #28292E;">
				<a data-role="button" data-rel="back" ><img src="img/mobile/icon_nav_back.png"/></a>
				<h4 class="color-w">确认方案</h4>
			</div>
			<div data-role = "content">
				<div class="content-div">
					<h4 class="color-w size-9 title">
						<span class="fl color-red">${planInfo.symbol}<span id="newSymbolName">${planInfo.symbolName}</span></span>
						<span class="fr">管理费率:${planInfo.manageFee}%</span>
					</h4>
					<h5 class="color-w size-8 content-h5">市值规模:<span>${planInfo.hiddenMarketValue}元</span></h5>
					<h5 class="color-w size-8 content-h5">股票现价:<span>${planInfo.curPrice}元</span></h5>
					<h5 class="color-w size-8 content-h5">管理期限:<span><ry:show parentCode="STOCK_PLAN_CYCLE" itemCode="${planInfo.stockPlanCycleValue}"></ry:show></span></h5>
					<%--<h5 class="color-w size-8 content-h5">建议买入价格:<span>${planInfo.buyPrice}元</span></h5>--%>
					<h5 class="color-w size-8 content-h5">需缴纳管理费:<span>${planInfo.payManageFee}元(一次性收取)</span></h5>
					<h5 class="color-w size-8 content-h5">方案申请日期:<span id="nowDate">2017-10-20</span></h5>
					<h5 class="color-w size-8 content-h5">方案结束日期:<span id="deadLine">2017-10-25</span></h5>
				</div>
				
				<div class="content-div">
					<h4 class="color-w size-9 title">
						<span class="fl color-red">确认信息</span>
					</h4>
					<p class="color-w size-8">
						支付管理费<span class="color-red">${planInfo.payManageFee}元</span>
						方案有效期<span class="color-red"><ry:show parentCode="STOCK_PLAN_CYCLE" itemCode="${planInfo.stockPlanCycleValue}"></ry:show></span>,
						确认无误后支付，投顾方案将进入审核队列并冻结管理费；审核不通过
						则全额退回管理费，审核通过只扣除管理费；投顾方案结束前不再产生
						额外费用。
					</p>
				</div>
				<form data-ajax="false" style="display: none" id="form" action="mobile/stock/plan/create" method="post">
					<input name="symbol" type="text" value="${planInfo.symbol}" id="symbol"/>
					<input name="curPrice" type="text" value="${planInfo.curPrice}" id="curPrice"/>
					<input name="symbolName" type="text" value="${planInfo.symbolName}">
					<%--<input name="manageFee" type="text" value="${planInfo.manageFee}"/>--%>
					<input name="buyMarketPrice" type="text" value="${planInfo.hiddenMarketValue}" id="hidden_market_value"/>
					<input name="cycle" id="stockPlanCycleValue" type="text"  value="${planInfo.stockPlanCycleValue}"/>
					<input name="buyPriceType" id="stockPriceType"   type="text"  value="${planInfo.stockPriceType}"/>
					<input name="buyLimitPrice" id="buyPrice"  type="text"  value="${planInfo.buyPrice}"/>
					<input name="manageFee" id="payManageFee"  type="text"  value="${planInfo.payManageFee}"/>
				</form>
				<div class="footer">
					<a id="toAddStockPlan" data-ajax='false' class="ui-btn color-w confirmation_application">确认申请</a>
					<a data-rel="back" class="ui-btn color-red">返回修改</a>
				</div>
			</div>
		</div>
	</body>
	<script src="https://cdn.bootcss.com/moment.js/2.19.0/moment.min.js"></script>

	<script>
	$("#toAddStockPlan").click(function () {
		$("#form").submit();
    });
	var cycle = '${planInfo.stockPlanCycleValue}';
	var tempCycle=cycle;
	var flag=tempCycle.substring(tempCycle.length-1);

    cycle=cycle.substring(0,cycle.length-1);

    $("#nowDate").html("");
    $("#nowDate").html(moment().format('YYYY-MM-DD'));
    $("#deadLine").html("");

    if(flag==="d"){
        $("#deadLine").html(moment().add(cycle,'day').format('YYYY-MM-DD'));

    }else if(flag==="m"){
        $("#deadLine").html(moment().add(cycle,'month').format('YYYY-MM-DD'));

    }else if(flag==="w"){
        $("#deadLine").html(moment().add(cycle,'week').format('YYYY-MM-DD'));

    }
</script>
</html>
