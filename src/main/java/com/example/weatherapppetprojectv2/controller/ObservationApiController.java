package com.example.weatherapppetprojectv2.controller;

import com.example.weatherapppetprojectv2.dto.PagedResponseDto;
import com.example.weatherapppetprojectv2.dto.WeatherRequestDto;
import com.example.weatherapppetprojectv2.dto.currentWeather.LocationDto;
import com.example.weatherapppetprojectv2.dto.currentWeather.WeatherObservationDto;
import com.example.weatherapppetprojectv2.service.ObservationService;
import com.example.weatherapppetprojectv2.service.WeatherObservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/v1/observation")
public class ObservationApiController {
    private ObservationService observationService;
    private WeatherObservationService weatherObservationService;

    @PutMapping("/addObservedLocation")
    public ResponseEntity<LocationDto> addLocationToObservation(@RequestParam("locationName") String locationName) {
        LocationDto locationDto = observationService.addLocationToUserObservedLocations(locationName);
        return ResponseEntity.ok().body(locationDto);
    }

    @DeleteMapping("/removeObservedLocation")
    public ResponseEntity<LocationDto> deleteLocationFromObservation(@RequestParam("locationName") String locationName) {
        LocationDto locationDto = observationService.removeLocationFromObservation(locationName);
        return ResponseEntity.ok().body(locationDto);
    }

    //TODO vracet strankovane
    @GetMapping("/getWeatherObservations")
    public ResponseEntity<PagedResponseDto<List<WeatherObservationDto>>> getWeatherObservations(@Valid @ModelAttribute WeatherRequestDto weatherRequestDto) {
        PagedResponseDto<List<WeatherObservationDto>> weatherObservationsPagedDto = weatherObservationService.getWeatherObservationsForLocation(weatherRequestDto.getLocationName(), weatherRequestDto.getPageNumber(), weatherRequestDto.getPageSize());
        return ResponseEntity.ok().body(weatherObservationsPagedDto);
    }
}
