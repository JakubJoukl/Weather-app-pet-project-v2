package com.example.weatherapppetprojectv2.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@AllArgsConstructor
@Getter
public class WeatherRequestDto {

    @NotBlank
    private String locationName;

    @Min(0)
    private int pageNumber;

    @Range(min = 0, max = 100)
    private int pageSize;
}
