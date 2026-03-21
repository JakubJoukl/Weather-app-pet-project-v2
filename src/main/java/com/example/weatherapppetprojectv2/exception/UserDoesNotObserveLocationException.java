package com.example.weatherapppetprojectv2.exception;

import com.example.weatherapppetprojectv2.aspect.DoNotLog;
import lombok.Getter;

@Getter
@DoNotLog
public class UserDoesNotObserveLocationException extends RuntimeException {
    private String username;
    private String location;

    public UserDoesNotObserveLocationException(String username, String location) {
        super("User " + username + " does not observe the location " + location);
        this.username = username;
        this.location = location;
    }
}
