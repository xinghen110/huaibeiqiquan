<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>table切换</title>

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
<div class="investment_f" style="width:850px;height:482px;">
    <div class="investment_con" style="border:none;width:830px;height:450px;overflow: auto;">
        <div class="investment_con_list" style="display:block;">
            <form action="auditshop/saveExamine?shopNum=${shopInfo.shopNum}&shopName=${shopInfo.shopName}&city=${shopInfo.city}&area=${shopInfo.area}"
                  method="post" id="form" onsubmit="return iframeCallback(this,dialogAjaxDone);">
                <table class="new_tab">
                    <tr>
                        <td colspan="4"  class="cue"><h2>分销详情</h2></td>
                    </tr>

                    <tr>
                        <td width="112" align="right" class="cue">订单创建人：</td>
                        <td  width="200">${userRecord.user.nickName}</td>

                        <td  width="116" align="right" class="cue">付款金额：</td>
                        <td  width="200">${-userRecord.consumPrice}元</td>
                    </tr>

                    <tr>
                        <td width="112" align="right" class="cue">一级分销用户：</td>
                        <td  width="200">
                            <c:if test="${not empty userRecords[0]}">${userRecords[0].user.nickName}</c:if>
                            <c:if test="${empty userRecords[0]}">无</c:if>
                        </td>

                        <td  width="116" align="right" class="cue">获得金额：</td>
                        <td  width="200">
                            <c:if test="${not empty userRecords[0]}">${userRecords[0].consumPrice}元</c:if>
                            <c:if test="${empty userRecords[0]}">无</c:if>
                        </td>
                    </tr>

                    <tr>
                        <td width="112" align="right" class="cue">二级分销用户：</td>
                        <td  width="200">
                            <c:if test="${not empty userRecords[1]}">${userRecords[1].user.nickName}</c:if>
                            <c:if test="${empty userRecords[1]}">无</c:if>
                        </td>

                        <td  width="116" align="right" class="cue">获得金额：</td>
                        <td  width="200">
                            <c:if test="${not empty userRecords[1]}">${userRecords[1].consumPrice}元</c:if>
                            <c:if test="${empty userRecords[1]}">无</c:if>
                        </td>
                    </tr>

                    <tr>
                        <td width="112" align="right" class="cue">三级分销用户：</td>
                        <td  width="200">
                            <c:if test="${not empty userRecords[2]}">${userRecords[2].user.nickName}元</c:if>
                            <c:if test="${empty userRecords[2]}">无</c:if>
                        </td>

                        <td  width="116" align="right" class="cue">获得金额：</td>
                        <td  width="200">
                            <c:if test="${not empty userRecords[2]}">${userRecords[2].consumPrice}元</c:if>
                            <c:if test="${empty userRecords[2]}">无</c:if>
                        </td>
                    </tr>

                </table>
            </form>
        </div>
    </div>
</div>

<div class="formBar" style="background-color: #e4ebf6;">
    <ul>
        <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
    </ul>
</div>