package edu.njnu.nybike.pojo.vo;
import lombok.Data;

//和柱状图所需对象对应
//后续返回给前端的数据类型：List<BarItemVO>
//封装柱状图中的一条记录的实体类
//name类型：需要根据查询到的数据进行设置
@Data
public class BarDateplusVO<K>{
    private K Data;
}
