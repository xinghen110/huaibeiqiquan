<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="header.jsp" %>
</head>
<body>
<style>
    .index1-div table {
        width: 89%;
        margin: auto;
    }
	.index1-div table tr th {
		 width: 0!important;
		text-align: center;
	}
</style>
<%@include file="header_title.jsp" %>
<div class="ydl"><!-- 已登录 -->
	<%@include file="header_personal.jsp" %>
	<style>
		.index1-div1 .title span{cursor: pointer;width: 20%;text-align: center;background: #535154;font-size: 14px;color: white;}
		.index1-div1 .title span:hover{font-size: 16px!important;background: #aa0000!important;font-weight: bold!important;}
		.index1-div1 .title_a_select{font-size: 16px!important;background: #aa0000!important;font-weight: bold!important;}

		.index1-div {
			background: rgba(0,0,0,.5);
			width: 900px;
			height: 630px;
			margin: 0 auto;
		}
	</style>
	<div class="index1"><!-- 我的方案 -->
		<div class="index1-div index1-div1" style="padding-bottom: 30px;">
			<h4 class="text-align color-white title">
				<span href="" onclick="choose_title(this)" class="fl title_a_select title_a">申请中</span>
				<span href="" onclick="choose_title(this)" class="fl title_a">持仓中</span>
				<span href="" onclick="choose_title(this)" class="fl title_a">行权中</span>
				<span href="" onclick="choose_title(this)" class="fl title_a">已结算</span>
				<span href="" onclick="choose_title(this)" class="fl title_a">申请失败</span>
			</h4>
			<table>
				<%--申请中--%>
				<tr>
					<th class="color-white">订单号</th>
					<th class="color-white">股票</th>
					<th class="color-white">股票代码</th>
					<th class="color-white">名义本金</th>
					<th class="color-white">买入限价</th>
					<th class="color-white">管理费</th>
					<th class="color-white">下单日期</th>
					<th class="color-white">操作</th>
				</tr>
				<c:forEach items="${stockPlanListMap}" var="plan">
					<c:if test="${plan.orderStatus==1}">
					<tr>
						<td>${plan.planId}</td>
						<td>${plan.symbolName}</td>
						<td>${plan.symbol}</td>
						<td>${plan.buyMarketPrice}</td>
						<td>${plan.buyLimitPrice}</td>
						<td>${plan.fee}</td>
						<td><fmt:formatDate value="${plan.createTime}" pattern="yyyy/MM/dd" /></td>
						<td><h4 onclick="getPlanDetail(${plan.planId})">方案详情</h4></td>
					</tr>
					</c:if>
				</c:forEach>
			</table>

			<table style="display: none;">
				<%--持仓中--%>
				<tr>
					<th class="color-white">订单号</th>
					<th class="color-white">建议日期</th>
					<th class="color-white">股票</th>
					<th class="color-white">股票代码</th>
					<th class="color-white">生效日期</th>
					<th class="color-white">结束日期</th>
					<th class="color-white">买入限价</th>
					<%--<th class="color-white">市值</th>--%>
					<th class="color-white">买入价格</th>
					<%--<th class="color-white">总价格</th>--%>
					<th class="color-white">下单日期</th>
					<th class="color-white">操作</th>
				</tr>
				<c:forEach items="${stockPlanListMap}" var="plan">
					<c:if test="${plan.orderStatus==2}">
					<tr>
						<td>${plan.planId}</td><!--订单号-->
						<td><fmt:formatDate value="${plan.buyRecommendDate}" pattern="yyyy/MM/dd" /></td><!--建议日期-->
						<td>${plan.symbolName}</td><!--股票-->
						<td>${plan.symbol}</td><!--股票代码-->
						<td><fmt:formatDate value="${plan.buyConfirmDate}" pattern="yyyy/MM/dd" /></td><!--生效日期-->
						<td><fmt:formatDate value="${plan.buyEndDate}" pattern="yyyy/MM/dd" /></td><!--结束日期-->
						<td>${plan.buyLimitPrice}</td><!--买入限价-->
						<%--<td>${plan.buyMarketPrice}</td><!--市值-->--%>
						<td>${plan.buyPrice}</td><!--买入价格-->
						<%--<td>${plan.manageFee}</td><!--总价格-->--%>
						<td><fmt:formatDate value="${plan.createTime}" pattern="yyyy/MM/dd" /></td><!--下单日期-->
						<td><h4 onclick="getPlanDetail(${plan.planId})">方案详情</h4></td>
					</tr>
					</c:if>
				</c:forEach>
			</table>
			<table style="display: none;">
				<%--行权中--%>
				<tr>
					<th class="color-white">订单号</th>
					<th class="color-white">建议日期</th>
					<th class="color-white">股票</th>
					<th class="color-white">股票代码</th>
					<th class="color-white">生效日期</th>
					<th class="color-white">结束日期</th>
					<th class="color-white">卖出限价</th>
					<th class="color-white">卖出申请时间</th>
					<th class="color-white">状态</th>
					<th class="color-white">下单日期</th>
					<th class="color-white">操作</th>
				</tr>
				<c:forEach items="${stockPlanListMap}" var="plan">
					<c:if test="${plan.orderStatus==3}">
						<tr>
							<td>${plan.planId}</td><!--订单号-->
							<td><fmt:formatDate value="${plan.buyRecommendDate}" pattern="yyyy/MM/dd" /></td><!--建议日期-->
							<td>${plan.symbolName}</td><!--股票-->
							<td>${plan.symbol}</td><!--股票代码-->
							<td>${plan.buyConfirmDate}</td>
							<td>${plan.buyEndDate}</td>
							<td>${plan.sellLimitPrice}</td>
							<td>${plan.sellCreateTime}</td>
							<td>${plan.orderStatusName}</td>
							<td><fmt:formatDate value="${plan.createTime}" pattern="yyyy/MM/dd" /></td><!--卖出申请时间-->
							<td><h4 onclick="getPlanDetail(${plan.planId})">方案详情</h4></td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
			<table style="display: none;">
				<%--已结算--%>
				<tr>
					<th class="color-white">订单号</th>
					<th class="color-white">股票名称</th>
					<th class="color-white">股票代码</th>
					<th class="color-white">名义本金</th>
					<th class="color-white">买入类型</th>
					<th class="color-white">买入限价</th>
					<th class="color-white">申买时间</th>
					<th class="color-white">卖出类型</th>
					<th class="color-white">卖出限价</th>
					<th class="color-white">申卖时间</th>
					<th class="color-white">管理费</th>
					<th class="color-white">收益</th>
					<th class="color-white">操作</th>
				</tr>
				<c:forEach items="${stockPlanListMap}" var="plan">
					<c:if test="${plan.orderStatus==4}">
						<tr>
							<td>${plan.planId}</td>
							<td>${plan.symbol}</td>
							<td>${plan.symbolName}</td>
							<td>${plan.buyMarketPrice}</td>
							<td>${plan.buyPriceTypeName}</td>
							<td>${plan.buyLimitPrice}</td>
							<td><fmt:formatDate value="${plan.createTime}" pattern="yy/MM/dd"/></td>
							<td>${plan.sellPriceTypeName}</td>
							<td>${plan.sellLimitPrice}</td>
							<td><fmt:formatDate value="${plan.sellCreateTime}" pattern="yy/MM/dd"/></td>
							<td>${plan.fee}</td>
							<td>${plan.profit}</td>
							<td><h4 onclick="getPlanDetail(${plan.planId})">方案详情</h4></td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
			<table style="display: none;">
				<%--申请失败--%>
				<tr>
					<th class="color-white">订单号</th>
					<th class="color-white">股票名称</th>
					<th class="color-white">股票代码</th>
					<th class="color-white">名义本金</th>
					<th class="color-white">买入类型</th>
					<th class="color-white">买入限价</th>
					<th class="color-white">申买时间</th>
					<th class="color-white">管理费</th>
					<th class="color-white">操作</th>
				</tr>
				<c:forEach items="${stockPlanListMap}" var="plan">
					<c:if test="${plan.orderStatus==-1}">
						<tr>
							<td>${plan.planId}</td>
							<td>${plan.symbol}</td>
							<td>${plan.symbolName}</td>
							<td>${plan.buyMarketPrice}</td>
							<td>${plan.buyPriceTypeName}</td>
							<td>${plan.buyPriceLimt}</td>
							<td>${plan.createTime}</td>
							<td>${plan.fee}</td>
							<td><h4 onclick="getPlanDetail(${plan.planId})">方案详情</h4></td>
						</tr>
					</c:if>
				</c:forEach>
			</table>

		</div>

		<div  class="index1-div index1-div2" style="padding-bottom: 30px;display: none;">
			<h4 class="text-align color-white title">方案详情</h4>
			<img class="fl break" src="img/web/left-bark.png" style="width: 18px;cursor: pointer; margin-top: -32px;margin-left: 20px;" />
			<style>
				.statistics_title{font-size: 12px;width: 89%;margin: 0 30px;color: white;}
				.statistics_title span{width: 25%;text-align: center;float: left;}
				.statistics_content{font-size: 14px;opacity: 1!important;width: 89%;margin: 0 30px;color: white;}
				.statistics_content span{width: 25%;text-align: center;float: left;}
			</style>
			<div id="planDetail">

				<table>
					<tr>
						<th class="color-white">股票名称</th>
						<th class="color-white symbolName"></th>
						<th class="color-white">股票代码</th>
						<th class="color-white symbol"></th>
					</tr>
					<tr>
						<td>名义本金</td>
						<td class="buyMarketPrice"></td>
						<td>周期</td>
						<td class="cycleName"></td>
					</tr>
					<tr>
						<td>买入类型</td>
						<td class="buyPriceTypeName"></td>
						<td>买入限价</td>
						<td class="buyLimitPrice"></td>
					</tr>
					<tr>
						<td>推荐买入日期</td>
						<td class="buyRecommendDate"></td>
						<td>买入价格</td>
						<td class="buyPrice"></td>
					</tr>
					<tr>
						<td>确认买入日期</td>
						<td class="buyConfirmDate"></td>
						<td>方案结束日期</td>
						<td class="buyEndDate"></td>
					</tr>
					<tr>
						<td>卖出价格类型</td>
						<td class="sellPriceTypeName"></td>
						<td>卖出限价</td>
						<td class="sellLimitPrice"></td>
					</tr>
					<tr>
						<td>卖出申请时间</td>
						<td class="sellCreateTime"></td>
						<td>卖出价格</td>
						<td class="sellPrice"></td>
					</tr>
					<tr>
						<td>确认卖出时间</td>
						<td class="sellConfirmTime"></td>
						<td>管理费</td>
						<td class="fee"></td>
					</tr>
					<tr>
						<td>方案创建时间</td>
						<td class="createTime"></td>
						<td>&nbsp;</td>
						<td class="">&nbsp;</td>
					</tr>
					<tr>
						<td>收益</td>
						<td class="profit"></td>
						<td>净收益</td>
						<td class="netProfit"></td>
					</tr>
				</table>
				<%--<div style="overflow: hidden; border-top: 1px solid white; border-bottom: 1px solid white;padding: 14px 0; line-height: 25px;">--%>
					<%--<h4 class="statistics_title">--%>
						<%--<span>盈利统计日期</span>--%>
						<%--<span>投顾盈利</span>--%>
						<%--<span>盈利分成</span>--%>
						<%--<span>方案盈利</span>--%>
					<%--</h4>--%>
					<%--<h4 class="statistics_content">--%>
						<%--<span>NO DATA</span>--%>
						<%--<span>NO DATA</span>--%>
						<%--<span>NO DATA</span>--%>
						<%--<span>NO DATA</span>--%>
					<%--</h4>--%>
				<%--</div>--%>
				<div class="stock_sell_div" style="overflow: hidden; border-top: 1px solid white; border-bottom: 1px solid white;padding: 14px 0; line-height: 25px;">
					<%--<h3 class="statistics_title">行权</h3>--%>
					<h4 class="statistics_title">
						<span>&nbsp;</span>
						<span>价格类型</span>
						<span>行权限价</span>
						<span>操作</span>
					</h4>
					<h4 class="statistics_content">
						<form action="web/stock/exercise" method="post">
							<span>&nbsp;</span>
							<span><select name="sellPriceType" id="sellPriceType" onchange="$('#sellLimitPrice').css('display',$(this).val()==0?'none':'inline-block')">
								<option value="0">市价</option>
								<option value="1">限价</option>
							</select></span>
							<span><input type="text" name="sellLimitPrice" id="sellLimitPrice" style="display: none;">&nbsp;</span>
							<span><button type="submit">确认行权</button></span>
							<input type="hidden" class="planId" name="planId" value="">
						</form>
					</h4>
				</div>

			</div>
		</div>
	</div>
</div>

<%@include file="footer.jsp" %>
<script src="https://cdn.bootcss.com/moment.js/2.19.0/moment.min.js"></script>
<script type="text/javascript">
    var wH = $(window).height(); //获取屏幕高度
    hqkg();
    /**************************************登录后**************************************/
    $(".grzx .exit-login").click(function(){//退出登录
        window.location.href='realTimeOpening_login.html';
    });
    function hqkg(){
        var ydl_img_H = $(".ydl-img").height();
        var ydl_img_W = $(".ydl-img").width();
        $(".grzx").height(ydl_img_H);
        $(".grzx").css("margin-top",-(ydl_img_H));
    }
    $('.update-password').click(function(){//修改密码
        window.location.href = 'realTimeOpening_update_password.html'
    });
    /*我的方案&方案详情之前的切换*/
//    $(".index1 .index1-div1 table tr td:last-child h4").click(function(){//进入方案详情
//        $(".index1 .index1-div1").hide();
//        $(".index1 .index1-div2").show();
//    });
    $(".index1 .index1-div2 .break").click(function(){//返回我的方案
        $(".index1 .index1-div2").hide();
        $(".index1 .index1-div1").show();
    });
    //标题状态切换
    function choose_title(obj){
        $(obj).parent().find('.title_a').removeClass('title_a_select');
        $(obj).addClass('title_a_select');
        var title_aVal = $(obj).index();
        $('.index1-div1 table').hide();
        $('.index1-div1 table').eq(title_aVal).show();
    }
    /*我的方案&方案详情之前的切换end*/
    $(function(){  /*调整窗口自动调整宽度*/
        $(window).resize(function(){//实时获取宽高
            var ydl_img_H = $(".ydl-img").height();
            var ydl_img_W = $(".ydl-img").width();
            $(".grzx").height(ydl_img_H);
            $(".grzx").css("margin-top",-(ydl_img_H));
        });
    });
	var data = ${stockPlanListMapJson};

    function getPlanDetail(planId) {
        var dateFormat = "YYYY-MM-DD"
        for (var i = 0; i < data.length; i++) {
            if (planId == data[i].planId) {
                var createTime = data[i].createTime ? moment(data[i].createTime).format(dateFormat) : "";
                var buyRecommendDate = data[i].buyRecommendDate ? moment(data[i].buyRecommendDate).format(dateFormat)  : "";
                var buyConfirmDate = data[i].buyConfirmDate ? moment(data[i].buyConfirmDate).format(dateFormat) : "";
                var buyEndDate = data[i].buyEndDate ? moment(data[i].buyEndDate).format(dateFormat) : "";
                var sellCreateTime = data[i].sellCreateTime ? moment(data[i].sellCreateTime).format(dateFormat) : "";
                var sellConfirmTime = data[i].sellConfirmTime ? moment(data[i].sellConfirmTime).format(dateFormat) : "";
                var symbolName = data[i].symbolName ? data[i].symbolName : "";
                var symbol = data[i].symbol ? data[i].symbol : "";
                var buyMarketPrice = data[i].buyMarketPrice ? data[i].buyMarketPrice : "";
                var cycleName = data[i].cycle ? data[i].cycleName : "";
                var buyPriceTypeName = data[i].buyPriceTypeName ? data[i].buyPriceTypeName : "";
                var buyLimitPrice = data[i].buyLimitPrice ? data[i].buyLimitPrice : "";
                var buyPrice = data[i].buyPrice ? data[i].buyPrice : "";
                var sellMarketPrice = data[i].sellMarketPrice ? data[i].sellMarketPrice : "";
                var sellPriceTypeName = data[i].sellPriceTypeName ? data[i].sellPriceTypeName : "";
                var sellLimitPrice = data[i].sellLimitPrice ? data[i].sellLimitPrice : "";
                var sellPrice = data[i].sellPrice ? data[i].sellPrice : "";
                var fee = data[i].fee ? data[i].fee : "";
                var profit = data[i].profit ? data[i].profit : "";
                var netProfit = data[i].netProfit ? data[i].netProfit : "";
                var orderStatus = data[i].orderStatus;
                $(".createTime").html(createTime);
                $(".buyRecommendDate").html(buyRecommendDate);
                $(".buyConfirmDate").html(buyConfirmDate);
                $(".buyEndDate").html(buyEndDate);
                $(".sellCreateTime").html(sellCreateTime);
                $(".sellConfirmTime").html(sellConfirmTime);
                $(".symbolName").html(symbolName);
                $(".symbol").html(symbol);
                $(".buyMarketPrice").html(buyMarketPrice);
                $(".cycleName").html(cycleName);
                $(".buyPriceTypeName").html(buyPriceTypeName);
                $(".buyLimitPrice").html(buyLimitPrice);
                $(".buyPrice").html(buyPrice);
                $(".sellMarketPrice").html(sellMarketPrice);
                $(".sellPriceType").html(sellPriceType);
                $(".sellLimitPrice").html(sellLimitPrice);
                $(".sellPriceTypeName").html(sellPriceTypeName);
                $(".sellPrice").html(sellPrice);
                $(".fee").html(fee);
                $(".profit").html(profit);
                $(".netProfit").html(netProfit);
				$(".planId").val(data[i].planId);
				$(".stock_sell_div").css("display", orderStatus == 2 ? "block" : "none");
//                var html = "<table>" +
//                    "<tr>" +
//                    "<th class=\"color-white\">股票名称</th>" +
//                    "<th class=\"color-white\">" + symbolName + "</th>" +
//                    "<th class=\"color-white\">股票代码</th>" +
//                    "<th class=\"color-white\">" + symbol + "</th>" +
//                    "</tr>" +
//                    "<tr>" +
//                    "<td>名义本金</td>" +
//                    "<td>" + buyMarketPrice + "</td>" +
//                    "<td>周期</td>" +
//                    "<td>" + cycle + "</td>" +
//                    "</tr>" +
//                    "<tr>" +
//                    "<td>买入类型</td>" +
//                    "<td>" + buyPriceType + "</td>" +
//                    "<td>买入限价</td>" +
//                    "<td>" + buyLimitPrice + "</td>" +
//                    "</tr>" +
//                    "<tr>" +
//                    "<td>推荐买入日期</td>" +
//                    "<td>" + buyRecommendDate + "</td>" +
//                    "<td>买入价格</td>" +
//                    "<td>" + buyPrice + "</td>" +
//                    "</tr>" +
//                    "<tr>" +
//                    "<td>确认买入日期</td>" +
//                    "<td>" + buyConfirmDate + "</td>" +
//                    "<td>方案结束日期</td>" +
//                    "<td>" + buyEndDate + "</td>" +
//                    "</tr>" +
//                    "<tr>" +
//                    "<td>卖出方案市值</td>" +
//                    "<td>" + sellMarketPrice + "</td>" +
//                    "<td>卖出价格类型</td>" +
//                    "<td>" + sellPriceType + "</td>" +
//                    "</tr>" +
//                    "<tr>" +
//                    "<td>卖出限价</td>" +
//                    "<td>" + sellLimitPrice + "</td>" +
//                    "<td>卖出申请时间</td>" +
//                    "<td>" + sellCreateTimee + "</td>" +
//                    "</tr>" +
//                    "<tr>" +
//                    "<td>卖出价格</td>" +
//                    "<td>" + sellPrice + "</td>" +
//                    "<td>确认卖出时间</td>" +
//                    "<td>" + sellConfirmTime + "</td>" +
//                    "</tr>" +
//                    "<tr>" +
//                    "<td>管理费</td>" +
//                    "<td>" + manageFee + "</td>" +
//                    "<td>方案创建时间</td>" +
//                    "<td>" + createTime + "</td>" +
//                    "</tr>" +
//                    "</table>" +
//                    "" +
//                    "<div style=\"overflow: hidden; border-top: 1px solid white; border-bottom: 1px solid white;padding: 14px 0; line-height: 25px;\">" +
//                    "<h4 class=\"statistics_title\">" +
//                    "<span>盈利统计日期</span>" +
//                    "<span>投顾盈利</span>" +
//                    "<span>盈利分成</span>" +
//                    "<span>方案盈利</span>" +
//                    "</h4>" +
//                    "<h4 class=\"statistics_content\">" +
//                    "<span>NO DATA</span>" +
//                    "<span>NO DATA</span>" +
//                    "<span>NO DATA</span>" +
//                    "<span>NO DATA</span>" +
//                    "</h4>" +
//                    "</div>";
                $("#planDetail").show();
                $(".index1 .index1-div1").hide();
                $(".index1 .index1-div2").show();
            }
        }
    }

    function fmtDate(obj){
        var date =  new Date(obj);
        var y = 1900+date.getYear();
        var m = "0"+(date.getMonth()+1);
        var d = "0"+date.getDate();
        return y+"-"+m.substring(m.length-2,m.length)+"-"+d.substring(d.length-2,d.length);
    }
</script>
</body>
</html>

