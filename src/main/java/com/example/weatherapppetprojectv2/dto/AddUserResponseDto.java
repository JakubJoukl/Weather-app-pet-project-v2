package com.example.weatherapppetprojectv2.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserResponseDto {
    @NotBlank
    @Length(min = 4, max = 255)
    private String username;
    @Email
    private String email;
    @Column
    @NotBlank
    private String firstName;
    @Column
    private String middleName;
    @NotBlank
    @Column
    private String surname;
}
