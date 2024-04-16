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
    private Set<Category> categories;

    private Set<Day> days;

    private Time startTime;

    private Time endTime;

    private ActivityType activityType;

    public BasicUser() {}
    public BasicUser(String username, String email, String password, String realname, Integer headcount, String nickname, String address,
            Set<Category> categories, Set<Day> days, Time startTime, Time endTime, ActivityType activityType) {
        super(username, email, password, realname, headcount, nickname, address);
        this.categories = categories;
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
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

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }
}
