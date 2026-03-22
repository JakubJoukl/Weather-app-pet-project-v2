package com.example.weatherapppetprojectv2.repository;

import com.example.weatherapppetprojectv2.entity.WeatherObservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;

public interface WeatherObservationRepository extends JpaRepository<WeatherObservation, Long> {
    Optional<WeatherObservation> getWeatherObservationByLocation_NameAndMeasuredAt(String locationName, Instant measuredAt);
    Page<WeatherObservation> getAllByLocation_Name(String locationName, Pageable pageable);
}
