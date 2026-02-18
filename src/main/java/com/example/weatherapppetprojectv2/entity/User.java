package com.example.weatherapppetprojectv2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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
    @Column(unique = true)
    @NotNull
    @Length(min = 4, max = 255)
    private String username;
    @NotNull
    @Column
    private String password;
    @NotNull
    @Email
    @Column(unique = true)
    private String email;
    @Column
    @NotBlank
    private String firstName;
    @Column
    private String middleName;
    @NotBlank
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
