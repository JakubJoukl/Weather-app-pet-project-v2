package com.example.weatherapppetprojectv2.exception;

import lombok.Getter;

@Getter
public class SameUsernameOrEmailUserExistsException extends RuntimeException {
    private String username;
    private String email;

    public SameUsernameOrEmailUserExistsException(String username, String email) {
        super("User with the same username or email exists");
    }
}
