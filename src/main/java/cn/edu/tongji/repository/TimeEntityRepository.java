package cn.edu.tongji.repository;

import cn.edu.tongji.entity.TimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TimeEntityRepository extends JpaRepository<TimeEntity,Integer> {
    @Query(value = "select count(*) from t_time where t_time.year = ?1",nativeQuery = true)
    int countByTimeId(int year);

    @Query(value = "select count(timeId) from TimeEntity where year = ?1")
    int countTimeEntitiesByYear(short year);
}
