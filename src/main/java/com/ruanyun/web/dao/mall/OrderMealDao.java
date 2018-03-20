package com.ruanyun.web.dao.mall;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.mall.TOrderMeal;

@Repository("orderMealDao")
public class OrderMealDao extends BaseDaoImpl<TOrderMeal>{
	/**
	 * 功能描述:根据订单编号获取商品信息
	 * @author cqm 2016-10-25 下午07:01:25
	 * @param orderNums
	 * @return
	 */
	public List<TOrderMeal> getGoodsInfoByOrderNums(String orderNums){
		String sql ="select order_goods_num,goods_num,single_actual_price,actual_price,total_price,shop_num,order_num,goods_name,main_photo,market_price,sale_price,goods_count,goods_attribute from t_order_goods where  order_num in ("+SQLUtils.sqlForIn(orderNums)+")";
		return sqlDao.getAll(TOrderMeal.class, sql);
	}
	

	/**
	 * 功能描述:根据订单编号删除
	 * @author cqm  2016-10-31 下午04:32:52
	 * @param orderNum 订单编号
	 * @return
	 */
	public Integer deleteOrderGoods(String orderNum){
		String sqlString = "delete from t_order_goods where order_num='"+orderNum+"'";
		return sqlDao.execute(sqlString);
	}
	/**
	 * 功能描述:获取数量
	 * @author cqm  2016-11-1 下午04:09:35
	 * @param orderNum
	 * @return
	 */
	public List<TOrderMeal> getGoodsCount(String orderNum){
		String sqlString="select goods_count from t_order_goods where order_num='"+orderNum+"'";
		return sqlDao.getAll(TOrderMeal.class,sqlString);
	}
	
	
	/**
	 * 分页查询每件商品销售量
	 */
	public Page<TOrderMeal> queryPage(Page<TOrderMeal> page, TOrderMeal orderGoods) {
		StringBuffer sql = new StringBuffer("SELECT goods_name ,SUM(goods_count) counts,sum(actual_price) actualPrice from t_order_goods WHERE shop_num='"+orderGoods.getShopNum()+"' ");
		if(EmptyUtils.isNotEmpty(orderGoods)){
			sql.append(SQLUtils.popuHqlMinDate("create_time", orderGoods.getStartTime()));
			sql.append(SQLUtils.popuHqlMaxDate("create_time", orderGoods.getCreateTime()));
		}
		sql.append(" GROUP BY goods_num ORDER BY counts DESC");
		return sqlDao.queryPageJdbc(page, TOrderMeal.class, sql.toString());
		
	}
	
	/**
	 * 功能描述:根据订单商品编号查询信息
	 * @author cqm  2016-12-12 下午04:54:55
	 * @param orderGoodsNums
	 * @return
	 */
	public List<TOrderMeal> getGoodsInfoByOrderGoodsNums(String orderGoodsNums){
		String sql ="select order_goods_num,goods_num,single_actual_price,actual_price,total_price,shop_num,order_num,goods_name,main_photo,market_price,sale_price,goods_count,goods_attribute from t_order_goods where  order_goods_num in ("+SQLUtils.sqlForIn(orderGoodsNums)+")";
		return sqlDao.getAll(TOrderMeal.class, sql);
	}
	
	/**
	 * 功能 :获取商品样式属性
	 * zhujingbo
	 * @param orderNum
	 * @return
	 */
	public List<TOrderMeal> getGoodsType(String orderNum){
		String sqlString="SELECT og.*,gt.goods_type_name from t_order_goods og , t_goods_info gi,t_goods_type gt where og.goods_num = gi.goods_num and gi.goods_type_num = gt.goods_type_num and order_num='"+orderNum+"'";
		return sqlDao.getAll(TOrderMeal.class,sqlString);
	}
	
	public Map<String,TOrderMeal> getOrderMealByOrderNum(String orderNums){
		final Map<String,TOrderMeal> map = new HashMap<String, TOrderMeal>();
		String sql ="select * from t_order_meal where order_num in ("+SQLUtils.sqlForIn(orderNums)+")";
		jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String orderNum=rs.getString("order_num");
				TOrderMeal info=new TOrderMeal();
				info.setOrderNum(orderNum);
				info.setMealName(rs.getString("meal_name"));
				info.setMainPhoto(rs.getString("main_photo"));
				map.put(orderNum, info);
			}
		});
		return map;
	}

	
	/**
	 * 店铺查看销售总值
	 * @param shopNum
	 * @return
	 */
	public	Page<TOrderMeal> queryPagegood(Page<TOrderMeal> page, TOrderMeal orderGoods){
		StringBuffer sql=new StringBuffer("select goods_num,goods_name,sum(actual_price) actualPrice,sum(goods_count) goodsCount from t_order_goods tog where tog.shop_num='"+orderGoods.getShopNum()+"' group by goods_num");
		return sqlDao.queryPageJdbc(page, TOrderMeal.class, sql.toString());
	}
	public	Page<TOrderMeal> queryPagegoods(Page<TOrderMeal> page){
		StringBuffer sql=new StringBuffer("select shop_num,sum(actual_price) actualPrice,sum(goods_count) goodsCount from t_order_goods tog group by shop_num");
		return sqlDao.queryPageJdbc(page, TOrderMeal.class, sql.toString());
	}
	
	/**
	 * 功能描述:根据订单编号获取订单商品信息
	 * @author cqm  2017-4-20 下午02:06:02
	 * @param orderNum
	 * @return
	 */
	public List<TOrderMeal> getGoodsInfoList(String orderNum){
		String sql ="select order_goods_num,goods_num,goods_name,goods_count from t_order_goods where  order_num='"+orderNum+"' ";
		return sqlDao.getAll(TOrderMeal.class, sql);
	}

	
}

















