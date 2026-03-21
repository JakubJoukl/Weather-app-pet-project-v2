package com.example.weatherapppetprojectv2.dto.currentWeather;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false)
public class WeatherObservationDto {
    @JsonAlias("last_updated_epoch")
    @JsonProperty("measuredAt")
    private Instant measuredAt;
    @JsonAlias("temp_c")
    @JsonProperty("temperature")
    private Double temperature;
    @JsonAlias("wind_kph")
    @JsonProperty("windSpeed")
    private Double windSpeed;
    @JsonAlias("wind_dir")
    @JsonProperty("windDirection")
    private String windDirection;
    @JsonProperty("humidity")
    private Double humidity;
}
