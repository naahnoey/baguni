package com.baguni.baguni.payload.request;

import jakarta.validation.constraints.NotBlank;

/*
 * 로그인 시 필요한 정보를 담은 payload
 */
public class LoginRequest {
    @NotBlank
    private String username;
    //private String email;

    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
