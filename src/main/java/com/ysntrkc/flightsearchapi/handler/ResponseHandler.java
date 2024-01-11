package com.ysntrkc.flightsearchapi.handler;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("message", message);
		map.put("status", status.value());
		map.put("data", responseObj);
		return new ResponseEntity<>(map, status);
	}

}
