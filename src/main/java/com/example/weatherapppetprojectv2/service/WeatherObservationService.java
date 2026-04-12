package com.example.weatherapppetprojectv2.service;

import com.example.weatherapppetprojectv2.dto.PagedResponseDto;
import com.example.weatherapppetprojectv2.dto.PagingDetailDto;
import com.example.weatherapppetprojectv2.dto.WeatherRequestDto;
import com.example.weatherapppetprojectv2.dto.currentWeather.WeatherObservationDto;
import com.example.weatherapppetprojectv2.entity.WeatherObservation;
import com.example.weatherapppetprojectv2.mapper.WeatherObservationDtoMapper;
import com.example.weatherapppetprojectv2.repository.WeatherObservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WeatherObservationService {
    private final WeatherObservationRepository weatherObservationRepository;
    private final WeatherObservationDtoMapper weatherObservationDtoMapper;

    public Optional<WeatherObservation> getWeatherObservationByLocationNameAndMeasuredAt(String locationName, Instant measuredAt) {
        return weatherObservationRepository.getWeatherObservationByLocation_NameAndMeasuredAt(locationName, measuredAt);
    }

    public WeatherObservation saveWeatherObservation(WeatherObservation weatherObservation) {
        return weatherObservationRepository.save(weatherObservation);
    }

    //TODO na strankovani (i se sort) nejaka helper metoda
    public PagedResponseDto<List<WeatherObservationDto>> getWeatherObservationsForLocation(String locationName, Pageable pageable) {
        Page<WeatherObservation> weatherObservationPage = weatherObservationRepository.getAllByLocation_Name(locationName, pageable);
        List<WeatherObservationDto> weatherObservationDtos = weatherObservationDtoMapper.toDtoList(weatherObservationPage.getContent());
        PagingDetailDto pagingDetailDto = new PagingDetailDto(weatherObservationPage);
        return new PagedResponseDto<>(weatherObservationDtos, pagingDetailDto);
    }
}
