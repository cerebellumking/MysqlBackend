package cn.edu.tongji.repository;

import cn.edu.tongji.entity.StyleEntity;
import cn.edu.tongji.entity.StyleEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StyleEntityRepository
        extends JpaRepository<StyleEntity, StyleEntityPK>, JpaSpecificationExecutor<StyleEntity> {
    @Query(value = "select movie_style from t_style",nativeQuery = true)
    List<String> getAllStyles();

    @Query(value = "select movie_id from t_style where movie_style like CONCAT('%',?1,'%')",nativeQuery = true)
    List<Integer> findMovieIdListByMovieStyle(String movie_style);

    @Query(value = "select movie_style from t_style where movie_id = ?1",nativeQuery = true)
    List<String> findMovieStyleListByMovieId(Integer movie_id);



}
