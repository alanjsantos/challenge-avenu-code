package com.alanjsantos.challenge.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_recorded", nullable = false)
    private LocalDateTime dateRecorded;

    private Long lat;
    private Long lon;
    private String city;
    private String state;

    private Double temperature;

}
