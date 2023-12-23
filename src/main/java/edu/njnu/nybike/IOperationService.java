package edu.njnu.nybike;

import edu.njnu.nybike.exception.EntityArgException;
import edu.njnu.nybike.exception.InsertException;
import edu.njnu.nybike.pojo.entity.Operation;
import edu.njnu.nybike.pojo.vo.*;

import java.time.LocalDate;
import java.util.Date;
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
    //查询每个性别的骑行数量
    List<PieItemVO<String,Integer>> findGenderRideCount();
    //查询每个性别的骑行的平均时长
    List<PieItemVO<String,Integer>> findGenderRideAvg();
    //查询会员年龄比
    List<PieItemVO<Integer,Integer>> findSubscriberAge();
    //查询非会员年龄比
    List<PieItemVO<Integer,Integer>> findCustomerAge();
    //查询所有线路经纬度
    List<RouteLineVO> findStationLine();
    //查询所有用户骑行次数
    List<PieItemVO<Date,Integer>> findDayRideCount();

    List<BarDateVO<LocalDate,Integer>> finddateCount();

    List<BarDateplusVO<Map<LocalDate, List<Integer>>>> finddateCountplus();

    LineItemVO getDailyHourlyCount();

}