package cn.edu.tongji.repository;

import cn.edu.tongji.entity.ViewDirectorActorTime;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author jqChen
 * @Date 2022/12/15
 * @Github https://github.com/KyrinChen
 */
public interface DirectorActorCoopRepository extends JpaRepository<ViewDirectorActorTime,Integer> {
}
