package cn.edu.tongji.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_style", schema = "test_amazon", catalog = "")
@IdClass(StyleEntityPK.class)
public class StyleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "movie_id")
    private int movieId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "movie_style")
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
        StyleEntity that = (StyleEntity) o;
        return movieId == that.movieId && Objects.equals(movieStyle, that.movieStyle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieStyle);
    }
}
