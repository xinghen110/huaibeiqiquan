package com.ruanyun.web.service.web;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.web.dao.web.ArticleDao;
import com.ruanyun.web.model.TArticle;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.util.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Service
public class ArticleService extends BaseServiceImpl<TArticle> {
    @Autowired
    private ArticleDao articleDao;

    public Map getPreNextArticle(TArticle article) {
        Map map = articleDao.getPreNextArticle(article);
        return map;
    }

    public Page<TArticle> queryArticleList(Page<TArticle> page, TArticle article) {
        return articleDao.queryArticleList(page, article,0);
    }

    public TArticle save(HttpSession session,TArticle article) {
        TUser user = HttpSessionUtils.getCurrentUser(session);
        article.setUserId(user.getUserId());
        article.setCreateTime(new Date());
        return articleDao.save(article);
    }

    @Override
    public TArticle update(TArticle article) {
        TArticle tArticle = articleDao.getArticle(article.getArticleId());
        tArticle.setArticleType(article.getArticleType());
        tArticle.setTitle(article.getTitle());
        tArticle.setContent(article.getContent());
        return super.update(tArticle);
    }

    public TArticle updateVideoInfo(TArticle article) {
        TArticle tArticle = articleDao.getArticle(article.getArticleId());
        tArticle.setTitle(article.getTitle());
        tArticle.setFileUrl(article.getFileUrl());
        tArticle.setLinkUrl(article.getLinkUrl());
        return super.update(tArticle);
    }
}
