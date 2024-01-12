package com.ysntrkc.flightsearchapi.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ysntrkc.flightsearchapi.model.Airport;
import com.ysntrkc.flightsearchapi.repository.AirportRepository;
import com.ysntrkc.flightsearchapi.service.AirportService;

@Service
public class AirportServiceImpl implements AirportService {

	private AirportRepository airportRepository;

	public AirportServiceImpl(AirportRepository airportRepository) {
		this.airportRepository = airportRepository;
	}

	@Override
	public List<Airport> getAll() {
		return airportRepository.findAll();
	}

	@Override
	public Optional<Airport> getById(int airportId) {
		return airportRepository.findById(airportId);
	}

	@Override
	public Airport getByCity(String city) {
		return airportRepository.findByCity(city);
	}

	@Override
	public Airport create(Airport airport) {
		if (airportRepository.findByCity(airport.getCity()) != null) {
			return null;
		}
		return airportRepository.save(airport);
	}

	@Override
	public Airport update(int airportId, Airport updatedAirport) {
		Optional<Airport> existingAirport = airportRepository.findById(airportId);

		if (existingAirport.isPresent()) {
			Airport airportToUpdate = existingAirport.get();
			airportToUpdate.setCity(updatedAirport.getCity());
			return airportRepository.save(airportToUpdate);
		}

		return null;
	}

	@Override
	public boolean delete(int airportId) {
		if (airportRepository.existsById(airportId)) {
			airportRepository.deleteById(airportId);
			return true;
		}
		return false;
	}

}
