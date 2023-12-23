package edu.njnu.nybike.mapper;

import edu.njnu.nybike.pojo.dto.*;
import edu.njnu.nybike.pojo.entity.Operation;
import edu.njnu.nybike.pojo.vo.PieItemVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * 测试用例
 *
 * @author tarena
 * @create 2023-11-18-15:59
 */
@SpringBootTest
public class OperationMapperTest {

    @Autowired(required = false)
    private OperationMapper operationMapper;
    @Test
    public void insertOperation(){
        Operation operation = new Operation();
        operation.setOptType(1);
        operation.setCreatedTime(new Date());
        operation.setDragStartLat(1.1);
        operation.setDragStartLon(1.2);
        operation.setDragEndLat(2.2);
        operation.setDragEndLon(2.3);
        operation.setIconType(1);
        operation.setIsEbikeStation(0);
        operation.setStationId("12-fdsa-21233");
        operation.setStatusLevel(3);
        operation.setUserIp("127.0.0.1");
        operation.setZoomStartLevel(12.2);
        operation.setZoomEndLevel(14.4);
//        执行添加操作
        operationMapper.insertOperation(operation);
    }
    @Test
    public void listOptTypeCount(){
        List<OptTypeCountDTO> optTypeCountDTOS = operationMapper.listOptTypeCount();
        optTypeCountDTOS.forEach(item->System.out.println(item));
    }
    @Test
    public void listZoomEndLevelCount(){
        List<ZoomEndLevelCountDTO> zoomEndLevelCountDTOS= operationMapper.listZoomEndLevelCount();
        zoomEndLevelCountDTOS.forEach(item -> System.out.println(item));
    }
    @Test
    public void listEndStationCount(){
        List<PieItemVO<String, Integer>> endStationCountDTOS=operationMapper.listEndStationCount();
        endStationCountDTOS.forEach(item-> System.out.println("EndStationName: " +
                item.getName() + ", Count: " + item.getValue()));
    }
    @Test
    public void listGenderRideCount(){
        List<GenderRideCountDTO> genderRideCountDTOS = operationMapper.listGenderRideCount();
        genderRideCountDTOS.forEach(item->System.out.println(item));
    }
    @Test
    public void listSubscriberAge(){
        List<SubscriberAgeDTO> subscriberAgeDTOS = operationMapper.listSubscriberAge();
        subscriberAgeDTOS.forEach(item->System.out.println(item));
    }
    @Test
    public void listCustomerAge(){
        List<CustomerAgeDTO> customerAgeDTOS = operationMapper.listCustomerAge();
        customerAgeDTOS.forEach(item->System.out.println(item));
    }
    @Test
    public void listStationLine(){
        List<StationLineDTO> stationLineDTOS = operationMapper.listStationLine();
        stationLineDTOS.forEach(item->System.out.println(item));
    }
    @Test
    public void listDayRideCOunt(){
        List<DayRideCountDTO> dayRideCountDTOS= operationMapper.listDayRideCount();
        dayRideCountDTOS.forEach(item->System.out.println(item));
    }
}
