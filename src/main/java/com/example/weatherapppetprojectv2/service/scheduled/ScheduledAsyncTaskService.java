package com.example.weatherapppetprojectv2.service.scheduled;

import com.example.weatherapppetprojectv2.aspect.LoggingAspect;
import com.example.weatherapppetprojectv2.dto.currentWeather.GetCurrentWeatherDtoResponse;
import com.example.weatherapppetprojectv2.entity.Location;
import com.example.weatherapppetprojectv2.entity.WeatherObservation;
import com.example.weatherapppetprojectv2.mapper.WeatherObservationDtoMapper;
import com.example.weatherapppetprojectv2.repository.WeatherObservationRepository;
import com.example.weatherapppetprojectv2.service.LocationService;
import com.example.weatherapppetprojectv2.service.WeatherApiService;
import com.example.weatherapppetprojectv2.service.WeatherObservationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScheduledAsyncTaskService {
    private static final Logger log = LoggerFactory.getLogger(ScheduledAsyncTaskService.class);

    private WeatherObservationService weatherObservationService;
    private LocationService locationService;
    private WeatherApiService weatherApiService;
    private WeatherObservationDtoMapper weatherObservationDtoMapper;

    @Async
    @Scheduled(cron = "0 */15 * * * *")
    public void getCurrentObservationForAllObservedLocations() {
        List<Location> locations = locationService.getAllLocations();
        locations.forEach(location -> {
            GetCurrentWeatherDtoResponse currentWeatherDtoResponse = weatherApiService.getCurrentWeatherDtoResponse(location.getName());
            WeatherObservation weatherObservation = weatherObservationDtoMapper.toEntity(currentWeatherDtoResponse.getWeatherObservationDto());
            weatherObservation.setLocation(location);
            Optional<WeatherObservation> existingWeatherObservation = weatherObservationService.getWeatherObservationByLocationNameAndMeasuredAt(location.getName(), weatherObservation.getMeasuredAt());
            if(existingWeatherObservation.isEmpty()) {
                try {
                    weatherObservationService.saveWeatherObservation(weatherObservation);
                } catch (Exception e) {
                    log.error("Error in job getCurrentObservationForAllObservedLocations for (location={}, time={})",
                              location.getName(),
                              weatherObservation.getMeasuredAt());
                }
            } else {
                log.debug("Weather observation with current timestamp and location was already saved for (location={}, time={})",
                          location.getName(),
                          weatherObservation.getMeasuredAt());
            }
        });
    }
}