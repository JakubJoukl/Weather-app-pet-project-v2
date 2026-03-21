package com.example.weatherapppetprojectv2.exception;

import com.example.weatherapppetprojectv2.aspect.DoNotLog;
import lombok.Getter;

@Getter
@DoNotLog
public class LocationNotFoundException extends RuntimeException {
    private String locationName;

    public LocationNotFoundException(String locationName) {
        super("Location " + locationName + " not found");
        this.locationName = locationName;
    }
}
