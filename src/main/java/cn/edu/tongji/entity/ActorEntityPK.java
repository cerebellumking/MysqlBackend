package cn.edu.tongji.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ActorEntityPK implements Serializable {
    @Column(name = "actor_name")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String actorName;
    @Column(name = "movie_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActorEntityPK that = (ActorEntityPK) o;
        return movieId == that.movieId && Objects.equals(actorName, that.actorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorName, movieId);
    }
}
