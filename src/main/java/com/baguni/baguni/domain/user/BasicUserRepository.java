package com.baguni.baguni.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BasicUserRepository extends JpaRepository<BasicUser, UUID> {
    List<BasicUser> findAll();
    //List<User> findByRole(String role);

    Optional<BasicUser> findByUsername(String username);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
