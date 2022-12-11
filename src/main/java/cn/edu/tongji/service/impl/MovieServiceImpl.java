package cn.edu.tongji.service.impl;

import cn.edu.tongji.entity.TimeEntity;
import cn.edu.tongji.repository.TimeEntityRepository;
import cn.edu.tongji.service.MovieService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MovieServiceImpl implements MovieService {
    @Resource
    private TimeEntityRepository timeEntityRepository;
    @Override
    public int getMovieNumByTime(int year) {
        //return timeEntityRepository.countTimeEntitiesByYear((short) year);
        return timeEntityRepository.countByTimeId(year);
    }
}
