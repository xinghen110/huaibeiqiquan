<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />  
<title>百度地图</title>  
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.3"></script>  
</head>  
<body>  
<body style="background:#EEEEEE;">
    <div>
    	<label style="margin-left:31px;">${bean.address}</label>  
		要查询的地址：
        <input id="text_" type="text" value="" style="margin-right:50px;"/>
		<input type="button" value="查询" onclick="searchByStationName();"/>
		查询结果：
        <input readonly="readonly" id="result_" type="text" style="width: 250px;"/>
        <label id="point_"></label>
        <input type="button" value="添加" onclick="addResult();"/>
        <div id="container" 
            style="
                margin-top:30px; 
				margin-left:30px; 
                width: 1260px; 
                height: 690px; 
                top: 50; 
                border: 1px solid gray;
                overflow:hidden;">
        </div>
    </div>
</body>
<script type="text/javascript">
    var map = new BMap.Map("container");
    var address = "${bean.address}";
    map.centerAndZoom(address,14);//传进来地址首次加载
    map.clearOverlays();
    marker = new BMap.Marker(new BMap.Point("${longitude}","${latitude}"));  // 创建标注
    map.addOverlay(marker);
    map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
    map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用

    map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
    map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
    map.addControl(new BMap.OverviewMapControl({ isOpen: true, anchor: BMAP_ANCHOR_TOP_LEFT}));   //右下角，打开

    var localSearch = new BMap.LocalSearch(map);
    localSearch.enableAutoViewport(); //允许自动调节窗体大小

    var gc = new BMap.Geocoder();  
    map.addEventListener("click", function(e){        
		gc.getLocation(e.point, function(rs){  
			showLocationInfo(e.point, rs);  
		});      
	});
function searchByStationName() {
	var placeName=document.getElementById("text_").value;
    if(placeName!='' || placeName!=null){
		try{
	    map.clearOverlays();//清空原来的标注
	    var keyword = document.getElementById("text_").value;
	    localSearch.setSearchCompleteCallback(function (searchResult) {
	        var poi = searchResult.getPoi(0);
			if(!poi){
				alert("未找到地址");
				return ;
			}
	        gc.getLocation(poi.point, function(rs){  
				showLocationInfo(poi.point, rs);  
			});
			
	        map.centerAndZoom(poi.point, 13);
	        var point = new BMap.Point(poi.point.lng, poi.point.lat);
	        addMarker(point);
	    });
	    localSearch.search(keyword);
		}catch(e){alert(e.message);}
    }
} 
function addMarker(point){
	var marker = new BMap.Marker(point);  // 创建标注，为要查询的地方对应的经纬度
	marker.enableDragging(); //标记开启拖拽  
	//添加标记拖拽监听  
	marker.addEventListener("dragend", function(e){  
		//获取地址信息  
		gc.getLocation(e.point, function(rs){  
			showLocationInfo(e.point, rs);  
		});  
	}); 
    map.addOverlay(marker);
}
//显示地址信息窗口  
function showLocationInfo(pt, rs){  
try{
     var addComp = rs.addressComponents;  //addComp.province+addComp.city+ 
     document.getElementById("result_").value=addComp.district+ addComp.street+ addComp.streetNumber;
	 document.getElementById("point_").innerText= pt.lng+ ","+pt.lat ; 
	 }catch(e){alert(e.message);}
}
  function addResult(){
	var _result=document.getElementById("point_").innerText;
	var text_=document.getElementById("result_").value;
	if(!_result){
		return;
	}
	var results=_result.split(",");
	var windows=window.opener;
	if(!windows){
		windows=window.parent;	
	}
	if(windows){
		windows.document.getElementById("address").value=text_;
		windows.document.getElementById("longitude").value=results[0];
		windows.document.getElementById("latitude").value=results[1];
	}
	window.close();
}
</script>
<script type="text/javascript">
document.onkeydown=function() {
	   if(event.keyCode==13) {
		   searchByStationName();
	   }
	};



</script>
</html>