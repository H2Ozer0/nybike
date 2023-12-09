var saveOperationUrl="operation/save";

//定义向服务器发送AJAx请求的代码
//params用户操作数据
function saveOperation(params){
    //补充通用的参数，实现车桩切换后使用
    // params.iconType = isBikeIcon?1:2;
    params.iconType = 1;
    $.post(saveOperationUrl,params,function(result){
        if(result.state == 1000){
            console.log("操作记录添加成功");
        }else{
            console.log("操作记录添加失败");
        }
    })
}

//保存拖拽经纬度信息的变量
var dragStartLat;
var dragStartLon;
var dragEndLat;
var dragEndLon;

var zoomStartLevel;
var zoomEndLevel;


//定义一个初始化操作的方法，在nymap.html中选择合适的时机调用此方法
function initCollectionOPeration(){
    //为所有的maeker添加点击事件
    for(var index in markerArray){
        var marker= markerArray[index]
        marker.addEventListener("click",function(s){
            //生成一条记录
            var params={};
            params.optType=1;
            //this表示当前marker对象，sid是之前和marker绑定的statinID
            params.stationId=this.sid;
            //基于站点id获取站点对应的bikelevel
            params.statusLevel=bikeLevelMap.get(this.sid);
            // if(isBikeIcon){//车桩切换
            //     params.statusLevel=bikeLevelMap.get(this.sid);
            // }else{
            //     params.statusLevel=dockLevelMap.get(this.sid);
            // }
            //可用电车数量
            var nea = infoWindowDataMap.get(this.sid).nea;
            params.isEbikeStation=(nea==0?0:1);
            //触发AJAX
            saveOperation(params);
        })

    }
    //开始和结束事件
    map.addEventListener("dargstart",function(e){
        dragStartLat=e.point.lat.toFixed(6);
        dragStartLon=e.point.lng.toFixed(6);
    })
    map.addEventListener("dragend",function(e){
        dragEndLat=e.point.lat.toFixed(6);
        dragEndLon=e.point.lng.toFixed(6);

        //生成请求参数
        var params={
            optType:2,
            dragStartLat:dragStartLat,
            dragStartLon:dragStartLon,
            dragEndLat: dragEndLat,
            dragEndLon:dragEndLon
        };
        saveOperation(params);
    })
    map.addEventListener("zoomstart",function(e){
        zoomStartLevel = map.getZoom().toFixed(2);

    })
    map.addEventListener("zoomend",function(e){
        zoomEndLevel = map.getZoom().toFixed(2);
        //收集用户操作参数
        var params = {
            optType:3,
            zoomStartLevel:zoomStartLevel,
            zoomEndLevel:zoomEndLevel
        };
        saveOperation(params);
    })
}

