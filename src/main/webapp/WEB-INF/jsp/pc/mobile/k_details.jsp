<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    #k_details {
        background: black;
    }

    #k_details .ui-content {
        padding: 0;
    }

    #k_details .ui-content h4 {
        margin: 0;
        overflow: hidden;
        background: rgba(255, 255, 255, .1);
    }

    #k_details .h4_span {
        width: 50%;
        padding: .7em 0;
    }

    #k_details .ygj {
        display: block;
        margin-bottom: -13px;
        margin-top: 3px;
    }

    #k_details .ui-content h5 {
        overflow: hidden;
        text-align: center;
        color: rgba(255, 255, 255, .7);
        border: 1px solid #c33f00 !important;
        border-radius: 20px;
        width: 140px;
        height: 18px;
        line-height: 18px;
        margin: 0 auto;
        padding: 4px 6px;
        margin-top: 1.5em;
    }

    #k_details .ui-content h5 span {
        width: 50%;
        text-align: center;
    }

    #k_details .h5_span {
        background: #c33f00 !important;
        border-radius: 20px;
    }

    #k_details .details-footer {
        overflow: hidden;
        clear: both;
        font-size: 1.5em;
        position: fixed;
        bottom: 0;
        width: 100%;
    }

    #k_details .details-footer h6 {
        width: 33.3%;
        margin: 0;
        text-align: center;
        height: 2.5em;
        padding: 0.6em 0;
        font-size: 14px;
        line-height: 2.5em;
    }

    #k_details .details-footer h6:nth-child(1) {
        background: rgba(255, 255, 255, .1);
    }

    #k_details .details-footer h6:nth-child(2) {
        background: #c33f00 !important;
        color: white;
        width: 66.7% !important;
        line-height: 2.5em;
    }
</style>
<div data-role="page" id="k_details">
    <div data-role="header" style="border-width: 0; background: #28292E;">
        <a href="mobile/option/labels" data-ajax="false" class="ui-link"><img src="img/mobile/icon_nav_back.png"/></a>
        <h4 class="color-w">股票详情</h4>
    </div>
    <div data-role="content">
        <input type="hidden" id="symbol_k">
        <h4>
					<span class="fl h4_span text-align">
						<span class="color-green size-14 curPrice"></span><br/>
						<span class="color-w size-9 stockInfo"></span>
					</span>
            <span class="fl h4_span text-align">
						<!--<span class="color-w ygj">预估价：<span class="color-green"></span></span>--><br/>
						<span class="color-w">涨跌幅：<span class="color-green  priceRate_details"></span></span>
					</span>
        </h4>
        <h5>
            <span onclick="type_choose(this,'zs')" class="fl h5_span fs_k" >分时图</span>
            <span onclick="type_choose(this,'rx')" class="fl">日线图</span>
        </h5>
      <div style=" backgroundColor: '#21202D';width: 100%;position: absolute;height: 67%;z-index: 99999;">
            <%@include file="/WEB-INF/jsp/pc/mobile/hq_details.jsp" %>
            <%@include file="/WEB-INF/jsp/pc/mobile/zs_details.jsp" %>
        </div>
        <div class="details-footer">
            <h6 class="fl zx"><a data-ajax="false" class="color-w" href="mobile/stock/plan/list?orderStatus=1" style="display:inline-block;width:100%;height:100%;">持仓</a>
            </h6>
            <h6 class="fl kd"><a data-ajax="false" class="color-w create_plan" style="display:inline-block;width:100%;height:100%;" href="http://baidu.com">申请方案</a></h6>
        </div>
        <input type="hidden" class="zx-state" value="0"/>
        <input type="hidden" id="manage_fee" value="${manageFee.itemCode}"/>
        <input type="hidden" id="symbol"/>
        <%--by hexin 【申请方案】增加股票最新价格参数--%>
        <input type="hidden" id="curPrice"/>
    </div>
</div>
<script>
    function type_choose(obj,type) {
        $(obj).parent().find('span').removeClass('h5_span');
        $(obj).addClass('h5_span');

        if(type == 'zs'){
            $('#k_details').find('#zs_line').show();
            $('#k_details').find('#main').hide();

           // var ticker_symbol=utils.getParam("ticker_symbol");
            var ticker_symbol= $("#k_details #symbol_k").val();

            var symbol=ticker_symbol;

            if ("60" == ticker_symbol.substring(0, 2)) {
                symbol =ticker_symbol+".SH";
            } else {
                symbol = ticker_symbol+".SZ";
            }

            loadZSData(symbol);
            setInterval(function(){

                    var ticker_symbol= $("#k_details #symbol_k").val();

                    var symbol=ticker_symbol;

                    if ("60" == ticker_symbol.substring(0, 2)) {
                        symbol =ticker_symbol+".SH";
                    } else {
                        symbol = ticker_symbol+".SZ";
                    }
                    loadZSData(symbol);},
                30000);
        }

        if(type == 'rx'){
            $('#k_details').find('#zs_line').hide();
            $('#k_details').find('#main').show();

            //var ticker_symbol=utils.getParam("ticker_symbol");
            var ticker_symbol= $("#k_details #symbol_k").val();
            var symbol=ticker_symbol;

            if ("60" == ticker_symbol.substring(0, 2)) {
                symbol =ticker_symbol+".SH";
            } else {
                symbol = ticker_symbol+".SZ";
            }
            toGetkline(symbol);
            setInterval(function(){
                    toGetkline(symbol)},
                30000);
        }

    }


    $(document).on("pageshow", "#k_details", function () { // 当进入页面二时
        $("#k_details .curPrice").click();


       $("#k_details .fs_k").click();


    });


    function  getSymbol(symbol) {

    }
</script>

