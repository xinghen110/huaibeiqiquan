package com.ruanyun.web.controller.daowei;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.daowei.TFeedBack;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.daowei.FeedBackService;
import com.ruanyun.web.util.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by wangyf on 2017/8/5.
 */
@Controller
@RequestMapping("feedback")
public class FeedBackController extends BaseController {

    @Autowired
    private FeedBackService feedBackService;

    /**
     * 功能描述:绑定时间
     */
    @InitBinder
    public void initBinders(WebDataBinder binder) {
        super.initBinder(binder, "yyyy-MM-dd", true);
    }

    /**
     * 功能描述：获取反馈信息
     * @param page
     * @param feedBack
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("list")
    public String list(Page<TFeedBack> page, TFeedBack feedBack, Model model, HttpSession session) {
        TUser user = HttpSessionUtils.getCurrentUser(session);
        addModel(model, "pageList", feedBackService.getList(page, feedBack));
        addModel(model, "user", user);
        addModel(model, "bean", feedBack);
        return "pc/backend/feedback/list";
    }
    
    @RequestMapping("detil")
    public String detil(String feedBackNum,Model model){
    	TFeedBack bean =feedBackService.get(TFeedBack.class, "feedBackNum",feedBackNum);
    	addModel(model, "bean", bean);
    	return "pc/backend/feedback/detil";
    }

}
