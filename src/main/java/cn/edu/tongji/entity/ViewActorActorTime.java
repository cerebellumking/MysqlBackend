package cn.edu.tongji.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Author jqChen
 * @Date 2022/12/15
 * @Github https://github.com/KyrinChen
 */
@Entity
@Table(name = "view_actor_actor_time", schema = "test_amazon", catalog = "")
public class ViewActorActorTime {
    @Id
    @Basic
    @Column(name = "actor1_name")
    private String actor1Name;
    @Basic
    @Column(name = "actor2_name")
    private String actor2Name;
    @Basic
    @Column(name = "coop_time")
    private long coopTime;

    public String getActor1Name() {
        return actor1Name;
    }

    public void setActor1Name(String actor1Name) {
        this.actor1Name = actor1Name;
    }

    public String getActor2Name() {
        return actor2Name;
    }

    public void setActor2Name(String actor2Name) {
        this.actor2Name = actor2Name;
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
        ViewActorActorTime that = (ViewActorActorTime) o;
        return coopTime == that.coopTime && Objects.equals(actor1Name, that.actor1Name) && Objects.equals(actor2Name, that.actor2Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actor1Name, actor2Name, coopTime);
    }
}
