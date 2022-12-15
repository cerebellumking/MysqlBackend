package cn.edu.tongji.service.impl;

import cn.edu.tongji.entity.*;
import cn.edu.tongji.repository.*;
import cn.edu.tongji.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl implements MovieService {
    @Resource
    private TimeEntityRepository timeEntityRepository;
    @Resource
    private MovieEntityRepository movieEntityRepository;
    @Resource
    private DirectorEntityRepository directorEntityRepository;
    @Resource
    private ActorEntityRepository actorEntityRepository;
    @Resource
    private DirectorActorCoopRepository directorActorCoopRepository;
    @Resource
    private ActorCoopRepository actorCoopRepository;
    @Override
    public int getMovieNumByTime(int year) {
        //return timeEntityRepository.countTimeEntitiesByYear((short) year);
        return timeEntityRepository.countByTimeId(year);
    }

    @Override
    public List<MovieEntity> getMoviesByName(String movieName, Integer pageNo, Integer pageSize){
        //List<HashMap<String,Object>> result=new ArrayList<>();

        /**
         *  分页参数Pageable
         *      参数1：查询的页码
         *      参数2：每页查询的条数
         *      参数3：查询结果的排序规则（可选
         */
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        //return movieEntityRepository.findByMovieNameLike(movieName+"%");
        return movieEntityRepository.findAllByMovieNameStartingWith(movieName, pageable);

    }

    @Override
    public Map<String,Object> getDirectorWorks(String directorName, Integer pageNo, Integer pageSize){
        Map<String ,Object> result=new HashMap<>();
        List<Object> movies = new ArrayList<>();
        List<DirectorEntity> directorEntities = directorEntityRepository.findByDirector(directorName);

        result.put("director_name", directorName);
        result.put("movieNum",directorEntities.size());
        for(DirectorEntity directorEntity:directorEntities){
            MovieEntity movieEntity=movieEntityRepository.findById(directorEntity.getMovieId()).orElse(null);
            movies.add(movieEntity.getMovieName());
        }
        result.put("movies",movies);

        return result;
    }

    @Override
    public Map<String,Object> getActorWorks(String actorName){
        Map<String ,Object> result=new HashMap<>();
        List<Object> isStarMovies = new ArrayList<>();
        List<Object> otherMovies = new ArrayList<>();
        List<ActorEntity> actorEntities = actorEntityRepository.findByActorName(actorName);

        result.put("actor_name", actorName);
        for(ActorEntity actorEntity:actorEntities){
            MovieEntity movieEntity=movieEntityRepository.findById(actorEntity.getMovieId()).orElse(null);
            if(actorEntity.isStar()){
                isStarMovies.add(movieEntity.getMovieName());
            }
            else{
                otherMovies.add(movieEntity.getMovieName());
            }

        }
        result.put("is_star_movieNum", isStarMovies.size());
        result.put("is_star_movies",isStarMovies);
        result.put("not_star_movieNum", otherMovies.size());
        result.put("not_star_movies",otherMovies);

        return result;
    }

    @Override
    public Map getDirectorActorCoop(Integer pageNo, Integer pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<ViewDirectorActorTime> coopList = directorActorCoopRepository.findAll(pageable);
        Map result = new HashMap<>();
        result.put("cooperation",coopList.getContent());
        return result;
    }

    @Override
    public Map getActorCoop(Integer pageNo, Integer pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<ViewActorActorTime> coopList = actorCoopRepository.findAll(pageable);
        Map result = new HashMap<>();
        result.put("cooperation",coopList.getContent());
        return result;
    }
}
