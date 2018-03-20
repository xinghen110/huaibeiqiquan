package com.ruanyun.web.controller.sys;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.mall.TGoodsInfo;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.GoodsInfoService;
import com.ruanyun.web.service.mall.OrderInfoService;
import com.ruanyun.web.service.mall.ShopInfoService;
import com.ruanyun.web.util.HttpSessionUtils;

@Controller
public class IndexController extends BaseController {
	
	//2017年 8月3日开工
	
	@Autowired
	@Qualifier("shopInfoService")
	ShopInfoService shopInfoService;
	
	@Autowired
	@Qualifier("goodsInfoService")
	GoodsInfoService goodsInfoService;
	
	@Autowired
	@Qualifier("orderInfoService")
	OrderInfoService orderInfoService;//订单
	
	@RequestMapping("/index")
	public String index(Model model,HttpSession session){
		
		return "pc/index";
	}
	
	
	

}
