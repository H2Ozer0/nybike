package edu.njnu.nybike.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
//操作类型及操作次数

@Data
@NoArgsConstructor
@AllArgsConstructor
public class dateCountDTO {
    private LocalDate date;
    private Integer count;
}
