package com.example.resttemplate.webclient.mapper;

import com.example.resttemplate.model.dto.WeatherDto;
import com.example.resttemplate.webclient.dto.OpenWeatherDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OpenWeatherMapper {
    public WeatherDto map(OpenWeatherDto dto) {
        return new WeatherDto()
                .setHumidity(dto.getMain().getHumidity())
                .setPressure(dto.getMain().getPressure())
                .setTemperature(dto.getMain().getTemp())
                .setWindSpeed(dto.getWind().getSpeed());
    }
}
