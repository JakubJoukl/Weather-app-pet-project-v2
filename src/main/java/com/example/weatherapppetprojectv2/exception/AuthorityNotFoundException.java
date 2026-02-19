package com.example.weatherapppetprojectv2.exception;

import lombok.Getter;

@Getter
public class AuthorityNotFoundException extends RuntimeException {
    private final String authority;
    public AuthorityNotFoundException(String authority) {
        super("Authority " + authority + " not found");
        this.authority = authority;
    }
}
