/**
 * 手机端接口: files
 *@author feiyang
 *@date 2016-3-16
 */
package com.ruanyun.web.dao.sys;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.sys.TCity;
import com.ruanyun.web.model.sys.TUser;


/**
 *@author feiyang
 *@date 2016-3-16
 */
@Repository
public class CityDao extends BaseDaoImpl<TCity>{
	
	
	/**
	 * 
	 * 功能描述:根据省份CODE 获取所有城市
	 * @param provinceCode
	 * @return
	 *@author feiyang
	 *@date 2016-3-16
	 */
	public List<TCity> getAllcity(String provinceCode ){
		StringBuffer sql=new StringBuffer(" SELECT * from t_city WHERE 1=1");
		if (EmptyUtils.isNotEmpty(provinceCode)) {
			sql.append(" and  province_code='"+provinceCode+"'");
		}
		sql.append(" and  status =1 ");
		return sqlDao.getAll(TCity.class, sql.toString());
	}
	
	/**
	 * 功能描述:查询城市列表
	 * @author cqm  2016-11-8 上午10:40:57
	 * @param page
	 * @param city
	 * @return
	 */
	public List<TCity> getCityList(TCity city){
		StringBuffer sql=new StringBuffer(" select * from t_city WHERE 1=1 ");
		if (EmptyUtils.isNotEmpty(city.getProvinceCode())) {
			sql.append(" and  province_code='"+city.getProvinceCode()+"'");
		}
		sql.append(" and  status =1 ");
		return sqlDao.getAll(TCity.class,sql.toString());
	}
	
	/**
	 * 查城市
	 * @param cityCode
	 * @return
	 */
	public List<TCity> getCities(String cityCode){
		String sql ="select * from t_city where city_code='"+cityCode+"' ";
		return sqlDao.getAll(sql.toString());
	}
}
