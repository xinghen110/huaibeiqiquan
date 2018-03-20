package com.ruanyun.web.dao.web;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.TArticle;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明：
 * <p>
 * 时间: 2017/9/30 21:14 Created by ShiXinBing
 */
@Repository("articleDao")
public class ArticleDao extends BaseDaoImpl<TArticle> {

    public Page queryArticleList(Page page, TArticle article, Integer userId) {
        // SELECT * FROM t_article ta INNER JOIN t_user u ON ta.user_id = u.user_id WHERE 1=1 AND ta.user_id=10001 AND ta.media_type=0 AND ta.article_type=1 ORDER BY ta.create_time DESC
        StringBuffer hql = new StringBuffer("from TArticle where 1=1");

        if(EmptyUtils.isNotEmpty(article)){
            hql.append(SQLUtils.popuHqlEq("mediaType", article.getMediaType()));
            hql.append(SQLUtils.popuHqlEq("articleType", article.getArticleType()));
            hql.append(SQLUtils.popuHqlLLike("title",article.getTitle()));
        }
        hql.append("order by createTime desc ");
        return hqlDao.queryPage(page, hql.toString());
    }

    /**
     * 查询文章详情
     * @param articleId      文章id
     * @return
     */
    public TArticle getArticle(Integer articleId) {
        String sql = "select * from t_article where article_id = ?";
        return sqlDao.get(TArticle.class, sql, articleId);
    }

    public Map getPreNextArticle(TArticle article){
        Map map  = new HashMap();
        String presql = "SELECT * FROM t_article WHERE 1=1 and media_type = 1 AND article_id <"+article.getArticleId()+" ORDER BY article_id DESC  LIMIT 0,1";
        map.put("pre",sqlDao.getAll(TArticle.class,presql));
        String nextsql = "SELECT * FROM t_article WHERE 1=1 and media_type = 1 AND article_id >"+article.getArticleId()+" ORDER BY article_id  LIMIT 0,1";
        map.put("next",sqlDao.getAll(TArticle.class,nextsql));
        return map;
    }
}
