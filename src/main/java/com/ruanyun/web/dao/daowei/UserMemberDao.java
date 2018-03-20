package com.ruanyun.web.dao.daowei;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import org.springframework.stereotype.Repository;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.web.model.daowei.TUserMember;

@Repository("userMemberDao")
public class UserMemberDao extends BaseDaoImpl<TUserMember>{

	/**
	 * 功能描述：获取办理过会员卡的用户
	 * @param page
	 * @param userMember
	 * @return
	 */
	public Page<TUserMember> getList(Page<TUserMember> page, TUserMember userMember, String nickName) {
		StringBuilder sql = new StringBuilder(" select * from t_user_member t where 1=1 ");
		if (EmptyUtils.isNotEmpty(userMember)) {
			sql.append(SQLUtils.popuHqlEq("status", userMember.getStatus()));
		}
		if (EmptyUtils.isNotEmpty(nickName)) {
			sql.append(" AND exists( SELECT 1 FROM t_user u where u.user_num = t.user_num  and u.nick_name LIKE '%"+nickName+"%')");
		}
		sql.append(" order by t.end_time desc ");
		return sqlDao.queryPage(page, TUserMember.class, sql.toString());
	}
	
	/**
	 * 功能描述:设置为已过期
	 * @author cqm  2017-8-19 上午09:23:22
	 * @param userMemberNum
	 * @return
	 */
	public int updateMemberStatus(String userMemberNum){
		String sqlString = "update t_user_member set status=1 where user_member_num='"+userMemberNum+"'";
		return sqlDao.execute(sqlString);
	}
	
	/**
	 * 功能描述:定时器定时更新
	 * @author cqm  2017-8-19 下午03:28:47
	 * @return
	 */
	public int updateMember(){
		String sqlString = "update t_user_member set `status`=1 where NOW()>end_time";
		return sqlDao.execute(sqlString);
	}


}
