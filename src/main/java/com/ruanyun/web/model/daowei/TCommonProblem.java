package com.ruanyun.web.model.daowei;
// Generated 2016-11-5 10:37:28 by Hibernate Tools 3.2.2.GA


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.ruanyun.web.model.sys.TUser;

/**
 * 常见问题
 */
@Entity
@Table(name="t_common_problem"
)
public class TCommonProblem  implements java.io.Serializable {


     private Integer commonProblemId;
     private String commonProblemNum;
     private String title;
     private Integer problemType;
     private String content;
     private Date createTime;
     private String userNum;
     private String flag1;
     private String flag2;
     private String flag3;
     
     private TUser user;

    public TCommonProblem() {
    }

	
    public TCommonProblem(Date createTime) {
        this.createTime = createTime;
    }
    public TCommonProblem(String commonProblemNum, Integer problemType, String content, Date createTime, String userNum, String flag1, String flag2, String flag3) {
       this.commonProblemNum = commonProblemNum;
       this.problemType = problemType;
       this.content = content;
       this.createTime = createTime;
       this.userNum = userNum;
       this.flag1 = flag1;
       this.flag2 = flag2;
       this.flag3 = flag3;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="common_problem_id", unique=true, nullable=false)
    public Integer getCommonProblemId() {
        return this.commonProblemId;
    }
    
    public void setCommonProblemId(Integer commonProblemId) {
        this.commonProblemId = commonProblemId;
    }
    
    @Column(name="common_problem_num", length=100)
    public String getCommonProblemNum() {
        return this.commonProblemNum;
    }
    
    public void setCommonProblemNum(String commonProblemNum) {
        this.commonProblemNum = commonProblemNum;
    }
    
    @Column(name="problem_type")
    public Integer getProblemType() {
        return this.problemType;
    }
    
    public void setProblemType(Integer problemType) {
        this.problemType = problemType;
    }
    @Column(name="title", length=200)
    public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	@Column(name="content", length=65535)
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_time", nullable=false, length=19)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="user_num", length=100)
    public String getUserNum() {
        return this.userNum;
    }
    
    public void setUserNum(String userNum) {
        this.userNum = userNum;
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
	public TUser getUser() {
		return user;
	}


	public void setUser(TUser user) {
		this.user = user;
	}

    



}


