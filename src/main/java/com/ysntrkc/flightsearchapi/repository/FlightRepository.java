package com.ysntrkc.flightsearchapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysntrkc.flightsearchapi.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
