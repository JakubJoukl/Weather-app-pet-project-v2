package com.example.weatherapppetprojectv2.repository;

import com.example.weatherapppetprojectv2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserById(Long id);
    Boolean existsUserByUsernameOrEmail(String username, String email);
}
