package com.ruanyun.web.service.mall;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.CommonUtils;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.dao.mall.AdverInfoDao;
import com.ruanyun.web.model.UploadVo;
import com.ruanyun.web.model.mall.TAdverInfo;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.FileUtils;
import com.ruanyun.web.util.NumUtils;
import com.ruanyun.web.util.QiniuUploadCommon;
//广告
@Service("adverInfoService")
public class AdverInfoService extends BaseServiceImpl<TAdverInfo>{
	
	@Autowired
	private AdverInfoDao adverInfoDao;
	
	@Autowired
	private ShopInfoService shopInfoService;
	
	/**
	 * 功能描述:查询
	 * @author wsp  2016-9-7 下午05:31:09
	 * @param page
	 * @param adverInfo
	 * @return
	 */
	public Page<TAdverInfo> getList(Page<TAdverInfo> page,TAdverInfo adverInfo){
		return adverInfoDao.queryPage(page,adverInfo);
	}
	
	/**
	 * 功能描述:修改和添加
	 * @author wsp  2016-9-7 下午05:41:14
	 * @param adverInfo
	 * @param request
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public Integer saveOrUpdate(TAdverInfo adverInfo,HttpServletRequest request,TUser user) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		UploadVo mainphoto =QiniuUploadCommon.uploadPic(multipartRequest.getFile("mainphoto"),Constants.QINIU_COMMONT_BUCKET); //主图
		UploadVo videopath=null;
		if(EmptyUtils.isNotEmpty(multipartRequest.getFile("videopath"))){
			 videopath = QiniuUploadCommon.uploadFile3(multipartRequest.getFile("videopath"), null,Constants.QINIU_COMMONT_BUCKET);
		}
				if (EmptyUtils.isNotEmpty(adverInfo.getAdverInfoId())) {
				TAdverInfo bean = get(TAdverInfo.class, adverInfo.getAdverInfoId());
				BeanUtils.copyProperties(adverInfo, bean, new String[] { "adverInfoId","adverInfoNum","createTime","mainPhoto","userNum","status","adverType"});
				if(EmptyUtils.isNotEmpty(mainphoto) && mainphoto.getResult()==1)
					bean.setMainPhoto(mainphoto.getFilename());
				if (EmptyUtils.isNotEmpty(videopath) && videopath.getResult()==1)
					bean.setVideoPath(videopath.getFilename());
				update(bean);
			} else {
				if(EmptyUtils.isNotEmpty(mainphoto) && mainphoto.getResult()==1)
					adverInfo.setMainPhoto(mainphoto.getFilename());
				if (EmptyUtils.isNotEmpty(videopath) && videopath.getResult()==1)
					adverInfo.setVideoPath(videopath.getFilename());
				
				adverInfo.setCreateTime(new Date());
				adverInfo.setUserNum(user.getUserNum());
				adverInfo.setStatus(1);
				if (EmptyUtils.isEmpty(adverInfo.getSortNum())) {
					adverInfo.setSortNum(getMaxSortNum(adverInfo.getAdverType())+1);
				}
				save(adverInfo);
				
				adverInfo.setAdverInfoNum(NumUtils.getCommondNum(NumUtils.PIX_ADVERINFO, adverInfo.getAdverInfoId()));
				update(adverInfo);
			}
		return 1;
	}
	
	/**
	 * 功能描述:是否显示
	 * @author wsp  2016-9-7 下午07:17:49
	 * @param type
	 * @param ids
	 * @return
	 */
	public int saveIsHomeShow(Integer type, String ids) {
		if (type != 1 & type != 2)return 0;
			String[] id=ids.split(Constants.FILE_COMMA);
			for (String adverId:id) {
				TAdverInfo bean = super.get(TAdverInfo.class, Integer.valueOf(adverId));
					bean.setStatus(type);
				super.update(bean);
			}
			return 1;
	}
	
	/**
	 * 功能描述:获取排序最大值
	 * @author wsp  2016-9-7 下午07:01:14
	 * @return
	 */
	public Integer getMaxSortNum(Integer adverType) {
		return adverInfoDao.getMaxSortNum(adverType);
	}
	/**
	 * 功能描述:获取排序最大值
	 * @author wsp  2016-9-7 下午07:01:14
	 * @return
	 */
	public Integer getMaxSortNum() {
		return adverInfoDao.getMaxSortNum();
	}

	@Override
	public TAdverInfo update(TAdverInfo adverInfo) {
		TAdverInfo tAdverInfo = adverInfoDao.get(TAdverInfo.class,adverInfo.getAdverInfoId());
		tAdverInfo.setAdverContent(adverInfo.getAdverContent());
		return super.update(tAdverInfo);
	}

	public TAdverInfo getAdverInfo(TAdverInfo adverInfo){
		return adverInfoDao.get(TAdverInfo.class,"flag1",adverInfo.getFlag1());
	}
}










