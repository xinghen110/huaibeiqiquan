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
	<style>
		#my_scheme_position table tr td .ui-btn {
			margin: .5em;
			background: #c33f00;
			color: white;
			border: none;
			text-shadow: none;
			width: 50%;
			padding: .5em;
			border-radius: 4px;
			font-size: .9em;
			margin-left: 1em;
			margin-bottom: 0;
		}
	</style>
</head>
	<body>
		<div data-role="page" id="my_scheme_position">
			<div data-role = "header" style="border-width: 0; background: #28292E;">
				<h4 class="color-red">我的方案</h4><!-- 持仓中  -->
			</div>
			<div data-role = "content">
				<h4 class="color-w choose_title size-9">
					<a data-ajax = "false" href="mobile/stock/plan/list?orderStatus=1" class="fl">申请中</a>
					<a data-ajax = "false" href="mobile/stock/plan/list?orderStatus=2" class="fl seleced_title">持仓中</a>
					<a data-ajax = "false" href="mobile/stock/plan/list?orderStatus=3" class="fl">行权中</a>
					<a data-ajax = "false" href="mobile/stock/plan/list?orderStatus=4" class="fl">已结算</a>
					<a data-ajax = "false" href="mobile/stock/plan/list?orderStatus=-1" class="fl">申请失败</a>
				</h4>
				<div style="overflow: scroll;margin-bottom:20px;">
					<table cellspacing="0" cellpadding="0">
						<tr>
							<th class="color-w size-9">订单号</th>
							<th class="color-w size-9">盈亏市算</th>
							<th class="color-w size-9">盈亏市算</th>
							<th class="color-w size-9 two">建议日期</th>
							<th class="color-w size-9">股票名称</th>
							<th class="color-w size-9">股票代码</th>
							<th class="color-w size-9">股票价格</th>
							<th class="color-w size-9">生效日期</th>
							<th class="color-w size-9">结束日期</th>
							<!--<th class="color-w size-9">买入限价</th>-->
							<th class="color-w size-9">买入价格</th>
							<th class="color-w size-9">下单日期</th>
							<th class="color-w size-9">行权</th>
						</tr>
						<c:forEach var="item" items="${stockPlanListMap}">
							<tr>
								<td class="color-w size-9">${item.planId}</td><!--订单号-->
								<td class="color-w size-9">${item.yingkui}</td><!--盈亏市算-->
								<td class="color-w size-9">${item.yingkui}</td><!--盈亏市算-->
								<td class="color-w size-9 two"><fmt:formatDate value="${item.buyRecommendDate}" pattern="yyyy/MM/dd" /></td><!--建议日期-->
								<td class="color-w size-9">${item.symbolName}</td><!--股票-->
								<td class="color-w size-9">${item.symbol}</td><!--股票代码-->
								<td class="color-w size-9">${item.curPrice}</td><!--股票价格-->
								<td class="color-w size-9"><fmt:formatDate value="${item.buyConfirmDate}" pattern="yyyy/MM/dd" /></td><!--生效日期-->
								<td class="color-w size-9"><fmt:formatDate value="${item.buyEndDate}" pattern="yyyy/MM/dd" /></td><!--结束日期-->
								<!--<td class="color-w size-9">${item.buyLimitPrice}</td><!--买入限价-->
								<td class="color-w size-9">${item.buyPrice}</td><!--买入价格-->
								<td class="color-w size-9"><fmt:formatDate value="${item.createTime}" pattern="yyyy/MM/dd" /></td><!--下单日期-->
								<td class="color-w size-9"><a class="ui-btn" onclick="show(${item.planId});">行权</a></td>
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
		<div class="xq_tc" style="display: none;position: absolute;z-index: 99999;top: 50%; left: 50%; margin-top: -130px; background: white; width: 65%; margin-left: -130px;border-radius:3px;">
			<h4 style="background:#c33f00;font-size:0.9em;position: relative;margin: 0; text-align: center; height: 30px; line-height: 30px;color:white;border-top-left-radius:3px;
border-top-right-radius:3px;">行权<span style="position: absolute;right: 0;z-index: 99999999;color:white;margin-right:0.5em;" class="fr size-8 close">关闭</span></h4>
			
			<form action="mobile/stock/exercise" method="post" data-ajax="false" >
			<div style="width:80%;margin:0 auto;">
			<div>
				<h5 style="margin: 0;margin-bottom: 10px;margin-top: 10px;">价格类型</h5>
				<select id="sellPriceType" name="sellPriceType" class="size-9" style="width: 100%;border-radius:3px;border:1px #a7a7a7 solid;height:30px; " data-role = "none">
					<!--<option value="">请选择</option>-->
					<option value="0">市价</option>
					<!--<option value="1">限价</option>-->
				</select>
			</div>
				<script>
					$("#sellPriceType").change(function () {
					    if($("#sellPriceType").val()==1){
                            $("#sellLimitPriceArea").css("display","");
                        }
                        if($("#sellPriceType").val()==0){
                            $("#sellLimitPriceArea").css("display","none");
                        }
                    });
				</script>
			<div id="sellLimitPriceArea" style="margin-top: 10px;display: none;">
				<h5 style="margin: 0;margin-bottom: 10px;">行权限价</h5>
				<input  style="width: 100%;" name="sellLimitPrice" class="size-9" data-role = "none" type="number" placeholder="请输入行权限价" />
			</div>
				<input type="hidden" id="hiddenPlanId" class="planId" name="planId" value="">
				<button type="submit" style="margin-top: 25px; background: #c33f00; color: white; font-weight: normal!important; text-shadow: none;border-radius:3px;" class="ui-btn size-9">确认行权</button>
				</div>
			</form>
		</div>

		<script type="text/javascript">
            function show(planId){//显示弹窗（onclick自行绑定）
				$("#hiddenPlanId").val(planId);
                $('.xq_tc').show();
            }

            $('.close').click(function(){//关闭弹窗
                $('.xq_tc').hide();
            });

			$(window).ready(function(){//每次加载动态获取table宽度
				var windowW = $(window).width();
				var i = 0;
				$('#my_scheme_position table tr th').each(function(){
					i++;
				})
			    var thW = windowW*0.25;//每个th的宽度
			    $('#my_scheme_position table th').css('width',windowW*0.25);//th宽度赋值
			     $('#my_scheme_position table td').css('width',windowW*0.25);//th宽度赋值
			    $('#my_scheme_position .two').css('padding-left',windowW*0.25)//设置第二个th的margin-left
				//计算总的table宽度
				var tableW = 0;
				if(i>4){
					tableW = thW * (i-1);
				}else{
					tableW = thW * i;
				}
				$('#my_scheme_position table').width(tableW);//table宽度赋值
			})
		</script>
	</body>
</html>
