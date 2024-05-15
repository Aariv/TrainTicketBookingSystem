package com.bees.trainbookingapp.service;

import com.bees.trainbookingapp.domain.*;
import com.bees.trainbookingapp.dto.*;
import com.bees.trainbookingapp.exception.*;
import com.bees.trainbookingapp.repository.*;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService
{
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private SeatRepository seatRepository;

  @Autowired
  private TicketRepository ticketRepository;

  public TicketResponse purchaseTicket( TicketRequest ticketDTO) {
    // Check if the user already exists in the database
    User existingUser = userRepository.findByEmail( ticketDTO.getUser().getEmail());
    User user;
    if(existingUser != null) {
      user = existingUser;
    } else {
      user = convertDTOToEntity(ticketDTO.getUser());
      userRepository.save(user);
    }

    // Allocate a seat based on availability in the requested section
    Seat seat = allocateSeat( ticketDTO.getSection(), user );

    // Calculate price
//    double price = 5.0; // Fixed price for now

    // Create and save the ticket
    Ticket ticket = new Ticket();
    ticket.setFrom(ticketDTO.getFrom());
    ticket.setTo(ticketDTO.getTo());
    ticket.setUser(user);
    ticket.setPricePaid(ticketDTO.getPriceToBePaid());
    // Save ticket to database or any other storage mechanism
    ticketRepository.save( ticket );
    return new TicketResponse( convertEntityToUserResponse( user ), ticketDTO.getFrom(), ticketDTO.getTo(), seat.getSection(), ticket.getPricePaid(), seat.getSeatNumber());
  }

  private User convertDTOToEntity( UserRequest user )
  {
    User entity = new User();
    entity.setEmail(user.getEmail());
    entity.setFirstName(user.getFirstName());
    entity.setLastName(user.getLastName());
    return entity;
  }

  private Seat allocateSeat(String section, User user) {
    // Try to find an available seat in section A
    Seat seat = seatRepository.findFirstBySectionAndUser(section, user );
    if (seat != null)
    {
      throw new RuntimeException("Seat already taken");
    }
    // If a seat is available, assign it to the user and save the updated seat information
    Seat newSeat = new Seat();
    newSeat.setSeatNumber( new Random().nextInt() );
    newSeat.setSection( section );
    newSeat.setUser(user);
    seatRepository.save(newSeat);
    return newSeat;
  }

  public TicketResponse getReceiptDetails( Long id )
  {
    User existingUser = userRepository.findById( id ).orElse(null);
    if(existingUser == null) {
      throw new UserNotFoundException( "User not found");
    }
    Ticket ticket = ticketRepository.findByUser( existingUser );
    if(ticket == null) {
      throw new TicketNotFoundException( "Ticket not found");
    }
    Seat seat = seatRepository.findByUser( existingUser );
    if(seat == null) {
      throw new SeatNotFoundException( "Seat not found");
    }
    return new TicketResponse( convertEntityToUserResponse( existingUser ), ticket.getFrom(), ticket.getTo(), seat.getSection(), ticket.getPricePaid(), seat.getSeatNumber());
  }

  private UserRequest convertEntityToDto( User existingUser )
  {
    UserRequest user = new UserRequest();
    user.setEmail(existingUser.getEmail());
    user.setFirstName(existingUser.getFirstName());
    user.setLastName(existingUser.getLastName());
    return user;
  }

  private UserResponse convertEntityToUserResponse( User existingUser )
  {
    UserResponse user = new UserResponse();
    user.setId( existingUser.getId() );
    user.setEmail(existingUser.getEmail());
    user.setFirstName(existingUser.getFirstName());
    user.setLastName(existingUser.getLastName());
    return user;
  }
}
