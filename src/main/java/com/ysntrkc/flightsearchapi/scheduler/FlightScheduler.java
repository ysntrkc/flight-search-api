package com.ysntrkc.flightsearchapi.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ysntrkc.flightsearchapi.implementation.AirportServiceImpl;
import com.ysntrkc.flightsearchapi.implementation.FlightServiceImpl;
import com.ysntrkc.flightsearchapi.implementation.ThirdPartyApiServiceImpl;
import com.ysntrkc.flightsearchapi.model.Airport;
import com.ysntrkc.flightsearchapi.model.Flight;

import jakarta.transaction.Transactional;

@Component
public class FlightScheduler {

	private FlightServiceImpl flightService;
	private ThirdPartyApiServiceImpl mockApiService;
	private AirportServiceImpl airportService;

	public FlightScheduler(FlightServiceImpl flightService, ThirdPartyApiServiceImpl mockApiService,
			AirportServiceImpl airportService) {
		this.flightService = flightService;
		this.mockApiService = mockApiService;
		this.airportService = airportService;
	}

	// Scheduled task to run every day at 00:00
	@Transactional
	@Scheduled(cron = "0 0 0 * * ?")
	public void performScheduledTask() {
		List<Flight> flights = mockApiService.fetchFlights();
		for (Flight flight : flights) {
			Airport departureAirport = airportService.getByCity(flight.getDepartureAirport().getCity());
			if (departureAirport == null) {
				departureAirport = airportService.create(flight.getDepartureAirport());
			}
			flight.setDepartureAirport(departureAirport);

			Airport arrivalAirport = airportService.getByCity(flight.getArrivalAirport().getCity());
			if (arrivalAirport == null) {
				arrivalAirport = airportService.create(flight.getArrivalAirport());
			}
			flight.setArrivalAirport(arrivalAirport);

			flightService.create(flight);
		}
		System.out.println("\033[32mScheduled task performed successfully.\033[0m");
	}

}
