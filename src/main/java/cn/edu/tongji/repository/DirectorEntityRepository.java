package cn.edu.tongji.repository;

import cn.edu.tongji.entity.DirectorEntity;
import cn.edu.tongji.entity.DirectorEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DirectorEntityRepository
        extends JpaRepository<DirectorEntity, DirectorEntityPK> , JpaSpecificationExecutor<DirectorEntity> {
    @Query(value = "select * from t_director where director = ?1", nativeQuery = true)
    List<DirectorEntity> findDirectorEntitiesByDirectorName(String directorName);

    @Query(value = "select director from t_director where movie_id = ?1", nativeQuery = true)
    List<String> findDirectorByMovieId(Integer movieId);

    List<DirectorEntity> findByDirector(String director_name);
}
