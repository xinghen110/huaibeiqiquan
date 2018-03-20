
import com.capinfo.crypt.Md5;
import com.google.gson.JsonObject;
import com.ruanyun.common.orm.ICommonDao;
import com.ruanyun.common.orm.ICommonSqlDao;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.MD5Util;
import com.ruanyun.web.dao.web.OrgInfoDao;
import com.ruanyun.web.dao.web.UserAccountOperationDao;
import com.ruanyun.web.model.*;
import com.ruanyun.web.model.payeasy.StandardPaymentRequestEntity;
import com.ruanyun.web.model.payeasy.StandardPaymentRetuenEntity;
import com.ruanyun.web.model.sys.TRole;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.model.web.OptionModel;
import com.ruanyun.web.model.web.SearchPriceContentModel;
import com.ruanyun.web.model.web.TOrgInfo;
import com.ruanyun.web.service.sys.UserRoleService;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.service.web.*;
import com.ruanyun.web.sms.SendMessage;
import com.ruanyun.web.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml" /*, "classpath:spring-servlet.xml"*/})
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
public class ApiTest {


    @Autowired
    @Qualifier("commonSqlExpandDao")
    protected ICommonSqlDao sqlDao;
    @Autowired
    private OrgInfoDao orgInfoDao;
    @Autowired
    private OrgInfoService orgInfoService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserAccountFlowService userAccountFlowService;
    @Autowired
    private UserAccountOperationService userAccountOperationService;
    @Autowired
    private UserAccountOrderService userAccountOrderService;
    @Autowired
    private UserInfoCheckService userInfoCheckService;
    @Autowired
    private BusinessOrderService businessOrderService;
    @Autowired
    private UserStockService userStockService;
    @Autowired
    private StockPlanService stockPlanService;


    @Test
    @Transactional
    public void testMd5() {
        //WebConstans.ALIYUN_SMS_CODE_USER_SUCCESS
       //SendMessage.doAliyun("15209827319", "SMS_122294261","未通过");
        SendMessage.doAliyun("15209827319","SMS_122297946","123"+"，市民");
        //getAllUser_XianShang();
    }

    //businessOrderService
    @Transactional
    public void getAllUser_XianShang(){
        List<Map<String,Object>> list =sqlDao.getAll("SELECT * FROM jinniu_xianshang.t_user WHERE user_type=1");
        System.out.println(list.size());
        for(Map<String,Object> map : list){

            //getUser(map);
            saveOther(map);
        }
    }

    public void  saveOther(Map<String,Object> map){
        String loginNmae=map.get("login_name").toString();
        String password=map.get("login_pass").toString();
        String parentCode=map.get("parent_code").toString();
        String currentUserNum="SY00000000000001";
        String nickName=getValue(map.get("nick_name"));
        Date createTime=(Date)map.get("create_time");
        Integer oldUserId=Integer.parseInt(map.get("user_id").toString());
        TUser user=userService.get(TUser.class,"loginName",loginNmae);
        user.setLoginPass(password);
        user.setCreateTime(createTime);
        userService.update(user);
        System.out.println(JSONObject.fromObject(user).toString());
        // deleteAll(user,oldUserId);
        //saveAll(user,oldUserId);
        //saveAllOther(user,oldUserId);
    }

    public void deleteAll(TUser user,Integer oldUserId){
       // sqlDao.delete("delete from t_user_info where user_id="+user.getUserId());
        sqlDao.delete("delete from t_user_account where user_id="+user.getUserId());
        sqlDao.delete("delete from T_User_Account_Flow where user_id="+user.getUserId());
    }

    public void saveAll(TUser user,Integer oldUserId){
        // 保存用户详细信息
      /*  TUserInfo userInfo =sqlDao.get(TUserInfo.class,"select * from jinniu_xianshang.t_user_info where user_id="+oldUserId);

        TUserInfo newUserInfo = new TUserInfo();
        BeanUtils.copyProperties(userInfo,newUserInfo,new String[]{"userId"});
        newUserInfo.setUserId(user.getUserId());
        userInfoService.save(newUserInfo);*/

        //保持用户账号
        TUserAccount userAccount=sqlDao.get(TUserAccount.class,"select * from jinniu_xianshang.t_user_account where user_id="+oldUserId);
        TUserAccount newUserAccount=new TUserAccount();
        BeanUtils.copyProperties(userAccount,newUserAccount,new String[]{"userId"});
        newUserAccount.setUserId(user.getUserId());
        userAccountService.save(newUserAccount);
        // 流水表
        List<TUserAccountFlow> userAccountFlowList=sqlDao.getAll(TUserAccountFlow.class,"select * from jinniu_xianshang.T_User_Account_Flow where user_id="+oldUserId);
        TUserAccountFlow flowNew=null;
        for (TUserAccountFlow flow : userAccountFlowList){
            flowNew=new TUserAccountFlow();
            BeanUtils.copyProperties(flow,flowNew,new String[]{"flowId","userId"});
            flowNew.setUserId(user.getUserId());
            userAccountFlowService.saveSuper(flowNew);
        }

    }

    public void saveAllOther(TUser user,Integer oldUserId){

        //冲正
        List<TUserAccountOperation> userAccountOperationList=sqlDao.getAll(TUserAccountOperation.class,"select * from jinniu_xianshang.t_user_account_operation where user_id="+oldUserId);
        TUserAccountOperation operationNew=null;
        for (TUserAccountOperation operation : userAccountOperationList){
            operationNew=new TUserAccountOperation();
            BeanUtils.copyProperties(operation,operationNew,new String[]{"id","userId"});
            operationNew.setUserId(user.getUserId());
            userAccountOperationService.save(operationNew);
        }

        //体现
        List<TUserAccountOrder> userAccountOrderList=sqlDao.getAll(TUserAccountOrder.class,"select * from jinniu_xianshang.t_user_account_order where user_id="+oldUserId);
        TUserAccountOrder userAccountOrderNew=null;
        for (TUserAccountOrder order  : userAccountOrderList){
            userAccountOrderNew=new TUserAccountOrder();
            BeanUtils.copyProperties(order,userAccountOrderNew,new String[]{"orderId","userId"});
            userAccountOrderNew.setUserId(user.getUserId());
            userAccountOrderService.saveSuper(userAccountOrderNew);
        }


        //审核
        List<TUserInfoCheck> userInfoCheckList=sqlDao.getAll(TUserInfoCheck.class,"select * from jinniu_xianshang.T_User_Info_Check where user_id="+oldUserId);
        TUserInfoCheck userInfoCheckNew=null;
        for (TUserInfoCheck check   : userInfoCheckList){
            userInfoCheckNew=new TUserInfoCheck();
            BeanUtils.copyProperties(check,userInfoCheckNew,new String[]{"id","userId"});
            userInfoCheckNew.setUserId(user.getUserId());
            userInfoCheckService.save(userInfoCheckNew);
        }

        //充值
       List<TBusinessOrder> businessOrderList=sqlDao.getAll(TBusinessOrder.class,"select * from jinniu_xianshang.t_business_order where buyer_user_id="+oldUserId);
        TBusinessOrder businessOrderNew=null;
        for (TBusinessOrder order   : businessOrderList){
            businessOrderNew=new TBusinessOrder();
            BeanUtils.copyProperties(order,businessOrderNew,new String[]{"businessOrder","buyerUserId"});
            businessOrderNew.setBuyerUserId(user.getUserId());
            businessOrderService.saveSuper(businessOrderNew);
        }

        //自选
        List<TUserStock> userStockList=sqlDao.getAll(TUserStock.class,"select * from jinniu_xianshang.T_User_Stock where user_Id="+oldUserId);
        TUserStock userStockNew=null;
        for (TUserStock userStock   : userStockList){
            userStockNew=new TUserStock();
            BeanUtils.copyProperties(userStock,userStockNew,new String[]{"id","userId"});
            userStockNew.setUserId(user.getUserId());
            userStockService.saveSuper(userStockNew);
        }

      // 下单
        List<TStockPlan> stockPlanList=sqlDao.getAll(TStockPlan.class,"select * from jinniu_xianshang.T_Stock_Plan where user_Id="+oldUserId);
        TStockPlan stockPlanNew=null;
        for (TStockPlan plan   : stockPlanList){
            stockPlanNew=new TStockPlan();
            BeanUtils.copyProperties(plan,stockPlanNew,new String[]{"planId","userId"});
            stockPlanNew.setUserId(user.getUserId());
            stockPlanService.saveSuper(stockPlanNew);
        }
    }




        public TOrgInfo getParentCode(String code){
        String [] codes=code.split(",");
        String nowCode=codes[codes.length-1];
        TOrgInfo orgInfo=orgInfoService.getOrgInfoByOrgCode(nowCode);
        return orgInfo;
    }
    public String getValue(Object obj){
        if(obj==null)
            return "";
        return obj.toString();
    }
   // @Transient
    public void getUser(Map<String,Object> map) {

       String loginName=map.get("login_name").toString();
       String password=map.get("login_pass").toString();
       String parentCode=map.get("parent_code").toString();
       String currentUserNum="SY00000000000001";
       String nickName=getValue(map.get("nick_name"));
       Integer oldUserId=Integer.parseInt(map.get("user_id").toString());

        TOrgInfo parentOrgInfo=getParentCode(parentCode);

        if(parentOrgInfo==null){
           return;
        }
        TUser user= new TUser();
        user.setNickName(nickName);
        user.setCreateTime(new Date());
        user.setParentCode(parentOrgInfo.getCode());
        user.setLoginName(loginName);
        user.setLoginPass(password);
        user.setUserType(Constants.USER_TYPE_99);
        user.setCreateUserNum(currentUserNum);
        user.setUserId(null);
        user.setUserStatus(Constants.GLOBAL_STATUS);
        user.setBindStatus(2);

        System.out.println("===================="+oldUserId);
        userService.save(user);
        userRoleService.save(String.valueOf(5), String.valueOf(user.getUserId()), new TUser());
        //TUser tUser = userService.save(user,new TRole());

        user.setUserNum(NumUtils.getCommondNum(NumUtils.PIX_USER_NUM,user.getUserId()));
        saveAll(user,oldUserId);

    }





    public void getAll(){
        List<Map<String,String>> list= sqlDao.getAll("select * from t_test");
        System.out.println("=========================================="+list.size());
        for(Map<String,String> map : list){
            saveOrUpdate(map);
        }
    }


    public Integer saveOrUpdate(Map<String,String> map) {
        String orgId=map.get("org_id");
        String orgInfoNum=map.get("org_info_num");
        String orgName=map.get("org_name");
        String orgCode=map.get("org_code");
        String code=map.get("code");
        String parentCode=map.get("parent_code");
        String createUserNum="SY00000000000001";//map.get("createUserNum");
        String createTime=map.get("create_time");
        String orgType=map.get("org_type");
        String loginName=map.get("login_name");
        String password=map.get("password");
        if(!orgType.equals("3"))
            return -1;

        TOrgInfo newOrgInfo = new TOrgInfo();
        newOrgInfo.setOrgName(orgName);
        newOrgInfo.setCreateTime(new Date());
        newOrgInfo.setCreateUserNum(createUserNum);
        newOrgInfo.setIncomeMoney(new BigDecimal(0));
        newOrgInfo.setAlreadyMoney(new BigDecimal(0));
        newOrgInfo.setOrgCode(orgCode);
        newOrgInfo.setParentCode(parentCode);
        orgInfoService.save(newOrgInfo);

        //新建用户对象并持久化
        TUser newUser = new TUser();
        newUser.setCreateUserNum(createUserNum);
        newUser.setLoginName(loginName);
        newUser.setLoginPass(password);
        newUser.setUserStatus(1);
        newUser.setCreateTime(new Date());
        newUser.setBindStatus(2);
        newUser.setNickName(newOrgInfo.getOrgName());
        newUser.setUserPhone(newOrgInfo.getLinkTel());
        userService.save(newUser);
        //3级以下，表示添加的是代理，共5级代理
        //如果orgType为3，并且parentCode为空表示添加的是一级代理。
        //因只有会员能添加代理商，因此一级代理的父级便是当前登录会员的code
        newUser.setUserType(Constants.USER_TYPE_4);
        Integer count = orgInfoDao.getCountByParentCode(newOrgInfo.getParentCode(), null);  //判断下级用户的数量
            //设置机构编号
        newOrgInfo.setParentCode(parentCode);
        newOrgInfo.setOrgType(3);
        newOrgInfo.setCode(code);
        newOrgInfo.setOrgInfoNum(NumUtils.getCommondNum(NumUtils.PIX_ORG_INFO_DAILISHANG, newOrgInfo.getOrgInfoId()));
        userRoleService.save(String.valueOf(Constants.ROLE_ID_4), String.valueOf(newUser.getUserId()), null);
            //机构用户在用户表中的parentCode是当前生成的机构编码
        newUser.setParentCode(newOrgInfo.getCode());
        newUser.setUserNum(NumUtils.getCommondNum(NumUtils.PIX_USER_DAILISHANG, newUser.getUserId()));
        userService.update(newUser);

        newOrgInfo.setUserNum(newUser.getUserNum());
        orgInfoService.update(newOrgInfo);
        System.out.println("============================="+JSONObject.fromObject(newOrgInfo));
        return 1;
    }


}
