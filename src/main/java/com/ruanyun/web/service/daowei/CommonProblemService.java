package com.ruanyun.web.service.daowei;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.dao.daowei.CommonProblemDao;
import com.ruanyun.web.model.daowei.TCommonProblem;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.util.NumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangyf on 2017/8/25.
 */
@Service("commonProblemService")
public class CommonProblemService extends BaseServiceImpl<TCommonProblem> {
    @Autowired
    private CommonProblemDao commonProblemDao;

    /**
     * 功能描述：获取所有协议
     * @param page
     * @param commonProblem
     * @return
     */
    public Page<TCommonProblem> getList(Page<TCommonProblem> page, TCommonProblem commonProblem) {
        Page<TCommonProblem> _page=commonProblemDao.querPage(page, commonProblem);
        return _page;
    }

    /**
     * 功能描述:常见问题的修改和添加
     */
    public Integer saveOrUpdate(TCommonProblem bean,HttpServletRequest request,TUser user) {
        System.out.println(bean.getCommonProblemNum());
        if (EmptyUtils.isNotEmpty(bean.getCommonProblemNum())) {

            TCommonProblem commonProblem = get(TCommonProblem.class,"commonProblemNum", bean.getCommonProblemNum());
            BeanUtils.copyProperties(bean, commonProblem, new String[]{"commonProblemId","commonProblemNum","title","problemType","createTime","userNum"});
            update(commonProblem);

        } else {
            bean.setUserNum(user.getUserNum());
            save(bean);
            bean.setCommonProblemNum(NumUtils.getCommondNum(NumUtils.PIX_COMMON_PROBLEM, bean.getCommonProblemId()));
            update(bean);
        }
        return 1;
    }

}
