<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
    <%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
</head>
<body>
<style>
    .ui-page-theme-a .ui-radio-on:after, html .ui-bar-a .ui-radio-on:after, html .ui-body-a .ui-radio-on:after, html body .ui-group-theme-a .ui-radio-on:after, .ui-btn.ui-radio-on.ui-btn-a:after{border-color: #31B16C /*{a-active-background-color}*/;}
    #qrcode{
        /*text-align: center;*/
        /*display: table-cell;*/
        /*width: 96px;*/
        /*height: 96px;*/
        /*vertical-align:middle;*/
        /*position: relative;*/
    }
</style>
<div data-role="page" id="sharecode">
    <div data-role = "header" style="background: white;border-width: 0px">
        <a data-role="button" data-rel="back" onclick="goBack(1)"><img src="app/img/icon_nav_back.png"/></a>
        <h4>我的二维码</h4>
    </div>
    <div data-role = "content">
        <div id="qrcode" style="text-align: -webkit-center; margin-top: 100px;" onclick="savePic()">
            <%--<img src="img/pc/img_login_ewm.png" />--%>
        </div>
    </div>
    <script type="text/javascript">
        window.onload = function(){
            var qrcode = new QRCode(document.getElementById("qrcode"), {
                width : 300,//设置宽高
                height : 300
            });
            qrcode.makeCode("${src}");
        }
        function goBack(num){
            var arrayString=num;
            var u = navigator.userAgent;
            var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
            var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
            if(isAndroid){
                window.ruanyun.viewBack(arrayString);
            }else if(isiOS){
                window.webkit.messageHandlers.viewBack.postMessage(arrayString);
            }
        }
        function savePic(){
            var arrayString=$("img")[1].src;
            var u = navigator.userAgent;
            var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
            var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
            if(isAndroid){
                window.ruanyun.savePic(arrayString);
            }else if(isiOS){
                window.webkit.messageHandlers.savePic.postMessage(arrayString);
            }
        }
    </script>
</div>
</body>
</html>
