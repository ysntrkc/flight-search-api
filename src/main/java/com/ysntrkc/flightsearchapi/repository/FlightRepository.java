package com.ysntrkc.flightsearchapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ysntrkc.flightsearchapi.model.Airport;
import com.ysntrkc.flightsearchapi.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

	List<Flight> findByDepartureAirportAndArrivalAirport(
			Airport departureAirport,
			Airport arrivalAirport);

	List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDate(
			Airport departureAirport,
			Airport arrivalAirport,
			Date departureDate);

	List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateAndReturnDate(
			Airport departureAirport,
			Airport arrivalAirport,
			Date departureDate,
			Date returnDate);

}
