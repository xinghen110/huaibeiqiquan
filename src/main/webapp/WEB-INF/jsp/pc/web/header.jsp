<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ry" uri="http://www.ruanyun.com/taglib/ry" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="dj" uri="/WEB-INF/defined-jstl.tld" %>
<ry:binding type="2"></ry:binding>
<ry:binding type="3"></ry:binding>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>"/>
    <meta charset="utf-8"/>
    <title>金点</title>
    <link rel="stylesheet" href="css/web/mystyle.css"/>
    <link rel="stylesheet" href="css/web/carousel.css"/>
    <link rel="stylesheet" href="css/web/featureCarousel.css"/>

    <script type="text/javascript" src="js/web/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.validate.js"></script>
    <script type="text/javascript" src="js/web/mj.js"></script>
    <script type="text/javascript" src="js/ajax2.js"></script>
    <script src="http://api.map.baidu.com/api?v=1.2"></script>
    <script>
        function getUrlPara(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
            var r = window.location.search.substr(1).match(reg);
            if (r!=null) return (r[2]); return null;
        }

        var msg = decodeURI(getUrlPara("msg"));
        if(msg && msg != "null") alert(msg);
    </script>
</head>