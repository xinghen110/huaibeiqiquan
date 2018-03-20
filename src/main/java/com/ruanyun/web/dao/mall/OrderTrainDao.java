package com.ruanyun.web.dao.mall;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.model.mall.TOrderTrain;
import com.ruanyun.web.model.sys.TUser;
 

@Repository("orderTrainDao")
public class OrderTrainDao extends BaseDaoImpl<TOrderTrain>{
	
	/**
	 * 功能描述: 获取订单转换详细信息	 *
	 * @author yangliu  2016年9月19日 下午5:10:47
	 * 
	 * @param orderNum 订单编号
	 * @param isRequired 是否必须
	 * @return
	 */
	public TOrderTrain getOrderTrainInfo(String outTrainOrder,boolean isRequired){
		TOrderTrain orderTrain=super.get(TOrderTrain.class,"outTrainOrder",outTrainOrder);
		if(isRequired&&orderTrain==null)
			throw new RuanYunException("订单中间表不存在");
		return orderTrain;
	}
	
	public TOrderTrain getOrderTrainByOrderNum(String orderNum){
		String sql="select * from t_order_train where order_num='"+orderNum+"'  order by create_time  desc  limit 1";
		return sqlDao.get(TOrderTrain.class, sql);
	}
	
	public Map<String,TOrderTrain> getorderTrainByorderNum(String orderNum){
		final Map<String,TOrderTrain> map = new HashMap<String, TOrderTrain>();
		String sql ="select * from t_order_train where order_num in ("+SQLUtils.sqlForIn(orderNum)+")";
		jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String orderNum=rs.getString("order_num");
				TOrderTrain orderTrain=new TOrderTrain();
				orderTrain.setOrderNum(orderNum);
				orderTrain.setOutTrainOrder(rs.getString("out_train_order"));
				map.put(orderNum, orderTrain);
			}
		});
		return map;
	}
	
}

