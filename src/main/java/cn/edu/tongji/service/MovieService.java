package cn.edu.tongji.service;

import cn.edu.tongji.MovieInfoDTO;
import cn.edu.tongji.entity.ActorEntity;
import cn.edu.tongji.entity.DirectorEntity;
import cn.edu.tongji.entity.MovieEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public interface MovieService {
    int getMovieNumByTime(int year);

    List<MovieEntity> getMoviesByName(String movieName, Integer pageNo, Integer pageSize);

    Map<String,Object> getDirectorWorks(String directorName, Integer pageNo, Integer pageSize);

    Map<String,Object> getActorWorks(String actorName);

    Map getDirectorActorCoop(Integer pageNo, Integer pageSize);

    Map getActorCoop(Integer pageNo, Integer pageSize);

//    int getMovieNumBySpecificRequirement(int year,int month,int day,int season,int weekday);

    Map getMovieBySpecificTimeRequirement(Integer year, Integer month, Integer day, Integer season, Integer weekday, Integer pageNo, Integer pageSize);

    Map getMovieByStyles(String style_1, String style_2, Integer pageNo, Integer pageSize);

    Map getMovieByScores(float score_floor, float score_ceiling, Integer pageNo, Integer pageSize);

    Map getMovieByCommentRate(String type, float proportion, Integer pageNo, Integer pageSize);


    HashMap<String, Object> getMovieResultsByMutipleRules(MovieInfoDTO movieInfoDTO);
}
