package com.ruanyun.web.dao.daowei;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.daowei.TJishiInfo;
import com.ruanyun.web.model.daowei.TMealInfo;

import org.springframework.stereotype.Repository;


@Repository("jishiInfoDao")
public class JishiInfoDao extends BaseDaoImpl<TJishiInfo> {


	 public Page<TJishiInfo> querPage(Page<TJishiInfo> page, TJishiInfo jishiInfo) {
	        StringBuilder hql = new StringBuilder(" from TJishiInfo where 1=1 ");
	        if (EmptyUtils.isNotEmpty(jishiInfo)) {
	            hql.append(SQLUtils.popuHqlEq("shopInfoNum", jishiInfo.getShopInfoNum()));
	            hql.append(SQLUtils.popuHqlLike("userName", jishiInfo.getUserName()));
	            hql.append(SQLUtils.popuHqlEq("userSex", jishiInfo.getUserSex()));
	        }
	        hql.append(" order by createTime DESC");
	        return hqlDao.queryPage(page, hql.toString());
	 }

}
