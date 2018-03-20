package com.ruanyun.web.controller.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.utils.RSAUtils;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TPayInfo;
import com.ruanyun.web.service.app.AppPayInfoService;
/**
 * 在线支付配置
 *
 */
@Controller
@RequestMapping("app/payinfo")
public class AppPayInfoController extends BaseController {
	
	@Autowired
	private AppPayInfoService appPayInfoService;
	
	/**
	 * 功能描述:支付宝支付
	 * @author cqm  2017-8-10 下午03:00:12
	 * @param response
	 * @param userNum
	 * @param orderNum
	 */
	@RequestMapping("getAlipayPay")
	public void getAlipayPay(HttpServletResponse response,String orderNum){
		AppCommonModel model=null;
		try {
			model=appPayInfoService.getAlipayPay(orderNum);
		} catch (Exception e) {
			e.printStackTrace();
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述:获取支付参数配置
	 *
	 * @author chenqian  2016-10-14 下午06:58:51
	 * 
	 * @param response
	 * @param userNum
	 */
	@RequestMapping("alipayConfig")
	public void alipayConfig(HttpServletResponse response){
		AppCommonModel model=null;
		try {
			TPayInfo payInfo = appPayInfoService.getPayInfo(1);
			ObjectMapper mapper = new ObjectMapper();  
	        String jsonStr = mapper.writeValueAsString(payInfo); //将实体类转换成字符串
	        model = new AppCommonModel(1,"获取成功",RSAUtils.encryptData(jsonStr, RSAUtils.PUCLIC_KEY_ALL)); //加密输出
		} catch (Exception e) {
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	
	@InitBinder
	public void initBinders(WebDataBinder binder) {
		super.initBinder(binder, "yyyy-MM-dd HH:mm:ss", true);
	}
	
	/**
	 * 功能描述:支付宝回调地址
	 *
	 * @author yangliu  2016年5月11日 上午9:41:11
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("aliyunReturn")
	public void alipayReturnUrl(HttpServletRequest request,HttpServletResponse response) {
		
		int result = -1;
		try {
			result=appPayInfoService.zhifubaoPayResult(request);
		} catch (Exception e) {
			e.getMessage();
		}
		super.writeText(response, result==1?"success":"fail");
	}
	
	/**
	 * 功能描述: 微信预下单
	 *
	 * @author yangliu  2016年5月11日 上午9:41:39
	 * 
	 * @param wxPaySendData 客户端不用穿任何信息
	 * @param request
	 * @param response
	 * @param accountDetailCode
	 * orderNum  替代中间表中的outTrainOrder
	 */
	@RequestMapping("weixinDoOrder")
	public void weixinDoOrder(HttpServletRequest request,HttpServletResponse response,String orderNum){
		AppCommonModel model =null;
		try {
			model=appPayInfoService.weixinDoPay(request,orderNum);
		} catch (Exception e) {
			model=new AppCommonModel(-1,"下单失败"+e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述: 微信回调地址
	 *
	 * @author yangliu  2016年5月11日 上午9:42:24
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("weixinReturnUrl")
	public void weixinReturnUrl(HttpServletRequest request,HttpServletResponse response){
		int result = -1;
		String msg="";
		try {
			result=appPayInfoService.weixinPayResult(request);
		} catch (Exception e) {
			msg=e.getMessage();
		}
		String restulMsg=String.format("<xml><return_code><![CDATA[%s]]></return_code><return_msg><![CDATA[%s]]></return_msg></xml>", (result==1?"SUCCESS":"FAIL "),msg);
		super.writeText(response, restulMsg);
	}

}
