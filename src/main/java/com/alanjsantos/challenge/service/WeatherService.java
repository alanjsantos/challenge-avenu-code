package com.alanjsantos.challenge.service;

import com.alanjsantos.challenge.models.Wather;
import com.alanjsantos.challenge.repository.WatherRepository;
import com.alanjsantos.challenge.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {

    @Autowired
    private WatherRepository repository;


    public Wather save (Wather wather) {
        return repository.save(wather);
    }

    public List<Wather> getAll () {
        return repository.findAll();
    }

    public Wather getId (Integer id) {
        Optional<Wather> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("ID " + id +  " This ID not aready exists in the database"));
    }

    public void delete(Integer id) {
        getId(id);
        repository.deleteById(id);
    }
}
