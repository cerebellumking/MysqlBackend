package cn.edu.tongji.repository;

import cn.edu.tongji.entity.ViewActorActorTime;
import cn.edu.tongji.entity.ViewDirectorActorTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActorCoopRepository extends JpaRepository<ViewActorActorTime,Integer> {
    @Query(value = "select * from view_actor_actor_time order by coop_time desc limit 1",nativeQuery = true)
    List<ViewActorActorTime> findMostCoop();
}
