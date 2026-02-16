package com.example.weatherapppetprojectv2.service;

import com.example.weatherapppetprojectv2.mapper.UserLoginResponseMapper;
import com.example.weatherapppetprojectv2.dto.UserLoginDto;
import com.example.weatherapppetprojectv2.dto.UserLoginDtoResponse;
import com.example.weatherapppetprojectv2.entity.User;
import com.example.weatherapppetprojectv2.exception.UsernameNotFoundException;
import com.example.weatherapppetprojectv2.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserLoginResponseMapper userLoginResponseMapper;

    public UserService(UserRepository userRepository, UserLoginResponseMapper userLoginResponseMapper) {
        this.userRepository = userRepository;
        this.userLoginResponseMapper = userLoginResponseMapper;
    }

    public UserLoginDtoResponse loginUserAndGetToken(UserLoginDto userLoginDto) {
        User user = userRepository.getUserByUsername(userLoginDto.getUsername()).orElseThrow(() -> new UsernameNotFoundException(userLoginDto.getUsername()));
        UserLoginDtoResponse userLoginDtoResponse = userLoginResponseMapper.toDto(user);
        userLoginDtoResponse.setJwtToken(null); //TODO generate and set token
        return userLoginDtoResponse;
    }
}
