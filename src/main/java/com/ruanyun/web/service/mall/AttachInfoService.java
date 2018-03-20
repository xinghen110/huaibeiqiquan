package com.ruanyun.web.service.mall;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.dao.mall.AttachInfoDao;
import com.ruanyun.web.model.UploadVo;
import com.ruanyun.web.model.sys.TAttachInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.util.QiniuUploadCommon;

@Service("attachInfoService")
public class AttachInfoService extends BaseServiceImpl<TAttachInfo>{
	//附件表
	@Autowired
	private AttachInfoDao attachInfoDao;
	
	
	/**
	 * 功能描述:根据关联获取所有附件列表
	 * @author wsp  2016-10-9 下午03:41:46
	 * @param attachType
	 * @param glIds
	 * @return
	 */
	public List<TAttachInfo> getList(String attachType,String glIds){
		return attachInfoDao.getList(attachType, glIds);
	}
	
	/**
	 * 功能描述:根据关联获取所有附件列表 并已map方式--
	 *
	 * @author yangliu  2016年9月3日 上午10:12:43
	 * 
	 * @param attachType
	 * @param glIds
	 * @return
	 */
	public Map<String,List<TAttachInfo>> getMap(String attachType,String glIds){
		Map<String,List<TAttachInfo>> map = new HashMap<String, List<TAttachInfo>>();
		List<TAttachInfo> list =getList(attachType, glIds);
		if(EmptyUtils.isNotEmpty(list)){
			Iterator<TAttachInfo> it=list.iterator();
			List<TAttachInfo> childList=null;
			TAttachInfo info=null;
			while(it.hasNext()){
				info=it.next();
				childList=map.get(info.getGlNum());
				if(EmptyUtils.isEmpty(childList)){
					childList=new ArrayList<TAttachInfo>();
					map.put(info.getGlNum(), childList);
				}
				childList.add(info);
			}
		}
		return map;
	}

	/**
	 * 功能描述：根据关联获取附件获取数量
	 * @param attachType
	 * @param glNum
	 * @return
	 */
	public Integer getCount(String attachType,String glNum) {
		return attachInfoDao.getCount(attachType, glNum);
	}
	
	/**
	 * 功能描述:上传附件 并保存记录
	 *
	 * @author yangliu  2016年9月3日 下午4:44:25
	 * 
	 * @param userNum 用户编号
	 * @param attachType 
	 * @param glNum 关联主键编号
	 * @param request 
	 * @param fileName 上传文件流参数 名称
	 * @param filePath 上传路径
	 * @param fileType 附件验证类型
	 * @return
	 * @throws Exception 
	 */
	public List<TAttachInfo> saveAttachInfos(String userNum,String attachType,String glNum,HttpServletRequest request,String fileName,String filePath,String fileType) throws Exception{
		List<UploadVo> uploadVos=QiniuUploadCommon.uploadPics2(request, fileName, filePath, fileType); //上传图片
		
		List<TAttachInfo> list = new ArrayList<TAttachInfo>();
		TAttachInfo info =null;
		Date  now= new Date();
		if(EmptyUtils.isNotEmpty(uploadVos)){
			for(UploadVo vo : uploadVos){
				if(EmptyUtils.isEmpty(vo.getFileOldName()))continue;
				info=new TAttachInfo(vo.getFilename(), vo.getFileOldName(), vo.getFileSize(), userNum,now, attachType, glNum);
				save(info);
				list.add(info);
			}
		}
		return list;
	}
	
	public List<TAttachInfo> saveAttachInfos(String userNum,String attachType,String glNum,HttpServletRequest request,String fileName,String filePath,String fileType,String delAttrachinfoIds) throws Exception{
		delAttachInfo(delAttrachinfoIds,attachType,glNum);
		return saveAttachInfos(userNum, attachType, glNum, request, fileName, filePath, fileType);
	}
	
	/**
	 * 功能描述: 删除附件
	 *
	 * @author yangliu  2016年9月3日 下午7:17:12
	 * 
	 * @param attachIds 附件ID
	 * @param attachType 类型 【与glNum 组合使用】 //
	 * @param glNum 关联主键
	 * @return
	 */
	public int delAttachInfo(String attachIds,String attachType,String glNum){
		if(EmptyUtils.isEmpty(attachIds))
			return 1;
		return attachInfoDao.delAttachInfo(attachIds, attachType, glNum);
	}
	
	/**
	 * 功能描述:获取一组图片
	 * @author wsp  2016-9-8 下午04:33:18
	 * @param attachType 
	 * @param glNum 关联表 num
	 * @return
	 */
	public List<TAttachInfo> getAttachInfoList(String attachType,String glNum){
		return super.getAllByCondition(TAttachInfo.class,new String[]{"attachType","glNum"}, new Object[]{attachType,glNum});
	}
	
	/**
	 * 功能描述:根据用户编号获取用户信息 返回值为map key为glNum value 为 tAttachInfo对象
	 * @author wsp  2016-10-20 下午08:23:05
	 * @param attachType
	 * @param glNums
	 * @return
	 */
	public Map<String, TAttachInfo> getAttachInfoByGlNums(String attachType,String glNums){
		return attachInfoDao.getAttachInfoByGlNums(attachType, glNums);
	}
	
	
	public List<TAttachInfo> getList(String glNum){
		return attachInfoDao.getList(glNum);
	}
	
	public List<TAttachInfo> getGoodsList(String glNum){
		return attachInfoDao.getGoodsList(glNum);
	}
	
	/**
	 * 上传文件文件
	 * Zjb 2017 上午09:23:40
	 * @param userNum
	 * @param attachType
	 * @param glNum
	 * @param request
	 * @param fileName
	 * @param filePath
	 * @param fileType
	 * @return
	 * @throws Exception
	 */
	public List<TAttachInfo> saveAttachInfo(String userNum,String attachType,String glNum,HttpServletRequest request,String fileName,String filePath,String fileType) throws Exception{
		List<UploadVo> uploadVos=QiniuUploadCommon.uploadPics3(request, fileName, filePath, fileType); //上传图片
		
		List<TAttachInfo> list = new ArrayList<TAttachInfo>();
		TAttachInfo info =null;
		Date  now= new Date();
		if(EmptyUtils.isNotEmpty(uploadVos)){
			for(UploadVo vo : uploadVos){
				if(EmptyUtils.isEmpty(vo.getFileOldName()))continue;
				info=new TAttachInfo(vo.getFilename(), vo.getFileOldName(), vo.getFileSize(), userNum,now, attachType, glNum);
				save(info);
				list.add(info);
			}
		}
		return list;
	}
			
}










