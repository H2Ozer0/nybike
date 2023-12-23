package edu.njnu.nybike.pojo.dto;

import lombok.Data;

import java.util.Date;
@Data
public class DayRideCountDTO {
    Date date;
    Integer count;
}
