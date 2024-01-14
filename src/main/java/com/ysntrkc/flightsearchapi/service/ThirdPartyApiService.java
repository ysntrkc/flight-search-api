package com.ysntrkc.flightsearchapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ysntrkc.flightsearchapi.model.Flight;

@Service
public interface ThirdPartyApiService {

	List<Flight> fetchFlights();

}
