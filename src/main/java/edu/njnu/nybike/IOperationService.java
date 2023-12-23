package edu.njnu.nybike;

import edu.njnu.nybike.exception.EntityArgException;
import edu.njnu.nybike.exception.InsertException;
import edu.njnu.nybike.pojo.entity.Operation;
import edu.njnu.nybike.pojo.vo.BarItemVO;
import edu.njnu.nybike.pojo.vo.LineItemVO;
import edu.njnu.nybike.pojo.vo.MapScatterVO;
import edu.njnu.nybike.pojo.vo.PieItemVO;

import java.util.List;
import java.util.Map;

public interface IOperationService {

    void saveOperation(Operation operation) throws InsertException, EntityArgException;
    List<PieItemVO<String,Integer>> findOptTypeCount();
    BarItemVO findZoomEndLevelCount();
    LineItemVO findDayHourCount();
    //获取最新的站点信息数据，与数据库已有数据进行对比，添加新的站点数据
    void addNewStationInfo();
    //查询站点名称，站点经纬度，站点访问次数，处理成相应格式，返回前端所需数据
    MapScatterVO findStationVisitCount();

    /**
     * 站点作为终点的次数
     * @return 散点图所需要的对象集合
     */
    MapScatterVO findEndStationCount();

}