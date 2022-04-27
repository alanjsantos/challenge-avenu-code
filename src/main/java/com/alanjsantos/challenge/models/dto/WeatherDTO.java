package com.alanjsantos.challenge.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDTO {

    private Integer id;

    @JsonProperty("date_recorded")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dateRecorded;

    private Float lat;
    private Float lon;
    private String city;
    private String state;
    private Double temperature;

}
