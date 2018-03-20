<%@ page import="org.springframework.web.context.request.SessionScope" %>
<%@ page import="com.ruanyun.web.model.sys.TUser" %>
<%@ page import="com.ruanyun.web.util.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    TUser user = (TUser) session.getAttribute("systemUser");
    int spreadUserType = Constants.USER_TYPE_SPREAD_04;
    session.setAttribute("spreadUserType", spreadUserType);
%>
<img src="img/web/banner_user_ydl.jpg" width="100%" class="ydl-img"/>
<div class="grzx">
    <h4 style="padding-top: 5%;" class="color-white"><img class="tx" src="img/web/icon_hp.png"/>
        <span class="name">${(empty sessionScope.systemUserInfo or empty sessionScope.systemUserInfo.userName) ? "未实名" : sessionScope.systemUserInfo.userName}</span>
    </h4>
    <h4 class="color-white" style="padding-top: 4%;"><span>${sessionScope.systemUser.loginName}</span> | <a class="update-password color-white" href="web/stock/password/update">修改密码</a> | <a class="exit-login color-white" href="web/stock/logout">退出登录</a></h4>
</div>
<ul class="product_list" style="margin-top: -4px;">
    <li style="width: 20%!important;" class="fl"><a href="web/stock/userinfo">个人资料</a></li>
    <li style="width: 20%!important;" class="fl"><a href="web/stock/deposit">账户充值</a></li>
    <li style="width: 20%!important;" class="fl"><a href="web/stock/withdraw">账户提现</a></li>
    <c:if test="${sessionScope.systemUser.userType == sessionScope.spreadUserType}">
        <li style="width: 20%!important;" class="fl"><a href="web/stock/promotion">推广赚钱</a></li>
    </c:if>
    <li style="width: 20%!important;" class="fl"><a href="web/stock/plan/list">我的方案</a></li>
</ul>
<script>
    var url = window.location.href;
    $(".product_list li").each(function (index, element) {
        var $e = $(element);
        var linkHref = $e.find("a").attr("href");
        if (url.indexOf(linkHref) > -1) {
            $e.addClass("product_list_li");
            $e.find("a").addClass("color-white");
            return;
        }
    });
</script>
