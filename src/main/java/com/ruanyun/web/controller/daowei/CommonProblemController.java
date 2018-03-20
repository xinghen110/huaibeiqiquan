package com.ruanyun.web.controller.daowei;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.daowei.TCommonProblem;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.daowei.CommonProblemService;
import com.ruanyun.web.util.CallbackAjaxDone;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by wangyf on 2017/8/25.
 */
@Controller
@RequestMapping("commonproblem")
public class CommonProblemController extends BaseController {

    @Autowired
    private CommonProblemService commonProblemService;

    /**
     * 功能描述:绑定时间
     */
    @InitBinder
    public void initBinders(WebDataBinder binder) {
        super.initBinder(binder, "yyyy-MM-dd", true);
    }

    @RequestMapping("list")
    public String list(Page<TCommonProblem> page, TCommonProblem commonProblem, Model model) {
        addModel(model, "pageList", commonProblemService.getList(page, commonProblem));
        addModel(model, "bean", commonProblem);
        return "pc/backend/commonproblem/list";
    }

    @RequestMapping("toEdit")
    public String toEdit(String commonProblemNum, Model model) {
        if (EmptyUtils.isNotEmpty(commonProblemNum)) {
            addModel(model, "bean", commonProblemService.get(TCommonProblem.class,"commonProblemNum",commonProblemNum));
        }
        return "pc/backend/commonproblem/edit";
    }
    /**
     * 功能描述:保存和修改
     *
     *
     */
    @RequestMapping("saveOrUpdate")
    public void saveOrUpdate(HttpSession session, Model model, TCommonProblem bean, HttpServletRequest request, HttpServletResponse response, MultipartFile fileName) throws IOException {
        TUser user = HttpSessionUtils.getCurrentUser(session);
        int result = commonProblemService.saveOrUpdate(bean, request,user);
        if(result == 1){
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_","commonproblem/list", "forward"));
        }else{
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "","", ""));
        }
    }
}
