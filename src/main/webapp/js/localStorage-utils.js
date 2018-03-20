utils = {
    setParam : function (name,value){
        localStorage.setItem(name,value);
    },
    getParam : function(name){
    	var val = localStorage.getItem(name);
        return val==null ? '': val;
    },
    clearAll: function(){
    	localStorage.clear();
    },
    clearParam : function(name){
    	localStorage.removeItem(name);
    },
    getUser : function(){
    	var userlogininfo = localStorage.getItem('login-user-info');
    	if(userlogininfo==null||userlogininfo=="" || userlogininfo =='null'){
    		return null;
    	}
    	//userlogininfo = userlogininfo.substring(1,userlogininfo.length-1);
    	return JSON.parse(userlogininfo);
    }
}

product={
	goodsId:"",
	goodsName:"",
	picture:"",
	goodsCnt:1,
	price:0.00,
	oldPrice:0.00,
	sellType:""
};
orderdetail={
    username:"",
    phone:"",
    address:"",
    zipcode:"",
    totalNumber:0,
    totalAmount:0.00,
    totalOldAmount:0.00
};
cart = {
    //向购物车中添加商品
    addproduct:function(product){
	
        var ShoppingCart = utils.getParam("ShoppingCart");
        product.price = parseFloat(product.price)==0 ? product.oldPrice : product.price;
        if(ShoppingCart==null||ShoppingCart=="" || ShoppingCart =='null'){
			//第一次加入商品
            var jsonstr = {"productlist":[{"goodsId":product.goodsId,"goodsName":product.goodsName,"picture":product.picture,"goodsCnt":product.goodsCnt,"price":product.price,"oldPrice":product.oldPrice,"sellType":product.sellType}],"totalNumber":product.goodsCnt,"totalAmount":(product.price*product.goodsCnt),"totalOldAmount":(product.oldPrice*product.goodsCnt)};
            orderdetail.totalNumber = parseInt(product.goodsCnt);
            orderdetail.totalAmount = parseInt(product.goodsCnt)*parseFloat(product.price);
            orderdetail.totalOldAmount = parseInt(product.goodsCnt)*parseFloat(product.oldPrice);
            utils.setParam("ShoppingCart","'"+JSON.stringify(jsonstr));
        }else{
            var jsonstr = JSON.parse(ShoppingCart.substr(1,ShoppingCart.length));
            var productlist = jsonstr.productlist;
            var result=false;
			//查找购物车中是否有该商品
            for(var i in productlist){
                if(productlist[i].goodsId==product.goodsId){
                    productlist[i].goodsCnt=parseInt(productlist[i].goodsCnt)+parseInt(product.goodsCnt);
                    result = true;
                }
            }
            if(!result){
				//没有该商品就直接加进去
                productlist.push({"goodsId":product.goodsId,"goodsName":product.goodsName,"picture":product.picture,"goodsCnt":product.goodsCnt,"price":parseFloat(product.price)==0 ? product.oldPrice:product.price,"oldPrice":product.oldPrice,"sellType":product.sellType});
            }
			//重新计算总价
            jsonstr.totalNumber=parseInt(jsonstr.totalNumber)+parseInt(product.goodsCnt);
            jsonstr.totalAmount=parseFloat(jsonstr.totalAmount)+(parseInt(product.goodsCnt)*parseFloat(product.price));
            jsonstr.totalOldAmount=parseFloat(jsonstr.totalOldAmount)+(parseInt(product.goodsCnt)*parseFloat(product.oldPrice));
            orderdetail.totalNumber = jsonstr.totalNumber;
            orderdetail.totalAmount = jsonstr.totalAmount;
            orderdetail.totalOldAmount = jsonstr.totalOldAmount;
            //保存购物车
            utils.setParam("ShoppingCart","'"+JSON.stringify(jsonstr));
        }
    },
    //修改给买商品数量
    updateproductnum:function(goodsId,goodsCnt){
        var ShoppingCart = utils.getParam("ShoppingCart");
        var jsonstr = JSON.parse(ShoppingCart.substr(1,ShoppingCart.length));
        var productlist = jsonstr.productlist;
        
        for(var i in productlist){
            if(productlist[i].goodsId==goodsId){
                jsonstr.totalNumber=parseInt(jsonstr.totalNumber)+parseInt(goodsCnt);
                jsonstr.totalAmount=parseFloat(jsonstr.totalAmount)+(parseInt(goodsCnt)*parseFloat(productlist[i].price));
                jsonstr.totalOldAmount=parseFloat(jsonstr.totalOldAmount)+(parseInt(goodsCnt)*parseFloat(productlist[i].oldPrice));
                //alert(jsonstr.totalAmount);
                productlist[i].goodsCnt=parseInt(productlist[i].goodsCnt) + parseInt(goodsCnt);
                
                orderdetail.totalNumber = jsonstr.totalNumber;
                orderdetail.totalAmount = jsonstr.totalAmount;
                orderdetail.totalOldAmount = jsonstr.totalOldAmount;
                utils.setParam("ShoppingCart","'"+JSON.stringify(jsonstr));
                return;
            }
        }
    },
    //获取购物车中的所有商品
    getproductlist:function(){
        var ShoppingCart = utils.getParam("ShoppingCart");
        
        if(ShoppingCart == null || ShoppingCart == ''){
        	return null;
        }
        var jsonstr = JSON.parse(ShoppingCart.substr(1,ShoppingCart.length));
        var productlist = jsonstr.productlist;
        orderdetail.totalNumber = jsonstr.totalNumber;
        orderdetail.totalAmount = jsonstr.totalAmount;
        orderdetail.totalOldAmount = jsonstr.totalOldAmount;
        return productlist;
    },
    //判断购物车中是否存在商品
    existproduct:function(goodsId){
    	var result=false;
        var ShoppingCart = utils.getParam("ShoppingCart");
        if(ShoppingCart == null || ShoppingCart == ''){
        	return result;
        }
        var jsonstr = JSON.parse(ShoppingCart.substr(1,ShoppingCart.length));
        var productlist = jsonstr.productlist;
        for(var i in productlist){
            if(productlist[i].goodsId==goodsId){
                result = true;
            }
        }
        return result;
    },
    //删除购物车中商品
    deleteproduct:function(goodsId){
        var ShoppingCart = utils.getParam("ShoppingCart");
        var jsonstr = JSON.parse(ShoppingCart.substr(1,ShoppingCart.length));
        var productlist = jsonstr.productlist;
        var list=[];
        for(var i in productlist){
            if(productlist[i].goodsId==goodsId){
                jsonstr.totalNumber=parseInt(jsonstr.totalNumber)-parseInt(productlist[i].goodsCnt);
                jsonstr.totalAmount=parseFloat(jsonstr.totalAmount)-parseInt(productlist[i].goodsCnt)*parseFloat(productlist[i].price);
                jsonstr.totalOldAmount=parseFloat(jsonstr.totalOldAmount)-parseInt(productlist[i].goodsCnt)*parseFloat(productlist[i].oldPrice);
            }else{
                list.push(productlist[i]);
            }
        }
        jsonstr.productlist = list;
        orderdetail.totalNumber = jsonstr.totalNumber;
        orderdetail.totalAmount = jsonstr.totalAmount;
        orderdetail.totalOldAmount = jsonstr.totalOldAmount;
        utils.setParam("ShoppingCart","'"+JSON.stringify(jsonstr));
    },
    clear:function(){
    	utils.clearParam("ShoppingCart");
    	orderdetail.totalNumber = 0;
        orderdetail.totalAmount = 0.00;
        orderdetail.totalOldAmount = 0.00;
    }
};
search = {
	addKey:function(keyword){
		var key = [];
		var searchKey = utils.getParam("searchKey");
	    if(searchKey==null||searchKey=="" || searchKey =='null'){
	    	key[0]= keyword;
		}else{
			key = JSON.parse(searchKey);
			var check = false;
			for(var i=0;i<key.length;i++){
				var k = key[i];
				if(keyword == k){
					check = true;
				}
			}
			if(!check){
				key[key.length]=keyword;
			}
		}
	    utils.setParam("searchKey",JSON.stringify(key));
	},
	getKeys:function(){
		var searchKey = utils.getParam("searchKey");
		if(searchKey==null||searchKey=="" || searchKey =='null'){
			return null
		}
		//alert(searchKey)
		return JSON.parse(searchKey);
	},
	clear:function(){
    	utils.clearParam("searchKey");
    }
};

pres = {
		//drugNum,drugName,drugBrandName,drugGz,drugBz,drugYfyl,prescriptionNum,drugCount,useMethod,workOrderNum
	add:function(drugNum,drugName,drugBrandName,drugGz,drugBz,drugYfyl,drugCount,prescriptionNum,workOrderNum){
		var PresCartStr = utils.getParam("PresCart");
		var preslist = [];
		if(PresCartStr != ''){
			preslist = JSON.parse(PresCartStr);
		}
		var isture=true;
		var iscount=0;
		if(preslist != null){
			for ( var int = 0; int < preslist.length; int++) {
				var item = preslist[int];
				if(item.drugNum == drugNum){
					isture = false;
					iscount = item.drugCount;
				}
			}
		}
		if(isture){
			var drug = {
					prescriptionNum:prescriptionNum,
					drugNum:drugNum,
					drugName:drugName,
					drugBrandName:drugBrandName,
					drugGz:drugGz,
					drugBz:drugBz,
					drugCount:drugCount,
					useMethod:drugYfyl,
					workOrderNum:workOrderNum
				};
			preslist.push(drug);
			utils.setParam("PresCart",JSON.stringify(preslist));
		}else{
			var newlist = [];
			if(preslist != null){
				for ( var i = 0; i < preslist.length; i++) {
					var item = preslist[i];
					if(item.drugNum != drugNum){
						newlist.push(item);
					}
				}
			}
			var drug = {
					prescriptionNum:prescriptionNum,
					drugNum:drugNum,
					drugName:drugName,
					drugBrandName:drugBrandName,
					drugGz:drugGz,
					drugBz:drugBz,
					drugCount:parseInt(drugCount)+parseInt(iscount),
					useMethod:drugYfyl,
					workOrderNum:workOrderNum
				};
			newlist.push(drug); //重新添加
			utils.setParam("PresCart",JSON.stringify(newlist));
		}
	},
	init:function(list){
		utils.setParam("PresCart",JSON.stringify(list));
	},
	list:function(){
		var PresCartStr = utils.getParam("PresCart");
		if(PresCartStr == ''){
			return null;
		}
		return JSON.parse(PresCartStr);
	},
	clear:function(){
		utils.clearParam("PresCart");
	},
	del:function(drugNum){
		var preslist = JSON.parse(utils.getParam("PresCart"));
		var newlist = [];
		if(preslist != null){
			for ( var i = 0; i < preslist.length; i++) {
				var item = preslist[i];
				if(item.drugNum != drugNum){
					newlist.push(item);
				}
			}
			utils.setParam("PresCart",JSON.stringify(newlist));
		}
	}
};