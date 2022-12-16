package cn.edu.tongji.repository;

import cn.edu.tongji.entity.ActorEntity;
import cn.edu.tongji.entity.ActorEntityPK;
import cn.edu.tongji.entity.DirectorEntity;
import cn.edu.tongji.entity.DirectorEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActorEntityRepository
        extends JpaRepository<ActorEntity, ActorEntityPK>, JpaSpecificationExecutor<ActorEntity> {

    @Query(value = "select movie_id from t_actor where actor_name like CONCAT('%',?1,'%') and is_star = true",nativeQuery = true)
    List<Integer> findMovieIdListByStarName(String actorName);

    @Query(value = "select movie_id from t_actor where actor_name like CONCAT('%',?1,'%') and is_star = false",nativeQuery = true)
    List<Integer> findMovieIdListByNormalActorName(String actorName);

    @Query(value = "select actor_name from t_actor where movie_id = ?1 and is_star = true",nativeQuery = true)
    List<String> findMainActorNameByMovieId(Integer movieId);

    @Query(value = "select actor_name from t_actor where movie_id = ?1 and is_star = false",nativeQuery = true)
    List<String> findActorNameByMovieId(Integer movieId);

}
