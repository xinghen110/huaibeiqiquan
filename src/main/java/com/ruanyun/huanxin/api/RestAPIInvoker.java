package com.ruanyun.huanxin.api;

import com.ruanyun.huanxin.comm.wrapper.BodyWrapper;
import com.ruanyun.huanxin.comm.wrapper.HeaderWrapper;
import com.ruanyun.huanxin.comm.wrapper.QueryWrapper;
import com.ruanyun.huanxin.comm.wrapper.ResponseWrapper;

import java.io.File;

public interface RestAPIInvoker {
	ResponseWrapper sendRequest(String method, String url, HeaderWrapper header, BodyWrapper body, QueryWrapper query);
	ResponseWrapper uploadFile(String url, HeaderWrapper header, File file);
    ResponseWrapper downloadFile(String url, HeaderWrapper header, QueryWrapper query);
    ResponseWrapper downloadFile(String url);
}
