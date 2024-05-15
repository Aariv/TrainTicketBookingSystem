package com.bees.trainbookingapp.repository;

import com.bees.trainbookingapp.domain.Seat;
import com.bees.trainbookingapp.domain.User;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SeatRepository extends CrudRepository<Seat, Long>
{
  Seat findFirstBySectionAndUserIsNull( String section );

  Seat findFirstBySectionAndUser( String section, User user );

  List<Seat> findBySection( String section );

  Seat findByUser( User existingUser );
}
