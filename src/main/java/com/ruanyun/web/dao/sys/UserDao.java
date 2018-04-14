package com.ruanyun.web.dao.sys;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.TUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.sys.DictionaryService;
import com.ruanyun.web.util.Constants;


@Repository("userDao")
public class UserDao extends BaseDaoImpl<TUser>{
	
	@Autowired
	private DictionaryService dictionaryService;

	@Override
	protected String queryPageSql(TUser tuser, Map<String, Object> params) {
			
			StringBuffer  sql = new StringBuffer("from TUser where 1=1 ");//and userStatus=1
			if(tuser!=null){
			    sql.append(SQLUtils.popuHqlLike("nickName", tuser.getNickName(),params));
				sql.append(SQLUtils.popuHqlLike("loginName",tuser.getLoginName(),params));
				sql.append(SQLUtils.popuHqlEq("userSex",tuser.getUserSex(),params));
				sql.append(SQLUtils.popuHqlMin("createTime",tuser.getCreateTime(),params));
				sql.append(SQLUtils.popuHqlMax("createTime",tuser.getEndDate(),params));
				sql.append(SQLUtils.popuHqlEq("userType",tuser.getUserType(),params));
				sql.append(SQLUtils.popuHqlEq("userStatus",tuser.getUserStatus(),params));
				sql.append(SQLUtils.popuHqlCityEq("city", "area", tuser.getCity()));
				sql.append(SQLUtils.popuHqlEq("province", tuser.getProvince()));
				sql.append(SQLUtils.popuHqlEq("isMember", tuser.getIsMember()));
				sql.append(SQLUtils.popuHqlEq("memberLevel", tuser.getMemberLevel()));
			}
			sql.append(" ORDER BY createTime DESC");
		return sql.toString();
	}

	/**
	 * 功能描述：获取未审核的技师或店铺用户
	 * @param page
	 * @param user
	 * @return
	 */
	public Page<TUser> getList(Page<TUser> page, TUser user) {
		StringBuffer hql = new StringBuffer("from TUser where 1=1 ");
		if (EmptyUtils.isNotEmpty(user)) {
			hql.append(SQLUtils.popuHqlEq("userNum", user.getUserNum()));
			hql.append(SQLUtils.popuHqlLike("nickName", user.getNickName()));
			hql.append(SQLUtils.popuHqlMin("createTime", user.getStartTime()));
			hql.append(SQLUtils.popuHqlMax("createTime", user.getCreateTime()));
			hql.append(SQLUtils.popuHqlEq("userType", user.getUserType()));
			hql.append(SQLUtils.popuHqlEq("auditShopStatus", user.getAuditShopStatus()));
		}
		return hqlDao.queryPage(page, hql.toString());
	}

	/**
	 * 功能描述:ajax判断登录名称是否重复
	 *
	 * @author L H T  2013-11-26 下午06:24:41
	 * 
	 * @param loginName 登录名称
	 * @return
	 */
	public TUser getAjaxLoginName(String loginName,Integer userType){
		String sql = "from TUser where loginName = "+loginName;
		return hqlDao.get(sql.toString());
	}
	
	public int getUserCount(Integer orgId){
		String sql = "select count(*) from t_user where org_id = ?";
		return sqlDao.getCount(sql, orgId);
		
	}
	/**
	 * 功能描述:通过用户id查询用户的名称
	 *
	 * @author L H T  2013-12-2 下午02:30:11
	 * 
	 * @param userId
	 * @return
	 */
	public String getUserNameById(Integer userId){
		return hqlDao.get("select loginName from TUser where userId=? ", userId);
	}
	
	/**
	 * 功能描述:根据账户查询信息
	 * @author cqm  2016-12-29 上午09:58:40
	 * @param loginName
	 * @return
	 */
	public TUser getLoginName(String  loginName){
		return hqlDao.get("select userNum from TUser where loginName=? ", loginName);
	}
	/**
	 * 功能描述:通过组织org_Code查询用户
	 *
	 * @author L H T  2013-12-18 下午03:34:31
	 * 
	 * @param orgId
	 * @return
	 */
	public List<TUser> getUserByOrgId(Integer orgId){
		return hqlDao.getAll("from TUser where orgId=? and userStatus=1", orgId);
	}
	 
	
	/**
	 * 功能描述: 根据用户编号获取用户信息 返回值为map key为userNum value 为 user对象
	 *
	 * @author yangliu  2016年9月5日 上午10:48:16
	 * 
	 * @param userNums 用户编号格式为 A,B,C,D
	 * @return
	 */
	public Map<String,TUser> getUserByUserNums(String userNums){
		final Map<String,TUser> map = new HashMap<String, TUser>();
		String sql ="select * from t_user where user_num in ("+SQLUtils.sqlForIn(userNums)+")";
		jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String userNum=rs.getString("user_num");
				TUser user=new TUser();
				user.setUserNum(userNum);
				user.setNickName(rs.getString("nick_name"));
				user.setUserPhoto(rs.getString("user_photo"));
				user.setLoginName(rs.getString("login_name"));
				user.setUserSex(rs.getInt("user_sex"));
				user.setAreaid(rs.getString("areaid"));
				map.put(userNum, user);
			}
		});
		return map;
	}

	/**
	 * 功能描述：清除设备号
	 * @author cqm  2017-8-22 下午03:12:27
	 * @param registrationId
	 * @param userType
	 * @return
	 */
	 public int updateRegistrationId(String registrationId,Integer userType){
		 StringBuffer hql = new StringBuffer("update t_user set registration_id=Null  where user_type="+userType+"  and registration_id='"+registrationId+"' ");
		 return sqlDao.update(hql.toString());
	 }
	
	 public int updateRegistrationId(String registrationId,Integer userType,String userNum){
		 StringBuffer hql = new StringBuffer("update t_user set registration_id='"+registrationId+"'  where user_type="+userType+"  and user_num='"+userNum+"' ");
		 return sqlDao.update(hql.toString());
	 }
	
	public int updateQuery(String filedValue,String filed,String queryFiledValue,String tableName,String queryFiled){
		String sqls= "update "+tableName+" set "+filed+" = "+filedValue+" where "+queryFiled+"='"+queryFiledValue+"'";
		sqlDao.update(sqls);
		return 1;
	}

	
	public TUser getUserInfo(String loginName,String userType){
		if(userType.equals("1")){
			userType ="(1,5)";
		}else if(userType.equals("3")){
			userType="(3)";
		}else {
			userType ="(2)";
		}
		String sql = "select * from t_user where login_name='"+loginName.trim()+"' and user_status="+Constants.GLOBAL_STATUS+" and user_type in "+userType+"";
		return sqlDao.get(TUser.class,sql);
	}

	/**
	 * 重新写上面的登录
	 * @param loginName
	 * @param userType
	 * @return
	 */
	public TUser getUserInfomation(String loginName,String userType){
		if("1".equals(userType)){
			userType ="(1,5)";
		}else if("3".equals(userType)){
			userType="(3)";
		}else {
			userType ="(2)";
		}
		String sql = "select * from t_user where login_name='"+loginName.trim()+"' and user_status="+Constants.GLOBAL_STATUS;
		return sqlDao.get(TUser.class,sql);
	}

	/**
	 * 功能描述：通过客户端userNum查询如果他是技师或商家的信息，否则返回空
	 * @param userNum
	 * @return
	 */
	public TUser getShopUser(String userNum) {
		String sql = "select * from t_user where user_type=2 and login_name=(select login_name from t_user where user_num='"+userNum+"')";
		return sqlDao.get(TUser.class,sql);
	}

	/**
	 * 功能描述：获取分销提成用户列表
	 * @param page
	 * @param user
	 * @return
	 */
	public Page<?> fxlist(Page<?> page, TUser user) {
		StringBuilder sql = new StringBuilder("select tu1.user_num,tu1.nick_name,tu1.user_code");
		sql.append(",(select count(tu2.user_num) FROM t_user tu2 where tu2.parent_code = tu1.user_code) as count1");
		sql.append(",(select count(tu3.user_num) FROM t_user tu3 where tu3.parent_code in (select tu2.user_code FROM t_user tu2 where tu2.parent_code = tu1.user_code)) as count2");
		sql.append(",(select count(tu4.user_num) FROM t_user tu4 where tu4.parent_code in (select tu3.user_code FROM t_user tu3 where tu3.parent_code in (select tu2.user_code FROM t_user tu2 where tu2.parent_code = tu1.user_code))) as count3");
		sql.append(",(select SUM(tur.consum_price) FROM t_user_record tur where tu1.user_num = tur.user_num and tur.consum_type = 7) as consum_price");
		sql.append(" from t_user tu1 where 1=1 and tu1.user_type=3");
		if (EmptyUtils.isNotEmpty(user)) {
			sql.append(SQLUtils.popuHqlEq("tu1.nick_name", user.getNickName()));
		}
		sql.append(" order by tu1.create_time desc ");
		return sqlDao.queryPage(page, sql.toString());
	}

	/**
	 * 功能描述：获取分销详情
	 */
	public Page<TUser> fxDetail(Page<TUser> page, TUser user, Integer type) {
		StringBuilder sql = new StringBuilder();
		if (type == 1) {
			//获取一级分销所有的user_num
			sql.append("select tu1.user_num,tu1.nick_name from t_user tu1 where tu1.parent_code = '"+user.getUserCode()+"'");
		} else if (type == 2) {
			//获取二级分销所有的user_num
			sql.append("select tu2.user_num,tu2.nick_name from t_user tu2 where tu2.parent_code in (select tu1.user_code from t_user tu1 where tu1.parent_code = '"+user.getUserCode()+"')");
		} else if (type == 3) {
			//获取三级级分销所有的user_num
			sql.append("select tu3.user_num,tu3.nick_name from t_user tu3 where tu3.parent_code in (select tu2.user_code from t_user tu2 where tu2.parent_code in (select tu1.user_code from t_user tu1 where tu1.parent_code = '"+user.getUserCode()+"'))");
		}
		if (EmptyUtils.isNotEmpty(user)) {
			sql.append(SQLUtils.popuHqlEq("tu"+String.valueOf(type)+".nick_name", user.getNickName()));
		}
		return sqlDao.queryPage(page, TUser.class, sql.toString());
	}

	/**
	 * 功能描述：获取所有分销用户
	 * @param user
	 * @param type
	 * @return
	 */
	public List<TUser> getAllFXUserNum(TUser user, Integer type) {
		StringBuilder sql = new StringBuilder();
		if (type == 1) {
			//获取一级分销所有的user_num
			sql.append("select tu1.user_num,tu1.nick_name from t_user tu1 where tu1.parent_code = '"+user.getUserCode()+"'");
		} else if (type == 2) {
			//获取二级分销所有的user_num
			sql.append("select tu2.user_num,tu2.nick_name from t_user tu2 where tu2.parent_code in (select tu1.user_code from t_user tu1 where tu1.parent_code = '"+user.getUserCode()+"')");
		} else if (type == 3) {
			//获取三级级分销所有的user_num
			sql.append("select tu3.user_num,tu3.nick_name from t_user tu3 where tu3.parent_code in (select tu2.user_code from t_user tu2 where tu2.parent_code in (select tu1.user_code from t_user tu1 where tu1.parent_code = '"+user.getUserCode()+"'))");
		}
		if (EmptyUtils.isNotEmpty(user)) {
			sql.append(SQLUtils.popuHqlEq("tu"+String.valueOf(type)+".nick_name", user.getNickName()));
		}
		return sqlDao.getAll(TUser.class, sql.toString());
	}


	/**
	 * 查询当前登录用户的邀请的用户列表
	 * @param page
	 * @param currentUser	当前登陆者信息
	 * @param selectInviteUser	前台条件查询数据容器
	 * @return
	 */
	public Page queryUserByInvite(Page page, TUser currentUser, TUser selectInviteUser, TUserInfo userInfo, String startTime, String endTime,Integer belongUserId,Integer parentCodeIndex,String parentLoginName) {
		Map map = new HashMap();
		StringBuffer  sql = new StringBuffer();
		sql.append("SELECT");
		sql.append("  u.user_id             AS userId,");
		sql.append("  u.user_num            AS userNum,");
		sql.append("  u.user_code           AS userCode,");
		sql.append("  u.parent_code         AS parentCode,");
		sql.append("  u.nick_name           AS nickName,");
		sql.append("  u.login_name          AS loginName,");
		sql.append("  u.login_pass          AS loginPass,");
		sql.append("  u.pay_password        AS payPassword,");
		sql.append("  u.shop_name           AS shopName,");
		sql.append("  u.shop_num            AS shopNum,");
		sql.append("  u.user_sex            AS userSex,");
		sql.append("  u.user_phone          AS userPhone,");
		sql.append("  u.user_type           AS userType,");
		sql.append("  u.user_birth          AS userBirth,");
		sql.append("  u.create_user_num     AS createUserNum,");
		sql.append("  u.create_time         AS createTime,");
		sql.append("  u.user_status         AS userStatus,");
		sql.append("  u.work_status         AS workStatus,");
		sql.append("  u.province            AS province,");
		sql.append("  u.city                AS city,");
		sql.append("  u.area                AS area,");
		sql.append("  u.audit_shop_status   AS auditShopStatus,");
		sql.append("  u.user_photo          AS userPhoto,");
		sql.append("  u.bind_status         AS bindStatus,");
		sql.append("  u.send_msg_status     AS sendMsgStatus,");
		sql.append("  u.registration_id     AS registrationId,");
		sql.append("  u.is_member           AS isMember,");
		sql.append("  u.is_default_address  AS isDefaultAddress,");
		sql.append("  u.areaid              AS areaid,");
		sql.append("  ui.user_name          AS userName,");
		sql.append("  ui.id_number          AS idNumber,");
		sql.append("  ui.bank_card_number   AS bankCardNumber,");
		sql.append("  ui.status          	AS status,");
		sql.append("  parentUser.login_name AS parentLoginName,");
		sql.append("  u.member_level       	AS memberLevel");
		sql.append(" from t_user u  left join t_user_info ui on ui.user_id=u.user_id   ");
		sql.append(" LEFT JOIN t_user parentUser  ON parentUser.user_id = SUBSTRING_INDEX(u.parent_code, ',', - 1)  where 1=1 ");
		if(EmptyUtils.isNotEmpty(currentUser) &&  EmptyUtils.isNotEmpty(currentUser.getUserId())){
			if(currentUser.getUserType()!=Constants.USER_TYPE_MOTHER_01){
				sql.append(" and FIND_IN_SET("+currentUser.getUserId()+", u.parent_code) > 0");
			}
//			sql.append(" and u.user_type >"+IPSConstants.USER_TYPE_MOTHER_01);
		}
		if(EmptyUtils.isNotEmpty(selectInviteUser)&& EmptyUtils.isNotEmpty(selectInviteUser.getNickName())){
			sql.append("      AND u.nick_name like :nickName ");
			map.put("nickName","%"+selectInviteUser.getNickName()+"%");
		}
		if(EmptyUtils.isNotEmpty(selectInviteUser)&& EmptyUtils.isNotEmpty(selectInviteUser.getLoginName())){
			sql.append("      AND u.login_name like :loginName ");
			map.put("loginName","%"+selectInviteUser.getLoginName()+"%");
		}
		if(EmptyUtils.isNotEmpty(parentLoginName)){
			sql.append("      AND parentUser.login_name like :parentLoginName ");
			map.put("parentLoginName","%"+parentLoginName+"%");
		}
		if(EmptyUtils.isNotEmpty(selectInviteUser)&& EmptyUtils.isNotEmpty(selectInviteUser.getUserStatus())){
			sql.append("      AND u.user_status = :userStatus ");
			map.put("userStatus", selectInviteUser.getUserStatus());
		}
		if(EmptyUtils.isNotEmpty(selectInviteUser)  && EmptyUtils.isNotEmpty(selectInviteUser.getUserType())&&(!"-1".equals(selectInviteUser.getUserType().toString()))){
			sql.append("      AND u.user_type = :userType ");
			map.put("userType",selectInviteUser.getUserType());
		}
		if(EmptyUtils.isNotEmpty(selectInviteUser)  && EmptyUtils.isNotEmpty(selectInviteUser.getUserType())&&"-1".equals(selectInviteUser.getUserType().toString())){
			sql.append("      AND u.user_type in (2,3,4)");
		}
		if (EmptyUtils.isNotEmpty(userInfo) && EmptyUtils.isNotEmpty(userInfo.getUserName())) {
			sql.append("      AND ui.user_name like :userName ");
			map.put("userName","%" + userInfo.getUserName()+ "%");
		}
		if(EmptyUtils.isNotEmpty(userInfo)&& EmptyUtils.isNotEmpty(userInfo.getStatus())){
			sql.append("      AND ui.status = :status ");
			map.put("status", userInfo.getStatus());
		}
		if (EmptyUtils.isNotEmpty(startTime)) {
			sql.append("      AND u.create_time >= :startTime ");
			map.put("startTime", startTime  );
		}
		if (EmptyUtils.isNotEmpty(endTime)) {
			sql.append("      AND u.create_time <= :endTime ");
			map.put("endTime", endTime  );
		}
		if (belongUserId>0) {
			Integer index = parentCodeIndex-1;
			if (index==3) {
				sql.append("   AND SUBSTRING_INDEX(u.parent_code,',',-1) = " + belongUserId);
			}
			if(index==1||index==2){
				sql.append("   AND SUBSTRING_INDEX(SUBSTRING_INDEX(u.parent_code,',',"+index+"),',',-1) = "+belongUserId);
			}
		}
		Page queryPage = sqlDao.queryPage(sql.toString(),page,map);
		page.setResult(queryPage.getResult());
		page.setTotalCount(queryPage.getTotalCount());
		return page;
	}

	@Override
	public Page queryPage(Page page, TUser user) {
		return queryUserByInvite(page,null,user, null, null, null,-1,-1,null);
	}


	/**
	 * 查询带有上一级的用户列表
	 * @param page
	 * @param currentUser
	 * @param user
	 * @return
	 */
	public Page queryUserAndParentInfoList(Page page, TUser user,TUser currentUser, TUserInfo userInfo, String startTime, String endTime,Integer belongUserId,Integer parentCodeIndex) {
		Map map = new HashMap();
		StringBuffer  sql = new StringBuffer();
		sql.append("SELECT");
		sql.append("  u.user_id             AS userId,");
		sql.append("  u.user_id             AS id,");
		sql.append("  substring_index(u.parent_code,',',-1) AS pId,");
		sql.append("  u.nick_name           AS name,");
		sql.append("  u.user_num            AS userNum,");
		sql.append("  u.user_code           AS userCode,");
		sql.append("  u.parent_code         AS parentCode,");
		sql.append("  u.nick_name           AS nickName,");
		sql.append("  u.login_name          AS loginName,");
		sql.append("  u.login_pass          AS loginPass,");
		sql.append("  u.pay_password        AS payPassword,");
		sql.append("  u.shop_name           AS shopName,");
		sql.append("  u.shop_num            AS shopNum,");
		sql.append("  u.user_sex            AS userSex,");
		sql.append("  u.user_phone          AS userPhone,");
		sql.append("  u.user_type           AS userType,");
		sql.append("  u.user_birth          AS userBirth,");
		sql.append("  u.create_user_num     AS createUserNum,");
		sql.append("  u.create_time         AS createTime,");
		sql.append("  u.user_status         AS userStatus,");
		sql.append("  u.work_status         AS workStatus,");
		sql.append("  u.province            AS province,");
		sql.append("  u.city                AS city,");
		sql.append("  u.area                AS area,");
		sql.append("  u.audit_shop_status   AS auditShopStatus,");
		sql.append("  u.user_photo          AS userPhoto,");
		sql.append("  u.bind_status         AS bindStatus,");
		sql.append("  u.send_msg_status     AS sendMsgStatus,");
		sql.append("  u.registration_id     AS registrationId,");
		sql.append("  u.is_member           AS isMember,");
		sql.append("  u.is_default_address  AS isDefaultAddress,");
		sql.append("  u.areaid              AS areaid,");
		sql.append("  tui.user_name          AS userName,");
		sql.append("  tui.id_number          AS idNumber,");
		sql.append("  tu.login_name          AS parentLoginName,");
		sql.append("  u.member_level       	AS memberLevel");
		sql.append(" from t_user u left join t_user_info tui on tui.user_id = u.user_id ");
		sql.append(" left join t_user tu on tu.user_id = SUBSTRING_INDEX(u.parent_code, ',', - 1) where 1=1 ");
		if(EmptyUtils.isNotEmpty(currentUser.getUserType())&&Constants.USER_TYPE_MOTHER_01!=currentUser.getUserType()){
			sql.append(" and FIND_IN_SET("+currentUser.getUserId()+", u.parent_code) > 0");
		}
		if(EmptyUtils.isNotEmpty(user)&& EmptyUtils.isNotEmpty(user.getLoginName())){
			sql.append("      AND u.login_name like :loginName ");
			map.put("loginName","%"+user.getLoginName()+"%");
		}
		if(EmptyUtils.isNotEmpty(user)  && EmptyUtils.isNotEmpty(user.getUserType())){
			sql.append("      AND u.user_type = :userType ");
			map.put("userType",user.getUserType());
		}
		if(EmptyUtils.isNotEmpty(user)&& EmptyUtils.isNotEmpty(user.getUserStatus())){
			sql.append("      AND u.user_status = :userStatus ");
			map.put("userStatus", user.getUserStatus());
		}
		if (EmptyUtils.isNotEmpty(userInfo) && EmptyUtils.isNotEmpty(userInfo.getUserName())) {
			sql.append("      AND tui.user_name like :userName ");
			map.put("userName","%" + userInfo.getUserName()+ "%");
		}
		if (EmptyUtils.isNotEmpty(startTime)) {
			sql.append("      AND u.create_time >= :startTime ");
			map.put("startTime", startTime  );
		}
		if (EmptyUtils.isNotEmpty(endTime)) {
			sql.append("      AND u.create_time <= :endTime ");
			map.put("endTime", endTime  );
		}
		if (belongUserId>0) {
			Integer index = parentCodeIndex-1;
			if (index==3) {
				sql.append("   AND SUBSTRING_INDEX(u.parent_code,',',-1) = " + belongUserId);
			}
			if(index==2||index==1){
				sql.append("   AND SUBSTRING_INDEX(SUBSTRING_INDEX(u.parent_code,',',"+index+"),',',-1) = "+belongUserId);
			}

		}
		Page queryPage = sqlDao.queryPage(sql.toString(),page,map);
		page.setResult(queryPage.getResult());
		page.setTotalCount(queryPage.getTotalCount());
		return page;
	}

	public List<HashMap> list(TUser currentUser){
		StringBuffer stringBuffer = new StringBuffer();
		Integer userType = currentUser.getUserType();
		if(currentUser.getUserType()!=5){
			userType = currentUser.getUserType() +1;
		}
		stringBuffer.append("select ");
		stringBuffer.append(" CASE WHEN u.user_type = 5 THEN u.login_name ELSE u.nick_name END AS name,");
		stringBuffer.append(" substring_index(u.parent_code,',',-1) as pId,");
		stringBuffer.append(" u.user_id 							  as id,");
		stringBuffer.append(" u.user_type 						  as userType ");
		stringBuffer.append(" from t_user u ");
		stringBuffer.append(" where 1=1 ");
		stringBuffer.append(" and u.user_type <5");
		if(currentUser.getUserType()!=1){
			stringBuffer.append(" and FIND_IN_SET("+currentUser.getUserId()+", u.parent_code) > 0");
		}
		List list = sqlDao.getAll(stringBuffer.toString());
		return list;
	}

	public List<HashMap> list(TUser currentUser, TUser selectInviteUser, TUserInfo userInfo, String startTime, String endTime){
		Map map =new HashMap();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT");
		sql.append("  u.user_id             AS userId,");
		sql.append("  u.user_id             AS id,");
		sql.append("  substring_index(u.parent_code,',',-1) as pId,");
		sql.append("  u.nick_name           AS name,");
		sql.append("  u.user_num            AS userNum,");
		sql.append("  u.user_code           AS userCode,");
		sql.append("  u.parent_code         AS parentCode,");
		sql.append("  u.nick_name           AS nickName,");
		sql.append("  u.login_name          AS loginName,");
		sql.append("  u.login_pass          AS loginPass,");
		sql.append("  u.pay_password        AS payPassword,");
		sql.append("  u.shop_name           AS shopName,");
		sql.append("  u.shop_num            AS shopNum,");
		sql.append("  u.user_sex            AS userSex,");
		sql.append("  u.user_phone          AS userPhone,");
		sql.append("  u.user_type           AS userType,");
		sql.append("  u.user_birth          AS userBirth,");
		sql.append("  u.create_user_num     AS createUserNum,");
		sql.append("  u.create_time         AS createTime,");
		sql.append("  u.user_status         AS userStatus,");
		sql.append("  u.work_status         AS workStatus,");
		sql.append("  u.province            AS province,");
		sql.append("  u.city                AS city,");
		sql.append("  u.area                AS area,");
		sql.append("  u.audit_shop_status   AS auditShopStatus,");
		sql.append("  u.user_photo          AS userPhoto,");
		sql.append("  u.bind_status         AS bindStatus,");
		sql.append("  u.send_msg_status     AS sendMsgStatus,");
		sql.append("  u.registration_id     AS registrationId,");
		sql.append("  u.is_member           AS isMember,");
		sql.append("  u.is_default_address  AS isDefaultAddress,");
		sql.append("  u.areaid              AS areaid,");
		sql.append("  ui.user_name          AS userName,");
		sql.append("  ui.id_number          AS idNumber,");
		sql.append("  parentUser.login_name AS parentLoginName,");
		sql.append("  u.member_level       	AS memberLevel");
		sql.append(" from t_user u  left join t_user_info ui on ui.user_id=u.user_id   ");
		sql.append(" LEFT JOIN t_user parentUser  ON parentUser.user_id = SUBSTRING_INDEX(u.parent_code, ',', - 1)  where 1=1 ");
		if(EmptyUtils.isNotEmpty(currentUser) &&  EmptyUtils.isNotEmpty(currentUser.getUserId())){
			if(currentUser.getUserType()!=Constants.USER_TYPE_MOTHER_01){
				sql.append(" and FIND_IN_SET("+currentUser.getUserId()+", u.parent_code) > 0");
			}
			if(currentUser.getUserType()!=Constants.USER_TYPE_MOTHER_01 && String.valueOf(Constants.USER_TYPE_SPREAD_04).equals(currentUser.getFlag3())){
				sql.append(" and FIND_IN_SET("+currentUser.getUserId()+", u.parent_code) > 0");
			}
//			sql.append(" and u.user_type >"+IPSConstants.USER_TYPE_MOTHER_01);
		}
		if(EmptyUtils.isNotEmpty(selectInviteUser)&& EmptyUtils.isNotEmpty(selectInviteUser.getNickName())){
			sql.append("      AND u.nick_name like :nickName ");
			map.put("nickName","%"+selectInviteUser.getNickName()+"%");
		}
		if(EmptyUtils.isNotEmpty(selectInviteUser)&& EmptyUtils.isNotEmpty(selectInviteUser.getLoginName())){
			sql.append("      AND u.login_name like :loginName ");
			map.put("loginName","%"+selectInviteUser.getLoginName()+"%");
		}
		if(EmptyUtils.isNotEmpty(selectInviteUser)&& EmptyUtils.isNotEmpty(selectInviteUser.getUserStatus())){
			sql.append("      AND u.user_status = :userStatus ");
			map.put("userStatus", selectInviteUser.getUserStatus());
		}
		if(EmptyUtils.isNotEmpty(selectInviteUser)  && EmptyUtils.isNotEmpty(selectInviteUser.getUserType())&&(!"-1".equals(selectInviteUser.getUserType().toString()))){
			sql.append("      AND u.user_type = "+selectInviteUser.getUserType());
//			map.put("userType",selectInviteUser.getUserType());
		}
		if(EmptyUtils.isNotEmpty(selectInviteUser)  && EmptyUtils.isNotEmpty(selectInviteUser.getUserType())&&"-1".equals(selectInviteUser.getUserType().toString())){
			sql.append("      AND u.user_type in (2,3,4)");
		}
		if (EmptyUtils.isNotEmpty(userInfo) && EmptyUtils.isNotEmpty(userInfo.getUserName())) {
			sql.append("      AND ui.user_name like :userName ");
			map.put("userName","%" + userInfo.getUserName()+ "%");
		}
		if (EmptyUtils.isNotEmpty(startTime)) {
			sql.append("      AND u.create_time >= :startTime ");
			map.put("startTime", startTime  );
		}
		if (EmptyUtils.isNotEmpty(endTime)) {
			sql.append("      AND u.create_time <= :endTime ");
			map.put("endTime", endTime  );
		}
		return sqlDao.getAll(sql.toString());
	}
}
