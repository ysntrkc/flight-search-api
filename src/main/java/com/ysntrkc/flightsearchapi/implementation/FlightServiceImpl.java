package com.ysntrkc.flightsearchapi.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ysntrkc.flightsearchapi.model.Flight;
import com.ysntrkc.flightsearchapi.repository.FlightRepository;
import com.ysntrkc.flightsearchapi.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

	private FlightRepository flightRepository;

	public FlightServiceImpl(FlightRepository flightRepository) {
		this.flightRepository = flightRepository;
	}

	@Override
	public List<Flight> getAll() {
		return flightRepository.findAll();
	}

	@Override
	public Optional<Flight> getById(int flightId) {
		return flightRepository.findById(flightId);
	}

	@Override
	public Flight create(Flight flight) {
		return flightRepository.save(flight);
	}

	@Override
	public Flight update(int flightId, Flight updatedFlight) {
		Optional<Flight> existingFlight = flightRepository.findById(flightId);

		if (existingFlight.isPresent()) {
			Flight flightToUpdate = existingFlight.get();
			flightToUpdate.setDepartureAirport(updatedFlight.getDepartureAirport());
			flightToUpdate.setArrivalAirport(updatedFlight.getArrivalAirport());
			flightToUpdate.setDepartureTime(updatedFlight.getDepartureTime());
			flightToUpdate.setArrivalTime(updatedFlight.getArrivalTime());
			flightToUpdate.setPrice(updatedFlight.getPrice());
			return flightRepository.save(flightToUpdate);
		}

		return null;
	}

	@Override
	public boolean delete(int flightId) {
		if (flightRepository.existsById(flightId)) {
			flightRepository.deleteById(flightId);
			return true;
		}
		return false;
	}

}
