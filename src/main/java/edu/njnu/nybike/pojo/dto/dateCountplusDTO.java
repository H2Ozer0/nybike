package edu.njnu.nybike.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.*;
//操作类型及操作次数

@Data
@NoArgsConstructor
@AllArgsConstructor
public class dateCountplusDTO {
    private Map<LocalDate, List<Integer>> Hourcount;
    private LocalDate date;
    private Integer Hour_0;
    private Integer Hour_1;
    private Integer Hour_2;
    private Integer Hour_3;
    private Integer Hour_4;
    private Integer Hour_5;
    private Integer Hour_6;
    private Integer Hour_7;
    private Integer Hour_8;
    private Integer Hour_9;
    private Integer Hour_10;
    private Integer Hour_11;
    private Integer Hour_12;
    private Integer Hour_13;
    private Integer Hour_14;
    private Integer Hour_15;
    private Integer Hour_16;
    private Integer Hour_17;
    private Integer Hour_18;
    private Integer Hour_19;
    private Integer Hour_20;
    private Integer Hour_21;
    private Integer Hour_22;
    private Integer Hour_23;
    public void getMap(){
        Hourcount = new HashMap<>();
        List<Integer> hour=new ArrayList<>(24);
        hour.add(Hour_0);
        hour.add(Hour_1);
        hour.add(Hour_2);
        hour.add(Hour_3);
        hour.add(Hour_4);
        hour.add(Hour_5);
        hour.add(Hour_6);
        hour.add(Hour_7);
        hour.add(Hour_8);
        hour.add(Hour_9);
        hour.add(Hour_10);
        hour.add(Hour_11);
        hour.add(Hour_12);
        hour.add(Hour_13);
        hour.add(Hour_14);
        hour.add(Hour_15);
        hour.add(Hour_16);
        hour.add(Hour_17);
        hour.add(Hour_18);
        hour.add(Hour_19);
        hour.add(Hour_20);
        hour.add(Hour_21);
        hour.add(Hour_22);
        hour.add(Hour_23);
        Hourcount.put(date,hour);
    }

}
