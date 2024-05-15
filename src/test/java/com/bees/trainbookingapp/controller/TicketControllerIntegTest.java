package com.bees.trainbookingapp.controller;

import static org.junit.Assert.assertEquals;

import com.bees.trainbookingapp.AbstractTest;
import com.bees.trainbookingapp.dto.TicketRequest;
import com.bees.trainbookingapp.dto.UserRequest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class TicketControllerIntegTest extends AbstractTest
{

  @Override
  @Before
  public void setUp() {
    super.setUp();
  }

  @Test
  public void purchaseTicket_shouldCreateTicketSuccess() throws Exception {
    String uri = "/api/v1/tickets/purchase";
    TicketRequest request = createTicketDto();
    String inputJson = super.mapToJson(request);
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                                          .contentType(MediaType.APPLICATION_JSON_VALUE)
                                          .content(inputJson)).andReturn();

    int status = mvcResult.getResponse().getStatus();
    assertEquals(201, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals(content, "Ticket is created successfully");
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
