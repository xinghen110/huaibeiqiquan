package com.ruanyun.web.dao.mall;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.mall.TCommentInfo;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wangyf on 2017/8/4.
 */
@Repository("commentInfoDao")
public class CommentInfoDao extends BaseDaoImpl<TCommentInfo> {

    /**
     * 功能描述：获取常见协议详情
     * @param page
     * @param commentInfo
     * @return
     */
    public Page<TCommentInfo> querPage(Page<TCommentInfo> page, TCommentInfo commentInfo) {
        StringBuilder sql = new StringBuilder(" select * from t_comment_info tci where 1=1 ");
        if (EmptyUtils.isNotEmpty(commentInfo)) {
            sql.append(SQLUtils.popuHqlEq("tci.meal_name", commentInfo.getMealName()));
            sql.append(SQLUtils.popuHqlMinDate("tci.create_time", commentInfo.getStartTime()));
            sql.append(SQLUtils.popuHqlMaxDate("tci.create_time", commentInfo.getCreateTime()));
            if (EmptyUtils.isNotEmpty(commentInfo.getFlag1()))
                sql.append(" AND exists( SELECT 1 FROM t_user tu where 1=1 and tci.user_num=tu.user_num and tu.nick_name ='"+commentInfo.getFlag1()+"')");
        }
        sql.append(" order by tci.update_time desc,tci.create_time desc ");
        return sqlDao.queryPage(page, TCommentInfo.class, sql.toString());
    }

    /**
     * 功能描述：根据 mealNum 获取从 numStart 的 num 条中间数据
     * @param mealNum
     * @param numStart
     * @param num
     * @return
     */
    public List<TCommentInfo> getSomeListByMealNum(String mealNum, Integer numStart, Integer num) {
        StringBuilder sql = new StringBuilder("select * from t_comment_info where status=1 and meal_num = '"+mealNum+"' order by create_time desc ");
        sql.append("limit "+numStart+","+num+" ");
        return sqlDao.getAll(TCommentInfo.class, sql.toString());
    }

    /**
     * 功能描述：根据 mealNum 获得总共评论数量
     * @param mealInfoNum
     * @return
     */
    public Integer getCountByMealInfoNum(String mealInfoNum) {
        String sql = "SELECT COUNT(*) FROM t_comment_info where status=1 and meal_num = '"+mealInfoNum+"'";
        return sqlDao.getCount(sql);
    }

    /**
     * 功能描述：根据 shopNum 获取从 numStart 的 num 条中间数据
     * @param shopNum
     * @param numStart
     * @param num
     * @return
     */
    public List<TCommentInfo> getSomeListByShopNum(String shopNum, Integer numStart, Integer num) {
        StringBuilder sql = new StringBuilder("select * from t_comment_info where status=1 and shop_num = '"+shopNum+"' order by create_time desc ");
        sql.append("limit "+numStart+","+num+" ");
        return sqlDao.getAll(TCommentInfo.class, sql.toString());
    }

    /**
     * 功能描述：根据 shopNum 获得总共评论数量
     * @param shopNum
     * @return
     */
    public Integer getCountByShopNum(String shopNum) {
        String sql = "SELECT COUNT(*) FROM t_comment_info where status=1 and shop_num = '"+shopNum+"'";
        return sqlDao.getCount(sql);
    }

    /**
     * 功能描述： 根据 commentId 更改显示状态
     */
    public Integer updateStatus(String[] commentId, Integer type) {
        StringBuilder sql = new StringBuilder("update t_comment_info set status="+type+", update_time=now() where 1=1 ");
        sql.append("and comment_id in( ");
        for (int i = 0; i < commentId.length; i++) {
            sql.append("'"+commentId[i]+"'");
            if ((i+1)<commentId.length) {
                sql.append(",");
            }
        }
        sql.append(" )");
        return sqlDao.update(sql.toString());
    }

    /**
     * 功能描述： 根据 commentId 删除
     */
    public Integer deleteComment(String[] commentId) {
        StringBuilder sql = new StringBuilder("delete from t_comment_info where 1=1 ");
        sql.append("and comment_id in( ");
        for (int i = 0; i < commentId.length; i++) {
            sql.append("'"+commentId[i]+"'");
            if ((i+1)<commentId.length) {
                sql.append(",");
            }
        }
        sql.append(" )");
        return sqlDao.execute(sql.toString());
    }

    /**
     * 功能描述：更新星级和内容
     * @param commentNum
     * @param score
     * @param content
     * @return
     */
    public Integer updateScoreAndContent(String commentNum, String score, String content) {
        String sql = "update t_comment_info set score="+score+" , content='"+content+"' , update_time=now() where comment_num='"+commentNum+"'";
        return sqlDao.execute(sql);
    }
}
