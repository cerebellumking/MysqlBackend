package cn.edu.tongji.Query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @title: MovieTimeQuery
 * @Author Xinyu Fang
 * @Date: 2022/12/13 14:44
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@Builder
public class MovieTimeQuery {
    private int year;
    private int month;
    private int day;
    private int season;
    private int weekday;
}