package cn.edu.tongji.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_format", schema = "test_amazon", catalog = "")
@IdClass(FormatEntityPK.class)
public class FormatEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "movie_id")
    private int movieId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "movie_format")
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
        FormatEntity that = (FormatEntity) o;
        return movieId == that.movieId && Objects.equals(movieFormat, that.movieFormat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieFormat);
    }
}
