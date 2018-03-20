package com.ruanyun.web.dao.daowei;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.daowei.TTypeInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangyf on 2017/8/4.
 */
@Repository
public class TypeInfoDao extends BaseDaoImpl<TTypeInfo> {

    /**
     * 功能描述：获取首页模块
     * @param page page对象
     * @param typeInfo
     * @return
     */
    public Page<TTypeInfo> queryPage(Page<TTypeInfo> page, TTypeInfo typeInfo) {
        StringBuilder hql = new StringBuilder("from TTypeInfo where 1=1");
        if (EmptyUtils.isNotEmpty(typeInfo)) {

        }
        hql.append(" order by sortNum asc ");
        return hqlDao.queryPage(page, hql.toString());
    }

    /**
     * 获取排序的最大值
     * @return
     */
    public Integer getMaxSortNum() {
        String sql= "select ifnull(max(sort_num),0) from t_type_info";
        return sqlDao.getCount(sql);
    }
    
	/**
	 * 功能描述:获取类型
	 * @author cqm  2017-8-7 上午10:40:10
	 * @return
	 */
	public List<TTypeInfo> getTypeList(){
		StringBuffer hql = new StringBuffer(" select * from t_type_info where 1=1 ");
		hql.append(" order by sort_num asc ");
		return sqlDao.getAll(TTypeInfo.class, hql.toString());
	}

    /**
     * 功能描述：获取非至尊会员的记录
     * @param typeId
     * @return
     */
    public List<TTypeInfo> getTypes(Integer typeId) {
        StringBuffer hql = new StringBuffer(" select * from t_type_info where 1=1 and type_id != "+typeId+" and type_num != 'TI54570000000009'");
        hql.append(" order by sort_num asc ");
        return sqlDao.getAll(TTypeInfo.class, hql.toString());
    }
}
