package com.example.weatherapppetprojectv2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//TODO omezit sloupce pomoci constraint
@Data
@NoArgsConstructor
@AllArgsConstructor
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
