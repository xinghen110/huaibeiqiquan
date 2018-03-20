<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@include file="header.jsp" %>
<body>
<%@include file="header_title.jsp"%>
<img src="img/web/banner_about.jpg" style="margin-bottom: -4px;min-width: 1280px;" width="100%" />
<ul class="product_list">
    <li class="fl product_list_li">关于我们</li>
    <li class="fl">投资保障</li>
    <li class="fl">集团理念</li>
    <li class="fl">社会责任</li>
    <li class="fl">联系我们</li>
    <li class="fl">合作机构</li>
</ul>

<div class="wc" style="display: ;"><!--集团介绍-->
    <div class="jtjs">
        <div class="td-ys">
            <h4 class="color-white size-15 fl clear">公司简介</h4>
            <h4 style="margin: 30px 0;" class="color-white size-15 fl clear">COMPANY PROFILE</h4>
            <img class="clear fl" src="img/web/icon_company.png" />
        </div>
        <div class="cptd2">
            <h4 class="size-15 fl clear" style="color: white!important;"></h4>
            <h4 class="size-10 fl clear" style="color: white!important;line-height: 27px;">&nbsp;&nbsp;&nbsp;&nbsp;
                金点护航是以“为投资者提供简单，轻松的投资体验”为自身使命，不断提升技术创新能力，专业研究能力，
                以丰富的信息分析，完善的科技支持，更健全的服务体系，为用户提供专业、多元、全面的综合服务性平台。</h4>
        </div>
        <div class="clear cpys">
            <h4 class="size-15 fl clear" style="color: white!important;"></h4>
            <h4 class="size-10 fl clear" style="color: white!important;line-height: 27px;">&nbsp;&nbsp;&nbsp;&nbsp;
                致力于向广大用户推出的简洁、易用的互联网多元金融人才服务平台，全程网络操作，高效便捷。<br/>
                &nbsp;&nbsp;&nbsp;&nbsp;为投资顾问等获取更高投资收益的股票投资者提供可靠的对接平台。<br/>
                &nbsp;&nbsp;&nbsp;&nbsp;为专业资产管理机构推荐输送优秀投资管理人才。</h4>
        </div>
    </div>
</div>
<div class="wc" style="display: none;height: auto!important;"><!--投资保障-->
    <img src="img/web/bg_about1.jpg" width="100%" />
    <img src="img/web/bg_about2_ry.jpg" width="100%" />
</div>
<div class="wc" style="display: none;height: auto!important;"><!--集团理念-->
    <img src="img/web/bg_ideal.jpg" width="100%" />
</div>

<div class="wc text-content-wc"><!--社会责任-->
    <img src="img/web/bg_circle.jpg" width="100%" />
    <div class="abuot-us-title">
        <h1 class="color-red" style="color: #a97b52!important;">社会责任</h1>
        <h1 class="color-hs size-12">SOCIAL RESPONSIBILITY</h1>
    </div>
    <div class="text-content">
        <h4 class="size-15">承担明礼诚信确保产品货真价实的责任</h4>
        <h4 class="size-15">承担科学发展与缴纳税款的责任</h4>
        <h4 class="size-15">承担可持续发展与节约资源的责任</h4>
        <h4 class="size-15">承担保护环境和维护自然和谐的责任</h4>
        <h4 class="size-15">承担公共产品与文化建设的责任</h4>
        <h4 class="size-15">承担扶贫济困和发展慈善事业的责任</h4>
        <h4 class="size-15">承担保护职工健康和确保职工待遇的责任</h4>
        <h4 class="size-15">承担发展科技和创造自主知识产权的责任</h4>
    </div>
</div>

<div class="wc lxwm text-content-wc"><!--联系我们-->
    <img src="img/web/bg_circle.jpg" width="100%" />
    <div class="abuot-us-title">
        <h1 style="color: #a97b52!important;" class="color-red">联系我们</h1>
        <h1 class="color-hs size-12">CONTACT US</h1>
    </div>
    <div class="text-content">
        <h4 class="size-13">联系电话：400-1808871</h4>
        <h4 class="size-13">QQ客服：1264244251</h4>
        <h4 class="size-13">邮箱：JDQQlive@163.com</h4>
        <h4 class="size-13">公司地址：镇江市</h4>
        <%--<h4 class="size-13">官方微信</h4>--%>
        <%--<img src="img/web/img_ewm.png" width="6%" />--%>
    </div>
</div>
<div class="wc hzhp" style="height: auto!important;display: none;"><!--合作伙伴-->
    <img src="img/web/bg_firends(1).jpg" width="100%" />
    <%--<ul style="position: absolute;background: white!important;margin-right: 15px; padding: 30px 0;">--%>
       <%-- <li class="fl">
            <img src="img/web/icon_bank.png" width="66%" />
        </li>
        <li class="fl">
            <img src="img/web/icon_bank.png" width="66%" />
        </li>
        <li class="fl">
            <img src="img/web/icon_bank.png" width="66%" />
        </li>
        <li class="fl">
            <img src="img/web/icon_bank.png" width="66%" />
        </li>
        <li class="fl">
            <img src="img/web/icon_bank.png" width="66%" />
        </li>
        <li class="fl">
            <img src="img/web/icon_bank.png" width="66%" />
        </li>
        <li class="fl">
            <img src="img/web/icon_bank.png" width="66%" />
        </li>
        <li class="fl">
            <img src="img/web/icon_bank.png" width="66%" />
        </li>--%>
    <%--</ul>--%>
</div>
<%@include file="footer.jsp"%>
<script type="text/javascript">
    var wH = $(window).height(); //获取屏幕高度
    $(".product_list li").click(function(){
        $(".product_list li").removeClass('product_list_li');
        $(this).addClass('product_list_li');
        $t = $(this).index();
        if($t==3){
            var text_content_HVal = $(".text-content-wc").height();
            console.info("窗口高度："+ text_content_HVal)
            $(".text-content").height(text_content_HVal)
            $(".text-content").css("margin-top",-(text_content_HVal));
            $(".text-content h4:first-child").css("margin-top",(text_content_HVal/2)-50)
            $(".abuot-us-title").css("margin-top",-(text_content_HVal-(text_content_HVal*0.05)));
        }
        if($t==4){
            var text_content_HVal = $(".lxwm").height();
            console.info("窗口高度："+ text_content_HVal)
            $(".text-content").height(text_content_HVal)
            $(".text-content").css("margin-top",-(text_content_HVal));
            $(".text-content h4:first-child").css("margin-top",(text_content_HVal/2)-50)
            $(".abuot-us-title").css("margin-top",-(text_content_HVal-(text_content_HVal*0.05)));
        }
        if($t==5){
            var hzhp_HVal = $(".hzhp").height();
            var hzhp_WVal = $(".hzhp").width();
            $(".hzhp ul").width(hzhp_WVal/2);
            $(".hzhp ul").css("margin-top", -(hzhp_HVal-(hzhp_HVal*0.19)));
        }
        $(".wc").hide();
        $(".wc").eq($t).show();
    });
    $(function(){
        /*调整窗口自动调整宽度*/
        $(window).resize(function(){//实时获取宽高
            //var h = $(window).height();//屏幕高度
            //var w = $(window).width();//屏幕宽度
            var text_content_HVal = $(".text-content-wc").height();
            $(".text-content").height(text_content_HVal)
            $(".text-content").css("margin-top",-(text_content_HVal));
            $(".abuot-us-title").css("margin-top",-(text_content_HVal-(text_content_HVal*0.05)));
            $(".text-content h4:first-child").css("margin-top",(text_content_HVal/2)-50)

            var hzhp_HVal = $(".hzhp").height();
            var hzhp_WVal = $(".hzhp").width();
            $(".hzhp ul").width(hzhp_WVal/2);
            $(".hzhp ul").css("margin-top", -(hzhp_HVal-(hzhp_HVal*0.2)));
            //console.info("窗口高度：" + h + "; 窗口宽度：" + w);
        });

        var index = ${param.index} -1;
        if (index > -1)
            $(".product_list li:eq(" + index + ")").click();
    });
</script>
</body>
</html>

