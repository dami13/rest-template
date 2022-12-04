package com.example.resttemplate.controller;

import com.example.resttemplate.model.dto.WeatherDto;
import com.example.resttemplate.service.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    WeatherService weatherService;

    @Test
     void isValid_getWeather_returnOk() throws Exception {
        // given
        var weatherDto = getCustomWeatherDto();
        Mockito.when(weatherService.getWeather())
                .thenReturn(weatherDto);
        
        // when
        MvcResult result = mockMvc.perform(get("/weather"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        // then
        Mockito.verify(weatherService, Mockito.only()).getWeather();
        var responseDto = objectMapper.readValue(result.getResponse().getContentAsString(), WeatherDto.class);
        assertThat(Objects.nonNull(responseDto)).isTrue();
    }

    @Test
     void isNull_getWeather_returnNoContent() throws Exception {
        // given
        var weatherDto = getCustomWeatherDto();
        Mockito.when(weatherService.getWeather())
                .thenReturn(null);

        // when
        MvcResult result = mockMvc.perform(get("/weather"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNoContent())
                .andReturn();

        // then
        Mockito.verify(weatherService, Mockito.only()).getWeather();
    }

    @Test
    void isValid_getForecast_returnOk() throws Exception {
        // given
        Mockito.when(weatherService.getForecast())
                .thenReturn("");

        // when
        MvcResult result = mockMvc.perform(get("/weather/forecast"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        // then
        Mockito.verify(weatherService, Mockito.only()).getForecast();
    }

    private WeatherDto getCustomWeatherDto() {
        return new WeatherDto().setHumidity(1).setPressure(1).setTemperature(1).setWindSpeed(1);
    }
}