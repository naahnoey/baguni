package com.baguni.baguni.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @NotBlank
    @Column(name = "username", unique = true)
    private String username;

    @NotBlank
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    @Size(min = 3, max = 10, message = "이름은 3 ~ 10자여야 합니다.")
    private String realname;

    private String profile_image_url;

    @NotNull
    @ColumnDefault("1")
    private Integer headcount;

    @ColumnDefault("0")
    private Integer points;

    @NotBlank
    @Size(min = 3, max = 20, message = "이름은 3 ~ 20자")
    private String nickname;

    private String address;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @CreationTimestamp
    @Column(name = "created_at")
    final private LocalDateTime createdAt = LocalDateTime.now();

    public User() {}
    public User(String username, String email, String password, String realname, Integer headcount, String nickname, String address) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.realname = realname;
        this.headcount = headcount;
        this.nickname = nickname;
        this.address = address;
    }

    public UUID getId() {
        return id;
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

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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
    public void setHeadcount(Integer count) {
        this.headcount = count;
    }

    public Integer getPoints() {
        return points;
    }
    public void setPoints(Integer points) {
        this.points = points;
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

    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "User [id = " + id
                + ", username = " + username
                + ", email = " + email
                + ", count = " + headcount
                + ", points = " + points
                + ", role = " + role
                + ", createdAt = " + createdAt + "]";
    }
}