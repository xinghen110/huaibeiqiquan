package com.ruanyun.common.cache.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ruanyun.common.cache.SystemCacheService;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SysCode;
 
import com.ruanyun.web.model.sys.TAreas;
import com.ruanyun.web.model.sys.TCity;
import com.ruanyun.web.model.sys.TProvince;
import com.ruanyun.web.model.sys.TTown;
import com.ruanyun.web.util.DateUtils;
 

@Service("areaCache")
public class AreaCache implements SystemCacheService {
	private static Map<String, List<TAreas>> mapAreas = new HashMap<String, List<TAreas>>();
	private static Map<String, List<TCity>> mapCities = new HashMap<String, List<TCity>>();
	private static List<TProvince> provincesList = new ArrayList<TProvince>();
	
	 
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Override
	public String getCacheName() {
		return "加载省份城市 地区结束时间为:"+DateUtils.getCurrentDay(SysCode.DATE_FORMAT_NUM_L);
	}

	@Override
	public void run() {
		update();
	}
	
	/**
	 * 功能描述:转换地区
	 *
	 * @author yangliu  2013-11-30 下午04:19:12
	 * 
	 * @param allList
	 * @return
	 */
	private  Map<String,List<TAreas>> getMapAreas(List<TAreas> allList){
		Map<String,List<TAreas>> map = new HashMap<String, List<TAreas>>();
		List<TAreas> childList=null;
		for(TAreas dic : allList){
			childList=map.get(dic.getCityCode());
			if(childList==null){
				childList=new ArrayList<TAreas>();
				map.put(dic.getCityCode(), childList);
			}
			childList.add(dic);
		}
		return map;
	}
	
	/**
	 * 功能描述:转换城市
	 *
	 * @author yangliu  2013-11-30 下午04:19:25
	 * 
	 * @param allList
	 * @return
	 */
	private  Map<String,List<TCity>> getMapCities(List<TCity> allList){
		Map<String,List<TCity>> map = new HashMap<String, List<TCity>>();
		List<TCity> childList=null;
		for(TCity dic : allList){
			childList=map.get(dic.getProvinceCode());
			if(childList==null){
				childList=new ArrayList<TCity>();
				map.put(dic.getProvinceCode(), childList);
			}
			childList.add(dic);
		}
		return map;
	}
	
	
	/**
	 * 功能描述:转换城市
	 *
	 * @author yangliu  2013-11-30 下午04:19:25
	 * 
	 * @param allList
	 * @return
	 */
	private  Map<String,List<TTown>> getMapStreet(List<TTown> allList){
		Map<String,List<TTown>> map = new HashMap<String, List<TTown>>();
		List<TTown> childList=null;
		for(TTown dic : allList){
			childList=map.get(dic.getAreasCode());
			if(childList==null){
				childList=new ArrayList<TTown>();
				map.put(dic.getAreasCode(), childList);
			}
			childList.add(dic);
		}
		return map;
	}
	
	
	
	@Override
	public void update() {
		provincesList.clear();
		provincesList=getAllProvinces();
		mapAreas.clear();
		mapAreas=getMapAreas(getAllAreas());
		mapCities.clear();
		mapCities=getMapCities(getAllCities());		
	 
		
		
	}
	
	/**
	 * 功能描述:获取城市
	 *
	 * @author yangliu  2013-11-30 下午04:19:41
	 * 
	 * @return
	 */
	private List<TCity> getAllCities(){
		return	jdbcTemplate.query("select * from t_city", new BeanPropertyRowMapper<TCity>(TCity.class));
	}
	
	/**
	 * 功能描述:获取地区
	 *
	 * @author yangliu  2013-11-30 下午04:19:52
	 * 
	 * @return
	 */
	private List<TAreas> getAllAreas(){
		return	jdbcTemplate.query("select * from t_areas", new BeanPropertyRowMapper<TAreas>(TAreas.class));
	}
	
	/**
	 * 功能描述:获取省份
	 *
	 * @author yangliu  2013-11-30 下午04:20:02
	 * 
	 * @return
	 */
	private List<TProvince> getAllProvinces(){
		return	jdbcTemplate.query("select * from t_province", new BeanPropertyRowMapper<TProvince>(TProvince.class));
	}

	/**
	 * 功能描述:获取街道
	 *
	 * @author yangliu  2013-11-30 下午04:20:02
	 * 
	 * @return
	 */
	private List<TTown> getAllStreet(){
		return	jdbcTemplate.query("select * from t_town", new BeanPropertyRowMapper<TTown>(TTown.class));
	}
	
	
	
	/**
	 * 功能描述:获取省份下的城市
	 *
	 * @author yangliu  2013-11-30 下午04:33:08
	 * 
	 * @param provinceId
	 * @return
	 */
	public static List<TCity> getCitiesByProvinces(String provinceId){
		return mapCities.get(provinceId);
	}
	
	/**
	 * 功能描述: 获取城市名称
	 *
	 * @author yangliu  2013-11-30 下午04:33:24
	 * 
	 * @param provinceId
	 * @param cityId
	 * @return
	 */
	public static String getCityName(String provinceId,String cityId){
		if(EmptyUtils.isNotEmpty(provinceId) && EmptyUtils.isNotEmpty(cityId)){
			List<TCity> list = mapCities.get(provinceId);
			if(EmptyUtils.isEmpty(list))
				return null;
			for(TCity dic : list){
				if(dic.getCityCode().equals(cityId))
					return dic.getCityName();
			}
		}
		return null;
	}
	
	/**
	 * 功能描述:获取地区县
	 *
	 * @author yangliu  2013-11-30 下午04:33:41
	 * 
	 * @param cityId
	 * @return
	 */
	public static List<TAreas> getAreaByCities(String cityId){
		return mapAreas.get(cityId);
	}
	
	/**
	 * 功能描述: 获取地区县名称
	 *
	 * @author yangliu  2013-11-30 下午04:33:54
	 * 
	 * @param cityId
	 * @param areaId
	 * @return
	 */
	public static String getAreaName(String cityId,String areaId){
		if(EmptyUtils.isNotEmpty(cityId) && EmptyUtils.isNotEmpty(areaId)){
			List<TAreas> list = mapAreas.get(cityId);
			if(EmptyUtils.isEmpty(list))
				return "";
			for(TAreas dic : list){
				if(dic.getAreaid().equals(areaId))
					return dic.getArea();
			}
		}
		return "";
	}
	
	
	/**
	 * 功能描述:获取省份列表
	 *
	 * @author yangliu  2013-11-30 下午04:36:44
	 * @return
	 */
	public static List<TProvince> getProvinces(){
		return provincesList;
	}
	
	/**
	 * 功能描述:获取省份名称
	 *
	 * @author yangliu  2013-11-30 下午04:36:57
	 * 
	 * @param provinceId
	 * @return
	 */
	public static String getProvinceName(String provinceId){
		for(TProvince tp : provincesList){
			if(tp.getProvinceCode().equals(provinceId))
				return tp.getProvinceName();
		}
		return null;
	}
	
	
	
	/**
	 * 功能描述: 获取地区县名称
	 *
	 * @author yangliu  2013-11-30 下午04:33:54
	 * 
	 * @param cityId
	 * @param areaId
	 * @return
	 */
//	public static String getStreetName(String areaId,String townId){
//		if(EmptyUtils.isNotEmpty(areaId) && EmptyUtils.isNotEmpty(townId)){
//			List<TTown> list = mapSteet.get(areaId);
//			for(TTown dic : list){
//				if(dic.getTownCode().equals(townId))
//					return dic.getTownName();
//			}
//		}
//		return null;
//	}
}
