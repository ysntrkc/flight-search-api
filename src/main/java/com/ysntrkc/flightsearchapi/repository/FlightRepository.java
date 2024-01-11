package com.ysntrkc.flightsearchapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ysntrkc.flightsearchapi.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
