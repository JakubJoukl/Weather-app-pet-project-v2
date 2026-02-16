package com.example.weatherapppetprojectv2.exception;

import lombok.Getter;

@Getter
public class UsernameNotFoundException extends RuntimeException {
    private String username;

    public UsernameNotFoundException(String username) {
        super("User with username " + username + " not found");
        this.username = username;
    }
}
