package com.ruanyun.web.service.mall;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.model.mall.TGoodsInfo;
import com.ruanyun.web.model.mall.TVersionUpdate;
import com.ruanyun.web.model.sys.TAttachInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.FileUtils;

@Service("versionUpdateService")
public class VersionUpdateService extends BaseServiceImpl<TVersionUpdate> {
	@Autowired
	private AttachInfoService attachInfoService;
	/**
	 * 功能描述:1.46.2获取版本更新信息
	 * @author cqm  2017-4-5 下午01:46:57
	 * @param type 手机类型1：安卓2：苹果
	 * @param isRequired
	 * @return
	 */
	public TVersionUpdate getVersionUpdate(Integer type,boolean isRequired){
		TVersionUpdate bean = get(TVersionUpdate.class, "type", type);
		if(isRequired && bean==null){
			throw new RuanYunException("版本信息不存在");
		}
		return bean;
	}
	/**
	 * 
	 * Zjb 2017 下午05:54:15
	 * @param bean
	 * @param request
	 * @param type
	 * @param num
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Integer saveOrUpdate(TVersionUpdate bean,HttpServletRequest request,Integer type,Integer num,TUser user ) throws Exception{
		if(EmptyUtils.isNotEmpty(type)){
			TVersionUpdate versionUpdate =get(TVersionUpdate.class, "type", type);
			if(EmptyUtils.isNotEmpty(num)){
			if(num==1){
				BeanUtils.copyProperties(bean, versionUpdate, new String[] { "versionUpdateId","versionUpdateNum","url","shopUrl","shopCode","wuliuUrl","wuliuCode"});
			}
			if(num==2){
				BeanUtils.copyProperties(bean, versionUpdate, new String[] { "versionUpdateId","versionUpdateNum","url","versionCode","shopUrl","wuliuUrl","wuliuCode"});
			}
			if(num==3){
				BeanUtils.copyProperties(bean, versionUpdate, new String[] { "versionUpdateId","versionUpdateNum","url","versionCode","shopUrl","shopCode","wuliuUrl"});
			}
			}else{
				BeanUtils.copyProperties(bean, versionUpdate, new String[] { "versionUpdateId","versionUpdateNum"});
			}
			
			update(versionUpdate);
			bean=versionUpdate;
		}
		if(EmptyUtils.isNotEmpty(num)){
		List<TAttachInfo> pics = attachInfoService.saveAttachInfo(user.getUserNum(), Constants.ATTACH_INFO_ATTACHTYPE_11, bean.getVersionUpdateNum(), request,"fileName", Constants.FILE_VERSIONUPDATE, FileUtils.FILE_APK);
		if(EmptyUtils.isNotEmpty(pics)){
		TAttachInfo attach=pics.get(0);
		if(num==1){
			bean.setUrl(attach.getFilePath());
		}if(num==2){
			bean.setShopUrl(attach.getFilePath());
		}if(num==3){
			bean.setWuliuUrl(attach.getFilePath());
		}
		update(bean);
		}
		}
		
		
		
		return 1;
	}
	
	

}
