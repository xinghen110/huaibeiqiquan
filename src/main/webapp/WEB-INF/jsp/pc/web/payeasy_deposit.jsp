<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>跳转到支付界面</title>
</head>
<script>
      function sub(){
          document.getElementById("form").submit();
       }
</script>
<body onload="sub();">
<form id="form" method="post" action=" https://pay.yizhifubj.com/customer/gb/pay_bank.jsp">
    <table align="center">
        <tr>
            <td><input type="hidden" name="v_mid" id="v_mid" value="${payment.v_mid}"></td></tr>
        <tr>
            <td><input type="hidden" name="v_oid" id="v_oid" value="${payment.v_oid}"></td></tr>
        <tr>
            <td><input type="hidden" name="v_rcvname" id="v_rcvname" value="${payment.v_rcvname}"></td></tr>
        <tr>
            <td><input type="hidden" name="v_rcvaddr"id="v_rcvaddr" value="${payment.v_rcvaddr}"></td></tr>
        <tr>
            <td><input type="hidden" name="v_rcvtel" id="v_rcvtel" value="${payment.v_rcvtel}"></td></tr>
        <tr>
            <td><input type="hidden" name="v_rcvpost" id="v_rcvpost" value="${payment.v_rcvpost}"></td></tr>
        <tr>
            <td><input type="hidden" name="v_amount" id="v_amount" value="${payment.v_amount}"></td></tr>
        <tr>
            <td><input type="hidden" name="v_ymd" id="v_ymd" value="${payment.v_ymd}"></td></tr>
        <tr>
            <td><input type="hidden" name="v_orderstatus" id="v_orderstatus" value="${payment.v_orderstatus}"></td></tr>
        <tr>
            <td><input type="hidden" name="v_ordername" id="v_ordername" value="${payment.v_ordername}"></td></tr>
        <tr>
            <td><input type="hidden" name="v_moneytype" id="v_moneytype" value="${payment.v_moneytype}"></td></tr>
        <tr>
            <td><input type="hidden" name="v_url" id="v_url" value="${payment.v_url}"></td></tr>
        <tr>
            <td><input type="hidden" name="v_pmode" id="v_pmode" value="${payment.v_pmode}"></td></tr>
        <tr>
            <td><input type="hidden" name="v_md5info" id="v_md5info" value="${payment.v_md5info}"></td></tr>
        <tr>
            <td><input type="hidden" name="v_userref" id="v_userref" value="${payment.v_userref}"></td></tr>
    </table>
</form>
</body>
</html>
