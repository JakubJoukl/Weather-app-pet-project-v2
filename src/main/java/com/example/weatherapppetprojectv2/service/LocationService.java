package com.example.weatherapppetprojectv2.service;

import com.example.weatherapppetprojectv2.entity.Location;
import com.example.weatherapppetprojectv2.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationService {
    private LocationRepository locationRepository;

    public Optional<Location> getLocationByName(String locationName) {
        return locationRepository.getLocationByName(locationName);
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }
}
