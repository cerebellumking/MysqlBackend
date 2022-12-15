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

import java.sql.Timestamp;
import java.util.List;

public interface TimeEntityRepository
        extends JpaRepository<TimeEntity,Integer> , JpaSpecificationExecutor<TimeEntity> {
    @Query(value = "select count(*) from t_time where t_time.year = ?1",nativeQuery = true)
    int countByTimeId(int year);

    @Query(value = "select count(timeId) from TimeEntity where year = ?1")
    int countTimeEntitiesByYear(short year);

    /**
     *
     * @param year
     * @return
     */
    @Query(value = "select count(*) from t_movie where time_id in (select time_id from t_time where t_time.year = ?1)",nativeQuery = true)
    int countMovieNumByYear(int year);

    @Query(value = "select count(*) from t_movie where time_id in (select time_id from t_time where t_time.year = ?1 and t_time.month = ?2)",nativeQuery = true)
    int countMovieNumByYearAndMonth(int year,int month);

//   Page<TimeEntity> findAll(Specification<TimeEntity> filter, Pageable pageable);
   @Override
   List<TimeEntity> findAll(Specification<TimeEntity> filter);
   List<TimeEntity> findAllByMovieTimeAfterAndMovieTimeBefore(Timestamp minDate, Timestamp maxDate);

   TimeEntity findByTimeId(Integer timeId);

}
