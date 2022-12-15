package cn.edu.tongji.repository;

import cn.edu.tongji.entity.MovieEntity;
import cn.edu.tongji.entity.TimeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieEntityRepository extends JpaRepository<MovieEntity,Integer> , JpaSpecificationExecutor<MovieEntity> {
    @Override
    List<MovieEntity> findAll(Specification<MovieEntity> spec, Sort sort);

    List<MovieEntity> findMovieEntitiesByTimeId(int time_id);

    @Query(value = "select * from t_movie where time_id in ?1",nativeQuery = true)
    Page<MovieEntity> findMovieEntitiesByTimeIdIn(List<Integer> timeIdList, Pageable pageable);

    @Query(value = "select * from t_movie where movie_id in ?1",nativeQuery = true)
    Page<MovieEntity> findMovieEntitiesByMovieIdIn(List<Integer> movieIdList, Pageable pageable);

    List<MovieEntity> findMovieEntitiesByMovieName(String movieName);

    @Query(value = "select movie_id from t_movie where time_id in ?1",nativeQuery = true)
    List<Integer> findMovieIdByTimeIdIn(List<Integer> timeIdList);

    MovieEntity findMovieEntityByMovieId(Integer movieId);



}
