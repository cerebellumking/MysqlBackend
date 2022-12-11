package cn.edu.tongji.controller;

import cn.edu.tongji.service.impl.MovieServiceImpl;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("movie")
public class MovieController {
    @Resource
    private MovieServiceImpl movieService;
    @GetMapping("time")
    public ResponseEntity<Integer> getMovieNumByTime(int year){
        long startTime = System.currentTimeMillis();
        int result = movieService.getMovieNumByTime(year);
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        return ResponseEntity.ok(result);
    }
}
