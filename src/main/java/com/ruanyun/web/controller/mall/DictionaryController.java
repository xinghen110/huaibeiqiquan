package com.ruanyun.web.controller.mall;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.sys.TDictionary;
import com.ruanyun.web.service.sys.DictionaryService;
import com.ruanyun.web.util.CallbackAjaxDone;
import com.ruanyun.web.util.Constants;

/**
 * 字典表
 * @author Administrator
 *
 */
@Controller
@RequestMapping("dictionary")
public class DictionaryController extends BaseController {
	
	@Autowired
	private DictionaryService dictionaryService;
	
	/**
	 * 功能描述:绑定时间
	 */
	@InitBinder
	public void initBinders(WebDataBinder binder) {
		super.initBinder(binder, "yyyy-MM-dd", true);
	}
	
	/**
	 * 功能描述:跳转到list页面
	 * @author zhujingbo
	 * @param model
	 * @return
	 */
	@RequestMapping("newList")
	public String getList(Model model){
		addModel(model, "authList", dictionaryService.getList());
		return "pc/backend/dictionary/newlist";
	}
	
	/**
	 * 进入字典表查询
	 * @param model
	 * @param dictionary
	 * @return
	 */
	@RequestMapping("toList")
	public String toList(Model model,TDictionary dictionary){
		addModel(model, "dictionaryList", dictionaryService.getDictionaryList(dictionary));
		addModel(model, "bean", dictionaryService.getAllByCondition(TDictionary.class,"parentCode",dictionary.getParentCode()).get(0));
		return "pc/backend/dictionary/list";
	}
	
	
	@RequestMapping("toEdit")
	public String toEdit(Model model,TDictionary dictionary){
		if(EmptyUtils.isEmpty(dictionary.getId())){
			addModel(model, "num", dictionaryService.getOrderby(dictionary));
			addModel(model, "bean", dictionary);
		}else{
			addModel(model, "bean", dictionaryService.get(TDictionary.class, dictionary.getId()));
		}
		return "pc/backend/dictionary/edit";
	}
	
	@RequestMapping("saveOrUpdate")
	public void saveOrUpdate(Model model,TDictionary dictionary,HttpServletResponse response,Integer type){
		int result = dictionaryService.saveOrUpdate(dictionary,null);
		
		if(result == 1){
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS,"","jbsxBox","","closeCurrent"));
		}else{
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED,"","jbsxBox","","closeCurrent"));
		}
	}
	
	
	@RequestMapping("del")
	public void del(Integer id,HttpServletResponse response) {
		TDictionary dictionary = dictionaryService.get(TDictionary.class, id);
		if(EmptyUtils.isEmpty(dictionary)){
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED,"","",""));
			return;
		}
		
		List<TDictionary>dictionarieList = dictionaryService.getAllByCondition(TDictionary.class, "parentCode", dictionary.getParentCode());
		if(EmptyUtils.isNotEmpty(dictionarieList) && dictionarieList.size() <= 1){
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE,"无法删除最后一项","","",""));
			return;
		}
		
		int result = 0;
		if(dictionary.getIsRead()==3){//3最大权限，增删改 
			result = dictionaryService.delete(dictionary);
		}
		if(result == 1){
			System.out.println("11");
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS,"","jbsxBox","","redirect"));
		}else{
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED,"","","",""));
		}

	}
	
}
