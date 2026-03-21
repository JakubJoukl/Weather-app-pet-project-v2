package com.example.weatherapppetprojectv2.service;

import com.example.weatherapppetprojectv2.dto.currentWeather.LocationDto;
import com.example.weatherapppetprojectv2.entity.Location;
import com.example.weatherapppetprojectv2.entity.User;
import com.example.weatherapppetprojectv2.exception.LocationNotFoundException;
import com.example.weatherapppetprojectv2.exception.UserAlreadyObservesLocation;
import com.example.weatherapppetprojectv2.mapper.LocationDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ObservationService {
    private UserService userService;
    private LocationService locationService;
    private LocationDtoMapper locationDtoMapper;
    private WeatherApiService weatherApiService;

    @Transactional(rollbackFor = Exception.class)
    public LocationDto addLocationToUserObservedLocations(String locationName) {
        Location location = getExistingLocationByNameOrSearchAPIForNewLocation(locationName);
        User user = userService.getCurrentUser();
        if(userService.currentUserHasLocation(location)) {
            throw new UserAlreadyObservesLocation(user.getUsername(), locationName);
        }
        addLocationToUser(user, location);
        userService.saveUser(user);
        return locationDtoMapper.toDto(location);
    }

    private static void addLocationToUser(User user, Location location) {
        user.getLocations().add(location);
        location.getUsers().add(user);
    }

    private Location getExistingLocationByNameOrSearchAPIForNewLocation(String locationName) {
        return locationService.getLocationByName(locationName)
                .orElseGet(() -> getLocationFromAPI(locationName));
    }

    private Location getLocationFromAPI(String locationName) {
        List<LocationDto> locationDtos = weatherApiService.getSearchApiDtoResponse(locationName);
        if (!locationDtos.isEmpty()) {
            Location location = locationDtoMapper.toEntity(locationDtos.getFirst());
            location = locationService.saveLocation(location);
            return location;
        } else {
            throw new LocationNotFoundException(locationName);
        }
    }
}
