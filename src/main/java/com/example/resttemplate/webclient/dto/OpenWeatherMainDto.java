package com.example.resttemplate.webclient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenWeatherMainDto {
    private float temp;
    private int pressure;
    private int humidity;
}
