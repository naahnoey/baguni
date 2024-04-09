package com.baguni.baguni.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @NotBlank
    @Size(min = 3, max = 10, message = "이름은 3 ~ 10자여야 합니다.")
    private String username;

    @NotBlank
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @Size(min = 8, max = 13, message = "비밀번호는 8 ~ 13자")
    private String password;

    private String profile_image_url;

    private Integer points;

    @NotBlank
    private Integer count;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User() {}
    public User(String username, String email, String password, Integer count) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.count = count;
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

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [id = " + id
                + ", username = " + username
                + ", email = " + email
                + ", count = " + count
                + "role = " + role + "]";
    }
}
