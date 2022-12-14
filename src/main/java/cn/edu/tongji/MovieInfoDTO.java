package cn.edu.tongji;

import java.util.List;

/**
 * @title: MovieInfoDTO
 * @Author Xinyu Fang
 * @Date: 2022/12/13 9:33
 * @Version 1.0
 */
public class MovieInfoDTO {
    private String movieName;
    private String category;
    private List<String> directorNames;
    private List<String> mainActors;
    private List<String> actors;
    private String minScore;
    private String maxScore;
    private Integer minYear;
    private Integer minMonth;
    private Integer minDay;
    private Integer maxYear;
    private Integer maxMonth;
    private Integer maxDay;
    private Integer positive;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getDirectorNames() {
        return directorNames;
    }

    public void setDirectorNames(List<String> directorNames) {
        this.directorNames = directorNames;
    }

    public List<String> getMainActors() {
        return mainActors;
    }

    public void setMainActors(List<String> mainActors) {
        this.mainActors = mainActors;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public String getMinScore() {
        return minScore;
    }

    public void setMinScore(String minScore) {
        this.minScore = minScore;
    }

    public String getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(String maxScore) {
        this.maxScore = maxScore;
    }

    public Integer getMinYear() {
        return minYear;
    }

    public void setMinYear(Integer minYear) {
        this.minYear = minYear;
    }

    public Integer getMinMonth() {
        return minMonth;
    }

    public void setMinMonth(Integer minMonth) {
        this.minMonth = minMonth;
    }

    public Integer getMinDay() {
        return minDay;
    }

    public void setMinDay(Integer minDay) {
        this.minDay = minDay;
    }

    public Integer getMaxYear() {
        return maxYear;
    }

    public void setMaxYear(Integer maxYear) {
        this.maxYear = maxYear;
    }

    public Integer getMaxMonth() {
        return maxMonth;
    }

    public void setMaxMonth(Integer maxMonth) {
        this.maxMonth = maxMonth;
    }

    public Integer getMaxDay() {
        return maxDay;
    }

    public void setMaxDay(Integer maxDay) {
        this.maxDay = maxDay;
    }

    public Integer getPositive() {
        return positive;
    }

    public void setPositive(Integer positive) {
        this.positive = positive;
    }
}