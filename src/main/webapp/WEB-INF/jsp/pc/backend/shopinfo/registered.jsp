<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
    <%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
    <title>成为技师</title>
</head>
<style>
    #registered_technician .ui-content div{overflow: hidden;}
    #registered_technician .ui-content div a{width: 50%;text-align: center;}
</style>
<body>
<div data-role="page" id="registered_technician">
    <div data-role = "header" style="background: white;border-width: 0px">
        <a data-role="button" data-rel="back" onclick="window.close()"><img src="app/img/icon_nav_back.png"/></a>
        <h4>成为技师</h4>
    </div>
    <div data-role = "content">
        <div class="div1">
            <a href="shopinfo/registered2?shopType=2" data-ajax="false" class="fl js"><img src="app/img/icon_js.png" width="100px"/></a>
            <a href="shopinfo/registered2?shopType=1" data-ajax="false" class="fl sj"><img src="app/img/icon_sj.png" width="100px"/></a>
        </div>
    </div>
</div>
<script type="text/javascript">
    $().ready(function(){
        var wH=document.documentElement.clientHeight||document.body.clientHeight;
        var dH = $(".div1").height();
        var mH = (wH-dH-130)/2+"px";
        $(".div1").css('margin-top',mH);
    });
</script>
</body>
</html>