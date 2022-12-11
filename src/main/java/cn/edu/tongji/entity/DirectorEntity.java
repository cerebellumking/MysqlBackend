package cn.edu.tongji.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_director", schema = "test_amazon", catalog = "")
@IdClass(DirectorEntityPK.class)
public class DirectorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "director")
    private String director;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "movie_id")
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
        DirectorEntity that = (DirectorEntity) o;
        return movieId == that.movieId && Objects.equals(director, that.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(director, movieId);
    }
}
