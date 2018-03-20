package com.ruanyun.web.dao.daowei;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.daowei.TMealInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangyf on 2017/8/4.
 */
@Repository
public class MealInfoDao extends BaseDaoImpl<TMealInfo> {

	/**
	 * 功能描述:查询列表
	 * @author wyf  2017-8-21 上午10:11:57
	 * @param page
	 * @param mealInfo
	 * @return
	 */
    public Page<TMealInfo> getList(Page<TMealInfo> page, TMealInfo mealInfo) {
        StringBuilder hql = new StringBuilder(" select tmi.* from t_meal_info tmi where 1=1 ");
        if (EmptyUtils.isNotEmpty(mealInfo)) {
            hql.append(SQLUtils.popuHqlEq("tmi.shop_num", mealInfo.getShopNum()));
            hql.append(SQLUtils.popuHqlEq("tmi.meal_info_num", mealInfo.getMealInfoNum()));
            hql.append(SQLUtils.popuHqlLike("tmi.title", mealInfo.getTitle()));
            hql.append(SQLUtils.popuHqlEq("tmi.meal_type", mealInfo.getMealType()));
            hql.append(SQLUtils.popuHqlEq("tmi.type_num", mealInfo.getTypeNum()));
            hql.append(SQLUtils.popuHqlMinDate("tmi.create_time", mealInfo.getStartTime()));
            hql.append(SQLUtils.popuHqlMaxDate("tmi.create_time", mealInfo.getCreateTime()));
            hql.append(SQLUtils.popuHqlEq("tmi.is_recommend", mealInfo.getIsRecommend()));
            hql.append(SQLUtils.popuHqlEq("tmi.make_method", mealInfo.getMakeMethod()));
        }
        hql.append(" and exists( select 1 from t_shop_info tsi where tsi.shop_num = tmi.shop_num and tsi.status = 1)");
        hql.append(" order by tmi.create_time DESC");
        return sqlDao.queryPage(page, TMealInfo.class, hql.toString());
    }

    /**
     * 功能描述:查询列表
     * @author wyf  2017-8-21 上午10:11:57
     * @param page
     * @param mealInfo
     * @return
     */
    public Page<?> getList2(Page<?> page, TMealInfo mealInfo) {
        StringBuilder sql = new StringBuilder("select tmi.*,tsi.member_level,tsi.shop_name,tti.type_info_name from ");
        //获取shopInfo表的关联数据
        sql.append(" (t_meal_info tmi left join t_shop_info tsi on tmi.shop_num=tsi.shop_num) ");
        //获取typeInfo表的关联数据
        sql.append("  left join t_type_info tti on tmi.type_num=tti.type_num where 1=1 ");
        if (EmptyUtils.isNotEmpty(mealInfo)) {
            sql.append(SQLUtils.popuHqlEq("tmi.meal_type", mealInfo.getMealType()));
            sql.append(SQLUtils.popuHqlLike("tmi.title", mealInfo.getTitle()));
            sql.append(SQLUtils.popuHqlEq("tmi.type_num", mealInfo.getTypeNum()));
            sql.append(SQLUtils.popuHqlMinDate("tmi.create_time", mealInfo.getStartTime()));
            sql.append(SQLUtils.popuHqlMaxDate("tmi.create_time", mealInfo.getCreateTime()));
            sql.append(SQLUtils.popuHqlEq("tmi.is_recommend", mealInfo.getIsRecommend()));
            sql.append(SQLUtils.popuHqlEq("tmi.make_method", mealInfo.getMakeMethod()));
            //由于字段不够，暂时使用这个字段用来根据等级查询
            if (EmptyUtils.isNotEmpty(mealInfo.getFlag1()))
                sql.append(SQLUtils.popuHqlEq("tsi.member_level", Integer.parseInt(mealInfo.getFlag1())));
        }
        sql.append(" and exists( select 1 from t_shop_info tsi where tsi.shop_num = tmi.shop_num and tsi.status = 1)");
        sql.append(" order by tmi.create_time DESC ");
        System.out.println("=="+sql.toString());
        return sqlDao.queryPage(page, sql.toString());
    }

	
	/**
	 * 功能描述:查询推荐套餐
	 * @author cqm  2017-8-7 上午11:35:27
	 * @return
	 */
	public List<TMealInfo> getMealInfos(Integer memberLevel){
		StringBuffer hql = new StringBuffer(" select * from t_meal_info tmi where 1=1 ");
		//hql.append(SQLUtils.popuHqlEq("meal_type", 1));//套餐类型(1商家 2技师)
		hql.append(SQLUtils.popuHqlEq("is_recommend", 1));//是否推荐  （1推荐 2未推荐）
        hql.append(" and exists( select 1 from t_shop_info tsi where tsi.shop_num = tmi.shop_num and tsi.status = 1 )");
        hql.append(" AND exists( SELECT 1 FROM t_shop_info tsi where tmi.shop_num = tsi.shop_num and tsi.member_level <= "+memberLevel+")");
		hql.append(" order by tmi.is_recommend asc,tmi.sold DESC ");
		System.out.println("==="+hql.toString());
		return sqlDao.getAll(TMealInfo.class, hql.toString());
	}
    /**
     * 功能描述：根据某些条件获取套餐
     * @param title
     * @param typeNum
     * @param type
     * @return
     */
    public List getMealInfoByCondition(String title, String[] typeNum, Integer type, Integer memberLevel, Integer startNum) {
        //StringBuilder sql = new StringBuilder(" select * from t_meal_info where 1=1 ");
    	StringBuilder sql = new StringBuilder(" select tmi.title,tyi.type_info_name,tmi.main_photo,tmi.meal_price,tmi.meal_info_num,tmi.meal_log,tmi.sold from t_meal_info tmi LEFT JOIN t_type_info tyi on tmi.type_num=tyi.type_num where 1=1 ");
        if (EmptyUtils.isNotEmpty(title)) {
            sql.append(SQLUtils.popuHqlLike("tmi.title", title));
        }
        if (EmptyUtils.isNotEmpty(typeNum)) {
            sql.append(" and tyi.type_num in (");
            for (int i = 0; i < typeNum.length; i++) {
                sql.append(" '"+typeNum[i]+"' ");
                if (i != (typeNum.length - 1)) {
                    sql.append(",");
                }
            }
            sql.append(") ");
        }
        sql.append(" and exists( select 1 from t_shop_info tsi where tsi.shop_num = tmi.shop_num and tsi.status = 1)");
        if (EmptyUtils.isNotEmpty(memberLevel)) {
            sql.append(" and exists( select 1 from t_shop_info tsi where tsi.shop_num = tmi.shop_num and tsi.member_level <= "+memberLevel+" )");
        }
        if (EmptyUtils.isNotEmpty(type)) {
            if (type == 1) {
                sql.append(" order by tmi.sold desc ");
            } else if (type == 2) {
                sql.append(" order by tmi.meal_price asc ");
            } else if (type == 3) {
                sql.append(" order by tmi.meal_price desc ");
            }
        } else {
            sql.append(" order by tmi.is_recommend asc,tmi.sold DESC ");
        }
        sql.append(" limit "+startNum+",10 ");
        return sqlDao.getAll(sql.toString());
    }

    /**
     * 功能描述：获取全部套餐
     * @param shopNum
     * @return
     */
    public List getMealInfos(String shopNum) {
        StringBuilder sql = new StringBuilder(" select tmi.*,tyi.type_info_name from t_meal_info tmi LEFT JOIN t_type_info tyi on tmi.type_num=tyi.type_num where 1=1 ");
        if (EmptyUtils.isNotEmpty(shopNum)) {
            sql.append(SQLUtils.popuHqlEq("tmi.shop_num", shopNum));
        }
        sql.append(" and exists( select 1 from t_shop_info tsi where tsi.shop_num = tmi.shop_num and tsi.status = 1");
        sql.append(" order by tmi.create_time DESC ");
        return sqlDao.getAll(sql.toString());
    }
}
