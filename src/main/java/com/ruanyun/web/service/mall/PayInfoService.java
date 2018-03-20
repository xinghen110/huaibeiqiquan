package com.ruanyun.web.service.mall;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.CommonUtils;
import com.ruanyun.common.utils.EmptyUtils;
 
import com.ruanyun.web.dao.mall.PayInfoDao;

import com.ruanyun.web.model.mall.TPayInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.sys.UserService;
 
@Service("payInfoService")
public class PayInfoService extends BaseServiceImpl<TPayInfo>{
	
	@Autowired
	private PayInfoDao payInfoDao;
	@Autowired
	private UserService userService;
	
	/**
	 * 查询公告
	 * zhujingbo
	 */
	public Page<TPayInfo> getList(Page<TPayInfo> page,TPayInfo payInfo,TUser user){
		Page<TPayInfo>_page=payInfoDao.queryPage(page,payInfo,user);
		
		String userNums=CommonUtils.getAttributeValue(TPayInfo.class, _page.getResult(), "userNum");
		Map<String,TUser> userMap=userService.getUserByUserNums(userNums);
		CommonUtils.setAttributeValue(TPayInfo.class,  _page.getResult(), userMap, "userNum", "user");
		
		return _page;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 功能描述:修改和添加
	 * @author wsp  2016-10-14 下午06:50:25
	 * @param payInfo
	 * @param user
	 * @return
	 */
	public Integer saveOrUpdate(TPayInfo payInfo,TUser user) {
		TPayInfo bean = super.get(TPayInfo.class, "payInfoId", payInfo.getPayInfoId());
			if (EmptyUtils.isNotEmpty(bean)) {
				BeanUtils.copyProperties(payInfo, bean, new String[] {"payInfoId","storeNum","userNum","createTime"});
				update(bean);
			} else {
				payInfo.setCreateTime(new Date());
				payInfo.setUserNum(user.getUserNum());
				save(payInfo);
			}
		return 1;
	}
	
	public TPayInfo getPayInfo(Integer payType){
    	return super.get(TPayInfo.class, new String[]{"payType"}, new Object[]{payType});
    }

}










