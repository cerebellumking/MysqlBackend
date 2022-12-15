package cn.edu.tongji.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "view_director_actor_time", schema = "test_amazon", catalog = "")
public class ViewDirectorActorTime {
    @Id
    @Basic
    @Column(name = "director_name")
    private String directorName;
    @Basic
    @Column(name = "actor_name")
    private String actorName;
    @Basic
    @Column(name = "coop_time")
    private long coopTime;

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public long getCoopTime() {
        return coopTime;
    }

    public void setCoopTime(long coopTime) {
        this.coopTime = coopTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewDirectorActorTime that = (ViewDirectorActorTime) o;
        return coopTime == that.coopTime && Objects.equals(directorName, that.directorName) && Objects.equals(actorName, that.actorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directorName, actorName, coopTime);
    }
}
