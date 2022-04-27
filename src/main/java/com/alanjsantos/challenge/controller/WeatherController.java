package com.alanjsantos.challenge.controller;

import com.alanjsantos.challenge.models.Wather;
import com.alanjsantos.challenge.models.dto.WatherDTO;
import com.alanjsantos.challenge.service.WeatherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("wather")
public class WeatherController {

    @Autowired
    private WeatherService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<WatherDTO> save (@RequestBody WatherDTO dto) {
        Wather wather  =
                service.save(modelMapper.map(dto, Wather.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(wather, WatherDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<WatherDTO>> getAll () {
        List<WatherDTO> body =
                service.getAll().stream()
                        .map(entity -> modelMapper.map(entity, WatherDTO.class))
                        .collect(Collectors.toList());
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("{id}")
    public ResponseEntity<WatherDTO> getId(@PathVariable Integer id) {
        var wather = service.getId(id);
        var dto = modelMapper.map(wather, WatherDTO.class);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<WatherDTO> delete (@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
