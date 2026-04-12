package com.example.weatherapppetprojectv2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WeatherRequestDto {

    @NotBlank
    private String locationName;
}
