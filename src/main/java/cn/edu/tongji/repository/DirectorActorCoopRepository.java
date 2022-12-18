package cn.edu.tongji.repository;

import cn.edu.tongji.entity.ViewDirectorActorTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DirectorActorCoopRepository extends JpaRepository<ViewDirectorActorTime,Integer> {
    //@Query(value ="select * from view_director_actor_time where coop_time = (select max(coop_time) from view_director_actor_time) ",nativeQuery = true)
    @Query(value = "select * from view_director_actor_time order by coop_time desc limit 1",nativeQuery = true)
    List<ViewDirectorActorTime> findMostCoop();
}
