package com.ruanyun.web.model.mall;
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

/**
 * 广告信息
 */
@Entity
@Table(name="t_adver_info"
)
public class TAdverInfo  implements java.io.Serializable {


     private Integer adverInfoId;
     private String adverInfoNum;
     private String title;
     private String linkTel;
     private String mainPhoto;
     private String videoPath;
     private Integer status;
     private Integer sortNum;
     private String userNum;
     private Date createTime;
     private Integer position;  //图片位置 1商城首页 2积分兑换首页
     private Integer jumpType;
     private Integer adverType;
     private String flag1;
     private String flag2;
     private String flag3;
     private String shopNum;
     private String adverContent;

     private Date startTime;
     
     private TShopInfo shopInfo;
     
    public TAdverInfo() {
    }
    
    
    

	
    public TAdverInfo(Integer adverInfoId,String adverInfoNum,Integer jumpType, String title, String linkTel,
			String mainPhoto, Integer status, Integer sortNum, String userNum, Date createTime,Integer position) {
		super();
		this.adverInfoId=adverInfoId;
		this.adverInfoNum = adverInfoNum;
		this.jumpType =jumpType;
		this.title = title;
		this.linkTel = linkTel;
		this.mainPhoto = mainPhoto;
		this.status = status;
		this.sortNum = sortNum;
		this.userNum = userNum;
		this.createTime = createTime;
		this.position=position;
	}





	public TAdverInfo(Date createTime) {
        this.createTime = createTime;
    }

    public TAdverInfo(String adverInfoNum, String title, String linkTel, String mainPhoto, Integer status, Integer sortNum, String userNum, Date createTime, Integer position, Integer jumpType, Integer adverType, String flag1, String flag2, String flag3) {
        this.adverInfoNum = adverInfoNum;
        this.title = title;
        this.linkTel = linkTel;
        this.mainPhoto = mainPhoto;
        this.status = status;
        this.sortNum = sortNum;
        this.userNum = userNum;
        this.createTime = createTime;
        this.position = position;
        this.jumpType = jumpType;
        this.adverType = adverType;
        this.flag1 = flag1;
        this.flag2 = flag2;
        this.flag3 = flag3;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="adver_info_id", unique=true, nullable=false)
    public Integer getAdverInfoId() {
        return this.adverInfoId;
    }
    
    public void setAdverInfoId(Integer adverInfoId) {
        this.adverInfoId = adverInfoId;
    }
    
    @Column(name="adver_info_num", length=100)
    public String getAdverInfoNum() {
        return this.adverInfoNum;
    }
    
    public void setAdverInfoNum(String adverInfoNum) {
        this.adverInfoNum = adverInfoNum;
    }
    
    
    @Column(name="title", length=100)
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Column(name="link_tel", length=100)
    public String getLinkTel() {
        return this.linkTel;
    }
    
    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }
    
    @Column(name="main_photo", length=100)
    public String getMainPhoto() {
        return this.mainPhoto;
    }
    
    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }
    
    @Column(name="status")
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    @Column(name="sort_num")
    public Integer getSortNum() {
        return this.sortNum;
    }
    
    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }
    
    @Column(name="user_num", length=100)
    public String getUserNum() {
        return this.userNum;
    }
    
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_time", nullable=false, length=19)
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



	@Column(name="position")
	public Integer getPosition() {
		return position;
	}


	public void setPosition(Integer position) {
		this.position = position;
	}
	

    @Transient
	public TShopInfo getShopInfo() {
		return shopInfo;
	}

	public void setShopInfo(TShopInfo shopInfo) {
		this.shopInfo = shopInfo;
	}
	

	@Column(name="jump_type")
	public Integer getJumpType() {
		return jumpType;
	}

	public void setJumpType(Integer jumpType) {
		this.jumpType = jumpType;
	}


	@Column(name="shop_num")
	public String getShopNum() {
		return shopNum;
	}
	
	public void setShopNum(String shopNum) {
		this.shopNum = shopNum;
	}




	@Column(name="adver_count")
	public String getAdverContent() {
		return adverContent;
	}

	public void setAdverContent(String adverContent) {
		this.adverContent = adverContent;
	}

	@Column(name = "adver_type")
    public Integer getAdverType() {
        return adverType;
    }

    public void setAdverType(Integer adverType) {
        this.adverType = adverType;
    }

    @Column(name = "video_path")
    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
}


