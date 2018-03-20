package com.ruanyun.web.service.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.daowei.TFeedBack;
import com.ruanyun.web.service.daowei.FeedBackService;

@Service
public class AppFeedBackService {
	
	@Autowired
	private FeedBackService feedBackService;
	
	/**
	 * 功能描述:反馈
	 * @author cqm  2017-8-10 下午01:55:08
	 * @param feedBack
	 * @return
	 */
	public AppCommonModel addFeedBack(TFeedBack feedBack){
	   feedBackService.addFeedBack(feedBack);
	return new AppCommonModel(1,"反馈成功");
	}

}
