package com.ruanyun.web.controller.daowei;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.daowei.TTypeInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.daowei.TypeInfoService;
import com.ruanyun.web.util.CallbackAjaxDone;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wangyf on 2017/8/4.
 */
@Controller
@RequestMapping("typeinfo")
public class TypeInfoController extends BaseController {

    @Autowired
    private TypeInfoService typeInfoService;

    /**
     * 功能描述:绑定时间
     */
    @InitBinder
    public void initBinders(WebDataBinder binder) {
        super.initBinder(binder, "yyyy-MM-dd", true);
    }

    /**
     * 功能描述：获取主页所有模块
     * @param page
     * @param typeInfo
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("list")
    public String list(Page<TTypeInfo> page, TTypeInfo typeInfo, Model model, HttpSession session) {
        TUser user = HttpSessionUtils.getCurrentUser(session);
        addModel(model, "List", typeInfoService.getList(page, typeInfo).getResult());
        addModel(model, "bean", typeInfo);
        addModel(model, "user", user);
        return "pc/backend/typeinfo/list";
    }

    /**
     * 功能描述：进入模块修改界面，如果没有传入typeId，则输入添加模块界面
     * @param model
     * @param typeNum
     * @return
     */
    @RequestMapping("toEdit")
    public String toEdit(Model model, String typeNum) {
        if (EmptyUtils.isNotEmpty(typeNum)) {
            addModel(model, "bean", typeInfoService.get(TTypeInfo.class, "typeNum", typeNum));
        } else {
            addModel(model, "MaxSort", typeInfoService.getMaxSortNum());
        }
        return "pc/backend/typeinfo/edit";
    }

    /**
     * 功能描述：保存或更新模块内容
     * @param session
     * @param model
     * @param request
     * @param response
     * @param typeInfo
     * @throws Exception
     */
    @RequestMapping("saveOrUpdate")
    public void saveOrUpdate(HttpSession session, Model model, HttpServletRequest request,HttpServletResponse response, TTypeInfo typeInfo) throws Exception {
        TUser user = HttpSessionUtils.getCurrentUser(session);
        int result = typeInfoService.saveOrUpdate(typeInfo, request, user);
        if (result == 1) {
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS, "main_", "typeinfo/list", "forward"));
        } else {
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "", "", ""));
        }
    }

    /**
     * 功能描述：删除分类
     * @param typeNum
     * @param response
     */
    @RequestMapping("delete")
    public void delete(String typeNum, HttpServletResponse response) {
        int result = 0;
        if (EmptyUtils.isNotEmpty(typeNum)) {
            TTypeInfo typeInfo = typeInfoService.get(TTypeInfo.class, "typeNum", typeNum);
            result = typeInfoService.delete(typeInfo);
        }
        if (result == 1) {
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS, "main_", "mealinfo/list", "redirect"));
        } else {
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "", "", ""));
        }
    }


}
