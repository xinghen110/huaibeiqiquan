package com.ruanyun.web.timer;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ruanyun.web.service.daowei.UserMemberService;
import com.ruanyun.web.service.sys.UserService;


/**
 * 
 *  #(c) ruanyun czy <br/>
 *
 *  版本说明: $id:$ <br/>
 *
 *  功能说明:  定时器
 * 
 *  <br/>创建说明: 2016年1月29日 下午9:26:00 yangliu  创建文件<br/>
 * 
 *  修改历史:<br/>
 *
 */
@Component
public  class TaskTimer {

    @Autowired
	private UserService userService;
    
    @Autowired
	private UserMemberService userMemberService;
	
	//每10秒执行一次
//	@Scheduled(cron="0/30 * * * * ?")   
//    public void goodsCrowdTask(){ 
//		String userNum = "";
//		userService.getUserByUserNum(userNum, false);
//		System.out.println("===执行时间==="+new Date());
//    }  
	
	
	//每天凌晨一点执行
	@Scheduled(cron="0 0 1 * * ?")  
    public void updateMember(){ 
		userMemberService.updateMember();
    } 
	
	
}
