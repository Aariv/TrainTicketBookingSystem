package com.bees.trainbookingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.*;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Train Booking REST API Documentation",
        description = "Train Booking REST API Documentation",
        version = "v1",
        contact = @Contact(
            name = "Ariv",
            email = "arivbits@gmail.com",
            url = "https://aariv.github.io"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "https://aariv.github.io"
        )
    ),
    externalDocs = @ExternalDocumentation(
        description =  "Train Booking REST API Documentation",
        url = "http://localhost:8080/swagger-ui.html"
    )
)
public class TrainBookingAppApplication
{

  public static void main( String[] args )
  {
    SpringApplication.run( TrainBookingAppApplication.class, args );
  }

}
