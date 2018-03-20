package com.ruanyun.huanxin.api.impl;

import java.net.URLEncoder;

import com.ruanyun.huanxin.api.ChatMessageAPI;
import com.ruanyun.huanxin.api.EasemobRestAPI;
import com.ruanyun.huanxin.comm.constant.HTTPMethod;
import com.ruanyun.huanxin.comm.helper.HeaderHelper;
import com.ruanyun.huanxin.comm.wrapper.HeaderWrapper;
import com.ruanyun.huanxin.comm.wrapper.QueryWrapper;

public class EasemobChatMessage extends EasemobRestAPI implements ChatMessageAPI {

    private static final String ROOT_URI = "/chatmessages";

    public Object exportChatMessages(Long limit, String cursor, String query) {
        String url = getContext().getSeriveURL() + getResourceRootURI();
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        QueryWrapper queryWrapper = QueryWrapper.newInstance().addLimit(limit).addQueryLang(query);
        return getInvoker().sendRequest(HTTPMethod.METHOD_GET, url, header, null, queryWrapper);
    }

    @Override
    public String getResourceRootURI() {
        return ROOT_URI;
    }
}
