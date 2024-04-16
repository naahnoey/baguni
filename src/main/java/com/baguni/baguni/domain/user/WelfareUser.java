package com.baguni.baguni.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "welfareUsers",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class WelfareUser extends User {
    private String category;

    private String telephone;

    @Column(length = 2000)
    private String introduction;

    public WelfareUser() {}
    public WelfareUser(String username, String email, String password, String realname, Integer headcount, String nickname, String address,
                       String category, String telephone, String introduction) {
        super(username, email, password, realname, headcount, nickname, address);
        this.category = category;
        this.telephone = telephone;
        this.introduction = introduction;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
