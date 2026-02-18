package com.example.weatherapppetprojectv2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

//TODO omezit sloupce pomoci constraint
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "WEATHER_OBSERVATION",
        uniqueConstraints = {@UniqueConstraint(name = "UC_WEATHER_OBSERVATION_MEASURED_AT_LOCATION", columnNames = {"MEASURED_AT", "LOCATION_ID"})})
public class WeatherObservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull
    private Instant measuredAt;
    @Column
    private Double temperature;
    @Column
    private Double windSpeed;
    @Column
    private String windDirection;
    @Column
    private Double humidity;
    @ManyToOne
    @JoinColumn(name = "location_id", foreignKey = @ForeignKey(name = "location_id_key_weather_observation"), nullable = false)
    private Location location;
}
