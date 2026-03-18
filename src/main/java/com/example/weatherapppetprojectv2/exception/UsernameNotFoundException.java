package com.example.weatherapppetprojectv2.exception;

import com.example.weatherapppetprojectv2.aspect.DoNotLog;
import lombok.Getter;

@Getter
@DoNotLog
public class UsernameNotFoundException extends RuntimeException {
    private String username;

    public UsernameNotFoundException(String username) {
        super("User with username " + username + " not found");
        this.username = username;
    }
}
