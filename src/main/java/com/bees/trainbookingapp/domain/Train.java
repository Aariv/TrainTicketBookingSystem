package com.bees.trainbookingapp.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String trainNumber;
    private String trainName;
    private int capacity;
}
