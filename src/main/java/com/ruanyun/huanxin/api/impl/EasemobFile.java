package com.ruanyun.huanxin.api.impl;

import com.ruanyun.huanxin.api.EasemobRestAPI;
import com.ruanyun.huanxin.api.FileAPI;
import com.ruanyun.huanxin.comm.helper.HeaderHelper;
import com.ruanyun.huanxin.comm.wrapper.HeaderWrapper;

import java.io.File;

public class EasemobFile extends EasemobRestAPI implements FileAPI {
    private static final String ROOT_URI = "/chatfiles";

    @Override
    public String getResourceRootURI() {
        return ROOT_URI;
    }

    public Object uploadFile(Object file) {
        String url = getContext().getSeriveURL() + getResourceRootURI();
        HeaderWrapper header = HeaderHelper.getUploadHeaderWithToken();

        return getInvoker().uploadFile(url, header, (File) file);
    }

    public Object downloadFile(String fileUUID, String shareSecret, Boolean isThumbnail) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + fileUUID;
        HeaderWrapper header = HeaderHelper.getDownloadHeaderWithToken(shareSecret, isThumbnail);

        return getInvoker().downloadFile(url, header, null);
    }

	public Object downloadFile(String url) {
		  return getInvoker().downloadFile(url);
	}
    
    
}
