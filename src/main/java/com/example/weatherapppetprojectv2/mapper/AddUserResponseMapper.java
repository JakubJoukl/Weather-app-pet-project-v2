package com.example.weatherapppetprojectv2.mapper;

import com.example.weatherapppetprojectv2.dto.AddUserResponseDto;
import com.example.weatherapppetprojectv2.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddUserResponseMapper {
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "middleName", target = "middleName")
    @Mapping(source = "surname", target = "surname")
    AddUserResponseDto toDto(User user);
}
