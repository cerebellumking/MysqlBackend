package cn.edu.tongji.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface MovieService {
    int getMovieNumByTime(int year);

//    int getMovieNumBySpecificRequirement(int year,int month,int day,int season,int weekday);

    Map getMovieBySpecificTimeRequirement(Integer year, Integer month, Integer day, Integer season, Integer weekday, Integer pageNo, Integer pageSize);

    Map getMovieByStyles(String style_1, String style_2, Integer pageNo, Integer pageSize);

    Map getMovieByScores(float score_floor, float score_ceiling, Integer pageNo, Integer pageSize);

    Map getMovieByCommentRate(String type, float proportion, Integer pageNo, Integer pageSize);


}
