package cn.edu.tongji.service;

import cn.edu.tongji.entity.ActorEntity;
import cn.edu.tongji.entity.DirectorEntity;
import cn.edu.tongji.entity.MovieEntity;

import java.util.List;
import java.util.Map;

public interface MovieService {
    int getMovieNumByTime(int year);

    List<MovieEntity> getMoviesByName(String movieName, Integer pageNo, Integer pageSize);

    Map<String,Object> getDirectorWorks(String directorName, Integer pageNo, Integer pageSize);

    Map<String,Object> getActorWorks(String actorName);

    Map getDirectorActorCoop(Integer pageNo, Integer pageSize);

    Map getActorCoop(Integer pageNo, Integer pageSize);
}
