package edu.njnu.nybike.pojo.vo;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Data
public class LineItemVO{
    private List<Integer> xData;
    private Map<LocalDate,List<Integer>> yDataMap;
}