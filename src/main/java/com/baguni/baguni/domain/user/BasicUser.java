package com.baguni.baguni.domain.user;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Set;

@Entity
@Table(name = "basicUsers",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class BasicUser extends User {
    private String nickname;

    private Set<Category> categories;

    private Set<Day> days;

    private Time startTime;

    private Time endTime;

    private String region;

    private ActivityType activityType;

    public BasicUser() {}
    public BasicUser(String username, String email, String password, String realname, Integer headcount,
            String nickname, Set<Category> categories, Set<Day> days, Time startTime, Time endTime, String region, ActivityType activityType) {
        super(username, email, password, realname, headcount);
        this.nickname = nickname;
        this.categories = categories;
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
        this.region = region;
        this.activityType = activityType;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Day> getDays() {
        return days;
    }

    public void setDays(Set<Day> days) {
        this.days = days;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }
}
