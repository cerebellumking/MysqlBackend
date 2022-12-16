package cn.edu.tongji.service.impl;

import cn.edu.tongji.MovieInfoDTO;
import cn.edu.tongji.entity.*;
import cn.edu.tongji.repository.*;
import cn.edu.tongji.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static java.lang.Math.min;

@Service
public class MovieServiceImpl implements MovieService {
    private static final Pattern alphaPattern = Pattern.compile(".*[a-zA-Z]+.*");
    @Resource
    private TimeEntityRepository timeEntityRepository;
    @Resource
    private MovieEntityRepository movieEntityRepository;
    @Resource
    private DirectorEntityRepository directorEntityRepository;
    @Resource
    private ActorEntityRepository actorEntityRepository;
    @Resource
    private DirectorActorCoopRepository directorActorCoopRepository;
    @Resource
    private ActorCoopRepository actorCoopRepository;
    @Resource
    private StyleEntityRepository styleEntityRepository;
    @Resource
    private ScoreEntityRepository scoreEntityRepository;
    @Resource
    private FormatEntityRepository formatEntityRepository;

    @Override
    public int getMovieNumByTime(int year) {
        return timeEntityRepository.countMovieNumByYear(year);
    }

    @Override
    public List<MovieEntity> getMoviesByName(String movieName, Integer pageNo, Integer pageSize){
        //List<HashMap<String,Object>> result=new ArrayList<>();
        /**
         *  分页参数Pageable
         *      参数1：查询的页码
         *      参数2：每页查询的条数
         *      参数3：查询结果的排序规则（可选
         */
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        //return movieEntityRepository.findByMovieNameLike(movieName+"%");
        return movieEntityRepository.findAllByMovieNameStartingWith(movieName, pageable);

    }



    @Override
    public Map<String,Object> getDirectorWorks(String directorName, Integer pageNo, Integer pageSize){
        Map<String ,Object> result=new HashMap<>();
        List<Object> movies = new ArrayList<>();
        List<DirectorEntity> directorEntities = directorEntityRepository.findByDirector(directorName);

        result.put("director_name", directorName);
        result.put("movieNum",directorEntities.size());
        for(DirectorEntity directorEntity:directorEntities){
            MovieEntity movieEntity=movieEntityRepository.findById(directorEntity.getMovieId()).orElse(null);
            movies.add(movieEntity.getMovieName());
        }
        result.put("movies",movies);

        return result;
    }

    @Override
    public List<String> getMovieNameByStr(String movieName){
        Pageable pageable = PageRequest.of(0, 15);
        List<MovieEntity> movieEntities = new ArrayList<>();
        if (movieName == null || !alphaPattern.matcher(movieName).matches()) {
            movieEntities = movieEntityRepository.findAllByMovieNameStartingWith("A", pageable);
        } else {
            movieEntities = movieEntityRepository.findAllByMovieNameStartingWith(movieName, pageable);
        }
        List<String> movieNameList = movieEntities.parallelStream()
                .map(MovieEntity::getMovieName)
                .collect(Collectors.toList());
        return movieNameList;
    }

    @Override
    public List<String> getActorNameByStr(String actorName, boolean isStar){
        Pageable pageable = PageRequest.of(0, 15);
        List<ActorEntity> actorEntities = new ArrayList<>();
        if (actorName == null || !alphaPattern.matcher(actorName).matches()) {
            actorEntities = actorEntityRepository.findAllByActorNameStartingWithAndIsStar("A",isStar,pageable);
        } else {
            actorEntities = actorEntityRepository.findAllByActorNameStartingWithAndIsStar(actorName,isStar,pageable);
        }
        List<String> actorNameList = actorEntities.parallelStream()
                .map(ActorEntity::getActorName)
                .collect(Collectors.toList());
        return actorNameList;


    }

    @Override
    public List<String> getDirectorNameByStr(String directorName) {
        Pageable pageable = PageRequest.of(0, 15);
        List<DirectorEntity> directorEntities = new ArrayList<>();
        if (directorName == null || !alphaPattern.matcher(directorName).matches()) {
            directorEntities = directorEntityRepository.findAllByDirectorStartingWith("A", pageable);
        } else {
            directorEntities = directorEntityRepository.findAllByDirectorStartingWith(directorName, pageable);
        }
        List<String> directorNameList = directorEntities.parallelStream()
                .map(directorEntity -> directorEntity.getDirector())
                .collect(Collectors.toList());
        return directorNameList;
    }



    @Override
    public Map getMovieBySpecificTimeRequirement(Integer year, Integer month, Integer day, Integer season, Integer weekday, Integer pageNo, Integer pageSize){
        Specification<TimeEntity> filter = ((root,query,criteriaBuilder)->{
            List<Predicate> predicates = new ArrayList<>();
            if(year != null) {
                predicates.add(criteriaBuilder.equal(root.get("year"), year));
            }
            if(month != null) {
                predicates.add(criteriaBuilder.equal(root.get("month"),month));
            }
            if(day != null) {
                predicates.add(criteriaBuilder.equal(root.get("day"),day));
            }
            if(weekday != null) {
                predicates.add(criteriaBuilder.equal(root.get("weekday"),weekday));
            }
            if(season != null) {
                predicates.add(criteriaBuilder.equal(root.get("season"),season));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });


        List<TimeEntity> timeList = timeEntityRepository.findAll(filter);
        List<Integer> timeIdList = timeList.parallelStream()
                .map(timeEntity -> timeEntity.getTimeId())
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<MovieEntity> movieList = movieEntityRepository.findMovieEntitiesByTimeIdIn(timeIdList,pageable);

        Map result = new HashMap<>();
        result.put("movieList",movieList.getContent());
        result.put("totalMovie",movieList.getTotalElements());
        return result;
    }

    @Override
    public Map<String,Object> getActorWorks(String actorName){
        Map<String ,Object> result=new HashMap<>();
        List<Object> isStarMovies = new ArrayList<>();
        List<Object> otherMovies = new ArrayList<>();
        List<ActorEntity> actorEntities = actorEntityRepository.findByActorName(actorName);

        result.put("actor_name", actorName);
        for(ActorEntity actorEntity:actorEntities){
            MovieEntity movieEntity=movieEntityRepository.findById(actorEntity.getMovieId()).orElse(null);
            if(actorEntity.isStar()){
                isStarMovies.add(movieEntity.getMovieName());
            }
            else{
                otherMovies.add(movieEntity.getMovieName());
            }

        }
        result.put("is_star_movieNum", isStarMovies.size());
        result.put("is_star_movies",isStarMovies);
        result.put("not_star_movieNum", otherMovies.size());
        result.put("not_star_movies",otherMovies);

        return result;
    }

    @Override
    public Map getDirectorActorCoop(Integer pageNo, Integer pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<ViewDirectorActorTime> coopList = directorActorCoopRepository.findAll(pageable);
        Map result = new HashMap<>();
        result.put("cooperation",coopList.getContent());
        return result;
    }

    @Override
    public Map getActorCoop(Integer pageNo, Integer pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<ViewActorActorTime> coopList = actorCoopRepository.findAll(pageable);
        Map result = new HashMap<>();
        result.put("cooperation",coopList.getContent());
        return result;
    }

    @Override
    public Map getMovieByStyles(String style_1, String style_2, Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<Integer> movieIdlist = new ArrayList<>();
        Map result = new HashMap<>();
        if (style_1 !=null && style_2 != null){
            movieIdlist = styleEntityRepository.findMovieIdListByMovieStyle(style_1);
            movieIdlist.retainAll(styleEntityRepository.findMovieIdListByMovieStyle(style_2));
        }
        else if (style_1 == null && style_2 == null)
        {
            result.put("error","未加入风格限制条件");
            return result;
        }
        else if(style_1 == null){
            movieIdlist = styleEntityRepository.findMovieIdListByMovieStyle(style_2);
        }
        else{
            movieIdlist = styleEntityRepository.findMovieIdListByMovieStyle(style_1);
        }
        Page<MovieEntity> movieList = movieEntityRepository.findMovieEntitiesByTimeIdIn(movieIdlist,pageable);
        result.put("movieList",movieList.getContent());
        result.put("totalMovie",movieList.getTotalElements());
        return result;
    }

    @Override
    public Map getMovieByScores(float score_floor, float score_ceiling, Integer pageNo, Integer pageSize) {
        Specification<ScoreEntity> filter = ((root, query, criteriaBuilder)->{
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.ge(root.get("movieScore"), score_floor));
            predicates.add(criteriaBuilder.le(root.get("movieScore"), score_ceiling));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
        List<ScoreEntity> scoreEntityList = scoreEntityRepository.findAll(filter);
        List<Integer> movieIdList = scoreEntityList.parallelStream()
                .map(scoreEntity -> scoreEntity.getMovieId())
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<MovieEntity> movieEntityList = movieEntityRepository.findMovieEntitiesByMovieIdIn(movieIdList,pageable);

        Map result = new HashMap<>();
        result.put("movieList",movieEntityList.getContent());
        result.put("totalMovie",movieEntityList.getTotalElements());
        return result;
    }

    @Override
    public Map getMovieByCommentRate(String type, float proportion, Integer pageNo, Integer pageSize){
        Map result = new HashMap<>();
        Specification<ScoreEntity> filter = ((root, query, criteriaBuilder)->{
            List<Predicate> predicates = new ArrayList<>();
            if(type.equals("positive"))
            {
                predicates.add(criteriaBuilder.gt(root.get("positiveRate"), proportion));
            }
            else if(type.equals("negative"))
            {
                predicates.add(criteriaBuilder.gt(root.get("negativeRate"), proportion));
            }
            else{
                result.put("error","type参数错误");
                return null;
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
        List<ScoreEntity> scoreEntityList = scoreEntityRepository.findAll(filter);
        List<Integer> movieIdList = scoreEntityList.parallelStream()
                .map(scoreEntity -> scoreEntity.getMovieId())
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<MovieEntity> movieEntityList = movieEntityRepository.findMovieEntitiesByMovieIdIn(movieIdList,pageable);

        result.put("movieList",movieEntityList.getContent());
        result.put("totalMovie",movieEntityList.getTotalElements());
        return result;
    }

    @Override
    public HashMap<String, Object> getMovieResultsByMutipleRules(MovieInfoDTO movieInfoDTO) {
        HashMap<String,Object> result = new HashMap<>();
        long startTime = System.currentTimeMillis();


//        List<StyleEntity> styleEntityList =  new ArrayList<>();
        Set<Integer> resultMovieIdList = new HashSet<>();

        //当前已经得到的筛选条件数
        Integer rulesNumber = 0;
        //电影名称查询
        if(movieInfoDTO.getMovieName() != null){
            rulesNumber++;
            for(MovieEntity movieEntity:movieEntityRepository.findMovieEntitiesByMovieName(movieInfoDTO.getMovieName())){
                resultMovieIdList.add(movieEntity.getMovieId());
            }
        }
        //电影类别查询
        if(movieInfoDTO.getStyle() != null){
            List<Integer> movieIdList = styleEntityRepository.findMovieIdListByMovieStyle(movieInfoDTO.getStyle());
            Set<Integer> movieIdOfStyle = new HashSet<>(movieIdList);

            if(rulesNumber != 0){
                resultMovieIdList.retainAll(movieIdOfStyle);
            }
            else {
                resultMovieIdList = movieIdOfStyle;
            }
            rulesNumber++;
        }

        // 电影导演查询
        if(movieInfoDTO.getDirectorNames() != null){
            List<DirectorEntity> directorMovieEntities = new ArrayList<>();
            Set<Integer> movieIdOfDirector = new HashSet<>();
            //筛选出导演实体
            for(int i =0;i<movieInfoDTO.getDirectorNames().size();i++){
                if(i==0){
                    directorMovieEntities = directorEntityRepository.findDirectorEntitiesByDirectorName(movieInfoDTO.getDirectorNames().get(i));
                    movieIdOfDirector = directorMovieEntities.parallelStream()
                            .map(directorEntity -> directorEntity.getMovieId())
                            .collect(Collectors.toSet());
                }
                else{
                    directorMovieEntities = directorEntityRepository.findDirectorEntitiesByDirectorName(movieInfoDTO.getDirectorNames().get(i));
                    Set<Integer> movieIdSet = directorMovieEntities.parallelStream()
                            .map(directorEntity -> directorEntity.getMovieId())
                            .collect(Collectors.toSet());
                    movieIdOfDirector.retainAll(movieIdSet);
                }
            }
            if(rulesNumber!=0){
                resultMovieIdList.retainAll(movieIdOfDirector);
            }
            else {
                resultMovieIdList = movieIdOfDirector;
            }
            rulesNumber++;
        }

        //主演查询
        if(movieInfoDTO.getMainActors() != null){
            Set<Integer> movieIdOfMainActors = new HashSet<>();
            for(int i=0; i<movieInfoDTO.getMainActors().size();i++){
                if(i == 0){
                    List<Integer> movieIdList = actorEntityRepository.findMovieIdListByStarName(movieInfoDTO.getMainActors().get(i));
                    movieIdOfMainActors = new HashSet<>(movieIdList);
                }
                else{
                    List<Integer> movieIdList = actorEntityRepository.findMovieIdListByStarName(movieInfoDTO.getMainActors().get(i));
                    movieIdOfMainActors.retainAll(new HashSet<>(movieIdList));
                }
            }
            //前面已经有条件，直接进行交集
            if(rulesNumber != 0){
                resultMovieIdList.retainAll(movieIdOfMainActors);
            }
            else{
                resultMovieIdList = movieIdOfMainActors;
            }
            rulesNumber++;
        }

        //演员查询
        if(movieInfoDTO.getActors() != null){
            Set<Integer> movieIdOfActors = new HashSet<>();
            for(int i=0; i<movieInfoDTO.getActors().size();i++){
                if(i == 0){
                    List<Integer> movieIdList = actorEntityRepository.findMovieIdListByNormalActorName(movieInfoDTO.getActors().get(i));
                    movieIdOfActors = new HashSet<>(movieIdList);
                }
                else{
                    List<Integer> movieIdList = actorEntityRepository.findMovieIdListByNormalActorName(movieInfoDTO.getActors().get(i));
                    movieIdOfActors.retainAll(new HashSet<>(movieIdList));
                }
            }
            //前面已经有条件，直接进行交集
            if(rulesNumber != 0){
                resultMovieIdList.retainAll(movieIdOfActors);
            }
            else{
                resultMovieIdList = movieIdOfActors;
            }
            rulesNumber++;
        }

        //按照分数最大最小值查找 需要给默认值 min默认0 max默认5
        if(movieInfoDTO.getMinScore() != null && movieInfoDTO.getMaxScore() !=null){
            Specification<ScoreEntity> filter = ((root, query, criteriaBuilder)->{
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.ge(root.get("movieScore"), Double.parseDouble(movieInfoDTO.getMinScore()) ));
                predicates.add(criteriaBuilder.le(root.get("movieScore"), Double.parseDouble(movieInfoDTO.getMaxScore()) ));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            });
            List<ScoreEntity> scoreEntityList = scoreEntityRepository.findAll(filter);
            Set<Integer> movieIdSet = scoreEntityList.parallelStream()
                    .map(scoreEntity -> scoreEntity.getMovieId())
                    .collect(Collectors.toSet());

            if(rulesNumber != 0){
                resultMovieIdList.retainAll(movieIdSet);
            }
            else {
                resultMovieIdList = movieIdSet;
            }
            rulesNumber++;
        }

        //按照正面评论比例查找 正面评价在positive之上
        if(movieInfoDTO.getPositive() != null){
            Specification<ScoreEntity> filter = ((root, query, criteriaBuilder)->{
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.ge(root.get("positiveRate"), movieInfoDTO.getPositive()/100));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            });


            List<ScoreEntity> scoreEntityList = scoreEntityRepository.findAll(filter);
            Set<Integer> movieIdSet = scoreEntityList.parallelStream()
                    .map(scoreEntity -> scoreEntity.getMovieId())
                    .collect(Collectors.toSet());

            if(rulesNumber != 0){
                resultMovieIdList.retainAll(movieIdSet);
            }
            else {
                resultMovieIdList = movieIdSet;
            }
            rulesNumber++;
        }


        //按照日期查询
        if(movieInfoDTO.getMinDay() != null){//由于前端是选择时间段，当这个参数不为空时，六个参数都不为空

            //获取最小日期的str
            String minDateStr = movieInfoDTO.getMinYear().toString()+"-"+
                    (movieInfoDTO.getMinMonth()<10?"0"+movieInfoDTO.getMinMonth().toString():movieInfoDTO.getMinMonth().toString())+
                    "-"+(movieInfoDTO.getMinDay()<10?"0"+movieInfoDTO.getMinDay().toString():movieInfoDTO.getMinDay().toString())
                    +" 00:00:00";
            //获取最大日期的str
            String maxDateStr = movieInfoDTO.getMaxYear().toString()+"-"+
                    (movieInfoDTO.getMaxMonth()<10?"0"+movieInfoDTO.getMaxMonth().toString():movieInfoDTO.getMaxMonth().toString())+
                    "-"+(movieInfoDTO.getMaxDay()<10?"0"+movieInfoDTO.getMaxDay().toString():movieInfoDTO.getMaxDay().toString())
                    +" 00:00:00";

            Timestamp minDate = Timestamp.valueOf(minDateStr);
            Timestamp maxDate = Timestamp.valueOf(maxDateStr);


            List<TimeEntity> timeMovieEntities = timeEntityRepository.findAllByMovieTimeAfterAndMovieTimeBefore(minDate,maxDate);

            List<Integer> timeIdList = timeMovieEntities.parallelStream()
                    .map(timeEntity -> timeEntity.getTimeId())
                    .collect(Collectors.toList());

            Set<Integer> movieIdOfDate = new HashSet<>(movieEntityRepository.findMovieIdByTimeIdIn(timeIdList));


            if(rulesNumber != 0){
                resultMovieIdList.retainAll(movieIdOfDate);
            }
            else {
                resultMovieIdList = movieIdOfDate;
            }
        }

        long endTime = System.currentTimeMillis();
        //查询完毕
        //开始输出结果
        List<HashMap<String, Object>> movieResult = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 10);//暂未确定传参方法
        Page<MovieEntity> movieEntityPage = movieEntityRepository.findMovieEntitiesByMovieIdIn(new ArrayList<Integer>(resultMovieIdList), pageable);

        result.put("totalMovieNum",movieEntityPage.getTotalElements());
        List<MovieEntity> resMovieEntityList = movieEntityPage.getContent();

        for(MovieEntity movieEntity:resMovieEntityList){

            HashMap<String, Object> movieNode = new HashMap<>();//单个电影信息节点
            movieNode.put("movieName",movieEntity.getMovieName());
            movieNode.put("formatNum",movieEntity.getFormatNum());
            movieNode.put("movieStyle",styleEntityRepository.findMovieStyleListByMovieId(movieEntity.getMovieId()));
            TimeEntity movieTime = timeEntityRepository.findByTimeId(movieEntity.getTimeId());
            movieNode.put("movieTime",movieTime.getMovieTime());
            movieNode.put("director",directorEntityRepository.findDirectorByMovieId(movieEntity.getMovieId()));
            movieNode.put("mainActor",actorEntityRepository.findMainActorNameByMovieId(movieEntity.getMovieId()));
            movieNode.put("actor",actorEntityRepository.findActorNameByMovieId(movieEntity.getMovieId()));
            movieNode.put("score",movieEntity.getMovieScore());
            movieNode.put("reviewNum",movieEntity.getReviewNum());
            movieResult.add(movieNode);
        }
        result.put("movies",movieResult);
        result.put("time",endTime-startTime);
        return result;
    }


}
