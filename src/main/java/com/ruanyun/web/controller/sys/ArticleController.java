package com.ruanyun.web.controller.sys;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.TArticle;
import com.ruanyun.web.service.web.ArticleService;
import com.ruanyun.web.util.CallbackAjaxDone;
import com.ruanyun.web.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("article/list")
    public String queryList(Page page, TArticle article, Model model){
        model.addAttribute("pageList",articleService.queryArticleList(page,article));
        return "pc/sys/article/list";
    }

    @RequestMapping(value = "article/add",method = RequestMethod.GET)
    public String toAdd(Model model){
        model.addAttribute("url","article/add");
        return "pc/sys/article/add";
    }

    @RequestMapping(value = "article/add",method = RequestMethod.POST)
    public void doAdd(HttpServletResponse response,HttpSession session, TArticle article){
        articleService.save(session,article);
        super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_", "article/list", "forward"));
    }

    @RequestMapping(value = "article/update",method = RequestMethod.GET)
    public String toUpdate(TArticle article,Model model){
        model.addAttribute("url","article/update");
        model.addAttribute("article",articleService.get(TArticle.class,article.getArticleId()));
        return "pc/sys/article/edit";
    }

    @RequestMapping(value = "article/update",method = RequestMethod.POST)
    public void doUpdate(HttpServletResponse response,TArticle article,Model model){
        articleService.update(article);
        super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_", "article/list", "forward"));
    }
}
