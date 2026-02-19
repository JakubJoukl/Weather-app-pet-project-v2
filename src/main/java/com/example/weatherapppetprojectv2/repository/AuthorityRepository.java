package com.example.weatherapppetprojectv2.repository;

import com.example.weatherapppetprojectv2.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    public Optional<Authority> getAuthorityByAuthority(String authority);
}
