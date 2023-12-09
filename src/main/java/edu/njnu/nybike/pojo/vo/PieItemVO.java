package edu.njnu.nybike.pojo.vo;

import lombok.Data;
//和饼状图所需对象对应
//后续返回给前端的数据类型：List<PieItemVO>
//封装饼图中的一条记录的实体类
//name类型：需要根据查询到的数据进行设置
@Data
public class PieItemVO <K,V>{
    private K name;
    private V value;
}
