package cn.edu.tongji.controller;

import cn.edu.tongji.entity.ActorEntity;
import cn.edu.tongji.entity.MovieEntity;
import cn.edu.tongji.service.impl.MovieServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("movie")
public class MovieController {
    /**
     * xx年，xx年xx季度，xx年xx月
     */
    @PostMapping(value = "/SpecificTimeMovie")
    @ResponseBody   //接受前端json格式的数据
    public ResponseEntity SpecificTimeMovie(
            @RequestParam("year") Integer year,
            @RequestParam(value="season",required = false,defaultValue = "0") Integer season,
            @RequestParam(value="month",required = false,defaultValue = "0")Integer month,
            @RequestParam(value="day",required = false,defaultValue = "0")Integer day
    ){
        //TODO:对年进行查询
        if(year<1900||year>2030){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok("");
    }


    @Resource
    private MovieServiceImpl movieService;
    @GetMapping("time")
    public ResponseEntity<Integer> getMovieNumByTime(@RequestParam("year")int year){
        long startTime = System.currentTimeMillis();
        int result = movieService.getMovieNumByTime(year);
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        return ResponseEntity.ok(result);
    }

    /**
     * @param movieName
     * @return
     */
    @GetMapping("")
    public ResponseEntity getMovieByName(@RequestParam("movieName")String movieName,
                                         @RequestParam(value = "pageNo",required = false,defaultValue = "0")Integer pageNo,
                                         @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pageSize){
        long startTime = System.currentTimeMillis();


        List<MovieEntity> result = movieService.getMoviesByName(movieName, pageNo, pageSize);

        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        return ResponseEntity.ok(result);
    }

    /**
     * 导演共有多少部电影，电影名称
     * @param directorName
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping(value="/directorWorks")
    public ResponseEntity getMovieByDirector(@RequestParam(value = "directorName") String directorName,
                                             @RequestParam(value = "pageNo",required = false,defaultValue = "0")Integer pageNo,
                                             @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pageSize){
        long startTime = System.currentTimeMillis();

        Map<String,Object> result = movieService.getDirectorWorks(directorName,pageNo,pageSize);

        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        result.put("time",(endTime - startTime));
        return ResponseEntity.ok(result);
    }

    /**
     * 根据演员名称查询参演电影和主演电影信息
     * @param actorName
     * @return
     */
    @GetMapping(value="/actorWorks")
    public ResponseEntity getMovieByActor(@RequestParam(value = "actorName") String actorName){
        long startTime = System.currentTimeMillis();

        //List<ActorEntity> result = movieService.getMovieByActor(actorName);
        Map<String,Object> result = movieService.getActorWorks(actorName);

        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        result.put("time",(endTime - startTime));
        return ResponseEntity.ok(result);
    }

    /**
     * 查询导演和演员合作次数
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("DirectorActorCoop")
    public ResponseEntity getDirectorActorCoop(
                                         @RequestParam(value = "pageNo",required = false,defaultValue = "0")Integer pageNo,
                                         @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pageSize){
        long startTime = System.currentTimeMillis();


        Map result = movieService.getDirectorActorCoop(pageNo, pageSize);

        long endTime = System.currentTimeMillis();
        result.put("time",(endTime - startTime));
        return ResponseEntity.ok(result);
    }

    @GetMapping("ActorCoop")
    public ResponseEntity getActorCoop(
            @RequestParam(value = "pageNo",required = false,defaultValue = "0")Integer pageNo,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pageSize){
        long startTime = System.currentTimeMillis();


        Map result = movieService.getActorCoop(pageNo, pageSize);

        long endTime = System.currentTimeMillis();
        result.put("time",(endTime - startTime));
        return ResponseEntity.ok(result);
    }


}
