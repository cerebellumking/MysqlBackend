package cn.edu.tongji.repository;

import cn.edu.tongji.entity.ActorEntity;
import cn.edu.tongji.entity.ActorEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface ActorEntityRepository extends JpaRepository<ActorEntity, ActorEntityPK> , JpaSpecificationExecutor<ActorEntity> {
    public List<ActorEntity> findByActorName(String name);
}
