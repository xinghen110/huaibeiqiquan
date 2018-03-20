package com.ruanyun.huanxin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.ruanyun.huanxin.api.*;
import com.ruanyun.huanxin.comm.wrapper.BodyWrapper;
import com.ruanyun.huanxin.comm.ClientContext;
import com.ruanyun.huanxin.comm.EasemobRestAPIFactory;
import com.ruanyun.huanxin.comm.body.IMUserBody;
import com.ruanyun.huanxin.comm.body.IMUsersBody;
import com.ruanyun.huanxin.comm.wrapper.ResponseWrapper;

import net.sf.json.JSONObject;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class Main {
	static EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
	static IMUserAPI user = (IMUserAPI)factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
	static ChatMessageAPI chat = (ChatMessageAPI)factory.newInstance(EasemobRestAPIFactory.MESSAGE_CLASS);
	static FileAPI file = (FileAPI)factory.newInstance(EasemobRestAPIFactory.FILE_CLASS);
	static SendMessageAPI message = (SendMessageAPI)factory.newInstance(EasemobRestAPIFactory.SEND_MESSAGE_CLASS);
	static ChatGroupAPI chatgroup = (ChatGroupAPI)factory.newInstance(EasemobRestAPIFactory.CHATGROUP_CLASS);
	static ChatRoomAPI chatroom = (ChatRoomAPI)factory.newInstance(EasemobRestAPIFactory.CHATROOM_CLASS);

	public static void main(String[] args) throws Exception {
		
		
		

       /* ResponseWrapper fileResponse = (ResponseWrapper) file.uploadFile(new File("d:/logo.png"));
        String uuid = ((ObjectNode) fileResponse.getResponseBody()).get("entities").get(0).get("uuid").asText();
        String shareSecret = ((ObjectNode) fileResponse.getResponseBody()).get("entities").get(0).get("share-secret").asText();
        InputStream in = (InputStream) ((ResponseWrapper) file.downloadFile(uuid, shareSecret, false)).getResponseBody();
        FileOutputStream fos = new FileOutputStream("d:/logo1.png");
        byte[] buffer = new byte[1024];
        int len1 = 0;
        while ((len1 = in.read(buffer)) != -1) {
            fos.write(buffer, 0, len1);
        }
        fos.close();*/

      
		// Create a IM user
		/**/BodyWrapper userBody = new IMUserBody("User102", "123456", "HelloWorld");
		Object obj=user.createNewIMUserSingle(userBody);
		System.out.println(JSONObject.fromObject(obj).toString());
		  /*
		// Create some IM users
		List<IMUserBody> users = new ArrayList<IMUserBody>();
		users.add(new IMUserBody("User002", "123456", null));
		users.add(new IMUserBody("User003", "123456", null));
		BodyWrapper usersBody = new IMUsersBody(users);
		user.createNewIMUserBatch(usersBody);
		
		// Get a IM user
		user.getIMUsersByUserName("User001");
		
		// Get a fake user
		user.getIMUsersByUserName("FakeUser001");
		
		// Get 12 users
		user.getIMUsersBatch(null, null);
		*/
	}

}
