<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>自选标的</title>
</head>

<%@include file="base.jsp" %>
<style>
    @media screen and (max-width: 1280px) {
        .content-div-two .content-div-two-div {
            margin-left: 209.92px !important;
            width: 608.768px !important;
            height: 288px !important;
        }

        .content-div-two-div-right {
            width: 30% !important;
        }
    }

    @media screen and (max-width: 1530px) {
        .content-div-two-div-left div {
            margin-bottom: 10% !important;
        }
    }

    @media screen and (max-width: 1400px) {
        .content-div-two-div-left {
            width: auto !important;
        }

        .content-div-two-div-right {
            width: 30% !important;
        }
    }

    .none {
        background-color: white;
    }

    .index-a a {
        color: #000000;
    }

    .ui-menu .ui-menu-item-wrapper {
        position: relative;
        padding: 1px 1em 3px .4em;
        background: white;
        width: 200px;
    }

    #all_subject_matter .ui-content .title span:first-child {
        width: 25%;
    }

    #all_subject_matter .ui-content .title span {
        width: 25%;
        text-align: center;
        color: white;
    }

    #all_subject_matter .ui-content .title-content span:first-child {
        width: 25%;
    }

    #all_subject_matter .ui-content .title-content span {
        width: 25%;
        text-align: center;
        color: white;
        height: 3em;
        line-height: 3em;
    }

    #all_subject_matter .ui-content .title-content span {
        width: 25%;
        text-align: center;
        color: white;
        height: 3em;
        line-height: 3em;
    }
    #all_subject_matter ul{
          overflow: scroll; 
    }
</style>
<body>
<div data-role="page" id="all_subject_matter">
    <div data-role="content">
        <!-- 头部搜索 -->
        <div class="header">
            <input id="Symbol" class="fl size-9" data-role="none" type="text" placeholder="输入股票代码或名称"/>
            <a id="queryStock" class="ui-btn color-w fr" onclick="findUserStock(this)" style="line-height:2.36em;height:2.36em;">查询</a>
        </div>
        <h4 class="title size-9">
            <span class="fl">代码名称</span>
            <span class="fl">最新价</span>
            <span class="fl">涨跌幅</span>
            <span class="fl">操作</span>
            <%--<span class="fl">最新价</span>--%>
            <%--<span class="fl">涨跌幅</span>--%>
        </h4>
        <!-- 列表  -->
        <style>
            #stockList li {
                border-bottom: 1px solid #424242 !important;
            }

            #stockList a {
                background: none;
                border: none;
                margin-bottom: 1px;
                padding: 0;
            }
           #all_subject_matter{
          overflow: scroll; padding-bottom:20px;
    }
            #stockList a {
                background: none;
                border: none;
                margin-bottom: 1px;
                padding: 0;
            }

            #stockList a .name {
                line-height: normal;
                width: 100% !important;
                margin-top: 3px;
            }

            #stockList a .number {
                line-height: normal;
                width: 100% !important;
            }

            #stockList .remove {
                border: 1px solid;
                border-radius: 5px;
                padding: 3px 10px;
                position: absolute;
                color: #FF302F;
                font-size: 0.8em;
                right: 23px;
                top: 8px;
            }
        </style>
        
        <ul id="stockList" data-role="listview">

        </ul>
        <h4  id="person" style="position: fixed;bottom: 60px;" class="text-align">
            <span onclick="add_tx()" style="background: #c33f00; padding: 10px 15px; border-radius: 3px;font-size:0.9em;"
                  class="color-w">+ 添加自选股</span>
        </h4>
        <div class="add_zxg" style="display: none;">
            <div class="add_zxg_div" style="border-radius:3px;">
                <h4 class="text-align size-11 h4_title" style="background:#c33f00;height:40px;line-height:40px;margin-bottom:1.5em;border-top-left-radius: 3px;border-top-right-radius: 3px;">
                    <span class="color-w" style="font-size:0.9em;">添加自选股</span>
                    <span class="close color-w" style="font-size:0.9em;">X</span>
                </h4>
                <input type="text" data-role="none" oninput="getList()" id="user_stock_code" placeholder="输入股票代码" style="BACKGROUND: #ddd; COLOR: #ff302f;border:1 dashed #000000;border-radius:3px;font-size:0.9em;" />
                <ul data-role="listview" class="add_list">

                </ul>
            </div>
        </div>
        <!-- 底部 -->
        <div class="footer">
            <div class="fl">
                <a href="mobile/option/labels" data-ajax="false">
                    <img src="img/mobile/tab_price_red.png"/>
                    <h4 class="color-red size-9">自选股</h4>
                </a>
            </div>
            <div class="fl">
                <a href="mobile/stock/plan/list?orderStatus=1" data-ajax="false">
                    <img src="img/mobile/tab_select_white.png"/>
                    <h4 class="color-w size-9">持仓</h4>
                </a>
            </div>
            <div class="fl">
                <a href="mobile/stock/userinfo" data-ajax="false">
                    <img src="img/mobile/tab_user_white.png"/>
                    <h4 class="color-w size-9">我的</h4>
                </a>
            </div>
        </div>
    </div>
</div>
<%@include file="k_details.jsp" %>
<script src="js/web/stock_data.js"></script>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.js"></script>
<script>
    var intervalMarket;

    function remove(code, obj) {
        $.ajax({
            url: "mobile/deleteUserStock",
            dataType: "json",
            type: "post",
            data: {"symbol": code},
            success: function (data) {
                layer.msg(data.msg, {
                    time: 1000,
                }, function () {
                    //删除成功后，重新加载数据
                    $(obj).parent().remove();
                })
            }
        });

    }

    function add_tx() {

        $('.add_zxg').fadeIn();

    }

    $('.close').click(function () {
        $('.add_zxg').fadeOut();

        $("#all_subject_matter #user_stock_code").val("");
        $("#all_subject_matter .add_list").empty();
    });

    $(function () {
        getUserStockList();


        $("#all_subject_matter .currentPrice").mouseover();

        intervalMarket = setInterval(function () {

            $("#all_subject_matter .currentPrice").each(function () {
                $(this).mouseover()

            });
        }, 12000);
    })

    //首次加载数据库所有自选股
    function getUserStockList(code) {

        $.ajax({
            url: "mobile/getUserStockList",
            dataType: "json",
            type: "post",
            data: {"symbol": code},
            async:false,
            success: function (data) {
                var result = data.obj;
                createHtml(result);
            }

        });
    }


    //创建首页自选股列表html
    function createHtml(result) {

        var position = $("#all_subject_matter #stockList");

        position.empty();

        var str = '';
        for (var i = 0; i < result.length; i++) {
            str += ' <li data-icon="false">\n' +
                '                <a   onclick="checkSymbol(\''+(result[i].symbol)+'\')">\n' +
                '                    <h4 class="title size-9" style="background: none">\n' +
                '                        <span class="fl">\n' +
                '                            <span class="fl name color-w">' + (result[i].stockName) + '</span>\n' +
                '                            <span class="fl number color-orange">' + (result[i].symbol) + '</span>\n' +
                '                        </span>\n' +
                '                        <span class="fl color-red currentPrice" onmouseover="getMarketForSina(\'' + (result[i].symbol) + '\',this)"></span>\n' +
                '                        <span class="fl color-red priceRate"></span>\n' +
                '                        <span class="fl">\n' +
                '                        </span>\n' +
                '                    </h4>\n' +
                '                </a>\n' +
                '                <span class="remove" onclick="remove(\'' + (result[i].symbol) + '\',this)">移除</span>\n' +
                '            </li>';

        }

        position.append(str);
    }


    //从新浪获取指定股票代码行情,首页交易列表
    function getMarketForSina(symbol, obj) {

        var Symbol = symbol;

        //判断是上证股票还是深证股票
        if ("60" == Symbol.substring(0, 2)) {
            Symbol = "sh" + Symbol;
        } else {
            Symbol = "sz" + Symbol;
        }

        $.ajax({
            url: "mobile/getMarket",
            dataType: "json",
            type: "post",
            data: {"symbol": Symbol},
            success: function (data) {
                if (data.result == 1) {

                    //当前价格
                    var currentPrice = data.obj[3];

                    //昨日收盘价格
                    var yesterdayClosingPrice = data.obj[2];

                    //涨跌比例
                    var priceRate = calaMarket(currentPrice, yesterdayClosingPrice);

                    if (priceRate >= 0) {
                        $(obj).parent().find(".currentPrice").html(Number(currentPrice).toFixed(2));
                        $(obj).parent().find(".priceRate").html(priceRate + "%");

                    } else {
                        $(obj).parent().find(".currentPrice").html(Number(currentPrice).toFixed(2));
                        $(obj).parent().find(".priceRate").html(priceRate + "%");

                        $(obj).parent().find(".currentPrice").removeClass('color-red');
                        $(obj).parent().find(".currentPrice").addClass('color-green');
                        $(obj).parent().find(".priceRate").removeClass('color-red');
                        $(obj).parent().find(".priceRate").addClass('color-green');
                    }

                }
            },
            error: function () {
                //返回不可避免的错误，如网络中断，就关闭定时器
                clearInterval(intervalMarket);
            }
        });

    }

    //计算涨跌幅
    function calaMarket(currentPrice, yesterdayClosingPrice) {
        if (currentPrice <= 0) {
            return 0;
        }
        var num = ((currentPrice - yesterdayClosingPrice) / yesterdayClosingPrice) * 100;
        return (num.toFixed(2));
    }


    //实时查找自选股
    function getList() {

        var symbol = $(" #user_stock_code").val();
        var position = $("#all_subject_matter .add_list");

        position.empty();
        var len=0;
        if(isNaN(symbol)) len=2; else len=3;
       
        if(symbol.length>=len){
         $.ajax({
            url: "mobile/getList2",
            dataType: "json",
            type: "post",
            data: {"code": symbol},
            success: function (data) {
                var result = data.obj;
                createHtml2(result, position);
            }

        });    
        }
       
    }

    //创建查找自选股html
    function createHtml2(result, position) {
        var str = '';
        for (var i = 0; i < result.length; i++) {
            str += '  <li data-icon="false">\n' +
                '                        <h4 class="size-9">\n' +
                '                            <span class="text-align fl">' + (result[i].name) + '</span>\n' +
                '                            <span class="text-align fl">' + (result[i].code) + '</span>\n' +
                '                            <span onclick="addUserStock(\'' + (result[i].code) + '\')" class="text-align fr h4_span">添加</span>\n' +
                '                        </h4>\n' +
                '                    </li>';

        }
        position.append(str);
    }


    //加入自选股
    function addUserStock(symbol) {
        $('.add_zxg').fadeOut();
        $.ajax({
            url: "mobile/createUserStock",
            dataType: "json",
            type: "post",
            async:false,
            data: {"symbol": symbol},
            success: function (data) {
                layer.msg(data.msg);
                if (data.result == 1) {
                    var result = data.obj;
                    createHtml_addStock(result);

                }
            }
        });
    }


    //加入自选股成功后，将生成的DOM结构追加到首页列表中
    function createHtml_addStock(result) {

        var position = $("#all_subject_matter #stockList");

        var str = '';

        str += ' <li data-icon="false">\n' +
            '                <a onclick="checkSymbol(\''+(result.symbol)+'\')">\n' +
            '                    <h4 class="title size-9" style="background: none">\n' +
            '                        <span class="fl">\n' +
            '                            <span class="fl name color-w">' + (result.stockName) + '</span>\n' +
            '                            <span class="fl number color-orange">' + (result.symbol) + '</span>\n' +
            '                        </span>\n' +
            '                        <span class="fl color-red currentPrice" onmouseover="getMarketForSina(\'' + (result.symbol) + '\',this)"></span>\n' +
            '                        <span class="fl color-red priceRate"></span>\n' +
            '                        <span class="fl">\n' +
            '                        </span>\n' +
            '                    </h4>\n' +
            '                </a>\n' +
            '                <span class="remove" onclick="remove(\'' + (result.symbol) + '\',this)">移除</span>\n' +
            '            </li>';

        position.append(str);

    }


    //跳转行情页
    function checkSymbol(symbol) {
       // utils.setParam("ticker_symbol", symbol);
        $("#k_details #symbol_k").val(symbol);

        getTradeDetails(symbol);
      location.hash="#k_details";
    }

    //获取指定自选股，加入到详情页面
    function getTradeDetails(symbol) {
        var manageFee=$("#k_details #manage_fee").val();
        $.ajax({
            url: "mobile/getStockInfo",
            dataType: "json",
            type: "post",
            data: {"code": symbol},
            success: function (data) {
                var result = data.obj;
                if (data.result == 1) {

                    $("#k_details .ygj span").html(result.hv30);
                    $("#k_details .stockInfo").html(result.name + "&nbsp;(" + (result.code) + ")");

                    //详情页，刷股票
                    $("#k_details .curPrice").attr("onclick", "getMarketForDetails('" + (symbol) + "')");


                    $("#k_details .create_plan").attr("href","mobile/application/scheme?symbol="+(result.code)+"&symbolName="+(result.name)+"&manageFee="+manageFee);
                   //  $("#k_details .create_plan").attr("href","http://baidu.com");

                    $("#k_details #symbol").val(result.code);
                } else {
                    layer.msg(data.msg);
                }
            }

        });

    }


    //详情页，获取股票行情
    function getMarketForDetails(symbol) {
        var Symbol = symbol;

        //判断是上证股票还是深证股票
        if ("60" == Symbol.substring(0, 2)) {
            Symbol = "sh" + Symbol;
        } else {
            Symbol = "sz" + Symbol;
        }

        $.ajax({
            url: "mobile/getMarket",
            dataType: "json",
            type: "post",
            data: {"symbol": Symbol},
            success: function (data) {
                if (data.result == 1) {

                    //当前价格
                    var currentPrice = data.obj[3];

                    //昨日收盘价格
                    var yesterdayClosingPrice = data.obj[2];

                    //涨跌比例
                    var priceRate = calaMarket(currentPrice, yesterdayClosingPrice);

                    if (priceRate >= 0) {

                        $("#k_details .curPrice").html(Number(currentPrice).toFixed(2));
                        $("#k_details .priceRate_details").html(priceRate + "%");

                        $("#k_details .curPrice").removeClass("color-green");
                        $("#k_details .priceRate_details").removeClass("color-green");

                        $("#k_details .curPrice").addClass("color-red");
                        $("#k_details .priceRate_details").addClass("color-red");

                    } else {
                        $("#k_details .curPrice").html(Number(currentPrice).toFixed(2));
                        $("#k_details .priceRate_details").html(priceRate + "%");


                        $("#k_details .curPrice").addClass("color-green");
                        $("#k_details .priceRate_details").addClass("color-green");

                        $("#k_details .curPrice").removeClass("color-red");
                        $("#k_details .priceRate_details").removeClass("color-red");



                    }

                }
            }
        });
    }



    //按条件查找自选股
    function findUserStock(obj) {
        var symbol =$("#Symbol").val();
        getUserStockList(symbol);
        $(".currentPrice").mouseover();
    }

</script>
<script>
            var winW = $(window).width()/2-57;
            $('#person').css('left',winW);
        </script>
</body>
</html>

