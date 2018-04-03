<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@include file="header.jsp" %>
<ry:binding type="1" bingdingName="STOCK_PLAN_CYCLE" parentCode="STOCK_PLAN_CYCLE"></ry:binding>
<link rel="stylesheet" href="https://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.css">
<body>
<style>

</style>
<%@include file="header_title.jsp" %>
<div class="content">
    <img class="banner-img" src="img/web/banner_ry.jpg" />
    <div class="content-div">
        <h4 style="color: #a97b52;" class="text-align weight-bolder size-14">YOUWONN INVESTMENT</h4>
        <hr class="hr-title" />
        <h4 class="text-align size-8 color-hs">对于每一笔投资，你都应当有信心和勇气投入</h4>
        <div class="fl choose-type">
            <div>
                <a href="web/aboutus">
                <img class="grey" src="img/web/icon_index_block1_grey.png" />
                <img class="white" style="display: none;" src="img/web/icon_index_block1_white.png" />
                <h4 style="color: #0c0c0c">Company Profile</h4>
                <h4 style="color: #0c0c0c">关于我们</h4>
                </a>
            </div>
        </div>
        <div class="fl choose-type">
            <div>
                <a href="web/aboutus?index=3">
                <img class="grey" src="img/web/icon_index_block2_grey.png" />
                <img class="white" style="display: none;" src="img/web/icon_index_block2_white.png" />
                <h4  style="color: #0c0c0c">Corporate Cultrue</h4>
                <h4  style="color: #0c0c0c">集团介绍</h4>
                </a>
            </div>
        </div>
        <div class="fl choose-type">
            <div>
                <a href="web/aboutus?index=2">
                <img class="grey" src="img/web/icon_index_block1_grey.png" />
                <img class="white" style="display: none;" src="img/web/icon_index_block1_white.png" />
                <h4 style="color: #0c0c0c">Investment</h4>
                <h4 style="color: #0c0c0c">投资保障</h4>
                </a>
            </div>
        </div>
        <h4 style="color: #a97b52;" class="text-align weight-bolder size-14">PROFIT AND LOSS</h4>
        <hr class="hr-title" />
        <h4 class="text-align size-8 color-hs">盈亏计算器</h4>
    </div>
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
    <!--<div style="margin: 0 auto;width: 1280px;height: 300px;">
        <div style="min-height: 110px;">
            <div class="news" style="line-height: 55px;display: block;">
                <h4 class="title">标题标题标题标题标题标题标题 1[2017/02/12]</h4>
                <h4 style="color: #999;" class="title-content">内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容...<a class="color-red"> 查看详情</a></h4>
            </div>
            <div class="news" style="line-height: 55px;">
                <h4 class="title">标题标题标题标题标题标题标题 2[2017/02/12]</h4>
                <h4 style="color: #999;" class="title-content">内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容...<a class="color-red"> 查看详情</a></h4>
            </div>
            <div class="news" style="line-height: 55px;">
                <h4 class="title">标题标题标题标题标题标题标题 3[2017/02/12]</h4>
                <h4 style="color: #999;" class="title-content">内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容...<a class="color-red"> 查看详情</a></h4>
            </div>
        </div>
        <h1 style="font-size:45px;margin-top: -67px; margin-right: 39px;" class="fr"><span class="fl index-num">01</span><span style="margin-top: -15px;" class="fl size-8">+</span><br /></h1>
            <h4 style="font-size: 18px;float: right;margin-right: 64px; margin-top: -12px;" class="color-hs">NEWS</h4>
        <div>
            <ul class="index-list">
                <li class="fl">
                    <img style="display: none;" width="12px" class="point_white" src="img/web/icon_point_white.png" />
                    <img style="display: ;" class="point_black" width="12px" src="img/web/icon_point_black.png" />
                </li>
                <li class="fl">
                    <img width="12px" class="point_white" src="img/web/icon_point_white.png" />
                    <img style="display: none;" class="point_black" width="12px" src="img/web/icon_point_black.png" />
                </li>
                <li class="fl">
                    <img width="12px" class="point_white" src="img/web/icon_point_white.png" />
                    <img style="display: none;" class="point_black" width="12px" src="img/web/icon_point_black.png" />
                </li>
            </ul>
        </div>
    </div>-->
    <div class="unkuown">
        <div class="unkuown-div">
            <div class="fl">
                <img style="margin-top: 45px;" src="img/web/icon_index_foot1.png" />
                <h4 class="color-hs size-9">Flexible funds</h4>
                <h4 class="color-hs size-9">资金灵活</h4>
            </div>
            <div class="fl">
                <img style="margin-top: 45px;" src="img/web/icon_index_foot2.png" />
                <h4 class="color-hs size-9">Limited risk</h4>
                <h4 class="color-hs size-9">风险有限</h4>
            </div>
            <div class="fl">
                <img style="margin-top: 45px;" src="img/web/icon_index_foot3.png" />
                <h4 class="color-hs size-9">Unlimited income</h4>
                <h4 class="color-hs size-9">收益无限</h4>
            </div>
            <div class="fl">
                <img style="margin-top: 45px;" src="img/web/icon_index_foot4.png" />
                <h4 class="color-hs size-9">Combination strategy</h4>
                <h4 class="color-hs size-9">组合策略</h4>
            </div>
            <div class="fl">
                <img style="margin-top: 45px;" src="img/web/icon_index_foot5.png" />
                <h4 class="color-hs size-9">Custom contract</h4>
                <h4 class="color-hs size-9">定制合约</h4>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
<script src="https://cdn.bootcss.com/moment.js/2.19.0/moment.min.js"></script>
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
</script>

<script>

    // 日期格式化添加原型对象
    // 对Date的扩展，将 Date 转化为指定格式的String
    // 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
    // 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
    Date.prototype.Format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "H+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }

    var $t = 0;
    var pageSize = "3";//每页行数
    var totalPage = $("#totalPage").val();//总页数
    var totalCount =$("#totalCount").val();//总条数
    var params="";//参数
    var url = "web/industry/information/list";
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

//        //todo 这里需要查询sina的最新价格和股票的最新手续费
//        if(!preClosePriceIsDone) {
//            queryStockPreClosePrice();
//            return;
//        }

        manageFeeIsDone = preClosePrice = false;

        var cycle = $("#cycle").val();
        var preClosePrice = $("#preClosePrice").val();//sina
//        var endDate = getEndDate(cycle).toLocaleDateString();
        cycle=cycle.substring(0,cycle.length-1);
        $(".daoqiri").val("");
        $(".daoqiri").val(moment().add(cycle,'month').format('YYYY-MM-DD'));
        var mingyibenjin = $("#buyMarketPrice").val();
        var quanlijinbili = ($("#manageFee").html() * 1).toFixed(2);
        var quanlijin = (mingyibenjin / 100 * quanlijinbili).toFixed(2);

//        $(".daoqiri").val(endDate);
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
    function changeValue() {
        queryStockPreClosePrice();
    }
//    $("#Symbol").bind('input propertychange', function() {
//        queryStockPreClosePrice();
//    });
</script>
</body>
</html>


