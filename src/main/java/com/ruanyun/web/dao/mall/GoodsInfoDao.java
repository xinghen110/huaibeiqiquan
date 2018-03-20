package com.ruanyun.web.dao.mall;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.mall.TGoodsInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.util.ConstantAuth;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.SecurityUtils;

@Repository("goodsInfoDao")
public class GoodsInfoDao extends BaseDaoImpl<TGoodsInfo>{
	
	public Page<TGoodsInfo> queryPage(Page<TGoodsInfo> page,TGoodsInfo goodsInfo,TUser user,String type){
		StringBuffer hql = new StringBuffer("select `goods_id`,`goods_num`,`shop_num`,`goods_name`,`vite_name`,`parent_num`,`goods_type_num`,`main_photo`,`market_price`,`sale_price`,`goods_status`,`is_essxsdh`,`is_qtkt`,`is_home`,`is_new`,`month_sale_count`,`comment_count`,`brand_num`,`unit`,`create_time`,`user_num`,`sale_count` from t_goods_info where 1=1");
		if(EmptyUtils.isNotEmpty(goodsInfo)){
			hql.append(SQLUtils.popuHqlLike("goods_name", goodsInfo.getGoodsName()));
			hql.append(SQLUtils.popuHqlEq("is_new", goodsInfo.getIsNew()));//最新商品
			hql.append(SQLUtils.popuHqlEq("goods_status", goodsInfo.getGoodsStatus()));
			hql.append(SQLUtils.popuHqlEq("is_home", goodsInfo.getIsHome()));
			hql.append(SQLUtils.popuHqlEq("shop_num", goodsInfo.getShopNum()));
			hql.append(SQLUtils.popuHqlEq("parent_num", goodsInfo.getParentNum()));
			hql.append(SQLUtils.popuHqlEq("goods_type_num", goodsInfo.getGoodsTypeNum()));
//     		hql.append(SQLUtils.popuHqlEq("goods_type_num",NumUtils.clearNum(goodsInfo.getGoodsNum(), 2)));
			hql.append(SQLUtils.popuHqlMinDate("create_time", goodsInfo.getStartTime()));
			hql.append(SQLUtils.popuHqlMaxDate("create_time", goodsInfo.getCreateTime()));
			hql.append(SQLUtils.popuHqlCityEq("city", "area", goodsInfo.getCity()));
		}
		if(user!=null &&SecurityUtils.isGranted(ConstantAuth.SHOP_AUTH, user)){   //店铺角色
			hql.append(SQLUtils.popuHqlEq("shop_num", user.getShopNum()));
		}
		if("1".equals(type)){
			hql.append(" and exists (select 1 from t_flow_info where common_num=goods_num and user_num='"+user.getUserNum()+"')");
		}
		if(EmptyUtils.isEmpty(page.getOrder())){
			hql.append(" order by create_Time DESC");
		}
		return sqlDao.queryPage(page,TGoodsInfo.class, hql.toString());
	}
	
	/**
	 * 功能描述:获取当前店铺下所有商品
	 * @author cqm  2017-3-6 下午06:50:54
	 * @param page
	 * @param shopNum
	 * @return
	 */
	public Page<TGoodsInfo> getGoodsInfos(Page<TGoodsInfo> page,String shopNum,Integer status){
		StringBuffer hql = new StringBuffer("select `goods_id`,`goods_num`,`shop_num`,`goods_name`,`vite_name`,`parent_num`,`goods_type_num`,`main_photo`,`market_price`,`sale_price`,`goods_status`,`is_essxsdh`,`is_qtkt`,`is_home`,`is_new`,`month_sale_count`,`comment_count`,`brand_num`,`unit`,`create_time`,`user_num`,`sale_count` from t_goods_info where 1=1");
		if(EmptyUtils.isNotEmpty(status)){
			hql.append("  and goods_status="+status+"  ");
		}
		hql.append(SQLUtils.popuHqlEq("shop_num", shopNum));
		return sqlDao.queryPage(page,TGoodsInfo.class, hql.toString());
	}
		
	/**
	 * 功能描述:根据用户编号获取用户信息 返回值为map key为goodsNums value 为 TGoodsInfo对象
	 * @author wsp  2016-10-17 下午06:42:00
	 * @param goodsNums
	 * @return
	 */
	public Map<String,TGoodsInfo> getGoodsInfoByGoodsNum(String goodsNums){
		final Map<String,TGoodsInfo> map = new HashMap<String, TGoodsInfo>();
		String sql ="select * from t_goods_info where goods_num in ("+SQLUtils.sqlForIn(goodsNums)+")";
		jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String goodsNum=rs.getString("goods_num");
				TGoodsInfo info=new TGoodsInfo();
				info.setGoodsNum(goodsNum);
				info.setGoodsName(rs.getString("goods_name"));
				info.setMainPhoto(rs.getNString("main_photo"));
				info.setSalePrice(rs.getBigDecimal("sale_price"));
				info.setMarketPrice(rs.getBigDecimal("market_price"));
				info.setBrandNum(rs.getString("brand_num"));
				info.setCommentCount(rs.getInt("comment_count"));
				info.setCreateTime(rs.getDate("create_time"));
				info.setGoodsId(rs.getInt("goods_id"));
				info.setGoodsNum(rs.getString("goods_num"));
				info.setGoodsTypeName(rs.getString("goods_type_num"));
				info.setUnit(rs.getString("unit"));
				info.setSaleCount(rs.getInt("sale_count"));
				map.put(goodsNum, info);
			}
		});
		return map;
	}
	
	/**
	 * 查看某个店铺下下架的商品
	 * @param shopNum
	 * @return
	 */
	public List<TGoodsInfo> getEnableGoodsList(String shopNum){
		String hql="from TGoodsInfo where goodsId in ("+SQLUtils.sqlForIn(shopNum)+") and goodsStatus=2 ";
		return hqlDao.getAll(hql);
	}
	
	/**
	 * 功能描述:查询最新商品
	 * @author cqm  2016-12-3 下午01:48:32
	 * @param goodsInfo
	 * @return
	 */
	public List<TGoodsInfo> getGoodsList(TGoodsInfo  goodsInfo){
		String sqlString="select * from t_goods_info where goods_status=1 and shop_num='"+goodsInfo.getShopNum()+"' order by create_Time DESC limit 0,10;";
		return sqlDao.getAll(TGoodsInfo.class,sqlString);
	}
	
	
	/**
	 * 查看某个店铺勾选首页显示的商品
	 * @param shopNum
	 * @return
	 */
	public List<TGoodsInfo> getGoodsInfossize(String shopNum){
		String hql="from TGoodsInfo where goodsId in ("+SQLUtils.sqlForIn(shopNum)+")";
		return hqlDao.getAll(hql);
	}
	
	public List<TGoodsInfo> getGoodsInfosIsHome(String shopNum){
		String hql ="from TGoodsInfo where shopNum in("+SQLUtils.sqlForIn(shopNum)+") and isHome=1";
		return hqlDao.getAll(hql);
	}
	
	
	
	public Map<String,List<TGoodsInfo>> getGoodsInfoByShopNums(String shopNums){
		final Map<String,List<TGoodsInfo>> map = new HashMap<String, List<TGoodsInfo>>();
		String sql="select * from t_goods_info where shop_num in ("+SQLUtils.sqlForIn(shopNums)+") and is_home=1 and goods_status=1 ORDER BY update_time desc ";   //获取最新更新为首页显示的4条信息
		jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String goodsNum=rs.getString("goods_num");
				String shopNum=rs.getString("shop_num");
				TGoodsInfo info=new TGoodsInfo();
				info.setGoodsNum(goodsNum);
				info.setGoodsName(rs.getString("goods_name"));
				info.setMainPhoto(rs.getNString("main_photo"));
				info.setSalePrice(rs.getBigDecimal("sale_price"));
				info.setMarketPrice(rs.getBigDecimal("market_price"));
				info.setBrandNum(rs.getString("brand_num"));
				info.setCommentCount(rs.getInt("comment_count"));
				info.setCreateTime(rs.getDate("create_time"));
				info.setGoodsId(rs.getInt("goods_id"));
				info.setGoodsNum(goodsNum);
				info.setGoodsTypeName(rs.getString("goods_type_num"));
				info.setShopNum(shopNum);
				info.setUnit(rs.getString("unit"));
				if(map.get(shopNum)==null){
					map.put(shopNum, new ArrayList<TGoodsInfo>());
				}
				map.get(shopNum).add(info);
			}
		});
		return map;
	}

	/**
	 * 功能描述:获取店铺下已上架的商品
	 * @author wsp  2016-10-21 下午02:34:06
	 * @param shopNum
	 * @return
	 */
	public Integer getTotalCount(String shopNum) {
		String count = "select count(goods_id) from t_goods_info where shop_num='"+shopNum+"' and goods_status= '1'";
		return sqlDao.getCount(count);
	}
	
	/**
	 * 功能描述:上架 下架
	 * @author cqm  2016-11-11 下午03:27:37
	 * @param goodsNum 商品编号
	 * @param goodsStatus 商品状态
	 * @return
	 */
	public int updateGoodsStatus(String goodsNum,Integer goodsStatus){
		String sqlString = "update t_goods_info set goods_status="+goodsStatus+" where goods_num='"+goodsNum+"'";
		return sqlDao.execute(sqlString);
	}
	
//	/**
//	 * 
//	 * @param shopNum,
//	 * @return
//	 */
//	public List<TGoodsInfo> getGoodsInfosIsHome(String shopNum){
//		String hql ="from TGoodsInfo where shopNum in("+SQLUtils.sqlForIn(shopNum)+") and isHome=1";
//		return hqlDao.getAll(hql);
//	}

//获取月销量
	public Integer getMonthSaleCount(String goodsNum){
		String sqlString = "SELECT COALESCE(sum(goods_count),0) as monthSaleCount FROM t_order_goods WHERE  date_format(create_time,'%Y%m') and  goods_num='"+goodsNum+"'";
		return sqlDao.getCount(sqlString);
	}
	
	public int updateQuery(String filedValue,String filed,String queryFiledValue,String tableName,String queryFiled){
		String sqls= "update "+tableName+" set "+filed+" = "+filedValue+" where "+queryFiled+"='"+queryFiledValue+"'";
		sqlDao.update(sqls);
		return 1;
	}
	
	/**
	 * 功能描述:查询当前店铺下所有商品
	 * @author cqm  2017-3-11 下午06:22:12
	 * @return
	 */
	public List<TGoodsInfo> getGoodsInfoList(String shopNum){
		StringBuffer hql = new StringBuffer("select tgi.shop_num,tgi.parent_num,tgi.main_photo,tgi.goods_num,tgi.sale_price,tgi.goods_type_num,tgi.goods_name,tgi.goods_status, ");
		hql.append("  tgi.month_sale_count,tgi.market_price,tgi.city,tgi.area,tgt.goods_type_name parentName from t_goods_info tgi  ");
		hql.append("  LEFT JOIN t_goods_type tgt on tgi.parent_num=tgt.goods_type_num where 1=1 ");
		hql.append(SQLUtils.popuHqlEq("tgi.goods_status", 1));
		hql.append(SQLUtils.popuHqlEq("tgi.shop_num", shopNum));
		return sqlDao.getAll(TGoodsInfo.class, hql.toString());

	}
}

