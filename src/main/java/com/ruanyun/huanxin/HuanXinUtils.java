package com.ruanyun.huanxin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.conn.EofSensorInputStream;
import org.junit.Test;

import com.push.AliyunBasePush;
import com.push.PushModel;
import com.ruanyun.common.utils.DateUtils;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.huanxin.api.ChatGroupAPI;
import com.ruanyun.huanxin.api.ChatMessageAPI;
import com.ruanyun.huanxin.api.ChatRoomAPI;
import com.ruanyun.huanxin.api.FileAPI;
import com.ruanyun.huanxin.api.IMUserAPI;
import com.ruanyun.huanxin.api.SendMessageAPI;
import com.ruanyun.huanxin.comm.ClientContext;
import com.ruanyun.huanxin.comm.EasemobRestAPIFactory;
import com.ruanyun.huanxin.comm.body.ChatGroupBody;
import com.ruanyun.huanxin.comm.body.IMUserBody;
import com.ruanyun.huanxin.comm.body.IMUsersBody;
import com.ruanyun.huanxin.comm.body.ModifyChatGroupBody;
import com.ruanyun.huanxin.comm.body.TextMessageBody;
import com.ruanyun.huanxin.comm.body.UserNamesBody;
import com.ruanyun.huanxin.comm.wrapper.BodyWrapper;
import com.ruanyun.huanxin.comm.wrapper.ResponseWrapper;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.model.UploadVo;
import com.ruanyun.web.util.Constants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HuanXinUtils {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HuanXinUtils.class);
	
	private static EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
	private static IMUserAPI user = (IMUserAPI)factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
	private static ChatMessageAPI chat = (ChatMessageAPI)factory.newInstance(EasemobRestAPIFactory.MESSAGE_CLASS);
	private static FileAPI file = (FileAPI)factory.newInstance(EasemobRestAPIFactory.FILE_CLASS);
	private static SendMessageAPI message = (SendMessageAPI)factory.newInstance(EasemobRestAPIFactory.SEND_MESSAGE_CLASS);
	private static ChatGroupAPI chatgroup = (ChatGroupAPI)factory.newInstance(EasemobRestAPIFactory.CHATGROUP_CLASS);
	private static ChatRoomAPI chatroom = (ChatRoomAPI)factory.newInstance(EasemobRestAPIFactory.CHATROOM_CLASS);
	
	/**
	 * 功能描述:注册用户信息
	 *
	 * @author yangliu  2016年7月26日 下午5:12:59
	 * 
	 * @param userName 用户名
	 * @param password 密码
	 * @param nickName 昵称
	 */
	public static void createUser(String userNun,String password,String nickName){
		BodyWrapper payload = new IMUserBody(userNun, password, nickName);
		ResponseWrapper object=(ResponseWrapper) user.createNewIMUserBatch(payload);
		if(object.getResponseStatus()!=200){
			logger.error("环信注册失败："+object.getResponseStatus()+",userNun:"+userNun);
			throw new RuanYunException("用户注册失败");
		}
	}
	
	/**
	 * 功能描述:删除用户失败
	 *
	 * @author yangliu  2016年7月26日 下午5:36:32
	 * 
	 * @param userNum 用户编号
	 */
	public static void deleteUser(String userNum){
		ResponseWrapper object=(ResponseWrapper) user.deleteIMUserByUserName(userNum);
		if(object.getResponseStatus()!=200){
			logger.error("环信删除用户失败："+object.getResponseStatus()+",userName:"+userNum);
			throw new RuanYunException("环信删除用户失败");
		}
	}
	
	
	/**
	 * 功能描述: 添加好友
	 *
	 * @author yangliu  2016年7月26日 下午5:39:47
	 * 
	 * @param userNum
	 * @param friendNum
	 */
	public static void addFriendSingle(String userNum, String friendNum){

		ResponseWrapper object=(ResponseWrapper) user.addFriendSingle(userNum,friendNum);
		System.out.println(object.toString());
		if(object.getResponseStatus()!=200){
			logger.error("环信添加好友："+object.getResponseStatus()+",userName:"+userNum+",friendNum:"+friendNum);
			throw new RuanYunException("环信添加好友失败");
		}
	}
	
	
	/**
	 * 功能描述:删除好友
	 *
	 * @author yangliu  2016年7月26日 下午5:39:47
	 * 
	 * @param userNum
	 * @param friendNum
	 */
	public static void deleteFriendSingle(String userNum, String friendNum){
		ResponseWrapper object=(ResponseWrapper) user.deleteFriendSingle(userNum,friendNum);
		if(object.getResponseStatus()!=200){
			logger.error("环信删除好友："+object.getResponseStatus()+",userName:"+userNum+",friendNum:"+friendNum);
			throw new RuanYunException("环信删除好友失败");
		}
	}
	
	/**
	 * 往IM用户的黑名单中加人 <br>
	 * POST
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @param payload
	 *            <code>{"usernames":["5cxhactgdj", "mh2kbjyop1"]}</code>
	 * @return
	 */
	public static void addToBlackList(String userNum, String[] friendNums){
		UserNamesBody payload=new UserNamesBody(friendNums);
		ResponseWrapper object=(ResponseWrapper) user.addToBlackList(userNum, payload);
		if(object.getResponseStatus()!=200){
			logger.error("环信加入黑名单："+object.getResponseStatus()+",userName:"+userNum+",friendNums:"+friendNums);
			throw new RuanYunException("环信添加黑名单失败");
		}
	}

	/**
	 * 从IM用户的黑名单中减人 <br>
	 * DELETE
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @param blackListName
	 *            黑名单用戶名或用戶ID
	 * @return
	 */
	public static void deleteFromBlackList(String userNum, String friendNum){
		ResponseWrapper object=(ResponseWrapper) user.removeFromBlackList(userNum, friendNum);
		if(object.getResponseStatus()!=200){
			logger.error("环信删除黑名单："+object.getResponseStatus()+",userName:"+userNum+",friendNum:"+friendNum);
			throw new RuanYunException("环信删除黑名单失败");
		}
	}
	/**
	 * 功能描述: 创建群组并返回群组编号
	 *
	 * @author yangliu  2016年7月27日 上午9:34:22
	 * 
	 * @param userNum 用户编号
	 * @param groupName 群名称
	 * @param otherUserNum 添加的其他组员编号 ["zhangsan","lisi"]
	 * @return
	 */
	public static String addGroup(String userNum,String groupName,String[] otherUserNum){
		ChatGroupBody payload = new ChatGroupBody(groupName, groupName, false, 10000l,true , userNum, otherUserNum);
		ResponseWrapper object=(ResponseWrapper) chatgroup.createChatGroup(payload);
		if(object.getResponseStatus()!=200){
			logger.error("环信删除好友："+object.getResponseStatus()+",userName:"+userNum+",groupName:"+groupName);
			throw new RuanYunException("环信创建群失败");
		}
		JSONObject jsonObject=JSONObject.fromObject(object.getResponseBody().toString());
		JSONObject data=jsonObject.getJSONObject("data");
		return data.getString("groupid");
	}
	
	/**
	 * 功能描述: 修改群信息
	 *
	 * @author yangliu  2016年8月13日 下午5:14:35
	 * 
	 * @param groupNum 群编号
	 * @param groupName 群名称
	 * @return
	 */
	public static void updateGroup(String groupNum,String groupName){
		ModifyChatGroupBody groupBody=new ModifyChatGroupBody(groupName, groupName, 10000l);
		ResponseWrapper object=(ResponseWrapper) chatgroup.modifyChatGroup(groupNum, groupBody);
		if(object.getResponseStatus()!=200){
			logger.error("修改群名片："+object.getResponseStatus()+",groupNum:"+groupNum+",groupName:"+groupName);
			throw new RuanYunException("修改群名片");
		}
	}
	
	/**
	 * 功能描述:环信删除群组
	 *
	 * @author yangliu  2016年7月27日 上午10:12:10
	 * 
	 * @param groupId 群组ID
	 */
	public static void deleteGroup(String groupId){
		ResponseWrapper object=(ResponseWrapper) chatgroup.deleteChatGroup(groupId);
		if(object.getResponseStatus()!=200){
			logger.error("环信删除群组失败："+object.getResponseStatus()+",groupId:"+groupId);
			throw new RuanYunException("环信删除群组失败");
		}
	}
	
	
	//	Object deleteChatGroup(String groupId);
	
	/**
	 * 功能描述: 给群组添加好友[单个]
	 *
	 * @author yangliu  2016年7月27日 上午9:36:55
	 * 
	 * @param groupId
	 * @param userNum
	 */
	public static void addUserToGroup(String userNum,String groupId){
		ResponseWrapper object=(ResponseWrapper) chatgroup.addSingleUserToChatGroup(groupId,userNum);
		if(object.getResponseStatus()!=200){
			logger.error("环信给群组添加好友失败："+object.getResponseStatus()+",userName:"+userNum+",groupId:"+groupId);
			throw new RuanYunException("环信给群组添加好友失败");
		}else{
			logger.info("环信群主添加好友成功！");
		}
	}
	
	/**
	 * 功能描述:给群组添加好友[多个]
	 *
	 * @author yangliu  2016年7月27日 上午9:59:50
	 * 
	 * @param userNum 数组
	 * @param groupId
	 */
	public static void addUserToGroup(String[] userNum,String groupId){
		UserNamesBody userNames=new UserNamesBody(userNum);
		ResponseWrapper object=(ResponseWrapper) chatgroup.addBatchUsersToChatGroup(groupId,userNames);
		if(object.getResponseStatus()!=200){
			logger.error("环信给群组添加好友失败："+object.getResponseStatus()+",userName:"+JSONArray.fromObject(userNames).toString()+",groupId:"+groupId);
			throw new RuanYunException("环信给群组添加好友失败");
		}
	}
	
	/**
	 * 功能描述: 把用户从群组里面删除
	 *
	 * @author yangliu  2016年7月27日 上午10:04:11
	 * 
	 * @param userNum 用户编号
	 * @param groupId 群组ID
	 */
	public static void deleteUserFromGroup(String userNum,String groupId){
		ResponseWrapper object=(ResponseWrapper) chatgroup.removeSingleUserFromChatGroup(groupId,userNum);
		if(object.getResponseStatus()!=200){
			logger.error("环信给群组添加好友失败："+object.getResponseStatus()+",userName:"+userNum+",groupId:"+groupId);
			throw new RuanYunException("环信删除群组成员失败");
		}
	}
	
	/**
	 * 功能描述: 把用户从群组里面删除[多个]
	 *
	 * @author yangliu  2016年7月27日 上午10:04:11
	 * 
	 * @param userNum 用户编号
	 * @param groupId 群组ID
	 */
	public static void deleteUserFromGroup(String[] userNum,String groupId){
		ResponseWrapper object=(ResponseWrapper) chatgroup.removeBatchUsersFromChatGroup(groupId,userNum);
		if(object.getResponseStatus()!=200){
			logger.error("环信给群组添加好友失败："+object.getResponseStatus()+",userName:"+userNum+",groupId:"+groupId);
			throw new RuanYunException("环信删除群组成员批量失败");
		}
	}
	
	/**
	 * 功能描述:给用户发文本消息[单个]
	 *
	 * @author yangliu  2016年7月27日 上午11:20:46
	 * 
	 * @param userNum 发送者
	 * @param msg 文本
	 * @param toUser 用户[单个]
	 */
	public static void sendUserTextMessage(String userNum,String msg,String toUser){
		TextMessageBody msgBody = new TextMessageBody("users", new String[]{toUser}, userNum, null, msg);
		ResponseWrapper object=(ResponseWrapper) message.sendMessage(msgBody);
		if(object.getResponseStatus()!=200){
			logger.error("环信给用户发送消息失败："+object.getResponseStatus()+",userName:"+userNum+",msg:"+msg);
			throw new RuanYunException("环信给用户发送消息失败");
		}
	}
	
	/**
	 * 功能描述:给用户发文本消息[多个]
	 *
	 * @author yangliu  2016年7月27日 上午11:20:46
	 * 
	 * @param userNum 发送者
	 * @param msg 文本
	 * @param toUsers 用户[多个]
	 */
	public static void sendUserTextMessage(String userNum,String msg,String[] toUsers){
		TextMessageBody msgBody = new TextMessageBody("users", toUsers, userNum, null, msg);
		ResponseWrapper object=(ResponseWrapper) message.sendMessage(msgBody);
		if(object.getResponseStatus()!=200){
			logger.error("环信给用户发送消息失败："+object.getResponseStatus()+",userName:"+userNum+",msg:"+msg);
			throw new RuanYunException("环信给用户发送消息失败");
		}
	}
	
	
	/**
	 * 功能描述:给用户发文本消息[单个]
	 *
	 * @author yangliu  2016年7月27日 上午11:20:46
	 * 
	 * @param userNum 发送者
	 * @param msg 文本
	 * @param extMap 扩展字段
	 * @param toUser 用户[单个]
	 */
	public static void sendUserTextMessage(String userNum,String msg,String toUser,Map<String,String> extMap){
		TextMessageBody msgBody = new TextMessageBody("users", new String[]{toUser}, userNum, extMap, msg);
		ResponseWrapper object=(ResponseWrapper) message.sendMessage(msgBody);
		if(object.getResponseStatus()!=200){
			logger.error("环信给用户发送消息失败："+object.getResponseStatus()+",userName:"+userNum+",msg:"+msg);
			throw new RuanYunException("环信给用户发送消息失败");
		}
	}
	
	@Test
	public  void test() {
		String userNum="sp30500000010886";
		String toUser="sp30500000010886";
		String smsType="MJXD";
		Map<String, String> extMap = new HashMap<String, String>();
		if (EmptyUtils.isEmpty(extMap)) {
			extMap = new HashMap<String, String>(); 
		}
		extMap.put("remindType", smsType);
		String msg="推送消息！！";
		sendUserTextMessage(userNum,msg,toUser,extMap);
	}
	
	 
	/**
	 * 功能描述:给用户发文本消息[多个]
	 *
	 * @author yangliu  2016年7月27日 上午11:20:46
	 * 
	 * @param userNum 发送者
	 * @param msg 文本
	 * @param extMap 扩展字段
	 * @param toUsers 用户[多个]
	 */
	public static void sendUserTextMessage(String userNum,String msg,Map<String,String> extMap,String[] toUsers){
		TextMessageBody msgBody = new TextMessageBody("users", toUsers, userNum, extMap, msg);
		ResponseWrapper object=(ResponseWrapper) message.sendMessage(msgBody);
		if(object.getResponseStatus()!=200){
			logger.error("环信给用户发送消息失败："+object.getResponseStatus()+",userName:"+userNum+",msg:"+msg);
			throw new RuanYunException("环信给用户发送消息失败");
		}
	}
	
	/**
	 * 功能描述:给群发文本消息[单个]
	 *
	 * @author yangliu  2016年7月27日 上午11:20:46
	 * 
	 * @param userNum 发送者
	 * @param msg 文本
	 * @param groupId 用户[单个]
	 */
	public static void sendGroupTextMessage(String userNum,String msg,String groupId){
		TextMessageBody msgBody = new TextMessageBody("chatgroups", new String[]{groupId}, userNum, null, msg);
		ResponseWrapper object=(ResponseWrapper) message.sendMessage(msgBody);
		if(object.getResponseStatus()!=200){
			logger.error("环信给用户发送消息失败："+object.getResponseStatus()+",userName:"+userNum+",msg:"+msg);
			throw new RuanYunException("环信给群发送消息失败");
		}
	}
	
	/**
	 * 功能描述:给群发文本消息[多个]
	 *
	 * @author yangliu  2016年7月27日 上午11:20:46
	 * 
	 * @param userNum 发送者
	 * @param msg 文本
	 * @param groupIds 用户[多个]
	 */
	public static void sendGroupTextMessage(String userNum,String msg,String[] groupIds){
		TextMessageBody msgBody = new TextMessageBody("chatgroups", groupIds, userNum, null, msg);
		ResponseWrapper object=(ResponseWrapper) message.sendMessage(msgBody);
		if(object.getResponseStatus()!=200){
			logger.error("环信给群组发送消息失败："+object.getResponseStatus()+",userName:"+userNum+",msg:"+msg);
			throw new RuanYunException("环信给批量群组发送消息失败");
		}
	}
	
	/**
	 * 功能描述:获取消息聊天记录
	 *
	 * @author yangliu  2016年8月10日 上午11:39:18
	 * 
	 * @param timestamp 时间
	 * @return
	 */
	public static JSONArray getChatMessages(long timestamp){
		String sql=String.format("timestamp>%s", timestamp);
		ResponseWrapper object=(ResponseWrapper)chat.exportChatMessages(1000L,"", sql);
		if(object.getResponseStatus()!=200){
			logger.error("环信获取消息失败"+object.getResponseStatus()+",timestamp:"+timestamp);
			throw new RuanYunException("环信获取消息失败");
		}
		JSONArray jsonObject=JSONObject.fromObject(object.getResponseBody().toString()).getJSONArray("entities");
		System.out.println(jsonObject.toString());
		return jsonObject;
	}
	
	/**
	 * 功能描述:下载文件
	 *
	 * @author yangliu  2016年8月10日 上午10:49:57
	 * 
	 * @param fileUUID
	 * @param shareSecret
	 * @param isThumbnail
	 * @return
	 */
	public static EofSensorInputStream loadFile(String fileUUID, String shareSecret, Boolean isThumbnail){
		ResponseWrapper object=(ResponseWrapper)file.downloadFile(fileUUID, shareSecret, isThumbnail);
		if(object.getResponseStatus()!=200){
			logger.error("环信下载文件失败"+object.getResponseStatus()+",fileUUID:"+fileUUID+",shareSecret:"+shareSecret);
			throw new RuanYunException("环信下载文件失败");
		}
		EofSensorInputStream eof=(EofSensorInputStream)object.getResponseBody();
		return eof;
	}
	
	/**
	 * 功能描述:下载文件
	 *
	 * @author yangliu  2016年8月9日 下午8:37:27
	 * 
	 * @param url 文件路径
	 * @return
	 */
	public static UploadVo loadFile(String url, String filePath, String fileName) {
		ResponseWrapper object = (ResponseWrapper) file.downloadFile(url);
		if (object.getResponseStatus() != 200) {
			 logger.error("环信下载文件失败"+object.getResponseStatus()+",url:"+url);
			throw new RuanYunException("环信下载文件失败");
		}
		
		EofSensorInputStream eof = (EofSensorInputStream) object.getResponseBody();
		fileName = System.nanoTime() + fileName;
		 String rootPath=HuanXinUtils.class.getResource("/").getFile().toString(); 
		 rootPath=rootPath.replace("WEB-INF/classes/", "");
		 //rootPath="D://eclipseworkspace//hfryspace//czy//src//main//webapp";
		try {
			File storeFile =new File(rootPath+filePath + fileName);
				OutputStream os;
				System.out.println(storeFile.getPath());
				
			os = new FileOutputStream(storeFile);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = eof.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			eof.close();
		} catch (Exception e) {
			e.printStackTrace();
			//throw new RuanYunException("下载文件失败:url="+url);
		}
		UploadVo vo = new UploadVo();
		vo.setResult(1);
		vo.setFilename(filePath+fileName);
		System.out.println(vo.getFilename());
		return vo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		//一天同城
		HuanXinUtils.sendUserTextMessage("SY00000000000001", "测试", "sys72660000010335");
		
	}
	
	
}
