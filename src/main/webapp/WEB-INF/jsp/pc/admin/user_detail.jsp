<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<ry:binding type="1" bingdingName="HTTP" parentCode="HTTP"></ry:binding>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户详情</title>

    <script language="javascript" src="js/table/common.js"></script>
    <link  type="text/css" rel="stylesheet" href="js/table/style.css" />
</head>

<style>
    .new_tab{border-collapse: collapse; margin-top: 25px;margin-left: 100px;margin-bottom: 20px}
    .new_tab h2{text-align:center;font-weight:10 }
    .new_tab tr td{height:30px;border:1px solid #e0e0e0; text-indent:10px}
    .investment div{background:#f5f5f5;border-top:2px soild #e0e0e0}
    .on{background:#f5f5f5;border-top:2px solid red}
    .cue{background:#f5f5f5;}
</style>
<div class="investment_f" style="width:100%;height:492px;">
    <div class="investment_con" style="border:none;width:100%;height:460px;overflow: auto;">
        <div class="investment_con_list" style="display:block;">

            <table class="new_tab" style="width: 70%;">
                <tr>
                    <td colspan="4"  class="cue"><h2>个人详情</h2></td>
                </tr>
                <tr>
                    <td width="112" align="right" class="cue">登录名：</td>
                    <td colspan="3" width="200">${userDetail.loginName}</td>
                </tr>
                <tr>
                    <td width="112" align="right" class="cue">昵称：</td>
                    <td colspan="3" width="200">${userDetail.nickName}</td>
                </tr>
                <tr>
                    <td width="112" align="right" class="cue">手机号码：</td>
                    <td colspan="3" width="200">${userDetail.userPhone}</td>
                </tr>
                <c:if test="${systemUser.userType==4}">
                    <tr>
                        <td align="right" class="cue">邀请码：</td>
                        <td id="erweima">${userDetail.userId}</td>
                    </tr>
                    <tr>
                        <td align="right" class="cue">邀请二维码：</td>
                        <td id="qrcode"></td>
                    </tr>
                </c:if>
            </table>
        </div>
    </div>
</div>

<div class="formBar">
    <ul>
        <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
    </ul>
</div>
<c:if test="${systemUser.userType==4}">
    <script type="text/javascript" src="app/js/qrcode.js"></script>
    <script>
        var url = "${HTTP[0].itemCode}/mobile/stock/register?parentCode="+$("#erweima").html();
        new QRCode(document.getElementById("qrcode"),url)
    </script>
</c:if>