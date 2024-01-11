package com.ysntrkc.flightsearchapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ysntrkc.flightsearchapi.model.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {

	Airport findByCity(String city);

}
