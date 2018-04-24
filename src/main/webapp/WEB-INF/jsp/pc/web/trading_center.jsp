<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@include file="header.jsp" %>
<ry:binding type="1" bingdingName="MANAGE_FEE" parentCode="MANAGE_FEE"></ry:binding>
<ry:binding type="1" bingdingName="MARKET_SCALE" parentCode="MARKET_SCALE"></ry:binding>
<ry:binding type="1" bingdingName="STOCK_PLAN_CYCLE" parentCode="STOCK_PLAN_CYCLE"></ry:binding>
<ry:binding type="1" bingdingName="STOCK_PRICE_TYPE" parentCode="STOCK_PRICE_TYPE"></ry:binding>
<body>
<%@include file="header_title.jsp" %>
<link rel="stylesheet" href="https://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.css">
<style>
    .jd{overflow: hidden;width: 1280px;margin: 0 auto;padding: 20px 0;}
    .jd li{width: 20%;}
    .li_opacity{opacity: .5;}
    .content-div2 input[type='text'],input[type='number']{background: #959595;border: none;width: 250px;height: 35px;border-radius:20px;padding-left: 20px;}
    .content-div2 input::-webkit-input-placeholder { color:white;}
    .content-div2 input:-moz-placeholder { color:white;}
    .content-div2 input::-moz-placeholder { color:white;}
    .content-div2 input:-ms-input-placeholder { color:white;}
    .content-div2 input{color:white!important}

    .sz-input{color:white!important}
    .sz-input::-webkit-input-placeholder { color:white;}
    .sz-input:-moz-placeholder { color:white;}
    .sz-input::-moz-placeholder { color:white;}
    .sz-input:-ms-input-placeholder { color:white;}
    .chosen-search-input input[type='text']{
        background: #959595;
        border: none;
        width: 250px;
        height: 35px;
        border-radius: 20px;
        padding-left: 20px;
    }
</style>
<div>
    <img src="img/web/banner_trade.jpg" width="100%" />
    <ul class="jd">
        <li class="fl"><img src="img/web/img_process1.png" /></li>
        <li class="fl li_opacity"><img src="img/web/img_process2.png" /></li>
        <li class="fl li_opacity"><img src="img/web/img_process3.png" /></li>
        <li class="fl li_opacity"><img src="img/web/img_process4.png" /></li>
        <li class="fl li_opacity"><img src="img/web/img_process5.png" /></li>
    </ul>
    <img class="bg_pro1_img" src="img/web/bg_trade3.jpg" width="100%" />
    <style>
        .bg_pro1{overflow: hidden;margin-bottom: 80px;}
        .bg_pro1 .content-div2{position: absolute;width: 100%;}
        .bg_pro1 .content-div2 .ljss{border: none;color: white;background: red;height: 35px;width: 100px;border-radius:20px ;}
        .fl-div{width: 50%;}
        .fl-div .left-div-list-title{height: 60px;line-height: 60px; background: none!important;  color: white;margin-right: 50px;}
        .fl-div .left-div-list{background: rgba(0,0,0,.3);margin-right: 50px;}
        .fl-div .left-div-list table{width: 100%;}
        .fl-div .left-div-list table tr{height: 50px;border-bottom: 1px dashed white;}
        .fl-div .left-div-list table tr:first-child{border-bottom: 1px solid white!important;}

        .fr-div .right-div-list-title{height: 60px;line-height: 60px;margin-left: 50px;color: white;}
        .fr-div .right-div-list{background: rgba(169,123,82,.6);margin-left: 50px;overflow: auto;}
        .fr-div .right-div-list table{width: 100%;}
        .fr-div .right-div-list table tr{height: 50px;border-bottom: 1px solid white;}
    </style>
    <div class="bg_pro1 clear">
        <div class="content-div2 text-align">
            <h4 style="height: 70px;line-height: 70px;color: #a97b52!important;" class="color-red size-13">选择股票标的</h4>
            <div class="query_input">
                <%--<div class="chosen_area" style="display:inline;">--%>
                <%--<select id="Symbol" data-placeholder="选择股票代码" class="chosen-select" multiple style="width:350px;" tabindex="1">--%>
                    <%--<option value="1">请输入股票代码</option>--%>
                    <%--<c:forEach var="item" items="${dataList}">--%>
                        <%--<option value="${item.symbol}">${item.symbol}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
                <%--</div>--%>
                    <h4 class="color-white" style="float: left; margin-left: 19%;width: 270px;margin-top: -11px;text-align: left;" >股票标的</h4>
                    <h4 class="color-white" style="margin-bottom: 10px; float: left; margin-left: 16%;    margin-top: -11px;">管理费率</h4>
                    <input class="fl clear" style="margin-left: 19%;background:rgba(255,255,255,.5)!important;"  type="text" id="Symbol" placeHolder="请输入股票代码或股票名称"/>
                <%--<input type="text"  style="margin-left: 19%;" class="fl clear" placeholder="请输入股票代码" id="Symbol"/>--%>
                <input type="number" style="width: 100px;margin-left: 15%;" class="fl" id="Min_option_price" placeholder="请输入最低价"/>
                <h4 style="font-size: 24px;margin: 0 12px;" class="fl">~</h4>
                <input type="number" style="width: 100px;margin-right: 3%;" class="fl" id="Max_option_price" placeholder="请输入最高价"/>
                <input class="fl ljss" type="button" value="立即搜索" onclick="queryStock()" style="cursor: pointer;background: #a97b52!important;"/>
            </div>
            <div class="fl fl-div">
                <h4 class="text-align fr left-div-list-title" ><span style="font-weight: bold;">全部标的</span></h4>
                <div class="clear fr left-div-list" style="overflow: auto;">
                    <table>
                        <thead>
                        <tr>
                            <th style="width: 15%;" class="text-align color-white">序号</th>
                            <th style="width: 20%;" class="text-align color-white">股票代码</th>
                            <th style="width: 20%;" class="text-align color-white">股票名称</th>
                            <th style="width: 20%;" class="text-align color-white">管理费率</th>
                            <th class="text-align color-white">操作</th>
                        </tr>
                        </thead>
                        <tbody id="stockList"></tbody>
                    </table>
                </div>
            </div>
            <div class="fl fr-div">
                <h4 class="text-align right-div-list-title">我的自选股</h4>
                <div class="fl right-div-list" style="overflow: auto;">
                    <table style="width: 100%;">
                        <thead>
                        <tr>
                            <th style="width: 15%;" class="text-align color-white">序号</th>
                            <th style="width: 20%;" class="text-align color-white">股票代码</th>
                            <th style="width: 20%;" class="text-align color-white">股票名称</th>
                            <th style="width: 20%;" class="text-align color-white">管理费率</th>
                            <th class="text-align color-white">操作</th>
                        </tr>
                        </thead>
                        <tbody id="userStockList"></tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <style>
        .choose-szgs span{width: 50%;text-align: center;}
        .sz-list{width: 100%;}
        .sz-list li{cursor: pointer;width: 30.3%;text-align: center; background:rgba(255,255,255,.8);  padding: 22px 0;margin-bottom: 5.5%;line-height: 32px;}
        .sz-list li:nth-child(3n+2){margin: 0 14px;}
        .gz-list{width: 100%;border: 1px solid white;}
        .gz-list li{height: 50px; line-height: 50px; border-bottom: 1px dashed white;}
        .gz-list li:last-child{border: none;}
        .gz-list li h4{margin: 0 15px;color: white;}

        .sqfa-fl{width: 50%;height: 100%;position: relative;}
        .sqfa-fr{width: 50%;height: 100%;position: relative;}
    </style>
    <div><!--申请方案-->
        <h4 class="text-align color-red size-15" style="margin-bottom: 50px;color: #a97b52!important;">申请方案</h4>
        <img src="img/web/bg_trade2.jpg" class="bg_trade2" width="100%" />
        <div class="sqfa">
            <div class="fl sqfa-fl">
                <div class="fr" style="margin-right: 10%;">
                    <div class="confime-befor">
                        <h3 style="font-size: 20px;padding: 4% 0;" class="color-white text-align sz-title">申请市值与股数</h3>
                        <h5 style="padding: 13% 0;padding-top: 0;" class="choose-szgs text-align color-white">选择市值规模</h5>
                        <ul class="sz-list">
                            <c:forEach items="${MARKET_SCALE}" var="d">
                                <li class="fl" onclick="choseMarketScale(${d.itemCode})">
                                    <span style="color: #a97b52!important;" class="size-12 color-red" data-marketscale="${d.itemCode}">${d.itemName}</span>
                                    <span class="size-9 color-hs" style="display: block;color: black!important;">市值规模</span>
                                </li>
                            </c:forEach>
                        </ul>
                        <input id="number" class="sz-input"  onchange="checkInt(this.value,1000,100);" type="number" oninput="if(value.length>4)value=value.slice(0,4)" style="width: 95%;border: none;height: 50px; line-height: 50px; padding-left: 5%;" placeholder="请输入您需要多少市值规模"  />
                        <h5 style="float: right; margin-top: -39px; position: relative; margin-right: 42px; color: white;">万元</h5>
                    </div>
                    <style>
                        .confime-after button{background: white; border: none;  height: 43px;width: 88px; border-radius: 7px; color: red;margin:0 20px;cursor: pointer;}
                    </style>
                    <%--询问界面--%>
                    <div class="confime-after text-align" style="display: none;">
                        <h3 style="font-size: 20px;padding: 4% 0;" class="color-white text-align sz-title">支付管理费</h3>
                        <p class="text-align color-white" style="height: 40px; line-height: 40px">
                            支付管理费<span class="size-15 guanlifei">1000</span>元，方案有效期<span class="size-15 cycle">一个月</span>
                        </p>
                        <span class="text-align color-white" style="height: 40px; line-height: 40px">
                            确认无误支付后，投顾方案将进入审核队列并冻结管理费
                        </span>
                        <span class="text-align color-white" style="height: 40px; line-height: 40px">
                            审核不通过则全额退回管理费，审核通过<span class="size-15">只扣除管理费</span>
                        </span>
                        <span class="text-align color-white" style="height: 40px; line-height: 40px">
                            投顾方案结束前<span class="size-15">不再产生额外费用</span>
                        </span>
                        <h5 style="margin: 20% 0;" class="color-white">
                            <c:if test="${userAccount != null}">
                            您的账户余额${userAccount.money}元<%--，本次支付还差2000元--%>
                                <br /><br /><a href="web/stock/deposit" style="text-decoration: underline;color: #fff;">马上充值</a>
                            </c:if>
                        </h5>
                        <button onclick="$('form').submit()">确定申请</button>
                        <button class="rel-black">返回修改</button>
                    </div>
                </div>
            </div>
            <div class="fl sqfa-fr">
                <div class="fl" style="margin-left: 10%;">
                    <h3 style="font-size: 20px;padding: 4% 0;" class="color-white text-align sz-title">确认方案规则</h3>
                    <%--确认前--%>
                    <div class="confime-befor">
                        <form action="web/stock/plan/create" method="post">
                        <ul class="gz-list" style="width: 100%;border: 1px solid white;">
                            <li style="width: 100%">
                                <h4>
                                    <span class="fl size-8" style="width: 80px;">股票标的：</span>
                                    <span class="fl size-8" style="width: 110px;">
												<span class="gp-name color-y"></span>
											</span>
                                </span>
                                </h4>
                                <h4>
                                    <span class="fr size-8" style="width: auto;">上一日收盘价：<span id="preClosePrice" class="old-price color-y"></span></span>
                                    </span>
                                </h4>
                            </li>
                            <li>
                                <h4><span class="fl size-8" style="width: 100px;">市值规模：</span>
                                    <span class="fl szgm-price color-y" style="margin-right: 11px;" id="shizhiguimo"></span>
                                    <span class="fr">股票现价：<span id="curPrice" class="old-price color-y"></span></span>
                                </h4>
                            </li>
                            <li>
                                <h4 style="width: 100px;" class="fl size-8">管理期限：</h4>
                                <select id="cycle" class="fl" style="margin-left: -15px;;margin-top: 12px; margin-right: 15px;border: none;background: #ebebeb; width: 100px; height: 25px; border-radius: 5px;" name="cycle" onchange="queryPlanManageFee()">
                                    <option value="">请选择管理期限</option>
                                    <c:forEach items="${STOCK_PLAN_CYCLE}" var="d">
                                        <option value="${d.itemCode}">${d.itemName}</option>
                                    </c:forEach>
                                </select>
                            </li>
                            <li>
                                <h4 style="width: 100px;" class="fl size-8">建议买入价格：</h4>
                                <select id="jy-type" class="fl jy-type" onchange="jy_type(this.options[this.options.selectedIndex].value)" style="margin-left: -15px;;margin-top: 12px; margin-right: 15px;border: none;background: #ebebeb; width: 75px; height: 25px; border-radius: 5px;" name="buyPriceType">
                                    <%--<option value="">请选择建议买入价格</option>--%>
                                    <c:forEach items="${STOCK_PRICE_TYPE}" var="d">
                                        <option value="${d.itemCode}">${d.itemName}</option>
                                    </c:forEach>
                                </select>
                                <input id="stock_price_type_1" class="fl qj" style="display: none;width: 41%;border: none;height: 25px;margin-top: 12px; border-radius: 5px; background: #ebebeb;padding-left: 10px;" type="number" placeholder="昨日收盘价正负10%区间" name="buyLimitPrice"/>
                            </li>
                            <li>
                                <h4>
                                    <span class="fl size-8" style="width: 100px;">管理费率：</span>
                                    <span class="gl_fv"></span>
                                </h4>
                            </li>
                            <li>
                                <h4>
                                    <span class="fl size-8" style="width: 105px;">需缴纳管理费用：</span>
                                    <span class="fl size-8"><span class="color-y guanlifei"></span>元(一次性收费)</span>
                                </h4>
                            </li>
                        </ul>
                        <p style="padding-top: 38px;" class="text-align color-white size-8">如不清楚规则，或有其他疑问，请联系客服：400-1155-875</p>
                        <h6 style="height: 50px;line-height: 50px;" class="text-align color-white size-8" class="text-align">
                            我以阅读并同意
                            <a class="color-y" href="web/investment/agreement" target="_blank">《投顾协议》</a>
                            <input name="investAgree" id="investAgree" type="checkbox" value="" />
                        </h6>
                        <div class="text-align">
                            <input style="width: 180px;border: none; height: 40px; line-height: 40px;border-radius: 5px;background: white; color: red; font-weight: bold!important;" type="button" class="my-sq" value="我要申请" />
                        </div>
                            <input type="hidden" name="buyMarketPrice" value="">
                            <%--<input type="hidden" name="serviceFee" value="">--%>
                            <input type="hidden" name="manageFee" value="">
                            <input type="hidden" name="managePrice" value="">
                            <input type="hidden" name="symbol" value="">
                            <input type="hidden" name="symbolName" value="">
                            <input type="hidden" name="curPrice" id="currentPrice" value="">
                        </form>
                    </div>

                    <%--确认后--%>
                    <div style="display: none;" class="confime-after">
                        <ul class="gz-list" style="width: 100%;border: 1px solid white;">
                            <li>
                                <h4>
                                    <span class="fl size-8" style="width: 100px;">股票标的：</span>
                                    <span class="fl size-8" style="width: 110px;">
												<span class="gp-name color-y"></span>
											</span>
                                    </span>
                                </h4>
                                <h4>
                                    <span class="fr size-8" style="width: auto;">上一日收盘价： <span id="confimepreClosePrice" class="old-price color-y"></span></span>
                                </h4>
                            </li>
                            <li>
                                <h4><span class="fl size-8" style="width: 100px;">市值规模：</span>
                                    <span class="fl szgm-price color-y" style="margin-right: 11px;">30万</span>
                                    <span class="fr">股票现价： <span id="confimecurPrice" class="old-price color-y"></span></span>
                                </h4>
                            </li>
                            <li>
                                <h4>
                                    <span style="width: 100px;" class="fl size-8">管理期限：</span>
                                    <span class="fl size-8 cycle"></span>
                                </h4>
                            </li>
                            <li>
                                <h4>
                                    <span style="width: 100px;" class="fl size-8">建议买入价格：</span>
                                    <span class="fl size-8 color-y priceType"></span>
                                    <span class="fl size-8 color-y buyLimitPrice" style="margin-left: 1em;"></span>
                                </h4>
                            </li>
                            <li>
                                <h4>
                                    <span class="fl size-8" style="width: 100px;">管理费率：</span>
                                    <span class="fl gl_fv"></span>
                                </h4>
                            </li>
                            <li>
                                <h4>
                                    <span class="fl size-8" style="width: 105px;">需缴纳管理费用：</span>
                                    <span class="fl size-8"><span class="color-y guanlifei"></span>元(一次性收费)</span>
                                </h4>
                            </li>
                            <li>
                                <h4>
                                    <span class="fl size-8" style="width: 105px;">方案申请日期：</span>
                                    <span class="fl size-8 color-white plan-create-date">2017-01-12</span>
                                </h4>
                            </li>
                            <%--<li>--%>
                                <%--<h4>--%>
                                    <%--<span class="fl size-8" style="width: 105px;">方案结束日期：</span>--%>
                                    <%--<span class="fl size-8 color-white">2017-02-12</span>--%>
                                <%--</h4>--%>
                            <%--</li>--%>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>
<script src="https://cdn.bootcss.com/chosen/1.8.2/chosen.jquery.js"></script>
<script type="text/javascript">
    var userStockList = ${userStockList};

    function stockIsCollection(symbol) {
        var result = false;
        $(userStockList).each(function (index, item) {
            if (symbol == item.symbol) {
                result = true;
            }
        });
        return result;
    }


    $(window).load(function() {
        var bg_pro1H = $(".bg_pro1_img").height();
        $(".content-div2").height(bg_pro1H);
        $(".content-div2").css("margin-top",-(bg_pro1H));

        var windwW = $(window).width();
        $(".left-div-list").width(windwW*0.28);
        $(".left-div-list-title").width(windwW*0.28);
        $(".left-div-list").height(bg_pro1H*0.61)

        $(".right-div-list").width(windwW*0.28);
        $(".right-div-list-title").width(windwW*0.28);
        $(".right-div-list").height(bg_pro1H*0.61)

        //申请方案
        var bg_trade2H = $(".bg_trade2").height();
        $(".sqfa").height(bg_trade2H);
        $(".sqfa .sqfa-fl").width(windwW*0.5);
        $(".sqfa .sqfa-fl div").width(windwW*0.3);
        $(".sqfa .sqfa-fr div").width(windwW*0.3);
        $(".sqfa").css("margin-top",-(bg_trade2H));
        $(".sz-title").css("margin-top",bg_trade2H*0.05);
        $(".choose-szgs").css("padding-bottom",bg_trade2H*0.03);
    });


//    $('.sz-list li').click(function(){
//        $(".sz-input").val("");
//        $('.sz-list li').css('background','#FBE2E3')
//        $(this).css('background','white');
//        var szgm = $(this).find("span:first-child").html();
//        $(".szgm-price").html(szgm);
//    });
//    $(".sz-input").click(function(){
//        $(".szgm-price").html("");
//        $('.sz-list li').css('background','#FBE2E3')
//    });
    $('.sz-list li').click(function(){
        $(".sz-input").val("");
        $('.sz-list li').css('background','rgba(255,255,255,.8)')
        $(this).css('background','rgba(255,255,255,1)');
        var szgm = $(this).find("span:first-child").html();
        $(".szgm-price").html(szgm);
    });

    $(".sz-input").click(function(){
        $(".szgm-price").html("");
        $('.sz-list li').css('background','rgba(255,255,255,.8)')
    });
    $(".sz-input").bind('input propertychange', function() {
        var inputVal = $(this).val()*10000;
        $(".szgm-price").html("");
        $(".szgm-price").html(inputVal);
        choseMarketScale(inputVal);
    });

    $('.my-sq').click(function(){
        if($(".gp-name").html() == ''){
            alert('请先选择股票');
            return;
        }
        if($(".szgm-price").html() == ''){
            alert("请先选择市值");
            return;
        }
        if($("#cycle").val() == ''){
            alert("请先选择管理期限");
            return;
        }
        if($("#jy-type").val() == ''){
            alert("请先选择建议买入价格");
            return;
        }
        if($("#jy-type").val() == 1){
            if($("#stock_price_type_1").val()==''){
                alert("请填写限价建议价格");
                return;
            }
        }
        if(!($("#investAgree").get(0).checked)){
            alert("请先同意投顾协议");
            return;
        }

        $('.confime-befor').hide();
        $('.confime-after').show();
        $('.jd li:lt(3)').removeClass("li_opacity")
        calculation();
    });

    $('.rel-black').click(function(){
        $('.confime-after').hide();
        $('.confime-befor').show();
        $('.jd li').addClass('li_opacity')
        $('.jd li:lt(1)').removeClass("li_opacity")
    });
    function trade_selected(obj){
        $('.trade_cart').show();
        $(obj).hide();
        $('.trade_selected').hide();
        $(obj).parent().find('.trade_selected').show();
        var gp_num = $(obj).parent().parent().find("td:nth-child(2)").html();
        var gp_name = $(obj).parent().parent().find("td:nth-child(3)").html();
        var gl_fv = $(obj).parent().parent().find("td:nth-child(4)").html();
        $(".gp-name").html(gp_num+" "+gp_name);
        $(".gl_fv").html(gl_fv);
    }

    function jy_type(val){
        if(val==1){
            $(".qj").show();
        }else{
            $(".qj").hide();
        }
    }

    $(function(){
        $(window).resize(function(){//实时获取宽高
            var bg_pro1H = $(".bg_pro1_img").height();
            $(".content-div2").height(bg_pro1H);
            $(".content-div2").css("margin-top",-(bg_pro1H));

            var windwW = $(window).width();
            $(".left-div-list").width(windwW*0.28);
            $(".left-div-list-title").width(windwW*0.28);
            $(".left-div-list").height(bg_pro1H*0.68)

            $(".right-div-list").width(windwW*0.28);
            $(".right-div-list-title").width(windwW*0.28);
            $(".right-div-list").height(bg_pro1H*0.68)

            //申请方案
            var bg_trade2H = $(".bg_trade2").height();
            $(".sqfa").height(bg_trade2H);
            $(".sqfa").css("margin-top",-(bg_trade2H));
            $(".sqfa .sqfa-fl").width(windwW*0.5)
            $(".sqfa .sqfa-fr").width(windwW*0.5)
            $(".sqfa .sqfa-fl div").width(windwW*0.3);
            $(".sqfa .sqfa-fr div").width(windwW*0.3);
            $(".sz-title").css("margin-top",bg_trade2H*0.03);
            $(".choose-szgs").css("padding-bottom",bg_trade2H*0.03);
        });
    });

    var CUSTOME_RATE = ${MANAGE_FEE[0].itemCode};

    function queryStock() {
        var Symbol = $("#Symbol").length == 0 ? "" : $("#Symbol").val();
        var Min_option_price = $("#Min_option_price").val().length == 0 ? "" : $("#Min_option_price").val();
        var Max_option_price = $("#Max_option_price").val().length == 0 ? "" : $("#Max_option_price").val();
        $.ajax({
            url: 'web/stock/list/json',
            type: 'GET', //GET
            async: true,    //或false,是否异步
            data: {"Symbols": Symbol, "Cycle": "1m", "Min_option_price": Min_option_price, "Max_option_price": Max_option_price},
            timeout: 10000,    //超时时间
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (data, textStatus, jqXHR) {
                var jsonArray = $.parseJSON(JSON.stringify(data));
                //加载股票标的
                $("#stockList").empty();
                $.each(jsonArray, function (i, item) {
//                    var beforeOfferPrice = item['Offer_price'] * 1;
//                    var afterOfferPrice = (beforeOfferPrice * (CUSTOME_RATE + 1)).toFixed(3);
                    var symbol = item['code'];
                    var symbolName = item['name'];
                    var manageFee = item.manageFee;

                    var trDom = document.createElement("tr");
                    var trInnerHTML =   "<td>" + i + 1 + "</td>" +
                                        "<td>" + symbol + "</td>" +
                                        "<td>" + symbolName + "</td>" +
                                        "<td>" + manageFee + "%</td>" +
                                        "<td>" +
                                        "    <img src='img/web/icon_trade_cart.png' class='trade_cart' width='28%' onclick='buyThis(\"" + symbol + "\",\"" + symbolName + "\",\"" + manageFee + "\")'>" +
                                        "    <img src='img/web/icon_trade_add.png' class='add' width='28%' onclick='dealAdd(this, \""+symbol+"\")'>" +
//                                        "    <img src='img/icon_trade_calculator.png' class='trade_calculator' width='28%' />" +
                                        "</td>";
                    trDom.innerHTML = trInnerHTML;
                    $("#stockList").append(trDom);
                    if(stockIsCollection(symbol)) {
//                        dealAdd($(trDom).find(".add"));
                    }
                });
            },
            error: function (xhr, textStatus) {
                console.log('错误')
                console.log(xhr)
                console.log(textStatus)
            },
            complete: function () {
                console.log('结束')
            }
        })
    }

    //自选股
    function queryOptionalStock() {
        var Symbol = $("#Symbol").length == 0 ? "" : $("#Symbol").val();
        var Min_option_price = "";
        var Max_option_price = "";
        $.ajax({
            url: 'web/stock/optional/labels/json',
            type: 'GET', //GET
            async: true,    //或false,是否异步
            data: {"Symbols": Symbol, "Cycle": "1m", "Min_option_price": Min_option_price, "Max_option_price": Max_option_price,"symbol": Symbol},
            timeout: 10000,    //超时时间
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (data, textStatus, jqXHR) {
                var jsonArray = $.parseJSON(JSON.stringify(data));
                //加载股票标的
                $("#userStockList").empty();
                $.each(jsonArray, function (i, item) {
//                    var beforeOfferPrice = item['Offer_price'] * 1;
//                    var afterOfferPrice = (beforeOfferPrice * (CUSTOME_RATE + 1)).toFixed(3);
                    var symbol = item['code'].toString();
                    var symbolName = item['name'];
                    var manageFee = item['manageFee'];
                    var xuhao = i+1;
                    var trDom = document.createElement("tr");
                    var trInnerHTML =   "<td>" + i + 1 + "</td>" +
                        "<td>" + symbol + "</td>" +
                        "<td>" + symbolName + "</td>" +
                        "<td>" + manageFee + "%</td>" +
                        "<td>" +
                        "    <img src='img/web/icon_trade_cart.png' class='trade_cart' width='28%' onclick='buyThis(\"" + symbol + "\",\"" + symbolName + "\",\"" + manageFee + "\")'>" +
                        '			<img src="img/web/icon_trade_subtract.png" onclick="userStockDelete(this)" width="28%" />'
                        //                                        "    <img src='img/icon_trade_calculator.png' class='trade_calculator' width='28%' />" +
                        "</td>";
                    trDom.innerHTML = trInnerHTML;
                    if(stockIsCollection(symbol)) {
                        $("#userStockList").append(trDom);
//                        dealAdd($(trDom).find(".add"));
                    }
                });
            },
            error: function (xhr, textStatus) {
                console.log('错误')
                console.log(xhr)
                console.log(textStatus)
            },
            complete: function () {
                console.log('结束')
            }
        })
    }

    function queryPlanManageFee(){
        var Symbol = $("[name='symbol']").val();
        var $cycle = $("[name='cycle']");
        if(!Symbol){
            alert('请先选择一只股票');
            $cycle[0].selectedIndex = 0;
            return;
        }

        $.ajax({
            url: 'web/stock/list/json',
            type: 'GET', //GET
            async: true,    //或false,是否异步
            data: {"Symbols": Symbol, "Cycle": $cycle.val(), "Min_option_price": null, "Max_option_price": null},
            timeout: 10000,    //超时时间
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (data, textStatus, jqXHR) {
                //应当只返回一条数据
                var stockData = data[0];
                //重新显示手续费并计算
                var manageFee = stockData.manageFee;
                $("input[name='manageFee']").val(manageFee);
                calculation();
            },
            error: function (xhr, textStatus) {
                console.log('错误')
                console.log(xhr)
                console.log(textStatus)
            },
            complete: function () {
                console.log('结束')
            }
        })
    }

    function dealAdd(obj, symbol){
        var td2 = $(obj).parent().parent().find("td:nth-child(2)").html();
        var td3 = $(obj).parent().parent().find("td:nth-child(3)").html();
        var td4 = $(obj).parent().parent().find("td:nth-child(4)").html();
        var td5 = $(obj).parent().find("img:eq(0)")[0].outerHTML;
        var tablist = $(".right-div-list table").html();
        var i = 0
        $(".right-div-list table tr").each(function(){
            i++
        });
        tablist +='<tr style="height: 50px;border-bottom: 1px dashed white;">'
            +'		<td>'+(i)+'</td>'
            +'		<td>'+td2+'</td>'
            +'		<td>'+td3+'</td>'
            +'		<td>'+td4+'</td>'
            +'		<td>' + td5
            //                +'			<img src="img/web/icon_trade_cart.png" width="28%" />'
            +'			<img src="img/web/icon_trade_subtract.png" onclick="userStockDelete(this)" width="28%" />'
            //+'			<img src="img/icon_trade_calculator.png" width="28%" />'
            +'		</td>'
            +'</tr>'
        $(".right-div-list table").html(tablist);

        if(!stockIsCollection(symbol) && symbol) {
            $.ajax({
                url: 'web/user/stock/save',
                type: 'post', //GET
                async: true,    //或false,是否异步
                data: {"symbol": symbol},
                timeout: 10000,    //超时时间
                cache: false,
                dataType: 'text',    //返回的数据格式：json/xml/html/script/jsonp/text
                success: function (data, textStatus, jqXHR) {
                    if(data == 'success'){}
                },
                error: function (xhr, textStatus) {
                    console.log('错误')
                    console.log(xhr)
                    console.log(textStatus)
                },
                complete: function () {
                    console.log('结束')
                }
            })

        }
    }

    function userStockDelete(obj){
        var xh = 0;//当前要移除的序号
        var next_xh = 0;//移除行后面的序号
        var td2 = $(obj).parent().parent().find("td:nth-child(2)").html();
        $(".right-div-list table tr").each(function(){
            var td2_each = $(this).find("td:nth-child(2)").html();
            next_xh = $(this).find("td:nth-child(1)").html();
            if(td2_each==td2){
                xh = $(obj).parent().parent().find("td:nth-child(1)").html();
                $(obj).parent().parent().remove();
            }
            if(next_xh >xh && xh!=0){
                $(this).find("td:nth-child(1)").html($(this).find("td:nth-child(1)").html()-1);
            }
        });

        var symbol = $(obj).parent().parent().find("td:eq(1)").html();
        $.ajax({
            url: 'web/user/stock/delete',
            type: 'post', //GET
            async: true,    //或false,是否异步
            data: {"symbol": symbol},
            timeout: 10000,    //超时时间
            cache: false,
            dataType: 'text',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (data, textStatus, jqXHR) {
                if(data == 'success'){}
            },
            error: function (xhr, textStatus) {
                console.log('错误')
                console.log(xhr)
                console.log(textStatus)
            },
            complete: function () {
                console.log('结束')
            }
        })

    }

    function choseMarketScale(marketScale) {
        $("[name='buyMarketPrice']").val(marketScale);
        calculation();
    }

    function buyThis(symbol, name, manageFee) {
        window.location.hash = "#sqfa";
        $(".gp-name").html(symbol + " " + name);
        $("[name='symbol']").val(symbol);
        $("[name='symbolName']").val(name);
//        $("[name='serviceFee']").val(beforeOfferPrice);
        $("[name='manageFee']").val(manageFee);
        calculation();
        queryStockPreClosePrice(symbol);
    }


    function calculation(){
        var buyMarketPrice = $("[name='buyMarketPrice']").val();
        var afterOfferPrice = $("[name='manageFee']").val();
        var guanlifei = (buyMarketPrice / 100 * afterOfferPrice).toFixed(2);
        $(".gl_fv").html(afterOfferPrice + "%");
        $(".guanlifei").html(guanlifei);
        $("[name='managePrice']").val(guanlifei);
        $(".buyLimitPrice").html($("[name='buyLimitPrice']").val());
        $(".cycle").html($("[name='cycle'] option:selected").html());
        $(".plan-create-date").html(new Date().toLocaleDateString());
    }

    $("[name='cycle']").on("change", function (event) {
        var cycleName = $(event.currentTarget).find(":selected").html();
        $(".cycle").html(cycleName);
    });
    $("[name='priceType']").on("change", function (event) {
        var priceType = $(event.currentTarget).find(":selected").html();
        $(".priceType").html(priceType);
    });
    $("[name='limitPrice']").bind("input propertychange", function (event) {
        var limitPrice = $(event.currentTarget).val();
        $(".limitPrice").html(limitPrice);
    });

    //input number不能输入负数
    $("input[type='number']").attr("min", 1).on("keydown", function(e){
        return e.keyCode != 189;
    });

    $(document).ready(function () {
        queryStock();
        queryOptionalStock();
        //初始化【建议买入价格】为【市价】
        $("#jy-type").val("0");
    });
</script>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.js"></script>
<script>
    $( function() {
        var data = ${dataList};
        var availableTags = [];
        for(var i=0;i<data.length;i++){
            var autocompleteData = {"value":data[i].symbol,"label":data[i].symbol + "/" + data[i].symbolName};
            availableTags[i]=autocompleteData;
        }
//        $( "#Symbol" ).autocomplete({
//            source: availableTags
//        });
        $("#Symbol").autocomplete({
//            minLength: 0,
            source: availableTags,
//            focus: function (event, ui) {
//                $("#Symbol").val(ui.item.value);
//                return false;
//            },
            select: function (event, ui) {
                $("#Symbol").val(ui.item.value);
                return false;
            }
        }).autocomplete("instance")._renderItem = function (ul, item) {
            if(ul[0].childElementCount>15){
                return $("").appendTo(ul);
            }
            return $("<li>")
                .append("<div>" + item.label + "</div>")
                .appendTo(ul);
        };

    } );

    function queryStockPreClosePrice(symbol) {
        var Symbol = symbol;
        if("60"==Symbol.substring(0,2)){
            Symbol = "sh"+Symbol;
        }else {
            Symbol = "sz"+Symbol;
        }
        //查询最后一次价格
        $.ajax({
            url: 'web/query/sina/data',
            type: 'GET', //GET
            async: true,    //或false,是否异步
            data: {"symbol": Symbol},
            timeout: 10000,    //超时时间
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (data, textStatus, jqXHR) {
                //应当只返回一条数据
                var stockData = data[2];
                //重新显示手续费并计算
//                var manageFee = stockData.manageFee;
//                $("#manageFee").html(manageFee);
                $("#preClosePrice").html(stockData);
                //增加股票现价
                $("#curPrice").html(data[3]);
                //写入确认后的界面
                $("#confimepreClosePrice").html(stockData);
                $("#confimecurPrice").html(data[3]);
                $("#currentPrice").val(data[3]);

//                preClosePriceIsDone = true;
//                kaishijisuan();
            },
            error: function (xhr, textStatus) {
                console.log('错误')
                console.log(xhr)
                console.log(textStatus)
            },
            complete: function () {
                console.log('结束')
            }
        })
    }

    function checkInt(n,max,min) {
        var regex = /^\d+$/;
        if (regex.test(n)) {
            if (n < min && n >= 0) {
                $("#number").val("");
                $("#shizhiguimo").html("");
                alert("请输入大于" + min + "的整数");
                return false;
            } else if (n > max && n > 0) {
                $("#number").val("");
                $("#shizhiguimo").html("");
                alert("请输入小于" + max + "的整数");
                return false;
            } else {
                return true;
            }
        } else {
            alert("请输入整数");
            $("#number").val("");
            $("#shizhiguimo").html("");
            return false;
        }
    }
</script>
</body>
</html>

