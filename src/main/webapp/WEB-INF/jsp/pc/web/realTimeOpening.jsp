<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <%@include file="header.jsp" %>
	<body>
	<%@include file="header_title.jsp" %>
		<div class="ydl"><!-- 已登录 -->
			<img src="img/banner_user_ydl.jpg" width="100%" class="ydl-img"/>
			<div class="grzx">
				<h4 style="padding-top: 5%;"  class="color-white"><img class="tx" src="img/icon_hp.png" /><span class="name">未实名</span></h4>
				<h4 class="color-white" style="padding-top: 4%;"><span>18715017045</span> | <span class="update-password">修改密码</span> | <span class="exit-login">退出登录</span></h4>
			</div>
			<ul class="product_list" style="margin-top: -4px;">
				<li style="width: 20%!important;" class="fl product_list_li">个人资料</li>
				<li style="width: 20%!important;" class="fl">账户充值</li>
				<li style="width: 20%!important;" class="fl">账户提现</li>
				<li style="width: 20%!important;" class="fl">推广赚钱</li>
				<li style="width: 20%!important;" class="fl">我的方案</li>
			</ul>
			<div class="index1">
				<div class="index1-div"><!--实名认证-->
					<div class="smrz">
						<h4 class="text-align color-white title">实名认证及银行卡绑定</h4>
						<h4 class="text-align color-white size-8 ts">实名信息提交后不可修改，请务必填写真实资料，并保证与银行卡预留个人信息一致</h4>
						<div class="index1-div-div">
							<div>
								<h4 class="fl color-white">姓名：</h4>
								<input class="fl" type="text" placeholder="请输入您的真实姓名" />
							</div>
							<div>
								<h4 class="fl color-white">身份证号：</h4>
								<input class="fl" type="text" placeholder="请输入您的身份证号" />
							</div>
							<div>
								<h4 class="fl color-white">身份证正面照片：</h4>
								<input onclick="clickFile(this)" class="fl" readonly="readonly" type="text" placeholder="请选择身份证正面照片" />
								<input type="file" onchange="qrFile(this)" style="display: none;" />
							</div>
							<div>
								<h4 class="fl color-white">身份证反面照片：</h4>
								<input onclick="clickFile(this)" class="fl" readonly="readonly" type="text" placeholder="请选择身份证反面照片" />
								<input type="file" onchange="qrFile(this)" style="display: none;" />
							</div>
							<div>
								<h4 class="fl color-white">银行卡号：</h4>
								<input class="fl" type="text" placeholder="请输入您的银行卡号" />
							</div>
							<div>
								<h4 class="fl color-white">所属银行：</h4>
								<select>
									<option>请选择所属银行</option>
								</select>
							</div>
							<div>
								<h4 class="fl color-white">银行卡照片：</h4>
								<input onclick="clickFile(this)" class="fl" readonly="readonly" type="text" placeholder="请选择您的银行卡照片" />
								<input type="file" onchange="qrFile(this)" style="display: none;" />
							</div>
						</div>
						<div style="margin-top: -26px;" class="text-align">
							<h6 class="color-white size-9" style="margin-bottom: 10px;">认证信息必须与公安部官方数据库一致</h6>
							<button>确认</button>
						</div>
					</div>

					<div class="update-xgmm" style="display: none;"><!--修改密码-->
						<h4 style="width: 100%;margin: 0;" class="text-align color-white title">修改密码</h4>
						<img class="fl break-data" src="img/left-bark.png" style="width: 18px;cursor: pointer; margin-top: -28px;margin-left: 20px;" />
						<h4 class="color-white size-9 clear">手机号</h4>
						<input type="text" placeholder="请输入手机号" />
						<h4 class="color-white size-9">验证码</h4>
						<input type="text" placeholder="请输入收到的验证码" />
						<a class="zhmm-yzm" onclick="sendMessage2()">获取验证码</a>
						<h4 class="color-white size-9">登录密码</h4>
						<input type="text" placeholder="请输入登录密码" />
						<h4 class="color-white size-9">确认密码</h4>
						<input type="text" placeholder="请确认登录密码" />
						<button class="color-red">登录</button>
					</div>
				</div>


			</div>
			<div style="display: none;" class="index1"><!-- 账户充值 -->
				<div class="index1-div">
					<h4 class="text-align color-white title">银行卡支付</h4>
					<div class="index1-div-div">
						<div>
							<h4 class="fl color-white">自有资金：</h4>
							<h5 class="fl color-white">0.00</h5>
						</div>
						<div>
							<h4 class="fl color-white">充值金额：</h4>
							<input class="fl" type="text" placeholder="请输入充值金额" />
							<h6 style="float: left; margin-left: -30px; margin-top: 3px;color: white;">元</h6>
						</div>
						<div>
							<h4 class="fl color-white">选择充值方式：</h4>
							<select>
								<option>请选择</option>
								<option>微信</option>
								<option>支付宝</option>
								<option>银行卡</option>
							</select>
						</div>
					</div>
					<h5 class="color-white size-9" style="padding: 0px 55px;margin-bottom: 10px;margin-top: -25px;">温馨提示:</h5>
					<p class="color-white size-8" style="padding: 0px 55px;margin-bottom: 17px;line-height: 30px;">
						1.为了您的资金安全，您的账号资金将专款专户管理<br />
						2.为了您的资金安全，您的账号资金将专款专户管理<br />
						3.为了您的资金安全，您的账号资金将专款专户管理<br />
						4.为了您的资金安全，您的账号资金将专款专户管理
					</p>
					<div class="text-align">
						<button>确认</button>
					</div>
				</div>
			</div>
			<div style="display: none;" class="index1"><!--账户提现-->
				<div class="index1-div" style="height: 450px!important;">
					<h4 class="text-align color-white title">银行卡支付</h4>
					<div class="index1-div-div">
						<div>
							<h4 class="fl color-white">可提现资金：</h4>
							<h5 class="fl color-white">0.00</h5>
						</div>
						<div>
							<h4 class="fl color-white">提现金额：</h4>
							<input class="fl" type="text" placeholder="请输入提现金额" />
							<h6 style="float: left; margin-left: -30px; margin-top: 3px;color: white;">元</h6>
						</div>
					</div>
					<h5 class="color-white size-9" style="padding: 0px 55px;margin-bottom: 10px;margin-top: -25px;">温馨提示:</h5>
					<p class="color-white size-8" style="padding: 0px 55px;margin-bottom: 17px;line-height: 30px;">
						1.为了您的资金安全，您的账号资金将专款专户管理<br />
						2.为了您的资金安全，您的账号资金将专款专户管理<br />
						3.为了您的资金安全，您的账号资金将专款专户管理<br />
						4.为了您的资金安全，您的账号资金将专款专户管理
					</p>
					<div class="text-align">
						<button>确认</button>
					</div>
				</div>
			</div>
			<div style="display: none;" class="index1"><!--推广链接-->
				<div class="index1-div" style="height: auto!important;padding-bottom: 30px;">
					<h4 class="text-align color-white title">推广链接</h4>
					<input id="copy-text" type="text" readonly="readonly" value="http://www.baidu.coms" />
					<div class="text-align">
						<button id="btn">点击复制</button>
					</div>
				</div>
			</div>
			<div style="display: none;" class="index1"><!-- 我的方案 -->
				<div class="index1-div index1-div1" style="padding-bottom: 30px;">
					<h4 class="text-align color-white title">管理方案 </h4>
					<table>
						<tr>
							<th class="color-white">初期市值</th>
							<th class="color-white">建议成本价</th>
							<th class="color-white">管理费</th>
							<th class="color-white">操作</th>
						</tr>
						<tr>
							<td>111</td>
							<td>111</td>
							<td>111</td>
							<td><h4>方案详情</h4></td>
						</tr>
						<tr>
							<td>111</td>
							<td>111</td>
							<td>111</td>
							<td><h4>方案详情</h4></td>
						</tr>
						<tr>
							<td>111</td>
							<td>111</td>
							<td>111</td>
							<td><h4>方案详情</h4></td>
						</tr>
						<tr>
							<td>111</td>
							<td>111</td>
							<td>111</td>
							<td><h4>方案详情</h4></td>
						</tr>
					</table>
				</div>

				<div class="index1-div index1-div2" style="padding-bottom: 30px;display: none;">
					<h4 class="text-align color-white title">方案详情</h4>
					<img class="fl break" src="img/left-bark.png" style="width: 18px;cursor: pointer; margin-top: -32px;margin-left: 20px;" />
					<table>
						<tr>
							<th class="color-white">股票名称</th>
							<th class="color-white">沪点股份</th>
							<th class="color-white">股票代码</th>
							<th class="color-white">000001</th>
						</tr>
						<tr>
							<td>初期方案市值</td>
							<td>1000元</td>
							<td>管理费状态</td>
							<td>已缴纳</td>
						</tr>
						<tr>
							<td>买入价格</td>
							<td>63189.45元</td>
							<td>卖出价格</td>
							<td>63189.45元</td>
						</tr>
						<tr>
							<td>建仓日期</td>
							<td>2017-02-12</td>
							<td>方案结束日期</td>
							<td>2017-04-18</td>
						</tr>
						<tr>
							<td>方案管理费</td>
							<td>1元</td>
							<td>盈利分配</td>
							<td>90%归您</td>
						</tr>
						<tr>
							<td>结案成功日期</td>
							<td>---</td>
							<td>建议成本价</td>
							<td>市价</td>
						</tr>
					</table>
					<style>
						.statistics_title{font-size: 12px;width: 89%;margin: 0 30px;color: white;}
						.statistics_title span{width: 25%;text-align: center;float: left;}
						.statistics_content{font-size: 14px;opacity: 1!important;width: 89%;margin: 0 30px;color: white;}
						.statistics_content span{width: 25%;text-align: center;float: left;}
					</style>
					<div style="overflow: hidden; border-top: 1px solid white; border-bottom: 1px solid white;padding: 14px 0; line-height: 25px;">
						<h4 class="statistics_title">
							<span>盈利统计日期</span>
							<span>投顾盈利</span>
							<span>盈利分成</span>
						<span>方案盈利</span>
						</h4>
						<h4 class="statistics_content">
							<span>2017-02-12</span>
							<span>7312元</span>
							<span>371元</span>
							<span>22元</span>
						</h4>
					</div>
				</div>
			</div>
		</div>

		<%@include file="footer.jsp"%>
		<script type="text/javascript">
			var wH = $(window).height(); //获取屏幕高度
			var ydl_img_H = $(".ydl-img").height();
			var ydl_img_W = $(".ydl-img").width();
			$(".grzx").height(ydl_img_H);
			$(".grzx").css("margin-top",-(ydl_img_H));
			/**************************************登录后**************************************/
			$(".grzx .exit-login").click(function(){//退出登录
				window.location.href='realTimeOpening_login.html';
			});
			$(".product_list li").click(function(){//切换标题及显示标题内容
				$(".product_list li").removeClass('product_list_li');
				$(this).addClass('product_list_li');
				$t = $(this).index();
				$(".index1").hide();
				$(".index1").eq($t).show();
			});
			function hqkg(){
				var ydl_img_H = $(".ydl-img").height();
				var ydl_img_W = $(".ydl-img").width();
				$(".grzx").height(ydl_img_H);
				$(".grzx").css("margin-top",-(ydl_img_H));
			}

			function clickFile(obj){ //点击触发input file
				$(obj).parent().find("input[type='file']").click();
			}
			function qrFile(obj){//选中图片时的判断
				var file = obj.files[0];
		   		//判断类型是不是图片
			    if(!/image\/\w+/.test(file.type)){
		            alert("请确保文件为图像类型");
		            return false;
			    }
				var fileName="";
	            if(typeof(fileName) != "undefined")
	            {
	                fileName = $(obj).val().split("\\").pop();
	                fileName=fileName.substring(0, fileName.lastIndexOf("."));
	            }
            	$(obj).parent().find("input[type='text']").val(fileName);
			}
			 var btn = document.getElementById('btn');
		    btn.addEventListener('click', function(){//点击复制链接地址
		        document.getElementById("copy-text").focus();
				document.getElementById("copy-text").select();
		        document.execCommand('copy', true);
		        alert("复制成功！")
		    });

		    /*我的方案&方案详情之前的切换*/
		    $(".index1 .index1-div1 table tr td:last-child h4").click(function(){//进入方案详情
		    	$(".index1 .index1-div1").hide();
		    	$(".index1 .index1-div2").show();
		    });
		    $(".index1 .index1-div2 .break").click(function(){//返回我的方案
		    	$(".index1 .index1-div2").hide();
		    	$(".index1 .index1-div1").show();
		    });
		    /*我的方案&方案详情之前的切换end*/
		    $('.update-password').click(function(){//修改密码
		    	$(".smrz").hide();
		    	$(".update-xgmm").show();
		    });
		     $('.break-data').click(function(){//修改密码-返回按钮
		    	window.location.href = 'realTimeOpening_realName_authentication.html';
		    });
			$(function(){  /*调整窗口自动调整宽度*/
		    	$(window).resize(function(){//实时获取宽高
		    		var ydl_img_H = $(".ydl-img").height();
					var ydl_img_W = $(".ydl-img").width();
					$(".grzx").height(ydl_img_H);
					$(".grzx").css("margin-top",-(ydl_img_H));
	    		});
			});
		</script>
	</body>
</html>
