package com.alanjsantos.challenge.service;

import com.alanjsantos.challenge.models.Weather;
import com.alanjsantos.challenge.repository.WeatherRepository;
import com.alanjsantos.challenge.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository repository;


    public Weather save (Weather weather) {
        return repository.save(weather);
    }

    public List<Weather> getAll () {
        return repository.findAll();
    }

    public Weather findId (Long id) {
        Optional<Weather> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("ID " + id +  " This ID not aready exists in the database"));
    }

    public Weather delete(Long id) {
        var weather =  findId(id);
        repository.deleteById(id);

        return weather;
    }
}
