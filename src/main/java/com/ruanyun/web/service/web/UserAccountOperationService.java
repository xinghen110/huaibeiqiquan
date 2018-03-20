package com.ruanyun.web.service.web;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.dao.web.UserAccountOperationDao;
import com.ruanyun.web.model.TUserAccountFlow;
import com.ruanyun.web.model.TUserAccountOperation;
import com.ruanyun.web.model.TUserInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.util.DateUtils;
import com.ruanyun.web.util.HttpSessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@Service
public class UserAccountOperationService extends BaseServiceImpl<TUserAccountOperation> {

    @Autowired
    private UserAccountOperationDao userAccountOperationDao;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserAccountFlowService userAccountFlowService;


    /**
     * 根据用户id和审核状态查询用户信息审核表
     * @param userId
     * @param checkResult
     * @return
     */
//    public TUserInfoCheck queryUserInfoCheck(int userId, String checkResult) {
//        TUserInfoCheck userInfoCheck = new TUserInfoCheck();
//        userInfoCheck.setUserId(userId);
//        userInfoCheck.setCheckResult(checkResult);
//        return userInfoCheckDao.queryUserInfoCheck(userInfoCheck);
//    }


    /**
     * 功能描述：查询用户资金冲正审核表
     * @param page
     * @param
     * @return
     */
    public Page<TUserAccountOperation> queryUserAccountOperation(Page<TUserAccountOperation> page, TUserAccountOperation userAccountOperation, TUserInfo userInfo, String startTime, String endTime,Integer belongUserId,Integer parentCodeIndex,String loginName) {
//        Date newStartTime = null;
//        Date newEndTime = null;
//        if (EmptyUtils.isEmpty(startTime)) {
//            newStartTime = DateUtils.doFormatDate("1970-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
//        } else {
//            newStartTime = DateUtils.doFormatDate(startTime , "yyyy-MM-dd HH:mm:ss");
//        }
//        if (EmptyUtils.isEmpty(endTime)) {
//            newEndTime = new Date();
//        } else {
//            newEndTime = DateUtils.doFormatDate(endTime + "00:00:00", "yyyy-MM-dd HH:mm:ss");
//        }
//        return userAccountOperationDao.queryPage(page, userAccountOperation, userInfo, newStartTime, newEndTime);
        return userAccountOperationDao.queryPage(page, userAccountOperation, userInfo, startTime, endTime,belongUserId,parentCodeIndex,loginName);

    }

    public TUserAccountOperation save(TUserAccountOperation userAccountOperation, TUser user) {
        TUserInfo tUserInfo = userInfoService.get(TUserInfo.class ,userAccountOperation.getUserId());
        if (tUserInfo == null || StringUtils.isEmpty(tUserInfo.getUserName()) || StringUtils.isEmpty(tUserInfo.getIdNumber())) {
            throw new RuntimeException("该用户尚未实名验证,无法冲正");
        }
        userAccountOperation = new TUserAccountOperation(
                userAccountOperation.getUserId(),
                userAccountOperation.getMoney(),
                "0",
                user.getUserId(),
                new Date(),
                null,
                null
        );
        return userAccountOperationDao.save(userAccountOperation);
    }

    /**
     * 处理冲正申请
     * @param userAccountOperation
     */
    @Transactional
    public void handle(TUserAccountOperation userAccountOperation){
        TUserAccountOperation tUserAccountOperation = get(TUserAccountOperation.class, userAccountOperation.getId());
        if (!"0".equals(tUserAccountOperation.getStatus())) {
            throw new RuntimeException("该申请已经被处理过了");
        }
        tUserAccountOperation.setStatus(userAccountOperation.getStatus());
        tUserAccountOperation.setHandleUserId(userAccountOperation.getHandleUserId());
        tUserAccountOperation.setHandleDateTime(new Date());
        update(tUserAccountOperation);
        if(tUserAccountOperation.getStatus().equals("1")) {
            TUserAccountFlow flow = new TUserAccountFlow(
                    tUserAccountOperation.getUserId(),
                    tUserAccountOperation.getMoney(),
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    "userAccountOperation",
                    "",
                    tUserAccountOperation.getId().toString(),
                    0,
                    "用户资金冲正",
                    new Date()
            );
            userAccountFlowService.save(flow);
        }
    }
}
