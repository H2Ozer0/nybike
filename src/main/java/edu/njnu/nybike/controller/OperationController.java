package edu.njnu.nybike.controller;

import edu.njnu.nybike.IOperationService;
import edu.njnu.nybike.pojo.entity.Operation;
import edu.njnu.nybike.pojo.vo.BarItemVO;
import edu.njnu.nybike.pojo.vo.LineItemVO;
import edu.njnu.nybike.pojo.vo.MapScatterVO;
import edu.njnu.nybike.pojo.vo.PieItemVO;
import edu.njnu.nybike.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public JsonResult<List<PieItemVO<String,Integer>>> optTypeCount(){
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
}
