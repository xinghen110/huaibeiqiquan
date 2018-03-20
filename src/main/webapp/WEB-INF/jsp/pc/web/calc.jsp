<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<base href="<%=basePath%>"/>
<ry:binding type="1" bingdingName="STOCK_PLAN_CYCLE" parentCode="STOCK_PLAN_CYCLE"></ry:binding>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="css/web/jquery.mobile-1.4.2.css" />
    <script type="text/javascript" src="js/web/jquery.min.js" ></script>
    <script type="text/javascript" src="js/web/jquery.mobile-1.4.2.js" ></script>
    <title></title>
</head>
<style>
    *{text-shadow: none;font-weight: normal !important;box-shadow: none !important;}
    h1,h2,h3,h4,h5,h6{font-weight: normal;text-shadow: none;}
    a{text-decoration: none;}
    .fr{ float:right;}
    .fl{ float:left;}
    .myAlert{width: 100px;height: 80px;border: 1px solid;border-radius:15px;position: fixed;top: 40%;left: 50%;margin-left: -50px;margin-top: -40px;line-height: 22px;background: #5A5A5A;color: #ffffff;font-size: 1.2em;padding: 10px;display: none;text-align: center;padding-top: 30px;z-index: 9999;}
    .myConfirm{position: fixed;top:0;left:0;width: 100%;height: 100%;background: rgba(0,0,0,0.2);font-size: 1em;}
    .myConfirm2{min-width: 200px;min-height: 120px;position: fixed;left: 50%; top: 50%;margin-left: -100px;margin-top:-60px;border-radius:15px;background: #fff;font-size: 1.2em;}
    .myConfirm2 .content{width: 100%;line-height: 30px;text-align: center;margin-top: 20px;padding-bottom:5px;font-weight: bold;}
    .myConfirm2 .footer{width: 100%;height: 40px;line-height: 40px;position: absolute;bottom: 0;left: 0;}
    .myConfirm2 .footer a{float: left;width: 50%;text-align: center;line-height: 30px;}

    .color-t{color: #5F5F5F !important;}/*标题字体颜色*/
    .color-c{color: #5F5F5F !important;}/*内容字体颜色*/
    .color-red{color: #d7000e !important;}
    .color-hs{color: #999 !important;}
    .color-ls{color: #27BC9C!important;}
    .color-blue{color: #229EED !important;}
    .color-orange{color: #F3CC1B!important;}
    .color-w{color: white!important;}
    .color-green{color: mediumseagreen!important;}
    .color-js{color:#D2A578!important;}

    .clear{clear: both;}
    .bgd-orange{background: orange!important;}
    .bgd-green{background: mediumseagreen!important;}

    .weight-bolder{font-weight: bolder !important;}
    .margin-left-5{margin-left: .5em!important;}
    .size-8{font-size: 0.8em !important;}
    .size-9{font-size: 0.9em !important;}
    .size-10{font-size: 1em !important;}
    .size-11{font-size: 1.1em !important;}
    .size-12{font-size: 1.2em !important;}
    .size-13{font-size: 1.3em !important;}
    .size-14{font-size: 1.4em !important;}
    .size-15{font-size: 1.5em !important;}

    .text-align{text-align: center!important;}
    .white-space{white-space: normal!important;}
    .ui-header, .ui-footer{border: none;}
    .ui-header a{background: none !important;border: none !important;}
    .ui-header a img{width: 0.6rem;margin-top: 0.1rem;}
    /*询价*/
    #inquiry{background: url(img/mobile/bg_inquiry.jpg) no-repeat;background-size:cover ;}
    #inquiry .inquiry .title{color: #E1B080;}
    #inquiry .inquiry .wc{width: 45%;margin-bottom: 1.5em;}
    #inquiry .inquiry .wc .wc-h4{margin: 0;margin-bottom: .5em;color: white;}
    #inquiry .inquiry .input_select{font-size: .9em;border-radius:5px ;color: white;outline: none;background: #514F4E;border: none;height: 2.2em;line-height: 2.2em;width: 100%; padding-left: 0.5em;}
    #inquiry .inquiry .ui-btn{border: none;background: #A28972!important;border-radius:20px;clear: both;text-shadow: none!important;color: white!important;margin: 1em 3em;margin-bottom: 2em;}
    #inquiry .inquiry .last_time_title{margin-bottom: .5em;}
    /*询价结果*/
    #inquiry{background: url(img/mobile/bg_inquiry.jpg) no-repeat;background-size:cover ;height: 100%;}
    #inquiry .inquiry_result{padding: 0;background: rgba(35,34,33,.8); height: 94%; width: 91%; margin-top: 5%; margin-left: 1em; margin-right: 1em;}
    #inquiry .inquiry_result .title{color: white;position: relative;}
    #inquiry .inquiry_result .title .back{position: absolute;right: 1em}
    #inquiry .inquiry_result .wc{width: 50%;margin-bottom: 2em;}
    #inquiry .inquiry_result table{width: 100%;margin-bottom: 3em;}
    #inquiry .inquiry_result table tr{    height: 2em;line-height: 2em;}
    #inquiry .inquiry_result table th{color: white!important;font-size: .9em;width: 25%;}
    #inquiry .inquiry_result table td{color: white;font-size: .9em;width: 25%;text-align: center;}
    #inquiry .inquiry_result .ui-btn{border: none;background: #A28972!important;border-radius:20px;clear: both;text-shadow: none!important;color: white!important;margin: 1em 3em;}

</style>
<body>
<div data-role="page" id="inquiry">
    <div data-role = "content" class="inquiry">
        <h4 class="title size-15 text-align">PROFIT & LOSS</h4>
        <div class="fl wc">
            <h4 class="wc-h4">标的股票</h4>
            <input id="Symbol" onblur="changeValue();" data-role = "none" class="input_select" type="text" />
        </div>
        <div class="fr wc">
            <h4 class="wc-h4">行权周期</h4>
            <select  id="cycle" data-role = "none" class="input_select">
                <option>请选择行权周期</option>
                <option value="1m">一个月</option>
                <option value="3m">三个月</option>
            </select>
        </div>
        <div class="fl wc clear">
            <h4 class="wc-h4">前收盘价</h4>
            <input id="preClosePrice" value="0" data-role = "none"  type="text" class="input_select" />
        </div>
        <div class="fr wc">
            <h4 class="wc-h4">到期日</h4>
            <input   type="text" class="input_select daoqiri" />
        </div>
        <div class="fl wc clear" style="margin-bottom: 2.5em;">
            <h4 class="wc-h4">名义本金(单位:元)</h4>
            <input value="1000000" id="buyMarketPrice" data-role = "none"  type="text" class="input_select" />
        </div>
        <input type="hidden" id="manageFee" value="6.5"/>
        <a class="ui-btn ksxj" data-ajax='false'>开始询价</a><!-- 不是Ajax请求  就加个data-ajax='false' -->

        <div class="text-align last_time">
            <a>
                <h4 class="color-js last_time_title">查看上次询价结果</h4>
                <img width="20px" src="img/web/icon_inquiry_arrow.png" />
            </a>
        </div>
    </div>

    <div data-role = "content" class="inquiry_result" style="display: none;">
        <h4 class="title text-align">询价结果<a class="back"><img width="20px" src="img/web/icon_inquiry_close.png" /></a></h4>
        <div class="fl text-align wc">
            <h4 id="qiquanjiage" class="color-js">0元</h4>
            <h4 class="color-w size-9">期权价格</h4>
        </div>
        <div class="fr text-align wc">
            <h4  class="color-js"><span id="qualijinbili">0</span>%</h4>
            <h4 class="color-w size-9">权利金比例</h4>
        </div>
        <table data-role = "none" id="table_area">
            <tr>
                <th>股价</th>
                <th>涨幅</th>
                <th>亏盈比例</th>
                <th>盈利金额</th>
            </tr>
            <%--<tr>--%>
                <%--<td>0.00</td>--%>
                <%--<td class="color-js">36.90%</td>--%>
                <%--<td class="color-js">0%</td>--%>
                <%--<td class="color-js">190000元</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>0.00</td>--%>
                <%--<td class="color-js">36.90%</td>--%>
                <%--<td class="color-js">0%</td>--%>
                <%--<td class="color-js">190000元</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>0.00</td>--%>
                <%--<td class="color-js">36.90%</td>--%>
                <%--<td class="color-js">0%</td>--%>
                <%--<td class="color-js">190000元</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>0.00</td>--%>
                <%--<td class="color-js">36.90%</td>--%>
                <%--<td class="color-js">0%</td>--%>
                <%--<td class="color-js">190000元</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>0.00</td>--%>
                <%--<td class="color-js">36.90%</td>--%>
                <%--<td class="color-js">0%</td>--%>
                <%--<td class="color-js">190000元</td>--%>
            <%--</tr>--%>
        </table>
        <a class="ui-btn cxxj">重新询价</a>
    </div>

</div>
</body>
<script>
    jQuery('.inquiry .ksxj').click(function(){//开始询价
        $("#table_area").empty();
        var table_head = " <tr>\n" +
            "                <th>股价</th>\n" +
            "                <th>涨幅</th>\n" +
            "                <th>亏盈比例</th>\n" +
            "                <th>盈利金额</th>\n" +
            "            </tr>";
        $("#table_area").append(table_head);
        jQuery('.inquiry').hide();
        jQuery('.inquiry_result').show();
        kaishijisuan();
    })
    jQuery('.inquiry .last_time').click(function(){//上次询价
        jQuery('.inquiry').hide();
        jQuery('.inquiry_result').show();
    })
    jQuery('.inquiry_result .back').click(function(){//询价结果-关闭按钮
        jQuery('.inquiry').show();
        jQuery('.inquiry_result').hide();
    })
    jQuery('.inquiry_result .cxxj').click(function(){//询价结果-关闭按钮
        jQuery('.inquiry').show();
        jQuery('.inquiry_result').hide();
    })
</script>
<script type="text/javascript">

    var $t = 0;
    $(".index-list li").mouseenter(function(){
        if($t==$(this).index()){

        }else{
            $t = $(this).index();
            $(".index-list li .point_white").show();
            $(this).find(".point_white").hide();

            $(".index-list li .point_black").hide();
            $(this).find(".point_black").show();

            $(".news").hide();
            $(this).parent().parent().parent().find(".news").eq($t).show(500);
            $(".index-num").text("0"+(parseInt($t)+1))
        }
    });
    //    $('#menu li a').click(function () {
    //        var f = this;
    //        $('#menu li a').each(function () {
    //            this.className = this == f ? 'active' : 'none'
    //        });
    //    });

    var manageFeeIsDone = false;
    var preClosePriceIsDone = false;

    function kaishijisuan(manageFee) {
        //开始计算盈亏收益
        var symbol = $("#Symbol").val();
        if(!symbol){
            alert("请先输入股票编码");
        }

        if(!manageFeeIsDone){
            queryPlanManageFee();
            return;
        }

        //todo 这里需要查询sina的最新价格和股票的最新手续费
//        if(!preClosePriceIsDone) {
//            queryStockPreClosePrice();
//            return;
//        }

        manageFeeIsDone = preClosePrice = false;

        var cycle = $("#cycle").val();
        var preClosePrice = $("#preClosePrice").val();//sina
        var endDate = getEndDate(cycle).toLocaleDateString();
        var mingyibenjin = $("#buyMarketPrice").val();
        var quanlijinbili = (manageFee * 1).toFixed(2);
        var quanlijin = (mingyibenjin / 100 * quanlijinbili).toFixed(2);
//        $(".daoqiri").val(endDate);
//        $(".quanlijin").html(quanlijin);
        $("#qiquanjiage").html(quanlijin);
//        $("#qualijinbili").html(quanlijinbili);

        var yinkuibili = [0, 20, 50, 100];//盈亏比例 百分比%
        $(yinkuibili).each(function (i, o) {
            var zongbi = 1 + (o / 100);//计算用的比例
            var zhangfuData = {
                gujia: (preClosePrice * (1 + (quanlijinbili * zongbi / 100))).toFixed(2),
                zhangfu: (quanlijinbili * zongbi).toFixed(2),
                yingkui: o.toFixed(2),
                yinglijine: (quanlijin * zongbi).toFixed(2)
            };
            var tr = $("<tr>").append("<td>"+zhangfuData.gujia+"</td>\n" +
                " <td class=\"color-js\">"+zhangfuData.zhangfu+"</td>\n" +
                " <td class=\"color-js\">"+zhangfuData.yingkui+"</td>\n" +
                " <td class=\"color-js\">"+zhangfuData.yinglijine+"</td>\n" +
                "</tr>");
            $("#table_area").append(tr);
        });
        chose;
    }

    function getEndDate(cycle) {
        var length = cycle.length;
        var number = cycle.substring(0, length - 1);
        var unit = cycle.substring(length - 1);
        var now = new Date();
        now = dateAdd(now, unit, number);
        return now;
    }

    function dateAdd(date, strInterval, Number) {  //参数分别为日期对象，增加的类型，增加的数量
        var dtTmp = date;
        Number = parseInt(Number);
        switch (strInterval) {
            case 'second':
            case 's' :
                return new Date(Date.parse(dtTmp) + (1000 * Number));
            case 'minute':
            case 'n' :
                return new Date(Date.parse(dtTmp) + (60000 * Number));
            case 'hour':
            case 'h' :
                return new Date(Date.parse(dtTmp) + (3600000 * Number));
            case 'day':
            case 'd' :
                return new Date(Date.parse(dtTmp) + (86400000 * Number));
            case 'week':
            case 'w' :
                return new Date(Date.parse(dtTmp) + ((86400000 * 7) * Number));
            case 'month':
            case 'm' :
                return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
            case 'year':
            case 'y' :
                return new Date((dtTmp.getFullYear() + Number), dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
        }
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
                $("#qualijinbili").html(manageFee);
                manageFeeIsDone = true;
                kaishijisuan(manageFee);
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

</script>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.js"></script>
<script src="js/web/stock_data.js"></script>
<script>
    chose;
    var chose = $(function () {
        //{"cycle":"1m","exerciseMode":"A","expireDate":"1509292800000","insureDate":"1506614400000","offerPrice":"4.97","offerPriceDate":"1506614400000","optionCode":"000001-C-A-20171030-100","optionType":"C","priceMode":"100","symbol":"000001","symbolName":"平安银行"}

        var availableTags = [];
        $(data).each(function(i, o){
            var autocompleteData = {
                "value": o.symbol,
                "label": o.symbol + "/" + o.symbolName
            };
            availableTags.push(autocompleteData);
        });

        $("#Symbol").autocomplete({
            minLength: 0,
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
            if(ul[0].childElementCount>10){
                return $("").appendTo(ul);
            }
            return $("<li style='list-style: none;background: #ffffff'>")
                .append("<div>" + item.label + "</div>")
                .appendTo(ul);
        };
    });

  $("#cycle").change(function () {
        var cycle = "1m";
        cycle = $("#cycle").val();
        var preClosePrice = $("#preClosePrice").val();//sina
        var endDate = getEndDate(cycle).toLocaleDateString();
        $(".daoqiri").val(endDate);
    });

    function changeValue() {
        queryStockPreClosePrice();
    }

    $("#Symbol").bind('input propertychange', function() {
        queryStockPreClosePrice();
    });
</script>
</html>

