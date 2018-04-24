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
	<link rel="stylesheet" href="http://gg.spqun.com/css/mobile/mystyle.css" />
	<link rel="stylesheet" href="http://gg.spqun.com/css/mobile/jquery.mobile-1.4.2.css" />
	<script type="text/javascript" src="http://gg.spqun.com/js/mobile/jquery.min.js" ></script>
	<script type="text/javascript" src="http://gg.spqun.com/js/mobile/jquery.mobile-1.4.2.js" ></script>
	<title></title>
</head>
	<body>
		<div data-role="page" id="my_scheme_exercise">
			<div data-role = "header" style="border-width: 0; background: #28292E;">
				<h4 class="color-red">我的方案</h4><!-- 行权中 -->
			</div>
			<div data-role = "content">
				<h4 class="color-w choose_title size-9">
					<a data-ajax = "false" href="mobile/stock/plan/list?orderStatus=1" class="fl">申请中</a>
					<a data-ajax = "false" href="mobile/stock/plan/list?orderStatus=2" class="fl">持仓中</a>
					<a data-ajax = "false" href="mobile/stock/plan/list?orderStatus=3" class="fl seleced_title">行权中</a>
					<a data-ajax = "false" href="mobile/stock/plan/list?orderStatus=4" class="fl">已结算</a>
					<a data-ajax = "false" href="mobile/stock/plan/list?orderStatus=-1" class="fl">申请失败</a>
				</h4>
				<div style="overflow: scroll;margin-bottom:20px;">
					<table cellspacing="0" cellpadding="0">
						<tr>
							<th class="color-w size-9">订单号</th>
							<th class="color-w size-9 two">建议日期</th>
							<th class="color-w size-9">股票名称</th>
							<th class="color-w size-9">股票代码</th>
							<th class="color-w size-9">股票价格</th>
							<th class="color-w size-9 three" colspan="2">生效日期</th>
							<th class="color-w size-9 four" colspan="2">结束日期</th>
							<!--<th class="color-w size-9">卖出限价</th>-->
							<th class="color-w size-9 five" colspan="2">卖申时间</th>
							<th class="color-w size-9">状态</th>
							<th class="color-w size-9">下单日期</th>
						</tr>
						<c:forEach var="item" items="${stockPlanListMap}">
							<tr>
								<td class="color-w size-9">${item.planId}</td><!--订单号-->
								<td class="color-w size-9 two"><fmt:formatDate value="${item.buyRecommendDate}" pattern="yyyy/MM/dd" /></td><!--建议日期-->
								<td class="color-w size-9">${item.symbolName}</td><!--股票-->
								<td class="color-w size-9">${item.symbol}</td><!--股票代码-->
								<td class="color-w size-9">${item.curPrice}</td><!--股票价格-->
								<td class="color-w size-9 three" colspan="2"><fmt:formatDate value="${item.buyConfirmDate}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
								<td class="color-w size-9 four" colspan="2"><fmt:formatDate value="${item.buyEndDate}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
								<!--<td class="color-w size-9">${item.sellLimitPrice}</td><!--卖出限价-->
								<td class="color-w size-9 five" colspan="2"><fmt:formatDate value="${item.sellCreateTime}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
								<td class="color-w size-9">${item.orderStatusName}</td>
								<td class="color-w size-9"><fmt:formatDate value="${item.createTime}" pattern="yyyy/MM/dd" /></td><!--卖出申请时间-->
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
				$('#my_scheme_exercise table tr th').each(function(){
					i++;
				})
			    var thW = windowW*0.25;//每个th的宽度
			    $('#my_scheme_exercise table th').css('width',windowW*0.25);//th宽度赋值
			     $('#my_scheme_exercise table td').css('width',windowW*0.25);//th宽度赋值
			  //  $('#my_scheme_exercise .two').css('width',windowW*0.25)//设置第二个th的margin-left
			 $('#my_scheme_exercise .two').css('padding-left',windowW*0.25)//设置第二个th的margin-left
				//计算总的table宽度
				var tableW = 0;
				if(i>4){
					tableW = thW * (i+3);
				}else{
					tableW = thW * i;
				}
				$('#my_scheme_exercise table').width(tableW);//table宽度赋值
				//$('#my_scheme_exercise table').width(1170);//table宽度赋值
			})
		</script>
	</body>
</html>
