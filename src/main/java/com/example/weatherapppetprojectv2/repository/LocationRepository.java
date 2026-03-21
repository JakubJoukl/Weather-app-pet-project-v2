package com.example.weatherapppetprojectv2.repository;

import com.example.weatherapppetprojectv2.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    public Optional<Location> getLocationByName(String locationName);
}
