package com.ysntrkc.flightsearchapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ysntrkc.flightsearchapi.model.Airport;
import com.ysntrkc.flightsearchapi.repository.AirportRepository;

@Service
public class AirportService {

	private AirportRepository airportRepository;

	public AirportService(AirportRepository airportRepository) {
		this.airportRepository = airportRepository;
	}

	public List<Airport> getAll() {
		return airportRepository.findAll();
	}

	public Optional<Airport> getById(int airportId) {
		return airportRepository.findById(airportId);
	}

	public Airport create(Airport airport) {
		return airportRepository.save(airport);
	}

	public Airport update(int airportId, Airport updatedAirport) {
		Optional<Airport> existingAirport = airportRepository.findById(airportId);

		if (existingAirport.isPresent()) {
			Airport airportToUpdate = existingAirport.get();
			airportToUpdate.setCity(updatedAirport.getCity());
			return airportRepository.save(airportToUpdate);
		}

		return null;
	}

	public boolean delete(int airportId) {
		if (airportRepository.existsById(airportId)) {
			airportRepository.deleteById(airportId);
			return true;
		}
		return false;
	}

}
