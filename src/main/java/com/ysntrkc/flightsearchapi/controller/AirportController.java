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

	@GetMapping("/city/{city}")
	public ResponseEntity<Airport> getByCity(@PathVariable String city) {
		Airport airport = airportService.getByCity(city);
		if (airport != null) {
			return new ResponseEntity<>(airport, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<Airport> create(Airport airport) {
		return new ResponseEntity<>(airportService.create(airport), HttpStatus.CREATED);
	}

	@PutMapping("/{airportId}")
	public ResponseEntity<Airport> update(@PathVariable int airportId, Airport updatedAirport) {
		Airport airport = airportService.update(airportId, updatedAirport);
		if (airport != null) {
			return new ResponseEntity<>(airport, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{airportId}")
	public ResponseEntity<Airport> delete(@PathVariable int airportId) {
		if (airportService.delete(airportId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
