package com.example.weatherapppetprojectv2.exception;

import com.example.weatherapppetprojectv2.aspect.DoNotLog;
import lombok.Getter;

@Getter
@DoNotLog
public class UserAlreadyObservesLocationException extends RuntimeException {
    private String username;
    private String location;

    public UserAlreadyObservesLocationException(String username, String location) {
        super("User " + username + " already observes the location " + location);
        this.username = username;
        this.location = location;
    }
}
