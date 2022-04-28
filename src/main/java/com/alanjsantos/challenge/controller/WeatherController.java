package com.alanjsantos.challenge.controller;

import com.alanjsantos.challenge.models.Weather;
import com.alanjsantos.challenge.models.dto.WeatherDTO;
import com.alanjsantos.challenge.service.WeatherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("weather")
public class WeatherController {

    @Autowired
    private WeatherService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Weather> save (@RequestBody Weather weather) {
         weather =
                service.save(weather);
        return ResponseEntity.status(HttpStatus.CREATED).body(weather);
    }

    @GetMapping
    public ResponseEntity<List<Weather>> getAll () {
        List<Weather> body =
                service.getAll();
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("{id}")
    public ResponseEntity<Weather> getId(@PathVariable Long id) {
        var wather = service.findId(id);

        return ResponseEntity.ok().body(wather);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Weather> delete (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
