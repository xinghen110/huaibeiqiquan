package com.ruanyun.web.service.mall;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.web.dao.mall.OrderRecordDao;
import com.ruanyun.web.model.mall.TOrderRecord;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.util.NumUtils;

@Service("orderRecordService")
public class OrderRecordService extends BaseServiceImpl<TOrderRecord>{
	
	@Autowired
	private OrderRecordDao orderRecordDao;

	/**
	 * 功能描述: 订单记录
	 *
	 * @author chenqian  2016年11月25日 下午2:45:16
	 * 
	 * @param userNum 用户编号
	 * @param orderNum 订单编号
	 * @param orderStatus 状态
	 */
	public void saveOrderRecord(TUser user,String orderNum,Integer orderStatus){
		TOrderRecord record = new TOrderRecord();
		record.setCreateTime(new Date());
		record.setOrderNum(orderNum);
		record.setOrderStatus(orderStatus);
		record.setUserNum(user.getUserNum());
		record.setUserName(user.getNickName());
		super.save(record);
		record.setGoodsRecordNum(NumUtils.getCommondNum(NumUtils.PIX_ORDER_RECORD,record.getGoodsRecordId()));
	}
}
