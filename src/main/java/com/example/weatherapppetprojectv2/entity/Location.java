package com.example.weatherapppetprojectv2.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "LOCATION")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String region;
    @Column
    private Double latitude;
    @Column
    private Double longitude;
    @ManyToMany(mappedBy = "locations")
    private List<User> users;
    @OneToMany(mappedBy = "location")
    private List<WeatherObservation> weatherObservations;
}
