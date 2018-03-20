package com.ruanyun.web.dao.mall;

import org.springframework.stereotype.Repository;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.mall.TSmsCount;

import com.ruanyun.web.model.sys.TUser;
@Repository("smsCountDao")
public class SmsCountDao extends BaseDaoImpl<TSmsCount> {
	/**
	* 查看分页查看所有短信信息；
 	* @param page
 	* @param smsInfo
 	* @param user
 	* @return
 	*/
	public Page<TSmsCount> queryPage(Page<TSmsCount> page,TSmsCount smsCount,TUser user){
		StringBuffer hql = new StringBuffer("from TSmsCount where 1=1");
		if(EmptyUtils.isNotEmpty(smsCount)){
			
			hql.append(SQLUtils.popuHqlMinDate("createTime", smsCount.getStartTime()));
			hql.append(SQLUtils.popuHqlMaxDate("createTime", smsCount.getCreateTime()));
		}
		
		hql.append(" order by createTime DESC");
		return hqlDao.queryPage(page, hql.toString());
	}
	
	/**
	 * 获取短信信息
	 * @param userNum
	 * @return
	 */
	public TSmsCount getSmsCount(String loginName){
		String sqlString = "select * from t_sms_count where to_days(create_Time)=to_days(now()) and link_tel='"+loginName+"' ";
		return sqlDao.get(TSmsCount.class,sqlString);
		
	}
}
