package cn.edu.tongji.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FormatEntityPK implements Serializable {
    @Column(name = "movie_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;
    @Column(name = "movie_format")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String movieFormat;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieFormat() {
        return movieFormat;
    }

    public void setMovieFormat(String movieFormat) {
        this.movieFormat = movieFormat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormatEntityPK that = (FormatEntityPK) o;
        return movieId == that.movieId && Objects.equals(movieFormat, that.movieFormat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieFormat);
    }
}
