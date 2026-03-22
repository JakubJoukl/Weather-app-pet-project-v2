package com.example.weatherapppetprojectv2.service;

import com.example.weatherapppetprojectv2.entity.Authority;
import com.example.weatherapppetprojectv2.exception.AuthorityNotFoundException;
import com.example.weatherapppetprojectv2.repository.AuthorityRepository;
import com.example.weatherapppetprojectv2.repository.WeatherObservationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthorityServiceTest {

    @Mock
    private AuthorityRepository authorityRepository;

    @InjectMocks
    private AuthorityService authorityService;

    @Test
    void getAuthorityByAuthorityExisting() {
        Authority expectedAuthority = new Authority();
        expectedAuthority.setAuthority("USER");
        Mockito.when(authorityRepository.getAuthorityByAuthority("USER"))
                .thenReturn(Optional.of(expectedAuthority));
        Optional<Authority> actualAuthority = Optional.ofNullable(authorityService.getAuthorityByAuthority("USER"));
        Assertions.assertTrue(actualAuthority.isPresent());
        Assertions.assertEquals(expectedAuthority, actualAuthority.get());
    }

    @Test
    void getAuthorityByAuthorityNonExisting() {
        Mockito.when(authorityRepository.getAuthorityByAuthority("ADMIN"))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(
                AuthorityNotFoundException.class,
                () -> authorityService.getAuthorityByAuthority("ADMIN")
        );
    }
}