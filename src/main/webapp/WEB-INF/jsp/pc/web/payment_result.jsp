<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@include file="header.jsp"%>
<script>
    function towebStockUserinfo() {
        window.load.href="web/stock/userinfo";
    }

</script>
<body>
<%--<%@include file="header_title.jsp"%>--%>
<div>
    <h1>
        <c:if test="${result==0}">支付失败</c:if>
        <c:if test="${result==1}">支付成功</c:if>
    </h1>

    <a href="#" onclick="towebStockUserinfo()">返回</a>
<%--<%@include file="footer.jsp" %>--%>
</div>
</body>
</html>
