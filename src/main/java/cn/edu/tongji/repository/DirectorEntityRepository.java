package cn.edu.tongji.repository;

import cn.edu.tongji.entity.DirectorEntity;
import cn.edu.tongji.entity.DirectorEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface DirectorEntityRepository extends JpaRepository<DirectorEntity, DirectorEntityPK> , JpaSpecificationExecutor<DirectorEntity> {
    public List<DirectorEntity> findByDirector(String director_name);
}
