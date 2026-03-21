package com.example.weatherapppetprojectv2.controller;

import com.example.weatherapppetprojectv2.dto.currentWeather.LocationDto;
import com.example.weatherapppetprojectv2.service.ObservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/v1/observation")
public class ObservationApiController {
    private ObservationService observationService;

    @PutMapping("/addObservedLocation")
    public ResponseEntity<LocationDto> addLocationToObservation(@RequestParam("locationName") String locationName) {
        LocationDto locationDto = observationService.addLocationToUserObservedLocations(locationName);
        return ResponseEntity.ok().body(locationDto);
    }
}
