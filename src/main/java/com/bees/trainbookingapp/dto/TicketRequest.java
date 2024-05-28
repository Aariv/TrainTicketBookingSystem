package com.bees.trainbookingapp.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class TicketRequest {

    @NotNull
    private UserRequest user;
    @NotEmpty
    private String from;
    @NotEmpty
    private String to;
    @NotNull
    private TrainDto train;
    @NotEmpty
    private String section;

//    @Min( value = 1, message = "least minimum 1")
//    @Max( value = 10, message = "least minimum 1")
    private double priceToBePaid;

    private int numberOfTickets;

}
