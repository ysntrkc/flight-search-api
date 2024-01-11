package com.ysntrkc.flightsearchapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ysntrkc.flightsearchapi.handler.ResponseHandler;
import com.ysntrkc.flightsearchapi.implementation.FlightServiceImpl;
import com.ysntrkc.flightsearchapi.model.Flight;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

	private FlightServiceImpl flightService;

	public FlightController(FlightServiceImpl flightService) {
		this.flightService = flightService;
	}

	@GetMapping
	public ResponseEntity<Object> getAll() {
		List<Flight> flights = flightService.getAll();
		return ResponseHandler.generateResponse("Flights retrieved successfully.", HttpStatus.OK, flights);
	}

	@GetMapping("/{flightId}")
	public ResponseEntity<Object> getById(@PathVariable int flightId) {
		return flightService.getById(flightId)
				.map(flight -> ResponseHandler.generateResponse("Flight retrieved successfully.", HttpStatus.OK,
						flight))
				.orElse(ResponseHandler.generateResponse("Flight not found.", HttpStatus.NOT_FOUND, null));
	}

	@PostMapping
	public ResponseEntity<Object> create(Flight flight) {
		Flight createdFlight = flightService.create(flight);
		return ResponseHandler.generateResponse("Flight created successfully.", HttpStatus.CREATED, createdFlight);
	}

	@PutMapping("/{flightId}")
	public ResponseEntity<Object> update(@PathVariable int flightId, Flight updatedFlight) {
		Flight flight = flightService.update(flightId, updatedFlight);
		if (flight != null) {
			return ResponseHandler.generateResponse("Flight updated successfully.", HttpStatus.OK, flight);
		}
		return ResponseHandler.generateResponse("Flight not found.", HttpStatus.NOT_FOUND, null);
	}

	@DeleteMapping("/{flightId}")
	public ResponseEntity<Object> delete(@PathVariable int flightId) {
		if (flightService.delete(flightId)) {
			return ResponseHandler.generateResponse("Flight deleted successfully.", HttpStatus.OK, null);
		}
		return ResponseHandler.generateResponse("Flight not found.", HttpStatus.NOT_FOUND, null);
	}

}
