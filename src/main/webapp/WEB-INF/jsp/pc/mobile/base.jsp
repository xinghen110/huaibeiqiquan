<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ry" uri="http://www.ruanyun.com/taglib/ry" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="dj" uri="/WEB-INF/defined-jstl.tld" %>
<%@ page import="com.ruanyun.web.util.Constants" %>
<ry:binding type="2"></ry:binding>
<ry:binding type="3"></ry:binding>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        String imgPath = Constants.DEFAULT_IMG;
        session.setAttribute("imgPath", imgPath);
    %>
    <base href="<%=basePath%>"/>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <%--<title>金点</title>--%>
    <link rel="stylesheet" href="css/mobile/mystyle.css" />
    <link rel="stylesheet" href="http://cdn.bootcss.com/jquery-mobile/1.4.2/jquery.mobile.css" />
    <script type="text/javascript" src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js" ></script>
    <script type="text/javascript" src="http://cdn.bootcss.com/jquery-mobile/1.4.2/jquery.mobile.min.js" ></script>
    <script type="text/javascript" src="layer/mobile/layer.js" ></script>
    <script type="text/javascript" src="layer/layer.js" ></script>
    <script type="text/javascript" src="http://cdn.bootcss.com/jquery-validate/1.11.1/jquery.validate.js"></script>
    <%--<script type="text/javascript" src="js/web/mj.js"></script>--%>
    <script type="text/javascript" src="js/ajax2.js"></script>
    <script type="text/javascript" src="http://cdn.bootcss.com/echarts/3.6.2/echarts.min.js"></script>
    <script type="text/javascript" src="js/localStorage-utils.js"></script>
    <script type="text/javascript" src="js/jquery.qrcode.js"></script>
    <script type="text/javascript" src="js/utf.js"></script>
    <%--<script src="http://api.map.baidu.com/api?v=1.2"></script>--%>
    <script type="text/javascript">

            function getUrlPara(name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
                var r = window.location.search.substr(1).match(reg);
                if (r!=null) return (r[2]); return null;
            }

            var msg = decodeURI(getUrlPara("msg"));
            if(msg && msg != "null") {
                alert(msg);
            }
    </script>
</head>



