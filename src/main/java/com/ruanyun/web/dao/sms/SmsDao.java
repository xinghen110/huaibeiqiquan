package com.ruanyun.web.dao.sms;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.mall.TSmsCount;
import com.ruanyun.web.util.DateUtils;
import com.ruanyun.web.util.NumUtils;
@Repository("smsDao")
public class SmsDao extends BaseDaoImpl<TSmsCount>{
	
	/**
	 * 功能描述:记录法短信信息
	 *
	 * @author yangliu  2013-11-21 上午10:59:32
	 * 
	 * @param sms 信息表
	 * @return
	 */
	public TSmsCount saveSms(TSmsCount sms){
		Session session=openSession();
		Transaction tra=session.beginTransaction();
		session.save(sms);
		sms.setSmsCountNum(NumUtils.getCommondNum("SC", sms.getSmsCountId()));
		session.update(sms);
		tra.commit();
		closeSession(session);
		return sms;
	}
	
	/**
	 * 功能描述: 查询需要发送的短信
	 *
	 * @author yangliu  2017年1月4日 下午4:47:04
	 * 
	 * @return
	 */
	public List<TSmsCount> queryList(){
		StringBuffer hql=new StringBuffer("SELECT * FROM t_sms_count WHERE  send_count<=3 AND send_status=2 ");
		hql.append(SQLUtils.popuHqlMin("create_time", DateUtils.addDay(new Date(),-1)));
		return sqlDao.getAll(TSmsCount.class,hql.toString());
	}
	
}
