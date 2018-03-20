/**
 * 手机端接口: files
 *@author feiyang
 *@date 2016-3-16
 */
package com.ruanyun.web.service.sys;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.dao.sys.AreasDao;
import com.ruanyun.web.model.mall.TAdverInfo;
import com.ruanyun.web.model.sys.TAreas;
import com.ruanyun.web.model.sys.TCity;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.util.Constants;

/**
 *@author feiyang
 *@date 2016-3-16
 */
@Service
public class AreasService extends BaseServiceImpl<TAreas>{
	
	@Autowired
	private AreasDao areasDao;

	/**
	 * 
	 * 功能描述:根据cityCode获取所有的区域
	 * @param cityCode
	 * @return
	 *@author feiyang
	 *@date 2016-3-16
	 */
	public List<TAreas> getAllAreas(String cityCode,TUser user ){
		return areasDao.getAllAreas(cityCode,user);
	}
	
	public List<TAreas> getAllAreas(String cityCode ){
		return areasDao.getAllAreas(cityCode);
	}
	
	/**
	 * 功能描述:查询区域列表
	 * @author cqm  2016-11-8 上午10:46:24
	 * @param page
	 * @param areas
	 * @return
	 */
	public List<TAreas> getAreasList(TAreas areas){
		return areasDao.getAreasList(areas);
	}
	
	public Page<TAreas> getlist(Page<TAreas> page,TAreas areas){
		return areasDao.getPageAreas(page, areas);
		
	}
	
	/**
	 * 功能描述:是否显示
	 * @author zhujingbo
	 * @param type
	 * @param ids
	 * @return
	 */
	public int saveIsOpen(Integer type, String ids) {
		if (type != 1 & type != 2)return 0;
		TAreas bean = super.get(TAreas.class, "areaid",ids);
					bean.setIsOpen(type);
				super.update(bean);
			
			return 1;
	}
	/**
	 *功能 保存级别
	 * @param level
	 * @param areaid
	 * @return
	 */
	public int saveLevel(Integer level,String areaid ){
		TAreas bean = super.get(TAreas.class, "areaid",areaid);
		if(level!=null){
		if (level != 1 & level != 2)return 0;
			bean.setLevel(level);
			super.update(bean);
		}else {
			bean.setLevel(null);
			super.update(bean);
		}
		return 1;
		}
	
	
	public int updateQuery(String filedValue,String filed,String queryFiledValue,String tableName,String queryFiled){
		areasDao.updateQuery(filedValue, filed, queryFiledValue, tableName, queryFiled);
		return 1;
	}
	
	
	/**
	 * 功能描述:根据id获取信息
	 * @author cqm  2016-12-29 上午11:52:10
	 * @param areaid
	 * @return
	 */
	public TAreas getAreas(String  areaid){
		return areasDao.getAreas(areaid);
	}
		
}
