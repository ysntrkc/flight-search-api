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
import com.ysntrkc.flightsearchapi.implementation.AirportServiceImpl;
import com.ysntrkc.flightsearchapi.model.Airport;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

	private AirportServiceImpl airportService;

	public AirportController(AirportServiceImpl airportService) {
		this.airportService = airportService;
	}

	@GetMapping
	public ResponseEntity<Object> getAll() {
		List<Airport> airports = airportService.getAll();
		return ResponseHandler.generateResponse("Airports retrieved successfully.", HttpStatus.OK, airports);
	}

	@GetMapping("/{airportId}")
	public ResponseEntity<Object> getById(@PathVariable int airportId) {
		return airportService.getById(airportId)
				.map(airport -> ResponseHandler.generateResponse("Airport retrieved successfully.", HttpStatus.OK,
						airport))
				.orElse(ResponseHandler.generateResponse("Airport not found.", HttpStatus.NOT_FOUND, null));
	}

	@GetMapping("/city/{city}")
	public ResponseEntity<Object> getByCity(@PathVariable String city) {
		Airport airport = airportService.getByCity(city);
		if (airport != null) {
			return ResponseHandler.generateResponse("Airport retrieved successfully.", HttpStatus.OK, airport);
		}
		return ResponseHandler.generateResponse("Airport not found.", HttpStatus.NOT_FOUND, null);
	}

	@PostMapping
	public ResponseEntity<Object> create(Airport airport) {
		Airport createdAirport = airportService.create(airport);
		return ResponseHandler.generateResponse("Airport created successfully.", HttpStatus.CREATED, createdAirport);
	}

	@PutMapping("/{airportId}")
	public ResponseEntity<Object> update(@PathVariable int airportId, Airport updatedAirport) {
		Airport airport = airportService.update(airportId, updatedAirport);
		if (airport != null) {
			return ResponseHandler.generateResponse("Airport updated successfully.", HttpStatus.OK, airport);
		}
		return ResponseHandler.generateResponse("Airport not found.", HttpStatus.NOT_FOUND, null);
	}

	@DeleteMapping("/{airportId}")
	public ResponseEntity<Object> delete(@PathVariable int airportId) {
		boolean isDeleted = airportService.delete(airportId);
		if (isDeleted) {
			return ResponseHandler.generateResponse("Airport deleted successfully.", HttpStatus.OK, null);
		}
		return ResponseHandler.generateResponse("Airport not found.", HttpStatus.NOT_FOUND, null);
	}

}
