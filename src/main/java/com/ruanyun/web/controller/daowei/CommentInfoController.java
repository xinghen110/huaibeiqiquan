package com.ruanyun.web.controller.daowei;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.mall.TCommentInfo;
import com.ruanyun.web.service.daowei.MealInfoService;
import com.ruanyun.web.service.mall.CommentInfoService;
import com.ruanyun.web.service.mall.ShopInfoService;
import com.ruanyun.web.util.CallbackAjaxDone;
import com.ruanyun.web.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangyf on 2017/8/5.
 */
@Controller
@RequestMapping("commentinfo")
public class CommentInfoController extends BaseController {

    @Autowired
    private CommentInfoService commentInfoService;
    @Autowired
    private ShopInfoService shopInfoService;
    @Autowired
    private MealInfoService mealInfoService;

    /**
     * 功能描述:绑定时间
     */
    @InitBinder
    public void initBinders(WebDataBinder binder) {
        super.initBinder(binder, "yyyy-MM-dd", true);
    }

    @RequestMapping("list")
    public String list(Page<TCommentInfo> page, TCommentInfo commentInfo, Model model) {
        addModel(model, "pageList", commentInfoService.getList(page, commentInfo));
        addModel(model, "bean", commentInfo);
        return "pc/backend/commentinfo/list";
    }

    /**
     * 功能描述：开启或者禁用评论
     */
    @RequestMapping("ishomeshow")
    public void ishomeshow(String ids, Integer type, HttpServletResponse response) {
        int result = commentInfoService.updateStatus(ids, type);
        if (result > 0) {
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS, "main_", "commentinfo/list", "redirect"));
        } else {
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "", "", ""));
        }
    }

    /**
     * 功能描述：删除评论
     */
    @RequestMapping("del")
    public void del(String ids, HttpServletResponse response) {
        int result = commentInfoService.deleteComment(ids);
        if (result > 0) {
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS, "main_", "commentinfo/list", "redirect"));
        } else {
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "", "", ""));
        }
    }

    /**
     * 功能描述：查看详情
     * @param commentNum
     * @param model
     * @return
     */
    @RequestMapping("detail")
    public String detail(String commentNum, Model model) {
        addModel(model, "bean", commentInfoService.get(TCommentInfo.class, "commentNum", commentNum));
        return "pc/backend/commentinfo/detailsEdit";
    }

    /**
     * 功能描述：修改内容和星级
     * @param commentNum
     * @param score
     * @param content
     * @param response
     */
    @RequestMapping("edit")
    public void edit(String commentNum, String score, String content, HttpServletResponse response) {
        int reuslt = commentInfoService.updateScoreAndContent(commentNum, score, content);
        if (reuslt==1){
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_","commentinfo/list", "forward"));
        }else{
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "","", ""));
        }
    }

}
