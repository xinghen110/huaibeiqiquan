<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>支付中</h1>
<form id="payForm" action="http://211.149.180.207/gateWay/service/bankOnline/pay" method="POST" style="display: none;">
    <table>
        <tr>
            <td>商户编号:</td>
            <td>
                <input id="pay-mchNo-input5" name="mchNo" type="text" size="36" value="${payModel.mchNo}"/>必填
            </td>
        </tr>
        <tr>
            <td>商户类型:</td>
            <td>
                <select id="pay-mchType-input5" name="mchType" style="width:80px;">
                    <option value="1">商户</option>
                    <option value="2">代理商</option>
                </select>必填
            </td>
        </tr>
        <tr>
            <td>支付渠道:</td>
            <td>
                <select id="pay-channel-input5" name="payChannel" style="width:80px;">
                    <%--<option value="1">杉德</option>--%>
                    <%--<option value="1_shwg">1_shwg</option>--%>
                    <option value="1_shdn">1_shdn</option>
                </select>必填
            </td>
        </tr>
        <tr>
            <td>产品类型:</td>
            <td>
                <select id="pay-type-input5" name="payChannelTypeNo" style="width:120px;">
                    <option value="6" selected>银行网关支付</option>
                </select>必填
            </td>
        </tr>
        <tr>
            <td>充值银行:</td>
            <td>
                <select id="bankCode" name="bankCode" class="input-xlarge required">
                    <option value="01020000">工商银行</option>
                    <option value="01050000">建设银行</option>
                    <option value="01030000">农业银行</option>
                    <option value="03080000">招商银行</option>
                    <option value="03010000">交通银行</option>
                    <option value="01040000">中国银行</option>
                    <option value="03030000">广大银行</option>
                    <option value="03050000">民生银行</option>
                    <option value="03090000">兴业银行</option>
                    <option value="03020000">中信银行</option>
                    <option value="03060000">广发银行</option>
                    <option value="03100000">浦发银行</option>
                    <option value="03070000">平安银行</option>
                    <option value="03040000">华夏银行</option>
                    <option value="04083320">宁波银行</option>
                    <option value="03200000">东亚银行</option>
                    <option value="04012900">上海银行</option>
                    <option value="01000000">中国邮储银行</option>
                    <option value="04243010">南京银行</option>
                    <option value="65012900">上海农商行</option>
                    <option value="03170000">渤海银行</option>
                    <option value="64296510">成都银行</option>
                    <option value="04031000">北京银行</option>
                    <option value="64296511">徽商银行</option>
                    <option value="04341101">天津银行</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>订单号:</td>
            <td><input id="pay-orderNo-input5" name="orderNo" type="text" size="36" value="${payModel.orderNo}"/>必填</td>
        </tr>
        <tr>
            <td>订单金额:</td>
            <td><input id="pay-amount-input5" name="amount" type="text" value="${payModel.amount}" size="36"/>必填</td>
        </tr>
        <tr>
            <td>商品名称:</td>
            <td><input id="pay-goodsname-input5" name="goodsName" type="text" value="${payModel.goodsName}" size="36"/>必填</td>
        </tr>
        <tr>
            <td>商品描述:</td>
            <td><input id="pay-goodsdesc-input5" name="goodsDesc" type="text" value="${payModel.goodsDesc}" size="36"/></td>
        </tr>
        <tr>
            <td>frontUrl:</td>
            <td><input id="pay-goodsdesc-input5" name="frontUrl" type="text" value="${payModel.frontUrl}" size="36"/></td>
        </tr>
        <tr>
            <td>notifyUrl:</td>
            <td><input id="pay-goodsdesc-input5" name="notifyUrl" type="text" value="${payModel.notifyUrl}" size="36"/></td>
        </tr>
        <tr>
            <td>时间戳:</td>
            <td><input id="pay-timestamp-input5" name="timeStamp" type="text" size="36" value="${payModel.timeStamp}"/>必填&nbsp; </td>
        </tr>
        <tr>
            <td>签名:</td>
            <td><input id="pay-sign-input5" name="sign" type="text"  size="36" value="${payModel.sign}"/>必填&nbsp; </td>
        </tr>
        <tr>
            <td><input type="submit" value="提交"/></td>
        </tr>
    </table>
</form>
<script>
    document.getElementById("payForm").submit();
</script>
</body>
</html>