<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@include file="header.jsp" %>
<ry:binding type="1" bingdingName="STOCK_PLAN_CYCLE" parentCode="STOCK_PLAN_CYCLE"></ry:binding>
<link rel="stylesheet" href="https://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.css">
<body>
<style>
    .page_div {
        margin-top: 20px;
        margin-bottom: 20px;
        font-size: 15px;
        font-family: "microsoft yahei";
        color: #666666;
        margin-right: 10px;
        padding-left: 20px;
        box-sizing: border-box;
    }
    /*
     * 页数按钮样式
     */
    .page_div a {
        min-width: 30px;
        height: 28px;
        border: 1px solid #dce0e0!important;
        text-align: center;
        margin: 0 4px;
        cursor: pointer;
        line-height: 28px;
        color: #666666;
        font-size: 13px;
        display: inline-block;
    }
    #firstPage,
    #lastPage {
        width: 50px;
        color: #0073A9;
        border: 1px solid #0073A9!important;
    }
    #prePage,
    #nextPage {
        width: 70px;
        color: #0073A9;
        border: 1px solid #0073A9!important;
    }
    .page_div .current {
        background-color: #0073A9;
        border-color: #0073A9;
        color: #FFFFFF;
    }
    .totalPages {
        margin: 0 10px;
    }
    .totalPages span,
    .totalSize span {
        color: #0073A9;
        margin: 0 5px;
    }
    .video-div-list {
         width: 100%;
        margin: 0 auto;
        height: 460px;
    }
    .video-div-list div {
        width: 23%;
        height: 255px;
        background: white;
        margin-left: 20px;
        margin-bottom: 20px;
        cursor: pointer;
    }
</style>
<%@include file="header_title.jsp" %>
<img src="img/web/banner_pro.jpg" style="margin-bottom: -4px;min-width: 1280px;" width="100%" />
<ul class="product_list">
    <li class="fl product_list_li">个股计算</li>
    <li class="fl">视频介绍</li>
    <li class="fl">产品介绍</li>
    <li class="fl">期货案例</li>
    <li class="fl">产品特点</li>
    <li class="fl">期权期货区别</li>
</ul>
<div class="input-div-wc wc" style=""><!--个股计算-->
    <h1><span class="color-red" style="color: #a97b52!important;">STOCK </span><span style="color: white!important;">OPTIONS</span></h1>
    <div class="input-div">
        <h4 class="gpdm color-hs size-9">股票代码</h4>
        <input id="symbol" type="text" style="margin-left: 250px;background: rgba(255,255,255,.5)!important;" class="fl" placeholder="请输入股票代码"/>
        <h4 class="hqzq color-hs size-9">行权周期</h4>
        <select type="text" style="margin-right: 250px;background: rgba(255,255,255,.5)!important;" class="fr" id="cycle">
            <c:forEach items="${STOCK_PLAN_CYCLE}" var="d">
                <option value="${d.itemCode}">${d.itemName}</option>
            </c:forEach>
        </select>
        <button style="background: #a97b52!important;" class="color-white ljcx">立即查询</button>
        <img src="img/web/icon_red_triangle.png" style="margin-top: 20px;"/>
        <h4 style="border-radius: 20px;background: rgba(255,255,255,.5)!important;width: 400px;height: 40px;line-height: 40px;margin: 0 auto;margin-top: 15px;" class="color-white">管理费比例：<span class="manageFee">---</span></h4>
    </div>
</div>
<div class="wc video-div-wc"><!--视频介绍-->
    <div id="t_body" class="video-div-list" >

    </div>
    <div id="page_area" style="width: 1280px; margin: 0 auto; height: 40px;text-align: center;">
        <input id="currentPage" type="hidden" value="${pageList.pageNum}" />
        <input id="totalPage" type="hidden" value="${pageList.totalPage}" />
        <input id="totalCount" type="hidden" value="${pageList.totalCount}" />
        <div id="page" class="page_div"></div>
    </div>
</div>

<div class="wc" style="height: auto!important;display: none;"><!--产品介绍-->
    <div class="cpjs-div-wc">
        <div class="ts-div" style="background: rgba(255,255,255,.3);">
            <div class="text-align">
                <img src="img/web/icon_pro1.png" />
                <h4 style="color: #a97b52!important;">什么是金点护航？</h4>
            </div>
            <h3 class="color-white text-align"><span class="size-15" style=" color: wheat;" >金点护航 </span>是一款期权为大规则前提的A股衍生产品。</h3>
            <h5 class="color-white text-align">相较于以往投顾产品不同之处在于用户在此方案中只需投入极小的管理费成本，即可享受在方案约定合作周期内股票上涨所带来的整段收益。</h5>
            <h5 class="color-white text-align">若方案执行过程中该股票下跌，用户无需承担下跌亏损，无需追加保证金，将以小博大的理念完美诠释。</h5>
        </div>
    </div>
    <div class="text-align lc-img">
        <h4 class="color-red text-align weight-bolder size-14" style="color: #a97b52!important;">PROFIT AND LOSS</h4>
        <hr class="hr-title" style="margin: 20px 48%!important;"/>
        <h4 class="text-align size-8 color-hs">操作流程</h4>
        <img src="img/web/pic_pro_ry.png" />
    </div>
</div>
<div class="wc" style="display: none;height: auto!important;">
    <div class="qhan"><!--期货案例-->
        <div class="yzfn">
            <h4 class="color-white size-15 fl clear">优质方案</h4>
            <h4 style="margin: 30px 0;" class="color-white size-15 fl clear">YOUZHIFANGAN</h4>
            <img class="clear fl" src="img/web/icon_case.png" />
        </div>
        <div class="cdsm">
            <h4 class="size-15 fl clear" style="color: white!important;">成单说明</h4>
            <h4 class="size-10 fl clear" style="color: white!important;line-height: 27px;">该用户看准600569招商证券，想买入30万市值，可手头上没有这么多钱。于是申请了“金点护航”，按这只股票的费率，客户申请30万市值的招商证券，只需要交纳管理费18052元，无需交纳其他费用。</h4>
        </div>
        <div class="clear pajc">
            <h4 class="size-15 fl clear" style="color: white!important;">平仓结案</h4>
            <h4 class="size-10 fl clear" style="color: white!important;line-height: 27px;">客户与8月1日建仓，8月11日要求平仓，十天时间，盈利了121292.3元，重要的是，客户全程只花费了18052元的管理费。盈利率高达671.9%，试问有什么投顾在一个月内可以达到这么高的盈利率？</h4>
        </div>
        <div class="clear table" style="background: rgba(169,123,82,.4)">
            <h4 class="table-tilte"><span>金点护航</span><span class="fr">OP100232</span></h4>
            <table>
                <tr>
                    <th class="color-white size-9">股票名称</th>
                    <th class="color-white size-9">招商证券</th>
                    <th class="color-white size-9">股票代码</th>
                    <th class="color-white size-9">600999</th>
                </tr>
                <tr>
                    <td>初期方案市值</td>
                    <td>10000000元</td>
                    <td>管理状态</td>
                    <td>已缴纳</td>
                </tr>
                <tr>
                    <td>买入价格</td>
                    <td>17.8元</td>
                    <td>卖出价格</td>
                    <td>20.8元</td>
                </tr>
                <tr>
                    <td>建仓日期</td>
                    <td>2017-08-25</td>
                    <td>方案结束日期</td>
                    <td>2018-09-25</td>
                </tr>
                <tr>
                    <td>方案管理费</td>
                    <td>65258元</td>
                    <td>结案申请日期</td>
                    <td>2018-09-13</td>
                </tr>
                <tr>
                    <td>结案成功日期</td>
                    <td>2018-09-6</td>
                    <td>建议终止日期</td>
                    <td>2017-09-14</td>
                </tr>
            </table>
            <div class="right-div" style="background: rgba(169,123,82,.6); opacity: 1;">
                <h4 class="table-tilte text-align">已结案</h4>
                <div>
                    <h5 class="text-align color-white">结案统计日期</h5>
                    <h5 class="text-align color-white">2018-09-26</h5>
                </div>
                <div>
                    <h5 class="text-align color-white">投顾盈利</h5>
                    <h5 class="text-align color-white">168539.326元</h5>
                </div>
                <div>
                    <h5 class="text-align color-white">方法盈利</h5>
                    <h5 class="text-align color-white">103281.326元</h5>
                </div>


            </div>
        </div>
    </div>
    <div>
        <img style="min-width: 1280px;" src="img/web/img_achievement_ry.jpg" width="100%" />
    </div>
</div>

<div class="wc" style="display: none;">
    <div class="cptd-all"><!--产品特点-->
        <div class="td-ys">
            <h4 class="color-white size-15 fl clear">特点和优势</h4>
            <h4 style="margin: 30px 0;" class="color-white size-15 fl clear">YOUZHIFANGAN</h4>
            <img class="clear fl" src="img/web/icon_feature.png" />
        </div>
        <div class="cptd2">
            <h4 class="size-15 fl clear" style="color: white!important;">产品特点</h4>
            <h4 class="size-10 fl clear" style="color: white!important;line-height: 27px;">1.全程只收取一次管理费，不收取保证金、手续费、方案期限灵活（1周、2周、1个月、两个月，甚至半年至一年）。<br />
                2.如果股票下跌，无需追加保证金补仓，无强制平仓操作，不承担亏损。</h4>
        </div>
        <div class="clear cpys">
            <h4 class="size-15 fl clear" style="color: white!important;">产品优势</h4>
            <h4 class="size-10 fl clear" style="color: white!important;line-height: 27px;">1.互联网首家也是唯一一家为专业资产管理机构推荐输送优秀投资管理人才的服务平台。<br />
                2.方案入门门槛低（最低1万市值起），而一般券商则必须500万起，且只能做场内50ETF指数，产品单一。<br />
                3.涵盖市场上主板、中小板、创业板等所有股票。</h4>
        </div>
    </div>
</div>
<div class="wc" style="height: auto!important;display: none;">
    <img src="img/web/bg_diffrence_ry.jpg" width="100%" />
</div>
<%--<div class="footer">--%>
    <%--<h4>版权所有 2017 金点护航 ICP备********号</h4>--%>
    <%--<h4>风险提示：保证金类交易时存在较大的亏损风险，这些产品不一定适合每一位投资者，请确保您完全了解所涉及的风险</h4>--%>
    <%--<h4>本站内容仅供参考，非操作建议，请您自觉决策交易。</h4>--%>
    <%--<img style="margin-top: 20px;" src="img/web/logo.png" />--%>
<%--</div>--%>
<div id="ceng" style="position:absolute;z-index:2;left:0;top:0;right:0;background-color:RGBA(0,0,0,0.8);filter:alpha(opacity=50);margin:1px 1px;display:none;width:100%;height:100%;text-align:center;">
</div>
<div id="close" style="position:absolute !important;left:30%;top:0px;z-index:3;background-color:#fff;margin:200px auto;padding:0px;display:none;height:300px;text-align:right">
    <a href="javascript:void(0);" onclick="closeCeng()">关闭</a>
    <%--<div id="video_area" style="text-align:center;"><br>--%>
        <div class="video">
            <video  controls="controls" id="video" src="">
                &nbsp;</video>
        </div>
    <%--</div>--%>
</div>
<%@include file="footer.jsp"%>
<script type="text/javascript">
    var wH = $(window).height(); //获取屏幕高度
    $(".product_list li").click(function(){
        $(".product_list li").removeClass('product_list_li');
        $(this).addClass('product_list_li');
        $t = $(this).index();
        $(".wc").hide();
        $(".wc").eq($t).show();
    });
    $('#menu li a').click(function () {
        var f = this;
        $('#menu li a').each(function () {
            this.className = this == f ? 'active' : 'none'
        });
    });
</script>
<script>
    var pageSize = "8";//每页行数
    var currentPage =$("#currentPage").val().length==0?1:$("#currentPage").val();//当前页
    var totalPage = $("#totalPage").val();//总页数
    var totalCount =$("#totalCount").val();//总条数
    var params="";//参数
    var url="web/video/list";//action
    $(document).ready(function(){//jquery代码随着document加载完毕而加载
        queryForPages(currentPage);
    });
    //分页查询
    function queryForPages(currentPage)
    {
        $.ajax({
            url:"web/video/list",
            type:'post',
            dataType:'json',
            data:"pageNum="+currentPage+"&pageSize="+pageSize+params,
            success:function callbackFun(data)
            {
                //解析json
                var info = eval(data.result);
                //清空数据
//                    clearDate();
                $("#t_body").empty();
                fillTable(info);
                $("#currentPage").val(data.pageNum);
                $("#totalPage").val(data.totalPage);
                $("#totalCount").val(data.totalCount);
            }
        });
    }
    //填充数据
    function fillTable(info)
    {
        if(info.length>1)
        {
//                totalPage=info[info.length-1].totalPage;
            var tbody_content="";//不初始化字符串"",会默认"undefined"
            for(var i=0;i<info.length;i++)
            {
                tbody_content +=" <div class=\"fl\"><a onclick='Ceng(\""+info[i].linkUrl+"\")'>" +
                    "                <img  style=\"width: 100%;\" src=\""+info[i].fileUrl+"\" />\n" +
                    "                <h4>"+info[i].title+"</h4>\n" +
                    "            </a>\n" +
                    "        </div>"
                $("#t_body").html(tbody_content);
            }
        }
        else
        {
            $("#page_area").html("");
            $("#t_head").html("");
            $("#t_body").html("<div style='height: 200px;width: 100%;padding-top: 100px;' align='center'>"+"暂无数据"+"</div>");
        }
    }
    //清空数据
    function clearDate()
    {
        $("#t_body").html("");
    }
    //搜索活动
    $("#searchActivity").click(function(){
        queryForPages();
    });
    //首页
    $("#firstPage").click(function(){
        currentPage="1";
        queryForPages();
    });
    //上一页
    $("#previous").click(function(){
        currentPage = $("#currentPage").val();
        if(currentPage>1)
        {
            currentPage-- ;
            queryForPages();
        }else {
            console.log("没有上一页");
        }
    });
    //下一页
    $("#next").click(function(){
        currentPage = $("#currentPage").val();
        totalPage = $("#totalPage").val();
        if(currentPage<totalPage)
        {
            currentPage++ ;
            queryForPages();
        }else {
            console.log("没有下一页");
        }
    });
    //尾页
    $("#last").click(function(){
        currentPage = $("#currentPage").val();
        totalPage = $("#totalPage").val();
        currentPage = totalPage;
        queryForPages();
    });
</script>
<script type="text/javascript">
    function Ceng(url) {
        document.getElementById('ceng').style.display = 'block';
        document.getElementById('close').style.display = 'block';
        document.getElementById("ceng").style.height = document.body.scrollHeight+"px";
        document.getElementById("ceng").style.width = document.body.scrollWidth+"px";
        document.getElementById("video").src=url;
        $('#video')[0].play();
        return false;
    }
    function closeCeng() {
        document.getElementById('ceng').style.display = 'none';
        document.getElementById('close').style.display = 'none';
        $('#video')[0].pause();
    }
</script>
<script type="text/javascript" src="js/web/paging.js"></script>
<script>
    //分页
    $("#page").paging({
        pageNo:$("#currentPage").val().length==0?currentPage:$("#currentPage").val(),
        totalPage: $("#totalPage").val().length==0?totalPage:$("#totalPage").val(),
        totalSize: $("#totalCount").val().length==0?totalCount:$("#totalCount").val(),
        callback: function(num) {
            queryForPages(num);
        }
    });

    function queryStockMangeFee(symbol, cycle){
        $.ajax({
            url: 'web/stock/list/json',
            type: 'GET', //GET
            async: true,    //或false,是否异步
            data: {"Symbols": symbol, "Cycle": cycle, "Min_option_price": null, "Max_option_price": null},
            timeout: 10000,    //超时时间
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (data, textStatus, jqXHR) {
                //应当只返回一条数据
                var stockData = data[0];
                //重新显示手续费并计算
                var manageFee = stockData.manageFee;
                $(".manageFee").html(manageFee+"%");
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
        $(".ljcx").on("click", function(e) {
            var symbol = $("#symbol").val();
            var cycle = $("#cycle").val();
            queryStockMangeFee(symbol, cycle);
        })
    });
</script>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.js"></script>
<script>
    $(function () {
        //{"cycle":"1m","exerciseMode":"A","expireDate":"1509292800000","insureDate":"1506614400000","offerPrice":"4.97","offerPriceDate":"1506614400000","optionCode":"000001-C-A-20171030-100","optionType":"C","priceMode":"100","symbol":"000001","symbolName":"平安银行"}
        var data = ${dataList};
        var availableTags = [];
        $(data).each(function(i, o){
            var autocompleteData = {
                "value": o.symbol,
                "label": o.symbol + "/" + o.symbolName
            };
            availableTags.push(autocompleteData);
        });

        $("#symbol").autocomplete({
            minLength: 0,
            source: availableTags,
            focus: function (event, ui) {
                $("#symbol").val(ui.item.value);
                return false;
            },
            select: function (event, ui) {
                $("#symbol").val(ui.item.value);
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
</body>
</html>

