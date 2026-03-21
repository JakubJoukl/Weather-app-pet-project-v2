package com.example.weatherapppetprojectv2.service;

import com.example.weatherapppetprojectv2.entity.WeatherObservation;
import com.example.weatherapppetprojectv2.repository.WeatherObservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WeatherObservationService {
    private final WeatherObservationRepository weatherObservationRepository;

    public Optional<WeatherObservation> getWeatherObservationByLocationNameAndMeasuredAt(String locationName, Instant measuredAt) {
        return weatherObservationRepository.getWeatherObservationByLocation_NameAndMeasuredAt(locationName, measuredAt);
    }

    public WeatherObservation saveWeatherObservation(WeatherObservation weatherObservation) {
        return weatherObservationRepository.save(weatherObservation);
    }
}
