package com.bees.trainbookingapp.dto;

import lombok.Data;

@Data
public class UserSeatResponse
{
  private UserRequest user;
  private SeatDTO seat;
}
