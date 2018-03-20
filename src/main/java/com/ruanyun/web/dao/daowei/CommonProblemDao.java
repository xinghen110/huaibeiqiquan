package com.ruanyun.web.dao.daowei;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.daowei.TCommonProblem;
import org.springframework.stereotype.Repository;

/**
 * Created by wangyf on 2017/8/25.
 */
@Repository("commonProblemDao")
public class CommonProblemDao extends BaseDaoImpl<TCommonProblem> {

    /**
     * 功能描述：获取常见协议详情
     * @param page
     * @param commonProblem
     * @return
     */
    public Page<TCommonProblem> querPage(Page<TCommonProblem> page, TCommonProblem commonProblem) {
        StringBuilder hql = new StringBuilder("from TCommonProblem where 1=1 ");
        if (EmptyUtils.isNotEmpty(commonProblem)) {
        }
        hql.append(" order by createTime desc ");
        return hqlDao.queryPage(page, hql.toString());
    }
}
