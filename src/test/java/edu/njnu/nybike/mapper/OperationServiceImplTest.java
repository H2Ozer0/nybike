package edu.njnu.nybike.mapper;

import edu.njnu.nybike.IOperationService;
import edu.njnu.nybike.pojo.entity.Operation;
import edu.njnu.nybike.pojo.vo.BarItemVO;
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
public class OperationServiceImplTest {

    @Autowired(required = false)
    private IOperationService operationService;
    @Test
    public void saveOperation(){
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
        operationService.saveOperation(operation);
    }

    @Test
    public void findOptTypeCount(){
        List<PieItemVO<String,Integer>> optTypeCount= operationService.findOptTypeCount();
    }

    @Test
    public void addNewStationInfo(){
        operationService.addNewStationInfo();
        try {
            Thread.sleep(6000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
