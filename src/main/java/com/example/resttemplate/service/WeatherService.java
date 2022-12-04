package com.example.resttemplate.service;

import com.example.resttemplate.model.dto.WeatherDto;
import com.example.resttemplate.webclient.WeatherClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WeatherService {

    private final WeatherClient weatherClient;

    @Value("${weather.location.city}")
    private String city;

    public WeatherDto getWeather() {
        return weatherClient.getWeatherForCity(city);
    }

    public String getForecast() {
        return weatherClient.getForecast(52.23, 21.01);
    }
}
