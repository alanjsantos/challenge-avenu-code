package com.alanjsantos.challenge.service;

import com.alanjsantos.challenge.models.Weather;
import com.alanjsantos.challenge.repository.WeatherRepository;
import com.alanjsantos.challenge.service.exception.ObjectNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;






@ExtendWith(SpringExtension.class)
public class WeatherServiceTest {

    @MockBean
    private WeatherRepository repository;

    @SpyBean
    private WeatherService service;

    @Test
    public void weatherSuccess() {
        var weather = new Weather();
        weather.setId(1L);
        weather.setCity("TESTE");
        weather.setState("TESTE");
        weather.setLat(10F);
        weather.setLon(10F);
        weather.setDateRecorded(LocalDate.now());
        weather.setTemperature(120.0);

        when(repository.save(any())).thenReturn(weather);
        service.save(weather);

    }

    @Test
    public void weatherGetIdSuccess() throws JsonProcessingException {
        var weather = new Weather();
        Long id = 1L;
        weather.setId(id);
        weather.setCity("TESTE");
        weather.setState("TESTE");
        weather.setLat(10F);
        weather.setLon(10F);
        weather.setDateRecorded(LocalDate.now());
        weather.setTemperature(120.0);
        when(repository.findById(id.intValue())).thenReturn(Optional.of(weather));

        Optional<Weather> result = Optional.ofNullable(service.findId(id));
        Assertions.assertThat(result.isPresent()).isTrue();

    }

    @Test
    public void weatherFindByIdNotFoundException() {
        var weather = new Weather();
        Long id = 1L;
        weather.setId(2L);
        weather.setCity("TESTE");
        weather.setState("TESTE");
        weather.setLat(10F);
        weather.setLon(10F);
        weather.setDateRecorded(LocalDate.now());
        weather.setTemperature(120.0);
        Throwable ex =
                Assertions.catchThrowable(() -> {
                    when(repository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
                    service.findId(id);
                });
        Assertions.assertThat(ex).isInstanceOf(ObjectNotFoundException.class);
    }

    @Test
    public void weatherDeleteSuccess() {
        var weather = new Weather();
        Long id = 1L;
        weather.setId(id);
        weather.setCity("TESTE");
        weather.setState("TESTE");
        weather.setLat(10F);
        weather.setLon(10F);
        weather.setDateRecorded(LocalDate.now());
        weather.setTemperature(120.0);
        when(repository.findById(weather.getId().intValue())).thenReturn(Optional.of(weather));
        doNothing().when(repository).deleteById(id.intValue());
        org.junit.jupiter.api.Assertions.assertEquals(weather, service.delete(id));
    }
}
