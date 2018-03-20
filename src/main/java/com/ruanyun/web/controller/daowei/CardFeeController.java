package com.ruanyun.web.controller.daowei;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.daowei.TCardFee;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.daowei.CardFeeService;
import com.ruanyun.web.util.CallbackAjaxDone;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.HttpSessionUtils;
@Controller
@RequestMapping("cardfee")
public class CardFeeController extends BaseController {
	@Autowired
	private CardFeeService cardFeeService;
	
    /**
     * 根据 cardtype 来区分 年费和分销
     * Zjb 2017 上午11:11:12
     * @param model
     * @param cardFee
     * @return
     */
    @RequestMapping("list")
    public String list(Model model,TCardFee cardFee){
    	List<TCardFee> list = cardFeeService.getAllByCondition(TCardFee.class, "cardType", cardFee.getCardType());
    	addModel(model, "list", list);
    	addModel(model, "bean", cardFee);
    	return "pc/backend/cardfee/nflist";	
    }
    /**
     * 修改页面
     * Zjb 2017 上午11:23:35
     * @param model
     * @param cardFee
     * @return
     */
    @RequestMapping("edit")
    public String edit(Model model,TCardFee cardFee){
    	if(EmptyUtils.isNotEmpty(cardFee.getCardFeeNum())){
    		TCardFee bean=cardFeeService.get(TCardFee.class, "cardFeeNum",cardFee.getCardFeeNum());
    		addModel(model, "bean", bean);
    	}
    	return "pc/backend/cardfee/edit";
    	
    }
    /**
     * 保存年费 和分销比例
     * Zjb 2017 上午11:35:26
     * @param session
     * @param model
     * @param request
     * @param response
     * @param cardFee
     */
    @RequestMapping("saveCardFee")
    public void saveCardFee(HttpSession session, Model model, HttpServletRequest request,HttpServletResponse response, TCardFee cardFee){
    	TUser user = HttpSessionUtils.getCurrentUser(session);
    	Integer result =cardFeeService.saveCardFee(cardFee, request, user);
    	if(result==1){
    		  super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS, "main_", "cardfee/list?cardType="+cardFee.getCardType(), "forward"));
        } else {
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "", "", ""));
        }
    	
    }
    
	
}
