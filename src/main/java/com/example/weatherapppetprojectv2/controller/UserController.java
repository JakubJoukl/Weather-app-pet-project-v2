package com.example.weatherapppetprojectv2.controller;

import com.example.weatherapppetprojectv2.dto.AddUserDto;
import com.example.weatherapppetprojectv2.dto.AddUserResponseDto;
import com.example.weatherapppetprojectv2.dto.UserLoginDto;
import com.example.weatherapppetprojectv2.dto.UserLoginDtoResponse;
import com.example.weatherapppetprojectv2.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/v1/user")
public class UserController {
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginDtoResponse> loginUser(@Valid @RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity.ok(userService.loginUserAndGetToken(userLoginDto));
    }

    @PostMapping("/addUser")
    public ResponseEntity<AddUserResponseDto> addUser(@Valid @RequestBody AddUserDto addUserDto) {
        return ResponseEntity.ok(userService.addUser(addUserDto));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("I require auth token");
    }
}
