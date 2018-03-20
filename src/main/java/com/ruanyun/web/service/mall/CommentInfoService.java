package com.ruanyun.web.service.mall;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.CommonUtils;
import com.ruanyun.web.dao.mall.CommentInfoDao;
import com.ruanyun.web.model.mall.TCommentInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyf on 2017/8/4.
 */
@Service("commentInfoService")
public class CommentInfoService extends BaseServiceImpl<TCommentInfo> {

    @Autowired
    private CommentInfoDao commentInfoDao;
    @Autowired
    private UserService userService;

    /**
     * 功能描述：获取所有评论
     * @param page
     * @param commentInfo
     * @return
     */
    public Page<TCommentInfo> getList(Page<TCommentInfo> page, TCommentInfo commentInfo) {
        Page<TCommentInfo> _page=commentInfoDao.querPage(page, commentInfo);

        String userNumss = CommonUtils.getAttributeValue(TCommentInfo.class, _page.getResult(), "userNum");
        Map<String, TUser> userMaps = userService.getUserByUserNums(userNumss);
        CommonUtils.setAttributeValue(TCommentInfo.class, _page.getResult(), userMaps, "userNum", "user");

        return _page;
    }

    /**
     * 功能描述：根据 mealNum 获取从 numStart 的 num 条中间数据
     * @param mealNum
     * @param numStart
     * @param num
     * @return
     */
    public List<TCommentInfo> getSomeListByMealNum(String mealNum, Integer numStart, Integer num) {
        List<TCommentInfo> commentInfoList = commentInfoDao.getSomeListByMealNum(mealNum, numStart, num);

        String userNumss = CommonUtils.getAttributeValue(TCommentInfo.class, commentInfoList, "userNum");
        Map<String, TUser> userMaps = userService.getUserByUserNums(userNumss);
        CommonUtils.setAttributeValue(TCommentInfo.class, commentInfoList, userMaps, "userNum", "user");

        return commentInfoList;
    }

    /**
     * 功能描述：根据 mealInfoNum 获得总共评论数量
     * @param mealInfoNum
     * @return
     */
    public Integer getCountByMealInfoNum(String mealInfoNum) {
        return commentInfoDao.getCountByMealInfoNum(mealInfoNum);
    }

    /**
     * 功能描述：根据 shopNum 获取从 numStart 的 num 条中间数据
     * @param shopNum
     * @param numStart
     * @param num
     * @return
     */
    public List<TCommentInfo> getSomeListByShopNum(String shopNum, Integer numStart, Integer num) {
        List<TCommentInfo> commentInfoList = commentInfoDao.getSomeListByShopNum(shopNum, numStart, num);

        String userNumss = CommonUtils.getAttributeValue(TCommentInfo.class, commentInfoList, "userNum");
        Map<String, TUser> userMaps = userService.getUserByUserNums(userNumss);
        CommonUtils.setAttributeValue(TCommentInfo.class, commentInfoList, userMaps, "userNum", "user");

        return commentInfoList;
    }

    /**
     * 功能描述：根据 shopNum 获得总共评论数量
     * @param shopNum
     * @return
     */
    public Integer getCountByShopNum(String shopNum) {
        return commentInfoDao.getCountByShopNum(shopNum);
    }

    /**
     * 功能描述： 根据 commentId 更改显示状态
     */
    public Integer updateStatus(String commentId, Integer type) {
        String[] comments = commentId.split(",");
        return commentInfoDao.updateStatus(comments, type);
    }

    /**
     * 功能描述： 根据 commentId 更改显示状态
     */
    public Integer deleteComment(String commentId) {
        String[] comments = commentId.split(",");
        return commentInfoDao.deleteComment(comments);
    }

    /**
     * 功能描述：更新星级和内容
     * @param commentNum
     * @param score
     * @param content
     * @return
     */
    public Integer updateScoreAndContent(String commentNum, String score, String content) {
        return commentInfoDao.updateScoreAndContent(commentNum, score, content);
    }

}
