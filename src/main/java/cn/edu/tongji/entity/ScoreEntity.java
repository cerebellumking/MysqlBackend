package cn.edu.tongji.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_score", schema = "test_amazon", catalog = "")
public class ScoreEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "movie_id")
    private int movieId;
    @Basic
    @Column(name = "movie_score")
    private double movieScore;
    @Basic
    @Column(name = "negative_rate")
    private double negativeRate;
    @Basic
    @Column(name = "positive_rate")
    private double positiveRate;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public double getMovieScore() {
        return movieScore;
    }

    public void setMovieScore(double movieScore) {
        this.movieScore = movieScore;
    }

    public double getNegativeRate() {
        return negativeRate;
    }

    public void setNegativeRate(double negativeRate) {
        this.negativeRate = negativeRate;
    }

    public double getPositiveRate() {
        return positiveRate;
    }

    public void setPositiveRate(double positiveRate) {
        this.positiveRate = positiveRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreEntity that = (ScoreEntity) o;
        return movieId == that.movieId && Double.compare(that.movieScore, movieScore) == 0 && Double.compare(that.negativeRate, negativeRate) == 0 && Double.compare(that.positiveRate, positiveRate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieScore, negativeRate, positiveRate);
    }
}
