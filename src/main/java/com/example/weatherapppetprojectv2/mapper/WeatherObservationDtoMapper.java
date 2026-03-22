package com.example.weatherapppetprojectv2.mapper;

import com.example.weatherapppetprojectv2.dto.currentWeather.WeatherObservationDto;
import com.example.weatherapppetprojectv2.entity.WeatherObservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WeatherObservationDtoMapper {
    @Mapping(source = "measuredAt", target = "measuredAt")
    @Mapping(source = "temperature", target = "temperature")
    @Mapping(source = "windSpeed", target = "windSpeed")
    @Mapping(source = "windDirection", target = "windDirection")
    @Mapping(source = "humidity", target = "humidity")
    public WeatherObservation toEntity(WeatherObservationDto WeatherObservation);

    @Mapping(source = "measuredAt", target = "measuredAt")
    @Mapping(source = "temperature", target = "temperature")
    @Mapping(source = "windSpeed", target = "windSpeed")
    @Mapping(source = "windDirection", target = "windDirection")
    @Mapping(source = "humidity", target = "humidity")
    public WeatherObservationDto toDto(WeatherObservation weatherObservation);

    public List<WeatherObservationDto> toDtoList(List<WeatherObservation> weatherObservations);
}
