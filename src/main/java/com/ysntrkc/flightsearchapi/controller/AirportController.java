package com.ysntrkc.flightsearchapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ysntrkc.flightsearchapi.model.Airport;
import com.ysntrkc.flightsearchapi.service.AirportService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

	private AirportService airportService;

	public AirportController(AirportService airportService) {
		this.airportService = airportService;
	}

	@GetMapping("/")
	public ResponseEntity<List<Airport>> getAll() {
		List<Airport> airports = airportService.getAll();
		return new ResponseEntity<>(airports, HttpStatus.OK);
	}

	@GetMapping("/{airportId}")
	public ResponseEntity<Airport> getById(@PathVariable int airportId) {
		return airportService.getById(airportId)
				.map(airport -> new ResponseEntity<>(airport, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}
