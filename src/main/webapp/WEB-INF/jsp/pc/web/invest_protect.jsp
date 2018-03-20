<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@include file="header.jsp" %>
<body>
<%@include file="header_title.jsp"%>
<img src="img/web/banner_about.jpg" style="margin-bottom: -4px;min-width: 1280px;" width="100%" />

<div style="width: 1280px;margin: 0 auto;">
    <%--<div style="line-height: 25px;padding: 15px 0;border-bottom: 1px solid #ebebeb;margin-bottom: 20px;">--%>
        <%--<h4 class="size-11">${adverInfo.title}</h4>--%>
        <%--<h5 class="color-hs size-8">${adverInfo.createTime}</h5>--%>
    <%--</div>--%>
    <p style="line-height: 35px;margin-bottom: 30px;text-indent: 2em;color: #646464;">${adverInfo.adverContent}
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
    });
</script>
</body>
</html>

