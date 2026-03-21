package com.example.weatherapppetprojectv2.mapper;

import com.example.weatherapppetprojectv2.dto.currentWeather.LocationDto;
import com.example.weatherapppetprojectv2.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface LocationDtoMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "region", target = "region")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    public LocationDto toDto(Location location);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "region", target = "region")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    Location toEntity(LocationDto locationDtoResponse);
}
