package com.ruanyun.web.model.daowei;
// Generated 2017-8-5 11:27:20 by Hibernate Tools 3.2.2.GA


import java.util.Date;
import javax.persistence.*;

import com.ruanyun.web.model.sys.TUser;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 意见反馈
 */
@Entity
@Table(name="t_feed_back"
)
public class TFeedBack  implements java.io.Serializable {


     private Integer feedBackId;
     private String feedBackNum;
     private String userNum;
     private String linkTel;
     private String content;
     private Date createTime;
     private String flag1;
     private String flag2;
     private String flag3;
     private TUser user;

     public Date startTime;

    public TFeedBack() {
    }

    public TFeedBack(String feedBackNum, String userNum, String linkTel, String content, Date createTime, String flag1, String flag2, String flag3) {
       this.feedBackNum = feedBackNum;
       this.userNum = userNum;
       this.linkTel = linkTel;
       this.content = content;
       this.createTime = createTime;
       this.flag1 = flag1;
       this.flag2 = flag2;
       this.flag3 = flag3;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="feed_back_id", unique=true, nullable=false)
    public Integer getFeedBackId() {
        return this.feedBackId;
    }
    
    public void setFeedBackId(Integer feedBackId) {
        this.feedBackId = feedBackId;
    }
    
    @Column(name="feed_back_num", length=100)
    public String getFeedBackNum() {
        return this.feedBackNum;
    }
    
    public void setFeedBackNum(String feedBackNum) {
        this.feedBackNum = feedBackNum;
    }
    
    @Column(name="user_num", length=100)
    public String getUserNum() {
        return this.userNum;
    }
    
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
    
    @Column(name="link_tel", length=100)
    public String getLinkTel() {
        return this.linkTel;
    }
    
    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }
    
    @Column(name="content", length=500)
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_time", length=19)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="flag1", length=100)
    public String getFlag1() {
        return this.flag1;
    }
    
    public void setFlag1(String flag1) {
        this.flag1 = flag1;
    }
    
    @Column(name="flag2", length=100)
    public String getFlag2() {
        return this.flag2;
    }
    
    public void setFlag2(String flag2) {
        this.flag2 = flag2;
    }
    
    @Column(name="flag3", length=100)
    public String getFlag3() {
        return this.flag3;
    }
    
    public void setFlag3(String flag3) {
        this.flag3 = flag3;
    }

    @Transient
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}


