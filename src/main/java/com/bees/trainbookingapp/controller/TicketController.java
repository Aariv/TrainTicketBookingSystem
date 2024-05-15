package com.bees.trainbookingapp.controller;

import com.bees.trainbookingapp.dto.TicketRequest;
import com.bees.trainbookingapp.dto.TicketResponse;
import com.bees.trainbookingapp.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag( name = "REST APIs for Ticket Booking", description = "REST APIs to purchase new tickets and get receipt details" )
@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Operation(summary = "Purchase new ticket", description = "REST API to book a new ticket for a user")
    @PostMapping("/purchase")
    public ResponseEntity<String> purchaseTicket( @RequestBody TicketRequest request) {
        ticketService.purchaseTicket(request);
        return new ResponseEntity<>( "Ticket is created successfully", HttpStatus.CREATED);
    }

    @Operation(summary = "Get ticket details", description = "REST API to get ticket for a user")
    @GetMapping("/user/{id}")
    public ResponseEntity<TicketResponse> getReceiptDetails(@PathVariable Long id) {
        TicketResponse receipt = ticketService.getReceiptDetails(id);
        if (receipt != null) {
            return ResponseEntity.ok(receipt);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
