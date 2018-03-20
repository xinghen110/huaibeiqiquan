<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>全部标的</title>
</head>
<%@include file="base.jsp"%>
<style>
    @media screen and (max-width: 1280px) {
        .content-div-two .content-div-two-div {
            margin-left: 209.92px!important;
            width: 608.768px!important;
            height: 288px!important;
        }
        .content-div-two-div-right{
            width: 30%!important;
        }
    }
    @media screen and (max-width: 1530px) {
        .content-div-two-div-left div{
            margin-bottom: 10%!important;
        }
    }
    @media screen and (max-width: 1400px) {
        .content-div-two-div-left{
            width: auto!important;
        }
        .content-div-two-div-right{
            width: 30%!important;
        }
    }


    .none {
        background-color: white;
    }

    .index-a a{
        color: #000000;
    }

    .ui-menu .ui-menu-item-wrapper {
        position: relative;
        padding: 1px 1em 3px .4em;
        background: white;
        width: 200px;
    }
    #all_subject_matter .ui-content .title span:first-child {
        width: 30%;
        margin-left: .3em;
    }
    #all_subject_matter .ui-content .title span {
        width: 30%;
        text-align: center;
        color: white;
    }
    #all_subject_matter .ui-content .title-content span:first-child {
        width: 30%;
        margin-left: .3em;
    }
    #all_subject_matter .ui-content .title-content span {
        width: 30%;
        text-align: center;
        color: white;
        height: 3em;
        line-height: 3em;
    }
    #all_subject_matter .ui-content .title-content span {
        width: 30%;
        text-align: center;
        color: white;
        height: 3em;
        line-height: 3em;
    }
</style>
<body>
<div data-role="page" id="all_subject_matter">
    <div data-role = "content">
        <!-- 头部搜索 -->
        <div class="header">
            <input id="Symbol" class="fl size-9" data-role = "none" type="text" placeholder="输入股票代码或名称" />
            <a id="queryStock" class="ui-btn color-w fr">查询</a>
        </div>
        <h4 class="title size-9">
            <%--<span class="fl">序号</span>--%>
            <span class="fl">股票代码</span>
            <span class="fl">代码名称</span>
            <span class="fl">管理费率</span>
            <%--<span class="fl">最新价</span>--%>
            <%--<span class="fl">涨跌幅</span>--%>
            <span class="fl"><img src="img/mobile/icon_operate.png" /></span>
        </h4>
        <!-- 列表  -->
        <ul id="stockList" data-role = "listview">
        </ul>

        <!-- 底部 -->
        <div class="footer">
            <div class="fl">
                <a href="mobile/stock/center" data-ajax = "false">
                    <img src="img/mobile/tab_price_yellow.png" />
                    <h4 class="color-js size-9">全部标的</h4>
                </a>
            </div>
            <div class="fl">
                <a href="mobile/option/labels" data-ajax = "false">
                    <img src="img/mobile/tab_select_white.png" />
                    <h4 class="color-w size-9">自选标的</h4>
                </a>
            </div>
            <div class="fl">
                <a href="mobile/stock/userinfo" data-ajax = "false">
                    <img src="img/mobile/tab_user_white.png" />
                    <h4 class="color-w size-9">个人中心</h4>
                </a>
            </div>
        </div>
    </div>
</div>
<script src="js/web/stock_data.js"></script>
<script>
    var userStockList = ${userStockList};

    /* 点击收起&展开  */
//    $('.title-content .operation').click(function(){
//        openOrClose();
//    })

    function openOrClose(id) {
        var operationState = $(".operation-state"+id).val();

        if(operationState==0){
            $(".c-state"+id).parent().addClass('seleced')
            $(".operation-state"+id).parent().find('.choose-btn').show();
            $(".operation-state"+id).parent().find('.down').hide();
            $(".operation-state"+id).parent().find('.up').show();
            $(".operation-state"+id).val('1');
        }else{
            $(".operation-state"+id).parent().find('.up').hide();
            $(".operation-state"+id).parent().find('.down').show();
            $(".operation-state"+id).parent().removeClass('seleced')
            $(".operation-state"+id).parent().find('.choose-btn').hide();
            $(".operation-state"+id).val('0')
        }
    }
    /* 加入自选  按钮   */
//    $('.trade_add').click(function(){
//        $(this).hide();
//        $(this).parent().find('.trade_delete').show();
//    })

    function addTrade(obj,id) {
        $("."+id).hide();
        $("."+id).parent().find('.trade_delete').show();
        if(!stockIsCollection(id) && id) {
            $.ajax({
                url: 'mobile/user/stock/save',
                type: 'post', //GET
                async: true,    //或false,是否异步
                data: {"symbol": id},
                timeout: 10000,    //超时时间
                cache: false,
                dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                success: function (data, textStatus, jqXHR) {
                    var jsonData = data[0];
                    if(jsonData.msg == 'success'){
                        console.log("success");
                        userStockList = jsonData.userStockList;
                    }
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
    /* 删除自选  按钮   */
//    $('.trade_delete').click(function(){
//        $(this).hide();
//        $(this).parent().find('.trade_add').show();
//    });
    function deleteTrade(obj,id) {
        $(".delete"+id).hide();
        $(".delete"+id).parent().find('.trade_add').show();
        var symbol = $(obj).parent().parent().find("td:eq(1)").html();
        $.ajax({
            url: 'mobile/user/stock/delete',
            type: 'post', //GET
            async: true,    //或false,是否异步
            data: {"symbol": id},
            timeout: 10000,    //超时时间
            cache: false,
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (data, textStatus, jqXHR) {
                var jsonData = data[0];
                if(jsonData.msg == 'success'){
                    console.log("success");
                    userStockList = jsonData.userStockList;
                }
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

    $(document).ready(function () {
        queryStock();
    });

    function queryStock() {
        var Symbol = $("#Symbol").length == 0 ? "" : $("#Symbol").val();
        var Min_option_price = "";
        var Max_option_price = "";
        $.ajax({
            url: 'mobile/stock/list/json',
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
                    var symbol = item['code'].toString();
                    var symbolName = item['name'];
                    var manageFee = item.manageFee;
                    var xuhao = i+1;
                    var trDom = document.createElement("li");
                    var trInnerHTML = '<h4 class="title-content size-9">\n' +
//                        '                    <span class="fl color-orange">'+xuhao+'</span>\n' +
                        '                    <span class="fl">'+symbol+'</span>\n' +
                        '                    <span class="fl color-orange">'+symbolName+'</span>\n' +
                        '                    <span class="fl">'+manageFee+'%</span>\n' +
//                        '                    <span class="fl color-red">13240.5</span>\n' +
//                        '                    <span class="fl color-red">+0.08%</span>\n' +
                        '                    <span  onclick="openOrClose(\''+symbol.toString()+'\')" class="fl operation">\n' +
                        '\t\t\t\t\t\t\t\t<img src="img/mobile/icon_arrow_white_down.png" class="down" />\n' +
                        '\t\t\t\t\t\t\t\t<img style="display: none;" src="img/mobile/icon_arrow_white_up.png" class="up" />\n' +
                        '\t\t\t\t\t\t\t</span>\n' +
                        '                </h4>\n' +
                        '                <h5 class="choose-btn">\n' +
                        '\t\t\t\t\t\t\t<span class="fl">\n' +
                        '\t\t\t\t\t\t\t\t<a class="color-w" data-ajax = "false" href="mobile/application/scheme?symbol='+symbol+'&symbolName='+encodeURI(symbolName)+'&manageFee='+manageFee+'"><img src="img/mobile/icon_trade_cart.png" />申请方案</a>\n' +
                        '\t\t\t\t\t\t\t\t<span style="width: 0;" class="fr">|</span>\n' +
                        '\t\t\t\t\t\t\t</span>\n' +
                        '                    <span onclick="addTrade(this,\''+symbol+'\')" class="fr trade_add '+symbol.toString()+'"><img src="img/mobile/icon_trade_add.png" />加入自选</span>\n' +
                        '                    <span style="display: none;" onclick="deleteTrade(this,\''+symbol.toString()+'\')" class="fr trade_delete delete'+symbol.toString()+'"><img src="img/mobile/icon_trade_delete.png" />删除自选</span>\n' +
                        '                    </span>\n' +
                        '                </h5>\n' +
                        '                <input type="hidden" value="0" id="operation-state'+symbol.toString()+'" class="operation-state'+symbol.toString()+'"/>';
                    trDom.innerHTML = trInnerHTML;
                    $("#stockList").append(trDom);
                    if(stockIsCollection(symbol)) {
                        $('#operation-state'+symbol.toString()).val(1);
                        $("."+symbol).hide();
                        $("."+symbol).parent().find('.trade_delete').show();
                    }
                });
            },
            error: function (xhr, textStatus) {
                console.log('错误')
                console.log(xhr);
                console.log(textStatus);
                alert("系统繁忙，请稍后重新提交！");
            },
            complete: function () {
                console.log('结束')
            }
        })
    }

    function stockIsCollection(symbol) {
        var result = false;
        $(userStockList).each(function (index, item) {
            if (symbol == item.symbol) {
                result = true;
            }
        });
        return result;
    }
</script>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.js"></script>
<script src="js/web/stock_data.js"></script>
<script>
    <%--$( function() {--%>
    <%--var data = ${dataList};--%>
    <%--var availableTags = [];--%>
    <%--for(var i=0;i<data.length;i++){--%>
    <%--availableTags[i]=data[i].symbol;--%>
    <%--}--%>
    <%--$( "#Symbol" ).autocomplete({--%>
    <%--source: availableTags--%>
    <%--});--%>
    <%--} );--%>


    $(function () {
        //{"cycle":"1m","exerciseMode":"A","expireDate":"1509292800000","insureDate":"1506614400000","offerPrice":"4.97","offerPriceDate":"1506614400000","optionCode":"000001-C-A-20171030-100","optionType":"C","priceMode":"100","symbol":"000001","symbolName":"平安银行"}
        <%--var data = ${dataList};--%>
        var availableTags = [];
        $(data).each(function(i, o){
            var autocompleteData = {
                "value": o.symbol,
                "label": o.symbol + "/" + o.symbolName
            };
            availableTags.push(autocompleteData);
        });
        $("#Symbol").autocomplete({
            minLength: 1,
            source: availableTags,
            focus: function (event, ui) {
                $("#Symbol").val(ui.item.value);
                return false;
            },
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
    });


    $("#queryStock").click(function () {
        queryStock();
    });
</script>
</body>
</html>

