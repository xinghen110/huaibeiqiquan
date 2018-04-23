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
<body>
<div data-role="page" id="my_scheme_apply">
    <div data-role = "header" style="border-width: 0; background: #28292E;">
        <a data-role="button" data-rel="back" ><img src="img/mobile/icon_nav_back.png"/></a>
        <h4 class="color-w">服务协议</h4>
    </div>
    <div data-role = "content" style="color: #FFFFFF;margin-left: 20px;margin-right: 20px">
       <h3>尊敬的用户：</h3>
    <p>
      当您进行期权交易的时候，可能获得较高的投资收益，但同时也存在着较大的投资风险。为了使您更好地了解其中的风险，请在交易前仔细阅读以下风险揭示书。且除以下揭示的风险外还有其他的外部风险，均需要用户自行承担，平台不承担相关责任：</p>
    <p><b>1、宏观经济风险：</b>国家宏观经济形势的变化以及国际经济环境和其他证券市场的变化，可能引起证券市场的波动，使您存在亏损的可能，您将承担由此可能造成的损失。</p>
    <p><b>2、政策监管风险：</b>因宏观政策、监管导向、行业政策、地区发展政策等因素引起的无法实现交易合作的风险。</p>
    <p><b>3、技术风险：</b>由于本平台属于网络技术服务，其中交易通讯、交易下单、行情揭示等都是通过电子通讯技术和网络技术来实现的，这些技术存在着被网络黑客和计算机病毒攻击的可能，同时通讯技术、电脑技术和相关软件存在缺陷的可能，这些风险均有可能造成服务中断或者延迟。</p>
    <p><b>4、不可抗力因素导致的风险：</b>不可抗力因素包括但不限于以下情形：平台系统停机维护；平台所依赖的通讯设备出现故障不能进行数据传输；因台风、地震、海啸、洪水 、停电、战争、恐怖袭击等不可抗力之因素，造成本平台系统障碍不能执行业务。</p>
    <p><b>5、其他风险：</b>由于您密码失密、操作不当、投资决策失误等原因可能会使您发生亏损。</p>
    <br>
    <p><b>温馨提示：</b>市场有风险，入市须谨慎！如果用户不同意本协议的任意内容，或者无法准确理解以上风险及可能存在的其他风险，请不要进行后续操作。</p>
    <p>以上《风险揭示书》本人已阅读并完全理解，愿意自行承担投资市场的各种风险。</p>
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
