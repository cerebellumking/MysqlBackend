package cn.edu.tongji.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class StyleEntityPK implements Serializable {
    @Column(name = "movie_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;
    @Column(name = "movie_style")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String movieStyle;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieStyle() {
        return movieStyle;
    }

    public void setMovieStyle(String movieStyle) {
        this.movieStyle = movieStyle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StyleEntityPK that = (StyleEntityPK) o;
        return movieId == that.movieId && Objects.equals(movieStyle, that.movieStyle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieStyle);
    }
}
