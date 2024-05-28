package com.bees.trainbookingapp.repository;

import com.bees.trainbookingapp.domain.Ticket;
import com.bees.trainbookingapp.domain.User;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long>
{
  Optional<Ticket> findByUser( User existingUser );
}
