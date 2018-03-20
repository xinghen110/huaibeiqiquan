package com.ruanyun.web.controller.app;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.sys.TAreas;
import com.ruanyun.web.service.app.AppAreasService;

@Controller
@RequestMapping("/app/areas")
public class AppAreasController extends BaseController{
	
	@Autowired
	private AppAreasService appAreasService;
	
	/**
	 * 功能描述:查询区域列表
	 * @author cqm  2016-11-8 上午10:56:07
	 * @param response
	 * @param page
	 * @param areas
	 */
	@RequestMapping("list")
	public void getCityList(HttpServletResponse response,TAreas areas){
		AppCommonModel model=null;
		try {
			model=appAreasService.getAreasList(areas);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/areas/list:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}

}
