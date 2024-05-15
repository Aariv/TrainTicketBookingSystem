package com.bees.trainbookingapp.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bees.trainbookingapp.dto.*;
import com.bees.trainbookingapp.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(TicketController.class)
public class TicketControllerWebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testPurchaseTicket() throws Exception {
        // Given
        TicketRequest request = createTicketDto();

        // When & Then
        mockMvc.perform(post("/api/v1/tickets/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Ticket is created successfully"));
    }

    @Test
    public void testGetReceiptDetails() throws Exception {
        // Given
        Long userId = 1L;
        TicketResponse response = constructResponse(userId);

        when(ticketService.getReceiptDetails(anyLong())).thenReturn(response);

        // When & Then
        mockMvc.perform(get("/api/v1/tickets/user/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private TicketResponse constructResponse(Long userId )
    {
        TicketResponse response = new TicketResponse();
        UserResponse user = new UserResponse();
        user.setId( userId );
        user.setFirstName( "Robin" );
        user.setLastName( "Singh" );
        user.setEmail( "robin@gmail.com" );
        response.setUser( user );
        response.setFrom( "London" );
        response.setTo( "USA" );
        response.setPricePaid( 5.0 );
        response.setSection( "A" );
        return response;
    }

    private TicketRequest createTicketDto()
    {
        TicketRequest request = new TicketRequest();
        UserRequest user = new UserRequest();
        user.setFirstName( "Robin" );
        user.setLastName( "Singh" );
        user.setEmail( "robin@gmail.com" );
        request.setUser( user );
        request.setFrom( "London" );
        request.setTo( "USA" );
        request.setPriceToBePaid( 5.0 );
        request.setSection( "A" );
        return request;
    }
}
