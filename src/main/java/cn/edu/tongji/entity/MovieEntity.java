package cn.edu.tongji.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_movie", schema = "test_amazon", catalog = "")
public class MovieEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "movie_id")
    private int movieId;
    @Basic
    @Column(name = "movie_name")
    private String movieName;
    @Basic
    @Column(name = "movie_asin")
    private String movieAsin;
    @Basic
    @Column(name = "format_num")
    private byte formatNum;
    @Basic
    @Column(name = "time_id")
    private int timeId;
    @Basic
    @Column(name = "review_num")
    private int reviewNum;
    @Basic
    @Column(name = "movie_time")
    private Timestamp movieTime;
    @Basic
    @Column(name = "movie_score")
    private Double movieScore;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieAsin() {
        return movieAsin;
    }

    public void setMovieAsin(String movieAsin) {
        this.movieAsin = movieAsin;
    }

    public byte getFormatNum() {
        return formatNum;
    }

    public void setFormatNum(byte formatNum) {
        this.formatNum = formatNum;
    }

    public int getTimeId() {
        return timeId;
    }

    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }

    public int getReviewNum() {
        return reviewNum;
    }

    public void setReviewNum(int reviewNum) {
        this.reviewNum = reviewNum;
    }

    public Timestamp getMovieTime() {
        return movieTime;
    }

    public void setMovieTime(Timestamp movieTime) {
        this.movieTime = movieTime;
    }

    public Double getMovieScore() {
        return movieScore;
    }

    public void setMovieScore(Double movieScore) {
        this.movieScore = movieScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieEntity that = (MovieEntity) o;
        return movieId == that.movieId && formatNum == that.formatNum && timeId == that.timeId && reviewNum == that.reviewNum && Objects.equals(movieName, that.movieName) && Objects.equals(movieAsin, that.movieAsin) && Objects.equals(movieTime, that.movieTime) && Objects.equals(movieScore, that.movieScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieName, movieAsin, formatNum, timeId, reviewNum, movieTime, movieScore);
    }
}
