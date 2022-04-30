package com.alanjsantos.challenge.controller;

import com.alanjsantos.challenge.models.Weather;
import com.alanjsantos.challenge.service.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest( controllers = WeatherController.class )
@AutoConfigureMockMvc
public class WeatherControllerTest {

    static final String API = "/weather";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WeatherService service;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void weatherSaveSuccess() throws Exception {
        var weather = new Weather();
        weather.setId(1L);
        weather.setCity("TESTE");
        weather.setState("TESTE");
        weather.setLat(10F);
        weather.setLon(10F);
        weather.setDateRecorded(LocalDate.now());
        weather.setTemperature(120.0);
        var request = MockMvcRequestBuilders.post(API)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(weather));
        when(service.save(weather)).thenReturn(weather);

        mvc.perform(request).andExpect(status().isCreated());
    }

    @Test
    public void weatherGetAllSuccess() throws Exception {
        var weather = new Weather();
        List<Weather> list = new ArrayList<>();
        list.add(weather);
        var request = MockMvcRequestBuilders.get(API)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(weather));
        when(service.getAll()).thenReturn(list);

        mvc.perform(request).andExpect(status().isOk());
    }

    @Test
    public void weatherGetIdSuccess() throws Exception {
        var weather = new Weather();
        weather.setId(1L);
        var request = MockMvcRequestBuilders.get(API)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(weather));
        when(service.findId(weather.getId())).thenReturn(weather);

        mvc.perform(request).andExpect(status().isOk());
    }

    @Test
    public void weatherDeleteIdSuccess() throws Exception {
        var weather = new Weather();
        weather.setId(1L);
        var request = MockMvcRequestBuilders.delete(API + "/1")
                .contentType("application/json");
        when(service.delete(weather.getId())).thenReturn(weather);

        mvc.perform(request).andExpect(status().isNoContent());
    }
}
