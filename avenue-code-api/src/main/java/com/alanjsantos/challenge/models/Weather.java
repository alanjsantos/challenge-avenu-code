package com.alanjsantos.challenge.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_recorded", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("date_recorded")
    private LocalDate dateRecorded;

    private Float lat;
    private Float lon;
    private String city;
    private String state;
    private Double temperature;


    @PrePersist
    public void prePersist() {
        dateRecorded = LocalDate.now();
    }

}
