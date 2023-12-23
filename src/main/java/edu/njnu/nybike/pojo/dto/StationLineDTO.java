package edu.njnu.nybike.pojo.dto;

import lombok.Data;

@Data
public class StationLineDTO {
    private Double startLatitude;
    private Double startLongitude;
    private Double endLatitude;
    private Double endLongitude;
}
