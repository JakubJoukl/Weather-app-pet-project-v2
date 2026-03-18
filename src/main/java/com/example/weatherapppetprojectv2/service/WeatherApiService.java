package com.example.weatherapppetprojectv2.service;

import com.example.weatherapppetprojectv2.dto.currentWeather.GetCurrentWeatherDtoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class WeatherApiService {
    @Value("${weatherAPI.key}")
    private String apiKey;
    @Value("${weatherAPI.language}")
    private String language;

    @Value("${weatherAPI.param.name.query}")
    private String queryParamName;
    @Value("${weatherAPI.param.name.key}")
    private String keyParamName;
    @Value("${weatherAPI.param.name.language}")
    private String languageParamName;

    private UserService userService;
    private final String baseUrl = "https://api.weatherapi.com/v1/";
    //private final RestClient restClient = RestClient.create(baseUrl);
    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherApiService (UserService userService) {
        this.userService = userService;
    }

    //TODO tělo metody dát do samostatné service
    /* Server na to vrací 500?
    public GetCurrentWeatherDtoResponse getCurrentWeatherDtoResponse(String query) {
        test(query);
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("current.json")
                        .queryParam("q", query)
                        .queryParam("key", apiKey)
                        .queryParam("lang", language)
                        .build()
                        .normalize()
                )
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(GetCurrentWeatherDtoResponse.class);
    }
    */

    public GetCurrentWeatherDtoResponse getCurrentWeatherDtoResponse(String query) {
        String currentWeatherPath = "current.json";
        String requestUrl = UriComponentsBuilder
                .fromUri(URI.create(baseUrl + currentWeatherPath))
                .queryParam(queryParamName, query)
                .queryParam(keyParamName, apiKey)
                .queryParam(languageParamName, language)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUriString();
        return restTemplate.getForEntity(requestUrl, GetCurrentWeatherDtoResponse.class).getBody();
    }
}
