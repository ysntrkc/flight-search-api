package com.ysntrkc.flightsearchapi.service;

import com.ysntrkc.flightsearchapi.model.Airport;

import java.util.List;
import java.util.Optional;


public interface AirportService {

	List<Airport> getAll();

	Optional<Airport> getById(int airportId);

	Airport getByCity(String city);

	Airport create(Airport airport);

	Airport update(int airportId, Airport updatedAirport);

	boolean delete(int airportId);

}
