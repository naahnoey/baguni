package com.baguni.baguni.domain.user;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Set;

@Entity
@Table(name = "userInfo")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private Set<Category> categories;

    private Set<Day> preferredDay;

    private Time startTime;

    private Time endTime;

    private String region;

    private ActivityType activityType;
}
