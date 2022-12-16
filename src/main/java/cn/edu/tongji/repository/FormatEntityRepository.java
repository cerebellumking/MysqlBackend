package cn.edu.tongji.repository;

import cn.edu.tongji.entity.DirectorEntity;
import cn.edu.tongji.entity.DirectorEntityPK;
import cn.edu.tongji.entity.FormatEntity;
import cn.edu.tongji.entity.FormatEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FormatEntityRepository
        extends JpaRepository<FormatEntity, FormatEntityPK>, JpaSpecificationExecutor<FormatEntity> {
    @Query(value = "select movie_format from t_format where movie_id = ?1",nativeQuery = true)
    List<String> findFormatNameByMovieId(Integer movie_id);
}
