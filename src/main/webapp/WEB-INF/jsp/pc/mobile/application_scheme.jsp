<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ry" uri="http://www.ruanyun.com/taglib/ry" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<ry:binding type="1" bingdingName="MANAGE_FEE" parentCode="MANAGE_FEE"></ry:binding>
<ry:binding type="1" bingdingName="MARKET_SCALE" parentCode="MARKET_SCALE"></ry:binding>
<ry:binding type="1" bingdingName="STOCK_PLAN_CYCLE" parentCode="STOCK_PLAN_CYCLE"></ry:binding>
<ry:binding type="1" bingdingName="STOCK_PRICE_TYPE" parentCode="STOCK_PRICE_TYPE"></ry:binding>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<base href="<%=basePath%>"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="css/mobile/mystyle.css" />
    <link rel="stylesheet" href="http://cdn.bootcss.com/jquery-mobile/1.4.2/jquery.mobile.css" />
    <script type="text/javascript" src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js" ></script>
    <script type="text/javascript" src="http://cdn.bootcss.com/jquery-mobile/1.4.2/jquery.mobile.min.js" ></script>
    <title></title>
</head>
<body>
<style>
    select {
        /*Chrome和Firefox里面的边框是不一样的，所以复写了一下*/
        border: solid 0px #000;

        /*很关键：将默认的select选择框样式清除*/
        appearance:none;
        -moz-appearance:none;
        -webkit-appearance:none;

        /*在选择框的最右侧中间显示小箭头图片*/
        background: url("http://ourjs.github.io/static/2015/arrow.png") no-repeat scroll right center transparent;
        color: #FFFFFF;
        /*为下拉小箭头留出一点位置，避免被文字覆盖*/
        padding-right: 14px;
        width: 100%;
        outline: none;
        border: none;
        background: none;
        direction: rtl;
    }


    /*清除ie的默认选择框样式清除，隐藏下拉箭头*/
    select::-ms-expand { display: none; }
     #application-scheme .agreement{margin: .5em 0;}
    #application-scheme .agreement img{width: 15px; float: left; margin-top: 1px; margin-left: 1em; margin-right: .5em;}
</style>
<div data-role="page" id="application-scheme">
    <div data-role = "header" style="border-width: 0; background: #28292E;">
        <a href="mobile/option/labels" data-ajax="false" class="ui-link"><img src="img/mobile/icon_nav_back.png"/></a>
        <h4 class="color-w">申请方案</h4>
    </div>
    <div data-role = "content">
        <ul data-role = "listview">
            <h4 class="color-w size-9 fl" style="margin: .6em;margin-left: 1em;">股票信息</h4>
            <li class="clear" data-icon = "false">
                <h4>
                    <span class="fl color-hs">股票标的</span>
                    <span class="color-red fr">${symbol}${symbolName}</span>
                    <%--<input type="hidden" value="${symbol}" id="symbol"/>--%>
                </h4>
            </li>
            <li class="clear" data-icon = "false">
                <h4>
                    <span class="fl color-hs">管理费率</span>
                    <span id="manageFee" class="color-w fr">${manageFee}%</span>
                </h4>
            </li>
            <h4 class="color-w size-9 fl" style="margin: .6em;margin-left: 1em;">选择信息</h4>
            <li class="clear" data-icon = "false" onclick="market_capitalization()">
                <h4>
                    <span class="fl color-hs">市值规模</span>
                    <span class="color-w fr">
								<span id="market_value" class="market_value">请选择</span>

								<img style="margin-left: .3em;" class="fr" width="20px" src="img/mobile/icon_arrow_right.png" />
							</span>
                </h4>
            </li>
            <li class="clear" data-icon = "false" onclick="management_deadline()">
                <h4>
                    <span class="fl color-hs">管理期限</span>
                    <span class="color-w fr">
								<span id="manageLimit">请选择</span>
								<img style="margin-left: .3em;" class="fr" width="20px" src="img/mobile/icon_arrow_right.png" />
							</span>
                </h4>
            </li>
            <li class="clear" data-icon = "false">
                <h4>
                    <span class="fl color-hs">建议买入价格</span>
                    <span class="color-w fr">
                        <select dir="rtl" style="background:#424242" id="type" name="bankId" data-role="none">
                            <option>选择价格类型</option>
                            <c:forEach items="${STOCK_PRICE_TYPE}" var="d">
                                <option class="color-w" style="color: #FFFFFF;float: right" value="${d.itemCode}">${d.itemName}</option>
                            </c:forEach>
                        </select>
								<%--<span>市价交易</span>--%>
								<%--<img style="margin-left: .3em;" class="fr" width="20px" src="img/mobile/icon_arrow_right.png" />--%>
					</span>
                </h4>
            </li>
            <li id="limitPriceInput" style="display: none;" class="clear" data-icon = "false">
                <input id="stock_price_type_1"   type="number" placeholder="昨日收盘价正负10%区间" name="buyLimitPrice"/>
            </li>
            <h4 class="color-w size-9 fl" style="margin: .6em;margin-left: 1em;">一次性收取管理费</h4>
            <li class="clear" data-icon = "false">
                <h4>
                    <span class="fl color-hs">需缴纳管理费</span>
                    <span class="color-w fr"><span id="willPayManageFee" class="color-red">0</span>元</span>
                </h4>
            </li>
        </ul>
        <h4 class="agreement color-w size-9">
            <img class="btn_select" src="img/mobile/btn_select.png" onclick="btn_select()" />
            <img style="display: none;" onclick="btn_selected()" class="btn_selected" src="img/mobile/btn_selected.png" />
            我已阅读并同意<a data-ajax="false" href="mobile/investment/agreement" class="color-red">《投顾协议》</a>
        </h4>
        <a id="apply" class="apply" data-ajax = "false" data-role = "button">我的申请</a>
        <input id="agreement_state" type="hidden" value="0" class="agreement_state" />
        <div style="height: 200px"></div>
        <form data-ajax="false" style="display: none;" id="form" action="mobile/validation/scheme" method="get">
            <input name="symbol" type="text" value="${symbol}" id="symbol"/>
            <input id="symbolName" name="symbolName" type="text" value="${symbolName}">
            <input id="lastManageFee" name="manageFee" type="text" value=""/>
            <input id="hidden_market_value" name="hiddenMarketValue" type="text" value="" />
            <input name="stockPlanCycleValue" id="stockPlanCycleValue" onchange="queryPlanManageFee()" type="text"  value=""/>
            <input name="stockPriceType" id="stockPriceType"   type="text"  value=""/>
            <input name="buyPrice" id="buyPrice"  type="text"  value=""/>
            <input name="payManageFee" id="payManageFee"  type="text"  value=""/>
        </form>

        <!-- 弹窗区  -->
        <!-- 市值规模   -->
        <div class="market_capitalization">
            <h4 class="title">
						<span class="color-w size-9 title-first-span">
							<span class="fl cancel">取消</span>
							 	市值规模
							<span class="fr color-red confirm">确定</span>
						</span>
            </h4>
            <div class="choose_market_capitalization">
                <c:forEach items="${MARKET_SCALE}" var="item">
                    <c:set var="marketScaleValue" value="${fn:length(item.itemName)-1}"/>
                    <a onclick="chooseMarketCapitalization(this);" class="ui-btn fl"><span class="money">${fn:substring(item.itemName,0,marketScaleValue)}</span>万元</a>
                </c:forEach>
                <%--<a onclick="chooseMarketCapitalization(this);" class="ui-btn fl"><span class="money">5</span>万元</a>--%>
                <%--<a onclick="chooseMarketCapitalization(this);" class="ui-btn fl"><span class="money">10</span>万元</a>--%>
                <%--<a onclick="chooseMarketCapitalization(this);" class="ui-btn fl"><span class="money">20</span>万元</a>--%>
                <%--<a onclick="chooseMarketCapitalization(this);" class="ui-btn fl"><span class="money">30</span>万元</a>--%>
                <%--<a onclick="chooseMarketCapitalization(this);" class="ui-btn fl"><span class="money">40</span>万元</a>--%>
                <%--<a onclick="chooseMarketCapitalization(this);" class="ui-btn fl"><span class="money">60</span>万元</a>--%>
                <%--<a onclick="chooseMarketCapitalization(this);" class="ui-btn fl"><span class="money">80</span>万元</a>--%>
                <%--<a onclick="chooseMarketCapitalization(this);" class="ui-btn fl"><span class="money">100</span>万元</a>--%>
            </div>
            <div style="margin: 0 2em;margin-bottom: 1em;position: relative;">
                <input id="customMarketCapitalization" onchange="checkInt(this.value,1000,100);" onblur="putMarketCapitalization(this);" class="zdy size-9" data-role='none' type="number" placeholder="请输入其他市值规模" oninput="if(value.length>4)value=value.slice(0,4)"/>
                <h6 class="dw">万元</h6>
            </div>
        </div>

        <!-- 管理期限   -->
        <div  class="management_deadline">
            <h4 class="title">
						<span class="color-w size-9 title-first-span">
							<span class="fl cancel">取消</span>
							 	管理期限
							<span class="fr color-red confirm">确定</span>
						</span>
            </h4>
            <div class="choose_management_deadline">
                <c:forEach items="${STOCK_PLAN_CYCLE}" var="d">
                    <a id="stockPlanCycleName" onclick="chooseManagementDeadline(this)" class="ui-btn fl"><input class="stockPlanCycleCode" type="hidden" id="stock_plan_cycle" value="${d.itemCode}"/>${d.itemName}</a>
                    <%--<option value="${d.itemCode}">${d.itemName}</option>--%>
                </c:forEach>
            </div>
        </div>

        <!-- 建议买入价格  -->
        <div style="display: ;" class="buying_price">
            <h4 class="title">
						<span class="color-w size-9 title-first-span">
							<span class="fl cancel">取消</span>
							 	买入价格
							<span class="fr color-red confirm">确定</span>
						</span>
            </h4>
            <div class="choose_buying_price">
                <c:forEach items="${STOCK_PRICE_TYPE}" var="d">
                    <c:if test="${d.itemCode==0}">
                        <a onclick="addStockPriceType(${d.itemCode});" class="ui-btn fl">${d.itemName}</a>
                    </c:if>
                    <c:if test="${d.itemCode==1}">
                        <a onclick="addInput(${d.itemCode});" class="ui-btn fr">${d.itemName}</a>
                    </c:if>
                    <%--<option value="${d.itemCode}">${d.itemName}</option>--%>
                </c:forEach>
                <%--<a class="ui-btn fl">市价交易</a>--%>
                <%--<a class="ui-btn fr">限价交易</a>--%>
            </div>
        </div>
    </div>
</div>
<script>

    /*********************
     * 市场规模
     **********************/
    $('.market_capitalization .cancel').click(function(){//取消
        $(this).parent().parent().parent().slideUp();
    });

    $('.market_capitalization .confirm').click(function(){//确定
        $(this).parent().parent().parent().slideUp();
    });

    function market_capitalization(){//市值规模
        $('.management_deadline').slideUp();
        $('.buying_price').slideUp();
        $('.market_capitalization').slideDown();
    }
    /*********************
     * 管理周期
     **********************/
    $('.management_deadline .cancel').click(function(){//取消
        $(this).parent().parent().parent().slideUp();
    });

    $('.management_deadline .confirm').click(function(){//确定
        $(this).parent().parent().parent().slideUp();
    });
    function management_deadline(){//管理周期
        $('.market_capitalization').slideUp();
        $('.buying_price').slideUp();
        $('.management_deadline').slideDown();
    }

    /*********************
     * 建议买入价
     **********************/
    $('.buying_price .cancel').click(function(){//取消
        $(this).parent().parent().parent().slideUp();
    });

    $('.buying_price .confirm').click(function(){//确定
        $(this).parent().parent().parent().slideUp();
    });
    function buying_price(){//建议买入价
        $('.buying_price').slideDown();
        $('.market_capitalization').slideUp();
        $('.management_deadline').slideUp();
    }

    function btn_select(){
        $('.agreement_state').val('1');//不同意协议为 0 同意协议 为1      我要申请时通过该状态判断协议是否勾选
        $('.btn_select').hide();
        $('.btn_selected').show();
    }
    function btn_selected(){
        $('.agreement_state').val('0');
        $('.btn_select').show();
        $('.btn_selected').hide();
    }

    /**
     * 选择市场规模
     * @param obj
     */
    function chooseMarketCapitalization(obj) {
        $("#market_value").html("");
        $("#market_value").html($(obj).find(".money").html()+"0000元");
        $("#hidden_market_value").val($(obj).find(".money").html()+"0000");
        queryPlanManageFee();
    }

    /**
     * 选择管理期限
     * @param obj
     */
    function chooseManagementDeadline(obj) {
        $("#manageLimit").html("");
        $("#manageLimit").html($(obj).html());
        $("#stockPlanCycleValue").val($(obj).find(".stockPlanCycleCode").val());
        queryPlanManageFee();
    }

    function queryPlanManageFee(){
        var Symbol = $("#symbol").val();
        var cycle = $("#stockPlanCycleValue").val();
        if(cycle == ''){
           return;
        }
        if(!Symbol){
            alert('请先选择一只股票');
            return;
        }

        $.ajax({
            url: 'web/stock/list/json',
            type: 'GET', //GET
            async: true,    //或false,是否异步
            data: {"Symbols": Symbol, "Cycle": cycle, "Min_option_price": null, "Max_option_price": null},
            timeout: 10000,    //超时时间
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (data, textStatus, jqXHR) {
                //应当只返回一条数据
                var stockData = data[0];
                //重新显示手续费并计算
                var manageFee = stockData.manageFee;
                $("#manageFee").html("");
                $("#manageFee").html(manageFee);
                $("#lastManageFee").val(manageFee);
                var buyMarketPrice = $("#hidden_market_value").val();
                var guanlifei = (buyMarketPrice / 100 * manageFee).toFixed(2);
                $("#willPayManageFee").html("");
                $("#willPayManageFee").html(guanlifei);
                $("#payManageFee").val(guanlifei);
            },
            error: function (xhr, textStatus) {
                console.log('错误');
                console.log(xhr);
                console.log(textStatus);
            },
            complete: function () {
                console.log('结束')
            }
        })
    }
    
    function addInput(code) {
        $("#limitPriceInput").css("display","");
        $("#stockPriceType").val(code);
    }

    function putMarketCapitalization(obj) {
        if($(obj).val()==''){
            return false;
        }
        $("#market_value").html("");
//        $("#market_value").html($(obj).val()+"元");
//        $("#hidden_market_value").val($(obj).val());
        $("#market_value").html($(obj).val()+"0000元");
        $("#hidden_market_value").val($(obj).val()+"0000");
    }

    $("#apply").click(function () {
        var isOk = true;
        if($("#symbol").val().length==0){
            isOk = false;
            alert("选择股票信息");
        }
        if($("#symbolName").val().length==0){
            isOk = false;
            alert("选择股票信息");
        }
        if($("#lastManageFee").val().length==0){
            isOk = false;
        }
        if($("#hidden_market_value").val().length==0){
            isOk = false;
        }
        if($("#stockPlanCycleValue").val().length==0){
            isOk = false;
        }
        if($("#stockPriceType").val().length==0){
            isOk = false;
        }
        if($("#stockPriceType").val()==1&&$("#buyPrice").val().length==0){
            isOk = false;
        }
        if($("#payManageFee").val().length==0){
            isOk = false;
        }
        if($("#agreement_state").val()!=1){
            isOk = false;
            alert("请选择投顾协议！");
        }
        if(isOk){
            $("#form").submit();
        }
    });
    
    $("#stock_price_type_1").change(function () {
        $("#buyPrice").val($("#stock_price_type_1").val());
    });
    
    function addStockPriceType(code) {
        $("#limitPriceInput").css("display","none");
        $("#stockPriceType").val(code);
    }
    
    $("#type").change(function () {
        if($("#type").val()!=1){
            $("#limitPriceInput").css("display","none");
            $("#stockPriceType").val($("#type").val());
        }else {
            $("#limitPriceInput").css("display","");
            $("#stockPriceType").val($("#type").val());
        }
    });

    function checkInt(n,max,min) {
        var regex = /^\d+$/;
        if (regex.test(n)) {
            if (n < min && n >= 0) {
                $("#hidden_market_value").val("");
                $("#customMarketCapitalization").val("");
                $("#market_value").html("请选择");
                alert("请输入大于" + min + "的整数");
                return false;
            } else if (n > max && n > 0) {
                $("#hidden_market_value").val("");
                $("#customMarketCapitalization").val("");
                $("#market_value").html("请选择");
                alert("请输入小于" + max + "的整数");
                return false;
            } else {
                return true;
            }
        } else {
            alert("请输入整数");
            $("#hidden_market_value").val("");
            $("#customMarketCapitalization").val("");
            $("#market_value").html("请选择");
            return false;
        }
    }
</script>
</body>
</html>

