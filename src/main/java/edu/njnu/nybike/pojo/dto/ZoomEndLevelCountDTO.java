package edu.njnu.nybike.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//操作类型及操作次数

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZoomEndLevelCountDTO {
    private Integer zoomEndLevel;
    private Integer count;
}
