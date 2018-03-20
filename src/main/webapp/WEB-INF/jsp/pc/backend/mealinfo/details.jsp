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
<div class="investment_f" style="width:850px;height:572px;">
    <div class="investment_con" style="border:none;width:830px;height:540px;overflow: auto;">
        <div class="investment_con_list" style="display:block;">

            <table class="new_tab">
                <tr>
                    <td colspan="4"  class="cue"><h2>套餐详情</h2></td>
                </tr>

                <tr>
                    <td width="112" align="right" class="cue">套餐编号：</td>
                    <td  width="200">${mealInfo.mealInfoNum}</td>

                    <td  width="116" align="right" class="cue">套餐类别：</td>
                    <td  width="200">${mealInfo.flag1}</td>
                </tr>

                <tr>
                    <td width="100" align="right" class="cue">套餐名称：</td>
                    <td  width="200">${mealInfo.title}</td>
                    <td  width="100" align="right" class="cue">套餐价格：</td>
                    <td  width="200">${mealInfo.mealPrice}元</td>
                </tr>

                <tr>
                    <td width="100" align="right" class="cue">套餐时长：</td>
                    <td  width="200">${mealInfo.mealLog}分钟</td>
                    <td  width="100" align="right" class="cue">套餐创建时间：</td>
                    <td  width="200"><fmt:formatDate value="${mealInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>

                <tr>
                    <td width="100" align="right" class="cue">套餐已售：</td>
                    <td  width="200">${mealInfo.sold}</td>
                    <td  width="100" align="right" class="cue">是否推荐：</td>
                    <td  width="200">
                        <c:if test="${mealInfo.isRecommend == 1}">推荐</c:if>
                        <c:if test="${mealInfo.isRecommend == 2}">未推荐</c:if>
                    </td>
                </tr>

                <tr>
                    <td width="100" align="right" class="cue">套餐类型：</td>
                    <td  width="200">
                        <c:if test="${mealInfo.mealType == 1}">商家套餐</c:if>
                        <c:if test="${mealInfo.mealType == 2}">技师套餐</c:if>
                    </td>
                    <td  width="100" align="right" class="cue">编号：</td>
                    <td  width="200">${mealInfo.shopNum}</td>
                </tr>

                <tr style="height: 150px;">
                    <td width="100" align="right" class="cue">服务流程：</td>
                    <td  width="200" style="text-indent: 0px;">${mealInfo.fwlc}</td>
                    <td  width="100" align="right" class="cue">功能特效：</td>
                    <td  width="200" style="text-indent: 0px;">${mealInfo.gntx}</td>
                </tr>

                <tr style="height: 150px;">
                    <td width="100" align="right" class="cue">禁忌症状：</td>
                    <td  width="200" style="text-indent: 0px;">${mealInfo.jjzz}</td>
                    <td  width="100" align="right" class="cue">预约须知：</td>
                    <td  width="200" style="text-indent: 0px;">${mealInfo.yyxz}</td>
                </tr>

            </table>
        </div>
    </div>
</div>

<div class="formBar" style="background-color: #e4ebf6;">
    <ul>
        <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
    </ul>
</div>
