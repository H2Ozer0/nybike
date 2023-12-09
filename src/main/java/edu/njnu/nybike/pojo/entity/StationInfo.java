package edu.njnu.nybike.pojo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationInfo {
    //官方站点信息数据中站点id的属性名为station_id
    @JsonProperty("station_id")
    private String id;
    private String name;
    private  Double lat;
    private Double lon;
}
