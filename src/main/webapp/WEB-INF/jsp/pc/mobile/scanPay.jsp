<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src=js/jquery-1.8.0.js"></script>
    <script type="text/javascript" src="js/jquery.qrcode.js"></script>
    <script type="text/javascript" src="js/utf.js"></script>
    <script>

        function sQrcode(qdata) {

            $("#showqrcode").empty().qrcode({		// 调用qQcode生成二维码
                render: "canvas",    			// 设置渲染方式，有table和canvas，使用canvas方式渲染性能相对来说比较好
                text: qdata,    				// 扫描了二维码后的内容显示,在这里也可以直接填一个网址或支付链接
                width: "200",              	// 二维码的宽度
                height: "200",             	// 二维码的高度
                background: "#ffffff",     	// 二维码的后景色
                foreground: "#000000",     	// 二维码的前景色
                src: ""    						// 二维码中间的图片
            });

        }

    </script>
</head>

<body>
<div id="showqrcode"><img src=""></div>
</body>
</html>
