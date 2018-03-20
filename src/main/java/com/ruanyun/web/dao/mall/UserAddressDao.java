package com.ruanyun.web.dao.mall;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.mall.TGoodsInfo;
import com.ruanyun.web.model.mall.TUserAddress;
import com.ruanyun.web.model.sys.TUser;

@Repository("userAddressDao")
public class UserAddressDao extends BaseDaoImpl<TUserAddress>{
	
	public Page<TUserAddress> queryPage(Page<TUserAddress> page,TUserAddress userAddress,TUser user){
		StringBuffer hql = new StringBuffer("from TUserAddress where 1=1");
		if(EmptyUtils.isNotEmpty(userAddress)){
//			hql.append(SQLUtils.popuHqlEq("areas", userAddress.getAreas()));
//			hql.append(SQLUtils.popuHqlEq("city", userAddress.getCity()));
			hql.append(SQLUtils.popuHqlCityEq("city", "area", userAddress.getCity()));
			hql.append(SQLUtils.popuHqlEq("province", userAddress.getProvince()));
			hql.append(SQLUtils.popuHqlLike("linkMan", userAddress.getLinkMan()));
			hql.append(SQLUtils.popuHqlLike("linkTel", userAddress.getLinkTel()));
			hql.append(SQLUtils.popuHqlEq("userNum", userAddress.getUserNum()));
		}
		hql.append(" order by isDeafult asc ");
		return hqlDao.queryPage(page, hql.toString());
	}
	
	/**
	 * 功能描述: 修改 所有地址为非默认地址
	 * @author cqm  2016-10-22 下午05:15:35
	 * @param userNum
	 * @return
	 */
	public int updateAddressNoDefault(String userNum){
		String sqlString="update  TUserAddress set  isDeafult = 2  where  userNum ='"+userNum+"'";
		return hqlDao.execute(sqlString);
	}
	
	/**
	 * 功能描述:根据当前用户获取默认地址
	 * @author cqm  2016-10-31 下午06:29:13
	 * @param userNum 用户编码
	 * @return
	 */
	public TUserAddress getIsDeafult(String userNum){
		String sql ="select * from t_user_address where  user_num='"+userNum+"'";
		System.out.println(sql);
		return sqlDao.get(TUserAddress.class, sql);
	}
	
		
}

