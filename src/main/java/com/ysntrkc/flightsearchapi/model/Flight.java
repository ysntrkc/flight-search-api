package com.ysntrkc.flightsearchapi.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

@Entity
@Table(name = "\"Flights\"")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "departure_airport_id")
	@NotNull(message = "Departure airport is mandatory")
	private Airport departureAirport;

	@ManyToOne
	@JoinColumn(name = "arrival_airport_id")
	@NotNull(message = "Arrival airport is mandatory")
	private Airport arrivalAirport;

	@Column(name = "departure_date")
	@NotNull(message = "Departure date is mandatory")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date departureDate;

	@Column(name = "return_date")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date returnDate;

	@Column(name = "price")
	@NotNull(message = "Price is mandatory")
	@Min(value = 0, message = "Price must be greater than 0")
	private Double price;

	public Flight() {
	}

	public Flight(Airport departureAirport, Airport arrivalAirport, Date departureDate, Date returnDate,
			Double price) {
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.departureDate = departureDate;
		this.returnDate = returnDate;
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

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureTime) {
		this.departureDate = departureTime;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date arrivalTime) {
		this.returnDate = arrivalTime;
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
				+ ", departureDate=" + departureDate + ", returnDate=" + returnDate + ", price=" + price + "]";
	}

}
