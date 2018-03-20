package com.ruanyun.web.dao.daowei;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.daowei.TFeedBack;
import org.springframework.stereotype.Repository;

/**
 * Created by wangyf on 2017/8/5.
 */
@Repository("feedBackDao")
public class FeedBackDao extends BaseDaoImpl<TFeedBack> {

    public Page<TFeedBack> querPage(Page<TFeedBack> page, TFeedBack feedBack) {
        StringBuilder hql = new StringBuilder("from TFeedBack where 1=1 ");
        if (EmptyUtils.isNotEmpty(feedBack)) {
            hql.append(SQLUtils.popuHqlEq("linkTel", feedBack.getLinkTel()));
            hql.append(SQLUtils.popuHqlMinDate("createTime", feedBack.getStartTime()));
            hql.append(SQLUtils.popuHqlMaxDate("createTime", feedBack.getCreateTime()));
        }
        hql.append(" order by createTime desc ");
        return hqlDao.queryPage(page, hql.toString());
    }
}
