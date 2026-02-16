package com.example.weatherapppetprojectv2.mapper;

import com.example.weatherapppetprojectv2.dto.UserLoginDtoResponse;
import com.example.weatherapppetprojectv2.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserLoginResponseMapper {

    //zbytecne
    @Mapping(source = "username", target = "username")
    UserLoginDtoResponse toDto(User user);

    User toEntity(UserLoginDtoResponse dto);
}
