<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
	<link rel="stylesheet" href="css/mobile/jquery.mobile-1.4.2.css" />
	<script type="text/javascript" src="js/mobile/jquery.min.js" ></script>
	<script type="text/javascript" src="js/mobile/jquery.mobile-1.4.2.js" ></script>
	<title></title>
</head>
<body>
<div data-role="page" id="my_scheme_apply">
	<div data-role = "header" style="border-width: 0; background: #28292E;">
		<a data-role="button" data-rel="back" ><img src="img/mobile/icon_nav_back.png"/></a>
		<h4 class="color-w">规则中心</h4>
	</div>
	<div data-role = "content" style="color: #FFFFFF;margin-left: 20px;margin-right: 20px">
		<p style=";text-align:center;vertical-align:baseline;">
			<strong><span style="font-size: 13px; font-family: 宋体;">投顾规则须知</span></strong>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">1、 全程除一次支付协议中涉及的居间管理费用外，无其他任何费用，股市有风险，投资需谨慎！市场风险莫测，务请谨慎行事！</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">2、 方案的提交与结算：投资顾问建议投资方投资的股票原则上应业绩优良、流通股本规模适度、流动性好，提交投资建议方案后不可撤销，建议市值最高1000万元，最低100万元，建议市值需是10000的倍数，投资方接收到投资建议方案后将对投资建议方案进行评估并决定是否采纳投资顾问的投资建议方案；</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">a) 投资方接收并确认采纳投资顾问提供的投资建议方案后，将在下一交易日执行投资顾问的投资建议，原则上按照投资顾问建议的投资标的和买入价格操作，如投资顾问建议按照市价交易，投资方有权选择以能够成交的任一价格买入股票并通过委托方网站向投资顾问展示方案执行结果；</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">b) 因市场规则导致投资方执行投资顾问的结算申请无法成功，投资顾问需在方案管理期限内再次提出结算申请，直至投资顾问提供的结算申请执行成功；</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">c) 投资顾问从方案生效日起至方案终止前两个交易日，可择机向投资方提出结算申请。投资顾问的结算申请一经提交不得修改取消。投资方将按照投资顾问建议的卖出价格操作并通过委托方网站向投资顾问展示方案执行结果；</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">d) 若投资顾问在方案终止前一个交易日，依旧未提出结算申请，投资方将在方案终止当天择机处理股票标的，并按最终的实际成交价格进行结算。</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">e）若投资顾问在方案终止前一个交易日提出结算申请，投资方将在方案终止当天下午14:30前参照结算申请执行，如未能按照结算申请执行，则投资方将在14:30后择机处理股票标的，并按最终的实际成交价格进行结算。</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">3、 方案周期：以投资方采纳投资顾问提交的申请方案当日为方案生效日期，方案结束时间以系统生成的结束时间为准；</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">4、 支付管理费：投资顾问在申请方案时，一次性缴付该方案管理费，该方案管理期限内无需再缴付管理费。</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">5、 盈亏说明：方案终止时，投资顾问所申请执行建议方案的期末资产净值高于期初资产净值将视为盈利；若投资顾问所申请执行建议方案的期末资产净值低于期初资产净值时，投资产生的亏损由投资方自行承担。</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">6、 停牌处理：若投资顾问建议的股票在方案生效日至方案终止前两个交易日发生停牌：</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">a) 停牌期间，针对投资顾问发起的结算申请，投资方一律视其为无效申请不予采纳；</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">b) 若股票在方案终止前两个交易日复牌，投资顾问可发起结算申请；</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">c) 若股票在方案终止前两个交易日仍未复牌，在方案终止当天投资方将按照该股票停牌前最后一个交易日收盘价进行结算。</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">7、除权除息处理：</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">a) 若股票在方案周期内发生转送、分红等除权除息（配股除外）情况，平台计算的股票价格以复权价计算收益；</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">b) 若股票在方案周期内发生配股情况，投资方在配股发生将不参与认购，若投资顾问建议仍继续持有，该情况将导致合作方案收益减小或亏损，该风险需投资顾问自行识别并及时作出决策；</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">8、方案提交时间：</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">a）投资顾问方案建议、结算申请需在正常交易日当天00:00至下午17:00之间提交，17:00-00:00之间提交的方案建议、结算申请将视为下一个交易日提交的申请。 </span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">b）投资顾问于非交易日提交的方案建议将默认为下一交易日所提交申请。</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">c)投资顾问于方案终止日提交的结算申请为无效申请，投资方将不予采纳执行。</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">9、节假日处理：若投资顾问建议的股票方案终止日为节假日时（包括周末）：</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">a) 节假日期间，针对投资顾问发起的结算申请，投资方一律视其为无效建议不予采纳；</span>
		</p>
		<p style="vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">b) 方案终止日期自动顺延至节假日后第一个交易日。</span>
		</p>
		<p style="margin-left: 48px;vertical-align: baseline;line-height: 23px;">
			<span style=";font-family:宋体;font-size:12px">&nbsp;</span>
		</p>
		<p>
			<span style=";font-family:Calibri;font-size:14px">&nbsp;</span>
		</p>
		<p>
			<br/>
		</p>
	</div>
</div>
<script type="text/javascript">
    $(window).ready(function(){//每次加载动态获取table宽度
        var windowW = $(window).width();
        var i = 0;
        $('#my_scheme_apply table tr th').each(function(){
            i++;
        })
        var thW = windowW*0.25;//每个th的宽度
        $('#my_scheme_apply table th').css('width',windowW*0.25);//th宽度赋值
        $('#my_scheme_apply table td').css('width',windowW*0.25);//th宽度赋值
        $('#my_scheme_apply .two').css('margin-left',windowW*0.25)//设置第二个th的margin-left
        //计算总的table宽度
        var tableW = 0;
        if(i>4){
            tableW = thW * i+1;
        }else{
            tableW = thW * i;
        }
        $('#my_scheme_apply table').width(tableW);//table宽度赋值
    })
</script>
</body>
</html>
