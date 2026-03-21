package com.example.weatherapppetprojectv2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//TODO omezit sloupce pomoci constraint
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LOCATION",
        uniqueConstraints = {@UniqueConstraint(name = "UC_LOCATION_NAME_LATITUDE_LONGITUDE", columnNames = {"NAME", "LATITUDE", "LONGITUDE"})},
        indexes = {
            @Index(name = "IDX_NAME_LATITUDE_LONGITUDE", columnList = "name, latitude, longitude"),
            @Index(name = "IDX_NAME", columnList = "name")
        }) //unique = true radši do constraint než index
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    //@Column(unique = true) Může API vrátit stejná jména lokalit, ale jiné souřadnice?
    private String name;
    @Column
    private String region;
    @Column
    private String country;
    @Column
    private Double latitude;
    @Column
    private Double longitude;
    @ManyToMany(mappedBy = "locations")
    private List<User> users = new ArrayList<>();
    @OneToMany(mappedBy = "location", orphanRemoval = true)
    private List<WeatherObservation> weatherObservations = new ArrayList<>();
}
