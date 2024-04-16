package com.baguni.baguni.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.sql.Time;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "admins",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public Admin() {}
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
        role = UserRole.ROLE_ADMIN;
    }
}
