package com.ysntrkc.flightsearchapi.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ysntrkc.flightsearchapi.model.Airport;
import com.ysntrkc.flightsearchapi.model.Flight;
import com.ysntrkc.flightsearchapi.repository.AirportRepository;
import com.ysntrkc.flightsearchapi.service.ThirdPartyApiService;

@Service
public class ThirdPartyApiServiceImpl implements ThirdPartyApiService {

	private AirportRepository airportRepository;

	public ThirdPartyApiServiceImpl(AirportRepository airportRepository) {
		this.airportRepository = airportRepository;
	}

	@Override
	public List<Flight> fetchFlights() {
		List<Flight> flights = new ArrayList<>();

		// Mock data
		List<Airport> airports = airportRepository.findAll();
		if (airports.size() < 2) {
			Airport airport1 = new Airport(UUID.randomUUID().toString());
			Airport airport2 = new Airport(UUID.randomUUID().toString());
			airportRepository.save(airport1);
			airportRepository.save(airport2);
			airports = airportRepository.findAll();
		}

		// create 5 flights
		for (int i = 0; i < 5; i++) {
			List<Airport> airportsCopy = new ArrayList<>(airports);
			Flight flight = new Flight();

			// get random airports
			int randomAirportIndex = new Random().nextInt(airportsCopy.size());
			flight.setDepartureAirport(airportsCopy.get(randomAirportIndex));
			airportsCopy.remove(randomAirportIndex);

			int randomAirportIndex2 = new Random().nextInt(airportsCopy.size());
			flight.setArrivalAirport(airportsCopy.get(randomAirportIndex2));

			// get random dates after today
			int random = new Random().nextInt(30);
			flight.setDepartureDate(new Date(System.currentTimeMillis() + random * 86400000L));
			flight.setReturnDate(new Date(System.currentTimeMillis() + (random + i + 1) * 86400000L));

			// get random price
			flight.setPrice(new Random().nextDouble() * 1000);

			// add to list
			flights.add(flight);
		}

		return flights;
	}

}
