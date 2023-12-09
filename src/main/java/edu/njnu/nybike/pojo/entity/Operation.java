package edu.njnu.nybike.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation {

  private Integer id;
  private Integer optType;
  private String stationId;
  private Integer statusLevel;
  private Integer isEbikeStation;
  private Double dragStartLat;
  private Double dragStartLon;
  private Double dragEndLat;
  private Double dragEndLon;
  private Double zoomStartLevel;
  private Double zoomEndLevel;
  private Integer iconType;
  private String userIp;
  private Date createdTime;
  
}
