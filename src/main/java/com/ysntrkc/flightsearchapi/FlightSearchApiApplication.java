package com.ysntrkc.flightsearchapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Flight Search API", version = "0.1", description = "Documentation Flight Search API v0.1"))
public class FlightSearchApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightSearchApiApplication.class, args);
	}

}
