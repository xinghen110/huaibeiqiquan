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
    <link rel="stylesheet" href="http://gg.spqun.com/css/mobile/mystyle.css" />
    <link rel="stylesheet" href="http://gg.spqun.com/css/mobile/jquery.mobile-1.4.2.css" />
    <script type="text/javascript" src="http://gg.spqun.com/js/mobile/jquery.min.js" ></script>
    <script type="text/javascript" src="http://gg.spqun.com/js/mobile/jquery.mobile-1.4.2.js" ></script> 
    <title></title>
</head>
<style> 
	table{width:98%;color:#fff;font-size:0.6em;border-collapse:collapse;border-spacing:1px;background:#28292E;}
    table td{background:#000;text-align:left;}

    #my_scheme_apply table tr td:first-child,#my_scheme_apply table tr td:nth-child(3){position:relative;background: #565656;   
    z-index: 9999;
    border-right: 1px solid #797979;
    border-bottom: 1px solid #797979;
    padding-left: 15px;}
    #my_scheme_apply table tr td{text-align: left;height: 2.8em;
    line-height: 2.8em;background:#2F2F2F;border-bottom: 1px solid #585858;}
   </style>
<body>
<div data-role="page" id="my_scheme_apply">
    <div data-role = "header" style="border-width: 0; background: #28292E;">
        <a data-role="button" data-rel="back" ><img src="img/mobile/icon_nav_back.png"/></a>
        <h4 class="color-w">投顾协议</h4>
    </div>
    <div data-role = "content" style="color: #FFFFFF;margin-left: 20px;margin-right: 20px">
        <p style="margin-bottom:0;text-align:center;vertical-align:baseline">
            <strong><span style="font-family: 宋体;font-size: 22px"><span style="font-family:宋体">股票收益互换交易确认书</span></span></strong>
        </p><br>
        <table>
            <tr>
              <td style="width:30%;">期权类型</td>
              <td>美式认购期权</td>  
              </tr><tr>
              <td>标的证券（代码）</td>
              <td>当前股票，标的资产代码：当前股票代码</td>            
           </tr>           
           <tr>
              <td>交易达成日</td>
              <td>下单日</td> 
             </tr><tr>
              <td>合约起始日</td>
              <td>下单日</td>             
           </tr>         
           <tr>
              <td>合约到期日</td>
             <td>下单日+周期日</td>
             </tr><tr>
              <td>合约终止日</td>
             <td>若期权合约行权，则为行权日；若未行权，则为合约到期日</td>             
           </tr>
           <tr>
              <td>行权期</td>
             <td>从合约起始日（不含）起至合约到期日（含）的交易日和营业日的共同日的标的交易时段,期权买入（T日）之后第 【T+5】个交易日（含）之后才能提出赎回申请.</td> 
             </tr><tr>
              <td>结算金额支付日</td> 
              <td>合约终止日后【2】个营业日内</td>           
           </tr>
           <tr>
              <td width="30%">交易货币</td>
              <td>人民币</td> 
             </tr><tr>
              <td width="30%">结算货币</td>
              <td>人民币</td>             
           </tr>
           <tr>
              <td>期初价格(以交易货币计价)</td>
              <td>市价交易以反馈价格为准；限价交易即所限价格</td> 
             </tr><tr>
              <td>行权价格（以交易货币计价)</td>
              <td>以行权价为准</td>             
           </tr>
          
                   
           <tr>
             <td>到期结算价格（以交易货币计价）</td>
              <td>以行权价为准</td>
             </tr><tr>
              <td>行权结算价格（以交易货币计价）</td> 
              <td>以行权价为准</td>
                          
           </tr>
           <tr>
             <td>合约名义本金额（以交易货币计价）</td> 
              <td>名义本金</td>
            </tr><tr>
             <td>期权费（以交易货币计价）</td> 
              <td>认购权利金</td>
                        
           </tr>
           <tr>
              <td>收益结算金额（以交易货币计价）</td>  
              <td>期权合约发生行权的，收益结算金额根据如下公式计算：每份合约的期权合约结算金额 = 合约名义本金额 ×（行权价格-期初价格）÷期初价格</td>
             </tr>
           
           <tr>
              <td colspan="4">*****此处填表格内项目说明******</td>             
           </tr>
           </table>
      <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <strong><span style="font-family: 宋体;font-size: 14px"><span style="font-family:宋体">甲方（期权发行人）：</span></span></strong>
        </p>
      <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <strong><span style="font-family: 宋体;font-size: 14px"><span style="font-family:宋体">乙方（受托方）</span></span></strong>
        </p>
      <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <strong><span style="font-family: 宋体;font-size: 14px"><span style="font-family:宋体">丙方（客户）</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <strong><span style="font-family: 宋体;font-size: 14px"><span style="font-family:宋体">甲方有权提前终止期权交易的情形</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">1、交易所对标的证券、构成标的证券的任一股票或股票指数有价格涨跌幅限制的，若其连续【2】个交易日收盘价格达到所规定的当日涨停价格时，甲方有权提前终止期权交易，并将上述第【2】个交易日作为该笔期权合约的到期日，以该日收盘价格作为到期结算价格，与乙方进行收益结算。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">2、交易所对标的证券、构成标的证券的任一股票或股票指数无价格涨跌幅限制的，若本交易确认书下某一笔交易对应的标的证券、构成标的证券的任一股票或指数收盘价格连续两个单日涨幅超过【10】%，甲方有权提前终止该笔交易，并将该日作为该笔期权合约的到期日，以该日其的收盘价格作为结算价格与乙方丙方进行收益结算。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <strong><span style="font-family: 宋体;font-size: 14px"><span style="font-family:宋体">乙方权利义务</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">乙方做为无资质购买期权丙方的受托人，只是负责受客户委托购买甲方发行的期权，并在交易中收取管理费，但不承担客户自主决定买卖产生的损失之责。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <strong><span style="font-family: 宋体;font-size: 14px"><span style="font-family:宋体">风险揭示书</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <strong><span style="font-family: 宋体;font-size: 14px"><span style="font-family:宋体">尊敬的投资者：</span></span></strong>
        </p>
         <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">投资有风险，在您/贵司争取获得投资收益的同时也面临着承担投资风险。您/贵司在做出投资决策之前，请仔细阅读本风险揭示书，充分认识投资风险，认真考虑各项风险因素，并充分考虑自身的风险承受能力，理性判断并谨慎做出投资决策。</span></span>
        </p>
         <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">在决定进行股票期权委托乙方交易之前，您应当充分了解以下事项：</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">一、 请您充分理解股票期权客户应当具备的经济能力、专业知识和投资经验，全面评估自身的经济承受能力、投资经历、产品认知能力、风险控制能力、身体及心理承受能力（仅对自然人客户而言）等，审慎决定是否参与股票期权业务。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">二、 请您了解应当了解期权的基础知识、相关法律、法规、规章、上海证券交易所（以下简称“交易所”）业务规则和各类公告信息、中国证券登记结算有限责任公司（以下简称“中国结算”）业务规则和各类公告信息以及期权经营机构的相关法律文件。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">三、 请您充分了解股票期权业务的风险特点。股票期权不同于股票交易业务，是具有杠杆性、跨期性、联动性、高风险性等特征的金融衍生工具。股票期权业务采用保证金交易方式，潜在损失可能成倍放大，损失的总额可能超过全部保证金。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">四、 股票期权业务实行客户适当性管理制度，客户应当满足中国证监会、证券交易所及证券公司关于客户适当性标准的规定。客户适当性制度对客户的各项要求以及依据制度规定对客户的综合评价结果，不构成对客户的投资建议，也不构成对客户的获利保证。您应根据自身判断做出投资决定，不得以不符合适当性标准为由拒绝承担股票期权交易结果。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">五、 请您知晓，参与机构对个人客户参与期权交易的权限进行分级管理。个人客户开立衍生品合约账户后，其期权交易权限将根据分级结果确定。期权经营机构可以根据相关规定自行调低个人客户的交易权限级别，个人客户只能根据调整后的交易权限参与期权交易。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">六、 请您知晓，在与公司的合同关系存续期间，如提供给公司的身份证明文件过期、身份信息发生变更的，应及时向公司提供新的相关材料。否则，公司有权拒绝客户开仓和出金指令，并有权进一步限制客户的交易权限。客户在开户时提供的其他信息发生变更时，也应及时向公司更新。如因客户未能及时提供更新信息而导致的后果、风险和损失由客户承担。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">七、 期权合约标的由交易所根据相关规则选择，并非由合约标的发行人自行决定。交易所及合约标的发行人对期权合约的上市、挂牌、合约条款以及期权市场表现不承担任何责任。期权的买方在行权交收前不享有作为合约标的持有人应当享有的权利。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">八、 在进行股票期权买入交易时，可选择将股票期权合约平仓、持有至到期行权或者任由期权合约到期但不行权；客户选择持有期权至到期行权的，应当确保其相应账户内有行权所需的足额合约标的或者资金。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">九、 卖出股票期权交易的风险一般高于买入股票期权交易的风险。卖方虽然能获得权利金，但也因承担行权履约义务而面临由于合约标的价格波动，可能承受远高于该笔权利金的损失。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">十、 在进行股票期权交易时，应关注合约标的价格波动、期权价格波动及其他市场风险及其可能造成的损失，包括但不限于以下情形：由于期权标的价格波动导致期权不具行权价值，期权买方将损失付出的所有权利金；期权卖方由于需承担行权履约义务，因合约标的价格波动导致的损失可能远大于其收取的权利金。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">十一、 您应当关注期权的涨跌幅限制，期权的涨跌幅限制的计算方式与现货涨跌幅计算方式不同，您应当关注期权合约的每日涨跌停价格。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">十二、 在进行股票期权交易时，请您关注当合约标的发生分红、派息、送股、公积金转增股本、配股、份额拆分或者合并等情况时，会对合约标的进行除权除息处理，交易所将对尚未到期的期权合约的合约单位、行权价格进行调整，合约的交易与结算事宜将按照调整后的合约条款进行。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">十三、 请您关注, 期权合约存续期间，合约标的停牌的，对应期权合约交易也停牌；当期权交易出现异常波动或者涉嫌违法违规等情形时，交易所可能对期权合约进行停牌。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">十四、 请您在进行股票期权交易时，应当严格遵守交易所相关业务规则、市场公告中有关限仓、限购、限开仓的规定，并在交易所要求时，在规定时间内及时报告。客户的持仓量超过规定限额的，将导致其面临被限制卖出开仓、买入开仓以及强行平仓的风险。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">十五、 请您关注期权合约可能难以或无法平仓的风险及其可能造成的损失，当市场交易量不足或者连续出现单边涨跌停价格时，期权合约持有者可能无法在市场上找到平仓机会。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">十六、 请您关注组合策略持仓不参加每日日终的持仓自动对冲，组合策略持仓存续期间，如遇1个以上成分合约已达到交易所及中国结算规定的自动解除触发日期，该组合策略于当日日终自动解除。除交易所规定的组合策略类型之外，客户不得对组合策略对应的成分合约持仓进行单边平仓</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">十七、 请您知悉交易所以结算参与人为单位，对结算参与人负责结算的衍生品合约账户的卖出开仓、买入开仓等申报进行前端控制。无论客户的保证金是否足额，如果结算参与人日间保证金余额小于卖出开仓申报对应的开仓保证金额度或者买入开仓申报对应的权利金额度的，相应卖出开仓或者买入开仓申报无效。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">十八、 请您知悉如使用合约标的除权、除息情形下因送股、转增等公司行为形成的无限售流通股作为备兑证券，如行权结算时尚未上市的，将不可用于行权交割，不足部分您须及时补足，否则将面临合约标的行权交割不足的风险。</span></span>
        </p>
       <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">十九、 请您知悉股票期权行权原则上进行实物交割，但在出现公司未能完成向中国结算的合约标的行权交割义务、期权合约行权日或交收日合约标的交易出现异常情形以及交易所、中国结算规定的其他情形时，期权行权交割可能全部或者部分以现金结算的方式进行，客户须承认行权现金结算的交收结果。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">二十、 请您知悉以现金结算方式进行行权交割时，合约标的应付方将面临按照交易所或者中国结算公布的价格进行现金结算而不能以实物交割方式进行行权交割的风险；合约标的应收方则存在无法取得合约标的并可能损失一定本金的风险。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">二十一、 请您知悉如果到期日遇合约标的全天停牌或者盘中临时停牌的，则期权合约的交易同时停牌，但行权申报照常进行。无论合约标的是否在收盘前复牌，期权合约的最后交易日、到期日以及行权日都不作顺延</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">二十二、 请您知悉在期权合约的最后交易日，有可能因期权合约交易停牌而无法进行正常的开仓与平仓。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">二十三、 请您知悉当合约标的发生暂停或终止上市，交易所有权将未平仓的期权合约提前至合约标的暂停或终止上市前最后交易日的前一交易日，期权合约于该日到期并行权</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">二十四、 请您关注衍生品合约账户内的期权合约通过该账户对应的证券账户完成合约标的交割。客户衍生品合约账户内存在未平仓合约或清算交收责任尚未了结前，客户衍生品合约账户的销户及对应证券账户的撤销指定交易及销户将受到限制。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">二十五、 请您关注当发生不可抗力、意外事件、技术故障、重大差错、市场操纵等异常情况影响股票期权业务正常进行，或者您违反股票期权业务规则并且对市场产生或者将产生重大影响的，交易所及中国结算可以按照相关规则决定采取包括但不限于调整保证金、调整涨跌停价格、调整客户持仓限额、限制交易、取消交易、强行平仓等风险控制措施，由此造成的损失，由客户自行承担。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">二十六、 请您知悉因不可抗力、意外事件、技术故障或者重大差错等原因，导致交易所、中国结算因不可抗力、意外事件、技术故障或者重大差错等原因，导致期权合约条款、结算价格、涨跌停价格、行权现金结算价格、保证金标准以及与期权交易相关的其他重要数据发生错误时，交易所、中国结算可以决定对相关数据进行调整，并向市场公告。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">二十七、 请您关注股票期权业务可能面临各种操作风险、技术系统风险、不可抗力及意外事件并承担由此可能造成的损失，包括但不限于：期权经营机构、结算参与人、交易所或者中国结算因电力、通讯失效、技术系统故障或重大差错等原因而不能及时完成相关业务或影响交易正常进行等情形。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">二十八、 请您知悉利用互联网进行股票期权交易时将存在但不限于以下风险并承担由此导致的损失：由于系统故障、设备故障、通讯故障、电力故障、网络故障、受到网络黑客和计算机病毒攻击及其他因素，可能导致网上交易及行情出现延迟、中断、数据错误或不完全；由于客户未充分了解期权交易及行情软件的实际功能、信息来源、固有缺陷和使用风险，导致对软件使用不当，造成决策和操作失误；客户网络终端设备及软件系统与期权经营机构提供的网上交易系统不兼容，可能导致无法下达委托指令或委托失败；客户缺乏网上交易经验，可能因操作不当造成交易失败或交易失误；客户密码失密或被盗用。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">二十九、 请您关注交易所、中国结算以及期权经营机构发布的公告、通知以及其他形式的提醒，了解包括但不限于期权交易相关业务规则、保证金标准、证券保证金范围及折算率、持仓限额等方面的调整和变化。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">三十、 请您关注股票期权业务中面临的各种政策风险，以及由此可能造成的损失，包括但不限于因法律法规及政策变动须作出重大调整或者终止该业务。 本风险揭示书的揭示事项仅为列举性质，未能详尽列明股票期权业务的所有风险。客户在参与股票期权业务前，应认真阅读相关业务规则及协议条款，对股票期权业务所特有的规则有所了解和掌握，并确信自己已做好足够的风险评估与财务安排，避免因参与股票期权业务而遭受难以承受的损失。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <strong><span style="font-family: 宋体;font-size: 14px"><span style="font-family:宋体">免责条款</span></span></strong>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">对于因下列任一情形而引起投资人所遭受的任何损失或所承担的任何责任，我司均不承担任何补偿、赔偿或其他责任：</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">一、股票收益互换交易按本协议的约定予以执行（到期终止）或提前终止，而导致投资人预期收益未能实现及/或发生损失；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">二、任何适用于本协议的法律、法规、规章、监管制度的颁布、适用、变化或失效，而导致本协议相关条款或内容失效、被变更、被撤销、被中止/终止、无效或不可执行；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">三、与任何挂钩事项（具体挂钩事项见每一笔具体交易所涉及的具体协议文本的约定）及/或金融信息（包括利率、价格等）有关的事实，包括挂钩事项及/或金融信息的产生、存在、消灭、变化、确认错误、信息传递错误、错误更正等（但是，甲方有故意欺诈或重大过失而导致出现本款项情形时，甲方不应免除相应的责任）；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">四、任何国家风险、政治风险、战争风险、自然风险；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">五、非因甲方的故意或重大过失而引致的任何事件或行为；</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">六、甲方根据仲裁机构、法院、金融监管机构、税务机关或/及其他国家行政/司法机关（包括上列机关的派出机构或分支机构）的规定、命令、裁决或要求而做出的行为。</span></span>
        </p>
        <p style="margin-bottom:0;vertical-align:baseline;line-height:24px">
            <span style=";font-family:宋体;font-size:14px"><span style="font-family:宋体">以上《股票收益互换交易确认书》《股票期权风险揭示书》《免责条款》的各项内容，本人/单位已仔细阅读并完全理解，愿意自行承担股票期权交易的风险和损失。</span></span>
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

</body>
</html>
