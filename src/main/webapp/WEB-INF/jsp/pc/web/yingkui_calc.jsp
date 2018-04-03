<%--
  Created by IntelliJ IDEA.
  User: hexin
  Date: 2018/4/2
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@include file="header.jsp" %>
<ry:binding type="1" bingdingName="STOCK_PLAN_CYCLE" parentCode="STOCK_PLAN_CYCLE"></ry:binding>
<link rel="stylesheet" href="https://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.css">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.js"></script>
<script src="https://cdn.bootcss.com/moment.js/2.19.0/moment.min.js"></script>
<script src="js/web/stock_data.js"></script>
<script>

    var manageFeeIsDone = false;

    function kaishijisuan() {
        //开始计算盈亏收益
        var symbol = $("#Symbol").val();
        if(!symbol){
            alert("请先输入股票编码");
        }

        if(!manageFeeIsDone){
            queryPlanManageFee();
            return;
        }

        manageFeeIsDone = preClosePrice = false;

        var cycle = $("#cycle").val();
        var preClosePrice = $("#preClosePrice").val();//sina
        cycle=cycle.substring(0,cycle.length-1);
        $(".daoqiri").val("");
        $(".daoqiri").val(moment().add(cycle,'month').format('YYYY-MM-DD'));
        var mingyibenjin = $("#buyMarketPrice").val();
        var quanlijinbili = ($("#manageFee").html() * 1).toFixed(2);
        var quanlijin = (mingyibenjin / 100 * quanlijinbili).toFixed(2);

        $(".quanlijin").html(quanlijin);

        var yinkuibili = [0, 20, 50, 100];//盈亏比例 百分比%
        $("#yinkuibili-div").empty();
        $(yinkuibili).each(function (i, o) {
            var zongbi = 1 + (o / 100);//计算用的比例
            var zhangfuData = {
                gujia: (preClosePrice * (1 + (quanlijinbili * zongbi / 100))).toFixed(2),
                zhangfu: (quanlijinbili * zongbi).toFixed(2),
                yingkui: o.toFixed(2),
                yinglijine: quanlijin * zongbi
            };
            var tr = $("<tr>").append(
                "<td style='text-align: left;'>"+zhangfuData.gujia+"</td>"+
                "<td  style='text-align: left;color: #e7b865;'>"+zhangfuData.zhangfu+"</td>"+
                "<td  style='text-align: left;color: #e7b865;'>"+zhangfuData.yingkui+"%</td>"+
                "<td style='text-align: left;color: #e7b865;'>"+zhangfuData.yinglijine+"</td>"
            );
            $("#yinkuibili-div").append(tr);
        })
    }

    function queryPlanManageFee(){
        var Symbol = $("#Symbol").val();
        var $cycle = $("#cycle");

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
                $("#manageFee").html(manageFee);

                manageFeeIsDone = true;
                kaishijisuan();
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
    function changeValue() {
        queryStockPreClosePrice();
    }
    function queryStockPreClosePrice() {
        var Symbol = $("#Symbol").val();
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
                $("#preClosePrice").val(stockData);
                preClosePriceIsDone = true;
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
</script>
<body>

<div class="content">
    <div class="content-div-two">
        <img class="fl" class="bg_black" src="img/web/bg_black.png" width="63%" style="margin-left: 16.4%;" />
        <div class="content-div-two-div">
            <div class="content-div-two-div-left fl">
                <div class="fl">
                    <h4 class="fl color-white">标的股票</h4>
                    <input onblur="changeValue();" id="Symbol" class="fl" type="text" />
                </div>
                <div class="fl">
                    <h4 class="fl color-white">行权周期</h4>
                    <select id="cycle">
                        <c:forEach items="${STOCK_PLAN_CYCLE}" var="d">
                            <option value="${d.itemCode}">${d.itemName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="fl" style="clear: both;">
                    <h4 class="fl color-white">前收盘价</h4>
                    <input  id="preClosePrice" readonly="readonly" class="fl" type="text" value="0" />
                </div>
                <div class="fl">
                    <h4 style="width: 64px; text-align: right;"  class="fl color-white">到期日</h4>
                    <input class="fl daoqiri" type="text" />
                </div>
                <div class="fl" style="clear: both;">
                    <h4 class="fl color-white">权 利 金</h4>
                    <h4 class="fl" style="width: 110px;margin-left: 20px;"><span class="quanlijin" style="color: #e7b865;"></span><span class="color-white"> 元</span></h4>
                    <%--<h4 class="fl" style="width: 110px;margin-left: 20px;"><span style="color: #e7b865;">9999</span><span class="color-white"> 元</span></h4>--%>
                </div>
                <div class="fl">
                    <h4 class="fl color-white">名义本金</h4>
                    <input id="buyMarketPrice"  class="fl" type="text" value="100000" />
                </div>
                <div class="fl" style="clear: both;">
                    <h4 class="fl color-white" style="width: 63px;">权利金比例</h4>
                    <h4 class="fl" style="width: 110px;margin-left: 20px;"><span style="color: #e7b865;" id="manageFee">9</span><span class="color-white"> %</span></h4>
                </div>
                <div class="fl">
                    <button style="cursor: pointer;border: 1px solid #e7b865; background: #a97b52; color: white; border-radius: 5px;height: 3em; width: 180px;" onclick="kaishijisuan()">开始计算</button>
                </div>
            </div>

            <div style="width: 37%; float: left;margin-top: -22px;" class="content-div-two-div-right">
                <table style="width: 100%;">
                    <thead>
                    <tr>
                        <th style="text-align: left;">股价</th>
                        <th style="text-align: left;">涨幅</th>
                        <th style="text-align: left;">亏盈比例</th>
                        <th>亏盈金额</th>
                    </tr>
                    </thead>
                    <tbody id="yinkuibili-div">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
