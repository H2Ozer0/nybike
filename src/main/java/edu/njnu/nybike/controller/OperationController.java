package edu.njnu.nybike.controller;

import edu.njnu.nybike.IOperationService;
import edu.njnu.nybike.pojo.entity.Operation;
import edu.njnu.nybike.pojo.vo.*;
import edu.njnu.nybike.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/operation")
public class OperationController {
    //接受浏览器发来的请求，并解析请求参数封装成Operation对象
    @Autowired
    private IOperationService operationService;
    @PostMapping("/save")
    public JsonResult<Void> saveOperation(Operation operation){
        operationService.saveOperation(operation);
        return JsonResult.getSuccessJR();
    }
    @GetMapping("/findOptTypeCount")
    public JsonResult<List<PieItemVO<String,Integer>>> findoptTypeCount(){
        List<PieItemVO<String,Integer>> pieItemVOList=
                operationService.findOptTypeCount();
        return JsonResult.getSuccessJR(pieItemVOList);
    }
    @GetMapping("/findZoomEndLevelCount")
    public JsonResult<BarItemVO> findZoomEndLevelCount(){
        BarItemVO barItemVO =
                operationService.findZoomEndLevelCount();
        return  JsonResult.getSuccessJR(barItemVO);
    }
    @GetMapping("/findDayHourCount")
    public JsonResult <LineItemVO> findDayHourCount(){
        LineItemVO lineItemVO =
                operationService.findDayHourCount();
        return  JsonResult.getSuccessJR(lineItemVO);
    }
    @GetMapping("/findStationVisitCount")
    public JsonResult <MapScatterVO> findStationVisitCount(){
        return  JsonResult.getSuccessJR(operationService.findStationVisitCount());
    }
    @GetMapping("/findEndStationCount")
    public JsonResult <MapScatterVO> findEndStationCount(){
        return JsonResult.getSuccessJR(operationService.findEndStationCount());
    }

    @GetMapping("/findGenderRideCount")
    public JsonResult<List<PieItemVO<String,Integer>>> findGenderRideCount(){
        List<PieItemVO<String,Integer>> pieItemVOList=operationService.findGenderRideCount();
        return JsonResult.getSuccessJR(pieItemVOList);
    }
    @GetMapping("/findGenderRideAvg")
    public JsonResult<List<PieItemVO<String,Integer>>> findGenderRideAvg(){
        List<PieItemVO<String,Integer>> pieItemVOList=operationService.findGenderRideAvg();
        return JsonResult.getSuccessJR(pieItemVOList);
    }
    @GetMapping("/findSubscriberAge")
    public JsonResult<List<PieItemVO<Integer,Integer>>> findSubscriberAge(){
        List<PieItemVO<Integer,Integer>> pieItemVOList=operationService.findSubscriberAge();
        return JsonResult.getSuccessJR(pieItemVOList);
    }
    @GetMapping("/findCustomerAge")
    public JsonResult<List<PieItemVO<Integer,Integer>>> findCustomerAge(){
        List<PieItemVO<Integer,Integer>> pieItemVOList=operationService.findCustomerAge();
        return JsonResult.getSuccessJR(pieItemVOList);
    }
    @GetMapping("/findStationLine")
    public JsonResult<List<RouteLineVO>> findStationLine(){
        List<RouteLineVO> routeLineVOList=operationService.findStationLine();
        return JsonResult.getSuccessJR(routeLineVOList);
    }
    @GetMapping("/findDayRideCount")
    public JsonResult<List<PieItemVO<Date,Integer>>> findDayRideCount(){
        List<PieItemVO<Date,Integer>> pieItemVOList=operationService.findDayRideCount();
        return JsonResult.getSuccessJR(pieItemVOList);
    }
}
