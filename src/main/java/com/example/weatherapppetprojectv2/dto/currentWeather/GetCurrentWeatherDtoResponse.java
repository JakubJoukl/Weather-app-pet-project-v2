package com.example.weatherapppetprojectv2.dto.currentWeather;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false)
public class GetCurrentWeatherDtoResponse {
    @JsonProperty("current")
    private CurrentWeatherDto currentWeatherDto;
    @JsonProperty("location")
    private LocationDto locationDto;
}
