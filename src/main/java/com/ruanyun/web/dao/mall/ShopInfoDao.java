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
import com.ruanyun.web.model.mall.TShopInfo;

@Repository("shopInfoDao")
public class ShopInfoDao extends BaseDaoImpl<TShopInfo>{
	
	
	/**
	 * 功能描述:查询推荐信息
	 * @author cqm  2017-8-7 上午11:46:43
	 * @return
	 */
	public List<TShopInfo> getShopInfos(Integer shopType,String longitude,String latitude,Integer memberLevel){
		StringBuffer hql = new StringBuffer(" select * from t_shop_info as ts where 1=1 ");
		if(shopType==1){//查询商家距离
			if(EmptyUtils.isEmpty(longitude)||EmptyUtils.isEmpty(latitude)){
				longitude="0";
				latitude="0";
			}
			hql = new StringBuffer(" select ts.*,cast(fn_distance("+longitude+","+latitude+",ts.longitude,ts.latitude) as decimal(9,1)) as distance from t_shop_info as ts where 1=1 ");
		}                        
		hql.append(SQLUtils.popuHqlEq("ts.is_recommond", 1));//是否推荐  （1推荐 2未推荐）
		hql.append(SQLUtils.popuHqlEq("ts.shop_type", shopType));//类型(1商家 2技师)
		hql.append(SQLUtils.popuHqlEq("ts.status", 1));
		if(EmptyUtils.isNotEmpty(memberLevel)&&memberLevel!=0){
		   hql.append(" and ts.member_level<="+memberLevel+" ");
		}
		hql.append(" order by ts.is_recommond asc, ts.create_time desc ");
		return sqlDao.getAll(TShopInfo.class, hql.toString());
	}
	
	/**
	 * 功能描述:搜索技师/商家
	 * @author cqm  2017-8-8 下午01:46:41
	 * @param page
	 * @param shopInfo
	 * @param type
	 * @return
	 */
	public Page<TShopInfo> getShopList(Page<TShopInfo> page,TShopInfo shopInfo,Integer type,String age,Integer memberLevel){
		StringBuffer hql = new StringBuffer(" select * from t_shop_info  where 1=1 ");
		if(EmptyUtils.isNotEmpty(shopInfo)){
			if(EmptyUtils.isNotEmpty(shopInfo.getLatitude()) && EmptyUtils.isNotEmpty(shopInfo.getLongitude())){
				 hql=new StringBuffer("select s.*,cast(fn_distance("+shopInfo.getLongitude()+","+shopInfo.getLatitude()+",s.`longitude`,s.`latitude`) as decimal(9,1)) as distance  from t_shop_info s where 1=1  ");
			}
			hql.append(SQLUtils.popuHqlEq("shop_type", shopInfo.getShopType()));
			hql.append(SQLUtils.popuHqlEq("user_sex", shopInfo.getUserSex()));//性别
			if(EmptyUtils.isNotEmpty(shopInfo.getTypeNum())){
				String str[] =shopInfo.getTypeNum().split(",");
				for (int i = 0; i < str.length; i++) {
					hql.append(" and type_num like '%"+str[i]+"%' " );
				}
				
			}
			
			if(EmptyUtils.isNotEmpty(shopInfo.getShopName())){//名称
				hql.append(" and concat(shop_name) like '%"+shopInfo.getShopName()+"%' ");
			}
		}
		if(EmptyUtils.isNotEmpty(age)){//查询年龄范围
			   String[] rents = age.split("-");
			   String str1=rents[0];
			   String str2=rents[1];
			hql.append(" and TIMESTAMPDIFF(YEAR,user_birth,NOW()) BETWEEN "+str1+" and "+str2+" ");
		}
		if(EmptyUtils.isNotEmpty(memberLevel)&&memberLevel!=0){
		     hql.append(" and member_level<="+memberLevel+" ");
		}
		hql.append(SQLUtils.popuHqlEq("status", 1));
		if(EmptyUtils.isNotEmpty(type)){//排序
			if(type==1){//评分最高
			    hql.append(" order by score desc ");
			}
			if(type==2){//距离最近
				hql.append(" order by distance asc ");
			}
			if(type==3){//接单最多
				hql.append(" order by jiedan_count desc ");
			}
		}else{
		    hql.append(" order by is_recommond asc, create_time desc ");
		}
		return sqlDao.queryPage(page, TShopInfo.class, hql.toString());
	}
	
	/**
	 * 功能描述:根据用户编号获取用户信息 返回值为map key为shopNum value 为 TShopInfo对象
	 * @author wsp  2016-9-20 下午07:33:18
	 * @param shopNums
	 * @return
	 */
	public Map<String,TShopInfo> getShopInfoByShopNum(String shopNums){
		final Map<String,TShopInfo> map = new HashMap<String, TShopInfo>();
		String sql ="select * from t_shop_info where shop_num in ("+SQLUtils.sqlForIn(shopNums)+")";
		jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String shopNum=rs.getString("shop_num");
				TShopInfo info=new TShopInfo();
				info.setShopNum(shopNum);
				info.setShopName(rs.getString("shop_name"));
				info.setMainPhoto(rs.getString("main_photo"));
				info.setLongitude(rs.getString("longitude"));
				info.setLatitude(rs.getString("latitude"));
				info.setShopType(rs.getInt("shop_type"));
				info.setProvince(rs.getString("province"));
				info.setCity(rs.getString("city"));
				info.setArea(rs.getString("area"));
				map.put(shopNum, info);
			}
		});
		return map;
	}

	/**
	 * 功能描述：获取符合条件的商家或技师
	 * @param typeNum
	 * @param shopType
	 * @return
	 */
	public List<TShopInfo> getShopInfoByTypeNum(String typeNum, Integer shopType) {
		StringBuilder hql = new StringBuilder(" from TShopInfo where 1=1 ");
		if (EmptyUtils.isNotEmpty(typeNum)) {
			hql.append(SQLUtils.popuHqlLike("typeNum", typeNum));
		}
		if (EmptyUtils.isNotEmpty(shopType)) {
			hql.append(SQLUtils.popuHqlEq("shopType", shopType));
		}
		hql.append(SQLUtils.popuHqlEq("shopStatus", 1));
		hql.append(SQLUtils.popuHqlEq("status", 1));
		hql.append(" order by isRecommond desc, score desc ");
		return hqlDao.getAll(hql.toString());
	}
	
	public Page<TShopInfo> querPage(Page<TShopInfo> page,TShopInfo shopInfo){
		StringBuffer hql =new StringBuffer(" from TShopInfo where 1=1 ");
		if(EmptyUtils.isNotEmpty(shopInfo)){
			hql.append(SQLUtils.popuHqlEq("shopStatus", shopInfo.getShopStatus()));
			hql.append(SQLUtils.popuHqlEq("shopType", shopInfo.getShopType()));
			hql.append(SQLUtils.popuHqlLike("shopName", shopInfo.getShopName()));
			hql.append(SQLUtils.popuHqlMinDate("createTime", shopInfo.getStartTime()));
			hql.append(SQLUtils.popuHqlMaxDate("createTime", shopInfo.getCreateTime()));
			hql.append(SQLUtils.popuHqlEq("memberLevel", shopInfo.getMemberLevel()));
		}
	return hqlDao.queryPage(page, hql.toString());
	}

	/**
	 * 功能描述：根据条件获取商家或者技师
	 * @param shopName    	店名
	 * @param typeNum		服务类型
	 * @param type			排序
	 * @param shopType		1.商家，2.技师
	 * @param longitude		经度
	 * @param latitude		纬度
	 * @return
	 */
	public List<TShopInfo> getShopInfoByCondition(String shopName, String[] typeNum, Integer type, String longitude, Integer memberLevel,String latitude, Integer shopType, Integer startNum) {
		StringBuilder sql = new StringBuilder(" select * from t_shop_info where 1=1 ");
		sql.append(SQLUtils.popuHqlEq("status", 1));
		if (EmptyUtils.isNotEmpty(longitude) && EmptyUtils.isNotEmpty(latitude))
			sql = new StringBuilder("select s.*,cast(fn_distance("+longitude+","+latitude+",s.`longitude`,s.`latitude`) as decimal(9,1)) as distance  from t_shop_info s where 1=1  ");
		if (EmptyUtils.isNotEmpty(shopName)) {
			sql.append(SQLUtils.popuHqlLike("shop_name", shopName));
		}
		if (EmptyUtils.isNotEmpty(shopType)) {
			sql.append(SQLUtils.popuHqlEq("shop_type", shopType));
		}
		if(EmptyUtils.isNotEmpty(memberLevel)){
			sql.append("and member_level<="+memberLevel+"");
		}
		if (EmptyUtils.isNotEmpty(typeNum)) {
			sql.append(" and ");
			for (int i = 0; i < typeNum.length; i++) {
				sql.append(" type_num like '%"+typeNum[i]+"%' ");
				if (i != (typeNum.length - 1)) {
					sql.append(" and ");
				}
			}
		}
		if (EmptyUtils.isNotEmpty(type)) {
			if (type == 1) {
				sql.append(" order by jiedan_count desc ");
			} else if (type == 2) {
				sql.append(" order by score desc ");
			}
		} else {
			sql.append(" order by is_recommond asc, create_time desc ");
		}
		sql.append(" limit "+startNum+",10 ");
		return sqlDao.getAll(TShopInfo.class, sql.toString());
	}

	/**
	 * 功能描述：更新技师数量
	 * @param shopNum
	 * @return
	 */
	public Integer updateJishiCount(String shopNum) {
		StringBuilder sql = new StringBuilder(" UPDATE t_shop_info tsi SET tsi.jishi_count = (SELECT COUNT(*) FROM t_jishi_info tji where 1=1 and tji.shop_info_num = '"+shopNum+"') WHERE tsi.shop_num = '"+shopNum+"' ");
		return sqlDao.update(sql.toString());
	}

	/**
	 * 功能描述：设置评分
	 * @param shopNum
	 * @return
	 */
	public Integer updateScore(String shopNum) {
		StringBuilder sql = new StringBuilder(" UPDATE t_shop_info tsi SET tsi.score = (SELECT AVG(tmi.score) FROM  t_comment_info tmi where 1=1 and tmi.shop_num = '"+shopNum+"') WHERE tsi.shop_num = '"+shopNum+"' ");
		return sqlDao.update(sql.toString());
	}

	/**
	 * 功能描述：修改合作状态
	 * @param shopNum
	 * @return
	 */
	public Integer updateIsHezuo(String shopNum, Integer isHezuo) {
		StringBuilder sql = new StringBuilder("update t_shop_info set is_hezuo = "+isHezuo+" where shop_num = '"+shopNum+"'");
		return sqlDao.update(sql.toString());
	}

	/**
	 * 功能描述：修改状态
	 * @param shopNum
	 * @return
	 */
	public Integer updateStatus(String shopNum, Integer status) {
		StringBuilder sql = new StringBuilder("update t_shop_info set status = "+status+" where shop_num = '"+shopNum+"'");
		return sqlDao.update(sql.toString());
	}
}

