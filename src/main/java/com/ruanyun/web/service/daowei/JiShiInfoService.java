package com.ruanyun.web.service.daowei;

import java.util.List;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.dao.daowei.JishiInfoDao;
import com.ruanyun.web.model.daowei.TJishiInfo;
import com.ruanyun.web.model.daowei.TTypeInfo;
import com.ruanyun.web.model.mall.TShopInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangyf on 2017/8/5.
 */
@Service("jiShiInfoService")
public class JiShiInfoService extends BaseServiceImpl<TJishiInfo> {

    @Autowired
    private JishiInfoDao jishiInfoDao;
    @Autowired
    private TypeInfoService typeInfoService;
    
    public Page<TJishiInfo> getlist(Page<TJishiInfo> page,TJishiInfo jishiInfo){
    	Page<TJishiInfo> _page=jishiInfoDao.querPage(page, jishiInfo);
    	List<TJishiInfo> list =_page.getResult();
		for (int i = 0; i < list.size(); i++) {
			String typeName="";
			if(EmptyUtils.isNotEmpty(list.get(i).getTypeNum())){
				String[] list2 = list.get(i).getTypeNum().split(",");
				for (int j = 0; j < list2.length; j++) {
					typeName +=typeInfoService.get(TTypeInfo.class, "typeNum",list2[j]).getTypeInfoName()+" ";
				}
				
			}
			list.get(i).setTypeName(typeName);
		}
    	return jishiInfoDao.querPage(page, jishiInfo);
    }

}
