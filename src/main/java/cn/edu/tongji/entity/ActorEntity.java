package cn.edu.tongji.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_actor", schema = "test_amazon", catalog = "")
@IdClass(ActorEntityPK.class)
public class ActorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "actor_name")
    private String actorName;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "movie_id")
    private int movieId;
    @Basic
    @Column(name = "is_star")
    private boolean isStar;

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public boolean isStar() {
        return isStar;
    }

    public void setStar(boolean star) {
        isStar = star;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActorEntity that = (ActorEntity) o;
        return movieId == that.movieId && isStar == that.isStar && Objects.equals(actorName, that.actorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorName, movieId, isStar);
    }
}
