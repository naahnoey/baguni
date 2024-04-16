package com.baguni.baguni.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.sql.Time;
import java.util.Set;

@Entity
@Table(name = "welfareUsers",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class WelfareUser extends User {
    private String nickname;

    private String category;

    private String telephone;

    private String region;

    @Column(length = 2000)
    private String introduction;

    public WelfareUser() {}
    public WelfareUser(String username, String email, String password, String realname, Integer headcount,
                       String nickname, String category, String telephone, String region, String introduction) {
        super(username, email, password, realname, headcount);
        this.nickname = nickname;
        this.category = category;
        this.telephone = telephone;
        this.region = region;
        this.introduction = introduction;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
