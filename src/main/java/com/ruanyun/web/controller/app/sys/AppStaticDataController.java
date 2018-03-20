package com.ruanyun.web.controller.app.sys;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.service.app.sys.AppStaticDataService;
@Controller
@RequestMapping("app/external/staticdata")
public class AppStaticDataController extends BaseController {
	@Autowired
	private AppStaticDataService appStaticDataService;

	/**
	 * 功能描述:加载字典表
	 *
	 * @author yangliu  2016年8月2日 上午10:33:08
	 * 
	 * @param parentCode
	 * @return
	 */
	@RequestMapping("/dictionary")
	public void getDictionaryList(HttpServletResponse response){
		AppCommonModel model=null;
		try {
			model=appStaticDataService.getDictionaryList();
		} catch (Exception e) {
			logger.error("login:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述:加载类型表
	 * @author cqm  2017-8-11 上午09:30:37
	 * @param response
	 */
	@RequestMapping("/type")
	public void getTypeList(HttpServletResponse response){
		AppCommonModel model=null;
		try {
			model=appStaticDataService.getTypeList();
		} catch (Exception e) {
			logger.error("login:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述: 获取版本信息
	 *
	 * @author yangliu  2016年8月29日 上午10:12:10
	 * 
	 * @param response
	 */
	@RequestMapping("/version")
	public void getVersion(HttpServletResponse response){
		AppCommonModel model=null;
		try {
			model=appStaticDataService.getDictionaryList("STATICDATA_VERSION");
		} catch (Exception e) {
			logger.error("login:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	
	
}
