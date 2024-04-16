package com.baguni.baguni.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    List<Admin> findAll();
    //List<User> findByRole(String role);

    Optional<Admin> findByUsername(String username);

    Boolean existsByUsername(String username);
}
