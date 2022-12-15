package cn.edu.tongji.repository;

import cn.edu.tongji.entity.ScoreEntity;
import cn.edu.tongji.entity.StyleEntity;
import cn.edu.tongji.entity.StyleEntityPK;
import cn.edu.tongji.entity.TimeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author 方新宇
 */
public interface ScoreEntityRepository
        extends JpaRepository<ScoreEntity, Integer>, JpaSpecificationExecutor<ScoreEntity> {

    /**
     * 根据条件查找满足条件的电影
     * @param filter
     * @return
     */
    @Override
    List<ScoreEntity> findAll(Specification<ScoreEntity> filter);
}
