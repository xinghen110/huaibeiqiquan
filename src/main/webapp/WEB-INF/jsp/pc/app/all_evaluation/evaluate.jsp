<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>评价</title>
</head>
	<body>
		<div data-role="page" id="evaluate">
			<div data-role="header" style="background: white;">
				<a data-role="button" data-rel="back"><img src="app/img/icon_nav_back.png"/></a>
				<h4>评价</h4>
			</div>
			<div data-role = "content">
				<div class="header">
					<img src="${constants.QINIU_USER_IMGURL}${orderMeal.mainPhoto}">
					<h4 class="name">${orderMeal.mealName}</h4>
					<h4><span>${shopInfo.shopName} ￥${orderMeal.salePrice}x${orderInfo.totalCount}</span><span class="fr color-red">￥${orderInfo.actualPrice}</span></h4>
				</div>
			
				<div class="xj-wc">
					<h4 class="xj size-10"><span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span></h4>
					<input type="hidden" id="score" name="score" value="">
				</div>
				<div class="textarea-wc">
					<textarea maxlength="200" placeholder="亲!服务如何？对技师还满意吗？" name="content"></textarea>
					<h3>限200字</h3>
				</div>
				<a class="ui-btn" onclick="tijiao()">提交</a>
			</div>
		</div>
		<script type="text/javascript">
			//显示星级
            $('#evaluate .xj span').click(function () {
            	$t = $(this).index()+1;
            	$('#evaluate .xj span').removeClass("active");
            	$("#score").val($t);
                $(this).parent().find("span:lt("+$t+")").addClass('active');
            });
            function goBack(num){
                var arrayString=num;
                var u = navigator.userAgent;
                window.history.go( -1 );
            }
            function tijiao() {
                var content = $('textarea[name=content]').val();
                if (content.trim() == "") {
                    alert("请输入评价内容！！！");
                    return false;
                }
                var score = $("#score").val();
                if (score == '') {
                    alert("请选择评分！");
                    return false;
				}
                var data = {
                    "orderNum" : '${orderInfo.orderNum}',
                    "userNum" : '${userNum}',
                    "content" : content.toString(),
					"score" : score
                }
                $.post("app/pageshow/scoring", data, function (data) {
					if (data == "true") {
					    window.location.href="app/page/order?orderNum=${orderInfo.orderNum}&userNum=${userNum}"
					} else {
					    alert("评论失败！");
					}
                });
            }
		</script>
	</body>
</html>