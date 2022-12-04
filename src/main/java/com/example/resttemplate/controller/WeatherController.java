package com.example.resttemplate.controller;

import com.example.resttemplate.model.dto.WeatherDto;
import com.example.resttemplate.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("weather")
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping
    public ResponseEntity<WeatherDto> getWeather() {
        return Optional.ofNullable(weatherService.getWeather())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/forecast")
    public ResponseEntity<String> getForecast() {
        return Optional.ofNullable(weatherService.getForecast())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}
