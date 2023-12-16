package edu.njnu.nybike.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*站点名称以及次数*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EndStationCountDTO {
    private String EndStationName;
    private Integer count;
}
