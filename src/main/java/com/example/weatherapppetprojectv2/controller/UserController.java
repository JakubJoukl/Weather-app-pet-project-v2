package com.example.weatherapppetprojectv2.controller;

import com.example.weatherapppetprojectv2.dto.AddUserDto;
import com.example.weatherapppetprojectv2.dto.AddUserResponseDto;
import com.example.weatherapppetprojectv2.dto.UserLoginDto;
import com.example.weatherapppetprojectv2.dto.UserLoginDtoResponse;
import com.example.weatherapppetprojectv2.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/v1/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginDtoResponse> loginUser(@Valid @RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity.ok(userService.loginUserAndGetToken(userLoginDto));
    }

    @PostMapping("/addUser")
    public ResponseEntity<AddUserResponseDto> addUser(@Valid @RequestBody AddUserDto addUserDto) {
        return ResponseEntity.ok(userService.addUser(addUserDto));
    }
}
