package com.baguni.baguni.payload.response;

import java.util.List;
import java.util.UUID;

public class UserInfoResponse {
    private UUID id;
    private String username;
    private String email;
    private String realname;
    private Integer headcount;
    private String nickname;
    private String address;
    private List<String> role;

    public UserInfoResponse(UUID id, String username, String email, String realname, Integer headcount, String nickname, String address, List<String> role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.realname = realname;
        this.headcount = headcount;
        this.nickname = nickname;
        this.address = address;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Integer getHeadcount() {
        return headcount;
    }

    public void setHeadcount(Integer headcount) {
        this.headcount = headcount;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getRole() {
        return role;
    }
}
