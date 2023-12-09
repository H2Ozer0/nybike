package edu.njnu.nybike.controller;
//数据接口
//1.控制器，添加一个springmvc提供的注解
//2.分别开发两个控制器方法，分别响应用户站点信息数据和站点状态数据的请求
//解释：将前端请求和后端方法绑定成一个映射关系
//实现：springmvc提供的@RequestMapping及其变形（方式不同，例如GET\Post\PUT等
import edu.njnu.nybike.listener.MySCListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/realtime")
public class RealTimeDataController {
    /*
    * 此方法实际上是和http://localhost/nybike/realtime/infoData 请求绑定
    * 获取站点信息数据
    * @param request 前端请求
    * @return 站点纤细 infoData字符串
     */
    @RequestMapping(value = "/infoData",produces = "application/json;charset=utf-8")
    public String getInfoData(HttpServletRequest request){
        //从全局共享的servletCintext中去除站点信息数据
        ServletContext sc = request.getServletContext();
        String infoData = (String)sc.getAttribute(MySCListener.INFO_DATA);
        if(infoData == null){
            //构建一个空的json串返回
            infoData = "{}";
        }
        return infoData;
    }

    /*
     * 此方法实际上是和http://localhost/nybike/realtime/statusData 请求绑定
     * 获取站点信息数据
     * @param request 前端请求
     * @return 站点纤细 infoData字符串
     */
    @RequestMapping(value = "/statusData",produces = "application/json;charset=utf-8")
    public String getStatusData(HttpServletRequest request){
        //从全局共享的servletCintext中去除站点信息数据
        ServletContext sc = request.getServletContext();
        String statusData = (String)sc.getAttribute(MySCListener.STATUS_DATA);
        if(statusData == null){
            //构建一个空的json串返回
            statusData = "{}";
        }
        return statusData;
    }
}
