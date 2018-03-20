package com.ruanyun.web.service.mall;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alipay.api.domain.ShopInfo;
import com.ruanyun.web.jgpush.JpushClientUtil;
import com.ruanyun.web.model.sys.*;
import com.ruanyun.web.service.daowei.TypeInfoService;
import com.ruanyun.web.service.sys.AreasService;
import com.ruanyun.web.service.sys.CityService;
import com.ruanyun.web.service.sys.ProvincService;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.util.NumUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.dao.mall.ShopInfoDao;
import com.ruanyun.web.model.daowei.TTypeInfo;
import com.ruanyun.web.model.mall.TAuditShopLog;
import com.ruanyun.web.model.mall.TOrderInfo;
import com.ruanyun.web.model.mall.TShopInfo;


//店铺管理 

@Service("shopInfoService")
public class ShopInfoService extends BaseServiceImpl<TShopInfo>{
	
	@Autowired
	private ShopInfoDao shopInfoDao;
	@Autowired
	private TypeInfoService typeInfoService;
	@Autowired
	private AuditShopLogService auditShopLogService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProvincService provincService;
	@Autowired
	private CityService cityService;
	@Autowired
	private AreasService areasService;
	@Autowired
	private SmsInfoService smsInfoService;
	
	/**
	 * 功能描述:搜索技师/商家
	 * @author cqm  2017-8-8 下午01:46:41
	 * @param page
	 * @param shopInfo
	 * @return
	 */
	public Page<TShopInfo> getShopList(Page<TShopInfo> page,TShopInfo shopInfo,Integer type,String age,Integer memberLevel){
		return shopInfoDao.getShopList(page, shopInfo,type,age,memberLevel);
	}
	
	/**
	 * 功能描述:根据店铺编号获取店铺信息 返回值为map key为shopNum value 为 TShopInfo对象
	 * @author wsp  2016-9-24 上午10:06:14
	 * @param shopNums
	 * @return
	 */
	public Map<String, TShopInfo> getShopInfoByShopNum(String shopNums){
		if(EmptyUtils.isEmpty(shopNums)) return null;
		return shopInfoDao.getShopInfoByShopNum(shopNums);
		
	}

    /**
     * 功能描述：添加店铺(审核通过时)
     * @param user
     * @return
     */
    public Integer saveShopInfo(TUser user) {
        TShopInfo shopInfo = new TShopInfo();
        shopInfo.setCreateTime(new Date());
        if (EmptyUtils.isNotEmpty(user.getUserNum()) && EmptyUtils.isNotEmpty(user.getUserType())) {
            shopInfo.setUserNum(user.getUserNum());
            if (user.getUserType() == 4) {
                shopInfo.setShopType(2);
            } else if (user.getUserType() == 2) {
                shopInfo.setShopType(1);
            }
            save(shopInfo);
            String shopNum = NumUtils.getCommondNum(NumUtils.PIX_SHOP_INFO, shopInfo.getShopInfoId());
            shopInfo.setShopNum(shopNum);
            update(shopInfo);
            return 1;
        }
        return 0;
    }
    
    /**
     * 功能描述:获取商家/技师详情
     * @author cqm  2017-8-10 上午09:57:24
     * @param shopNum
     * @param isRequired
     * @return
     */
	public TShopInfo getShopInfo(String shopNum,boolean isRequired){
		TShopInfo shopInfo = get(TShopInfo.class, "shopNum", shopNum);
		if(isRequired && shopInfo==null){
			throw new RuanYunException("商家信息不存在");
		}
		return shopInfo;
	}

	public List<TShopInfo> getShopInfoByTypeNum(String typeNum, Integer shopType) {
		return shopInfoDao.getShopInfoByTypeNum(typeNum, shopType);
	}
	
	public Page<TShopInfo> getlist(Page<TShopInfo> page,TShopInfo shopInfo){
		Page<TShopInfo> _page =shopInfoDao.querPage(page, shopInfo);
		List<TShopInfo> list =_page.getResult();
		for (int i = 0; i < list.size(); i++) {
			String typeName="";
			if(EmptyUtils.isNotEmpty(list.get(i).getTypeNum())){
				String[] list2 = list.get(i).getTypeNum().split(",");
				for (int j = 0; j < list2.length; j++) {
					typeName +=typeInfoService.get(TTypeInfo.class, "typeNum",list2[j]).getTypeInfoName()+" ";
				}
				
			}
			list.get(i).setTypName(typeName);
		}
		return shopInfoDao.querPage(page, shopInfo);
	}
	
	
	public Integer updateExamine(TShopInfo shopInfo,TUser user){//user 是当前操作者用户信息
		TShopInfo shopInfo2=get(TShopInfo.class, "shopNum",shopInfo.getShopNum());
		shopInfo2.setMemberLevel(shopInfo.getMemberLevel());
		shopInfo2.setShopStatus(shopInfo.getShopStatus());
		update(shopInfo2);//修改店铺信息
		//生成审核流水表
		TAuditShopLog auditShopLog=new TAuditShopLog();
		auditShopLog.setShopNum(shopInfo2.getShopNum());
		auditShopLog.setShopName(shopInfo2.getShopName());
		auditShopLog.setCity(shopInfo2.getCity());
		auditShopLog.setArea(shopInfo2.getArea());
		auditShopLog.setCreateTime(new Date());
		if(shopInfo.getShopStatus()==-1){
		auditShopLog.setReason(shopInfo.getReason());
		}
		if(shopInfo.getShopStatus()==1){
			auditShopLog.setReason("审核通过");
		}
		auditShopLogService.save(auditShopLog);
		auditShopLog.setAuditShopLogNum(NumUtils.getCommondNum(NumUtils.PIX_AUDIT_SHOP_LOG, auditShopLog.getAuditShopLogId()));
		auditShopLogService.update(auditShopLog);
		//修改用户状态
		TUser users=userService.get(TUser.class, "userNum",shopInfo2.getUserNum());
		TUser user2=userService.getUserByUserNum(shopInfo2.getCreatorNum(), true);
		if(shopInfo.getShopStatus()==1){
			users.setAuditShopStatus(1);
			if(EmptyUtils.isNotEmpty(user2.getRegistrationId())){
		    	JpushClientUtil.sendToRegistrationId(user2.getRegistrationId(), "恭喜您！您的资料审核通过", "恭喜您！您的资料审核通过", "恭喜您！您的资料审核通过", 1, null);
		    	smsInfoService.saveSmsInfo(users.getUserNum(), "恭喜您！您的资料审核通过", user.getUserNum(), "SHTG");
			}
		}if(shopInfo.getShopStatus()==-1){
			users.setAuditShopStatus(3);
			if(EmptyUtils.isNotEmpty(user2.getRegistrationId())){
		    	JpushClientUtil.sendToRegistrationId(user2.getRegistrationId(), "很抱歉！您的资料审核未通过", "很抱歉！您的资料审核未通过", "很抱歉！您的资料审核未通过", 1, null);
		    	smsInfoService.saveSmsInfo(users.getUserNum(), "很抱歉！您的资料审核未通过", user.getUserNum(), "SHWTG");
			}
		}
		userService.update(users);
		return 1;
		
	}

	/**
	 * 功能描述：根据jsonString修改shopInfo对象
	 * @param shopInfo
	 * @param jsonStr
	 * @return
	 */
	public TShopInfo setShopInfoByJsonStr(TShopInfo shopInfo, String jsonStr) {
		JSONObject data = JSONObject.fromObject(jsonStr);
		if (data.containsKey("shopName")) {
			shopInfo.setShopName(data.getString("shopName"));
		}
		if (data.containsKey("userBirth")) {
			shopInfo.setUserBirth(data.getString("userBirth"));
		}
		if (data.containsKey("userSex")) {
			shopInfo.setUserSex(data.getInt("userSex"));
		}
		if (data.containsKey("linkTel")) {
			shopInfo.setLinkTel(data.getString("linkTel"));
		}
		if (data.containsKey("longitude")) {
			shopInfo.setLongitude(data.getString("longitude"));
		}
		if (data.containsKey("latitude")) {
			shopInfo.setLatitude(data.getString("latitude"));
		}
		if (data.containsKey("area") && data.containsKey("province") && data.containsKey("city")) {
			TProvince province = provincService.get(TProvince.class, "provinceName", data.getString("province"));
			TCity city = cityService.get(TCity.class, new String[]{"provinceCode","cityName"}, new Object[]{province.getProvinceCode(),data.getString("city")});
			TAreas areas = areasService.get(TAreas.class, new String[]{"cityCode", "area"}, new Object[]{city.getCityCode(),data.getString("area")});
			shopInfo.setProvince(province.getProvinceCode());
			shopInfo.setCity(city.getCityCode());
			shopInfo.setArea(areas.getAreaid());
		}
		if (data.containsKey("flag1")) {
			shopInfo.setBusinessTime(null);
			shopInfo.setFlag1(data.getString("flag1"));
		}
		if (data.containsKey("flag2")) {
			shopInfo.setBusinessTime(null);
			shopInfo.setFlag2(data.getString("flag2"));
		}
		if (data.containsKey("address")) {
			shopInfo.setAddress(data.getString("address"));
		}
		if (data.containsKey("description")) {
			shopInfo.setDescription(data.getString("description"));
		}
		if (data.containsKey("typeNums")) {
			shopInfo.setTypeNum(data.getString("typeNums"));
		}
		return shopInfo;
	}

	/**
	 * 功能描述：根据条件获取商家或者技师
	 * @param shopName
	 * @param typeNums
	 * @param type
	 * @param shopType
	 * @return
	 */
	public List<TShopInfo> getShopInfoByCondition(String shopName, String typeNums, Integer type, String longitude, Integer memberLevel,String latitude, Integer shopType, Integer startNum) {
		if (EmptyUtils.isNotEmpty(typeNums)) {
			String[] typeNum = typeNums.split(",");
			return shopInfoDao.getShopInfoByCondition(shopName, typeNum, type, longitude, memberLevel,latitude, shopType, startNum);
		} else {
			return shopInfoDao.getShopInfoByCondition(shopName, null, type, longitude, memberLevel,latitude, shopType, startNum);
		}
	}

	/**
	 * 功能描述：更新技师数量
	 * @param shopNum
	 * @return
	 */
	public Integer updateJishiCount(String shopNum) {
		return shopInfoDao.updateJishiCount(shopNum);
	}

	/**
	 * 功能描述：设置评分
	 * @param shopNum
	 * @return
	 */
	public Integer updateScore(String shopNum) {
		return shopInfoDao.updateScore(shopNum);
	}

	/**
	 * 功能描述：设置合作状态
	 * @param shopNum
	 * @return
	 */
	public Integer updateIsHezuo(String shopNum, Integer isHezuo) {
		return shopInfoDao.updateIsHezuo(shopNum, isHezuo);
	}

	/**
	 * 功能描述：修改状态
	 * @param shopNum
	 * @return
	 */
	public Integer updateStatus(String shopNum, Integer status) {
		return shopInfoDao.updateStatus(shopNum, status);
	}

	/**
	 * 功能描述：变更商家或技师的推荐状态
	 * @param shopNum
	 * @return
	 */
	public Integer update(String shopNum) {
		TShopInfo shopInfo = get(TShopInfo.class, "shopNum", shopNum);
		Integer isRecommond = shopInfo.getIsRecommond();
		if (isRecommond==1) {
			shopInfo.setIsRecommond(2);
			update(shopInfo);
			return 1;
		} else if (isRecommond==2) {
			shopInfo.setIsRecommond(1);
			update(shopInfo);
			return 1;
		}
		return 0;
	}


}


