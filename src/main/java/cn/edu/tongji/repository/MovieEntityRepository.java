package cn.edu.tongji.repository;

import cn.edu.tongji.entity.MovieEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface MovieEntityRepository extends JpaRepository<MovieEntity,Integer> {
    List<MovieEntity> findByMovieNameLike(String name);

    List<MovieEntity> findAllByMovieNameStartingWith(String movieName, Pageable pageable);

    //List<MovieEntity> findById()


}
