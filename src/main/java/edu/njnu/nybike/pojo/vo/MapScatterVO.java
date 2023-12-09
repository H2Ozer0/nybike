package edu.njnu.nybike.pojo.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
//散点图所需数据对应的实体类
public class MapScatterVO {
    //对应散点图的data属性
    private List<PieItemVO<String,Integer>> data;
    //对应散点图geoCorrdMap属性
    private Map<String,Double[]> geoCoordMap;
}
