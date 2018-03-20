<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
    <%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
    <title>协议</title>
</head>
<body>
<style>
*{padding:0px;margin:0px;}
body{ font-family:黑体; font-size:.9em}
.fw_auto{width:90%;margin:0 auto;color: #242424;}
.fw_hd{width:90%;height:auto; position:relative;}
.fw_hd p{ bottom:0px;height:2.4em;line-height:2.4em; font-size:1.2em;width:100%;height: auto;font-size: 18px;color: #242424;font-weight: bold;}
.fw_main{padding:1em 0}
 
.fw_info{margin: 5px 0px 14px;color: #888888;}
.fw_img{margin: 10px 0px 0px;text-align: center;}
.fw_main p{font-size:1em;color:#555;line-height:1.4em} 
 
            html,body{
                height:100%;
                width:100%;
                padding:0;
                margin:0;
            }
            #preview{
                width:120%;
                height:100%;
                padding:0;
                margin:0;
            }
            #preview *{font-family:sans-serif;font-size:16px;width:100%;}
        </style>
        <script type="text/javascript" charset="utf-8" src="/daowei/ueditor/dialogs/internal.js"></script>
         <script type="text/javascript" charset="utf-8" src="/daowei/ueditor/ueditor.parse.js"></script>
<div data-role="page" id="indiana-details">
    <input type="hidden" id="userNum" value="${userNum}">
    <div data-role="header" style="background: white;color: black;border: none;">
        <a data-role="button" onclick="goBack(1)"><img src="app/img/icon_nav_back.png"/></a>
        <h4 class="text-shadow">${commonProblem.title}</h4>
    </div>
    
     <div id="preview"  style="width: 100%;" data-role = "content">
 ${commonProblem.content }
        </div>
</div>
</body>
<script type="text/javascript">
    /**
     * 返回前一页（或关闭本页面）
     * <li>如果没有前一页历史，则直接关闭当前页面<>
     */
    function goBack(num){
        if (history.length == 1) {
            var arrayString=num;
            var u = navigator.userAgent;
            var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
            var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
            if(isAndroid){
                window.ruanyun.viewBack(arrayString);
            }else if(isiOS){
                window.webkit.messageHandlers.viewBack.postMessage(arrayString);
            }
        } else {
            window.history.go(-1);
        }
    }
 
	
        uParse('#preview',{
            rootPath : '/daowei/',
            chartContainerHeight:100
        })
        dialog.oncancel = function(){
            document.getElementById('preview').innerHTML = '';
        }
    </script>
</html>