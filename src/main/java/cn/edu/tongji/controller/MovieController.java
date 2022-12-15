package cn.edu.tongji.controller;
import cn.edu.tongji.MovieInfoDTO;
import cn.edu.tongji.service.impl.MovieServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
            @RequestParam(value = "pageNo",required = false,defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize
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
            @RequestParam(value = "pageNo",required = false,defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize
    ){
        long startTime = System.currentTimeMillis();
        Map result = movieService.getMovieByStyles(style_1,style_2,pageNo,pageSize);
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        result.put("time",endTime - startTime);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "score",method = RequestMethod.GET)
    public ResponseEntity<Map> getMovieByScores(
            @RequestParam(value = "score_floor",required = false,defaultValue = "0.0") float score_floor,
            @RequestParam(value = "score_ceiling",required = false,defaultValue = "5.0") float score_ceiling,
            @RequestParam(value = "pageNo",required = false,defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize
    ){
        long startTime = System.currentTimeMillis();
        Map result = movieService.getMovieByScores(score_floor,score_ceiling,pageNo,pageSize);
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        result.put("time",endTime - startTime);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "comment",method = RequestMethod.GET)
    public ResponseEntity<Map> getMovieByCommentRate(
            @RequestParam(value = "type",required = false,defaultValue = "positive") String type,
            @RequestParam(value = "rate",required = false,defaultValue = "0.0") float rate,
            @RequestParam(value = "pageNo",required = false,defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize
    ){
        long startTime = System.currentTimeMillis();
        Map result = movieService.getMovieByCommentRate(type, rate,pageNo,pageSize);
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        result.put("time",endTime - startTime);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "result",method = RequestMethod.POST)
    public ResponseEntity<Map> getMovieByMultipleConditions(
            @RequestBody MovieInfoDTO movieInfoDTO
    ){
        HashMap<String, Object> result = movieService.getMovieResultsByMutipleRules(movieInfoDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
