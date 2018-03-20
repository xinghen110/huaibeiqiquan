<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header" style="width:100%;">
    <div style="width: 1280px; margin: 0 auto;">
        <img src="img/web/logo_ruanyun.png" class="fl"/>
        <ul id="menu">
            <li><a href="web/index">首页</a></li>
            <li><a href="web/product">产品介绍</a></li>
            <li><a href="web/aboutus">关于我们</a></li>
            <li><a href="web/industry/information/list">行业资讯</a></li>
            <li><a href="web/latest/analysis/information/list">实时解盘</a></li>
            <%--<li><a href="web/download">软件下载</a></li>--%>
            <li><a href="web/stock/center">交易中心</a></li>
            <li><a href="web/realTime/open">实盘开户</a></li>
            <li style="width: 133px;margin-left: 100px;"><img style="margin-top: 9px" src="img\web\img_demo.png"></li>
        </ul>
    </div>

    <script>
        var url = window.location.href;
        $("#menu li a").each(function (index, element) {
            var $e = $(element);
            var linkHref = $e.attr("href");
            if (url.indexOf(linkHref) > -1
                || (url.indexOf("web/stock/") > -1 && $e.attr("href") == "web/realTime/open" && url.indexOf("web/stock/center") == -1)
            ) {
                $e.addClass("header-ul-select");
                return;
            }
        });
    </script>
</div>