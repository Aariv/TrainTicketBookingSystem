package com.bees.trainbookingapp.controller;

import com.bees.trainbookingapp.dto.UserSeatModificationDTO;
import com.bees.trainbookingapp.dto.UserSeatResponse;
import com.bees.trainbookingapp.service.SeatService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag( name = "REST APIs for Seating Information", description = "REST APIs to know the seating details" )
@RestController
@RequestMapping("/api/v1/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @Operation(summary = "Get Users Information based on the section", description = "REST API to get users information based on the section")
    @GetMapping("/{section}")
    public ResponseEntity<List<UserSeatResponse>> getUsersBySection( @PathVariable String section) {
        List<UserSeatResponse> users = seatService.getUsersBySection(section);
        return ResponseEntity.ok( users );
    }

    @Operation(summary = "Remove User from the Train", description = "REST API to remove user from the train based on the user id")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> removeUserFromTrain(@PathVariable Long id) {
        seatService.removeUserFromTrain(id);
        return ResponseEntity.ok("User removed from the train.");
    }

    @Operation(summary = "Modify the seating", description = "REST API to modify the seating of a user")
    @PutMapping("/user/{id}/seat")
    public ResponseEntity<String> modifyUserSeat(@PathVariable Long id, @RequestBody UserSeatModificationDTO userSeatModificationDTO) {
        seatService.modifyUserSeat(id, userSeatModificationDTO );
        return ResponseEntity.ok("User's seat modified successfully.");
    }
}
