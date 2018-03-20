<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
    <%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
    <title>取消原因</title>
</head>
<body>
<div data-role="page" id="feedback">
    <div data-role = "header" style="background: white;border-width: 0px">
        <a data-role="button" data-rel="back" onclick="goBack(-1)"><img src="app/img/icon_nav_back.png"/></a>
        <h4>取消原因</h4>
    </div>
    <div data-role = "content">
        <textarea placeholder="填写您的取消原因~" name="content"></textarea>
        <h5>客服电话 : <a>0551-76416350</a></h5>
        <h5>工作地址 : <span>09:00</span>~<span>12:00</span></h5>
        <a data-role="button" class="submit" onclick="qxdd()">取消订单</a>
    </div>
</div>
<script type="text/javascript">
    function qxdd() {
        var reason = $('textarea[name=content]').val();
        if (reason.trim() == "") {
            alert("请输入取消原因！！！");
            return false;
        }
        var data = {
            "orderNum" : '${orderNum}',
            "userNum" : '${userNum}',
            "reason" : reason.toString()
        }
        $.post("app/page/cancel_order", data, function (data) {
            if (data == "true") {
                alert("取消订单成功！");
                cancelOrder(1);
            } else {
                alert("取消订单失败！");
            }
        });
    }
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
</script>
</body>
</html>