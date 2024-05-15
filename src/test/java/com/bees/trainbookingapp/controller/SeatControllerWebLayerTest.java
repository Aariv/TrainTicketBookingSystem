package com.bees.trainbookingapp.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bees.trainbookingapp.dto.*;
import com.bees.trainbookingapp.service.SeatService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest( SeatController.class)
public class SeatControllerWebLayerTest
{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeatService seatService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetUsersBySection_EmptyResult() throws Exception {

        List<UserSeatResponse> response = Arrays.asList();
        when(seatService.getUsersBySection(anyString())).thenReturn(response);

        // When & Then
        String section = "A";
        mockMvc.perform( get( "/api/v1/seats/{section}", section)
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void testGetUsersBySection() throws Exception {
        // Given
        UserRequest user = new UserRequest();
        user.setFirstName( "F" );
        user.setLastName( "L" );
        user.setEmail( "temp@gdg.com" );

        UserRequest user2 = new UserRequest();
        user2.setFirstName( "F" );
        user2.setLastName( "L" );
        user2.setEmail( "temp@gdg.com" );

        String section = "A";
        UserSeatResponse response1 = new UserSeatResponse();
        SeatDTO seat = new SeatDTO();
        seat.setSeatNumber( 1 );
        seat.setSection( "A" );
        response1.setSeat( seat );
        response1.setUser( user );

        UserSeatResponse response2 = new UserSeatResponse();
        SeatDTO seat2 = new SeatDTO();
        seat2.setSeatNumber( 1 );
        seat2.setSection( "A" );
        response2.setSeat( seat2 );

        response2.setUser( user2 );

        List<UserSeatResponse> response = Arrays.asList(response1, response2);
        when(seatService.getUsersBySection(anyString())).thenReturn(response);

        // When & Then
        mockMvc.perform(get("/api/v1/seats/{section}", section)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testRemoveUserFromTrain() throws Exception {
        // When & Then
        Long userId = 1L;
        mockMvc.perform( delete( "/api/v1/seats/user/{id}", userId)
                             .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void testModifyUserSeat() throws Exception {
        // When & Then
        Long userId = 1L;
        UserSeatModificationDTO userSeatModificationDTO = new UserSeatModificationDTO( );
        userSeatModificationDTO.setSection( "A" );

        mockMvc.perform(put("/api/v1/seats/user/{id}", userId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(userSeatModificationDTO)))
            .andExpect(status().isOk())
            .andExpect(content().string("User's seat modified successfully."));

    }
}
