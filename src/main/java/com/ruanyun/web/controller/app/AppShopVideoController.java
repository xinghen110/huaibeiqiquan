package com.ruanyun.web.controller.app;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TShopVedio;
import com.ruanyun.web.service.app.AppShopVideoService;

@Controller
@RequestMapping("/app/video")
public class AppShopVideoController extends BaseController{
	
	@Autowired
	private AppShopVideoService appShopVedioService;
	
	
	/**
	 * 功能描述:上传视频
	 * @author cqm  2017-8-21 上午11:20:08
	 * @param response
	 * @param shopVedio
	 */
	@RequestMapping("add")
	public void getShopList(HttpServletResponse response,TShopVedio shopVedio){
		AppCommonModel model=null;
		try {
			model=appShopVedioService.saveOrUpdateUserVedio(shopVedio);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/video/add:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述:删除视频
	 * @author cqm  2017-8-24 上午09:49:18
	 * @param response
	 * @param videoNum
	 */
	@RequestMapping("delete")
	public void deleteShopVedio(HttpServletResponse response,String  videoNum){
		AppCommonModel model=null;
		try {
			model=appShopVedioService.deleteShopVedio(videoNum);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/video/delete:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述:获取视频列表
	 * @author cqm  2017-8-21 上午11:57:13
	 * @param response
	 * @param page
	 * @param shopVedio
	 */
	@RequestMapping("list")
	public void getShopVedioList(HttpServletResponse response,Page<TShopVedio> page,TShopVedio shopVedio){
		AppCommonModel model=null;
		try {
			model=appShopVedioService.getShopVedioList(page, shopVedio);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/video/list:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述:获取七牛Token
	 * @author cqm  2017-4-19 下午01:55:57
	 * @param response
	 */
	@RequestMapping("upload_token")
	public void addUserVedio(HttpServletResponse response){
		AppCommonModel model=null;
		try {
			model=appShopVedioService.getUploadToken();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/video/upload_token:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	

}
