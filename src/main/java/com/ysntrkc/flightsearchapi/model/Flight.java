package com.ysntrkc.flightsearchapi.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"Flights\"")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "departure_airport_id")
	private Airport departureAirport;

	@ManyToOne
	@JoinColumn(name = "arrival_airport_id")
	private Airport arrivalAirport;

	@Column(name = "departure_time")
	private Date departureTime;

	@Column(name = "arrival_time")
	private Date arrivalTime;

	@Column(name = "price")
	private Double price;

	public Flight() {
	}

	public Flight(Airport departureAirport, Airport arrivalAirport, Date departureTime, Date arrivalTime,
			Double price) {
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(Airport departureAirport) {
		this.departureAirport = departureAirport;
	}

	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(Airport arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", departureAirport=" + departureAirport + ", arrivalAirport=" + arrivalAirport
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", price=" + price + "]";
	}

}
