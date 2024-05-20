package com.bees.trainbookingapp.dto;

import lombok.Data;

@Data
public class TicketRequest {
    private UserRequest user;
    private String from;
    private String to;
    private TrainDto train;
    private String section;
    private double priceToBePaid;
}
