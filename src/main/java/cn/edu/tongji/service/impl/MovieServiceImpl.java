package cn.edu.tongji.service.impl;

import cn.edu.tongji.entity.MovieEntity;
import cn.edu.tongji.entity.TimeEntity;
import cn.edu.tongji.repository.MovieEntityRepository;
import cn.edu.tongji.repository.StyleEntityRepository;
import cn.edu.tongji.repository.TimeEntityRepository;
import cn.edu.tongji.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.min;

@Service
public class MovieServiceImpl implements MovieService {
    @Resource
    private TimeEntityRepository timeEntityRepository;
    @Resource
    StyleEntityRepository styleEntityRepository;
    @Resource
    private MovieEntityRepository movieEntityRepository;
    @Override
    public int getMovieNumByTime(int year) {
        return timeEntityRepository.countMovieNumByYear(year);
    }

    @Override
    public Map getMovieBySpecificTimeRequirement(Integer year, Integer month, Integer day, Integer season, Integer weekday, Integer pageNo, Integer pageSize){
        Specification<TimeEntity> filter = ((root,query,criteriaBuilder)->{
            List<Predicate> predicates = new ArrayList<>();
            if(year != null) {
                predicates.add(criteriaBuilder.equal(root.get("year"), year));
            }
            if(month != null) {
                predicates.add(criteriaBuilder.equal(root.get("month"),month));
            }
            if(day != null) {
                predicates.add(criteriaBuilder.equal(root.get("day"),day));
            }
            if(weekday != null) {
                predicates.add(criteriaBuilder.equal(root.get("weekday"),weekday));
            }
            if(season != null) {
                predicates.add(criteriaBuilder.equal(root.get("season"),season));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });


        List<TimeEntity> timeList = timeEntityRepository.findAll(filter);
        List<Integer> timeIdList = timeList.parallelStream()
                .map(timeEntity -> timeEntity.getTimeId())
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<MovieEntity> movieList = movieEntityRepository.findMovieEntitiesByTimeIdIn(timeIdList,pageable);

        Map result = new HashMap<>();
        result.put("movieList",movieList.getContent());
        result.put("totalMovie",movieList.getTotalElements());
        return result;
    }

    @Override
    public Map getMovieByStyles(String style_1, String style_2, Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<Integer> movieIdlist = new ArrayList<>();
        Map result = new HashMap<>();
        if (style_1 !=null && style_2 != null){
            movieIdlist = styleEntityRepository.findMovieIdListByMovieStyle(style_1);
            movieIdlist.retainAll(styleEntityRepository.findMovieIdListByMovieStyle(style_2));
        }
        else if (style_1 == null && style_2 == null)
        {
            result.put("error","未加入风格限制条件");
            return result;
        }
        else if(style_1 == null){
            movieIdlist = styleEntityRepository.findMovieIdListByMovieStyle(style_2);
        }
        else{
            movieIdlist = styleEntityRepository.findMovieIdListByMovieStyle(style_1);
        }
        Page<MovieEntity> movieList = movieEntityRepository.findMovieEntitiesByTimeIdIn(movieIdlist,pageable);
        result.put("movieList",movieList.getContent());
        result.put("totalMovie",movieList.getTotalElements());
        return result;
    }
}
