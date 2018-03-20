package com.ruanyun.web.service.daowei;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.dao.daowei.TypeInfoDao;
import com.ruanyun.web.model.UploadVo;
import com.ruanyun.web.model.daowei.TMealInfo;
import com.ruanyun.web.model.daowei.TTypeInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.AttachInfoService;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.NumUtils;
import com.ruanyun.web.util.QiniuUploadCommon;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by wangyf on 2017/8/4.
 */
@Service("typeInfoService")
public class TypeInfoService extends BaseServiceImpl<TTypeInfo> {

    @Autowired
    private TypeInfoDao typeInfoDao;
    @Autowired
    private AttachInfoService attachInfoService;

    /**
     * 功能描述：获取主页模块
     * @return
     */
    public Page<TTypeInfo> getList(Page<TTypeInfo> page, TTypeInfo typeInfo) {
        return typeInfoDao.queryPage(page, typeInfo);
    }


    public Integer saveOrUpdate(TTypeInfo typeInfo, HttpServletRequest request, TUser user) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        System.out.println(multipartRequest.getFile("mainPhoto")+"--------");
        UploadVo mainphoto = QiniuUploadCommon.uploadPic(multipartRequest.getFile("mainPhoto"), Constants.QINIU_COMMONT_BUCKET); //主图

        if (EmptyUtils.isNotEmpty(typeInfo.getTypeId())) {
            TTypeInfo bean = get(TTypeInfo.class, typeInfo.getTypeId());
            BeanUtils.copyProperties(typeInfo, bean, new String[] { "typeId","typeNum","createTime","userNum"});
            if(EmptyUtils.isNotEmpty(mainphoto) && mainphoto.getResult()==1)
                bean.setImgUrl(mainphoto.getFilename());
            bean.setTypeInfoName(typeInfo.getTypeInfoName());
            bean.setSortNum(typeInfo.getSortNum());
            bean.setTypeNumber(typeInfo.getTypeNumber());
            update(bean);
        } else {
            if(EmptyUtils.isNotEmpty(mainphoto) && mainphoto.getResult()==1)
                typeInfo.setImgUrl(mainphoto.getFilename());
            typeInfo.setCreateTime(new Date());
            typeInfo.setSortNum(getMaxSortNum()+1);
            typeInfo.setUserNum(user.getUserNum());
            typeInfo.setTypeNumber(0);
            typeInfo.setTypeNumber(typeInfo.getTypeNumber());
            save(typeInfo);
            typeInfo.setTypeNum(NumUtils.getCommondNum(NumUtils.PIX_TYPE_INFO, typeInfo.getTypeId()));
            update(typeInfo);
        }
        return 1;
    }

    public Integer getMaxSortNum() {
        return typeInfoDao.getMaxSortNum();
    }
    
    /**
     * 功能描述:获取类别
     * @author cqm  2017-8-8 下午03:14:39
     * @return
     */
	public List<TTypeInfo> getTypeList(){
		return typeInfoDao.getTypeList();
	}
	
	/**
	 * 功能描述:获取类型
	 * @author cqm  2017-8-17 下午05:08:40
	 * @param typeNum
	 * @param isRequired
	 * @return
	 */
    public TTypeInfo getTypeInfo(String typeNum,boolean isRequired) {
    	TTypeInfo typeInfo = get(TTypeInfo.class, "typeNum", typeNum);
        if(isRequired && typeInfo==null){
        	throw new RuanYunException("该类型不存在");
        }
        return typeInfo;
    }

    /**
     * 功能描述：根据typeNums（里面含有“,”分开），获取typeNames（“,”分开）
     * @param typeNums
     * @return
     */
    public String getTypeInfoNames(String typeNums, List<TTypeInfo> typeInfos) {
        if (EmptyUtils.isNotEmpty(typeNums)) {
            String[] typeNum = typeNums.split(",");
            String typeNames = "";
            for (int i = 0; i < typeNum.length; i++) {
                for (int j = 0; j < typeInfos.size(); j++) {
                    if (typeNum[i].equals(typeInfos.get(j).getTypeNum())) {
                        typeNames += typeInfos.get(j).getTypeInfoName();
                        break;
                    }
                }
                if (i < typeNum.length - 1) {
                    typeNames += ",";
                }
            }
            return typeNames;
        } else {
            return null;
        }
    }

    /**
     * 功能描述：获取非至尊会员的记录
     * @param typeId
     * @return
     */
    public List<TTypeInfo> getTypes(Integer typeId) {
        return typeInfoDao.getTypes(typeId);
    }
}
