package com.ysntrkc.flightsearchapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ysntrkc.flightsearchapi.model.Flight;

@Service
public interface FlightService {

	List<Flight> getAll();

	Optional<Flight> getById(int flightId);

	Flight create(Flight flight);

	Flight update(int flightId, Flight updatedFlight);

	boolean delete(int flightId);

}
