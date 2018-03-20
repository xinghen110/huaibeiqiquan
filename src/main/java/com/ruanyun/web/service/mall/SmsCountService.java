package com.ruanyun.web.service.mall;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.CommonUtils;
import com.ruanyun.web.dao.mall.SmsCountDao;
import com.ruanyun.web.dao.sys.UserDao;
import com.ruanyun.web.model.mall.TSmsCount;

import com.ruanyun.web.model.sys.TUser;
@Service("smsCountService")
public class SmsCountService extends BaseServiceImpl<TSmsCount> {
@Autowired
private SmsCountDao smsCountDao;
@Autowired
private UserDao userDao;

public Page<TSmsCount> getList(Page<TSmsCount> page,TSmsCount smsCount,TUser user){
	 Page<TSmsCount> _page = smsCountDao.queryPage(page,smsCount,user);
		String userNums=CommonUtils.getAttributeValue(TSmsCount.class, _page.getResult(), "userNum");
		Map<String,TUser> userMap=userDao.getUserByUserNums(userNums);
		CommonUtils.setAttributeValue(TSmsCount.class,  _page.getResult(), userMap, "userNum", "user");
		return _page;
}


}
