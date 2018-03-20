/**
 * 手机端接口: files
 *@author feiyang
 *@date 2016-3-16
 */
package com.ruanyun.web.service.sys;

import java.util.List;

import org.hibernate.engine.query.spi.ReturnMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.web.dao.sys.CityDao;
import com.ruanyun.web.model.sys.TCity;
import com.ruanyun.web.model.sys.TUser;

/**
 *@author feiyang
 *@date 2016-3-16
 */
@Service
public class CityService extends BaseServiceImpl<TCity>{

	@Autowired
	private CityDao cityDao;
	
	/**
	 * 
	 * 功能描述:根据省份CODE 获取所有城市
	 * @param provinceCode
	 * @return
	 *@author feiyang
	 *@date 2016-3-16
	 */
	public List<TCity> getAllcity(String provinceCode ){
		return  cityDao.getAllcity(provinceCode);
	}
	
	/**
	 * 功能描述:查询城市列表
	 * @author cqm  2016-11-8 上午10:46:24
	 * @param page
	 * @param city
	 * @return
	 */
	public List<TCity> getList(TCity city){
		return cityDao.getCityList(city);
	}
	/**
	 */
	public List<TCity> getCities(String cityCode){
		return cityDao.getCities(cityCode);
	}
}
