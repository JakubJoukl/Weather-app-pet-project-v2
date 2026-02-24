package com.example.weatherapppetprojectv2.service;

import com.example.weatherapppetprojectv2.entity.Authority;
import com.example.weatherapppetprojectv2.exception.AuthorityNotFoundException;
import com.example.weatherapppetprojectv2.repository.AuthorityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    public Authority getAuthorityByAuthority(String authority) {
        return authorityRepository.getAuthorityByAuthority(authority).orElseThrow(() -> new AuthorityNotFoundException(authority));
    }
}
