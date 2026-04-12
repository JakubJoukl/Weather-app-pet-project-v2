package com.example.weatherapppetprojectv2.service.scheduled;

import com.example.weatherapppetprojectv2.entity.Location;
import com.example.weatherapppetprojectv2.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class ScheduledAsyncTaskService {
    private LocationService locationService;
    private AsyncLocationService asyncLocationService;

    @Scheduled(cron = "0 */15 * * * *")
    public void getCurrentObservationForAllObservedLocations() {
        List<Location> locations = locationService.getAllLocations();
        locations.forEach(asyncLocationService::processLocationAsync);
    }
}