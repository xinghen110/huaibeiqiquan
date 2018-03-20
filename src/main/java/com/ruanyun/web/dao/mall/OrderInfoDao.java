package com.ruanyun.web.dao.mall;


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.mall.TOrderInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.util.ConstantAuth;
import com.ruanyun.web.util.SecurityUtils;

@Repository("orderInfoDao")
public class OrderInfoDao extends BaseDaoImpl<TOrderInfo>{
	
	/**
	 * 功能描述:查询订单数据
	 * @author cqm  2017-8-8 下午03:23:22
	 * @param page
	 * @param orderInfo
	 * @return
	 */
	public Page<TOrderInfo> queryPage(Page<TOrderInfo> page,TOrderInfo orderInfo){
		StringBuffer hql = new StringBuffer(" from TOrderInfo where 1=1");
		if(EmptyUtils.isNotEmpty(orderInfo)){
			hql.append(SQLUtils.popuHqlEq("orderNum", orderInfo.getOrderNum()));
			hql.append(SQLUtils.popuHqlEq("orderUserName", orderInfo.getOrderUserName()));
			hql.append(SQLUtils.popuHqlEq("orderLinkMan", orderInfo.getOrderLinkMan()));
			hql.append(SQLUtils.popuHqlEq("userNum", orderInfo.getUserNum()));
			hql.append(SQLUtils.popuHqlEq("shopNum", orderInfo.getShopNum()));
			hql.append(SQLUtils.popuHqlEq("orderType", orderInfo.getOrderType()));
			hql.append(SQLUtils.popuHqlEq("orderStatus", orderInfo.getOrderStatus()));
			hql.append(SQLUtils.popuHqlEq("orderStatus", orderInfo.getOrderStatusString()));
			hql.append(SQLUtils.popuHqlMinDate("payTime", orderInfo.getPaystartTime()));
			hql.append(SQLUtils.popuHqlMaxDate("payTime", orderInfo.getPayTime()));
			hql.append(SQLUtils.popuHqlEq("payMethod", orderInfo.getPayMethod()));
		}
		if(EmptyUtils.isNotEmpty(orderInfo.getOrderStatusString())){
			 
			if("-1".equals(orderInfo.getOrderStatusString())){   //取消订单
				 
				hql.append(" order by cancelTime DESC");
			}else{
				hql.append(" order by orderCreateTime DESC");
			}
		} else {
			hql.append(" order by orderCreateTime DESC");
		}
		
		return hqlDao.queryPage(page,hql.toString());
	}

	/**
	 * 功能描述:查询分销提成列表信息
	 * @param page
	 * @param orderInfo
	 * @return
	 */
	public Page<TOrderInfo> getFXList(Page<TOrderInfo> page,TOrderInfo orderInfo) {
		StringBuilder sql = new StringBuilder(" select toi.* ");
		sql.append(" from t_order_info toi where toi.order_type  = 2 and toi.order_status = 6 ");
		if (EmptyUtils.isNotEmpty(orderInfo)) {
			sql.append(SQLUtils.popuHqlEq("toi.order_num", orderInfo.getOrderNum()));
			sql.append(SQLUtils.popuHqlMinDate("toi.pay_time", orderInfo.getPaystartTime()));
			sql.append(SQLUtils.popuHqlMaxDate("toi.pay_time", orderInfo.getPayTime()));
			sql.append(SQLUtils.popuHqlEq("toi.pay_method", orderInfo.getPayMethod()));
			if (EmptyUtils.isNotEmpty(orderInfo.getOrderUserName()))
				sql.append(" AND exists( SELECT 1 FROM t_user tu where toi.user_num = tu.user_num and tu.nick_name = '"+orderInfo.getOrderUserName()+"')");
		}
		sql.append(" AND exists( SELECT 1 FROM t_user_record tur where toi.order_num = tur.order_num and consum_type = 7)");
		sql.append(" order by toi.order_create_time desc");
		return sqlDao.queryPage(page, TOrderInfo.class, sql.toString());
	}



	
	/**
	 * 功能描述:根据优惠卷编码查询订单信息
	 * @author cqm  2016-11-24 下午02:37:01
	 * @param UserCouponNum
	 * @return
	 */
	public List<TOrderInfo> getOrderInfoCouponList(String UserCouponNum){
		String sqlString="select * from t_order_info where coupon_num='"+UserCouponNum+"'";
		return sqlDao.getAll(TOrderInfo.class, sqlString);
	}
	/**
	 * 功能描述:修改订单流水状态
	 * @author cqm 2016-10-25 下午03:16:32
	 * @param orderStatus
	 * @param orderNum
	 * @return
	 */
	public int updateStatus(Integer orderStatus,String orderNum){
		String sqlString = "update t_order_record set order_status="+orderStatus+" where order_num='"+orderNum+"'";
		return sqlDao.execute(sqlString);
	}
	
	/**
	 * 功能描述:修改订单状态
	 * @author cqm  2016-11-3 下午02:48:49
	 * @param orderNum 订单编号
	 * @param orderStatus 订单状态
	 * @return
	 */
	public int updateOrderStatus(String orderNum,Integer orderStatus){
		String sqlString = "update t_order_info set order_status="+orderStatus+" where order_num='"+orderNum+"'";
		return sqlDao.execute(sqlString);
	}
	
	/**
	 * 功能描述:查看订单（查询条件）
	 * @author wsp  2016-10-12 上午11:34:46
	 * @param orderInfo
	 * @param params
	 * @return
	 */
	public List<TOrderInfo> getList(TOrderInfo orderInfo,Map<String,Object> params,String ids,TUser user){
			StringBuffer sql = new StringBuffer("from TOrderInfo where 1=1 ");
			if(EmptyUtils.isNotEmpty(ids)){
				sql.append(SQLUtils.popuHqlIn("orderNum", SQLUtils.sqlForIn(ids)));
			}else {
				if(orderInfo != null){
					if(EmptyUtils.isNotEmpty(orderInfo.getOrderNum()))
					sql.append(SQLUtils.popuHqlEq("orderNum", orderInfo.getOrderNum(),params));
					
					sql.append(SQLUtils.popuHqlLike("shopNum", orderInfo.getShopNum(),params));
					sql.append(SQLUtils.popuHqlLike("orderLoginName", orderInfo.getOrderLoginName(),params));
					sql.append(SQLUtils.popuHqlLike("orderUserName", orderInfo.getOrderUserName(),params));
					
					sql.append(SQLUtils.popuHqlEq("orderStatus", orderInfo.getOrderStatus(),params));
					sql.append(SQLUtils.popuHqlEq("userNum", orderInfo.getUserNum(),params));
					sql.append(SQLUtils.popuHqlEq("userNum", orderInfo.getUserNum(),params));
				}
				if(SecurityUtils.isGranted(ConstantAuth.SHOP_AUTH, user)){   //店铺角色
					sql.append(SQLUtils.popuHqlEq("shopNum", user.getShopNum()));
				}
			}
			sql.append(" order by orderCreateTime desc ");
			return hqlDao.getAll(sql.toString(),params);
		}
	 

	/**
	 * 根据 店铺编号 和 订单状态  查询订单数
	 * @param shopNum
	 * @param orderStatus
	 * @return
	 */
	public Integer getCount(String shopNum ,Integer orderStatus){
		String sql = "SELECT ifnull(COUNT(order_id),0) from t_order_info where shop_num = '"+shopNum+"' and order_status = "+orderStatus;
		return sqlDao.getCount(sql);
	}

	
	 
	
	public int updateOrderStatus2(String orderNum){
		
		String roomSql="update t_order_info  set order_status=4   where order_num = '"+orderNum+"'  ";
		
		return sqlDao.execute(roomSql);
	} 
	
	
	public List<TOrderInfo> list(TOrderInfo orderInfo){
		String sql ="SELECT toe.logistics_name as logisticsName, COUNT(1) as rs  FROM t_order_express toe,t_order_info toi WHERE toi.order_num= toe.order_num " 
					+" and toi.pay_time IS NOT NULL AND toi.order_status IN (4,5,6) AND toi.order_type="+orderInfo.getOrderType()+" AND toe.logistics_status IN (3,4)";
					if(EmptyUtils.isNotEmpty(orderInfo.getPaystartTime())&&EmptyUtils.isNotEmpty(orderInfo.getPayTime())){
						sql+=SQLUtils.popuHqlMinDate("toi.pay_time", orderInfo.getPaystartTime());
						sql+=SQLUtils.popuHqlMaxDate("toi.pay_time", orderInfo.getPayTime());
					}
					sql+=" GROUP BY toe.logistics_name";
		return sqlDao.getAll(sql.toString());
	}

	/**
	 * 功能描述：根据用户编号查询首单
	 * @param userNums
	 * @return
	 */
	public List<TOrderInfo> getFirstCardFeeOrderListByUserNums(String userNums) {
		StringBuilder sql = new StringBuilder(" SELECT toi.user_num, toi.actual_price FROM t_order_info toi WHERE toi.order_type = 2 AND toi.order_status = 6 ");
		sql.append(" AND exists( SELECT 1 FROM t_user_record tur where tur.order_num = toi.order_num and tur.consum_type = 7 ) ");
		sql.append(" and toi.user_num in ("+SQLUtils.sqlForIn(userNums)+")");
		return sqlDao.getAll(TOrderInfo.class, sql.toString());
	}

	/**
	 * 功能描述：根据用户编号查询首单
	 * @param userNums
	 * @return
	 */
	public Integer getFirstCardFeeTotalPriceByUserNums(String userNums) {
		StringBuilder sql = new StringBuilder(" SELECT SUM(toi.actual_price) FROM t_order_info toi WHERE toi.order_type = 2 AND toi.order_status = 6 ");
		sql.append(" AND exists( SELECT 1 FROM t_user_record tur where tur.order_num = toi.order_num and tur.consum_type = 7 ) ");
		sql.append(" and toi.user_num in ("+SQLUtils.sqlForIn(userNums)+")");
		Session session=openSession();
		BigDecimal obj= (BigDecimal) session.createSQLQuery(sql.toString()).uniqueResult();
		closeSession(session);
		if (obj != null) {
			return obj.intValue();
		} else {
			return 0;
		}
	}

	/**
	 * 功能描述：根据用户编号获取购买会员卡的数量 返回值为map key为userNum value为数量
	 * @param userNums
	 * @return
	 */
	public Map<String, Integer> getCFOrderCount(String userNums) {
		final Map<String, Integer> map = new HashMap<String, Integer>();
		String sql = "SELECT COUNT(order_num) AS count, user_num FROM t_order_info WHERE order_type=2 AND order_status=6 AND user_num IN ("+SQLUtils.sqlForIn(userNums)+")  GROUP BY user_num";
		jdbcTemplate.query(sql, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				map.put(rs.getString("user_num"), rs.getInt("count"));
			}
		});
		return map;
	}
}





















