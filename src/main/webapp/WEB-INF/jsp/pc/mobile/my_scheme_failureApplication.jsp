<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ry" uri="http://www.ruanyun.com/taglib/ry" %>
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
	<link rel="stylesheet" href="css/mobile/mystyle.css" />
	<link rel="stylesheet" href="css/mobile/jquery.mobile-1.4.2.css" />
	<script type="text/javascript" src="js/mobile/jquery.min.js" ></script>
	<script type="text/javascript" src="js/mobile/jquery.mobile-1.4.2.js" ></script>
	<title></title>
</head>
	<body>
		<div data-role="page" id="my_scheme_failureApplication">
			<div data-role = "header" style="border-width: 0; background: #28292E;">
				<h4 class="color-red">我的方案</h4><!--申请失败-->
			</div>
			<div data-role = "content">
				<h4 class="color-w choose_title size-9">
					<a data-ajax = "false" href="mobile/stock/plan/list?orderStatus=1" class="fl">申请中</a>
					<a data-ajax = "false" href="mobile/stock/plan/list?orderStatus=2" class="fl">持仓中</a>
					<a data-ajax = "false" href="mobile/stock/plan/list?orderStatus=3" class="fl">行权中</a>
					<a data-ajax = "false" href="mobile/stock/plan/list?orderStatus=4" class="fl">已结算</a>
					<a data-ajax = "false" href="mobile/stock/plan/list?orderStatus=-1" class="fl seleced_title">申请失败</a>
				</h4>
				<div style="overflow: auto;">
					<table cellspacing="0" cellpadding="0">
						<tr>
							<th class="color-w size-9">订单号</th>
							<th class="color-w size-9 two">股票名称</th>
							<th class="color-w size-9">股票代码</th>
							<th class="color-w size-9">名义本金</th>
							<th class="color-w size-9">买入类型</th>
							<th class="color-w size-9">买入限价</th>
							<th class="color-w size-9">申买时间</th>
							<th class="color-w size-9">管理费</th>
						</tr>
						<c:forEach var="item" items="${stockPlanListMap}">
							<tr>
								<td class="color-w size-9">${item.planId}</td>
								<td class="color-w size-9 two">${item.symbol}</td>
								<td class="color-w size-9">${item.symbolName}</td>
								<td class="color-w size-9">${item.buyMarketPrice}</td>
								<td class="color-w size-9">${item.buyPriceTypeName}</td>
								<td class="color-w size-9">${item.buyPriceLimt}</td>
								<td class="color-w size-9">${item.createTime}</td>
								<td class="color-w size-9">${item.fee}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<!-- 底部 -->
				<div class="footer">
					<div class="fl">
						<a href="mobile/option/labels" data-ajax = "false">
							<img src="img/mobile/tab_select_white.png" />
							<h4 class="color-w size-9">自选股</h4>
						</a>
					</div>
					<div class="fl">
						<a href="mobile/stock/plan/list?orderStatus=1" data-ajax = "false">
							<img src="img/mobile/tab_trade_red.png" />
							<h4 class="color-red size-9">持仓</h4>
						</a>
					</div>
					<div class="fl">
						<a href="mobile/stock/userinfo" data-ajax = "false">
							<img src="img/mobile/tab_user_white.png" />
							<h4 class="color-w size-9">我的</h4>
						</a>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			$(window).ready(function(){//每次加载动态获取table宽度
				var windowW = $(window).width();
				var i = 0;
				$('#my_scheme_failureApplication table tr th').each(function(){
					i++;
				})
			    var thW = windowW*0.25;//每个th的宽度
			    $('#my_scheme_failureApplication table th').css('width',windowW*0.25);//th宽度赋值
			     $('#my_scheme_failureApplication table td').css('width',windowW*0.25);//th宽度赋值
			   // $('#my_scheme_failureApplication .two').css('margin-left',windowW*0.25)//设置第二个th的margin-left
				//计算总的table宽度
				var tableW = 0;
				if(i>4){
					tableW = thW * i+1;
				}else{
					tableW = thW * i;
				}
				$('#my_scheme_failureApplication table').width(tableW);//table宽度赋值
			})
		</script>
	</body>
</html>
