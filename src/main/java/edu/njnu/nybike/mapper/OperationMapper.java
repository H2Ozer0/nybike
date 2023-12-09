package edu.njnu.nybike.mapper;

import edu.njnu.nybike.pojo.dto.DayHourCountDTO;
import edu.njnu.nybike.pojo.dto.OptTypeCountDTO;
import edu.njnu.nybike.pojo.dto.ZoomEndLevelCountDTO;
import edu.njnu.nybike.pojo.entity.Operation;
import edu.njnu.nybike.pojo.entity.StationInfo;
import edu.njnu.nybike.pojo.vo.PieItemVO;

import java.util.List;
import java.util.Set;

public interface OperationMapper{
    /*
     * 新增一条用户的操作记录
     * @param operation用户操作记录对象
     * @return 操作结果，影响的行数，根据此数值判断是否新增成功
     * */

    int insertOperation(Operation operation);
    List<OptTypeCountDTO> listOptTypeCount();
    List<ZoomEndLevelCountDTO> listZoomEndLevelCount();
    List<DayHourCountDTO> listDayHourCount();
    //基于站点id查询站点信息
    StationInfo getStationInfoById(String sid);
    //查询所有站点信息
    Set<String> listStationInfoIds();
    //添加多条站点信息
    int insertStationInfos(List<StationInfo> stationInfos);
    //查询站点名称以及被访问的次数，返回名称-访问次数对应关系
    List<PieItemVO<String,Integer>> listStationVisitCount();
    //基于站点名称的数组查询站点数据,返回名称-经纬度对应关系
    List<StationInfo> listStationInfo(List<String> names);
}