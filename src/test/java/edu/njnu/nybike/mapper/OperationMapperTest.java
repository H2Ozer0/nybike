package edu.njnu.nybike.mapper;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import edu.njnu.nybike.IOperationService;
import edu.njnu.nybike.pojo.dto.EndStationCountDTO;
import edu.njnu.nybike.pojo.dto.OptTypeCountDTO;
import edu.njnu.nybike.pojo.dto.ZoomEndLevelCountDTO;
import edu.njnu.nybike.pojo.entity.Operation;
import edu.njnu.nybike.pojo.vo.PieItemVO;
import edu.njnu.nybike.service.impl.OperationServiceImpl;
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
        optTypeCountDTOS.forEach(item->System.out.println());
    }
    @Test
    public void listZoomEndLevelCount(){
        List<ZoomEndLevelCountDTO> zoomEndLevelCountDTOS= operationMapper.listZoomEndLevelCount();
        zoomEndLevelCountDTOS.forEach(item -> System.out.println(item));
    }
    @Test
    public void listEndStationCount(){
        List<EndStationCountDTO> endStationCountDTOS=operationMapper.listEndStationCount();
        endStationCountDTOS.forEach(item-> System.out.println("EndStationName: " +
                item.getEndStationName() + ", Count: " + item.getCount()));
    }
}
