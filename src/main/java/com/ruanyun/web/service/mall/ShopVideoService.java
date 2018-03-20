package com.ruanyun.web.service.mall;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.dao.mall.ShopVideoDao;
import com.ruanyun.web.model.mall.TShopVedio;
import com.ruanyun.web.util.NumUtils;

@Service("shopVedioService")
public class ShopVideoService extends BaseServiceImpl<TShopVedio>{
	
	@Autowired
	private ShopVideoDao shopVideoDao;
	
	/**
	 * 功能描述:查询视频列表
	 * @author cqm  2017-8-21 上午11:50:27
	 * @param page
	 * @param shopVedio
	 * @return
	 */
	public Page<TShopVedio> getShopVedioList(Page<TShopVedio> page,TShopVedio shopVedio){
		return shopVideoDao.getShopVedioList(page, shopVedio);
	}
	
	
	/**
	 * 功能描述:上传视频
	 * @author cqm  2017-4-19 下午12:58:53
	 * @param request
	 * @param userVedio
	 */
	public Integer saveOrUpdateShopVedio(TShopVedio shopVedio){
		  if(EmptyUtils.isEmpty(shopVedio.getShopNum())||//用户num
			   EmptyUtils.isEmpty(shopVedio.getVideoUrl())||//视频url
			   EmptyUtils.isEmpty(shopVedio.getVideoTitle())||//视频名称
			   EmptyUtils.isEmpty(shopVedio.getMainPhotoUrl())||//视频主图url
			   EmptyUtils.isEmpty(shopVedio.getVideoLength())){//视频时长
				throw new RuanYunException("参数不全");
			}
		    shopVedio.setCreateTime(new Date());
			save(shopVedio);
			String videoNum = NumUtils.getCommondNum(NumUtils.PIX_SHOP_VIDEO, shopVedio.getVideoId());
			shopVedio.setVideoNum(videoNum);
						
			return shopVideoDao.getCount(shopVedio.getShopNum());
	}
	
	/**
	 * 功能描述:视频详情
	 * @author cqm  2017-8-21 上午11:06:02
	 * @param videoNum
	 * @param isRequired
	 * @return
	 */
	public TShopVedio getShopVedio(String videoNum,boolean isRequired){
		TShopVedio userVedio = get(TShopVedio.class, "videoNum", videoNum);
		if(isRequired && userVedio==null){
			throw new RuanYunException("视频信息不存在");
		}
		return userVedio;
	}
	
	/**
	 * 功能描述:删除视频
	 * @author cqm  2017-8-21 上午11:51:54
	 * @param videoNum
	 */
	public void deleteShopVedio(String videoNum){
		TShopVedio userVedio = getShopVedio(videoNum, true);
		delete(userVedio);
	}

	public Integer getCount(String shopNum) {
		return shopVideoDao.getCount(shopNum);
	}

}
