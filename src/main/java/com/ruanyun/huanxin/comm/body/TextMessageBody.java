package com.ruanyun.huanxin.comm.body;

import com.ruanyun.huanxin.comm.constant.MsgType;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Map;

public class TextMessageBody extends MessageBody {
	private String msg;

	public TextMessageBody(String targetType, String[] targets, String from, Map<String, String> ext, String msg) {
		super(targetType, targets, from, ext);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

    public ContainerNode<?> getBody() {
        if(!isInit()){
        	ObjectNode objectNode=getMsgBody();
        	ObjectNode msgNode= JsonNodeFactory.instance.objectNode();
        	msgNode.put("type", MsgType.TEXT);
        	msgNode.put("msg", msg);
        	objectNode.put("msg", msgNode);
            this.setInit(true);
        }

        return this.getMsgBody();
    }

    public Boolean validate() {
		return super.validate() && StringUtils.isNotBlank(msg);
	}
}
