package com.bees.trainbookingapp.service;

import com.bees.trainbookingapp.domain.*;
import com.bees.trainbookingapp.dto.*;
import com.bees.trainbookingapp.exception.UserNotFoundException;
import com.bees.trainbookingapp.repository.*;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeatService
{
  @Autowired
  private SeatRepository seatRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TicketRepository ticketRepository;

  public List<UserSeatResponse> getUsersBySection( String section) {
    List<Seat> seats = seatRepository.findBySection( section);
    List<UserSeatResponse> users = new ArrayList<>();

    for (Seat seat : seats) {
      User user = seat.getUser();
      if (user != null) {
        UserRequest userDTO = new UserRequest();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());

        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setSection(seat.getSection());
        seatDTO.setSeatNumber(seat.getSeatNumber());

        UserSeatResponse userSeatResponse = new UserSeatResponse();
        userSeatResponse.setUser(userDTO);
        userSeatResponse.setSeat(seatDTO);

        users.add(userSeatResponse);
      }
    }
    return users;
  }

  @Transactional
  public void removeUserFromTrain(Long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    Seat seat = user.getSeat();
    if (seat != null) {
      seatRepository.delete(seat);
    }
    Ticket ticket = ticketRepository.findByUser( user ).orElse( null );
    if(ticket != null) {
      ticketRepository.delete(ticket);
    }
    userRepository.delete(user);
  }

  public void modifyUserSeat( Long id, UserSeatModificationDTO userSeatModificationDTO )
  {
    User user = userRepository.findById( id ).orElseThrow(() -> new UserNotFoundException("User not found"));
    if (user != null) {
      Seat currentSeat = user.getSeat();
      if (currentSeat != null) {
        currentSeat.setUser(null);
        seatRepository.save(currentSeat);
      }
      currentSeat.setSeatNumber( new Random().nextInt() );
      currentSeat.setSection( userSeatModificationDTO.getSection() );
      currentSeat.setUser(user);
      seatRepository.save(currentSeat);
    }
  }
}
