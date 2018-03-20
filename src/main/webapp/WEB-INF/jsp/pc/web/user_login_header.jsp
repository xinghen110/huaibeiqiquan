<%@ page import="org.springframework.web.context.request.SessionScope" %>
<%@ page import="com.ruanyun.web.model.sys.TUser" %>
<%@ page import="com.ruanyun.web.util.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    TUser user = (TUser) session.getAttribute("systemUser");
    int spreadUserType = Constants.USER_TYPE_SPREAD_04;
    session.setAttribute("spreadUserType", spreadUserType);
%>
<style>
    .user_login_header_info .trading_center{position: absolute; left: 555px; top: 19px; font-size: 15px; background: #3984E1;color: white; border: none; width: 140px; height: 40px; border-radius: 5px;outline: none;cursor: pointer;}
    .user_login_header_info .trading_center:hover{background: red;}
</style>
<div class="user_login_header">
    <div class="user_login_header_info">
        <img class="img_user_tx" src="img/web/img_user_tx.png" />
        <h4 class="name_h4">您好！<span>${systemUser.loginName}</span></h4>
        <button class="exit_login" onclick="loginout();">退出登录</button>
        <button class="trading_center" onclick="toWebStockCenter();">交易中心</button>
        <hr class="hr" />
        <h4 class="tel_h4">手机号：<span>${systemUser.userPhone}</span></h4>
        <a onclick="updata_password()" class="update_password">修改密码</a>
    </div>
</div>
<script type="text/javascript">
    function loginout(){
        window.location.href='web/stock/logout';
    }
    function getUrlPara(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
        var r = window.location.search.substr(1).match(reg);
        if (r!=null) return (r[2]); return null;
    }

    var msg = decodeURI(getUrlPara("msg"));
    if(msg && msg != "null") alert(msg);
    
    
    function toWebStockCenter() {
        window.location.href='web/stock/center';
    }
</script>
