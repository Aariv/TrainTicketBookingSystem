package com.bees.trainbookingapp.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "app_user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String lastName;
  private String email;

  @OneToOne(mappedBy = "user")
  private Seat seat;
}
