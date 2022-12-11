package cn.edu.tongji.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DirectorEntityPK implements Serializable {
    @Column(name = "director")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String director;
    @Column(name = "movie_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectorEntityPK that = (DirectorEntityPK) o;
        return movieId == that.movieId && Objects.equals(director, that.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(director, movieId);
    }
}
