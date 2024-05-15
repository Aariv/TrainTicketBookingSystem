package com.bees.trainbookingapp.dto;

import lombok.Data;

@Data
public class TicketResponse
{
    private UserResponse user;
    private String from;
    private String to;
    private String section;
    private double pricePaid;
    private int seatNumber;

    public TicketResponse( UserResponse user, String from, String to, String section, double pricePaid, int seatNumber )
    {
        this.user = user;
        this.from = from;
        this.to = to;
        this.section = section;
        this.pricePaid = pricePaid;
        this.seatNumber = seatNumber;
    }
}
