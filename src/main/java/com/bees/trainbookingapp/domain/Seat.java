package com.bees.trainbookingapp.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Seat
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String section;
  private int seatNumber;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;
}
