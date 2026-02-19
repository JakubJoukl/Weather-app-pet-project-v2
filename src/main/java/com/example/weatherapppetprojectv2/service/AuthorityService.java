package com.example.weatherapppetprojectv2.service;

import com.example.weatherapppetprojectv2.entity.Authority;
import com.example.weatherapppetprojectv2.exception.AuthorityNotFoundException;
import com.example.weatherapppetprojectv2.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Authority getAuthorityByAuthority(String authority) {
        return authorityRepository.getAuthorityByAuthority(authority).orElseThrow(() -> new AuthorityNotFoundException(authority));
    }
}
