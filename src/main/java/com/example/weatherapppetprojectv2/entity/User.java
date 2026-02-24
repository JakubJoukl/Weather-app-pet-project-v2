package com.example.weatherapppetprojectv2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//TODO omezit sloupce pomoci constraint
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER", indexes = {
        @Index(name = "username_index", columnList = "username"),
        @Index(name = "email_index", columnList = "email")
})
@Entity
//TODO přidat práva
public class User implements UserDetails {
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
    private List<Location> locations = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_authority_key")),
            inverseJoinColumns = @JoinColumn(name = "authority_id", foreignKey = @ForeignKey(name = "authority_user_key")),
            uniqueConstraints = {@UniqueConstraint(name = "UC_USER_AUTHORITY", columnNames = {"user_id", "authority_id"})}
    )
    private List<Authority> authorities = new ArrayList<>();
}
