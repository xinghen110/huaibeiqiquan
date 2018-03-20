package com.ruanyun.huanxin.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruanyun.huanxin.api.AuthTokenAPI;
import com.ruanyun.huanxin.api.EasemobRestAPI;
import com.ruanyun.huanxin.comm.wrapper.BodyWrapper;
import com.ruanyun.huanxin.comm.constant.HTTPMethod;
import com.ruanyun.huanxin.comm.helper.HeaderHelper;
import com.ruanyun.huanxin.comm.wrapper.HeaderWrapper;
import com.ruanyun.huanxin.comm.body.AuthTokenBody;

public class EasemobAuthToken extends EasemobRestAPI implements AuthTokenAPI{
	
	public static final String ROOT_URI = "/token";
	
	private static final Logger log = LoggerFactory.getLogger(EasemobAuthToken.class);
	
	@Override
	public String getResourceRootURI() {
		return ROOT_URI;
	}

	public Object getAuthToken(String clientId, String clientSecret) {
		String url = getContext().getSeriveURL() + getResourceRootURI();
		BodyWrapper body = new AuthTokenBody(clientId, clientSecret);
		HeaderWrapper header = HeaderHelper.getDefaultHeader();
		
		return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, body, null);
	}
}
