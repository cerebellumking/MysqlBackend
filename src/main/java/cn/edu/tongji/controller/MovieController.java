package cn.edu.tongji.controller;
import cn.edu.tongji.MovieInfoDTO;
import cn.edu.tongji.entity.ActorEntity;
import cn.edu.tongji.entity.MovieEntity;
import cn.edu.tongji.service.impl.MovieServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("mysql/movie")
public class MovieController {

    @Resource
    private MovieServiceImpl movieService;

    @CrossOrigin("*")
    @GetMapping("time/year")
    public ResponseEntity<Integer> getMovieNumByYear(int year){
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
    @CrossOrigin("*")
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
    @CrossOrigin("*")
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
    @CrossOrigin("*")
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
    @CrossOrigin("*")
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

    @CrossOrigin("*")
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


//    @GetMapping("time/yearAndMonth")
//    public ResponseEntity<Integer> getMovieNumByYearAndMonth(int year, int month){
//        long startTime = System.currentTimeMillis();
//        int result = movieService.getMovieNumByYearAndMonth(year,month);
//        long endTime = System.currentTimeMillis();
//        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
//        return ResponseEntity.ok(result);
//    }

    @CrossOrigin("*")
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

    @CrossOrigin("*")
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

    @CrossOrigin("*")
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

    @CrossOrigin("*")
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

    @CrossOrigin("*")
    @RequestMapping(value = "association/result",method = RequestMethod.POST)
    public ResponseEntity<Map> getMovieByMultipleConditions(
            @RequestBody MovieInfoDTO movieInfoDTO
    ){
        HashMap<String, Object> result = movieService.getMovieResultsByMutipleRules(movieInfoDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @CrossOrigin("*")
    @RequestMapping(value = "association/movieName",method = RequestMethod.GET)
    public ResponseEntity<List<String>> getMovieNameListByString(
            @RequestParam(value = "movieName") String movieName
    ){
        return new ResponseEntity<>(movieService.getMovieNameByStr(movieName), HttpStatus.OK);
    }

    @CrossOrigin("*")
    @RequestMapping(value = "association/actorName",method = RequestMethod.GET)
    public ResponseEntity<Set<String>> getActorNameListByString(
            @RequestParam(value = "actorName") String actorName,
            @RequestParam(value = "isStar") boolean isStar
    ){
        return new ResponseEntity<>(new HashSet<>(movieService.getActorNameByStr(actorName,isStar)), HttpStatus.OK);
    }

    @CrossOrigin("*")
    @RequestMapping(value = "association/directorName",method = RequestMethod.GET)
    public ResponseEntity<Set<String>> getDirectorNameListByString(
            @RequestParam(value = "directorName") String directorName
    ){
        return new ResponseEntity<>(new HashSet<>(movieService.getDirectorNameByStr(directorName)), HttpStatus.OK);
    }

}
