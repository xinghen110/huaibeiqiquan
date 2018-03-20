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
        <h4 class="color-w">充值</h4>
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
        <div>
            <h4 class="fl">充值方式</h4>
            <select data-role="none" id="payType">
                <option value="">请选择充值方式</option>
                <option value="qq_scan">QQ支付</option>
                <option value="weixin_scan">微信</option>
            </select>
        </div>

        <p id="showqrcode"><img src=""></p>

        <p style="display: none;">返回的XML数据：</p>
        <textarea rows="12" cols="90" id="xmldata" style="display: none;"></textarea>

        <button class="ui-btn" onclick="toPay()">确定</button>
    </div>

</div>
<script>

 /*   $(function () {

        var url = "http://chaxun.1616.net/s.php?type=ip&output=json&callback=?&_=" + Math.random();
        $.getJSON(url, function (data) {
            alert(1);
            alert(data.Ip);
            document.getElementById("client_ip").value = data.Ip;  // 获取客户端IP
        });
    })
*/

    //生成二维码图片
    function sQrcode(qdata) {
        alert(qdata);
        $("#showqrcode").empty().qrcode({		// 调用qQcode生成二维码
            render: "canvas",    			// 设置渲染方式，有table和canvas，使用canvas方式渲染性能相对来说比较好
            text: qdata,    				// 扫描了二维码后的内容显示,在这里也可以直接填一个网址或支付链接
            width: "200",              	// 二维码的宽度
            height: "200",             	// 二维码的高度
            background: "#ffffff",     	// 二维码的后景色
            foreground: "#000000",     	// 二维码的前景色
            src: ""    						// 二维码中间的图片
        });

    }

    //提交充值信息，后台处理
    function toPay() {

        var money = $("#money").val();
        var payType = $("#payType").val();

        if (money === "") {
            alert("金额不能为空");
            return;
        }

        if (payType === "") {
            alert("扫码支付方式不能为空");
            return;
        }

        $.ajax({
            url: "mobile/pay",
            data: {"rechargeMoney": money, "payType": payType},
            dataType: "text",
            success: function (data, textStatus) {
                $("#xmldata").text(data);
                var resp_code = $(data).find("resp_code").text();
                var result_code = $(data).find("result_code").text();

                console.log(resp_code+","+result_code);
                console.log(data);

                if (resp_code == "SUCCESS" && result_code == "0") {

                    var qrcode = $(data).find("qrcode").text();
                    sQrcode(qrcode);
                } else if (resp_code == "SUCCESS" && result_code == "1") {

                    $("#showqrcode").text("获取二维码失败!");
                    document.getElementById("showqrcode").style.color = "red";
                    document.getElementById("showqrcode").style.fontSize = "200%";
                }
            }
        });
    }
</script>
</body>
</html>
