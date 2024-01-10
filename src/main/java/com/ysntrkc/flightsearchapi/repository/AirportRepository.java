package com.ysntrkc.flightsearchapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysntrkc.flightsearchapi.model.Airport;

public interface AirportRepository extends JpaRepository<Airport, Integer> {
}
