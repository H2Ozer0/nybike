package edu.njnu.nybike.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.log.Log;
import edu.njnu.nybike.IOperationService;
import edu.njnu.nybike.exception.EntityArgException;
import edu.njnu.nybike.exception.InsertException;
import edu.njnu.nybike.listener.MySCListener;
import edu.njnu.nybike.mapper.OperationMapper;
import edu.njnu.nybike.pojo.dto.DayHourCountDTO;
import edu.njnu.nybike.pojo.dto.OptTypeCountDTO;
import edu.njnu.nybike.pojo.dto.ZoomEndLevelCountDTO;
import edu.njnu.nybike.pojo.entity.Operation;
import edu.njnu.nybike.pojo.entity.StationInfo;
import edu.njnu.nybike.pojo.vo.BarItemVO;
import edu.njnu.nybike.pojo.vo.LineItemVO;
import edu.njnu.nybike.pojo.vo.MapScatterVO;
import edu.njnu.nybike.pojo.vo.PieItemVO;
import edu.njnu.nybike.util.IPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sun.security.ssl.Debug;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class OperationServiceImpl  implements IOperationService {
    //注入持久层对象
    @Autowired(required=false)
    private OperationMapper operationMapper;
    String [] opTypeName = {"点击","拖拽","缩放"};
    //处理用户操作记录，并判断是否有异常，最后调用持久层插入
    @Override
    public List<PieItemVO<String,Integer>> findOptTypeCount(){
        List<OptTypeCountDTO> optTypeCountDTOList=operationMapper.listOptTypeCount();
        List<PieItemVO<String,Integer>> pieItemVOList=new ArrayList<>(optTypeCountDTOList.size());
        for(OptTypeCountDTO optTypeCountDTO:optTypeCountDTOList){
            PieItemVO<String,Integer> pieItemVO = new PieItemVO();
            pieItemVO.setName(opTypeName[optTypeCountDTO.getOptType()-1]);
            pieItemVO.setValue(optTypeCountDTO.getCount());
            pieItemVOList.add(pieItemVO);

        }
        return pieItemVOList;
    }

    @Override
    public BarItemVO findZoomEndLevelCount(){
        List<ZoomEndLevelCountDTO> zoomEndLeveCountDTOList= operationMapper.listZoomEndLevelCount();
        BarItemVO barItemVO=new BarItemVO();;
        Map<Integer,Integer> yDataMap = new HashMap<>();
        //因为14.4和14在加入Integer列表时会被算作两个14，所以用Set来防止重复值被加入数组，
        Set<Integer> set = new HashSet<>();
        for(ZoomEndLevelCountDTO zoomEndLevelCountDTO:zoomEndLeveCountDTOList){
            //Jackson 在序列化时，默认不支持将 null 作为 Map 的键，需要避免key为null
            if (zoomEndLevelCountDTO.getZoomEndLevel() != null) {
                set.add(zoomEndLevelCountDTO.getZoomEndLevel());
                yDataMap.put(zoomEndLevelCountDTO.getZoomEndLevel(), zoomEndLevelCountDTO.getCount());
            }
        }
        barItemVO.setYDataMap(yDataMap);
        List<Integer> xData = new ArrayList<>(set);
        Collections.sort(xData);//给列表升序排序
        barItemVO.setXData(xData);
        return barItemVO;
    }
    @Override
    public LineItemVO findDayHourCount(){
        List<DayHourCountDTO> dayHourCountDTOList=operationMapper.listDayHourCount();
        LineItemVO lineItemVO=new LineItemVO();
        Map<LocalDate,List<Integer>> yDataMap=new HashMap<>();
        for(int i=0;i<=2;i++){
            List<Integer> countlist=new ArrayList<>();
            Map<Integer,Integer> tempMap=new HashMap<>();
            LocalDate date=LocalDate.now().minusDays(i);
            for(DayHourCountDTO dayHourCountDTO:dayHourCountDTOList){
               if(ChronoUnit.DAYS.between(dayHourCountDTO.getDay(), LocalDate.now())==i) {
//                   System.out.println(dayHourCountDTO.getDay());
//                   System.out.println(LocalDate.now());
//                   System.out.println(ChronoUnit.DAYS.between(dayHourCountDTO.getDay(), LocalDate.now()));
                   tempMap.put(dayHourCountDTO.getHour(),dayHourCountDTO.getCount());
                   date=dayHourCountDTO.getDay();
               }
            }
            for(int j=0;j<24;j++){
                if(tempMap.containsKey(j)){
                    countlist.add(tempMap.get(j));
                }else{
                    countlist.add(0);
                }
            }
            yDataMap.put(date,countlist);

        }
        lineItemVO.setYDataMap(yDataMap);
        List<Integer> xData=new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24));
        lineItemVO.setXData(xData);
        return lineItemVO;
    }

    @Override
    public void saveOperation(Operation operation) throws InsertException,EntityArgException{
        if(operation == null) {
            throw new EntityArgException("添加操作记录异常：操作记录为空");
        }
        if(operation.getOptType()==null){
            throw new EntityArgException("添加操作类型异常：操作记录为空");
        }
        //TODO 根据不同操作类型，校验响应数据非空
        //判断是否存在站点信息
        if(operation.getOptType().equals(1)){
            //基于站点id查询站点信息
           StationInfo stationInfo=operationMapper.getStationInfoById(operation.getStationId());
           if(stationInfo==null){
               //增加站点信息
               addNewStationInfo();
           }
        }
        //补全用户IP
        operation.setUserIp(IPUtils.getIpAddr());
        //补全创建时间
        operation.setCreatedTime(new Date());
        try{
            int row = operationMapper.insertOperation(operation);
            if(row!=1){
                throw new InsertException("添加操作记录异常：数据添加失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new InsertException("添加操作记录异常："+e.getMessage());
        }

    }

    @Override
    public void addNewStationInfo(){
        //创建一个子线程来执行耗时操作
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                //查询信息表中所有站点id
                Set<String>existIds=operationMapper.listStationInfoIds();
                //请求一次站点信息数据，从官方url中获取
                SimpleClientHttpRequestFactory requestFactory=new SimpleClientHttpRequestFactory();
                requestFactory.setConnectTimeout(3000);
                requestFactory.setReadTimeout(3000);
                RestTemplate template=new RestTemplate(requestFactory);
                String infoData=null;
                //自动重试次数不超过3次
                for(int i=1;i<=3;i++){
                    try {
                        //请求官方站点信息url的数据
                        infoData = template.getForObject(MySCListener.INFO_URL, String.class);
                        System.err.println("查询到的infoData"+ LocalDateTime.now()+infoData.length());
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    if(infoData!=null){
                        break;//查询到数据，跳出重试
                    }else{
                        try{
                            Thread.sleep(3000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }

                    }
                }
                //如果未获取到站点信息数据，输出错误日志
                if(infoData==null){
                    System.err.println("未查询到的infoData，更新站点信息一次");
                    return;
                }
                //获取到了数据，解析JSON数据--infoData,运用通用Mapper技术
                ObjectMapper objectMapper = new ObjectMapper();
                //设置忽略实体类StationInfo中不存在的熟悉
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
                try{
                    //将json格式的字符串转换为JsonNode节点树
                    JsonNode jsonNode=objectMapper .readTree(infoData);
                    //获取到stations节点的数据：字符串类型--数据是数组
                    String stations = jsonNode.get("data").get("stations").toString();
                    //将其转化为一个元素为Satations的数组[{'id','1232','name'.....}]
                    List<StationInfo> stationInfoList=objectMapper.readValue(stations,new TypeReference<List<StationInfo>>(){});
                    //从最新站点信息中移除数据库中已经存在的站点信息
                    Iterator<StationInfo> it=stationInfoList.iterator();
                    while (it.hasNext()){
                        StationInfo stationInfo= it.next();
                        //移除已经保存的
                        if(existIds.contains(stationInfo.getId())){
                            it.remove();
                        }
                    }
                    //将list中剩余的站点是本次需要新增的站点，将其批量添加到数据中
                    operationMapper.insertStationInfos(stationInfoList);

                }catch(JsonProcessingException e){
                    e.printStackTrace();
                }
            }
        });
        t1.start();
    }

    @Override
    public MapScatterVO findStationVisitCount(){
        //查询名称和次数
        List<PieItemVO<String,Integer>> pieItemVOS=operationMapper.listStationVisitCount();
        //将所有名称封装成一个集合
        List<String> names=new ArrayList<>();
        for(PieItemVO<String,Integer> pieItemVO:pieItemVOS){
            names.add(pieItemVO.getName());
        }
        //基于站点名称集合，查询所有对应经纬度信息
        List<StationInfo> stationInfoList=operationMapper.listStationInfo(names);
        //将站点名称和经纬度信息封装成Map
        Map<String,Double[]> geoCoordMap=new HashMap<>();
        for(StationInfo stationInfo:stationInfoList){
            Double[] coord={stationInfo.getLon(),stationInfo.getLat()};
            geoCoordMap.put(stationInfo.getName(),coord);
        }
        //将查询并处理完成的数据封装成vo对象并返回
        MapScatterVO mapScatterVO=new MapScatterVO();
        mapScatterVO.setData(pieItemVOS);
        mapScatterVO.setGeoCoordMap(geoCoordMap);
        return mapScatterVO;
    }

}
