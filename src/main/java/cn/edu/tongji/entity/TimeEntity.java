package cn.edu.tongji.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_time", schema = "test_amazon", catalog = "")
public class TimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "time_id")
    private int timeId;
    @Basic
    @Column(name = "year")
    private short year;
    @Basic
    @Column(name = "month")
    private byte month;
    @Basic
    @Column(name = "day")
    private byte day;
    @Basic
    @Column(name = "season")
    private byte season;
    @Basic
    @Column(name = "weekday")
    private byte weekday;
    @Basic
    @Column(name = "movie_time")
    private Timestamp movieTime;

    public int getTimeId() {
        return timeId;
    }

    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public byte getMonth() {
        return month;
    }

    public void setMonth(byte month) {
        this.month = month;
    }

    public byte getDay() {
        return day;
    }

    public void setDay(byte day) {
        this.day = day;
    }

    public byte getSeason() {
        return season;
    }

    public void setSeason(byte season) {
        this.season = season;
    }

    public byte getWeekday() {
        return weekday;
    }

    public void setWeekday(byte weekday) {
        this.weekday = weekday;
    }

    public Timestamp getMovieTime() {
        return movieTime;
    }

    public void setMovieTime(Timestamp movieTime) {
        this.movieTime = movieTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeEntity that = (TimeEntity) o;
        return timeId == that.timeId && year == that.year && month == that.month && day == that.day && season == that.season && weekday == that.weekday && Objects.equals(movieTime, that.movieTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeId, year, month, day, season, weekday, movieTime);
    }
}
