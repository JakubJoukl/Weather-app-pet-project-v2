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
public class CurrentWeatherDto {
    @JsonProperty("last_updated_epoch")
    private Instant measuredAt;
    @JsonAlias("temp_c")
    @JsonProperty("temperature")
    private Double temperature;
    @JsonProperty("wind_kph")
    private Double windSpeed;
    @JsonProperty("wind_dir")
    private String windDirection;
    @JsonProperty("humidity")
    private Double humidity;
}
