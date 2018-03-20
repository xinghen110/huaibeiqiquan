<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>订单详情</title>
</head>
	<body>
		<div data-role="page" id="order">
			<div data-role = "header" style="background: white;border-width: 0px">
				<a data-role="button" data-rel="back" onclick="goBack(1)"><img src="app/img/icon_nav_back.png"/></a>
				<h4>订单详情</h4>
			</div>
			<div data-role = "content">
				<div class="header">
					<img class="fl goods_img" src="${constants.QINIU_USER_IMGURL}${orderMeal.mainPhoto}" width="80px" height="80px" style=" border-radius: 7px;position: absolute;">
					<h4 class="tc_name">${orderMeal.mealName}<img class="yjd fr" <c:if test="${orderInfo.orderStatus == 2}">src="app/img/icon_yfg.png"</c:if><c:if test="${orderInfo.orderStatus == 3}">src="app/img/icon_yjd.png"</c:if><c:if test="${orderInfo.orderStatus == 4}">src="app/img/icon_ywc.png"</c:if><c:if test="${orderInfo.orderStatus == 5}">src="app/img/icon_ypj.png"</c:if><c:if test="${orderInfo.orderStatus == -1}">src="app/img/icon_yqx.png"</c:if> ></h4><!-- 已付款的图片地址app/img/icon_yfk.png 已取消的图片地址app/img/icon_yqx.png -->
					<h4 class="price size-8"><span class="fl">￥${orderMeal.salePrice}x${orderInfo.totalCount}</span>
						<c:if test="${orderInfo.serviceType == 1}"><span class="fr dj">全款：<sapn class="color-red">￥${orderInfo.actualPrice}</sapn></span></c:if>
						<c:if test="${orderInfo.serviceType == 2}"><c:if test="${empty orderInfo.freight || orderInfo.freight == ''}"><span class="fr dj">全款：<sapn class="color-red">￥${orderInfo.actualPrice}</sapn></span></c:if><c:if test="${not empty orderInfo.freight && orderInfo.freight != ''}"><span class="fr dj">定金：<sapn class="color-red">￥${orderInfo.freight}</sapn></span></c:if></c:if>
					</h4>
				</div>
				<c:if test="${orderInfo.orderStatus != -1}">
					<div class="fwxx">
						<p>服务信息</p>
						<h4>服务人员<span class="fr name">${shopInfo.shopName}</span></h4>
						<h4>服务时间<span class="fr">${orderMeal.mealLog}分钟</span></h4>
						<h4>服务方式<span class="fr"><ry:show parentCode="SERVICE_TYPE" itemCode="${orderInfo.serviceType}"></ry:show></span></h4>
					</div>
					<div class="fwxx">
						<p>个人信息</p>
						<h4>联系方式<span class="fr">${orderInfo.orderLinkTel}</span></h4>
						<h4>上门时间<span class="fr"><ry:formatDate date="${orderInfo.arrivaTime}" toFmt="yyyy-MM-dd HH:mm" /></span></h4>
						<h4>上门地址<span class="fr address"><ry:show parentCode="${orderInfo.falg1}" itemCode="${orderInfo.city}" type="3"></ry:show><ry:show parentCode="${orderInfo.city}" itemCode="${orderInfo.area}" type="4"></ry:show>${shopInfo.address}</span></h4>
					</div>
					<div class="fwxx">
						<p>备注信息</p>
						<h4><c:if test="${empty orderInfo.orderRemark}">无</c:if><c:if test="${not empty orderInfo.orderRemark}">${orderInfo.orderRemark}</c:if></h4>
					</div>
				</c:if>
				<c:if test="${orderInfo.orderStatus == -1}">
					<div class="fwxx">
						<p>取消及退款信息</p>
						<h4>退还金额<span class="fr color-red">￥${orderInfo.returnPrice}</span></h4>
						<h4>取消用户<span class="fr name">${cancelUser.nickName}</span></h4>
						<h4>取消原因<span class="fr address"><c:if test="${empty orderInfo.cancelReason}">无</c:if><c:if test="${not empty orderInfo.cancelReason}">${orderInfo.cancelReason}</c:if></span></h4>
					</div>
				</c:if>
				<div class="fwxx" style="margin-bottom: 3.8em;">
					<p>订单信息</p>
					<h4>订单编号<span class="fr">${orderInfo.orderNum}</span></h4>
					<h4>付款时间<span class="fr"><ry:formatDate date="${orderInfo.payTime}" toFmt="yyyy-MM-dd HH:mm" /></span></h4>
					<c:if test="${not empty orderInfo.receiveTime}"><h4>接单时间<span class="fr"><ry:formatDate date="${orderInfo.receiveTime}" toFmt="yyyy-MM-dd HH:mm" /></span></h4></c:if>
					<c:if test="${orderInfo.orderStatus == 4 || orderInfo.orderStatus == 5}"><h4>完成时间<span class="fr"><ry:formatDate date="${orderInfo.completeTime}" toFmt="yyyy-MM-dd HH:mm" /></span></h4></c:if>
					<c:if test="${orderInfo.orderStatus == -1}"><h4>取消时间<span class="fr"><ry:formatDate date="${orderInfo.cancelTime}" toFmt="yyyy-MM-dd HH:mm" /></span></h4></c:if>
				</div>
				<c:if test="${orderInfo.orderStatus == 2 || orderInfo.orderStatus == 3}">
					<div class="div1" style="display: none;">
						<h4 class="title">确定取消预约？</h4>
						<c:if test="${orderInfo.orderStatus == 2}"><h4 style="margin: 1em;color:black;">技师未结单，现在取消预约，将退还全款，确定取消吗？</h4></c:if>
						<c:if test="${orderInfo.orderStatus == 3}"><h4 style="margin: 1em;color:black;">技师已结单，现在取消预约，支付款将之退还80%，确定取消吗？</h4></c:if>
						<div>
							<a data-role="button" class="qxBtn font-weight text-shadow size-9 color-t" data-ajax="false" href="app/page/cancel?orderNum=${orderInfo.orderNum}&userNum=${userNum}">坚决取消</a>
						</div>
						<div>
							<a data-role="button" class="font-weight text-shadow size-9 qrBtn">保留预约</a>
						</div>
					</div>
					<a data-role="button" class="qxdd">取消订单</a>
					<div class="query-c"></div><!-- 灰色背景 -->
				</c:if>
				<c:if test="${orderInfo.orderStatus == 4}">
					<a data-role="button" class="pjyx" data-ajax="false" href="app/pageshow/evaluate?orderNum=${orderInfo.orderNum}&userNum=${userNum}">评价一下</a>
					<div class="query-c"></div><!-- 灰色背景 -->
				</c:if>
				<c:if test="${orderInfo.orderStatus == 5}">
					<a data-role="button" class="pjyx">已完成</a>
					<div class="query-c"></div><!-- 灰色背景 -->
				</c:if>
			</div>
		</div>
		<script type="text/javascript">
			$(".qxdd").click(function(){
				var wH=document.documentElement.clientHeight||document.body.clientHeight;
				var dH = $(".div1").height();
				var mH = (wH-dH)/2+"px";
				$(".div1").css('margin-top',mH);
				$(".div1").show("2000");
				$(".query-c").show("2000");
				
			});
			$(".qxBtn").click(function(){
				$(".div1").hide("2000");
				$(".query-c").hide("2000");
			});
			$(".qrBtn").click(function(){
				$(".div1").hide("2000");
				$(".query-c").hide("2000");
			});
			$(".query-c").click(function(){
				$(".div1").hide("2000");
				$(".query-c").hide("2000");
			});
            function cancelOrder(num){
                var arrayString="app/page/order?orderNum=${orderNum}&userNum=${userNum}";
                var u = navigator.userAgent;
                var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
                var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
                if (isAndroid||isiOS) {
                    if($.mobile.navigate.history.activeIndex == 0){
                        if(isAndroid){
                            window.ruanyun.cancelOrder(arrayString);
                        }else if(isiOS){
                            window.webkit.messageHandlers.cancelOrder.postMessage(arrayString);
                        }
                    }
                }
			}
			function goBack(num) {
                var arrayString=num;
                var u = navigator.userAgent;
                var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
                var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
                if (isAndroid||isiOS) {
                    if($.mobile.navigate.history.activeIndex == 0){
                        if(isAndroid){
                            window.ruanyun.viewBack(arrayString);
                        }else if(isiOS){
                            window.webkit.messageHandlers.viewBack.postMessage(arrayString);
                        }
                    }
                }
            }
		</script>
	</body>
</html>