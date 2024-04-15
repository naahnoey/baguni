package com.baguni.baguni.payload.response;

import com.baguni.baguni.domain.user.UserRole;

import java.util.List;
import java.util.UUID;

public class UserInfoResponse {
    private UUID id;
    private String username;
    private String email;
    private List<String> role;

    public UserInfoResponse(UUID id, String username, String email, List<String> role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRole() {
        return role;
    }
}
