package com.example.resttemplate.webclient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenWeatherDto {
    private OpenWeatherMainDto main;
    private OpenWeatherWindDto wind;
}
