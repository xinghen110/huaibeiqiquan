package com.ruanyun.web.service.daowei;


import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.dao.daowei.MealInfoDao;
import com.ruanyun.web.model.daowei.TMealInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangyf on 2017/8/4.
 */
@Service("mealInfoService")
public class MealInfoService extends BaseServiceImpl<TMealInfo> {

    @Autowired
    private MealInfoDao mealInfoDao;

    /**
     * 功能描述:查询列表
     * @author wyf  2017-8-21 上午10:12:23
     * @param page
     * @param mealInfo
     * @return
     */
    public Page<TMealInfo> getList(Page<TMealInfo> page, TMealInfo mealInfo) {
        return mealInfoDao.getList(page, mealInfo);
    }

    /**
     * 功能描述:查询列表
     * @author wyf  2017-8-21 上午10:12:23
     * @param page
     * @param mealInfo
     * @return
     */
    public Page<?> getList2(Page<?> page, TMealInfo mealInfo) {
        return mealInfoDao.getList2(page, mealInfo);
    }

    /**
     * 功能描述：通过 mealInfoNum 更改 TMealInfo 的推荐状态
     * @param mealInfoNum
     * @return
     */
    public Integer update(String mealInfoNum) {
        TMealInfo mealInfo = get(TMealInfo.class, "mealInfoNum", mealInfoNum);
        Integer isRecommend = mealInfo.getIsRecommend();
        if (isRecommend==1) {
            mealInfo.setIsRecommend(2);
            update(mealInfo);
            if (!isRecommend.equals(get(TMealInfo.class, "mealInfoNum", mealInfoNum).getIsRecommend()))
                return  1;
        } else if (isRecommend==2) {
            mealInfo.setIsRecommend(1);
            update(mealInfo);
            if (!isRecommend.equals(get(TMealInfo.class, "mealInfoNum", mealInfoNum).getIsRecommend()))
                return  1;
        }
        return 0;
    }
    
    /**
     * 功能描述:获取套餐详情
     * @author cqm  2017-8-17 下午04:55:38
     * @param mealInfoNum
     * @param isRequired
     * @return
     */
    public TMealInfo getMealInfo(String mealInfoNum,boolean isRequired) {
        TMealInfo mealInfo = get(TMealInfo.class, "mealInfoNum", mealInfoNum);
        if(isRequired && mealInfo==null){
        	throw new RuanYunException("该套餐不存在");
        }
        return mealInfo;
    }

    /**
     * 功能描述：根据某些条件获取套餐
     * @param title
     * @param typeNums
     * @param type
     * @return
     */
    public List getMealInfoByCondition(String title, String typeNums, Integer type, Integer memberLevel, Integer startNum) {
       System.out.println("====");
    	if (EmptyUtils.isNotEmpty(typeNums)) {
            String[] typeNum = typeNums.split(",");
            return mealInfoDao.getMealInfoByCondition(title, typeNum, type, memberLevel,startNum);
        } else {
            return mealInfoDao.getMealInfoByCondition(title, null, type, memberLevel, startNum);
        }
    }

    /**
     * 功能描述：获取全部套餐
     * @param shopNum
     * @return
     */
    public List getMealInfos(String shopNum) {
        return mealInfoDao.getMealInfos(shopNum);
    }

}
