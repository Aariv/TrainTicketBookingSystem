package com.bees.trainbookingapp.repository;

import com.bees.trainbookingapp.domain.Ticket;
import com.bees.trainbookingapp.domain.User;

import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long>
{
  Ticket findByUser( User existingUser );
}
