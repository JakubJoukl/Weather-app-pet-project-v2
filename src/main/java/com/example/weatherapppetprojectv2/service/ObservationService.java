package com.example.weatherapppetprojectv2.service;

import com.example.weatherapppetprojectv2.dto.currentWeather.LocationDto;
import com.example.weatherapppetprojectv2.entity.Location;
import com.example.weatherapppetprojectv2.entity.User;
import com.example.weatherapppetprojectv2.exception.LocationNotFoundException;
import com.example.weatherapppetprojectv2.exception.UserAlreadyObservesLocationException;
import com.example.weatherapppetprojectv2.exception.UserDoesNotObserveLocationException;
import com.example.weatherapppetprojectv2.mapper.LocationDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
            throw new UserAlreadyObservesLocationException(user.getUsername(), locationName);
        }
        user.getLocations().add(location);
        userService.saveUser(user);
        return locationDtoMapper.toDto(location);
    }

    @Transactional(rollbackFor = Exception.class)
    protected Location getExistingLocationByNameOrSearchAPIForNewLocation(String locationName) {
        Location location = locationService.getLocationByName(locationName)
                                            .orElseGet(() -> getLocationFromAPI(locationName));
        if(location.getId() == null) {
            location = locationService.saveLocation(location);
        }
        return location;
    }

    private Location getLocationFromAPI(String locationName) {
        List<LocationDto> locationDtos = weatherApiService.getSearchApiDtoResponse(locationName);
        if (!locationDtos.isEmpty()) {
            Location location = locationDtoMapper.toEntity(locationDtos.getFirst());
            Optional<Location> locationByApiName = locationService.getLocationByName(location.getName());
            if(locationByApiName.isPresent()) {
                location = locationByApiName.get();
            }
            return location;
        } else {
            throw new LocationNotFoundException(locationName);
        }
    }

    public LocationDto removeLocationFromObservation(String locationName) {
        User user = userService.getCurrentUser();
        Location location = user.getLocations()
                .stream()
                .filter(possibleLocation -> possibleLocation.getName().equals(locationName))
                .findFirst().orElseThrow(() -> new UserDoesNotObserveLocationException(user.getUsername(), locationName));
        user.getLocations().remove(location);
        userService.saveUser(user);
        return locationDtoMapper.toDto(location);
    }
}
