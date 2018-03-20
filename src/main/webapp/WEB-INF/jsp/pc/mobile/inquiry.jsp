<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<title></title>
	</head>
	<%@include file="base.jsp"%>
	<body>
		<div data-role="page" id="inquiry">
			<div data-role = "content" class="inquiry">
				<h4 class="title size-15 text-align">PROFIT & LOSS</h4>
				<div class="fl wc">
					<h4 class="wc-h4">标的股票</h4>
					<input id="myFilter" data-type="search" data-role = "none" class="input_select" type="text" />
				</div>
				<div class="fr wc">
					<h4 class="wc-h4">行权周期</h4>
					<select data-role = "none" class="input_select">
						<option>请选择</option>
					</select>
				</div>
				<div class="fl wc clear">
					<h4 class="wc-h4">前收盘价</h4>
					<input data-role = "none"  type="text" class="input_select" />
				</div>
				<div class="fr wc">
					<h4 class="wc-h4">到期日</h4>
					<input data-role = "none"  type="date" class="input_select" />
				</div>
				<div class="fl wc clear" style="margin-bottom: 2.5em;">
					<h4 class="wc-h4">名义本金(单位:元)</h4>
					<input data-role = "none"  type="text" class="input_select" />
				</div>
				
				<a class="ui-btn ksxj" data-ajax='false'>开始询价</a><!-- 不是Ajax请求  就加个data-ajax='false' -->
				
				<div class="text-align last_time">
					<a>
						<h4 class="color-js last_time_title">查看上次询价结果</h4>
						<img width="20px" src="img/mobile/icon_inquiry_arrow.png" />
					</a>
				</div>
			</div>
			
			<div data-role = "content" class="inquiry_result" style="display: none;">
				<h4 class="title text-align">询价结果<a class="back"><img width="20px" src="img/icon_inquiry_close.png" /></a></h4>
				<div class="fl text-align wc">
					<h4 class="color-js">190000元</h4>
					<h4 class="color-w size-9">期权价格</h4>
				</div>
				<div class="fr text-align wc">
					<h4 class="color-js">100.00%</h4>
					<h4 class="color-w size-9">权利金比例</h4>
				</div>
				<table data-role = "none">
					<tr>
						<th>股价</th>
						<th>涨幅</th>
						<th>亏盈比例</th>
						<th>盈利金额</th>
					</tr>
					<tr>
						<td>0.00</td>
						<td class="color-js">36.90%</td>
						<td class="color-js">0%</td>
						<td class="color-js">190000元</td>
					</tr>
					<tr>
						<td>0.00</td>
						<td class="color-js">36.90%</td>
						<td class="color-js">0%</td>
						<td class="color-js">190000元</td>
					</tr>
					<tr>
						<td>0.00</td>
						<td class="color-js">36.90%</td>
						<td class="color-js">0%</td>
						<td class="color-js">190000元</td>
					</tr>
					<tr>
						<td>0.00</td>
						<td class="color-js">36.90%</td>
						<td class="color-js">0%</td>
						<td class="color-js">190000元</td>
					</tr>
					<tr>
						<td>0.00</td>
						<td class="color-js">36.90%</td>
						<td class="color-js">0%</td>
						<td class="color-js">190000元</td>
					</tr>
				</table>
				<a class="ui-btn cxxj">重新询价</a>
			</div>
			<script>
				jQuery('.inquiry .ksxj').click(function(){//开始询价
					jQuery('.inquiry').hide();
					jQuery('.inquiry_result').show();
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
		</div>
	</body>
</html>
