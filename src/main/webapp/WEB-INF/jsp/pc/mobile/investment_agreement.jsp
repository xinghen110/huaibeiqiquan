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
        <h4 class="color-w">投顾协议</h4>
    </div>
    <div data-role = "content" style="color: #FFFFFF;margin-left: 20px;margin-right: 20px">
        <p style="margin-bottom:0;text-align:center;vertical-align:baseline">
            <strong><span style="font-family: 宋体;font-size: 22px"><span style="font-family:宋体">网站投资顾问合作协议</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">本合作协议（下称</span>“本协议”）由以下各方于______年___月___日签署：</span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <strong><span style="font-family: 宋体;font-size: 14px"><span style="font-family:宋体">甲方（出资方）：镇江满茂商贸有限公司</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">联系电话：</span></span><span style=";font-family:宋体;font-size:14px">4001808871</span><span style=";font-family:宋体;font-size:14px"><br/></span><span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">联系方式：镇江市润州区区黄山路66号B座四层452室</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <strong><span style="font-family: 宋体;font-size: 14px"><span style="font-family:宋体">乙方（投资顾问）：</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">身份证号：</span></span><span style=";font-family:宋体;font-size:14px"><br/></span><span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">联系方式：</span></span><span style=";font-family:宋体;font-size:14px"><br/></span><span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">金点护航用户名：</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <strong><span style="font-family: 宋体;font-size: 14px"><span style="font-family:宋体">丙方（居间人）：镇江满茂商贸有限公司</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">联系电话：</span></span><span style=";font-family:宋体;font-size:14px">4001808871</span><span style=";font-family:宋体;font-size:14px"><br/></span><span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">联系方式：镇江市润州区区黄山路66号B座四层452室</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline">
            <strong><span style="font-family: 宋体;font-size: 18px"><span style="font-family:宋体">第一条</span> <span style="font-family:宋体">总则</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <strong><span style="font-family: inherit;font-size: 14px"><span style="font-family:宋体">鉴于：</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">1. <span style="font-family:宋体">甲方授权丙方按照甲方制定的标准，发掘并推荐为甲方在证券二级市场进行投资提供资产管理建议（以下称资产管理建议）的投资顾问；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">2. <span style="font-family:宋体">乙方已在金点护航网站注册，并承诺其提供给丙方的信息是完全真实有效的。乙方作为中华人民共和国境内具有完全民事行为能力的自然人，在充分了解证券二级市场风险和责任的前提下，通过丙方居间推荐成为为甲方提供资产管理建议的投资顾问；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">3. <span style="font-family:宋体">丙方是一家在</span></span><span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">上海</span></span><span style=";font-family:inherit;font-size:14px"><span style="font-family:宋体">市合法成立并有效存续的有限责任公司，系金点护航网站（下称</span>“<span style="font-family:宋体">金点护航</span><span style="font-family:inherit">”</span><span style="font-family:宋体">）的运营管理人，并通过金点护航网站平台为甲方、乙方提供信息交互、撮合、等中介服务</span></span><span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px">4.<span style="font-family:宋体">乙方承诺，其具备在证券二级市场进行投资并提供资产管理建议的专业能力，同时其具备符合国家法律法规规定的投资顾问的相应资质，并已取得证券从业资格、证券投资咨询资格等相应证书；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px">5.<span style="font-family:宋体">丙方承诺，其具备符合国家法律规定的为甲方和乙方提供居间服务的相应资质。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <strong><span style="font-family: inherit;font-size: 14px"><span style="font-family:宋体">据此，各方经协商一致，达成如下协议，以资共同遵照履行：</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline">
            <strong><span style="font-family: 宋体;font-size: 18px"><span style="font-family:宋体">第二条</span> <span style="font-family:宋体">合作方式</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">1.<span style="font-family:宋体">甲方委托乙方担任其</span></span><span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">个股期权投资</span></span><span style=";font-family:inherit;font-size:14px"><span style="font-family:宋体">方案的投资顾问，就甲方管理范围内</span>_______<span style="font-family:宋体">元资产提供资产管理建议，在不违反本协议约定的风控规则的前提下，甲方将依据乙方指示的投资建议进行资产管理操作；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">2.<span style="font-family:宋体">乙方提供建议投资的股票标的、投资规模以及买卖价格通过丙方的网站向甲方提出投资建议，甲方有权决定是否接受乙方的投资建议并通过丙方网站向乙方展示评估结果；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">3.<span style="font-family:宋体">如甲方接受乙方的投资建议，将按照乙方所提供的投资建议作为参考并进行投资，双方约定的合作周期为</span><span style="font-family:inherit">___</span><span style="font-family:宋体">年</span><span style="font-family:inherit">___</span><span style="font-family:宋体">月</span><span style="font-family:inherit">___</span><span style="font-family:宋体">日至</span><span style="font-family:inherit">___</span><span style="font-family:宋体">年</span><span style="font-family:inherit">___</span><span style="font-family:宋体">月</span><span style="font-family:inherit">___</span><span style="font-family:宋体">日</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">4.<span style="font-family:宋体">当方案终止时，甲方所管理的期末资产净值高于期初资产净值时，</span></span><span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">甲乙双方按协商比例自行进行利润分配</span></span><span style=";font-family:inherit;font-size:14px"><span style="font-family:宋体">；如甲方所管理的期末资产净值低于期初资产净值时，投资产生的亏损由甲方独立承担；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">5.<span style="font-family:宋体">丙方依据其向甲方所提供投资顾问推荐服务向乙方收取居间服务费用，即：在本合同生效时向乙方收取居间服务费用</span><span style="font-family:inherit">___</span><span style="font-family:宋体">元</span><span style="font-family:inherit">/</span><span style="font-family:宋体">每个方案的居间服务费用；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">6.<span style="font-family:宋体">甲方和乙方一致同意委托丙方代为收取及支付本协议中因甲乙双方合作行为而产生的所有相关费用，并一致同意接受丙方就本协议内容所进行的监管。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline">
            <strong><span style="font-family: 宋体;font-size: 18px"><span style="font-family:宋体">第三条</span> <span style="font-family:宋体">投资建议的说明</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">1.<span style="font-family:宋体">乙方建议甲方投资的股票原则上应业绩优良、流通股本规模适度、流动性好。乙方的投资建议方案在甲方评估后认为存在风险，甲方有权拒绝乙方所提供的投资建议：</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">2.<span style="font-family:宋体">乙方发起的投资建议由甲方评估后，结果通过丙方网站向乙方展示，乙方需及时关注丙方网站通知，丙方不再通过其他方式通知，如因乙方未能及时查看网站通知而造成的损失，皆由乙方独立承担；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">3.<span style="font-family:宋体">甲方接受乙方提供的投资建议后，在第二个交易日执行乙方的投资建议方案，原则上按照乙方建议的投资标的和买卖价格操作，如乙方建议按照市价交易，甲方有权选择以能够成交的任意价格买卖股票并通过丙方网站向乙方展示方案执行结果。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">4.<span style="font-family:宋体">乙方若在方案执行周期内提出方案结算，需向甲方提供股票卖出价格的投资建议</span><span style="font-family:inherit">:</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">1<span style="font-family:宋体">）如甲方执行乙方提供的建议方案成功，则按照本协议第二条第</span><span style="font-family:inherit">4</span><span style="font-family:宋体">点约定方式进行结算分配；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">2<span style="font-family:宋体">）如甲方执行乙方的提供的结算建议方案无法成功，则乙方需在方案执行周期内再次提出结算建议方案，直至乙方提供的结算建议方案由甲方执行成功；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">3<span style="font-family:宋体">）如乙方在周期方案结束前两个交易日依然未提供结算建议方案，甲方有权按市价交易的方式择价卖出股票，并按照协议第二条第</span><span style="font-family:inherit">4</span><span style="font-family:宋体">点约定的方式进行结算分配；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">5.<span style="font-family:宋体">如在方案执行期间乙方投资建议的股票出现停牌不能交易</span><span style="font-family:inherit">:</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">1<span style="font-family:宋体">）股票在停牌期间投资顾问发起的投顾建议甲方有权一律视为无效建议并不予采纳；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">2<span style="font-family:宋体">）股票停牌期间，乙方将无法提出结算。若在方案执行期间股票已复牌，乙方则可继续进行投资建议；若方案到期结束而股票依然未复牌，甲方有权按方案结束前的最后一个交易日收盘价予以结算；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">6.<span style="font-family:宋体">如在方案周期内乙方投资建议的股票出现转送、分红等除权除息（配股除外）情况，平台计算的股票价格以复权价计算收益；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">7.<span style="font-family:宋体">如在方案周期内乙方投资建议的股票出现配股情况，投资方在配股发生时不参与认购，若投资顾问建议仍继续持有，该情况所导致合作方案收益减小或亏损乙方表示无异议；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline">
            <strong><span style="font-family: 宋体;font-size: 18px"><span style="font-family:宋体">第四条</span> <span style="font-family:宋体">居间服务费及担保范围</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">1.<span style="font-family:宋体">乙方应根据本协议约定在提交建议方案时支付方案管理费到丙方指定账户作为居间服务费用，甲方评估投资建议时，居间服务费暂时冻结，待甲方确认接受投资建议则居间服务费用由丙方收取；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">2.<span style="font-family:宋体">乙方的居间服务费用担保范围包括但不限于：滞纳金、违约金、损害赔偿金、诉讼费、律师费、调查费、公证费等甲方为进行本协议中涉及的资产管理计划的实施所发生的费用；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">3.<span style="font-family:宋体">乙方授权丙方，如乙方未能履行本协议约定的任何义务，丙方无论是否事先通知乙方，均可通过甲方直接从乙方的建议方案中划扣相应收益用于支付乙方应付欠款。本授权委托在本协议法定诉讼时效内不可撤销；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline">
            <strong><span style="font-family: 宋体;font-size: 18px"><span style="font-family:宋体">第五条</span> <span style="font-family:宋体">结算说明</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">1.<span style="font-family:宋体">本协议终止前，乙方须按本协议规定时间将相关费用支付到丙方指定的银行账户；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">2.<span style="font-family:宋体">本协议终止时，甲方将授权丙方将其中高于期初净值的部分依照第二条第</span><span style="font-family:inherit">4</span><span style="font-family:宋体">点完成结算；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">3.<span style="font-family:宋体">当乙方需要在丙方平台账号中进行现金提出时，需通过丙方向甲方发出申请。经甲方核对后，如不存在异议，甲方将同意乙方的提现申请。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline">
            <strong><span style="font-family: 宋体;font-size: 18px"><span style="font-family:宋体">第六条</span> <span style="font-family:宋体">甲方的权利、义务及违约责任</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">1.<span style="font-family:宋体">甲方保证资金来源合法，甲方是该资金的合法权利人，如果本协议范围外的任意方对资金归属、合法性问题发生争议，由甲方负责解决，与其他方无涉。如甲方未能解决，则协议马上进入终止清算，乙方及丙方不受连带责任；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">2.<span style="font-family:宋体">甲方享有根据乙方建议的资产管理方案带来的应得收益，并应主动缴纳由此所得带来的可能的税费及其他相应成本；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">3.<span style="font-family:宋体">甲方有权对乙方提供的投资建议是否采纳拥有最终决定权，评估完成并确认接受乙方的投资建议则视为同意且有义务遵守本协议中的相关约定</span></span><span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px">4<span style="font-family:宋体">．甲方知晓证券市场相应的投资风险，乙方向甲方给出的投资建议仅供甲方参考，并不保证必然收益，可能出现的亏损风险由甲方个人承担。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline">
            <strong><span style="font-family: 宋体;font-size: 18px"><span style="font-family:宋体">第七条</span> <span style="font-family:宋体">乙方的权利、义务及违约责任</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">1.<span style="font-family:宋体">乙方有权根据本协议约定建议甲方在本资管方案中进行股票交易投资，有权根据第二条第</span><span style="font-family:inherit">4</span><span style="font-family:宋体">点向甲方收取本资管方案投资所得的收益；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">2.<span style="font-family:宋体">乙方不得建议进行坐庄、对敲、接盘、大宗交易、内幕信息等违反股票交易法律法规及证券公司规定的交易，如有任一次上述交易行为，应支付给甲方资管方案所涉及资产总额</span><span style="font-family:inherit">10%</span><span style="font-family:宋体">的违约金，因此产生的一切责任</span></span><span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">（包括但不限于民事责任、刑事责任、行政责任等）</span></span><span style=";font-family:inherit;font-size:14px"><span style="font-family:宋体">和损失由乙方承担；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">3.<span style="font-family:宋体">如因乙方导致甲方账户（包括银行账户和股票、期货账户）被依法采取查封、冻结、划拨等强制措施，造成甲方损失的，乙方应从账户被依法处置之日起以本资管项目涉及资产总额的</span><span style="font-family:inherit">1‰</span><span style="font-family:宋体">为日利率向甲方支付违约金，如违约金不足以弥补损失的，乙方还应承担赔偿责任。合作结束，账户仍未解除强制措施的，乙方应另行全额归还给甲方本资管项目涉及总资产额并按约定支付违约金；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px">4<span style="font-family:宋体">、乙方承诺，其具备在证券市场进行投资并提供资产管理建议的专业能力，同时其具备符合国家法律法规规定的投资顾问的相应资质，并已取得证券从业资格、证券投资咨询资格等相应资格证书；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline">
            <strong><span style="font-family: 宋体;font-size: 18px"><span style="font-family:宋体">第八条</span> <span style="font-family:宋体">丙方的权利、义务</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">1.<span style="font-family:宋体">丙方有权就为本协议所提供的服务向</span></span><span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">乙</span></span><span style=";font-family:inherit;font-size:14px"><span style="font-family:宋体">方收取居间服务费用。居间服务费用参照本协议中第二条第</span>5<span style="font-family:宋体">点执行；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">2.<span style="font-family:宋体">丙方作为甲乙双方订约的居间方，丙方不对甲乙双方的履约承担任何实质责任</span></span><span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">，甲乙双方之间相关的权利义务由甲乙双方各自承担，与丙方无关</span></span><span style=";font-family:inherit;font-size:14px"><span style="font-family:宋体">；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">3.<span style="font-family:宋体">丙方应对甲方和乙方的信息及本协议内容保密；</span></span><span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">除</span></span><span style=";font-family:inherit;font-size:14px"><span style="font-family:宋体">因相关权力部门要求（包括但不限于法院、仲裁机构、金融监管机构等），丙方有权披露</span></span><span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px">4.<span style="font-family:宋体">丙方应具备符合国家法律规定的为甲方和乙方提供居间服务的相应资质，并应同时具备相应的资金监管资质。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline">
            <strong><span style="font-family: 宋体;font-size: 18px"><span style="font-family:宋体">第九条</span> <span style="font-family:宋体">风险揭示</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">1.<span style="font-family:宋体">甲方在签署本合同前须履行风险提示义务；乙方应在签署本合同前须详细阅读本协议内容及相关风险提示，以便正确、全面了解投资顾问合作的风险。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">2.<span style="font-family:宋体">技术风险揭示</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">1<span style="font-family:宋体">）互联网是开放性的公众网络，网上委托方式共同的风险外，还有其特有的风险，如由于互联网数据传输等原因，交易指令可能会出现中断、停顿、延迟、数据错误等情况；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">2<span style="font-family:宋体">）互联网存在黑客恶意攻击的可能性，亦存在病毒入侵的可能性，互联网服务器可能会出现故障及其他不可预测的因素；或由于乙方不慎将信息泄露或被他人盗用，均存在乙方投资建议权限被滥用的风险；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">3<span style="font-family:宋体">）乙方的电脑设备及软件系统与所提供的网上交易系统不相匹配，导致无法下达建议委托指令或建议委托失败；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">4<span style="font-family:宋体">）丙方为实现甲乙双方合作所提供下载使用的软件，属于免费性质，丙方不承担软件使用可能造成的风险，如因软件问题造成交易影响，丙方不承担因此产生的任何责任；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">5<span style="font-family:宋体">）由于政策或者市场原因，需取消乙方投资顾问权限，甲方将通知乙方在规定时间内行使投资建议权（仅限于在期限内将本资管方案中涉及的资产变现）；若在规定时间内乙方未行使投资建议权，则视为放弃履行投资顾问的权利与义务，甲方依照本协议规定清算后终止该协议；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">6<span style="font-family:宋体">）其他不可抗力风险；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">3.<span style="font-family:宋体">规避风险建议</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">1<span style="font-family:宋体">）乙方务必注意个人信息资料的保密，账户密码妥善保存，如意识到密码可能泄露，请及时修改密码；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">2<span style="font-family:宋体">）乙方用于投资建议委托的电脑必须安装可信赖的病毒防火墙和网络防火墙软件，并确认其正常工作；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">3<span style="font-family:宋体">）乙方如遇个人电脑系统、网络通讯或相关软件出现异常情况，应暂时停止网上投资建议委托。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">4.<span style="font-family:宋体">特别提示</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px"><span style="font-family:宋体">根据证监会颁布的《证券业从业人员执业行为准则》规定，乙方应具备获得证券从业人员的资质，若乙方存在刻意隐瞒或欺骗行为，甲方有权取消乙方资管方案投资顾问资格，因此而产生的其他后果由乙方自行承担；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px"><span style="font-family:宋体">乙方签订本合同后，表明乙方已完全了解网上业务可能存在的相应风险，以及自身决策失误造成的损失，并承诺自行承担。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline">
            <strong><span style="font-family: 宋体;font-size: 18px"><span style="font-family:宋体">第十条</span> <span style="font-family:宋体">协议的订立、生效</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">1.<span style="font-family:宋体">甲乙双方同意并确认自行根据金点护航网站有关规则和说明在金点护航网站签署本协议；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">2.<span style="font-family:宋体">甲乙双方通过上述方式接受本协议且丙方审核通过时，本协议立即生效。协议到期时，经甲乙双方合议，如无异议，可延续本合同同等条件及合同期限；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">3.<span style="font-family:宋体">鉴于甲方可能会对本协议风控规则进行修订并在丙方网站发布，甲乙双方需应不时地注意甲方及具体规则的变更，若甲乙双方在本协议及具体规则内容公告变更后继续使用本服务的，表示甲乙双方已充分阅读、理解并接受修改后的协议和具体规则内容，也将遵循修改后的协议和具体规则使用本网站的服务；同时就甲乙双方在协议和具体规则修订前通过本网站进行的交易及其效力，视为您已同意并已按照本协议及有关规则进行了相应的授权和追认；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">4.<span style="font-family:宋体">在本协议履行过程中产生争议的，各方应当协商解决。协商不成的，任何一方向丙方所在地有管辖权的人民法院提起诉讼。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline">
            <strong><span style="font-family: 宋体;font-size: 18px"><span style="font-family:宋体">第十一条</span> <span style="font-family:宋体">协议终止</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">1.<span style="font-family:宋体">本协议生效之日起至合同到期时，如甲乙双方没有达成延续协议，则终止协议。协议终止时，甲方将根据本协议进行本资管方案结束清算；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">2.<span style="font-family:宋体">如因证券公司政策或不可抗力原因，导致资管方案无法正常继续进行，本协议自动终止，协议三方互不追究违约责任，按本协议约定进行结算；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:inherit;font-size:14px">3.<span style="font-family:宋体">乙方有严重违约行为的，甲方有权立即解除本协议，收回乙方投资顾问权限，并不退还乙方在以往业务中已经缴纳的管理费；</span></span>
        </p>
        <p>
            <span style=";font-family:Tahoma;font-size:15px">&nbsp;</span>
        </p>
        <p>
            <span style=";font-family:Tahoma;font-size:15px">&nbsp;</span>
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
