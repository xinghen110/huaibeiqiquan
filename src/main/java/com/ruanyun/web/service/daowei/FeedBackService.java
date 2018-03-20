package com.ruanyun.web.service.daowei;

import java.util.Date;
import java.util.Map;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.CommonUtils;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.dao.daowei.FeedBackDao;
import com.ruanyun.web.model.daowei.TFeedBack;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.util.NumUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangyf on 2017/8/5.
 */
@Service("feedBackService")
public class FeedBackService extends BaseServiceImpl<TFeedBack> {

    @Autowired
    private FeedBackDao feedBackDao;
    @Autowired
    private UserService userService;

    public Page<TFeedBack> getList(Page<TFeedBack> page, TFeedBack feedBack) {
    	Page<TFeedBack> _page=feedBackDao.querPage(page, feedBack);
        return _page;
    }
    
    /**
     * 功能描述:意见反馈
     * @author cqm  2017-8-10 下午01:51:05
     * @param feedBack
     */
    public void addFeedBack(TFeedBack feedBack){
    	if(EmptyUtils.isEmpty(feedBack.getContent())||
    	   EmptyUtils.isEmpty(feedBack.getLinkTel())||
    	   EmptyUtils.isEmpty(feedBack.getUserNum())){
    		throw new RuanYunException("参数不全");
    	}
    	feedBack.setCreateTime(new Date());
    	save(feedBack);
    	String feedBackNum = NumUtils.getCommondNum(NumUtils.PIX_FEEDBACK, feedBack.getFeedBackId());
    	feedBack.setFeedBackNum(feedBackNum);
    }

}
