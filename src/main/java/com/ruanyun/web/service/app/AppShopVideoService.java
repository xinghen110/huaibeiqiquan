package com.ruanyun.web.service.app;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TShopVedio;
import com.ruanyun.web.service.mall.ShopVideoService;
import com.ruanyun.web.util.QiNiuUploadUtils;


@Service
public class AppShopVideoService {
	
	@Autowired
	private ShopVideoService shopVedioService;
	
	/**
	 * 功能描述:上传视频
	 * @author cqm  2017-4-19 下午01:42:10
	 * @param request
	 * @param shopVedio
	 * @return
	 */
	public AppCommonModel saveOrUpdateUserVedio(TShopVedio shopVedio) {
		return new AppCommonModel(1, "上传视频成功",shopVedioService.saveOrUpdateShopVedio(shopVedio));
	}
	
	/**
	 * 功能描述:获取视频列表
	 * @author cqm  2017-8-21 上午11:55:26
	 * @param page
	 * @param shopVedio
	 * @return
	 */
	public AppCommonModel getShopVedioList(Page<TShopVedio> page,TShopVedio shopVedio) {
		return new AppCommonModel(1, "获取视频列表成功",shopVedioService.getShopVedioList(page, shopVedio));
	}
	
	/**
	 * 功能描述:删除视频
	 * @author cqm  2017-8-21 上午11:54:21
	 * @param videoNum
	 * @return
	 */
	public AppCommonModel deleteShopVedio(String videoNum) {
	  	AppCommonModel model = new AppCommonModel(-1, "");
			shopVedioService.deleteShopVedio(videoNum);
			model.setMsg("删除视频成功");
			model.setResult(1);
			model.setObj("{}");
		return model;
	}
	
	/**
	 * 功能描述:获取七牛Token
	 * @author cqm  2017-4-19 下午01:53:48
	 * @return
	 */
	public AppCommonModel getUploadToken() {
		Map<String,String> map = new HashMap<String, String>();
		map.put("token", QiNiuUploadUtils.getUploadToken(QiNiuUploadUtils.bucket));
		map.put("baseUrl",QiNiuUploadUtils.bucket_video_url);
		return new AppCommonModel(1, "获取成功",map);
	}

}
