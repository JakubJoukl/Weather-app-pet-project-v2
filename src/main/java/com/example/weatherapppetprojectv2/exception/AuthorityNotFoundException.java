package com.example.weatherapppetprojectv2.exception;

import com.example.weatherapppetprojectv2.aspect.DoNotLog;
import lombok.Getter;

@Getter
@DoNotLog
public class AuthorityNotFoundException extends RuntimeException {
    private final String authority;
    public AuthorityNotFoundException(String authority) {
        super("Authority " + authority + " not found");
        this.authority = authority;
    }
}
