package com.example.weatherapppetprojectv2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

//TODO omezit sloupce pomoci constraint
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "WEATHER_OBSERVATION")
public class WeatherObservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Instant measuredAt;
    @Column
    private Double temperature;
    @Column
    private Double windSpeed;
    @Column
    private String windDirection;
    @Column
    private Double humidity;
    //TODO rename constraint
    @ManyToOne
    @JoinColumn(name = "location_id", foreignKey = @ForeignKey(name = "location_id_key_weather_observation"))
    private Location location;
}
