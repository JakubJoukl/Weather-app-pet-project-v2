package com.example.weatherapppetprojectv2.mapper;

import com.example.weatherapppetprojectv2.dto.AddUserDto;
import com.example.weatherapppetprojectv2.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddUserDtoMapper {
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "middleName", target = "middleName")
    @Mapping(source = "surname", target = "surname")
    User toEntity(AddUserDto addUserDto);
}
