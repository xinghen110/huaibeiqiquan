package com.ruanyun.web.controller.app;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruanyun.web.util.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TUserAddress;
import com.ruanyun.web.model.mall.TUserRecord;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.app.AppUserRecordService;
@Controller
@RequestMapping("/app/{userNum}/record")
public class AppUserRecoedController extends BaseController{
	
	@Autowired
	private AppUserRecordService appUserRecordService;
	
	/**
	 * 功能描述:查询流水记录
	 * @author cqm  2016-11-5 下午01:42:22
	 * @param response
	 * @param userNum
	 * @param page
	 * @param userRecord
	 * @param user
	 */
	@RequestMapping("list")
	public void getShopList(HttpServletResponse response,@PathVariable String userNum,Page<TUserRecord> page,TUserRecord userRecord,TUser user){
		AppCommonModel model=null;
		try {
			model=appUserRecordService.getUserRecordList(page,userRecord);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/{userNum}/record/list:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}





}
