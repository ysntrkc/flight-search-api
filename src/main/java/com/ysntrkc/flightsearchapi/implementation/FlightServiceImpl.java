package com.ysntrkc.flightsearchapi.implementation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ysntrkc.flightsearchapi.model.Airport;
import com.ysntrkc.flightsearchapi.model.Flight;
import com.ysntrkc.flightsearchapi.repository.AirportRepository;
import com.ysntrkc.flightsearchapi.repository.FlightRepository;
import com.ysntrkc.flightsearchapi.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

	private FlightRepository flightRepository;
	private AirportRepository airportRepository;

	public FlightServiceImpl(FlightRepository flightRepository, AirportRepository airportRepository) {
		this.flightRepository = flightRepository;
		this.airportRepository = airportRepository;
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
		if (flight == null) {
			return null;
		} else if (flight.getReturnDate() != null && (flight.getReturnDate().before(flight.getDepartureDate()))) {
			return null;
		}
		return flightRepository.save(flight);
	}

	@Override
	public Flight update(int flightId, Flight updatedFlight) {
		Optional<Flight> existingFlight = flightRepository.findById(flightId);

		if (existingFlight.isPresent()) {
			Flight flightToUpdate = existingFlight.get();
			flightToUpdate.setDepartureAirport(updatedFlight.getDepartureAirport());
			flightToUpdate.setArrivalAirport(updatedFlight.getArrivalAirport());
			flightToUpdate.setDepartureDate(updatedFlight.getDepartureDate());
			flightToUpdate.setReturnDate(updatedFlight.getReturnDate());
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

	@Override
	// return count of flights
	public List<Flight> search(int departureAirportId, int arrivalAirportId, Date departureDate,
			Date returnDate) {
		if (departureAirportId == 0 || arrivalAirportId == 0) {
			return null;
		}

		Airport departureAirport = airportRepository.findById(departureAirportId).map(airport -> airport).orElse(null);
		Airport arrivalAirport = airportRepository.findById(arrivalAirportId).map(airport -> airport).orElse(null);
		if (departureAirport == null || arrivalAirport == null) {
			return null;
		}

		if (departureDate == null && returnDate == null) {
			return flightRepository.findByDepartureAirportAndArrivalAirport(departureAirport, arrivalAirport);
		} else if (returnDate == null) {
			return flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDate(
					departureAirport,
					arrivalAirport,
					departureDate);
		} else {
			return flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateAndReturnDate(
					departureAirport,
					arrivalAirport,
					departureDate,
					returnDate);
		}
	}

}
