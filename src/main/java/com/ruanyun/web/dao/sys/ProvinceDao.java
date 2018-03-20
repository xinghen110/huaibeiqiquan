/**
 * 手机端接口: files
 *@author feiyang
 *@date 2016-3-16
 */
package com.ruanyun.web.dao.sys;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.sys.TProvince;

/**
 *@author feiyang
 *@date 2016-3-16
 */
@Repository
public class ProvinceDao extends BaseDaoImpl<TProvince>{

	
	/**
	 * 
	 * 功能描述:获取省份
	 * @param provinceCode
	 * @return
	 *@author feiyang
	 *@date 2016-3-16
	 */
	public List<TProvince> getAllprovice(String provinceCode ){
		StringBuffer sql=new StringBuffer(" SELECT * from t_province WHERE 1=1");
		if (EmptyUtils.isNotEmpty(provinceCode)) {
			sql.append(" and  province_code='"+provinceCode+"'");
		}
		return sqlDao.getAll(TProvince.class, sql.toString());
	}
	
	
	

}
