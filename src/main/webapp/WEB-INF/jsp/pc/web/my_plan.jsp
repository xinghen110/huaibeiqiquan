<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title></title>
    <link rel="stylesheet" href="css/web/mystyle.css" />
    <link rel="stylesheet" href="css/web/carousel.css" />
    <link rel="stylesheet" href="css/web/featureCarousel.css" />

    <script type="text/javascript" src="js/web/jquery.min.js" ></script>
    <script type="text/javascript" src="js/web/mj.js" ></script>
</head>
<body>
<!--<div class="header" style="width:100%;">
    <div style="width: 1280px; margin: 0 auto;">
        <img src="img/logo_jinniu.png" class="fl" />
        <ul>
            <li><a class="header-ul-select">首页</a></li>
            <li><a href="product_introduction.html">产品介绍</a></li>
            <li><a href="abouts_us.html">关于我们</a></li>
            <li><a>行业资讯</a></li>
            <li><a>实时解盘</a></li>
            <li><a>软件下载</a></li>
            <li><a href="realTimeOpening.html">实盘开户</a></li>
            <li><a>交易</a></li>
        </ul>
    </div>
</div>-->
<style>
    .user_login_header{background: url(img/web/img_user.jpg) no-repeat; width: 100%; background-size: 100% 100%; height: 221px; }
    .user_login_header_info{position: relative;width: 900px; margin: 0 auto; background: rgba(255,255,255,.8);height: 145px; top: 36px;}
    .user_login_header_info .img_user_tx{width: 155px; border-radius: 50%; position: absolute; left: -90px; margin-top: -5px;}
    .user_login_header_info .hr{position: relative; top: 77px; margin: 0 12%; border: none; border-top: 1px solid #999; margin-right: 5%;}
    .user_login_header_info .name_h4{position: absolute;left: 105px; top: 26px; font-size: 20px; color: #333333;}
    .user_login_header_info .exit_login{position: absolute; left: 385px; top: 19px; font-size: 15px; background: #3984E1;color: white; border: none; width: 140px; height: 40px; border-radius: 5px;outline: none;cursor: pointer;}
    .user_login_header_info .exit_login:hover{background: red;}
    .user_login_header_info .tel_h4{position: absolute;left: 105px; top: 100px; font-size: 14px; color: #333333;}
    .user_login_header_info .update_password{position: absolute;left: 290px; top: 100px; font-size: 14px;color: #069;text-decoration: none;}
    .user_login_header_info .update_password:hover{color: #CE4972;}

    .personal_center .left_list .li_a_selected{background: #3984E1;color: white;}
    .personal_center{width: 1180px;margin: 0 auto;}
    .personal_center .left_list{float: left;width: 230px;border: 1px solid #EEEEEE;border-bottom: 0px; margin: 25px 0px;}
    .personal_center .left_list li{float: left; width: 100%; border-bottom: 1px solid #EEEEEE; position: relative;}
    .personal_center .left_list li h2{float: left;line-height: 50px; width: 205px; padding-left: 25px; background: #FAFAFA; color: #656565; font-size: 18px; font-weight: normal;}
    .personal_center .left_list li a{float: left; width: 130px;padding-left: 100px; line-height: 50px; color: #656565; font-size: 14px;}
    .personal_center .left_list li a:hover{background: #3984E1;color: white;}
    .personal_center .left_list li img{position: absolute; width: 21px; left: 69px; top: 14px}
    .personal_center_div{float: right; width: 915px; padding: 25px 0px;}
    .personal_center_div .member_title{width: 100%;height: 50px; border-bottom: 1px solid #DDDDDD;line-height: 50px; color: #333333; font-size: 14px; margin-bottom: 15px;}
    .personal_center_div .member_title h3{font-weight: bold!important;color: #333333; font-size: 16px;}
    table{float: left;margin-left: 20px;width: 900px; border: 1px solid #EEEEEE; border-left: 0px; border-bottom: 0px;margin-bottom: 20px;}
    table th,td{border-left: 1px solid #EEEEEE;border-bottom: 1px solid #EEEEEE;}
    table tr th{padding: 15px 0px; text-align: center; color: #333333; font-size: 12px;line-height: 20px;vertical-align: top; font-weight: normal;}
    table tr td{padding: 12px 0px;line-height: 25px; color: #333333;font-size: 12px;text-align: center;}
    table tr td span{color: #FF3F13; font-size: 16px; font-weight: bold!important;}
    table tr td input{width: 100px; height: 30px; line-height: 30px; border: 1px solid #999999;border-radius: 4px; margin-right: 10px; padding-left: 5px;}
    table tr td select{width: 105px; height: 35px; line-height: 35px; border: 1px solid #999999;border-radius: 4px; margin-right: 10px; padding-left: 5px;}
    /*去掉input[type=number]的默认样式*/
    input[type=number] {-moz-appearance:textfield; }
    input[type=number]::-webkit-inner-spin-button,
    input[type=number]::-webkit-outer-spin-button { -webkit-appearance: none;margin: 0;}
    .next_btn{font-size: 14px;    width: 85%; background: #3984E1;color: white; border: none;  padding: 0 6px;height: 40px; border-radius: 5px;outline: none;cursor: pointer;}
    .next_btn:hover{background: red;}
    .tx_two{float: left; width: 770px; margin-left: 20px; border: 1px solid #EEEEEE; background: #F9F9F9; padding: 30px;}
    .tx_two .bottom{float: left; width: 100%;color: #333333;font-size: 14px; line-height: 25px;}
    .ul{float: left!important; width: 100%!important; border: none!important;border-bottom: 1px solid #DCDCDC!important; padding-bottom: 15px!important;}
    .ul li{float: left!important; width: 230px!important; padding-left: 20px!important;border: none!important;}
    .ul h3{float: left; width: 100%; height: 40px; color: #333333; font-size: 16px; font-weight: bold!important;}
    .ul .desc{float: left; width: 100%; line-height: 25px; color: #333333; font-size: 14px;}

    .ul_one{margin-bottom: 20px;margin-left: 21px; font-size: 14px; color: #333;float: left; width: 850px; height: 40px; border-bottom: 1px solid #DCDCDC; line-height: 38px;}
    .ul_one li{float: left; text-align: center; width: 119px;border-left: 1px solid #fff; border-right: 1px solid #fff;color: #656565; font-size: 14px; background: #fff; border-top: 2px solid #fff; font-size: 16px; cursor: pointer;}
    .ul_one .sel{border-left: 1px solid #ccc; border-right: 1px solid #ccc;height: 38px;border-bottom: 1px solid #fff;border-top: 2px solid #3984E1;}

    .realName_authentication_div{padding-right: 102px;display: none;box-shadow:0px 5px 48PX #EBEBEB!important;background: white;width: 570px;height: 740px;margin: 0 auto; position: absolute;left: 50%;margin-left: -336px;top: 50%;margin-top: -324px;}

    .realName_authentication_div .title_div{float: left;width: 118%;height: 50px;background: #4997CF;}
    .realName_authentication_div .title_div i{float: left;padding-left: 18px;color: #fff;font-size: 18px;line-height: 50px;padding-left: 45px;}
    .realName_authentication_div .title_div .close{color: white;float: right;margin-right: 18px;margin-top: 13px;cursor: pointer;width: 25px;height: 25px;}
    .realName_authentication_div .ts_info{margin-top: 10px;width: 100%;float: left;text-align: center;font-size: 12px;color: #333333;padding-bottom: 10px;}
    .realName_authentication_div .submit{margin-top: 11px;cursor: pointer;font-size: 15px; background: #3984E1;color: white; border: none; width: 140px; height: 40px; border-radius: 5px;    margin-left: 184px;}
    .realName_authentication_div .submit:hover{background:red;color:white}
    .confirm_updata {font-size: 15px; background: #3984E1;color: white; border: none; width: 90px!important; height: 25px!important; border-radius: 5px;outline: none;cursor: pointer;}
    .confirm_updata:hover{background: red;}
</style>
<%@include file="user_login_header.jsp" %>

<div class="personal_center">
    <ul class="left_list">
        <li><h2>个人中心</h2></li>
        <li><img src="img/web/home_grey.png" /><a href="web/stock/userinfo" >个人资料</a></li>
        <li><img src="img/web/recharge_grey.png" /><a href="web/stock/deposit">账户充值</a></li>
        <li><img src="img/web/withdrawal_grey.png" /><a href="web/stock/withdraw" >账户提现</a></li>
        <c:if test="${sessionScope.systemUser.userType == sessionScope.spreadUserType}">
            <li><img src="img/web/extension_grey.png" /><a href="web/stock/promotion">推广赚钱</a></li>
        </c:if>
        <li><img src="img/web/data_white.png" /><a href="web/stock/plan/list"  class="li_a_selected">我的方案</a></li>
    </ul>
    <div class="personal_center_div index1-div1">
        <div class="member_title">
            <h3>我的方案</h3>
        </div>
        <ul class="ul_one qie_ul">
            <li onclick="select_table(this)" class="sel">
                申请中
            </li>
            <li onclick="select_table(this)" class="">
                持仓中
            </li>
            <li onclick="select_table(this)" class="">
                行权中
            </li>
            <li onclick="select_table(this)" class="">
                已结算
            </li>
            <li onclick="select_table(this)"  class="">
                申请失败
            </li>
        </ul>
        <%--申请中--%>
        <table cellpadding="0" cellspacing="0">
            <tr>
                <th>订单号</th>
                <th>股票</th>
                <th>股票代码</th>
                <th>股票现价</th>
                <th>名义本金</th>
                <th>买入限价</th>
                <th>管理费</th>
                <th>下单日期</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${stockPlanListMap}" var="item">
                <c:if test="${item.orderStatus==1}">
                    <tr>
                        <td>${item.planId}</td>
                        <td>${item.symbolName}</td>
                        <td>${item.symbol}</td>
                        <td>${item.curPrice}</td>
                        <td>${item.buyMarketPrice}</td>
                        <td>${item.buyLimitPrice}</td>
                        <td>${item.fee}</td>
                        <td><fmt:formatDate value="${item.createTime}" pattern="yyyy/MM/dd" /></td>
                        <td><button onclick="getPlanDetail(${item.planId})" class="next_btn">方案详情</button></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
        <%--持仓中--%>
        <table style="display:none;" cellpadding="0" cellspacing="0">
            <tr>
                <th>订单号</th>
                <th>建议日期</th>
                <th>股票</th>
                <th>股票代码</th>
                <th>股票现价</th>
                <th>生效日期</th>
                <th>结束日期</th>
                <th>买入限价</th>
                <%--<th class="color-white">市值</th>--%>
                <th>买入价格</th>
                <%--<th class="color-white">总价格</th>--%>
                <th>下单日期</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${stockPlanListMap}" var="item">
                <c:if test="${item.orderStatus==2}">
                <tr>
                    <td>${item.planId}</td><!--订单号-->
                    <td><fmt:formatDate value="${item.buyRecommendDate}" pattern="yyyy/MM/dd" /></td><!--建议日期-->
                    <td>${item.symbolName}</td><!--股票-->
                    <td>${item.symbol}</td><!--股票代码-->
                    <td>${item.curPrice}</td><!--股票现价-->
                    <td><fmt:formatDate value="${item.buyConfirmDate}" pattern="yyyy/MM/dd" /></td><!--生效日期-->
                    <td><fmt:formatDate value="${item.buyEndDate}" pattern="yyyy/MM/dd" /></td><!--结束日期-->
                    <td>${item.buyLimitPrice}</td><!--买入限价-->
                        <%--<td>${item.buyMarketPrice}</td><!--市值-->--%>
                    <td>${item.buyPrice}</td><!--买入价格-->
                        <%--<td>${item.manageFee}</td><!--总价格-->--%>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy/MM/dd" /></td><!--下单日期-->
                    <td><button onclick="getPlanDetail(${item.planId})" class="next_btn">方案详情</button></td>
                </tr>
                </c:if>
            </c:forEach>
        </table>
        <%--行权中--%>
        <table style="display:none;" cellpadding="0" cellspacing="0">
            <tr>
                <th>订单号</th>
                <th>建议日期</th>
                <th>股票</th>
                <th>股票代码</th>
                <th>股票现价</th>
                <th>生效日期</th>
                <th>结束日期</th>
                <th>卖出限价</th>
                <th>卖出申请时间</th>
                <th>状态</th>
                <th>下单日期</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${stockPlanListMap}" var="item">
                <c:if test="${item.orderStatus==3}">
                    <tr>
                        <td>${item.planId}</td><!--订单号-->
                        <td><fmt:formatDate value="${item.buyRecommendDate}" pattern="yyyy/MM/dd" /></td><!--建议日期-->
                        <td>${item.symbolName}</td><!--股票-->
                        <td>${item.symbol}</td><!--股票代码-->
                        <td>${item.curPrice}</td><!--股票现价-->
                        <td><fmt:formatDate value="${item.buyConfirmDate}" pattern="yyyy/MM/dd"/></td>
                        <td><fmt:formatDate value="${item.buyEndDate}" pattern="yyyy/MM/dd"/></td>
                        <td>${item.sellLimitPrice}</td>
                        <td>${item.sellCreateTime}</td>
                        <td>${item.orderStatusName}</td>
                        <td><fmt:formatDate value="${item.createTime}" pattern="yyyy/MM/dd" /></td><!--卖出申请时间-->
                        <td><button onclick="getPlanDetail(${item.planId})" class="next_btn">方案详情</button></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
        <%--已结算--%>
        <table style="display:none;" cellpadding="0" cellspacing="0">
            <tr>
                <th>订单号</th>
                <th>股票名称</th>
                <th>股票代码</th>
                <th>股票现价</th>
                <th>名义本金</th>
                <th>买入类型</th>
                <th>买入限价</th>
                <th>申买时间</th>
                <th>卖出类型</th>
                <th>卖出限价</th>
                <th>申卖时间</th>
                <th>管理费</th>
                <th>收益</th>
                <th>操作</th>

            </tr>
            <c:forEach items="${stockPlanListMap}" var="item">
                <c:if test="${item.orderStatus==4}">
                <tr>
                    <td>${item.planId}</td>
                    <td>${item.symbol}</td>
                    <td>${item.symbolName}</td>
                    <td>${item.curPrice}</td><!--股票现价-->
                    <td>${item.buyMarketPrice}</td>
                    <td>${item.buyPriceTypeName}</td>
                    <td>${item.buyLimitPrice}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yy/MM/dd"/></td>
                    <td>${item.sellPriceTypeName}</td>
                    <td>${item.sellLimitPrice}</td>
                    <td><fmt:formatDate value="${item.sellCreateTime}" pattern="yy/MM/dd"/></td>
                    <td>${item.fee}</td>
                    <td>${item.profit}</td>
                    <td><button onclick="getPlanDetail(${item.planId})" class="next_btn">方案详情</button></td>
                </tr>
            </c:if>
        </c:forEach>
        </table>
        <%--申请失败--%>
        <table style="display:none;" cellpadding="0" cellspacing="0">
            <tr>
                <th>订单号</th>
                <th>股票名称</th>
                <th>股票代码</th>
                <th>股票现价</th>
                <th>名义本金</th>
                <th>买入类型</th>
                <th>买入限价</th>
                <th>申买时间</th>
                <th>管理费</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${stockPlanListMap}" var="item">
                <c:if test="${item.orderStatus==-1}">
                <tr>
                    <td>${item.planId}</td>
                    <td>${item.symbol}</td>
                    <td>${item.symbolName}</td>
                    <td>${item.curPrice}</td><!--股票现价-->
                    <td>${item.buyMarketPrice}</td>
                    <td>${item.buyPriceTypeName}</td>
                    <td>${item.buyPriceLimt}</td>
                    <td>${item.createTime}</td>
                    <td>${item.fee}</td>
                    <td><button onclick="getPlanDetail(${item.planId})" class="next_btn">方案详情</button></td>
                </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
</div>
<div class="realName_authentication_div">
    <div class="title_div">
        <i>方案详情</i>
        <span onclick="close_realName_authentication()" class="close">x</span>
    </div>
    <table style="width: 100%; margin: 50px;">
        <tr>
            <th>股票名称</th>
            <th class="symbolName"></th>
            <th>股票代码</th>
            <th class="symbol"></th>
        </tr>
        <tr>
            <td>名义本金</td>
            <td class="buyMarketPrice"></td>
            <td>周期</td>
            <td class="cycleName"></td>
        </tr>
        <tr>
            <td>买入类型</td>
            <td class="buyPriceTypeName"></td>
            <td>买入限价</td>
            <td class="buyLimitPrice"></td>
        </tr>
        <tr>
            <td>推荐买入日期</td>
            <td class="buyRecommendDate"></td>
            <td>买入价格</td>
            <td class="buyPrice"></td>
        </tr>
        <tr>
            <td>确认买入日期</td>
            <td class="buyConfirmDate"></td>
            <td>方案结束日期</td>
            <td class="buyEndDate"></td>
        </tr>
        <tr>
            <td>卖出价格类型</td>
            <td class="sellPriceTypeName"></td>
            <td>卖出限价</td>
            <td class="sellLimitPrice"></td>
        </tr>
        <tr>
            <td>卖出申请时间</td>
            <td class="sellCreateTime"></td>
            <td>卖出价格</td>
            <td class="sellPrice"></td>
        </tr>
        <tr>
            <td>确认卖出时间</td>
            <td class="sellConfirmTime"></td>
            <td>管理费</td>
            <td class="fee"></td>
        </tr>
        <tr>
            <td>方案创建时间</td>
            <td class="createTime"></td>
            <td>股票现价</td>
            <td class="curPrice">&nbsp;</td>
        </tr>
        <tr>
            <td>收益</td>
            <td class="profit"></td>
            <td>净收益</td>
            <td class="netProfit"></td>
        </tr>
    </table>
    <div class="stock_sell_div" style="border-top: 1px solid white; border-bottom: 1px solid white;padding: 14px 0; line-height: 25px;">
        <%--<h3 class="statistics_title">行权</h3>--%>
        <%--<h4 class="statistics_title">--%>
            <%--<span>&nbsp;</span>--%>
            <%--<span>价格类型</span>--%>
            <%--<span>行权限价</span>--%>
            <%--<span>操作</span>--%>
        <%--</h4>--%>
        <h4 class="statistics_content" style="text-align: center">
            <form action="web/stock/exercise" method="post">
                <span style="    font-size: 14px;">价格类型:</span><select name="sellPriceType" id="sellPriceType" onchange="$('#sellLimitPrice').css('display',$(this).val()==0?'none':'inline-block')">
                    <option value="">请选择价格类型</option>
                    <option value="1">限价</option>
                </select>
                <span id="sellLimitPrice" style="display: none;font-size: 14px;">行权限价:<input type="text" name="sellLimitPrice" ></span><br/><br/>
                <%--<span>&nbsp;</span>--%>
                <%--<span><select name="sellPriceType" id="sellPriceType" onchange="$('#sellLimitPrice').css('display',$(this).val()==0?'none':'inline-block')">--%>
								<%--<option value="0">市价</option>--%>
								<%--<option value="1">限价</option>--%>
							<%--</select></span>--%>
                <%--<span><input type="text" name="sellLimitPrice" id="sellLimitPrice" style="display: none;">&nbsp;</span>--%>
                <span><button class="confirm_updata" type="submit">确认行权</button></span>
                <input type="hidden" class="planId" name="planId" value="">
            </form>
        </h4>
    </div>
</div>
<%@include file="updatePassword.jsp" %>
<%@include file="footer.jsp" %>

<script type="text/javascript">
//    function details(){
//        $('.realName_authentication_div').show()//显示实名认证弹窗
//    }
    function close_realName_authentication(){
        $('.realName_authentication_div').hide()//显示实名认证弹窗
    }
    function select_table(obj){
        $(obj).parent().find('li').removeClass('sel');
        $(obj).addClass('sel');
        var title_aVal = $(obj).index();
        $('.index1-div1 table').hide();
        $('.index1-div1 table').eq(title_aVal).show();
    };
</script>
<script src="https://cdn.bootcss.com/moment.js/2.19.0/moment.min.js"></script>
<script type="text/javascript">
    var wH = $(window).height(); //获取屏幕高度
    hqkg();
    /**************************************登录后**************************************/
    $(".grzx .exit-login").click(function(){//退出登录
        window.location.href='realTimeOpening_login.html';
    });
    function hqkg(){
        var ydl_img_H = $(".ydl-img").height();
        var ydl_img_W = $(".ydl-img").width();
        $(".grzx").height(ydl_img_H);
        $(".grzx").css("margin-top",-(ydl_img_H));
    }
    $('.update-password').click(function(){//修改密码
        window.location.href = 'realTimeOpening_update_password.html'
    });
    /*我的方案&方案详情之前的切换*/
    //    $(".index1 .index1-div1 table tr td:last-child h4").click(function(){//进入方案详情
    //        $(".index1 .index1-div1").hide();
    //        $(".index1 .index1-div2").show();
    //    });
    $(".index1 .index1-div2 .break").click(function(){//返回我的方案
        $(".index1 .index1-div2").hide();
        $(".index1 .index1-div1").show();
    });
    //标题状态切换
    function choose_title(obj){
        $(obj).parent().find('.title_a').removeClass('title_a_select');
        $(obj).addClass('title_a_select');
        var title_aVal = $(obj).index();
        $('.index1-div1 table').hide();
        $('.index1-div1 table').eq(title_aVal).show();
    }
    /*我的方案&方案详情之前的切换end*/
    $(function(){  /*调整窗口自动调整宽度*/
        $(window).resize(function(){//实时获取宽高
            var ydl_img_H = $(".ydl-img").height();
            var ydl_img_W = $(".ydl-img").width();
            $(".grzx").height(ydl_img_H);
            $(".grzx").css("margin-top",-(ydl_img_H));
        });
    });
    var data = ${stockPlanListMapJson};

    function getPlanDetail(planId) {
        var dateFormat = "YYYY-MM-DD"
        for (var i = 0; i < data.length; i++) {
            if (planId == data[i].planId) {
                var createTime = data[i].createTime ? moment(data[i].createTime).format(dateFormat) : "";
                var buyRecommendDate = data[i].buyRecommendDate ? moment(data[i].buyRecommendDate).format(dateFormat)  : "";
                var buyConfirmDate = data[i].buyConfirmDate ? moment(data[i].buyConfirmDate).format(dateFormat) : "";
                var buyEndDate = data[i].buyEndDate ? moment(data[i].buyEndDate).format(dateFormat) : "";
                var sellCreateTime = data[i].sellCreateTime ? moment(data[i].sellCreateTime).format(dateFormat) : "";
                var sellConfirmTime = data[i].sellConfirmTime ? moment(data[i].sellConfirmTime).format(dateFormat) : "";
                var symbolName = data[i].symbolName ? data[i].symbolName : "";
                var curPrice = data[i].curPrice ? data[i].curPrice : "";
                var symbol = data[i].symbol ? data[i].symbol : "";
                var buyMarketPrice = data[i].buyMarketPrice ? data[i].buyMarketPrice : "";
                var cycleName = data[i].cycle ? data[i].cycleName : "";
                var buyPriceTypeName = data[i].buyPriceTypeName ? data[i].buyPriceTypeName : "";
                var buyLimitPrice = data[i].buyLimitPrice ? data[i].buyLimitPrice : "";
                var buyPrice = data[i].buyPrice ? data[i].buyPrice : "";
                var sellMarketPrice = data[i].sellMarketPrice ? data[i].sellMarketPrice : "";
                var sellPriceTypeName = data[i].sellPriceTypeName ? data[i].sellPriceTypeName : "";
                var sellLimitPrice = data[i].sellLimitPrice ? data[i].sellLimitPrice : "";
                var sellPrice = data[i].sellPrice ? data[i].sellPrice : "";
                var fee = data[i].fee ? data[i].fee : "";
                var profit = data[i].profit ? data[i].profit : "";
                var netProfit = data[i].netProfit ? data[i].netProfit : "";
                var orderStatus = data[i].orderStatus;
                $(".createTime").html(createTime);
                $(".curPrice").html(curPrice);
                $(".buyRecommendDate").html(buyRecommendDate);
                $(".buyConfirmDate").html(buyConfirmDate);
                $(".buyEndDate").html(buyEndDate);
                $(".sellCreateTime").html(sellCreateTime);
                $(".sellConfirmTime").html(sellConfirmTime);
                $(".symbolName").html(symbolName);
                $(".symbol").html(symbol);
                $(".buyMarketPrice").html(buyMarketPrice);
                $(".cycleName").html(cycleName);
                $(".buyPriceTypeName").html(buyPriceTypeName);
                $(".buyLimitPrice").html(buyLimitPrice);
                $(".buyPrice").html(buyPrice);
                $(".sellMarketPrice").html(sellMarketPrice);
//                $(".sellPriceType").html(sellPriceType);
                $(".sellLimitPrice").html(sellLimitPrice);
                $(".sellPriceTypeName").html(sellPriceTypeName);
                $(".sellPrice").html(sellPrice);
                $(".fee").html(fee);
                $(".profit").html(profit);
                $(".netProfit").html(netProfit);
                $(".planId").val(data[i].planId);
                $(".stock_sell_div").css("display", orderStatus == 2 ? "block" : "none");
                $(".realName_authentication_div").show();
                $(".index1 .index1-div1").hide();
                $(".index1 .index1-div2").show();
            }
        }
    }

    function fmtDate(obj){
        var date =  new Date(obj);
        var y = 1900+date.getYear();
        var m = "0"+(date.getMonth()+1);
        var d = "0"+date.getDate();
        return y+"-"+m.substring(m.length-2,m.length)+"-"+d.substring(d.length-2,d.length);
    }
</script>
</body>
</html>

