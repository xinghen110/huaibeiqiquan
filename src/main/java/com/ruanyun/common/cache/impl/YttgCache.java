package com.ruanyun.common.cache.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ruanyun.common.cache.SystemCacheService;
import com.ruanyun.web.model.sys.TDictionary;
@Service
public class YttgCache  implements SystemCacheService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
 
	@Override
	public void run() {
		update();
	}

	@Override
	public void update() {
	  
	}

	@Override
	public String getCacheName() {
		return "加载t_shop_type 店铺类别";
	}
	
	
	 
	
	
	
	

}
