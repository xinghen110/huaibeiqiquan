<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>接单详情</title>
</head>
	<body>
		<div data-role="page" id="receiving_details">
			<div data-role = "header" style="background: white;border-width: 0px">
				<a data-role="button" data-rel="back" onclick="goBack(1)"><img src="app/img/icon_nav_back.png"/></a>
				<h4>接单详情</h4>
			</div>
			<div data-role = "content">
				<div class="header">
					<img class="fl goods_img" src="${constants.QINIU_USER_IMGURL}${orderMeal.mainPhoto}" width="80px" height="80px" style=" border-radius: 7px;position: absolute;">
					<h4 class="tc_name">${orderMeal.mealName}<img class="yjd fr" <c:if test="${orderInfo.orderStatus == 2}">src="app/img/icon_yfg.png"</c:if><c:if test="${orderInfo.orderStatus == 3}">src="app/img/icon_yjd.png"</c:if><c:if test="${orderInfo.orderStatus == 4}">src="app/img/icon_ywc.png"</c:if><c:if test="${orderInfo.orderStatus == 5}">src="app/img/icon_ypj.png"</c:if><c:if test="${orderInfo.orderStatus == -1}">src="app/img/icon_yqx.png"</c:if> ></h4><!-- 进行中的图片地址app/img/icon_jxz.png 已取消的图片地址app/img/icon_yqx.png 已评价图片地址:app/img/icon_ypj.png -->
					<h4 class="price size-8"><span class="fl">￥${orderMeal.salePrice}x${orderInfo.totalCount}</span>
						<c:if test="${orderInfo.serviceType == 1}"><span class="fr dj">全款：<sapn class="color-red">￥${orderInfo.actualPrice}</sapn></span></c:if>
						<c:if test="${orderInfo.serviceType == 2}"><c:if test="${empty orderInfo.freight || orderInfo.freight == ''}"><span class="fr dj">全款：<sapn class="color-red">￥${orderInfo.actualPrice}</sapn></span></c:if><c:if test="${not empty orderInfo.freight && orderInfo.freight != ''}"><span class="fr dj">定金：<sapn class="color-red">￥${orderInfo.freight}</sapn></span></c:if></c:if>
					</h4>
				</div>
				
				<c:if test="${orderInfo.orderStatus == 5}">
					<div class="fwxx">
						<p>客户评价</p>
						<h4 class="xj size-10"><span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span><span style="font-size: .8em; margin-right: 1.2em;" class="fr"><ry:formatDate date="${commentInfo.createTime}" toFmt="MM-dd" /></span></h4>
						<h4 style="margin: 1em; font-size: .9em; margin-top: .3em; line-height: 1.5em; margin-right: .7em;">${commentInfo.content}</h4>
					</div>
				</c:if>
				<div class="fwxx">
					<p>服务信息</p>
					<h4>服务时间<span class="fr">${orderMeal.mealLog}分钟</span></h4>
					<h4>服务方式<span class="fr"><ry:show parentCode="SERVICE_TYPE" itemCode="${orderInfo.serviceType}"></ry:show></span></h4>
				</div>
				<c:if test="${orderInfo.orderStatus == -1}">
					<div class="fwxx">
						<p>取消及退款信息</p>
						<h4>退还金额<span class="fr color-red">￥${orderInfo.returnPrice}</span></h4>
						<h4>取消用户<span class="fr name">${cancelUser.nickName}</span></h4>
						<c:if test="${not empty orderInfo.cancelReason}"><h4>取消原因<span class="fr address">${orderInfo.cancelReason}</span></h4></c:if>
					</div>
				</c:if>
				<div class="fwxx">
					<p>客户信息</p>
					<h4>客户姓名<span class="fr">${orderInfo.orderLinkMan}</span></h4>
					<h4>联系方式<span class="fr">${orderInfo.orderLinkTel}</span></h4>
					<c:if test="${orderInfo.serviceType == 1}"><h4>到店时间<span class="fr">08-30 13:20</span></h4></c:if>
					<c:if test="${orderInfo.serviceType == 2}"><h4>上门时间<span class="fr">08-30 13:20</span></h4></c:if>
					<h4>上门地址<span class="fr address"><ry:show parentCode="${orderInfo.falg1}" itemCode="${orderInfo.city}" type="3"></ry:show><ry:show parentCode="${orderInfo.city}" itemCode="${orderInfo.area}" type="4"></ry:show>${orderInfo.orderAddress}</span></h4>
				</div>
				<div class="fwxx">
					<p>备注信息</p>
					<h4><c:if test="${empty orderInfo.orderRemark}">无</c:if><c:if test="${not empty orderInfo.orderRemark}">${orderInfo.orderRemark}</c:if></h4>
				</div>
				<div class="fwxx" style="margin-bottom: 3.8em;">
					<p>订单信息</p>
					<h4 class="size-9">订单编号<span class="fr">${orderInfo.orderNum}</span></h4>
					<h4 class="size-9">付款时间<span class="fr"><ry:formatDate date="${orderInfo.payTime}" toFmt="yyyy-MM-dd HH:mm" /></span></h4>
					<c:if test="${not empty orderInfo.receiveTime}"><h4 class="size-9">接单时间<span class="fr"><ry:formatDate date="${orderInfo.receiveTime}" toFmt="yyyy-MM-dd HH:mm" /></span></h4></c:if>
					<c:if test="${orderInfo.orderStatus == 4 || orderInfo.orderStatus == 5}"><h4 class="size-9">完成时间<span class="fr"><ry:formatDate date="${orderInfo.completeTime}" toFmt="yyyy-MM-dd HH:mm" /></span></h4></c:if>
					<c:if test="${orderInfo.orderStatus == -1}"><h4 class="size-9">取消时间<span class="fr"><ry:formatDate date="${orderInfo.cancelTime}" toFmt="yyyy-MM-dd HH:mm" /></span></h4></c:if>
					<c:if test="${orderInfo.orderStatus == 5}"><h4 class="size-9">评价时间<span class="fr"><ry:formatDate date="${commentInfo.createTime}" toFmt="yyyy-MM-dd HH:mm" /></span></h4></c:if>
				</div>
				 <div class="div1" style="display: none;">
					 <h4 class="title">确定取消订单？</h4>
					 <textarea placeholder="请输入你的取消原因" name="content"></textarea>
		  	         <div>
		  	         	<a data-role="button" class="qxBtn font-weight text-shadow size-9 color-t" onclick="qxdd()">坚决取消</a>
		  	         </div>
					 <div>
						 <a data-role="button" class="font-weight text-shadow size-9 qrBtn">保留预约</a>
					 </div>
				</div> 
				<div class="query-c"></div><!-- 灰色背景 -->
			</div>
			<c:if test="${orderInfo.orderStatus == 2}">
				<div class="jd">
					<a data-role="button" class="qxdd fl">取消订单</a>
					<a data-role="button" class="ksjd fr" onclick="ksjd()">快速接单</a>
				</div>
			</c:if>
			<c:if test="${orderInfo.orderStatus == 3}">
				<div class="wc">
					<a data-role="button" class="ywc" onclick="ywc()">已完成</a>
				</div>
			</c:if>
			
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
			
			$(".xj").find("span:lt(${commentInfo.score})").addClass('active');

            function qxdd() {
                var reason = $('textarea[name=content]').val();
                if (reason.trim() == "") {
                    alert("请输入取消原因！！！");
                    return false;
                }
                var data = {
                    "orderNum" : '${orderInfo.orderNum}',
                    "userNum" : '${userNum}',
                    "reason" : reason.toString()
                }
                $.post("app/page/cancel_order", data, function (data) {
                    if (data == "true") {
                        alert("取消订单成功！")
                        updateOrder(-1);
                    }
                });
            }
            function ksjd() {
				var data = {
				    "userNum" : '${userNum}',
					"orderNum" : '${orderInfo.orderNum}'
				}
                $.post("app/page/quick_order", data, function (data) {
                    if (data == "true") {
                        alert("接单成功！");
                        updateOrder(3);
                    } else {
                        alert("接单失败！");
					}
                })
            }
            function ywc() {
                $(".ywc").removeAttr("onclick");
                var data = {
                    "userNum" : '${userNum}',
                    "orderNum" : '${orderInfo.orderNum}'
                }
                $.post("app/page/complete_order", data, function (data) {
                    if (data == "true") {
                        alert("完成成功！");
                        updateOrder(4);
                    } else {
                        alert("完成订单失败！");
                        $(".ywc").attr("onclick", "ywc()");
                    }
                })
            }
            function updateOrder(type){
                var arrayString=type;
                var u = navigator.userAgent;
                var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
                var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
                if (isAndroid||isiOS) {
                    if($.mobile.navigate.history.activeIndex == 0){
                        if(isAndroid){
                            window.ruanyun.updateOrder(arrayString);
                        }else if(isiOS){
                            window.webkit.messageHandlers.updateOrder.postMessage(arrayString);
                        }
                    }
                }
            }
            function goBack(num){
                var arrayString=num;
                var u = navigator.userAgent;
                var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
                var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
                if(isAndroid){
                    window.ruanyun.viewBack(arrayString);
                }else if(isiOS){
                    window.webkit.messageHandlers.viewBack.postMessage(arrayString);
                }
            }
		</script>
	</body>
</html>