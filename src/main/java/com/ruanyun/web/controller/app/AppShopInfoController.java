package com.ruanyun.web.controller.app;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.service.app.AppShopInfoService;

@Controller
@RequestMapping("/app/shop")
public class AppShopInfoController extends BaseController{
	
	@Autowired
	private AppShopInfoService appShopInfoService;
	
	/**
	 * 功能描述:搜索技师
	 * @author cqm  2017-8-8 下午03:05:45
	 * @param response
	 * @param page
	 * @param shopInfo
	 * @param type
	 * @param age
	 */
	@RequestMapping("list")
	public void getShopList(HttpServletResponse response,Page<TShopInfo> page,TShopInfo shopInfo,Integer type,String age,Integer memberLevel){
		AppCommonModel model=null;
		try {
			model=appShopInfoService.getShopList(page, shopInfo, type, age,memberLevel);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/shop/list:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}

}
