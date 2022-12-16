package cn.edu.tongji.repository;

import cn.edu.tongji.entity.ViewActorActorTime;
import cn.edu.tongji.entity.ViewDirectorActorTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorCoopRepository extends JpaRepository<ViewActorActorTime,Integer> {
}
