package com.example.weatherapppetprojectv2.controller;

import com.example.weatherapppetprojectv2.dto.currentWeather.GetCurrentWeatherDtoResponse;
import com.example.weatherapppetprojectv2.service.WeatherApiService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/v1/weather")
public class WeatherApiController {
    private WeatherApiService weatherApiService;

    @GetMapping("/current")
    public ResponseEntity<GetCurrentWeatherDtoResponse> getCurrentWeatherDtoResponseResponseEntity(@RequestParam("query") String query) {
        return ResponseEntity.ok(weatherApiService.getCurrentWeatherDtoResponse(query));
    }
}
