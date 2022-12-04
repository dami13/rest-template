package com.example.resttemplate.webclient;

import com.example.resttemplate.model.dto.WeatherDto;
import com.example.resttemplate.webclient.dto.OpenWeatherDto;
import com.example.resttemplate.webclient.mapper.OpenWeatherMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherClient {
    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String DEFAULT_PARAMS = "&units=metric&lang=pl";

    @Value("${weather.api.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherDto getWeatherForCity(String city) {
        OpenWeatherDto openWeatherDto = callGetMethod("weather?appid={appiKey}&q={city}", OpenWeatherDto.class, apiKey, city);
        return OpenWeatherMapper.map(openWeatherDto);
    }

    public String getForecast(double lat, double lon) {
        return callGetMethod("onecall?appid={appiKey}&lat={lat}&lon={lon}&exclude=minutely,hourly", String.class, apiKey, lat, lon);
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object...objects) {
        return restTemplate.getForObject(WEATHER_URL + url + DEFAULT_PARAMS, responseType, objects);
    }
}
