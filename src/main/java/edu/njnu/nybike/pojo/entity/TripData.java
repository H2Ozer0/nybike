package edu.njnu.nybike.pojo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripData {
    private Integer tripduration;
    private LocalDateTime starttime;
    private LocalDateTime stoptime;
    private Integer start_station_id;
    private String start_station_name;
    private Double start_station_latitude;
    private Double start_station_longitude;
    private Integer end_station_id;
    private String end_station_name;
    private Double end_station_latitude;
    private Double end_station_longitude;
    private Integer bikeid;
    private String usertype;
    private Integer birth_year;
    private Integer gender;
}
