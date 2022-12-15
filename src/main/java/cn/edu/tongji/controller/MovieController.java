package cn.edu.tongji.controller;

import cn.edu.tongji.service.impl.MovieServiceImpl;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.management.ValueExp;
import java.util.Map;

@RestController
@RequestMapping("mysql/movie")
public class MovieController {
    @Resource
    private MovieServiceImpl movieService;


    @GetMapping("time/year")
    public ResponseEntity<Integer> getMovieNumByYear(int year){
        long startTime = System.currentTimeMillis();
        int result = movieService.getMovieNumByTime(year);
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        return ResponseEntity.ok(result);
    }

//    @GetMapping("time/yearAndMonth")
//    public ResponseEntity<Integer> getMovieNumByYearAndMonth(int year, int month){
//        long startTime = System.currentTimeMillis();
//        int result = movieService.getMovieNumByYearAndMonth(year,month);
//        long endTime = System.currentTimeMillis();
//        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
//        return ResponseEntity.ok(result);
//    }

    @RequestMapping(value = "time/condition",method = RequestMethod.GET)
    public ResponseEntity<Map> getMovieNumByYearAndMonth(
            @RequestParam(value = "year",required = false) Integer year,
            @RequestParam(value = "month",required = false) Integer month,
            @RequestParam(value = "day",required = false) Integer day,
            @RequestParam(value = "season",required = false) Integer season,
            @RequestParam(value = "weekday",required = false) Integer weekday,
            @RequestParam(value = "pageNo",required = true,defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize",required = true,defaultValue = "10") Integer pageSize
    ){
        long startTime = System.currentTimeMillis();
        Map result = movieService.getMovieBySpecificTimeRequirement(year,month,day,season,weekday,pageNo,pageSize);
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        result.put("time",endTime - startTime);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "style",method = RequestMethod.GET)
    public ResponseEntity<Map> getMovieByStyles(
            @RequestParam(value = "style_1",required = false) String style_1,
            @RequestParam(value = "style_2",required = false) String style_2,
            @RequestParam(value = "pageNo",required = true,defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize",required = true,defaultValue = "10") Integer pageSize
    ){
        long startTime = System.currentTimeMillis();
        Map result = movieService.getMovieByStyles(style_1,style_2,pageNo,pageSize);
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        result.put("time",endTime - startTime);
        return ResponseEntity.ok(result);
    }
}
