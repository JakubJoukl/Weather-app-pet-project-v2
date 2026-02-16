package com.example.weatherapppetprojectv2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//TODO omezit sloupce pomoci constraint
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER", indexes = {
        @Index(name = "username_index", columnList = "username"),
        @Index(name = "email_index", columnList = "email")
})
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String firstName;
    @Column
    private String middleName;
    @Column
    private String surname;
    //TODO rename keys
    @ManyToMany
    @JoinTable(
        name = "USER_LOCATION",
        joinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_id_key")),
        inverseJoinColumns = @JoinColumn(name = "location_id", foreignKey = @ForeignKey(name = "location_id_key"))
    )
    private List<Location> locations;
}
