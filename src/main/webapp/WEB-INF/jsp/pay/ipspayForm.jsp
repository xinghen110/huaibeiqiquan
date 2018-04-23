<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>订单支付</title>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
</head>
<body>

<form id="form" action="${action}" method="post">
    <input name="pGateWayReq" type="hidden" value="${xml}" />
</form>

<script type="text/javascript">
    $(document).ready(function(){
        $("#form").submit();
    });
</script>

</body>
</html>
