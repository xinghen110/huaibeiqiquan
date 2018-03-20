package com.ruanyun.web.dao.mall;

import org.springframework.stereotype.Repository;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.web.model.mall.TGoodsSale;

@Repository("goodsSaleDao")
public class GoodsSaleDao extends BaseDaoImpl<TGoodsSale>{
	
//	public Page<TAdverInfo> queryPage(Page<TAdverInfo> page,TAdverInfo adverInfo,TUser user){
//		StringBuffer hql = new StringBuffer("select new TAdverInfo(adverInfoId,adverInfoNum,shopNum,adverType,title,linkTel,mainPhoto,status,sortNum,userNum,createTime,city,position)from TAdverInfo where 1=1");
//		if(EmptyUtils.isNotEmpty(adverInfo)){
//			hql.append(SQLUtils.popuHqlEq("position", adverInfo.getPosition()));
//			hql.append(SQLUtils.popuHqlEq("status", adverInfo.getStatus()));
//			hql.append(SQLUtils.popuHqlLike("title", adverInfo.getTitle()));
//			hql.append(SQLUtils.popuHqlEq("adverType", adverInfo.getAdverType()));
//			hql.append(SQLUtils.popuHqlMinDate("createTime", adverInfo.getStartTime()));
//			hql.append(SQLUtils.popuHqlMaxDate("createTime", adverInfo.getCreateTime()));
//		}
//		hql.append(" order by sortNum DESC");
//		return hqlDao.queryPage(page, hql.toString());
//	}
	
	
}

